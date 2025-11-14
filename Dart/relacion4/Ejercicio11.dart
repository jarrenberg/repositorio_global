/*11- Define la clase fracción en Dart con los atributos numerador y denominador. Define
también los siguientes métodos:
● constructor
● sumar
● restar
● multiplicar
● dividir
● simplificar
● máximo común divisor
● toString()
*/

class Fraccion {
  late int numerador;
  late int denominador;

  Fraccion(int numerador, int denominador) {
    if (denominador == 0) {
      throw Exception("El denominado no puede ser 0");
    }
    this.numerador = numerador;
    this.denominador = denominador;
  }

  int getNumerador() {
    return numerador;
  }

  int getDenominador() {
    return denominador;
  }

  void sumar(Fraccion f) {
    this.numerador =
        (numerador * f.getDenominador()) + (f.getNumerador() * denominador);
    this.denominador = denominador * f.getDenominador();
  }

  void restar(Fraccion f) {
    numerador =
        (numerador * f.getDenominador()) - (f.getNumerador() * denominador);
    denominador = denominador * f.getDenominador();
  }

  void multiplicar(Fraccion f) {
    numerador = numerador * f.getNumerador();
    denominador = denominador * f.getDenominador();
  }

  void dividir(Fraccion f) {
    numerador = numerador * f.getDenominador();
    denominador = denominador * f.getNumerador();
  }

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

  static Fraccion maximoComunDivisor(List<Fraccion> f) {
    return Fraccion(2, 3);
  }

  @override
  String toString() {
    return "[Fraccion: $numerador/$denominador]";
  }
}

void main(List<String> args) {
  try {
    Fraccion f = new Fraccion(30, 180);
    print(f.toString());
    f.simplificar();
    print(f.toString());
  } catch (e) {
    print(e);
  }
}
