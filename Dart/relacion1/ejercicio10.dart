/*10- Haz un programa en Dart que calcule la suma de los n primeros números naturales. El
valor de n será inicializado en el propio programa.*/
import 'dart:io';

void main() {
  int resultado = 0;
  int n = -1;
  // Pedimos al usuario numeros enteros hasta que se cumpla la condicion de que sea un numero natural
  while (n < 0) {
    n = devolverEnteroValido("Introduzca un numero natural");
  }
  // Sumamaos los n primeros numeros naturales
  for (int i = 0; i <= n; i++) {
    resultado = resultado + i;
  }
  // Mostramos el resultado de la suma de los N primeros numeros naturales
  print("El resultado de los $n primeros numeros naturales es $resultado");
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
