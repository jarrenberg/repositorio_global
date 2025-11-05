/*7- Haz el programa que resuelva las raíces de una ecuación de segundo grado (ax
2 + bx + c
= 0) a partir de los valores de los coeficientes a, b y c (double). Si las raíces no fueran
reales, debe mostrar un mensaje que así lo indique.*/
import 'dart:io';
import 'dart:math';

void main() {
  double a = 0;
  // Pedimos al usuario que introduzca los terminos de la ecuacion, verificando que mientras el primer termino sea 0 no se guarde
  // Una vez tenemos un numero valido lo convertimos a double
  while (a == 0) {
    stdout.write(
      'Por favor, introduzca el coeficiente del termino elevado al cuadrado:\n',
    );
    a = double.parse(stdin.readLineSync()!);
  }
  stdout.write(
    'Por favor, introduzca el coeficiente del termino elevado elevado a uno:\n',
  );
  double? b = double.parse(stdin.readLineSync()!);
  stdout.write(
    'Por favor, introduzca el coeficiente del termino elevado a cero:\n',
  );
  double? c = double.parse(stdin.readLineSync()!);

  // Calculamos las dos soluciones de la ecuacion
  num r1 = (-b + sqrt((b * b) - (4 * a * c))) / (2 * a);
  num r2 = (-b - sqrt((b * b) - (4 * a * c))) / (2 * a);

  // Interpretamos los resultados de la ecuacion y los mostramos
  if (r1.isNaN && r2.isNaN) {
    print("No existen relaciones reales a la ecuacion");
  } else if (r1.isNaN) {
    print("El resultado de la ecuacion introducida es $r2");
  } else if (r2.isNaN) {
    print("El resultado de la ecuacion introducida es $r1");
  } else if (r1 == r2) {
    print("El resultado de la ecuacion introducida es $r1");
  } else {
    print("Los resulados de la ecuacion introducida son $r2 y $r1");
  }
}
