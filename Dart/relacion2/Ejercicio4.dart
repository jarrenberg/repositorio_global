/*4- Hagamos esta vez un programa con una función que muestre el nombre de un empleado
que se le pasa como parámetro y los apellidos, pero esta vez, puede que no se pase
apellido, en cuyo caso se considerará null (nullable parameter), cosa que deberá procesar
la función adecuadamente. (Función con parámetros posicionales y sin devolución de valor
de retorno)*/
String nombreApellidos(String nombre, String? apellidos) {
  String resultado;
  if (apellidos == Null) {
    resultado = nombre + " {apellidos desconocidos}";
  } else {
    resultado = nombre + " " + apellidos!;
  }
  return resultado;
}

void main() {
  print("El nombre del empleado es ${nombreApellidos("Juan", "Benitez")}");
}
