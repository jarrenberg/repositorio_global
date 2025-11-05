/*11- Haz el pequeño programa en Dart que calcule el factorial de un número n inicializado a
un valor entero y positivo*/
import 'dart:io';

void main() {
  int n = 0;
  // Pedimos al usuario numeros enteros que sean mayores que -1 hasta que el usuario los introduzca
  while(n<=0){
    n = devolverEnteroValido("Introduzca un entero positivo");
  }
  int resultado = 1;

  // Calculamos el factorial del numero introducido por el usuario y lo guardamos en la variable resultados
  for (int i = n; i >= 1; i--) {
    resultado = resultado * i;
  }

  // Mostramos el resultado
  stdout.write("\nEl resultado del factorial de $n es $resultado");
}

// Metodo que recibe como parametro un mensaje para mostrar al usuario y devuelve un entero valido
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
