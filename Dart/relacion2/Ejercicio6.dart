/*6- Haz un programa en Dart que contenga una función que calcule la suma de los n
primeros números naturales. La función tendrá un parámetro n y devolverá el valor de la
suma al punto de la llamada*/
import 'dart:io';

void main() {
  int n;
  // Pedimos al usuario un numero entero mayor que 0 hasta que lo introduzca
  do {
    n = devolverEnteroValido(("Introduzca un entero mayor que 0: "));
  } while (n <= 0);

  // Mostramos la suma de los n primeros numeros naturales mediante el metodo
  print(
    "La suma de los ${n} primeros numeros enteros es ${sumaNNumerosNaturales(n)}",
  );
}

// Metodo que recibe un entero como parametro y devuelve la suma de todos los numeros naturales hasta el parametro
int sumaNNumerosNaturales(int n) {
  int res = 0;
  for (int i = 1; i <= n; i++) {
    res = res + i;
  }
  return res;
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
