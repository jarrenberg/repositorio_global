
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

  double getX() {
    return _x;
  }

  double getY() {
    return _y;
  }

  double getZ() {
    return _z;
  }

  @override
  String toString() {
    return "($_x, $_y, $_z)";
  }
}