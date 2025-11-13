/*6- Repitamos el ejercicio anterior pero partiendo de una perspectiva distinta: se ha
introducido en Gemini el siguiente prompt:
“definir clase vector en el plano dart con dos puntos origen y destino constructor y métodos
para producto escalar, suma de vectores, módulo”
y se ha obtenido el siguiente código, que si bien es correcto, no casa con lo que se pedía en
el enunciado. Refactorízalo para que haga uso de la clase Punto ya definida:*/

import 'Ejercicio4.dart';

class Vector {
  late double x1, y1, z1; // Coordenadas del origen
  late double x2, y2, z2; // Coordenadas del destino

  // Constructor
  Vector(Punto inicio, Punto fin) {
    this.x1 = inicio.getX();
    this.y1 = inicio.getY();
    this.z1 = inicio.getZ();
    this.x2 = fin.getX();
    this.y2 = fin.getY();
    this.z2 = fin.getZ();
  }

  // Método para calcular el producto escalar
  double productoEscalar(Vector otroVector) {
    return (x2 - x1) * (otroVector.x2 - otroVector.x1) +
        (y2 - y1) * (otroVector.y2 - otroVector.y1) +
        (z2 - z1) * (otroVector.z2 - otroVector.z1);
  }

  // Método para multiplicar un vector por un escalar
  Vector multiplicarPorEscalar(double escalar) {
    return Vector(
      new Punto(x1, y1, z1),
      new Punto(
        x1 + (x2 - x1) * escalar,
        y1 + (y2 - y1) * escalar,
        z1 + (z2 - z1) * escalar,
      ),
    );
  }

  // Método para sumar dos vectores
  Vector suma(Vector otroVector) {
    return Vector(
      new Punto(x1, y1, z1),
      new Punto(
        x2 + (otroVector.x2 - otroVector.x1),
        y2 + (otroVector.y2 - otroVector.y1),
        z2 + (otroVector.z2 - otroVector.z1),
      ),
    );
  }

  // Método para calcular el módulo del vector
  double modulo() {
    return ((x2 - x1) * (x2 - x1) +
        (y2 - y1) * (y2 - y1) +
        (z2 - z1) * (z2 - z1));
  }
}
