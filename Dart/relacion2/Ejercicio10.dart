/*10- Haz un programa que declare e inicialice un número entero positivo e invoque a una
función que lo convierta en binario y lo devuelva en forma de cadena (String) de ceros y
unos. El programa deberá mostrar el resultado por pantalla.

Una vez que funcione adecuadamente, modifica la función para que tenga un segundo
parámetro, la base de conversión, que será 2 por defecto, pero que pueda ser cualquier
otra, hasta la base 8.

Pregunta: qué habría que añadirle a la función para que también pudiera convertir a base
16?*/

import 'dart:io';

void main() {
  int n = -1;
  // Pedimos al usuario un numero entero mayor que 0 hasta que lo introduce
  do {
    n = devolverEnteroValido("Introduzca un entero valido: ");
  } while (n < 0);

  // Mostrmoas el resultado de pasar el numero introducido por el usuario a la base
  print("El numero $n en binario se escribe ${pasarABaseX(n)}");
  print("El numero $n en base 8 se escribe ${pasarABaseX(n, 8)}");
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

// Metodo cuyo parametro es un entero que se transforma a la base pasada como parametro opcional
String pasarABaseX(int num, [int base = 2]) {
  int resto = 0, dividendo = num;
  String resultado = "";
  while (dividendo >= base) {
    resto = dividendo % base;
    dividendo = (dividendo ~/ base);
    resultado = "$resto$resultado";
  }

  return "$dividendo$resultado";
}
