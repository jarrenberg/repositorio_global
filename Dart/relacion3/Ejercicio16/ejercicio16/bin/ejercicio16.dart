import 'package:intl/intl.dart';
import 'package:intl/date_symbol_data_local.dart';

void main(List<String> arguments) {
  print('Fecha actual en formato por defecto: ${DateTime.now()}');
  print('Locale del systema: ${Intl.systemLocale}');
  print('Locale por defecto: ${Intl.defaultLocale}');

  initializeDateFormatting('es_ES', null).then((_) {
    Intl.defaultLocale = 'es_ES';
    final formatter = DateFormat('dd MMMM yyyy HH:mm', 'es_ES');
    print('Fecha localizada: ${formatter.format(DateTime.now())}');
    print(
      'Fecha dentro de 5 dias: ${formatter.format(DateTime.now().add(Duration(days: 5)))}',
    );
    print(
      'Fecha hace de 7 horas: ${formatter.format(DateTime.now().subtract(Duration(hours: 7)))}',
    );
  });
}
