/*11- Haz un pequeño programa en Dart que declare una lista de temperaturas máximas. A
continuación, deberá invocar a una función que reciba como parámetro a esa lista y calcule
y devuelva cual es la temperatura máxima. Haz otra con el mismo esquema de parámetros
y devolución, que devuelva la mínima, y otra para la media.

Pregunta: estas funciones devuelven un valor numérico que queda descontextualizado en el
caso de la máxima y la mínima, perdiendo el dato de en qué día se produjeron. ¿Cómo lo
solucionarías? Hay varias opciones*/
import 'dart:io';

void main() {
  List registro = [];
  int dias = 1;

  // Pedimos al usuario que introduzca un entero valido hasta que lo introduce
  do {
    dias = devolverEnteroValido(
      "Introduzca el numero de dias de los cuales quiere tomar la temperatura: ",
    );
  } while (dias < 2);
  int i = 0;
  // Pedimos al usuario que introduzca la temperatura para cada dia y lo guardamos en una lsita
  while (i < dias) {
    int temperatura = devolverEnteroValido(
      "Introduzca la temperatura del dia ${(i + 1)}",
    );
    registro.add(temperatura);
    i++;
  }
  // Guaradmos la temperatura maxima en una variable usando el metodo al que le pasamos como parametro la lista con el registro de temperaturas
  int tempMax = tempsLimite(registro);
  print("La temperatura maxima de la lista $registro es $tempMax.");
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

// Metodo que devuelve la temperatura maxima a partir de una lista pasada como parametro
int tempsLimite(List lista) {
  return lista.reduce((tempMax, temp) => temp > tempMax ? temp : tempMax);
}
