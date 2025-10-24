/*16- Declara en Dart un lista con los días laborables en Español y muéstrala. Añádele los
dos días del fin de semana. Recorre la lista día por día y muéstralos por pantalla. Haz lo
mismo con un Map que contenga una serie de nombres y edades. En estos dos últimos
casos, usa forEach
*/

import 'dart:io';

void main(){
List<String> laborables = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes'];

print("Los dias laborables son:");

for(int i =0; i<laborables.length;i++){
  stdout.write("${laborables[i]} ");
}
print("");
print("Añadiendo el Sabado y el Domingo ...");
laborables.add("Sabado");
laborables.add("Domingo");

print("Mostrando los dias de la semana ...");
for(int i =0; i<laborables.length;i++){
  stdout.write("${laborables[i]} ");
}
stdout.write("\n\n");


Map<String,int>personas=Map.new();
print("Añadiendo alumnos a la clase ...");
personas.addEntries([MapEntry("Jorge", 23),MapEntry("Antonio", 24),MapEntry("Raquel", 25),MapEntry("Eliana",19)]);
personas.forEach((nombre, edad) => stdout.write("$nombre, $edad años\n"));

}

