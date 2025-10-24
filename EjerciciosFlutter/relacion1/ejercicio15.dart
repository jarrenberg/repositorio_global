/*15- Versiona el programa anterior para que simule el lanzamiento de un dado un número n
de veces (n>100) y muestre la frecuencia con la que se ha obtenido cada resultado.*/

import 'dart:io';
import 'dart:math';
void main(){
    int numCaras = 6;
    int numLanzamientos=0;
    while(numLanzamientos<=100){
      numLanzamientos=devolverEnteroValido("Introduzca el numero de lanzamientos (100 minimo) de un dado de 6 caras:");
    }
    int veces1=0,veces2=0,veces3=0,veces4=0,veces5=0,veces6=0;
    int contador=0;
    while(contador<numLanzamientos){
    int valor = Random().nextInt(numCaras)+1;
    switch (valor){
      case 1:
        veces1++;
        break;
      case 2:
        veces2++;
        break;
      case 3:
        veces3++;
        break;
      case 4:
        veces4++;
        break;
      case 5:
        veces5++;
        break;
      case 6:
        veces6++;
        break;
    }
    contador++;
    }
    print(contador);
    print("Numero de veces que ha salido 1: $veces1");
    print("Numero de veces que ha salido 2: $veces2");
    print("Numero de veces que ha salido 3: $veces3");
    print("Numero de veces que ha salido 4: $veces4");
    print("Numero de veces que ha salido 5: $veces5");
    print("Numero de veces que ha salido 6: $veces6");
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