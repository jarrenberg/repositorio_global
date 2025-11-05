/*3- Vamos a hacer un pequeño programa Dart con una función que muestre el nombre y el id
de un empleado que se les pasa como parámetros, pero sabiendo que, tanto uno como otro
podrían no saberse, en cuyo caso, se mostrará el nombre ‘desconocido’ y/o el id 999, que
serán una suerte de valores por defecto. (Función con parámetros opcionales, y sin
devolución de valor de retorno)*/

void main() {
  // Mostramos los saludos a empleados con distintos nombres e id
  print(saludoEmpleado("Jorge", 34));
  print(saludoEmpleado(null, null));
  print(saludoEmpleado("Jorge", null));
  print(saludoEmpleado(null, 34));
}

// Metodo que devuelve un saludo cuyo nombre e id se pasan como parametros y pueden ser nulos
String saludoEmpleado(String? nombre, int? id) {
  String? nombreFinal = nombre;
  int? idFinal = id;
  if (id == null) {
    idFinal = 999;
  }
  if (nombre == null) {
    nombreFinal = "desconocido";
  }
  return "Hola empleado $nombreFinal con id $idFinal";
}
