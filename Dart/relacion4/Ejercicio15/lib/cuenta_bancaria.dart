abstract class CuentaBancaria {
  void depositar(double cantidad);
  void extraer(double cantidad);
  void transferir(CuentaBancaria cb, double cantidad);
}