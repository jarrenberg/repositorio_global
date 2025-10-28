/*12- Soluciona el problema anterior mediante la declaración de un Map y haciendo que cada
función, en lugar de devolver valores, muestran los resultados por pantalla ellas mismas.*/
import 'dart:io';

void main() {
  Map registro = {};
  int dias = 1;
  do {
    dias = devolverEnteroValido(
      "Introduzca el numero de dias de los cuales quiere tomar la temperatura: ",
    );
  } while (dias < 2);
  int i = 0;
  while (i < dias) {
    int temperatura = devolverEnteroValido(
      "Introduzca la temperatura del dia ${(i + 1)}",
    );
    registro[(i + 1)] = temperatura;
    i++;
  }
  print(registro);
  print("El dia mas calido es el dia ${devolverDiaMasCalido(registro).key} con una temperatura de ${devolverDiaMasCalido(registro).value}ºC");
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

MapEntry devolverDiaMasCalido(Map mapa) {
  MapEntry resultado = MapEntry(0, -99999999999);
  mapa.forEach(
    (key, value) => value > resultado.value
        ? resultado = MapEntry(key, value)
        : resultado = resultado,
  );
  return resultado;
  
}
