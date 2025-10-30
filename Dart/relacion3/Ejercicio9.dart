/*9- Crea otra función que haga lo mismo con URL’s*/
import 'dart:io';

void main(List<String> args) {
  stdout.write("Introduzca una URL: ");
  String cadena = stdin.readLineSync()!;

  if (URLValido(cadena)) {
    print("La URL $cadena es valida");
  } else {
    print("La URL $cadena no es valida");
  }
}

bool URLValido(String cadena) {
  bool valido = false;
  final regex = RegExp(r'^http[s]?://www.[A-Za-z]+.[com|es]{1}[/A-Za-z]*');
  if (regex.hasMatch(cadena)) {
    valido = true;
  }
  return valido;
}
