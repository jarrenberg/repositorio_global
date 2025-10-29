/*3- Vamos a declarar en un programa Dart un String al que inicializamos con una frase.
Examinaremos el contenido del String para saber si está todo en mayúsculas, en
minúsculas o ni lo uno ni lo otro. ( toUpperCase() y toLowerCase() ). Investigaremos y
probaremos también cómo se crea un string multilínea. */

import 'dart:io';

void main(List<String> args) {
  stdout.write("Introduzca una cadena de texto: ");
  String cadena = stdin.readLineSync()!;
  if (comprobarCadenaMayusculas(cadena) && !comprobarCadenaMinusculas(cadena)) {
    print("La cadena esta toda en mayusculas");
  } else if (!comprobarCadenaMayusculas(cadena) &&
      comprobarCadenaMinusculas(cadena)) {
    print("La cadena esta toda en minusculas");
  } else {
    print("La cartera no esta ni toda en minusculas ni toda en mayusculas");
  }
  print("Para crear un caracter multi\nlinea usamos el caracter de escape %\n");
}

bool comprobarCadenaMayusculas(String cadena) {
  bool resultado = false;
  if (cadena.toUpperCase() == cadena) {
    resultado = true;
  }
  return resultado;
}

bool comprobarCadenaMinusculas(String cadena) {
  bool resultado = false;
  if (cadena.toLowerCase() == cadena) {
    resultado = true;
  }
  return resultado;
}
