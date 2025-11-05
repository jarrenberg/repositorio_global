/*7- Haz el pequeño programa en Dart que calcule el factorial de un número n inicializado a
un valor entero y positivo. La función tendrá a n como parámetro de entrada y devolverá el
valor de la suma al punto de la llamada*/
import 'dart:io';

void main() {
  int n;
  // Pedimos al usuario un numero entero mayor que 0 hasta que lo introduzca
  do {
    n = devolverEnteroValido(
      "Introduzca un entero positivo para calcular su factorial: ",
    );
  } while (n < 0);
  // Mostramos el factorial de n mediante el metodo

  print("El factorial de $n es ${calcularFactorial(n)}");
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

int calcularFactorial(int n) {
  int res = 1;
  if (n > 1) {
    for (int i = 1; i <= n; i++) {
      res = res * i;
    }
  }
  return res;
}
