# 3. Escribe un programa que lea tres números y que muestre los números mayor
# y menor.
numero1=int(input("Introduzca un numero:"))
numeroMenor=numero1
numeroMayor=numero1
numero2=int(input("Introduzca otro numero:"))
if numero2>numeroMayor:
    numeroMayor=numero2
if numero2<numeroMenor:
    numeroMenor=numero2
numero3=int(input("Introduzca otro numero:"))
if numero3>numeroMayor:
    numeroMayor=numero3
if numero3<numeroMenor:
    numeroMenor=numero3
print("El numero menor es",numeroMenor,"y el numero mayor es",numeroMayor)