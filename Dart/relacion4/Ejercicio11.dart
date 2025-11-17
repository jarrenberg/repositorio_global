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

  // Metodo que suma la fraccion que emplea este metodo con la fraccion pasada como parametro
  void sumar(Fraccion f) {
    this.numerador =
        (numerador * f.getDenominador()) + (f.getNumerador() * denominador);
    this.denominador = denominador * f.getDenominador();
  }

  // Metodo que resta la fraccion que emplea este metodo con la fraccion pasada como parametro
  void restar(Fraccion f) {
    numerador =
        (numerador * f.getDenominador()) - (f.getNumerador() * denominador);
    denominador = denominador * f.getDenominador();
  }

  // Metodo que multiplica la fraccion que emplea este metodo con la fraccion pasada como parametro
  void multiplicar(Fraccion f) {
    numerador = numerador * f.getNumerador();
    denominador = denominador * f.getDenominador();
  }

  // Metodo que divide la fraccion que emplea este metodo entre la fraccion pasada como parametro
  void dividir(Fraccion f) {
    numerador = numerador * f.getDenominador();
    denominador = denominador * f.getNumerador();
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

  // Método para calcular el MCD de dos números usando el algoritmo de Euclides
  static int _calcularMCD(int a, int b) {
    a = a.abs();
    b = b.abs();

    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  // Método estático para calcular el MCD de una lista de fracciones
  static Fraccion maximoComunDivisor(List<Fraccion> fracciones) {
    if (fracciones.isEmpty) {
      throw Exception("La lista de fracciones no puede estar vacía");
    }

    if (fracciones.length == 1) {
      return Fraccion(fracciones[0].numerador, fracciones[0].denominador);
    }

    // Calcular el MCD de todos los numeradores
    int mcdNumeradores = fracciones[0].numerador.abs();
    for (int i = 1; i < fracciones.length; i++) {
      mcdNumeradores = _calcularMCD(mcdNumeradores, fracciones[i].numerador);
    }

    // Calcular el MCM de todos los denominadores
    int mcmDenominadores = fracciones[0].denominador;
    for (int i = 1; i < fracciones.length; i++) {
      mcmDenominadores = _calcularMCM(
        mcmDenominadores,
        fracciones[i].denominador,
      );
    }

    return Fraccion(mcdNumeradores, mcmDenominadores);
  }

  // Método auxiliar para calcular el MCM usando la relación MCD * MCM = a * b
  static int _calcularMCM(int a, int b) {
    int resultado = 0;
    if (a == 0 || b == 0) {
      resultado = 0;
    } else {
      resultado = (a * b) ~/ _calcularMCD(a, b);
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
  try {
    Fraccion f1 = new Fraccion(60, 180);
    print(f1.toString());
    Fraccion f2 = Fraccion(12, 7);
    print(f2.toString());
    print("\nSimplficando ambas fracciones ...");
    f1.simplificar();
    f2.simplificar();
    print(f1.toString());
    print(f2.toString());
    print("\nSumando las dos fracciones ...");
    // Creamos una variable con los mismos valores que f1
    Fraccion suma = new Fraccion(f1.getNumerador(), f1.getDenominador());
    suma.sumar(f2);
    print("Resultado: ${suma.toString()}");

    print("\nRestando las dos fracciones ...");
    Fraccion resta = new Fraccion(f1.getNumerador(), f1.getDenominador());
    resta.restar(f2);
    print("Resultado: ${resta.toString()}");

    print("\nMultiplicando las dos fracciones ...");
    Fraccion prod = new Fraccion(f1.getNumerador(), f1.getDenominador());
    prod.multiplicar(f2);
    print("Resultado: ${prod.toString()}");

    print("\nRestando las dos fracciones ...");
    Fraccion div = new Fraccion(f1.getNumerador(), f1.getDenominador());
    div.dividir(f2);
    print("Resultado: ${div.toString()}");

    print("\nMCM de las dos fracciones ...");
    List<Fraccion> f = new List.empty(growable: true);
    f.add(f1);
    f.add(f2);
    Fraccion mcm = Fraccion.maximoComunDivisor(f);
    print("El MCM de las dos fracciones es $mcm");
  } catch (e) {
    print(e);
  }
}
