import 'package:test_rgb/libreriaRegEx/lib_reg_ex/lib/lib_reg_ex.dart';
import 'package:test/test.dart';

void main() {
  group('Pruebas para rGBValido', () {
    test('Códigos RGB válidos en mayúsculas', () {
      expect(rGBValido('#FFFFFF'), true);
      expect(rGBValido('#000000'), true);
      expect(rGBValido('#123456'), true);
      expect(rGBValido('#ABCDEF'), true);
      expect(rGBValido('#FF0000'), true);
      expect(rGBValido('#00FF00'), true);
      expect(rGBValido('#0000FF'), true);
    });

    test('Códigos RGB válidos en minúsculas - DEBERÍAN FALLAR', () {
      // Estos deberían fallar porque la regex solo acepta A-F (mayúsculas)
      expect(rGBValido('#ffffff'), false);
      expect(rGBValido('#abcdef'), false);
      expect(rGBValido('#ff0000'), false);
    });

    test('Códigos RGB inválidos por formato incorrecto', () {
      expect(rGBValido('FFFFFF'), false); // Falta #
      expect(rGBValido('#FFFFF'), false); // Solo 5 caracteres
      expect(rGBValido('#FFFFFFFF'), false); // 8 caracteres
      expect(rGBValido('#GGGGGG'), false); // Caracteres no hexadecimales
      expect(rGBValido('#12345G'), false); // Último carácter no válido
      expect(rGBValido('#-12345'), false); // Carácter especial
    });

    test('Códigos RGB inválidos por caracteres especiales', () {
      expect(rGBValido('#FFFFF '), false); // Espacio al final
      expect(rGBValido(' #FFFFFF'), false); // Espacio al inicio
      expect(rGBValido('#FFFFFF#'), false); // # extra al final
      expect(rGBValido('#FF FF FF'), false); // Espacios en medio
    });

    test('Casos con números y letras mezclados', () {
      expect(rGBValido('#A1B2C3'), true);
      expect(rGBValido('#1A2B3C'), true);
      expect(rGBValido('#F0F0F0'), true);
      expect(rGBValido('#0F0F0F'), true);
    });

    test('Casos extremos y valores vacíos', () {
      expect(rGBValido(''), false); // String vacío
      expect(rGBValido('#'), false); // Solo #
      expect(rGBValido(' #'), false); // Espacio y #
      expect(rGBValido('TEST#FFFFFF'), false); // Texto antes del código
    });

    test('Códigos con formato correcto pero longitud incorrecta', () {
      expect(rGBValido('#12345'), false); // 5 dígitos
      expect(rGBValido('#123'), false); // 3 dígitos
      expect(rGBValido('#12'), false); // 2 dígitos
      expect(rGBValido('#1'), false); // 1 dígito
      expect(rGBValido('#1234567'), false); // 7 dígitos
    });
  });
}
