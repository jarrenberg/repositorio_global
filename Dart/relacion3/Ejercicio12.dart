/*12- Crea una librería con todas estas funciones llamada validaciones.dart, y prueba a
importarla desde cada programa anterior. Investiga la documentación oficial para más
posibilidades*/
import 'dart:io';
import 'libreriaRegEx/lib_reg_ex/lib/lib_reg_ex.dart' as UtilidadesRegEx;

void main(List<String> args) {
  print("Introduzca el codigo de color RGB en formato hexadecimal: ");
  String cadena = stdin.readLineSync()!;
  if (UtilidadesRegEx.rGBValido(cadena)) {
    print("El codigo de color RGB en formato hexadecimal es correcto");
  } else {
    print("El codigo de color RGB en formato hexadecimal es incorrecto");
  }

  print("Introduzca el email: ");
  cadena = stdin.readLineSync()!;
  if (UtilidadesRegEx.mailValido(cadena)) {
    print("El email es correcto");
  } else {
    print("El email es incorrecto");
  }

  print("Introduzca la URL: ");
  cadena = stdin.readLineSync()!;
  if (UtilidadesRegEx.uRLValido(cadena)) {
    print("La URL es correcta");
  } else {
    print("La URL es incorrecta");
  }

  print("Introduzca el DNI español: ");
  cadena = stdin.readLineSync()!;
  if (UtilidadesRegEx.letraDNIValida(cadena)) {
    print("El DNI es correcto");
  } else {
    print("El DNI es incorrecto");
  }
}
