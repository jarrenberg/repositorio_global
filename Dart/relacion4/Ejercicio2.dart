import 'dart:io';

class Restaurante {
  late String name;
  late String cuisine;
  late List<double> ratings;

  Restaurante(String name, String cuisine, List<double> ratings) {
    this.name = name;
    this.cuisine = cuisine;
    this.ratings = ratings;
  }

  int getCantidadRatings() {
    return this.ratings.length;
  }

  void anyadirRating(double rating) {
    this.ratings.add(rating);
  }

  void anyadirMultiplesRatings(List<double> ratingsExtra) {
    this.ratings.addAll(ratingsExtra);
  }

  double getRatingMedio() {
    double resultado = 0;
    this.ratings.forEach((n) {
      resultado = resultado + n;
    });
    resultado = resultado / ratings.length;
    return resultado;
  }
}

double devolverDoubleValido(String mensaje) {
  bool numValido = false;
  double n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = double.parse(stdin.readLineSync()!);
      numValido = true;
    } catch (ex) {
      print(ex.toString());
    }
  }
  return n;
}

int devolverEnteroValido(String mensaje) {
  bool numValido = false;
  int n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = int.parse(stdin.readLineSync()!);
      numValido = true;
    } catch (ex) {
      print(ex.toString());
    }
  }
  return n;
}

void main(List<String> args) {
  stdout.write("Creando el restaurante Baratie ...\n");
  final baratie = Restaurante("Restaurante Baratie", "Sanji", [1, 7.8, 5.4]);
  stdout.write(
    "Cantidad de calificaciones que tiene el restaurante: ${baratie.getCantidadRatings()}\n",
  );
  stdout.write(
    "Mostrando el rating medio del restaurante: ${baratie.getRatingMedio()}\n",
  );
  stdout.write("Añadiendo una calificacion ...\n");
  double calificacion = devolverDoubleValido("Introduzca una calificacion: ");
  baratie.anyadirRating(calificacion);
  stdout.write(
    "Cantidad de calificaciones que tiene el restaurante: ${baratie.getCantidadRatings()}\n",
  );
  stdout.write(
    "Mostrando el rating medio del restaurante: ${baratie.getRatingMedio()}\n",
  );
  int numeroRatings = devolverEnteroValido(
    "Introduzca un numero de calificaciones que desería añadir",
  );
  int contador = 0;
  List<double> listaRatings = List.empty(growable: true);
  do {
    calificacion = devolverDoubleValido("Introduzca una calificaicon: ");
    listaRatings.add(calificacion);
    contador++;
  } while (contador < numeroRatings);
  stdout.write("Añadiendo la lista de calificaciones ...\n");
  baratie.anyadirMultiplesRatings(listaRatings);
  stdout.write(
    "Cantidad de calificaciones que tiene el restaurante: ${baratie.getCantidadRatings()}\n",
  );
  stdout.write(
    "Mostrando el rating medio del restaurante: ${baratie.getRatingMedio()}\n",
  );
}