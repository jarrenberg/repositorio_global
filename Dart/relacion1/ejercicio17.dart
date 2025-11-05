/*17- Declara un enum con los colores del arco iris. Muestra con un print todos los valores,
después, solo uno (están indexados a partir del 0), y accede a uno por su nombre
(byName). Provoca una excepción buscando uno que no exista ¿Qué ocurre?*/

import 'dart:io';

// Creamos un enumerado con los colores del arcoiris
enum coloresArcoiris { Rojo, Naranja, Amarillo, Verde, Azul, Indigo, Morado }

void main() {
  // Mostramos los colores del arcoiris
  print("Los colores del arcoiris son:");
  for (int i = 0; i < coloresArcoiris.values.length; i++) {
    stdout.write("${coloresArcoiris.values.elementAt(i).name} ");
  }
  print("");
  // Si buscamos un valor por el nombre y no existe sucede lo siguiente:
  String colorInexistente = "Negro";
  try {
    print(coloresArcoiris.values.byName(colorInexistente));
  } catch (ex) {
    print("No existe el color $colorInexistente en el arcoiris");
  }
}
