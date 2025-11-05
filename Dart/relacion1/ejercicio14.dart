/*14- Haz un pequeño programa en Dart que simule el lanzamiento de un dado. Investiga la
librería ‘dart:math’ en la documentación oficial*/
import 'dart:io';
import 'dart:math';

void main() {
  int numCaras = 0;
  // Pedimos que el usuario introduzca el numero de caras del dado hasta que este numero sea mayor que 5
  while (numCaras < 5) {
    numCaras = devolverEnteroValido(
      "Introduzca un numero de caras mayor a 5: ",
    );
  }
  // Calculamos el resultado de lanzar un dado del numero de caras introducido por el usuario y lo mostramos
  int valor = Random().nextInt(numCaras) + 1;
  print("Resultado: $valor");
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
