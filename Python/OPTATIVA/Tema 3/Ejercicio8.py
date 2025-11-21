#8. Escribe un programa que recoja un número y calcule si es primo.
num = int(input("Introduzca un numero para saber si es primo:"))
primo = True
i=2

while primo==True and i<num:
    if(num%i==0):
        primo=False
    i+=1
if(num==1):
    primo=False

if(primo == True):
    print("El numero introducido",num,"es primo")
else:
    print("El numero introducido",num,"no es primo")