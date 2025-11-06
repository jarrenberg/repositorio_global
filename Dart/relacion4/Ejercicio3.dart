class Bandera {
  late bool _esHorizontal;
  late List<String> _colores;
  late String? _propietario;

  Bandera(bool esHorizontal, List<String> colores, String? propietario) {
    _esHorizontal = esHorizontal;
    _colores = colores;
    _propietario = propietario;
  }

  bool getHorizontalidad() {
    return _esHorizontal;
  }

  List<String> getListaColores() {
    return _colores;
  }

  String? getPropietario() {
    return _propietario;
  }

  void setListaColores(List<String> colores) {
    _colores = colores;
  }

  @override
  String toString() {
    return "Es horizontal?: ${_esHorizontal == true ? "Si" : "No"}, Lista de colores: $_colores, Propietario: ${_propietario ?? "No tiene propietario"}";
  }

  void revertirOrientacion() {
    _esHorizontal == true ? _esHorizontal = false : _esHorizontal = true;
  }

  void revertirOrdenColores() {
    setListaColores(_colores.reversed.toList());
  }

  Map<String, bool> banderaToMap() {
    Map<String, bool> resultado = {};
    for (int i = 0; i < this._colores.length; i++) {
      resultado.addEntries([
        MapEntry(this._colores.elementAt(i), this._esHorizontal),
      ]);
    }
    return resultado;
  }

  bool mismaBanderaDiferenteOrientacion(Bandera bandera2) {
    bool misma = false;
    Map<String, bool> mapaBandera1 = this.banderaToMap();
    Map<String, bool> mapaBandera2 = bandera2.banderaToMap();
    List<bool> listaOrientacion1 = mapaBandera1.values.toList();
    List<bool> listaOrientacion2 = mapaBandera2.values.toList();
    List<String> listaColores1 = mapaBandera1.keys.toList();
    List<String> listaColores2 = mapaBandera2.keys.toList();

    if ((listaColores1.toString() == listaColores2.toString()) &&
        (listaOrientacion1.toString() != listaOrientacion2.toString())) {
      misma = true;
    }
    return misma;
  }
}

void main(List<String> args) {
  print("Creando la bandera de Francia ...");
  var francia = Bandera(false, ["Azul", "Blanco", "Rojo"], "Francia");
  print(francia.toString());
  print("\nRevirtiendo la orientacion y los colores de la bandera ...");
  francia.revertirOrientacion();
  francia.revertirOrdenColores();
  print(francia.toString());
  print("\nCreando la bandera del orgullo ...");
  var orgullo = Bandera(true, [
    "Rojo",
    "Naranja",
    "Amarillo",
    "Verde",
    "Azul",
    "Morado",
  ], null);
  print(orgullo.toString());
  print(
    "\nComparando si la bandera del orgullo es igual que la de Francia... ",
  );

  if (mapEquals(orgullo.banderaToMap(), francia.banderaToMap())) {
    print("Las banderas son identicas");
  } else {
    print("Las banderas son diferentes");
  }

  print("\nCreando la bandera de Holanda ...");
  var holanda = Bandera(true, ["Azul", "Blanco", "Rojo"], "Holanda");
  print(holanda.toString());

  print(
    "\nComprobando si la bandera de Holanda y Francia son la misma pero con las franjas en distinta direccion:",
  );
  if (francia.mismaBanderaDiferenteOrientacion(holanda) == true) {
    print("Son la misma bandera con distinta direccion");
  } else {
    print("No son la misma bandera con distinta direccion");
  }
}

bool mapEquals<T, U>(Map<T, U>? a, Map<T, U>? b) {
  if (a == null) {
    return b == null;
  }
  if (b == null || a.length != b.length) {
    return false;
  }
  if (identical(a, b)) {
    return true;
  }
  for (final T key in a.keys) {
    if (!b.containsKey(key) || b[key] != a[key]) {
      return false;
    }
  }
  return true;
}
