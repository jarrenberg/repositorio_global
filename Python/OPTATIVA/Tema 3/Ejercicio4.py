#4. Escribe un programa que recoja un número y muestre un triángulo. Por
#ejemplo, si se ha introducido el valor 5, se debe mostrar:

#*
#**
#***
#****
#*****

n = int(input("Introduzca el la base de la piramide:"))
contador = int(1)

while contador<=n:
    print("*"*contador)
    contador=contador+1
