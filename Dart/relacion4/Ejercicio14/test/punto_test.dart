
import 'package:ejercicio14/punto.dart';
import 'package:test/test.dart';
  void main(){
  group('Pruebas de la clase Punto', () {
    
    test('Constructor inicializa correctamente las coordenadas', () {
      // Arrange & Act
      final punto = Punto(1.0, 2.0, 3.0);
      
      // Assert
      expect(punto.getX(), equals(1.0));
      expect(punto.getY(), equals(2.0));
      expect(punto.getZ(), equals(3.0));
    });

    test('Traslación horizontal positiva', () {
      // Arrange
      final punto = Punto(1.0, 2.0, 3.0);
      
      // Act
      punto.traslacionHorizontal(5.0);
      
      // Assert
      expect(punto.getX(), equals(6.0));
      expect(punto.getY(), equals(2.0)); // Y no debe cambiar
      expect(punto.getZ(), equals(3.0)); // Z no debe cambiar
    });

    test('Traslación horizontal negativa', () {
      // Arrange
      final punto = Punto(10.0, 2.0, 3.0);
      
      // Act
      punto.traslacionHorizontal(-3.0);
      
      // Assert
      expect(punto.getX(), equals(7.0));
      expect(punto.getY(), equals(2.0));
      expect(punto.getZ(), equals(3.0));
    });

    test('Traslación vertical positiva', () {
      // Arrange
      final punto = Punto(1.0, 2.0, 3.0);
      
      // Act
      punto.traslacionVertical(4.0);
      
      // Assert
      expect(punto.getX(), equals(1.0)); // X no debe cambiar
      expect(punto.getY(), equals(6.0));
      expect(punto.getZ(), equals(3.0)); // Z no debe cambiar
    });

    test('Traslación vertical negativa', () {
      // Arrange
      final punto = Punto(1.0, 8.0, 3.0);
      
      // Act
      punto.traslacionVertical(-2.0);
      
      // Assert
      expect(punto.getX(), equals(1.0));
      expect(punto.getY(), equals(6.0));
      expect(punto.getZ(), equals(3.0));
    });

    test('Traslación de cota positiva', () {
      // Arrange
      final punto = Punto(1.0, 2.0, 3.0);
      
      // Act
      punto.traslacionCota(7.0);
      
      // Assert
      expect(punto.getX(), equals(1.0)); // X no debe cambiar
      expect(punto.getY(), equals(2.0)); // Y no debe cambiar
      expect(punto.getZ(), equals(10.0));
    });

    test('Traslación de cota negativa', () {
      // Arrange
      final punto = Punto(1.0, 2.0, 10.0);
      
      // Act
      punto.traslacionCota(-4.0);
      
      // Assert
      expect(punto.getX(), equals(1.0));
      expect(punto.getY(), equals(2.0));
      expect(punto.getZ(), equals(6.0));
    });

    test('Múltiples traslaciones consecutivas', () {
      // Arrange
      final punto = Punto(0.0, 0.0, 0.0);
      
      // Act
      punto.traslacionHorizontal(2.0);
      punto.traslacionVertical(3.0);
      punto.traslacionCota(1.0);
      punto.traslacionHorizontal(-1.0);
      punto.traslacionVertical(-1.0);
      punto.traslacionCota(2.0);
      
      // Assert
      expect(punto.getX(), equals(1.0));
      expect(punto.getY(), equals(2.0));
      expect(punto.getZ(), equals(3.0));
    });

    test('Traslación con cero no cambia coordenadas', () {
      // Arrange
      final punto = Punto(5.0, 5.0, 5.0);
      
      // Act
      punto.traslacionHorizontal(0.0);
      punto.traslacionVertical(0.0);
      punto.traslacionCota(0.0);
      
      // Assert
      expect(punto.getX(), equals(5.0));
      expect(punto.getY(), equals(5.0));
      expect(punto.getZ(), equals(5.0));
    });

    test('Método toString devuelve formato correcto', () {
      // Arrange
      final punto = Punto(1.5, 2.7, 3.9);
      
      // Act
      final resultado = punto.toString();
      
      // Assert
      expect(resultado as double, equals("(1.5, 2.7, 3.9)"));
    });

    test('Coordenadas con valores decimales', () {
      final punto = Punto(1.5, 2.7, 3.9);
      
      punto.traslacionHorizontal(0.5);
      punto.traslacionVertical(-0.7);
      punto.traslacionCota(1.1);
      
      expect(punto.getX(), equals(2.0));
      expect(punto.getY(), equals(2.0));
      expect(punto.getZ(), closeTo(5.0, 0.0001));
    });

    test('Coordenadas con valores grandes', () {
      final punto = Punto(1000.0, 2000.0, 3000.0);
      
      punto.traslacionHorizontal(500.0);
      punto.traslacionVertical(-1000.0);
      punto.traslacionCota(2000.0);
      
      expect(punto.getX(), equals(1500.0));
      expect(punto.getY(), equals(1000.0));
      expect(punto.getZ(), equals(5000.0));
    });
  });

  group('Pruebas de aislamiento de traslaciones', () {
    
    test('Traslación horizontal solo afecta a X', () {
      final punto = Punto(1.0, 2.0, 3.0);
      final yOriginal = punto.getY();
      final zOriginal = punto.getZ();
      
      punto.traslacionHorizontal(10.0);
      
      expect(punto.getY(), equals(yOriginal));
      expect(punto.getZ(), equals(zOriginal));
      expect(punto.getX(), isNot(equals(1.0)));
    });

    test('Traslación vertical solo afecta a Y', () {
      final punto = Punto(1.0, 2.0, 3.0);
      final xOriginal = punto.getX();
      final zOriginal = punto.getZ();
      
      punto.traslacionVertical(10.0);
      
      expect(punto.getX(), equals(xOriginal));
      expect(punto.getZ(), equals(zOriginal));
      expect(punto.getY(), isNot(equals(2.0)));
    });

    test('Traslación de cota solo afecta a Z', () {
      final punto = Punto(1.0, 2.0, 3.0);
      final xOriginal = punto.getX();
      final yOriginal = punto.getY();
      
      punto.traslacionCota(10.0);
      
      expect(punto.getX(), equals(xOriginal));
      expect(punto.getY(), equals(yOriginal));
      expect(punto.getZ(), isNot(equals(3.0)));
    });
  });
  }
