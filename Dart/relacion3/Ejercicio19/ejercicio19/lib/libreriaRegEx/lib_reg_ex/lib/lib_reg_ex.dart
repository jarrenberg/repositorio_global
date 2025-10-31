/// A Calculator.
library;

import 'dart:convert';

bool letraDNIValida(String cadena) {
  bool valido = false;
  try {
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
  } catch (ex) {}
  return valido;
}

bool rGBValido(String cadena) {
  bool valido = false;
  final regex = RegExp(r'^#[0-9A-F]{6}$');
  if (regex.hasMatch(cadena)) {
    valido = true;
  }
  return valido;
}

bool uRLValido(String cadena) {
  bool valido = false;
  final regex = RegExp(r'^https?://www\.[a-zA-Z]+\.(com|es)(/[a-zA-Z]*)?$');
  if (regex.hasMatch(cadena)) {
    valido = true;
  }
  return valido;
}

bool mailValido(String cadena) {
  bool valido = false;
  final regex = RegExp(r'^[a-z0-9]+@[a-z]+\.[com|es]{1}');
  if (regex.hasMatch(cadena)) {
    valido = true;
  }
  return valido;
}
