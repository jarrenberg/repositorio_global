#6. Escribe un programa que recoja las notas de las tres evaluaciones de un
#alumno. A continuación debe calcular y mostrar la nota final, teniendo en
#cuenta que la primera evaluación cuenta un 20% de la nota final, la segunda
#evaluación un 35% y la tercera evaluación un 45%.
nota1 = float(input("Introduzca la nota de la primera evaluacion:"))
nota2 = float(input("Introduzca la nota de la segunda evaluacion:"))
nota3 = float(input("Introduzca la nota de la tercera evaluacion:"))
notaFinal = nota1*.2 + nota2*.35 + nota3*.45
print("La nota final es ", notaFinal)