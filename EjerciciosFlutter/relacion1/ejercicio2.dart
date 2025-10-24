/*2- Busca en la documentación oficial de Dart cuales son los tipos de datos Built-in y
experimenta con ellos.

Declara e inicializa una variable de cada uno de estos tipos: int, double, String, bool,
dynamic. */
void main(){
// Numeros
int entero = 1;
print("$entero");

double decimal = 1.5;
print("$decimal");

// Cadenas de caracteres
String cadena = "Hola Pilar, soy Jorge";
print("$cadena");

// Booleans
bool feliz = true;
print("$feliz");

// Records
// ignore: unused_local_variable
Record rec = ((1,2),(3,4));

// Functions
Function suma = (int a, int b) => a + b;
var resultado = suma(1,3);
print("$resultado");

/*Añade tipos más complejos como List, Map, Set, record al repaso anterior
Investiga qué operadores se pueden utilizar con cada uno de ellos y cómo se comporta la
función print, así como las opciones de parseo */

// Lists
List<int> numeros = [1,2,1,2,3];
numeros.add(3); //Añade el parametro;
numeros.indexOf(1); //Devuleve el valor en el que se encuentra el parametro
print("$numeros");


// Set (no se pueden repetir elementos)
Set<String> aula = {'Pilar','Antonio','Jorge'};
aula.toList(); // Convierte el conjunto a una lista
print("$aula");

// Maps
Map<String,int> persona;
persona = {"Juan":entero,"Marco":3};
print("$persona");

// Null
// ignore: unused_local_variable
Null nulo;
}