/*9- Versiona el programa anterior para que muestre las 10 tablas de multiplicar (del 1 al 10).
Ya no será necesario introducir nada*/

import 'dart:io';

void main() {
  // Mostramos la tabla de multiplicar enter el 1 y el 10
  stdout.write("Tablas de multiplicar del 1 al 10\n\n");
  for (int n = 1; n <= 10; n++) {
    print("Tabla del $n:");
    for (int i = 1; i <= 10; i++) {
      print("$n*$i=${(i * n)}");
    }
    print("");
  }
}
