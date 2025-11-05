/*1- Vamos a declarar en un programa Dart una función sin parámetros ni valores devueltos,
que únicamente muestre por pantalla la fecha y la hora actuales (DateTime.now() ).*/
void main() {
  // Guardamos la fecha actual en una variable y la mostramos formateada
  DateTime fecha = fechaActual();
  print(
    "La fecha actual es: ${fecha.day}-${fecha.month}-${fecha.year} ${fecha.hour}:${fecha.minute}",
  );
}

// Metodo que devuelve la la fecha actual
DateTime fechaActual() {
  return DateTime.now();
}
