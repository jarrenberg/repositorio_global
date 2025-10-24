/*3- Declara la constante pi en Dart. Utiliza para ello la palabra reservada const.
Declara también otra constante llamada diasDelAnyo que pueda contener 365 o 366 días
dependiendo de si el año es bisiesto o no. Deberás utilizar para ésta final. Prueba hacerlo
con const ¿qué ocurre?
*/

import 'dart:math';

const PI_JORGE = pi;

final diasDelAnyo = (diasDelAnyo%4==0)?366:365;

// Si lo intento con cons me da error