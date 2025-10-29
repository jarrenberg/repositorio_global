/*15- Lo mismo con el ejercicio 7*/
/*7- Haz el pequeño programa en Dart que calcule el factorial de un número n inicializado a
  un valor entero y positivo. La función tendrá a n como parámetro de entrada y devolverá el
  valor de la suma al punto de la llamada*/
import 'dart:io';

void main() {
  int n;
  do {
    n = devolverEnteroValido(
      "Introduzca un entero positivo para calcular su factorial: ",
    );
  } while (n < 0);

  print("El factorial de $n es ${calcularFactorial(n)}");
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

int calcularFactorial(int n) {
  int resultado;
  if (n <= 1) {
    resultado = 1;
  } else {
    resultado = n *calcularFactorial(n - 1);
  }
  return resultado;
}
