/*6- Haz un programa en el que, a partir de dos variables enteras, y un String introducidos
por consola que represente un operador, realice solo una de entre las operaciones: suma,
resta, multiplicación, división entera, resto de la división entera. La rama que se ejecute,
dependerá del operador en sí, funcionará como una rudimentaria calculadora*/

import 'dart:io';

void main(){
  stdout.write('Por favor, introduzca un entero:\n');
  int? n1 = int.parse(stdin.readLineSync()!);

  stdout.write('Por favor, introduzca otro entero:\n');
  int? n2 = int.parse(stdin.readLineSync()!);

  stdout.write('Por favor, introduzca un operando:\n');
  String? operando = stdin.readLineSync()!;

  int? resultado;

  switch(operando){
    case '+':
    resultado = n1+n2;
    print("El resultado es: $resultado");
    break;
    case '-':
    resultado = n1-n2;
    print("El resultado es: $resultado");
    break;
    case '*':
    resultado = n1*n2;
    print("El resultado es: $resultado");
    break;
    case '~/':
    if(n2!=0){
      resultado=(n1~/n2);
      print("El resultado es: $resultado");
    }else{
      print("El resultado es invalido, ya que no se puede dividir entre 0");
    }
    break;
    case '%':
    resultado = (n1%n2);
     print("El resultado es: $resultado");
    break;
    default:
    print("El operando introducido (${operando}) no es correcto.");
  }


}