/*14- Haz una nueva versión del ejercicio 6, esta vez con una función RECURSIVA*/
/*6- Haz un programa en Dart que contenga una función que calcule la suma de los n
primeros números naturales. La función tendrá un parámetro n y devolverá el valor de la
suma al punto de la llamada*/

import 'dart:io';

void main() {
  int n;
  // Pedimos al usuario un entero mayor que -1 hasta que el numero sea valido
  do {
    n = devolverEnteroValido(("Introduzca un entero mayor que 0: "));
  } while (n <= 0);

  // Mostramos la suma de los n primeros numeros enteros hasta el numero introducido por el usuario utilizando el metodo
  print(
    "La suma de los ${n} primeros numeros enteros es ${sumaNNumerosNaturales(n)}",
  );
}

// Metodo que devuelve la suma de los numeros naturales hasta el entero pasado como parametro
int sumaNNumerosNaturales(int n) {
  int resultado = 0;
  if (n == 0) {
    resultado = 0;
  } else {
    resultado = n + sumaNNumerosNaturales(n - 1);
  }
  return resultado;
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
