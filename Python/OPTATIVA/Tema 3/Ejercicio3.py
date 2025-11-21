#3. Escribe un programa que recoja números por teclado hasta que se introduzca
#el valor cero. A continuación, debe mostrar el número de valores introducidos,
#el valor mínimo introducido, el máximo, la suma de todos ellos y su media
#aritmética (todos los cálculos sin contar el cero)
condicion = True
lista = []
while condicion==True:
    n = int(input("Introduzca un numero(0 para salir):"))
    if n!=0:
        lista.append(n)
    else:
        condicion=False

print("La lista es:",lista)
print("El numero maximo introducido es:",max(lista))
print("El numero minimo introducido es:",min(lista))
print("La suma de los numeros introducidos es:",sum(lista))
print("La media es:",sum(lista)/len(lista))


