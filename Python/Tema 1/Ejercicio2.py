#2. Escribe un programa que recoja dos números enteros por teclado y muestre
#los siguientes resultados: suma, resta, multiplicación, división real, división
#entera, resto de la división entera y potencia.

num1 = int(input("Introduce un entero por teclado: "))
num2 = int(input("Introduce otro entero por teclado: "))
print("Mostrando la suma de los dos numeros introducidos: " + str((num1+num2)))
print("Mostrando la resta de los dos numeros introducidos: " + str((num1-num2)))
print("Mostrando la multiplicacion de los dos numeros introducidos: " + str((num1*num2)))
print("Mostrando la division real de los dos numeros introducidos: " + str((num1/num2)))
print("Mostrando la division entera de los dos numeros introducidos: " + str((num1//num2)))
print("Mostrando la resto de la division entera de los dos numeros introducidos: " + str((num1%num2)))
print("Mostrando la potencia de los dos numeros introducidos: " + str(pow(num1,num2)))