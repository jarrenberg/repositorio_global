/*6- Haz un pequeño programa que Dart que te pida una frase por la entrada estándar y te la
muestre con las palabras en orden contrario. Y también la frase en orden inverso carácter a
carácter. También que te diga si la frase es un palíndromo (por ejemplo: ‘Dabale arroz a la
zorra el abad’ debería dar positivo) */
import 'dart:io';

void main(List<String> args) {
  stdout.write("Introduzca una cadena de texto: ");
  String cadena = stdin.readLineSync()!;
  dynamic palabrasReversa = cadena.split(" ").reversed;
  String cadenaReversa = "";
  for (var elemento in palabrasReversa) {
    cadenaReversa = cadenaReversa + elemento + " ";
  }

  print("Cadena de texto original: ${cadena}");
  print("Cadena de texto reversa: ${cadenaReversa}");
  if (esPalindromo(cadena)) {
    print("Las cadenas de texto son palindromas");
  } else {
    print("Las cadenas de texto no son palindromas");
  }
}

bool esPalindromo(String cadena) {
  bool palindromo = false;
  if (cadena.trim() == "") {
    palindromo = true;
  }
  return palindromo;
}
