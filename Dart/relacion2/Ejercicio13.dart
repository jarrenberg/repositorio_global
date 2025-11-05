/*13- Haz un pequeño programa Dart que defina la función esPrimo(int n), a la que invocará
pasándole un número entero positivo, y que devolverá un valor booleano tras buscarle
divisores al número: true si lo es, false si no lo es.*/
import 'dart:io';

// Metodo que devuelve true si el numero introducido como parametro es primo o false si no lo es
bool esPrimo(int num) {
  bool resultado = true;
  int i = 2;
  if (num == 1) {
    resultado = false;
  }
  if (num == 2) {
    resultado = true;
  } else {
    while (i <= num ~/ 2) {
      if (num % i == 0) {
        resultado = false;
      }
      i++;
    }
  }
  return resultado;
}

void main() {
  int num = 0;
  // Pedimos al usuario que introduzca un numero mayor que 0 hasta que lo haga
  while (num < 1) {
    num = devolverEnteroValido("Introduzca un entero positivo: ");
  }
  // Mostramos si el numero introducido es primo o no pasandoselo como parametro al metodo esPrimo
  if (esPrimo(num)) {
    print("El numero $num es primo.");
  } else {
    print("El numero $num no es primo.");
  }
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
