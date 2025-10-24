/*8- Haz un programa en Dart que muestre la tabla de multiplicar de un número entero (entre
1 y 10) que se introducirá por consola. Investiga la función stdin.readLineSync() para
realizar entrada de datos desde consola. ¿Qué ocurre con el sistema “sound type”?.
Soluciónalo utilizando un tipo nullable. Testea la entrada sometiéndola a distintas pruebas
de datos de entrada.*/
import 'dart:io';

void main() {
  bool numValido = false;
  int n=0;
  while (!numValido) {
   int n = devolverEnteroValido("Introduzca un numero entero entre 1 y 10");
    if((n>=1)&&(n<=10)){
      numValido=true;
    }
  }
  print("Tabla de multiplicar del $n");
  for (int i = 1; i <= 10; i++) {
    print("$n*$i=${(i * n)}");
  }
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
