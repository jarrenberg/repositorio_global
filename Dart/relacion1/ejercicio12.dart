/*12- Calcula en Dart el MCD de dos números enteros positivos utilizando el algoritmo de
Euclides*/

import 'dart:io';

void main() {
  int n1 = -1;
  int n2 = -1;

  // Pedimos al usuario dos enteros positivos hasta que ambos sean validos
  while (n1 <= 0) {
    n1 = devolverEnteroValido("Introduzca un numero entero");
  }
  while (n2 <= 0) {
    n2 = devolverEnteroValido("Introduzca un numero entero");
  }

  // Usamos el metodo que calcula el resultado, lo guardamos en una variable y lo mostramos
  int mcd = calcularMCD(n1, n2);
  print("El MCD de $n1 y $n2 es: $mcd");
}

// Metodo que recibe como parametro un mensaje para mostrar al usuario y devuelve un entero valido
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

// Metodo que recibe dos enteros como parametro y devuelve su Maximo Comun Divisor
int calcularMCD(int a, int b) {
  while (b != 0) {
    int temp = b;
    b = a % b;
    a = temp;
  }
  return a;
}
