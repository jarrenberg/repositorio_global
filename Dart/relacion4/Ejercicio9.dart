/**9- En el ejercicio anterior, no estamos teniendo en cuenta si está permitido que una cuenta
tenga el saldo negativo (cuenta de crédito) o no (cuenta de débito). En realidad, todo es
prácticamente igual, salvo que en la de crédito, hay un límite máximo para el saldo negativo,
y en la de débito, no se puede extraer dinero si el saldo no es suficiente.
Reestructura el ejercicio y define la clase CuentaBancaria como abstracta e instancia dos
clases hijas CuentaDebito y CuentaCredito que tengan todo esto en cuenta en su
operatoria.
Incluye un main que defina un objeto de cada tipo y pruebe que se ejecuta correctamente.*/

abstract class CuentaBancaria {
  void depositar(double cantidad);
  void extraer(double cantidad);
  void transferir(CuentaBancaria cb, double cantidad);
}

class CuentaDebito extends CuentaBancaria {
  late int _numeroCuenta;
  late String _nombreTitular;
  late double _saldo;
  int _numeroOperaciones = 0;

  CuentaDebito(int numeroCuenta, String nombreTitular, double saldo) {
    if (saldo < 0) {
      throw Exception("No se puede crear una cuenta con saldo negativo");
    }
    this._numeroCuenta = numeroCuenta;
    this._nombreTitular = nombreTitular;
    this._saldo = saldo;
  }

  @override
  void depositar(double cantidad) {
    this._saldo = _saldo + cantidad;
    this._numeroOperaciones += 1;
  }

  @override
  void extraer(double cantidad) {
    if (cantidad > _saldo) {
      throw Exception(
        "No se puede extraer una cantidad superior al saldo de la cuenta",
      );
    }
    this._saldo = _saldo - cantidad;
    this._numeroOperaciones += 1;
  }

  @override
  void transferir(CuentaBancaria cb, double cantidad) {
    try {
      this.extraer(cantidad);
      cb.depositar(cantidad);
    } catch (Exception) {
      print(Exception);
    }
  }

  @override
  String toString() {
    return "CUENTA BANCARIA = [NUMERO DE CUENTA: $_numeroCuenta, NOMBRE DEL TITULAR: $_nombreTitular, SALDO: $_saldo, NUMERO DE OPERACIONES: $_numeroOperaciones]";
  }
}

class CuentaCredito extends CuentaBancaria {
  late int _numeroCuenta;
  late String _nombreTitular;
  late double _saldo;
  int _numeroOperaciones = 0;
  double _maximoNegativo = -1500.0;

  CuentaCredito(int numeroCuenta, String nombreTitular, double saldo) {
    if (saldo < 0) {
      throw Exception("No se puede crear una cuenta con saldo negativo");
    }
    this._numeroCuenta = numeroCuenta;
    this._nombreTitular = nombreTitular;
    this._saldo = saldo;
  }

  @override
  void depositar(double cantidad) {
    this._saldo = _saldo + cantidad;
    this._numeroOperaciones += 1;
  }

  @override
  void extraer(double cantidad) {
    if (this._saldo - cantidad < _maximoNegativo) {
      throw Exception(
        "No se puede extraer esta cantidad. Supera el maximo de credito",
      );
    }
    this._saldo = _saldo - cantidad;
    this._numeroOperaciones += 1;
  }

  @override
  void transferir(CuentaBancaria cb, double cantidad) {
    try {
      this.extraer(cantidad);
      cb.depositar(cantidad);
    } catch (Exception) {
      print(Exception);
    }
  }

  @override
  String toString() {
    return "CUENTA BANCARIA = [NUMERO DE CUENTA: $_numeroCuenta, NOMBRE DEL TITULAR: $_nombreTitular, SALDO: $_saldo, NUMERO DE OPERACIONES: $_numeroOperaciones, MAXIMO DESCUBIERTO: $_maximoNegativo]";
  }
}

void main(List<String> args) {
  print("\nCreando dos cuentas bancarias ...");
  CuentaCredito? cd;
  CuentaDebito? cc;
  try {
    cc = new CuentaDebito(123456, "Jorge", 500);
    cd = new CuentaCredito(456789, "Raquel", -1);
  } catch (Exception) {
    print(Exception);
    print("Creando una cuenta por defecto ...");
    cd = new CuentaCredito(456789, "Raquel", 0);
  }

  print("\nCuentas creadas ...");
  print(cd.toString());
  print(cc.toString());

  print("\nRealizando una transferecia  de 2000€ desde Raquel a Jorge ...");
  try {
    if (cc != null) {
      cd.transferir(cc, 2000);
    }
  } catch (e) {
    print(e);
  }
  print(cd.toString());
  print(cc.toString());

    print("\nRealizando una transferecia  de 200€ desde Jorge a Raquel ...");
  try {
    if (cc != null) {
      cc.transferir(cd, 200);
    }
  } catch (e) {
    print(e);
  }
  print(cd.toString());
  print(cc.toString());

  print("\nFIN");
}
