package com.betfair.esa.console.commands;

import static org.springframework.shell.standard.ShellOption.NULL;

import com.betfair.esa.client.Client;
import com.betfair.esa.client.ClientCache;
import com.betfair.esa.client.auth.AppKeyAndSessionProvider;
import com.betfair.esa.client.auth.InvalidCredentialException;
import com.betfair.esa.client.cache.market.*;
import com.betfair.esa.client.cache.order.OrderCache;
import com.betfair.esa.client.cache.order.OrderMarket;
import com.betfair.esa.client.cache.util.LevelPriceSize;
import com.betfair.esa.client.cache.util.OrderMarketSnap;
import com.betfair.esa.client.protocol.ConnectionException;
import com.betfair.esa.client.protocol.StatusException;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;

@ShellComponent
@Configuration
public class ClientCommands
        implements MarketCache.MarketChangeListener, OrderCache.OrderMarketChangeListener {

    public static final String CONFIG_PROPERTIES = "config.properties";
    public static final String HOST_NAME = "host";
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "userName";
    public static final String APP_KEY = "appKey";
    public static final String DEFAULT_HOST = "stream-api-integration.betfair.com";
    public static final int PORT = 443;

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final Properties properties = new Properties();
    private AppKeyAndSessionProvider sessionProvider;
    private ClientCache clientCache;
    private Client client;
    private boolean traceMarkets = false;
    private boolean traceOrders = false;

    public ClientCommands() throws IOException, InvalidCredentialException {
        loadConfig();

        if (getAppKey() != null) {
            newSessionProvider(getHost(), getAppKey(), getUserName(), getPassword());
            sessionProvider.getOrCreateNewSession();
        }
    }

    private ClientCache getClientCache() {

        if (clientCache == null) {
            if (sessionProvider == null) {
                logger.error("No login saved - Use SaveLogin");
                throw new IllegalStateException("No login saved - Use SaveLogin");
            } else {
                client = new Client(DEFAULT_HOST, PORT, sessionProvider);
                clientCache = new ClientCache(client);
                clientCache.getMarketCache().addMarketChangeListener(this);
                clientCache.getOrderCache().addOrderMarketChangeListener(this);
            }
        }

        return clientCache;
    }

    @Override
    public void marketChange(MarketCache.MarketChangeEvent marketChangeEvent) {
        if (traceMarkets) {
            printMarket(marketChangeEvent.getSnap());
        }
    }

    @Override
    public void orderChange(OrderCache.OrderMarketChangeEvent orderChangeEvent) {
        if (traceOrders) {
            printOrderMarket(orderChangeEvent.snap());
        }
    }

    @ShellMethod(key = "saveLogin", value = "Save Login - (not encrypted)")
    public void saveLogin(
            @ShellOption(
                            value = {"--host"},
                            defaultValue = "identitysso.betfair.com",
                            help = "sso host")
                    String host,
            @ShellOption(
                            value = {"--appKey"},
                            defaultValue = NULL,
                            help = "app key")
                    String appKey,
            @ShellOption(
                            value = {"--userName"},
                            defaultValue = NULL,
                            help = "user name")
                    String userName,
            @ShellOption(
                            value = {"--password"},
                            defaultValue = NULL,
                            help = "password")
                    String password)
            throws IOException, InvalidCredentialException {
        properties.setProperty(APP_KEY, appKey);
        properties.setProperty(USER_NAME, userName);
        properties.setProperty(PASSWORD, password);
        properties.setProperty(HOST_NAME, host);

        // set the login and verify before saving
        newSessionProvider(host, appKey, userName, password);
        // test it
        sessionProvider.getOrCreateNewSession();

        // save it
        properties.store(new FileOutputStream(CONFIG_PROPERTIES), "");
        System.out.println("saveLogin - saved credentials (un-encrypted) to: " + CONFIG_PROPERTIES);
    }

    @ShellMethod("subscribes to market(s) (comma separated with no spaces)")
    public void market(@ShellOption(defaultValue = NULL, help = "marketId") String... marketId)
            throws ConnectionException, StatusException, InvalidCredentialException {
        getClientCache().subscribeMarkets(marketId);
    }

    @ShellMethod(key = "marketFirehose", value = "subscribes to all markets")
    public void marketFirehose()
            throws ConnectionException, StatusException, InvalidCredentialException {
        getClientCache().subscribeMarkets();
    }

    @ShellMethod(key = "orders", value = "subscribes to orders")
    public void orders() throws ConnectionException, StatusException, InvalidCredentialException {
        getClientCache().subscribeOrders();
    }

    @ShellMethod(key = "listMarkets", value = "lists the cached markets")
    public void listMarkets() {
        for (Market market : getClientCache().getMarketCache().getMarkets()) {
            printMarket(market.getSnap());
        }
    }

    @ShellMethod(key = "listOrders", value = "lists the cached orders")
    public void listOrders() {
        for (OrderMarket orderMarket : getClientCache().getOrderCache().getOrderMarkets()) {
            printOrderMarket(orderMarket.getOrderMarketSnap());
        }
    }

    @ShellMethod(key = "traceMarkets", value = "trace Markets")
    public void traceMarkets() {
        traceMarkets = true;
    }

    @ShellMethod(key = "traceOrders", value = "trace Orders")
    public void traceOrders() {
        traceOrders = true;
    }

    @ShellMethod(key = "stop", value = "stops the client")
    public void stop() {
        client.stop();
    }

    @ShellMethod(key = "start", value = "starts the client")
    public void start() throws ConnectionException, StatusException, InvalidCredentialException {
        client.start();
    }

    @ShellMethod(key = "disconnect", value = "socket level disconnect - this will auto-reconnect")
    public void disconnect() {
        client.disconnect();
    }

    @ShellMethod(key = "traceMessages", value = "trace Messages (Markets and Orders)")
    public void traceMessages(@ShellOption(help = "truncate", defaultValue = "200") int truncate) {
        client.setTraceChangeTruncation(truncate);
    }

    private void printMarket(MarketSnap market) {
        market.getMarketRunners()
                .sort(Comparator.comparingInt(mr -> mr.getDefinition().getSortPriority()));

        List<MarketDetailsRow> marketDetails = new ArrayList<>();

        for (MarketRunnerSnap runner : market.getMarketRunners()) {
            MarketRunnerPrices snap = runner.getPrices();
            MarketDetailsRow marketDetail =
                    new MarketDetailsRow(
                            market.getMarketId(),
                            runner.getRunnerId().selectionId(),
                            getLevel(snap.getBdatb(), 0).getPrice(),
                            getLevel(snap.getBdatb(), 0).getSize(),
                            getLevel(snap.getBdatl(), 0).getPrice(),
                            getLevel(snap.getBdatl(), 0).getSize());
            marketDetails.add(marketDetail);
        }

        BeanListTableModel model =
                new BeanListTableModel(
                        marketDetails,
                        createHeader(
                                "marketId",
                                "selectionId",
                                "batbPrice",
                                "batbSize",
                                "batlPrice",
                                "batlSize"));
        renderTable(model);
    }

    private void printOrderMarket(OrderMarketSnap orderMarketSnap) {
        System.out.println("Orders  (marketid=" + orderMarketSnap.getMarketId() + ")");

        List<com.betfair.esa.swagger.model.Order> orders = new ArrayList<>();

        orderMarketSnap
                .getOrderMarketRunners()
                .forEach(
                        orderMarketRunnerSnap ->
                                orders.addAll(orderMarketRunnerSnap.getUnmatchedOrders().values()));

        BeanListTableModel model =
                new BeanListTableModel(
                        orders,
                        createHeader(
                                "id", "side", "pt", "ot", "status", "sv", "p", "sc", "rc", "s",
                                "pd", "rac", "md", "cd", "ld", "sl", "avp", "sm", "bsp", "sr"));
        renderTable(model);
    }

    private void renderTable(BeanListTableModel model) {
        org.springframework.shell.table.TableBuilder tableBuilder =
                new org.springframework.shell.table.TableBuilder(model);
        tableBuilder.addHeaderAndVerticalsBorders(BorderStyle.oldschool);
        Table table = tableBuilder.build();
        final String rendered = table.render(1000);
        System.out.println(rendered);
    }

    private LinkedHashMap<String, Object> createHeader(String... headers) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        for (String header : headers) {
            result.put(header, header);
        }
        return result;
    }

    private static LevelPriceSize getLevel(List<LevelPriceSize> values, int level) {
        return !values.isEmpty() ? values.get(0) : new LevelPriceSize(level, 0, 0);
    }

    private void loadConfig() {
        InputStream input = null;
        try {
            File configFile = new File(CONFIG_PROPERTIES);
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            input = new FileInputStream(CONFIG_PROPERTIES);
            if (input != null) {
                properties.load(input);
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void newSessionProvider(
            String ssoHost, String appKey, String userName, String password) {
        sessionProvider = new AppKeyAndSessionProvider(ssoHost, appKey, userName, password);
    }

    private String getAppKey() {
        return properties.getProperty(APP_KEY);
    }

    private String getPassword() {
        return properties.getProperty(PASSWORD);
    }

    private String getUserName() {
        return properties.getProperty(USER_NAME);
    }

    private String getHost() {
        return properties.getProperty(HOST_NAME);
    }

    class MarketDetailsRow {
        private final String marketId;
        private final long selectionId;
        private final double batbPrice;
        private final double batbSize;
        private final double batlPrice;
        private final double batlSize;

        public MarketDetailsRow(
                String marketId,
                long selectionId,
                double batbPrice,
                double batbSize,
                double batlPrice,
                double batlSize) {
            this.marketId = marketId;
            this.selectionId = selectionId;
            this.batbPrice = batbPrice;
            this.batbSize = batbSize;
            this.batlPrice = batlPrice;
            this.batlSize = batlSize;
        }

        public String getMarketId() {
            return marketId;
        }

        public long getSelectionId() {
            return selectionId;
        }

        public double getBatbPrice() {
            return batbPrice;
        }

        public double getBatbSize() {
            return batbSize;
        }

        public double getBatlPrice() {
            return batlPrice;
        }

        public double getBatlSize() {
            return batlSize;
        }
    }
}
