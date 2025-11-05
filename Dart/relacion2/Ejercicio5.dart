/*5- Repitamos el ejercicio de la hora actual y el desplazamiento horario como parámetro
entero normal, pero esta vez, la función devolverá la fecha y hora al punto de llamada.*/
void main() {
  // Guardamos la fecha actual en una variable y la mostramos formateada
  DateTime fechaMod = fechaModificadaPorParametro(5);
  print(
    "La fecha modificada es: ${fechaMod.day}-${fechaMod.month}-${fechaMod.year} ${fechaMod.hour}:${fechaMod.minute}",
  );
}

// Metodo que devuelve la fecha actual modificada el numero de horas introducidas por parametros
DateTime fechaModificadaPorParametro(int horas) {
  return DateTime.now().add(Duration(hours: horas));
}
