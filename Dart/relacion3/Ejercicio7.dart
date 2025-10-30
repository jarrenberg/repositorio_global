/*7- Haz un pequeño programa en Dart al que le introduzcas una frase y una subcadena.
Buscará si está contenida en la frase, y en qué posición la encuentra por primera y última
vez*/
import 'dart:io';

void main(List<String> args) {
  stdout.write("Introduzca una cadena de texto: ");
  String cadena = stdin.readLineSync()!;
  stdout.write("Introduzca una cadena de texto: ");
  String subcad = stdin.readLineSync()!;
  if (cadenaSinEspacios(cadena).contains(cadenaSinEspacios(subcad))) {
    print("La subcadena se haya en la cadena principal");
    //Checkear la posicion
  } else {
    print("La subcadena no se haya en la cadena principal");
  }
}

String cadenaSinEspacios(String s) {
  String resultado = "";
  List listaCad = s.split(" ");
  for (var element in listaCad) {
    resultado = resultado + element;
  }
  return resultado;
}
