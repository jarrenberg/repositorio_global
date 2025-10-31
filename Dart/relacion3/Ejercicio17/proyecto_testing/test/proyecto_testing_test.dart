import 'package:test/test.dart';
import 'package:proyecto_testing/libreriaRegEx/lib_reg_ex/lib/lib_reg_ex.dart';

void main() {
test('Prueba DNI valido', (){
  expect(letraDNIValida("77773198D"), true);
});
test('Prueba DNI invalido', (){
  expect(letraDNIValida("77773198F"), false);
});
}
