/*5- Para el caso inverso anterior, utilizaremos la función parse(): a partir de una serie de
Strings que representan en formato cadena datos de tipo int, double, etc… convertiremos
y asignaremos su contenido a variables de cada uno de estos tipos. ¿Qué ocurre con List,
Map, Enum, Set…? ¿Cómo deben convertirse en String?

Por otra parte, a la hora de convertir un double en String, existen opciones que permiten
controlar el formato. Investígalo*/
import 'dart:convert';

void main(List<String> args) {
  String n1 = '2';
  String d1 = '2.2';
  String b1 = 'true';
  String l1 = '["Coche","Moto","Quad","Autobus"]';
  String m1 = '{"Luna":1,"Urano":2,"Saturno":3}';
  //Mirar como se parsea un string a enum

  int n = int.parse(n1);
  double d = double.parse(d1);
  bool b = bool.parse(b1);
  List l = jsonDecode(l1);
  Map m = jsonDecode(m1);
  print('Entero: $n');
  print('Double: $d');
  print('Booleano: $b');
  print('Lista: $l');
  print('Mapa: $m');
  print('Enum: ');
}
