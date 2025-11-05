/*5- Haz un pequeño programa en Dart en el que, a partir de las medidas de los tres lados de
un triángulo (double) con valores asignados en la propia declaración, dictamine si éste es
equilátero, isósceles o escaleno. */

import 'dart:io';

void main() {
  String? resultado;
  stdout.write(
    'Por favor, un lado del triangulo:\n',
  ); // Solicita un numero al user
  int? l1 = int.parse(
    stdin.readLineSync()!,
  ); // Guarda la entrada como un entero

  stdout.write(
    '\nPor favor, un lado del triangulo:\n',
  ); // Solicita un numero al user
  int? l2 = int.parse(
    stdin.readLineSync()!,
  ); // Guarda la entrada como un entero

  stdout.write(
    '\nPor favor, un lado del triangulo:\n',
  ); // Solicita un numero al user
  int? l3 = int.parse(
    stdin.readLineSync()!,
  ); // Guarda la entrada como un entero

  //Dependiendo de la longitud de cada lago mostramos el tipo de triangulo que es
  if ((l1 == l2) && (l1 == l3)) {
    resultado = "El triangulo es equilatero";
  } else if ((l1 == l2) && (l1 != l3)) {
    resultado = "El triangulo es isosceles";
  } else {
    resultado = "El triangulo es escaleno";
  }
  print("$resultado");
}
