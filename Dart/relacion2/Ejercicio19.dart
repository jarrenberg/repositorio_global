/*19- Haz lo mismo en otro programa con una función que genere una lista de todos los
números primos que hay entre 1 y n, siendo n un valor entero declarado e inicializado en el
programa. Ten en cuenta que puedes reutilizar la función esPrimo ya trabajada en el
ejercicio 13*/
import 'dart:io';

// Metodo que devuelve si el entero pasado como parametro es primo o no
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

// Metodo que devuelve una lista con los numeros primos desde 1 hasta el entero pasado como parametro
List primosHastaN(int n) {
  List resultado = [];
  for (int i = 2; i <= n; i++) {
    if (esPrimo(i) == true) {
      resultado.add(i);
    }
  }
  return resultado;
}

void main() {
  int num = 0;
  // Pedimos al usuario que introduzca un numero entero mayor que 1 hasta que sea valido
  while (num < 1) {
    num = devolverEnteroValido("Introduzca un entero positivo: ");
  }

  // Mostramos los numeros primos desde 1 hasta el numero introducido por el usuario pasado como parametro usando el metodo primosHastaN
  print("Los numeros primos de 1 a $num son: ${primosHastaN(num)}");
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
