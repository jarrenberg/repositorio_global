/*10- Haz un programa en Dart que calcule la suma de los n primeros números naturales. El
valor de n será inicializado en el propio programa.*/
import 'dart:io';

void main() {
  int resultado = 0;
  int n = -1;
  while (n < 0) {
    n = devolverEnteroValido("Introduzca un numero natural");
  }
  for (int i = 0; i <= n; i++) {
    resultado = resultado + i;
  }
  print("El resultado de los $n primeros numeros naturales es $resultado");
}

int devolverEnteroValido(String mensaje) {
  bool numValido = false;
  int n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n",);
    try {
      n = int.parse(stdin.readLineSync()!);
      return n;
    } catch (ex) {
      print(ex.toString());
    }
  }
}
