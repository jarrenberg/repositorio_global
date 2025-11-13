/*7- Dado el siguiente programa que define las clases Figura, Círculos y Cuadrado:
Añade una nueva clase hija llamada Triangulo que tenga como atributos lado1 ,
lado2 y lado3.
Añade un nuevo método abstracto get perimetro a la clase Figura e impleméntala en
todas las subclases.
Notas
● el perímetro de un cuadrado es igual a 4 * lado
● el perímetro de un círculo es igual a 2 * pi * radio
● el perímetro de un triángulo es igual a lado1 + lad2 + lado3
Después, añade un nuevo método printValores() a la clase Figura. Cuando sea
llamado, deberá imprimir tanto area como perimetro.
Finalmente, crea una lista de Figuras que contenga tanto Cuadrados como Circulos y
Triangulos y llame a printValues() con cada uno de sus componentes.*/

import 'dart:math';

abstract class Figura {
  double get area;
  double get perimetro;
  void get printValores;
}

class Cuadrado extends Figura {
  Cuadrado(this.lado);
  final double lado;
  @override
  double get area => lado * lado;

  @override
  double get perimetro => lado * 4;

  @override
  void get printValores => print("Area: $area\tPerimetro: $perimetro");
}

class Circulo extends Figura {
  Circulo(this.radio);
  final double radio;
  @override
  double get area => pi * radio * radio;

  @override
  double get perimetro => pi * radio * 2;
  void get printValores => print("Area: $area\tPerimetro: $perimetro");
}

class Triangulo extends Figura {
  late double lado1;
  late double lado2;
  late double lado3;
  Triangulo(double lado1, double lado2, double lado3) {
    this.lado1 = lado1;
    this.lado2 = lado2;
    this.lado3 = lado3;
  }
  @override
  double get area => sqrt(
    ((lado1 + lado2 + lado3) / 2) *
        (((lado1 + lado2 + lado3) / 2) - lado1) *
        (((lado1 + lado2 + lado3) / 2) - lado2) *
        (((lado1 + lado2 + lado3) / 2) - lado3),
  );

  @override
  double get perimetro => lado1 + lado2 + lado3;

  void get printValores => print("Area: $area\tPerimetro: $perimetro");
}

void main(List<String> args) {
  List<Figura> lista = List.empty(growable: true);
  lista.add(Circulo(4));
  lista.add(Cuadrado(4));
  lista.add(Triangulo(3, 4, 5));

  for (var element in lista) {
    element.printValores;
  }
}
