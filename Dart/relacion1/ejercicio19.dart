/*19- Haz un programa Dart que declare un Map con nombres y edades, y lo recorra
mostrando todo (clave-valor), que lo recorra mostrando solo las claves, y también, aparte,
mostrando solo los valores. ¿Qué pasaría si sabemos que alguna de las edades no están
inicializadas?*/
void main() {
  Map<String,int?> personas = new Map();
personas.addEntries([MapEntry("Jorge", 23),MapEntry("Raquel", 25),MapEntry("Antonio", 24),MapEntry("Eliana",null)]);

print("Mostrando toda la informacion de los alumnos de la clase ...");
personas.forEach((nombre,edad)=>print("$nombre, $edad años"));

print("Mostrando los nombres los alumnos de la clase ...");
personas.forEach((nombre,edad)=>print("$nombre"));

print("Mostrando los edades de los alumnos de la clase ...");
personas.forEach((nombre,edad)=>print("$edad"));

// En el caso de que algun valor no este inicializado, como es el caso de Eliana mostrara null

}
