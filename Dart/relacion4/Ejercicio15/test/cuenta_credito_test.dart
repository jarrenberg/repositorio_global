// Clase mock para testing
import 'package:ejercicio15/cuenta_bancaria.dart';
import 'package:ejercicio15/cuenta_credito.dart';
import 'package:ejercicio15/cuenta_debito.dart';
import 'package:test/test.dart';

class CuentaBancariaMock implements CuentaBancaria {
  int depositosRealizados = 0;
  double cantidadDepositada = 0.0;
  int extraccionesRealizadas = 0;
  double cantidadExtraida = 0.0;
  
  @override
  void depositar(double cantidad) {
    depositosRealizados++;
    cantidadDepositada += cantidad;
  }
  
  @override
  void extraer(double cantidad) {
    extraccionesRealizadas++;
    cantidadExtraida += cantidad;
  }
  
  @override
  void transferir(CuentaBancaria cb, double cantidad) {
    // Implementación básica para testing
    extraer(cantidad);
    cb.depositar(cantidad);
  }
}

void main() {
  group('Pruebas de CuentaCredito', () {
    test('Constructor inicializa correctamente', () {
      final cuenta = CuentaCredito(12345, 'Juan Pérez', 1000.0);
      
      expect(cuenta.numeroCuenta, equals(12345));
      expect(cuenta.nombreTitular, equals('Juan Pérez'));
      expect(cuenta.saldo, equals(1000.0));
      expect(cuenta.numeroOperaciones, equals(0));
      expect(cuenta.maximoNegativo, equals(-1500.0));
    });

    test('Constructor lanza excepción con saldo negativo', () {
      expect(() => CuentaCredito(12347, 'Carlos López', -100.0),
          throwsA(isA<Exception>()));
    });

    test('Depósito aumenta saldo y operaciones', () {
      final cuenta = CuentaCredito(12345, 'Juan Pérez', 500.0);
      
      cuenta.depositar(300.0);
      
      expect(cuenta.saldo, equals(800.0));
      expect(cuenta.numeroOperaciones, equals(1));
    });

    test('Extracción dentro del límite de crédito', () {
      final cuenta = CuentaCredito(12345, 'Juan Pérez', 500.0);
      
      cuenta.extraer(1000.0); // Saldo: -500.0
      
      expect(cuenta.saldo, equals(-500.0));
      expect(cuenta.numeroOperaciones, equals(1));
    });

    test('Extracción hasta el límite exacto de crédito', () {
      final cuenta = CuentaCredito(12345, 'Juan Pérez', 0.0);
      
      cuenta.extraer(1500.0); // Saldo: -1500.0 (límite exacto)
      
      expect(cuenta.saldo, equals(-1500.0));
      expect(cuenta.numeroOperaciones, equals(1));
    });

    test('Extracción que supera límite de crédito lanza excepción', () {
      final cuenta = CuentaCredito(12345, 'Juan Pérez', 0.0);
      
      expect(() => cuenta.extraer(1500.01), throwsA(isA<Exception>()));
      expect(cuenta.saldo, equals(0.0));
      expect(cuenta.numeroOperaciones, equals(0));
    });

    test('Transferencia exitosa entre cuentas', () {
      final cuentaOrigen = CuentaCredito(12345, 'Juan Pérez', 1000.0);
      final cuentaDestino = CuentaCredito(67890, 'María García', 500.0);
      
      cuentaOrigen.transferir(cuentaDestino, 300.0);
      
      expect(cuentaOrigen.saldo, equals(700.0));
      expect(cuentaDestino.saldo, equals(800.0));
    });

    test('Transferencia que supera límite no se ejecuta', () {
      final cuentaOrigen = CuentaCredito(12345, 'Juan Pérez', 0.0);
      final cuentaDestino = CuentaCredito(67890, 'María García', 500.0);
      
      cuentaOrigen.transferir(cuentaDestino, 1600.0); // Supera límite
      
      expect(cuentaOrigen.saldo, equals(0.0));
      expect(cuentaDestino.saldo, equals(500.0));
    });

    test('toString muestra información completa', () {
      final cuenta = CuentaCredito(12345, 'Ana Silva', 750.50);
      
      final resultado = cuenta.toString();
      
      expect(resultado, contains('12345'));
      expect(resultado, contains('Ana Silva'));
      expect(resultado, contains('750.5'));
      expect(resultado, contains('-1500.0'));
    });
  });

  group('Pruebas de CuentaDebito', () {
    test('Constructor inicializa correctamente', () {
      final cuenta = CuentaDebito(12345, 'Juan Pérez', 1000.0);
      
      expect(cuenta.numeroCuenta, equals(12345));
      expect(cuenta.nombreTitular, equals('Juan Pérez'));
      expect(cuenta.saldo, equals(1000.0));
      expect(cuenta.numeroOperaciones, equals(0));
    });

    test('Constructor lanza excepción con saldo negativo', () {
      expect(() => CuentaDebito(12347, 'Carlos López', -100.0),
          throwsA(isA<Exception>()));
    });

    test('Depósito aumenta saldo y operaciones', () {
      final cuenta = CuentaDebito(12345, 'Juan Pérez', 500.0);
      
      cuenta.depositar(300.0);
      
      expect(cuenta.saldo, equals(800.0));
      expect(cuenta.numeroOperaciones, equals(1));
    });

    test('Extracción dentro del saldo disponible', () {
      final cuenta = CuentaDebito(12345, 'Juan Pérez', 1000.0);
      
      cuenta.extraer(300.0);
      
      expect(cuenta.saldo, equals(700.0));
      expect(cuenta.numeroOperaciones, equals(1));
    });

    test('Extracción de todo el saldo disponible', () {
      final cuenta = CuentaDebito(12345, 'Juan Pérez', 500.0);
      
      cuenta.extraer(500.0);
      
      expect(cuenta.saldo, equals(0.0));
      expect(cuenta.numeroOperaciones, equals(1));
    });

    test('Extracción que supera el saldo lanza excepción', () {
      final cuenta = CuentaDebito(12345, 'Juan Pérez', 500.0);
      
      expect(() => cuenta.extraer(500.01), throwsA(isA<Exception>()));
      expect(cuenta.saldo, equals(500.0));
      expect(cuenta.numeroOperaciones, equals(0));
    });

    test('Transferencia exitosa entre cuentas', () {
      final cuentaOrigen = CuentaDebito(12345, 'Juan Pérez', 1000.0);
      final cuentaDestino = CuentaDebito(67890, 'María García', 500.0);
      
      cuentaOrigen.transferir(cuentaDestino, 300.0);
      
      expect(cuentaOrigen.saldo, equals(700.0));
      expect(cuentaDestino.saldo, equals(800.0));
    });

    test('Transferencia que supera saldo no se ejecuta', () {
      final cuentaOrigen = CuentaDebito(12345, 'Juan Pérez', 500.0);
      final cuentaDestino = CuentaDebito(67890, 'María García', 100.0);
      
      cuentaOrigen.transferir(cuentaDestino, 500.01);
      
      expect(cuentaOrigen.saldo, equals(500.0));
      expect(cuentaDestino.saldo, equals(100.0));
    });

    test('toString muestra información sin límite de crédito', () {
      final cuenta = CuentaDebito(12345, 'Ana Silva', 750.50);
      
      final resultado = cuenta.toString();
      
      expect(resultado, contains('12345'));
      expect(resultado, contains('Ana Silva'));
      expect(resultado, contains('750.5'));
      expect(resultado, isNot(contains('-1500.0'))); // No tiene límite de crédito
    });
  });

  group('Pruebas Comparativas entre CuentaCredito y CuentaDebito', () {
    test('Diferencias en extracción con saldo insuficiente', () {
      final cuentaCredito = CuentaCredito(11111, 'Usuario', 0.0);
      final cuentaDebito = CuentaDebito(22222, 'Usuario', 0.0);
      
      // CuentaCrédito permite extracción dentro del límite
      cuentaCredito.extraer(1000.0); // Funciona
      expect(cuentaCredito.saldo, equals(-1000.0));
      
      // CuentaDébito no permite extracción sin saldo
      expect(() => cuentaDebito.extraer(1000.0), throwsA(isA<Exception>()));
      expect(cuentaDebito.saldo, equals(0.0));
    });

    test('Transferencias entre diferentes tipos de cuenta', () {
      final cuentaCredito = CuentaCredito(11111, 'Usuario Crédito', 0.0);
      final cuentaDebito = CuentaDebito(22222, 'Usuario Débito', 1000.0);
      
      // Transferencia desde débito a crédito
      cuentaDebito.transferir(cuentaCredito, 500.0);
      
      expect(cuentaDebito.saldo, equals(500.0));
      expect(cuentaCredito.saldo, equals(500.0));
      
      // Transferencia desde crédito a débito (usando crédito)
      cuentaCredito.transferir(cuentaDebito, 600.0); // Crédito queda en -100.0
      
      expect(cuentaCredito.saldo, equals(-100.0));
      expect(cuentaDebito.saldo, equals(1100.0));
    });

    test('Límites diferentes en operaciones', () {
      final cuentaCredito = CuentaCredito(11111, 'Usuario', 0.0);
      final cuentaDebito = CuentaDebito(22222, 'Usuario', 100.0);
      
      // CuentaCrédito tiene límite de -1500
      expect(() => cuentaCredito.extraer(1600.0), throwsA(isA<Exception>()));
      
      // CuentaDébito tiene límite de 0
      expect(() => cuentaDebito.extraer(100.01), throwsA(isA<Exception>()));
    });
  });

  group('Pruebas de Integración Compleja', () {
    test('Sistema bancario con múltiples operaciones', () {
      final cuentaCredito = CuentaCredito(11111, 'Cliente Premium', 2000.0);
      final cuentaDebito1 = CuentaDebito(22222, 'Cliente Básico 1', 1000.0);
      final cuentaDebito2 = CuentaDebito(33333, 'Cliente Básico 2', 500.0);
      
      // Operaciones complejas
      cuentaCredito.depositar(500.0);
      cuentaCredito.transferir(cuentaDebito1, 800.0);
      cuentaDebito1.transferir(cuentaDebito2, 300.0);
      cuentaDebito2.extraer(200.0);
      cuentaCredito.extraer(2200.0); // Usa crédito
      
      // Verificaciones finales
      expect(cuentaCredito.saldo, equals(-500.0)); // 2000 + 500 - 800 - 2200 = -500
      expect(cuentaDebito1.saldo, equals(500.0));  // 1000 + 800 - 300 = 1500
      expect(cuentaDebito2.saldo, equals(600.0));  // 500 + 300 - 200 = 600
      
      expect(cuentaCredito.numeroOperaciones, equals(3));
      expect(cuentaDebito1.numeroOperaciones, equals(1));
      expect(cuentaDebito2.numeroOperaciones, equals(2));
    });

    test('Comportamiento con CuentaBancariaMock', () {
      final cuentaReal = CuentaCredito(12345, 'Usuario Real', 1000.0);
      final cuentaMock = CuentaBancariaMock();
      
      cuentaReal.transferir(cuentaMock, 500.0);
      
      expect(cuentaReal.saldo, equals(500.0));
      expect(cuentaMock.depositosRealizados, equals(1));
      expect(cuentaMock.cantidadDepositada, equals(500.0));
    });
  });

  group('Pruebas de Casos Límite', () {
    test('CuentaCredito con operaciones en límite negativo', () {
      final cuenta = CuentaCredito(12345, 'Usuario', 0.0);
      
      cuenta.extraer(1499.99);
      cuenta.depositar(100.0);
      cuenta.extraer(100.0); // Vuelve al límite
      
      expect(cuenta.saldo, equals(-1499.99));
      expect(() => cuenta.extraer(0.02), throwsA(isA<Exception>())); // Supera -1500
    });

    test('CuentaDebito con saldo exacto para extracción', () {
      final cuenta = CuentaDebito(12345, 'Usuario', 100.0);
      
      cuenta.extraer(100.0); // Extracción exacta
      
      expect(cuenta.saldo, equals(0.0));
      expect(() => cuenta.extraer(0.01), throwsA(isA<Exception>()));
    });
  });
}
