/*20- Investiga qué son las funciones sin nombre y como se declaran y utilizan en Dart. Haz
un programa que pruebe cada uno de los siguientes tipos:
1. Función sin nombre asignada a una variable:
2. Función sin nombre dentro de una función de orden superior (como forEach)
3. Función con la sintaxis de flecha (=>) para funciones de una sola línea
4. Función como argumento de otra función
5. Función dentro de un map*/
void main(List<String> arguments) {
  //1. Funcion sin nombre asignada a una variable
  print("1. Funcion sin nombre asignada a una variable");
  void muestraNumero(int numero) {
    print("Mostrando el numero $numero");
  }
  muestraNumero(5);

  // 2. Función sin nombre dentro de una función de orden superior (como forEach)
  print(
    "\n2. Función sin nombre dentro de una función de orden superior (como forEach)",
  );
  List diasSemana = List.empty(growable: true);
  diasSemana.add("Lunes");
  diasSemana.add("Martes");
  diasSemana.add("Miercoles");
  diasSemana.add("Jueves");
  diasSemana.add("Viernes");

  for (var dia in diasSemana) {
    String diaLower = dia.toLowerCase();
    print("Dia de la semana: $diaLower");
  }

  // 3. Función con la sintaxis de flecha (=>) para funciones de una sola línea
  print(
    "\n3. Función con la sintaxis de flecha (=>) para funciones de una sola línea",
  );
  List diasSemanaOrdenados = List.of(diasSemana);
  diasSemanaOrdenados.sort();
  for (var dia in diasSemanaOrdenados) {
    print(
      "Dia ${((diasSemanaOrdenados.indexOf(dia)) + 1)} de la semana ordenada por nombre del dia: $dia",
    );
  }

  // 4. Función como argumento de otra función
  print("\n4. Función como argumento de otra función");
  int suma(int sumando1, int sumando2) {
    return sumando1 + sumando2;
  }

  print("La suma de 3+4+9 es: ${suma(3, suma(4, 9))}");

  // 5. Función dentro de un map
  print("\n5. Función dentro de un map");
  var numeros = [1, 2, 3, 4];
  Map duplicados = numeros.map((numero) => numero * 2).toList().asMap();
  print(numeros);
  print(duplicados);
}
