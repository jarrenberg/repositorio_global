import 'punto.dart';

class Vector {
  late Punto _inicio;
  late Punto _fin;
  late double _x;
  late double _y;
  late double _z;
  Vector(Punto inicio, Punto fin) {
    _inicio = inicio;
    _fin = fin;
    _x = _getXVector(inicio, fin);
    _y = _getYVector(inicio, fin);
    _z = _getZVector(inicio, fin);
  }

  double _getXVector(Punto inicio, Punto fin) {
    return fin.getX() - inicio.getX();
  }

  double _getYVector(Punto inicio, Punto fin) {
    return fin.getY() - inicio.getY();
  }

  double _getZVector(Punto inicio, Punto fin) {
    return fin.getZ() - inicio.getZ();
  }

  void trasladarVectorHorizontalmente(double distancia) {
    _inicio.traslacionHorizontal(distancia);
    _fin.traslacionHorizontal(distancia);
    _x = _getXVector(_inicio, _fin);
    _y = _getYVector(_inicio, _fin);
    _z = _getZVector(_inicio, _fin);
  }

  void trasladarVectorVerticalmente(double distancia) {
    _inicio.traslacionVertical(distancia);
    _fin.traslacionVertical(distancia);
    _x = _getXVector(_inicio, _fin);
    _y = _getYVector(_inicio, _fin);
    _z = _getZVector(_inicio, _fin);
  }

  void trasladarVectorCota(double distancia) {
    _inicio.traslacionCota(distancia);
    _fin.traslacionCota(distancia);
    _x = _getXVector(_inicio, _fin);
    _y = _getYVector(_inicio, _fin);
    _z = _getZVector(_inicio, _fin);
  }

  Punto getInicio() {
    return _inicio;
  }

  Punto getFin() {
    return _fin;
  }

  double getXVector() {
    return _x;
  }

  double getYVector() {
    return _y;
  }

  double getZVector() {
    return _z;
  }

  double? productoEscalar(Vector v) {
    double? producto;
    producto =
        (_x * v.getXVector()) +
        (_y * v.getYVector()) +
        (_z * v.getZVector());
    return producto;
  }

  List<double>? sumaVectorial(Vector v) {
    List<double>? resultado = List.empty(growable: true);
    resultado.add(_x + v.getXVector());
    resultado.add(_y + v.getYVector());
    resultado.add(_z + v.getZVector());
    return resultado;
  }

  
}
