/*18- Modifica el programa anterior para que gestione el error con try-catch-finally. Para ello,
investiga en la documentación oficial como se gestionan los errores*/

import 'dart:io';

// No me di cuenta y lo solucione en el ejercicio anterior
enum coloresArcoiris { Rojo, Naranja, Amarillo, Verde, Azul, Indigo, Morado }

void main() {
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
