/*20- Haz un programa Dart que realice estadísticas sobre el tiempo. Declararás un Map que
contenga como claves los días de la semana y como valores las temperaturas máximas.
Calcula y muestra la mayor y la menor de las máximas, así como los días en que se
produjeron. Calcula también la media aritmética de las máximas.*/
enum diasSemana { Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo }

void main() {
  Map<diasSemana, double> historico = Map();
  historico.addEntries([
    MapEntry(diasSemana.Lunes, 23.4),
    MapEntry(diasSemana.Martes, 24),
    MapEntry(diasSemana.Miercoles, 27.3),
    MapEntry(diasSemana.Jueves, 39.9),
    MapEntry(diasSemana.Viernes, 19.3),
    MapEntry(diasSemana.Sabado, 33.1),
    MapEntry(diasSemana.Domingo, 22),
  ]);
  
  MapEntry<diasSemana, double> diaMax = MapEntry(diasSemana.Domingo, 0.0);
  
  try {
    diaMax = encontrarMaxima(historico);
  } on ArgumentError {
    print("Error");
    return;
  }
  
  diasSemana d = diaMax.key;
  String dia = d.toString().split('.').last;
  print("La temperatura maxima es ${diaMax.value}ºC y fue el $dia");
}

MapEntry<diasSemana, double> encontrarMaxima(Map<diasSemana, double> mapa) {
  if (mapa.isEmpty) {
    throw ArgumentError("El mapa no puede estar vacío");
  }
  
  MapEntry<diasSemana, double> resultado = mapa.entries.first;
  double tempMaxima = double.negativeInfinity;
  mapa.forEach((dia, temp) {
    if (temp > tempMaxima) {
      tempMaxima = temp;
      resultado = MapEntry(dia, temp);
    }
  });
  return resultado;
}

/*Forma alternativa usando deepseek
MapEntry<diasSemana, double> encontrarMaxima(Map<diasSemana, double> mapa) {
  if (mapa.isEmpty) {
    throw ArgumentError("El mapa no puede estar vacío");
  }
  
  return mapa.entries.reduce((max, current) => 
    current.value > max.value ? current : max
  );
}
*/