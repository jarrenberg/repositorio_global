/*18- Haz un programa Dart que declare una lista, y la inicializa con los valores devueltos por
una función que devuelva los números de la serie de Fibonacci hasta llegar al término
n-ésimo, siendo n un valor entero declarado e inicializado al principio del programa*/
import 'dart:io';

List devolverLista(int num) {
  List resultado = serieFibonacci(num);
  return resultado;
}
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

void main() {
  int n = 0;
  while(n<=0){
    n = devolverEnteroValido("Introduzca un numero enero positivo: ");
  }
  List numeros = devolverLista(n);
  print("La lista de los $n primeros numeros de fibonacci es: $numeros");
}

List<int> serieFibonacci(int limite) {
  List<int> resultado = [];
  
  int a = 0;
  int b = 1;
  
  for (int i = 0; i < limite; i++) {
    resultado.add(a);
    int siguiente = a + b;
    a = b;
    b = siguiente;
  }
  
  return resultado;
}