#9. Escribe un programa que recoja un número impar. Debe asegurarse de que
#sea impar, en caso de no serlo debe descartarlo y pedirlo de nuevo. Una vez
#tenga el número impar debe mostrar una pirámide de asteriscos cuya base es
#igual al número introducido. Por ejemplo, si se introduce el valor 7 se debe
#mostrar:
#   *
#  ***
# *****
#******* <- base de la pirámide (7 asteriscos)
impar=False
base = 0
while impar == False:
    base = int(input("Introduzca el tamaño de la base de la piramide (Este texto se repetira mientras el numero introducido no sea impar):"))
    if(int(base%2)==1):
        impar = True

espacios=int((base/2)+1)
numAsteriscos=1

for i in range (int((base/2)+1)):
    for j in range (espacios):
        print(" ",end="")
    espacios-=1
    print("*"*numAsteriscos)
    numAsteriscos+=2
