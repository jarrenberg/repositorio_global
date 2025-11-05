/*18- Haz un programa Dart que declare una lista, y la inicializa con los valores devueltos por
una función que devuelva los números de la serie de Fibonacci hasta llegar al término
n-ésimo, siendo n un valor entero declarado e inicializado al principio del programa*/
import 'dart:io';

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

void main() {
  int n = 0;
  // Pedimos al usuario que introduzca un numero entero mayor que -1 hasta que sea valido
  while (n <= 0) {
    n = devolverEnteroValido("Introduzca un numero enero positivo: ");
  }
  // Guardamos la lista de numeros de fibonacci hasta el numero introducido como parametro y la mostramos
  List numeros = serieFibonacci(n);
  print("La lista de los $n primeros numeros de fibonacci es: $numeros");
}

// Metodo que devuelve una lista con los valores de la serie de fibonacci hasta el limite pasado como parametro
List<int> serieFibonacci(int limite) {
  List<int> resultado = [];

  int a = 0;
  int b = 1;

  for (int i = 0; i < limite; i++) {
    resultado.add(a);
    int siguiente = a + b;
    a = b;
    b = siguiente;
  }

  return resultado;
}
