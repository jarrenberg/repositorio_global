#4. Escribe un programa que recoja dividendo y divisor, y realice su división siempre que el divisor sea distinto de cero.
dividendo=int(input("Introduzca un dividendo:"))
divisor=int(input("Introduzca un divisor:"))
if divisor!=0:
    resultado = dividendo/divisor
    print("El resultado de dividir", dividendo,"entre",divisor,"es:",resultado)
else:
    print("El divisor es 0 y no se puede dividir")