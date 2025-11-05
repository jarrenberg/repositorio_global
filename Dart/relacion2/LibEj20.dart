import 'dart:io';

// Metodo que recibe como parametro un mensaje para mostrar al usuario y devuelve un entero valido
int devolverEnteroValido(String mensaje) {
  bool numValido = false;
  int n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = int.parse(stdin.readLineSync()!);
      return n;
    } catch (ex) {
      print(ex.toString());
    }
  }
}

// Metodo que devuelve la temperatura maxima de la lista pasada como parametro
int tempsLimite(List lista) {
  return lista.reduce((tempMax, temp) => temp > tempMax ? temp : tempMax);
}
