/*20- Teniendo en cuenta que la creación de librerías de funciones favorece la reusabilidad de
las mismas, recoge todas las funciones del ejercicio 11 y ponlas en un archivo. A
continuación haz una versión de ese ejercicio, en el que importes la librería y te ahorres de
declarar dichas funciones*/
import 'LibEj20.dart';

void main() {
  List registro = [];
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
    registro.add(temperatura);
    i++;
  }
  int tempMax = tempsLimite(registro);
  print("La temperatura maxima de la lista $registro es $tempMax.");
}