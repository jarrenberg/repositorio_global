/*14- Haz un pequeño programa en Dart que simule el lanzamiento de un dado. Investiga la
librería ‘dart:math’ en la documentación oficial*/
import 'dart:io';
import 'dart:math';
void main(){
    int numCaras = 0;
    while(numCaras<5){
      numCaras=devolverEnteroValido("Introduzca un numero de caras mayor a 5: ");
    }
    int valor = Random().nextInt(numCaras)+1;
    print("Resultado: $valor");
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