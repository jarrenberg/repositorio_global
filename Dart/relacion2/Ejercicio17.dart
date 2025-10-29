/*17- Ídem con el ejercicio 9*/
/*9- Haz en Dart el programa que invoca a dos funciones, una que calcula el cociente de la
división entre dos números enteros positivos utilizando el algoritmo de Euclides y la otra que
calcula el resto. Los dos números serán parámetros de entrada, y la función devolverá el
resultado al punto de la llamada. Ambos números serán inicializados en el propio programa.

Pregunta 1: qué estructura de datos nos permitiría que una función pudiera devolver dos
valores simultáneamente?
En mi caso, use una estructura List

Pregunta 2: ¿Por qué no sería recomendable hacer una función así? Investiga el principio SRP*/

import 'dart:io';

void main() {
  int n1 = -1;
  int n2 = -1;

  while (n1 <= 0) {
    n1 = devolverEnteroValido("Introduzca un numero entero");
  }
  while (n2 <= 0) {
    n2 = devolverEnteroValido("Introduzca un numero entero");
  }

  int dividendo = n1 >= n2 ? n1 : n2;
  int divisor = n1 >= n2 ? n2 : n1;
  List<int>? resultadoLista;
  try {
    resultadoLista = divisionEnteraLista(dividendo, divisor);
    int cociente = resultadoLista[0];
    int resto = resultadoLista[1];
    print("El cociente de la division es $cociente y el resto es $resto");
  } catch (ex) {
    print(ex.toString());
  }
}

int devolverEnteroValido(String mensaje) {
  bool numValido = false;
  int n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = int.parse(stdin.readLineSync()!);
      return n;
    } catch (ex) {
      print(ex.toString());
    }
  }
}

int devolverCociente(int dividendo, int divisor) {
  if (dividendo < divisor) {
    return 0;
  }
  return 1 + devolverCociente(dividendo - divisor, divisor);
}

int devolverResto(int dividendo, int divisor) {
  if (dividendo < divisor) {
    return dividendo;
  }
  return devolverResto(dividendo - divisor, divisor);
}

List<int> divisionEnteraLista(int dividendo, int divisor) {
  return [
    devolverCociente(dividendo, divisor),
    devolverResto(dividendo, divisor),
  ];
}
