/*12- Revisita la clase Fracción y crea una clase derivada FraccionComparable que incluya
los métodos booleanos que calculen:
● esMayorQue
● esMenosQue
● esIgualA
● esMayorOIgualQue
● esMenorOIgualQue
● esDistintoQue */

import 'dart:core';

class Fraccion {
  late int numerador;
  late int denominador;

  // Constructor de la clase Fraccion
  Fraccion(int numerador, int denominador) {
    if (denominador == 0) {
      throw Exception(
        "El denominado no puede ser 0",
      ); // lanzamos una excepcion si el denominador es 0
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

// Metodo que devuelve 
  bool esMayorQue(Fraccion f) {
    bool mayor = false;
    if ((this.numerador / this.denominador) >
        f.getNumerador() / f.getDenominador()) {
      mayor = true;
    }
    return mayor;
  }

  bool esMenosQue(Fraccion f) {
    bool resultado = true;
    if ((this.numerador / this.denominador) >
        f.getNumerador() / f.getDenominador()) {
      resultado = false;
    }
    return resultado;
  }

  bool esIgualA(Fraccion f) {
    bool resultado = false;
    this.simplificar();
    f.simplificar();
    if ((this.getDenominador() == f.getDenominador()) &&
        (this.getNumerador() == f.getNumerador())) {
      resultado = true;
    }
    return resultado;
  }

  bool esMayorOIgualQue(Fraccion f) {
    bool resultado = false;
    if ((this.numerador / this.denominador) >=
        f.getNumerador() / f.getDenominador()) {
      resultado = true;
    }
    return resultado;
  }

  bool esMenorOIgualQue(Fraccion f) {
    bool resultado = true;
    if ((this.numerador / this.denominador) >=
        f.getNumerador() / f.getDenominador()) {
      resultado = false;
    }
    return resultado;
  }

  bool esDistintoQue(Fraccion f) {
    bool resultado = false;
    if (!this.esIgualA(f)) {
      resultado = true;
    }
    return resultado;
  }

  // Metodo que muestra la informacion de un objeto Fraccion
  @override
  String toString() {
    return "[Fraccion: $numerador/$denominador]";
  }
}

void main(List<String> args) {
  Fraccion f = new Fraccion(5, 7);
  Fraccion g = new Fraccion(48, 54);

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
