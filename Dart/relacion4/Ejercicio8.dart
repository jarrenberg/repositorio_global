/*8- Define una clase CuentaBancaria con los siguientes atributos:
● número de cuenta
● nombre del titular
● saldo (inicial, 0 euros)
● número de operaciones (inicial, 0)
Define también las siguientes operaciones:
● constructor
● toString
● depositar dinero
● extraer dinero
● transferir dinero*/

class CuentaBancaria {
  late int _numeroCuenta;
  late String _nombreTitular;
  late double _saldo;
  int _numeroOperaciones = 0;

  CuentaBancaria(int numeroCuenta, String nombreTitular, double saldo) {
    if (saldo < 0) {
      throw Exception("No se puede crear una cuenta con saldo negativo");
    }
    this._numeroCuenta = numeroCuenta;
    this._nombreTitular = nombreTitular;
    this._saldo = saldo;
  }

  @override
  String toString() {
    return "CUENTA BANCARIA = [NUMERO DE CUENTA: $_numeroCuenta, NOMBRE DEL TITULAR: $_nombreTitular, SALDO: $_saldo, NUMERO DE OPERACIONES: $_numeroOperaciones]";
  }

  void depositar(double cantidad) {
    if (cantidad < 0) {
      throw Exception("No se puede depositar una cantidad negativa de dinero");
    }
    this._saldo = _saldo + cantidad;
    this._numeroOperaciones += 1;
  }

  void extraer(double cantidad) {
    if (cantidad < 0) {
      throw Exception("No se puede extraer una cantidad negativa de dinero");
    }
    this._saldo = _saldo - cantidad;
    this._numeroOperaciones += 1;
  }

  void transferir(CuentaBancaria cb, double cantidad) {
    this.extraer(cantidad);
    cb.depositar(cantidad);
  }
}
