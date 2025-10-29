/*Ejercicios de Dart - Programación multimedia y de dispositivos móviles - IES Playamar
RELACIÓN DE EJERCICIOS DE DART (III)
EJERCICIOS CON FUNCIONES - 2ª PARTE
Para la resolución de estos ejercicios, seguiremos la documentación oficial
1- Vamos a resolver por fin, aunque de forma no satisfactoria estéticamente, el problema de
introducir valores por entrada estándar y validarlos antes de que propaguen un error en
tiempo de ejecución. Con valores numéricos, será fácil, con los String, algo más complejo.
Volveremos a versionar el ejercicio 6 de la relación (II) que calculaba la suma de los n
primeros números naturales, pero esta vez, el valor de n será solicitado por consola.
Para ello deberás importar la librería dart:io e invocar a las funciones:
● stdout.write("Introduce un número ") → devuelve void
● stdin.readLineSync() → devuelve un string captado por la entrada estándar
● int.parse() → convierte un String en número entero (se puede utilizar también con
double y bool)
También deberás ajustar el entorno de ejecución dentro de VSCode de la siguiente manera:
Hasta ahora mismo, la consola que muestra los resultados es la de Depuración
(Debug) que no se adapta exactamente al comportamiento de stdin.
Puedes cambiar esto yendo Extensiones -> Dart. pulsa la ruedecita de settings que
aparece bajo el título, y después pulsar Settings:
Si te desplazas hacia abajo, en el apartado Run & Debug encontrarás "Dart: Cli
Console". Debes ponerla a “terminal”. Finalmente, reinicia VSCode
Todo funciona correctamente si se introduce un número tal y como se espera, pero
¿qué ocurre si no es así? ¿qué pasa si se introduce algo no convertible a entero?¿o un
intro? Soluciona este problema con try-catch.
Ahora puedes retomar los ejercicios de las relaciones anteriores y experimentar con
distintos tipos de datos de entrada. Propongo rehacer el ejercicio de cálculo de factorial
(en alguna de sus versiones), y/o el de cálculo de las raíces de una ecuación de 2º
grado a partir de los coeficientes a, b y c (de tipo double)*/
import 'dart:io';

int devolverEnteroValido(String mensaje) {
  bool numValido = false;
  int n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = int.parse(stdin.readLineSync()!);
      numValido=true;
    } catch (ex) {
      print(ex.toString());
    }
  }
  return n;
}

double devolverDoubleValido(String mensaje) {
  bool numValido = false;
  double n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = double.parse(stdin.readLineSync()!);
      numValido=true;
    } catch (ex) {
      print(ex.toString());
    }
  }
  return n;
}

void main() {
  int n;
  do {
    n = devolverEnteroValido(
      "Introduzca un entero positivo para calcular su factorial: ",
    );
  } while (n < 0);

  print("El factorial de $n es ${calcularFactorial(n)}");
}

int calcularFactorial(int n) {
  int res = 1;
  if (n > 1) {
    for (int i = 1; i <= n; i++) {
      res = res * i;
    }
  }
  return res;
}
