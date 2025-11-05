/*16- Declara en Dart un lista con los días laborables en Español y muéstrala. Añádele los
dos días del fin de semana. Recorre la lista día por día y muéstralos por pantalla. Haz lo
mismo con un Map que contenga una serie de nombres y edades. En estos dos últimos
casos, usa forEach
*/

import 'dart:io';

void main() {
  // Declaramos una lista con los dias laborables
  List<String> laborables = [
    'Lunes',
    'Martes',
    'Miércoles',
    'Jueves',
    'Viernes',
  ];
  // Mostramos dicha lista
  print("Los dias laborables son:");

  for (int i = 0; i < laborables.length; i++) {
    stdout.write("${laborables[i]} ");
  }
  print("");

  // Añadimos el sabado y el domingo a la lista de dias laborables
  print("Añadiendo el Sabado y el Domingo ...");
  laborables.add("Sabado");
  laborables.add("Domingo");

  // Mostramos los dias de la semana
  print("Mostrando los dias de la semana ...");
  for (int i = 0; i < laborables.length; i++) {
    stdout.write("${laborables[i]} ");
  }
  stdout.write("\n\n");

  // Creamos un mapa con alumnos de la clase, con clave su nombre y valor su edad
  Map<String, int> personas = Map.new();
  print("Añadiendo alumnos a la clase ...");
  personas.addEntries([
    MapEntry("Jorge", 23),
    MapEntry("Antonio", 24),
    MapEntry("Raquel", 25),
    MapEntry("Eliana", 19),
  ]);

  // Mostramos el nombre y la edad de cada alumno de la clase
  personas.forEach((nombre, edad) => stdout.write("$nombre, $edad años\n"));
}
