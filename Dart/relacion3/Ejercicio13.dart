/*13- Investiga las funciones que se pueden utilizar en el manejo de una lista: isEmpty, add,
addAll, length, removeAt, clear, indexOf, sort… y construye un programa que defina una
lista inicial de colores:
List<String> colores = [‘rojo’,’verde’,’azul’];
A partir de ahí, muestre el siguiente menú de operaciones:
1. incluir color
2. eliminar color
3. buscar color
4. mostrar lista de colores
5. salir
El usuario entrará en un bucle del que se sale pulsando la opción 5. En cualquier otro caso,
efectuará la operación elegida.
Prueba a incluir, eliminar colores, y visualiza la evolución de la lista. ¿Qué ocurriría si
incluimos dos veces el mismo color?¿Se puede considerar esto un comportamiento
erróneo? De ser así, ¿cómo lo solucionarías? */
import 'dart:io';

void main(List<String> args) {
  menu();
}

void menu() {
  List colores = ['rojo', 'verde', 'azul'];
  bool salir = false;
  while (!salir) {
    print("\n\n*** MENU COLORES ***");
    int accion = devolverEnteroValido(
      "1. incluir color\n2. eliminar color\n3. buscar color\n4. mostrar lista de colores\n5. salir",
    );
    switch (accion) {
      case 1:
        print("Introduzca un color para añadir a la lista: ");
        String color = stdin.readLineSync()!;
        if (anyadirAlaListaSinRepetidos(colores, color)) {
          print("Se ha añadido el color $color a la lista");
        } else {
          print("No se ha añadido el color $color a la lista");
        }
        break;
      case 2:
        print("Introduzca un color para eliminar de la lista: ");
        String color = stdin.readLineSync()!;
        if (colores.remove(color)) {
          print("Se ha eliminado con exito el color $color");
        } else {
          print("No se ha podido eliminar el color $color");
        }
        break;
      case 3:
        print("Introduzca un color para buscar en la lista: ");
        String color = stdin.readLineSync()!;
        if (colores.contains(color)) {
          print("El color $color se encuentra en la lista");
        } else {
          print("El color $color no se encuentra en la lista");
        }
        break;
      case 4:
        print("Mostrando la lista de colores");
        colores.forEach((n) => print("- $n"));
        break;
      case 5:
        print("Saliendo ...");
        salir = true;
        break;
      default:
        print("La accion introducida no es valida");
    }
  }
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

bool anyadirAlaListaSinRepetidos(List lista, String color) {
  bool anyadido = false;
  bool existe = false;
  lista.forEach((elemento) {
    if (elemento.toLowerCase() == color.toLowerCase()) {
      existe = true;
    }
  });
  if (!existe) {
    lista.add(color);
    anyadido = true;
  }
  return anyadido;
}
