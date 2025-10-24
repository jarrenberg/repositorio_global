#7. Escribe un programa que recoja una cadena de texto por teclado y una letra a
#buscar. Luego debe buscar dicha letra por la cadena y al finalizar debe indicar
#el número de veces que se repite la letra en el texto.

cad = input("Introduzca una cadena de texto:")
char = input("Introduzca una letra:")
contador=0

for n in(cad):
    if n == char:
        contador+=1

print("La letra",char,"aparece",contador,"veces en la frase",cad)