import 'package:ejercicio15/cuenta_bancaria.dart';

class CuentaCredito extends CuentaBancaria {
  late int _numeroCuenta;
  late String _nombreTitular;
  late double _saldo;
  int _numeroOperaciones = 0;
  final double _maximoNegativo = -1500.0;

  CuentaCredito(int numeroCuenta, String nombreTitular, double saldo) {
    if (saldo < 0) {
      throw Exception("No se puede crear una cuenta con saldo negativo");
    }
    _numeroCuenta = numeroCuenta;
    _nombreTitular = nombreTitular;
    _saldo = saldo;
  }

  // Getters corregidos
  int get numeroCuenta => _numeroCuenta;
  String get nombreTitular => _nombreTitular;
  double get saldo => _saldo;
  int get numeroOperaciones => _numeroOperaciones;
  double get maximoNegativo => _maximoNegativo;

  @override
  void depositar(double cantidad) {
    _saldo = _saldo + cantidad;
    _numeroOperaciones += 1;
  }

  @override
  void extraer(double cantidad) {
    if (_saldo - cantidad < _maximoNegativo) {
      throw Exception(
        "No se puede extraer esta cantidad. Supera el maximo de credito",
      );
    }
    _saldo = _saldo - cantidad;
    _numeroOperaciones += 1;
  }

  @override
  void transferir(CuentaBancaria cb, double cantidad) {
    try {
      extraer(cantidad);
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