/*2- Haz un programa Dart “adivina un número entre 1 y 100”. El programa deberá extraer un
número al azar, ir pidiendo al usuario exterior que lo adivine, y darle pistas (“te pasaste”, “te
quedaste corto”), hasta que lo acierte. En ese momento, mostrará una felicitación, así como
el número de intentos que te ha costado acertar.
Probar distintas estrategias. La estrategia más exitosa en promedio es la búsqueda binaria
Para resolver el resto de ejercicios de esta relación, estudiaremos a fondo la librería
dart:core. No será necesario importarla, pues está “built-in”, pero se recomienda dar un
repaso a la documentación al respecto. */
import 'dart:math';
import 'dart:io';

int devolverEnteroValido(String mensaje) {
  bool numValido = false;
  int n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = int.parse(stdin.readLineSync()!);
      numValido = true;
    } catch (ex) {
      print(ex.toString());
    }
  }
  return n;
}

void main() {
  int random = (Random().nextInt(100) + 1);
  int numeroUser = -1;
  bool fin = false, numValido = false;
  while (!fin) {
    numValido = false;
    while (!numValido) {
      numeroUser = devolverEnteroValido("Introduzca un numero del 1 al 100: ");
      if ((numeroUser > 0) || (numeroUser < 101)) {
        numValido = true;
      }
    }
    if (numeroUser > random) {
      print("Te has pasado");
    }
    if (numeroUser < random) {
      print("Te has quedado corto");
    }
    if (numeroUser == random) {
      print("ACERTASTE");
      fin = true;
    }
  }
}
