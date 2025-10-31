void main(List<String> args) {
  var jorge = Persona("Jorge Arrenberg", 23, 1.88);
  var antonio = Persona("Antonio Lopez", 50, 1.83);

  print(jorge.toString());
  print(antonio.toString());

  jorge.personaDescripcion();
  antonio.personaDescripcion();
}

class Persona {
  String? nombre;
  int? edad;
  double? altura;

  Persona(String nombre, int edad, double altura) {
    if (edad < 0 || edad > 120) {
      throw ArgumentError("La edad no puede ser negativa o mayor a 120");
    }
    if (altura < 0.4 || altura > 2.8) {
      throw ArgumentError("La altura no puede ser negativa o mayor a 2.8m");
    }
    this.nombre = nombre;
    this.edad = edad;
    this.altura = altura;
  }

  @override
  String toString() {
    return "Nombre: ${this.nombre}\tEdad: ${this.edad}\tAltura: ${this.altura}";
  }

  void personaDescripcion() {
    print(
      "Mi nombre es ${this.nombre}. Tengo ${this.edad} años,tengo ${this.altura} metros de altura.",
    );
  }
}
