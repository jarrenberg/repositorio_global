import 'dart:io';

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

int tempsLimite(List lista) {
  return lista.reduce((tempMax, temp) => temp > tempMax ? temp : tempMax);
}
