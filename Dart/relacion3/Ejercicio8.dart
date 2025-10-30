/*8- Vamos por fin a trabajar la validación de strings. Investiga cómo se definen las
expresiones regulares en Dart y construye una función que devuelva true si el string que
se le pase como parámetro hace match con la expresión regular de un color codificado en
RGB:
● ejemplos positivos: #4F6, #982A3B
● ejemplos negativos: red, 987s, 882244*/
import 'dart:io';

void main(List<String> args) {
  stdout.write("Introduzca el codigo de color RGB en formato hexadecimal: ");
  String cadena = stdin.readLineSync()!;
  if (RGBValido(cadena)) {
    print("El color $cadena es un codigo de color RGB valido");
  } else {
    print("El color $cadena no es un codigo de color RGB valido");
  }
}

bool RGBValido(String cadena) {
  bool valido = false;
  final regex = RegExp('^#[0-9A-F]{6}');
  if (regex.hasMatch(cadena)) {
    valido = true;
  }
  return valido;
}
