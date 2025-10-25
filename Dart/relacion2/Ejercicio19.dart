/*19- Haz lo mismo en otro programa con una función que genere una lista de todos los
números primos que hay entre 1 y n, siendo n un valor entero declarado e inicializado en el
programa. Ten en cuenta que puedes reutilizar la función esPrimo ya trabajada en el
ejercicio 13*/
import 'dart:io';

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
  while (num < 1) {
    num = devolverEnteroValido("Introduzca un entero positivo: ");
  }
  print("Los numeros primos de 1 a $num son: ${primosHastaN(num)}");
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
