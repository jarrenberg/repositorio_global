/*13- Calcula en Dart el cociente y el resto de la división entera entre dos números enteros y
positivos utilizando el algoritmo de Euclides*/
import 'dart:io';

void main() {
  int n1 = -1;
  int n2 = -1;

  // Pedimos numeros enteros y positivos al usuario hasta que los introduzca
  while (n1 <= 0) {
    n1 = devolverEnteroValido("Introduzca un numero entero");
  }
  while (n2 <= 0) {
    n2 = devolverEnteroValido("Introduzca un numero entero");
  }

  // Dependiendo de los valores introducidos asignamos el numero mayor al dividendo y el menor al divisor
  int dividendo = n1 >= n2 ? n1 : n2;
  int divisor = n1 >= n2 ? n2 : n1;
  List<int>? resultadoLista;

  try {
    // Utilizamos el metodo divisionEnteraLista introduciendo los paamtros y mostramos el cociente y el resto de la division guardados en una lista
    resultadoLista = divisionEnteraLista(dividendo, divisor);
    int cociente = resultadoLista[0];
    int resto = resultadoLista[1];
    print("El cociente de la division es $cociente y el resto es $resto");
  } catch (ex) {
    print(ex.toString());
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

// Metodo que devuelve una lista con el cociente y el resto de los dos enteros pasados como parametro
List<int> divisionEnteraLista(int dividendo, int divisor) {
  if (divisor == 0) {
    throw ArgumentError("El divisor no puede ser cero");
  }

  int cociente = 0;
  int resto = dividendo;

  while (resto >= divisor) {
    resto -= divisor;
    cociente++;
  }

  return [cociente, resto];
}
