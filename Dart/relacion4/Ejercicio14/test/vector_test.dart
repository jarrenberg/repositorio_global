import 'package:test/test.dart';
import 'package:ejercicio14/punto.dart';
import 'package:ejercicio14/vector.dart';

void main() {
  group('Pruebas de Constructor y Propiedades Básicas', () {
    test('Constructor calcula correctamente las componentes del vector', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      
      // Act
      final vector = Vector(inicio, fin);
      
      // Assert
      expect(vector.getXVector(), equals(3.0)); // 4.0 - 1.0
      expect(vector.getYVector(), equals(4.0)); // 6.0 - 2.0
      expect(vector.getZVector(), equals(6.0)); // 9.0 - 3.0
    });

    test('Vector con puntos iguales tiene componentes cero', () {
      // Arrange
      final punto = Punto(5.0, 5.0, 5.0);
      
      // Act
      final vector = Vector(punto, punto);
      
      // Assert
      expect(vector.getXVector(), equals(0.0));
      expect(vector.getYVector(), equals(0.0));
      expect(vector.getZVector(), equals(0.0));
    });

    test('Getters de inicio y fin devuelven los puntos correctos', () {
      // Arrange
      final inicio = Punto(1.0, 1.0, 1.0);
      final fin = Punto(2.0, 3.0, 4.0);
      
      // Act
      final vector = Vector(inicio, fin);
      
      // Assert
      expect(vector.getInicio().getX(), equals(1.0));
      expect(vector.getInicio().getY(), equals(1.0));
      expect(vector.getInicio().getZ(), equals(1.0));
      expect(vector.getFin().getX(), equals(2.0));
      expect(vector.getFin().getY(), equals(3.0));
      expect(vector.getFin().getZ(), equals(4.0));
    });
  });

  group('Pruebas de Traslación de Vectores', () {
    test('Traslación horizontal mantiene componentes del vector', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      final vector = Vector(inicio, fin);
      final xOriginal = vector.getXVector();
      final yOriginal = vector.getYVector();
      final zOriginal = vector.getZVector();
      
      // Act
      vector.trasladarVectorHorizontalmente(5.0);
      
      // Assert - Las componentes del vector no deben cambiar
      expect(vector.getXVector(), equals(xOriginal));
      expect(vector.getYVector(), equals(yOriginal));
      expect(vector.getZVector(), equals(zOriginal));
    });

    test('Traslación vertical mantiene componentes del vector', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      final vector = Vector(inicio, fin);
      final xOriginal = vector.getXVector();
      final yOriginal = vector.getYVector();
      final zOriginal = vector.getZVector();
      
      // Act
      vector.trasladarVectorVerticalmente(-3.0);
      
      // Assert - Las componentes del vector no deben cambiar
      expect(vector.getXVector(), equals(xOriginal));
      expect(vector.getYVector(), equals(yOriginal));
      expect(vector.getZVector(), equals(zOriginal));
    });

    test('Traslación de cota mantiene componentes del vector', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      final vector = Vector(inicio, fin);
      final xOriginal = vector.getXVector();
      final yOriginal = vector.getYVector();
      final zOriginal = vector.getZVector();
      
      // Act
      vector.trasladarVectorCota(10.0);
      
      // Assert - Las componentes del vector no deben cambiar
      expect(vector.getXVector(), equals(xOriginal));
      expect(vector.getYVector(), equals(yOriginal));
      expect(vector.getZVector(), equals(zOriginal));
    });

    test('Traslación horizontal actualiza puntos correctamente', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      final vector = Vector(inicio, fin);
      
      // Act
      vector.trasladarVectorHorizontalmente(5.0);
      
      // Assert - Los puntos se trasladan correctamente
      expect(vector.getInicio().getX(), equals(6.0)); // 1.0 + 5.0
      expect(vector.getFin().getX(), equals(9.0));   // 4.0 + 5.0
    });

    test('Traslación vertical actualiza puntos correctamente', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      final vector = Vector(inicio, fin);
      
      // Act
      vector.trasladarVectorVerticalmente(-2.0);
      
      // Assert
      expect(vector.getInicio().getY(), equals(0.0)); // 2.0 - 2.0
      expect(vector.getFin().getY(), equals(4.0));   // 6.0 - 2.0
    });

    test('Traslación de cota actualiza puntos correctamente', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      final vector = Vector(inicio, fin);
      
      // Act
      vector.trasladarVectorCota(7.0);
      
      // Assert
      expect(vector.getInicio().getZ(), equals(10.0)); // 3.0 + 7.0
      expect(vector.getFin().getZ(), equals(16.0));   // 9.0 + 7.0
    });
  });

  group('Pruebas de Operaciones Vectoriales', () {
    test('Producto escalar de vectores ortogonales es cero', () {
      // Arrange
      final vector1 = Vector(Punto(0.0, 0.0, 0.0), Punto(1.0, 0.0, 0.0));
      final vector2 = Vector(Punto(0.0, 0.0, 0.0), Punto(0.0, 1.0, 0.0));
      
      // Act
      final resultado = vector1.productoEscalar(vector2);
      
      // Assert
      expect(resultado, equals(0.0));
    });

    test('Producto escalar de vectores paralelos', () {
      // Arrange
      final vector1 = Vector(Punto(0.0, 0.0, 0.0), Punto(2.0, 0.0, 0.0));
      final vector2 = Vector(Punto(0.0, 0.0, 0.0), Punto(3.0, 0.0, 0.0));
      
      // Act
      final resultado = vector1.productoEscalar(vector2);
      
      // Assert
      expect(resultado, equals(6.0)); // 2.0 * 3.0 + 0.0 * 0.0 + 0.0 * 0.0
    });

    test('Producto escalar de vectores en 3D', () {
      // Arrange
      final vector1 = Vector(Punto(0.0, 0.0, 0.0), Punto(1.0, 2.0, 3.0));
      final vector2 = Vector(Punto(0.0, 0.0, 0.0), Punto(4.0, 5.0, 6.0));
      
      // Act
      final resultado = vector1.productoEscalar(vector2);
      
      // Assert
      expect(resultado, equals(32.0)); // 1*4 + 2*5 + 3*6 = 4 + 10 + 18
    });

    test('Suma vectorial de vectores', () {
      // Arrange
      final vector1 = Vector(Punto(0.0, 0.0, 0.0), Punto(1.0, 2.0, 3.0));
      final vector2 = Vector(Punto(0.0, 0.0, 0.0), Punto(4.0, 5.0, 6.0));
      
      // Act
      final resultado = vector1.sumaVectorial(vector2);
      
      // Assert
      expect(resultado, equals([5.0, 7.0, 9.0])); // [1+4, 2+5, 3+6]
    });

    test('Suma vectorial con vectores negativos', () {
      // Arrange
      final vector1 = Vector(Punto(0.0, 0.0, 0.0), Punto(1.0, -2.0, 3.0));
      final vector2 = Vector(Punto(0.0, 0.0, 0.0), Punto(-4.0, 5.0, -6.0));
      
      // Act
      final resultado = vector1.sumaVectorial(vector2);
      
      // Assert
      expect(resultado, equals([-3.0, 3.0, -3.0])); // [1-4, -2+5, 3-6]
    });

    test('Suma vectorial con vector cero', () {
      // Arrange
      final vector1 = Vector(Punto(0.0, 0.0, 0.0), Punto(1.0, 2.0, 3.0));
      final vector2 = Vector(Punto(0.0, 0.0, 0.0), Punto(0.0, 0.0, 0.0));
      
      // Act
      final resultado = vector1.sumaVectorial(vector2);
      
      // Assert
      expect(resultado, equals([1.0, 2.0, 3.0]));
    });
  });

  group('Pruebas de Casos Especiales', () {
    test('Vector con componentes negativas', () {
      // Arrange
      final inicio = Punto(5.0, 5.0, 5.0);
      final fin = Punto(2.0, 3.0, 1.0);
      
      // Act
      final vector = Vector(inicio, fin);
      
      // Assert
      expect(vector.getXVector(), equals(-3.0)); // 2.0 - 5.0
      expect(vector.getYVector(), equals(-2.0)); // 3.0 - 5.0
      expect(vector.getZVector(), equals(-4.0)); // 1.0 - 5.0
    });

    test('Múltiples traslaciones consecutivas', () {
      // Arrange
      final inicio = Punto(0.0, 0.0, 0.0);
      final fin = Punto(1.0, 1.0, 1.0);
      final vector = Vector(inicio, fin);
      
      // Act
      vector.trasladarVectorHorizontalmente(2.0);
      vector.trasladarVectorVerticalmente(3.0);
      vector.trasladarVectorCota(4.0);
      vector.trasladarVectorHorizontalmente(-1.0);
      
      // Assert - Las componentes del vector no cambian
      expect(vector.getXVector(), equals(1.0));
      expect(vector.getYVector(), equals(1.0));
      expect(vector.getZVector(), equals(1.0));
      
      // Assert - Los puntos se trasladan correctamente
      expect(vector.getInicio().getX(), equals(1.0)); // 0 + 2 - 1
      expect(vector.getInicio().getY(), equals(3.0)); // 0 + 3
      expect(vector.getInicio().getZ(), equals(4.0)); // 0 + 4
      expect(vector.getFin().getX(), equals(2.0));   // 1 + 2 - 1
      expect(vector.getFin().getY(), equals(4.0));   // 1 + 3
      expect(vector.getFin().getZ(), equals(5.0));   // 1 + 4
    });

    test('Traslación con distancia cero no afecta componentes', () {
      // Arrange
      final inicio = Punto(1.0, 2.0, 3.0);
      final fin = Punto(4.0, 6.0, 9.0);
      final vector = Vector(inicio, fin);
      final xOriginal = vector.getXVector();
      final yOriginal = vector.getYVector();
      final zOriginal = vector.getZVector();
      
      // Act
      vector.trasladarVectorHorizontalmente(0.0);
      vector.trasladarVectorVerticalmente(0.0);
      vector.trasladarVectorCota(0.0);
      
      // Assert
      expect(vector.getXVector(), equals(xOriginal));
      expect(vector.getYVector(), equals(yOriginal));
      expect(vector.getZVector(), equals(zOriginal));
    });
  });
}