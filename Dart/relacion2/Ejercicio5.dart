/*5- Repitamos el ejercicio de la hora actual y el desplazamiento horario como parámetro
entero normal, pero esta vez, la función devolverá la fecha y hora al punto de llamada.*/
void main(){
    DateTime fechaMod  = fechaModificadaPorParametro(5);

    print("La fecha dentro de 5 horas es: ${fechaMod.day}-${fechaMod.month}-${fechaMod.year} ${fechaMod.hour}:${fechaMod.minute}");
}

DateTime fechaModificadaPorParametro(int horas) {
    return DateTime.now().add(Duration(hours: horas));
}