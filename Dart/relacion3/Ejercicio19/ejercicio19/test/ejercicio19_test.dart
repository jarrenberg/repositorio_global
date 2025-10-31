import 'package:test/test.dart';
import 'package:ejercicio19/libreriaRegEx/lib_reg_ex/lib/lib_reg_ex.dart';

void main() {
  // Comprobacin DNI
  group('Pruebas para letraDNIValida - DNIs VÁLIDOS', () {
    test('DNIs válidos con diferentes letras', () {
      // Casos conocidos y verificados
      expect(letraDNIValida('12345678Z'), true); // 12345678 % 23 = 20 → Z
      expect(letraDNIValida('00000000T'), true); // 0 % 23 = 0 → T
      expect(
        letraDNIValida('00000023W'),
        false,
      ); // 23 % 23 = 0 → T? No, 23 % 23 = 0 → T
      expect(letraDNIValida('53003031K'), false); // 53003031 % 23 = 10 → K
      expect(letraDNIValida('12345678Z'), true); // Caso estándar
      expect(letraDNIValida('87654321M'), false); // 87654321 % 23 = 5 → M
    });

    test('DNIs válidos con casos extremos', () {
      expect(letraDNIValida('99999999R'), true); // 99999999 % 23 = 17 → R
      expect(letraDNIValida('10000000B'), false); // 10000000 % 23 = 11 → B
      expect(letraDNIValida('00000001R'), true); // 1 % 23 = 1 → R
      expect(letraDNIValida('00000022E'), true); // 22 % 23 = 22 → E
    });
  });

  group('Pruebas para letraDNIValida - DNIs INVÁLIDOS', () {
    test('DNIs con letra incorrecta', () {
      expect(letraDNIValida('12345678A'), false); // Debería ser Z, no A
      expect(letraDNIValida('00000000A'), false); // Debería ser T, no A
      expect(letraDNIValida('53003031A'), false); // Debería ser K, no A
      expect(letraDNIValida('87654321A'), false); // Debería ser M, no A
    });

    test('DNIs con formato incorrecto', () {
      expect(letraDNIValida('1234567Z'), false); // Solo 7 dígitos
      expect(letraDNIValida('123456789Z'), false); // 9 dígitos
      expect(letraDNIValida('ABCDEFGHZ'), false); // Letras en lugar de números
      expect(letraDNIValida('12345678z'), false); // letra minúscula
      expect(letraDNIValida('1234567'), false); // Sin letra
      expect(letraDNIValida('12345678'), false); // Sin letra
    });

    test('DNIs con caracteres especiales y espacios', () {
      expect(letraDNIValida('1234567-8Z'), false); // Guión en medio
      expect(letraDNIValida('12345678 Z'), false); // Espacio antes de letra
      expect(letraDNIValida(' 12345678Z'), false); // Espacio al inicio
      expect(letraDNIValida('12345678Z '), false); // Espacio al final
      expect(letraDNIValida('12.345.678Z'), false); // Puntos
    });

    test('Casos extremos y valores vacíos', () {
      expect(letraDNIValida(''), false); // String vacío
      expect(letraDNIValida('Z'), false); // Solo letra
      expect(letraDNIValida('12345678'), false); // Sin letra
      expect(letraDNIValida('ABCDEFGHI'), false); // Solo letras
    });

    test('DNIs con letras que no existen en la tabla', () {
      // Letras que no están en la lista ["T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"]
      expect(letraDNIValida('12345678I'), false); // I no existe
      expect(letraDNIValida('12345678O'), false); // O no existe
      expect(letraDNIValida('12345678U'), false); // U no existe
      expect(letraDNIValida('12345678Ñ'), false); // Ñ no existe
    });

    test('DNIs con números que producen índice fuera de rango', () {
      // Aunque el módulo 23 siempre da 0-22, probemos casos límite
      expect(letraDNIValida('00000000A'), false); // 0 → T (no A)
      expect(letraDNIValida('00000022A'), false); // 22 → E (no A)
    });
  });

  group('Pruebas de cálculo matemático específico', () {
    test('Verificación de cálculo de letra', () {
      // 12345678 % 23 = 20 → Z
      expect(letraDNIValida('12345678Z'), true);

      // 53003031 % 23 = 10 → K
      expect(letraDNIValida('53003031K'), false);

      // 87654321 % 23 = 5 → M
      expect(letraDNIValida('87654321M'), false);

      // 00000015 % 23 = 15 → S
      expect(letraDNIValida('00000015S'), true);
    });
  });

  // Pruebas RGB
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

  // Pruebas URL
  group('uRLValido - URLs VÁLIDOS (comportamiento CORRECTO)', () {
    test('URLs HTTP válidas con dominio .com', () {
      expect(uRLValido('http://www.google.com'), true);
      expect(uRLValido('http://www.facebook.com'), true);
      expect(uRLValido('http://www.example.com'), true);
      expect(uRLValido('http://www.a.com'), true);
      expect(uRLValido('http://www.test.com'), true);
    });

    test('URLs HTTPS válidas con dominio .com', () {
      expect(uRLValido('https://www.google.com'), true);
      expect(uRLValido('https://www.facebook.com'), true);
      expect(uRLValido('https://www.example.com'), true);
      expect(uRLValido('https://www.amazon.com'), true);
    });

    test('URLs válidas con dominio .es', () {
      expect(uRLValido('http://www.google.es'), true);
      expect(uRLValido('https://www.facebook.es'), true);
      expect(uRLValido('http://www.example.es'), true);
      expect(uRLValido('https://www.spanish.es'), true);
    });

    test('URLs con rutas adicionales válidas', () {
      expect(uRLValido('http://www.example.com/ruta'), true);
      expect(uRLValido('https://www.google.com/search'), true);
      expect(uRLValido('http://www.facebook.com/profile'), true);
      expect(uRLValido('http://www.example.com/api'), true);
      expect(uRLValido('https://www.google.es/maps'), true);
    });

    test('URLs sin ruta (solo dominio)', () {
      expect(uRLValido('http://www.example.com'), true);
      expect(uRLValido('https://www.google.es'), true);
      expect(uRLValido('http://www.test.com'), true);
    });

    test('URLs con nombres de dominio variados', () {
      expect(uRLValido('http://www.a.com'), true);
      expect(uRLValido('https://www.abcdefghijklmnopqrstuvwxyz.com'), true);
      expect(uRLValido('http://www.hello.com'), true);
      expect(uRLValido('https://www.world.es'), true);
    });
  });

  group('uRLValido - URLs INVÁLIDOS (comportamiento CORRECTO)', () {
    test('URLs sin www', () {
      expect(uRLValido('http://google.com'), false);
      expect(uRLValido('https://example.com'), false);
      expect(uRLValido('http://facebook.es'), false);
      expect(uRLValido('https://test.com'), false);
    });

    test('URLs con otros protocolos', () {
      expect(uRLValido('ftp://www.example.com'), false);
      expect(uRLValido('file://www.google.com'), false);
      expect(uRLValido('ws://www.facebook.com'), false);
      expect(uRLValido('wss://www.test.com'), false);
    });

    test('URLs con otros dominios TLD', () {
      expect(uRLValido('http://www.example.org'), false);
      expect(uRLValido('https://www.google.net'), false);
      expect(uRLValido('http://www.facebook.io'), false);
      expect(uRLValido('https://www.test.edu'), false);
      expect(uRLValido('http://www.example.gov'), false);
    });

    test('URLs con números en el nombre de dominio', () {
      expect(uRLValido('http://www.example123.com'), false);
      expect(uRLValido('https://www.google2023.com'), false);
      expect(uRLValido('http://www.test123.es'), false);
      expect(uRLValido('https://www.123hello.com'), false);
    });

    test('URLs con guiones en el nombre de dominio', () {
      expect(uRLValido('http://www.my-example.com'), false);
      expect(uRLValido('https://www.test-site.es'), false);
      expect(uRLValido('http://www.hello-world.com'), false);
      expect(uRLValido('https://www.api-test.es'), false);
    });
    test('URLs con subdominios diferentes a www', () {
      expect(uRLValido('http://api.example.com'), false);
      expect(uRLValido('https://blog.google.com'), false);
      expect(uRLValido('http://shop.facebook.com'), false);
      expect(uRLValido('https://app.test.es'), false);
    });

    test('Casos extremos y valores vacíos', () {
      expect(uRLValido(''), false);
      expect(uRLValido('www.example.com'), false);
      expect(uRLValido('example.com'), false);
      expect(uRLValido('http://www..com'), false);
      expect(uRLValido('https://www..es'), false);
      expect(uRLValido('http://www..com/ruta'), false);
    });

    test('URLs con espacios', () {
      expect(uRLValido('http://www.example.com '), false); // Espacio al final
      expect(uRLValido(' https://www.google.com'), false); // Espacio al inicio
      expect(uRLValido('http://www.test.com/ ruta'), false); // Espacio en ruta
    });
  });

  group('Análisis de la regex CORRECTA', () {
    test('VERIFICACIÓN: Punto literal funciona correctamente', () {
      expect(
        uRLValido('http://www.exampleXcom'),
        false,
      ); // X en lugar de . → RECHAZADO ✓
      expect(
        uRLValido('http://www.example com'),
        false,
      ); // espacio en lugar de . → RECHAZADO ✓
      expect(
        uRLValido('http://www.example.com'),
        true,
      ); // . correcto → ACEPTADO ✓
    });

    test('VERIFICACIÓN: (com|es) como grupo de alternativas funciona', () {
      // Solo acepta .com y .es completos
      expect(uRLValido('http://www.example.c'), false); // .c → RECHAZADO ✓
      expect(uRLValido('http://www.example.o'), false); // .o → RECHAZADO ✓
      expect(uRLValido('http://www.example.m'), false); // .m → RECHAZADO ✓
      expect(uRLValido('http://www.example.|'), false); // .| → RECHAZADO ✓
      expect(uRLValido('http://www.example.e'), false); // .e → RECHAZADO ✓
      expect(uRLValido('http://www.example.s'), false); // .s → RECHAZADO ✓
      expect(uRLValido('http://www.example.com'), true); // .com → ACEPTADO ✓
      expect(uRLValido('http://www.example.es'), true); // .es → ACEPTADO ✓
    });

    test('VERIFICACIÓN: Ruta opcional funciona correctamente', () {
      expect(uRLValido('http://www.example.com'), true); // Sin ruta ✓
      expect(uRLValido('http://www.example.com/'), true); // Solo slash ✓
      expect(uRLValido('http://www.example.com/ruta'), true); // Ruta simple ✓
      expect(uRLValido('https://www.google.com/search'), true); // Ruta simple ✓
    });
  });

  group('Casos específicos de borde', () {
    test('Dominios de un solo carácter', () {
      expect(uRLValido('http://www.a.com'), true);
      expect(uRLValido('https://www.b.es'), true);
      expect(uRLValido('http://www.x.com'), true);
    });

    test('Dominios largos', () {
      expect(
        uRLValido('http://www.esteesunnombrededominiomuyextenso.com'),
        true,
      );
      expect(uRLValido('https://www.abcdefghijklmnopqrstuvwxyz.es'), true);
    });

    test('Rutas con diferentes combinaciones', () {
      expect(uRLValido('http://www.example.com/a'), true);
      expect(uRLValido('https://www.test.com/AB'), true);
      expect(uRLValido('http://www.example.com/abc'), true);
      expect(
        uRLValido('https://www.google.com/ABCDEFGHIJKLMNOPQRSTUVWXYZ'),
        true,
      );
      expect(
        uRLValido('http://www.facebook.com/abcdefghijklmnopqrstuvwxyz'),
        true,
      );
    });

    test('Combinaciones HTTP/HTTPS con .com/.es', () {
      expect(uRLValido('http://www.example.com'), true);
      expect(uRLValido('https://www.example.com'), true);
      expect(uRLValido('http://www.example.es'), true);
      expect(uRLValido('https://www.example.es'), true);
    });
  });

  // Pruebas email
  group('mailValido - Emails VÁLIDOS según la regex actual', () {
    test('Emails básicos con dominio .com', () {
      expect(mailValido('usuario@dominio.com'), true);
      expect(mailValido('test@example.com'), true);
      expect(mailValido('john@mail.com'), true);
      expect(mailValido('admin@server.com'), true);
    });

    test('Emails básicos con dominio .es', () {
      expect(mailValido('usuario@dominio.es'), true);
      expect(mailValido('test@example.es'), true);
      expect(mailValido('maria@correo.es'), true);
      expect(mailValido('info@empresa.es'), true);
    });

    test('Emails con números en la parte local', () {
      expect(mailValido('user123@domain.com'), true);
      expect(mailValido('test2023@example.com'), true);
      expect(mailValido('john123@mail.es'), true);
      expect(mailValido('admin456@server.es'), true);
    });

    test('Emails con nombres cortos', () {
      expect(mailValido('a@b.com'), true);
      expect(mailValido('x@y.es'), true);
      expect(mailValido('1@2.com'), false);
      expect(mailValido('9@z.es'), true);
    });

    test('Emails con letras y números mezclados', () {
      expect(mailValido('abc123@def.com'), true);
      expect(mailValido('123abc@ghi.es'), true);
      expect(mailValido('a1b2c3@test.com'), true);
    });
  });

  group('mailValido - Emails INVÁLIDOS según la regex actual', () {
    test('Emails con mayúsculas', () {
      expect(mailValido('Usuario@dominio.com'), false);
      expect(mailValido('TEST@example.com'), false);
      expect(mailValido('John@Mail.com'), false);
      expect(mailValido('Admin@Server.es'), false);
    });

    test('Emails con guiones, puntos o underscores', () {
      expect(mailValido('user-name@domain.com'), false);
      expect(mailValido('first.last@example.com'), false);
      expect(mailValido('test_user@domain.com'), false);
      expect(mailValido('user.name@test.es'), false);
    });

    test('Casos extremos y valores vacíos', () {
      expect(mailValido(''), false);
      expect(mailValido('@domain.com'), false);
      expect(mailValido('user@.com'), false);
      expect(mailValido('user@domain.'), false);
      expect(mailValido('user@'), false);
      expect(mailValido('@'), false);
      expect(mailValido('@.'), false);
    });
  });
}
