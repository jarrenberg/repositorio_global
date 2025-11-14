/*5- Define una clase Vector utilizando para ello la clase Punto:
● un vector queda definido por dos puntos: inicio y fin
● debe poder trasladarse en vertical, horizontal y en la cota (trasladando cada uno de
sus puntos)
● debe poder calcularse el producto de un escalar por un vector
● debe poderse sumar con otro vector
● debe poderse calcular el producto vectorial (solo si tienes formación matemática)*/


import 'Ejercicio4.dart';

class Vector {
  late Punto _inicio;
  late Punto _fin;
  late double _x;
  late double _y;
  late double _z;
  Vector(Punto inicio, Punto fin) {
    this._inicio = inicio;
    this._fin = fin;
    this._x = _getXVector(inicio, fin);
    this._y = _getYVector(inicio, fin);
    this._z = _getZVector(inicio, fin);
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
    this._x = _getXVector(this._inicio, this._fin);
    this._y = _getYVector(this._inicio, this._fin);
    this._z = _getZVector(this._inicio, this._fin);
  }

  void trasladarVectorVerticalmente(double distancia) {
    _inicio.traslacionVertical(distancia);
    _fin.traslacionVertical(distancia);
    this._x = _getXVector(this._inicio, this._fin);
    this._y = _getYVector(this._inicio, this._fin);
    this._z = _getZVector(this._inicio, this._fin);
  }

  void trasladarVectorCota(double distancia) {
    _inicio.traslacionCota(distancia);
    _fin.traslacionCota(distancia);
    this._x = _getXVector(this._inicio, this._fin);
    this._y = _getYVector(this._inicio, this._fin);
    this._z = _getZVector(this._inicio, this._fin);
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
    double? producto = null;
    producto =
        (this._x * v.getXVector()) +
        (this._y * v.getYVector()) +
        (this._z * v.getZVector());
    return producto;
  }

  List<double>? sumaVectorial(Vector v) {
    List<double>? resultado = List.empty(growable: true);
    resultado.add(this._x + v.getXVector());
    resultado.add(this._y + v.getYVector());
    resultado.add(this._z + v.getZVector());
    return resultado;
  }

  
}
