/*10- Lo mismo con correos electrónicos*/
import 'dart:io';

void main(List<String> args) {
  stdout.write("Introduzca un correo electronico: ");
  String cadena = stdin.readLineSync()!;
  if (mailValido(cadena)) {
    print("El correo electronico $cadena es valido");
  } else {
    print("El correo electronico $cadena no es valido");
  }
}

bool mailValido(String cadena) {
  bool valido = false;
  final regex = RegExp(r'^[a-z0-9]+@[a-z]+.[com|es]{1}');
  if (regex.hasMatch(cadena)) {
    valido = true;
  }
  return valido;
}
