/*20- Teniendo en cuenta que la creación de librerías de funciones favorece la reusabilidad de
las mismas, recoge todas las funciones del ejercicio 11 y ponlas en un archivo. A
continuación haz una versión de ese ejercicio, en el que importes la librería y te ahorres de
declarar dichas funciones*/
import 'LibEj20.dart';

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
