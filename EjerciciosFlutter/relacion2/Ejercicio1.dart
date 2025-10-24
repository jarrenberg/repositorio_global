/*1- Vamos a declarar en un programa Dart una función sin parámetros ni valores devueltos,
que únicamente muestre por pantalla la fecha y la hora actuales (DateTime.now() ).*/
void main() {
  DateTime fecha = fechaActual();
  print("La fecha actual es: ${fecha.day}-${fecha.month}-${fecha.year} ${fecha.hour}:${fecha.minute}");
}

DateTime fechaActual(){
  return DateTime.now();
}