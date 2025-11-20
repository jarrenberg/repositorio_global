/*13- Crea un mixin en Dart llamado Comparable<T> . A partir de un tipo T, deberá crear una
interfaz que incluya los métodos anteriores. ¿Podría usarse en el caso anterior?*/
import 'dart:core';

mixin Comparable<T> {
  bool esMayorQue(T otro);
  bool esMenosQue(T otro);
  bool esIgualA(T otro);
  
  bool esMayorOIgualQue(T otro) => esMayorQue(otro) || esIgualA(otro);
  bool esMenorOIgualQue(T otro) => esMenosQue(otro) || esIgualA(otro);
  bool esDistintoQue(T otro) => !esIgualA(otro);
}

class Fraccion with Comparable<Fraccion> {
  late int numerador;
  late int denominador;

  // Constructor de la clase Fraccion
  Fraccion(int numerador, int denominador) {
    if (denominador == 0) {
      throw Exception(
        "El denominado no puede ser 0",
      );
    }
    this.numerador = numerador;
    this.denominador = denominador;
  }

  // Metodo que devuelve el numerador
  int getNumerador() {
    return numerador;
  }

  // Metodo que devuelve el denominador
  int getDenominador() {
    return denominador;
  }

  // Metodo que simplifica una fraccion
  void simplificar() {
    bool simplificable = true;
    while (simplificable) {
      int numMayor = numerador > denominador ? numerador : denominador;
      int numMenor = numerador > denominador ? denominador : numerador;
      if ((numMayor % numMenor == 0) && (numMenor != 1)) {
        numerador = (numerador ~/ numMenor);
        denominador = (denominador ~/ numMenor);
      } else {
        simplificable = false;
      }
    }
  }

  @override
  bool esMayorQue(Fraccion f) {
    return (this.numerador / this.denominador) > 
           (f.getNumerador() / f.getDenominador());
  }

  @override
  bool esMenosQue(Fraccion f) {
    return (this.numerador / this.denominador) < 
           (f.getNumerador() / f.getDenominador());
  }

  @override
  bool esIgualA(Fraccion f) {
    this.simplificar();
    f.simplificar();
    return (this.getDenominador() == f.getDenominador()) &&
           (this.getNumerador() == f.getNumerador());
  }

  // Metodo que muestra la informacion de un objeto Fraccion
  @override
  String toString() {
    return "[Fraccion: $numerador/$denominador]";
  }
}

void main(List<String> args) {
  Fraccion f = Fraccion(5, 7);
  Fraccion g = Fraccion(48, 54);

  // Pruebas de los métodos del mixin
  if (f.esMayorQue(g)) {
    print("${f.toString()} es mayor que ${g.toString()}");
  } else {
    print("${g.toString()} es mayor que ${f.toString()}");
  }

  if (f.esMenosQue(g)) {
    print("${f.toString()} es menor que ${g.toString()}");
  } else {
    print("${g.toString()} es menor que ${f.toString()}");
  }

  if (f.esIgualA(g)) {
    print("${f.toString()} es igual que ${g.toString()}");
  } else {
    print("${f.toString()} es distinto que ${g.toString()}");
  }

  if (f.esMayorOIgualQue(g)) {
    print("${f.toString()} es mayor o igual que ${g.toString()}");
  } else {
    print("${f.toString()} no es mayor o igual que ${g.toString()}");
  }

  if (f.esMenorOIgualQue(g)) {
    print("${f.toString()} es menor o igual que ${g.toString()}");
  } else {
    print("${f.toString()} no es menor o igual que ${g.toString()}");
  }

  if (f.esDistintoQue(g)) {
    print("${f.toString()} es distinto que ${g.toString()}");
  } else {
    print("${f.toString()} es igual que ${g.toString()}");
  }
}