/*11- Ídem con DNIs españoles. Tendrá que comprobar que la letra es correcta . Consulta la
web del Ministerio de Interior*/
import 'dart:convert';
import 'dart:io';

void main(List<String> args) {
  stdout.write("Introduzca un DNI español: ");
  String cadena = stdin.readLineSync()!;
  if (letraDNIValida(cadena)) {
    print("El DNI $cadena es valido");
  } else {
    print("El DNI $cadena no es valido");
  }
}

bool letraDNIValida(String cadena) {
  bool valido = false;
  final regex = RegExp(r'^[0-9]{8}[A-Z]{1}$');
  List letrasDNI = jsonDecode(
    '["T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"]',
  );
  String subcad = cadena.substring(0, 8);
  String letra = cadena.substring(8, 9);
  int num = int.parse(subcad);
  int resto = num % 23;
  if ((letrasDNI.elementAt((resto)) == letra) && (regex.hasMatch(cadena))) {
    valido = true;
  }
  return valido;
}
