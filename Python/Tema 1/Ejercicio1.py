# 1. Escriba un programa que recoja un valor por teclado y muestre de qué tipo
# es.
contador=0
while contador<5:
    teclado = input("Introduce un valor por teclado: ")
    print("Mostrando el tipo del valor introducido: " + str(type(teclado)) )
    contador=contador+1