#2. Escribe un programa que recoja un número y calcule su factorial.
num=int(input("Introduzca un numero:"))
for n in range(1,num):
    num=num*n
print(num)