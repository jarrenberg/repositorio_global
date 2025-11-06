import 'dart:io';

class Punto {
  late double _x;
  late double _y;
  late double _z;

  Punto(double x, double y, double z) {
    _x = x;
    _y = y;
    _z = z;
  }

  void traslacionHorizontal(double distancia) {
    _x = (_x + distancia);
  }

  void traslacionVertical(double distancia) {
    _y = (_y + distancia);
  }

  void traslacionCota(double distancia) {
    _z = (_z + distancia);
  }

  @override
  String toString() {
    return "(${_x}, ${_y}, ${_z})";
  }
}

double devolverDoubleValido(String mensaje) {
  bool numValido = false;
  double n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = double.parse(stdin.readLineSync()!);
      numValido = true;
    } catch (ex) {
      print(ex.toString());
    }
  }
  return n;
}

void main(List<String> args) {
  print("Creando un punto con las coordenadas (0,0,0) ...");
  Punto p = Punto(0, 0, 0);
  double distancia = devolverDoubleValido(
    "Introduzca la cantidad de distancia que quieres que se desplace el punto horizontalmente: ",
  );
  p.traslacionHorizontal(distancia);
  print(p.toString());

  distancia = devolverDoubleValido(
    "Introduzca la cantidad de distancia que quieres que se desplace el punto verticalmente: ",
  );
  p.traslacionVertical(distancia);
  print(p.toString());

  distancia = devolverDoubleValido(
    "Introduzca la cantidad de distancia que quieres que se desplace el punto en el eje Z: ",
  );
  p.traslacionCota(distancia);
  print(p.toString());
}
