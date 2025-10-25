/*14- Haz una nueva versión del ejercicio 6, esta vez con una función RECURSIVA*/
/*6- Haz un programa en Dart que contenga una función que calcule la suma de los n
primeros números naturales. La función tendrá un parámetro n y devolverá el valor de la
suma al punto de la llamada*/

import 'dart:io';

void main() {
  int n;
  do {
    n = devolverEnteroValido(("Introduzca un entero mayor que 0: "));
  } while (n <= 0);
  print("La suma de los ${n} primeros numeros enteros es ${sumaNNumerosNaturales(n)}");
}

int sumaNNumerosNaturales(int n) {
  int res = 0;
  for (int i = 1; i <= n; i++) {
    int anterior = sumaNNumerosNaturales(i);
    res = anterior + i;
  }
  return res;
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