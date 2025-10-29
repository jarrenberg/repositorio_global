/*4- Haremos un pequeño programa en Dart que defina e inicialice internamente un String y,
a continuación, una serie de datos de cada uno de los tipos vistos en la relación 1 (int,
double, bool, List, Map, Enum, Set) y concatenamos el String con cada uno de ellos
utilizando el operador + y la función toString(). Mostraremos el resultado por pantalla para
ver cómo se comporta esta función*/

enum diasSemana { Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo }

void main() {
  int n1 = 1;
  double d1 = 2.2;
  bool b1 = true;
  List l1 = [1, 2, 3];
  Map m1 = {};
  List<MapEntry<String, int>> entradas = [
    MapEntry('Saturno', 1),
    MapEntry('Marte', 2),
    MapEntry('Pluton', 3),
  ];
  m1.addEntries(entradas);

  String cadena = 'Entero = '+n1.toString()+' Double = '+d1.toString()+' Boolean = '+b1.toString()+' Lista = '+l1.toString()+' Mapa = '+m1.toString()+' Enum = '+diasSemana.Lunes.name.toString();
  print(cadena);
}
