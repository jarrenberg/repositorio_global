/*11- Haz el pequeño programa en Dart que calcule el factorial de un número n inicializado a
un valor entero y positivo*/
import 'dart:io';

void main() {
  int n = 0;
  while(n<=0){
    n = devolverEnteroValido("Introduzca un entero positivo");
  }
  int resultado = 1;
  for (int i = n; i >= 1; i--) {
    resultado = resultado * i;
  }
  stdout.write("\nEl resultado del factorial de $n es $resultado");
}

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
