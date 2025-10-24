#10.Escribe un programa que recoja un número y muestre un triángulo formado por
#secuencias decrecientes de números impares. Por ejemplo, si se introduce el
#5 se debe mostrar:
# 1
# 3 1
# 5 3 1
# 7 5 3 1
# 9 7 5 3 1

impar=False
num = 0
while impar == False:
    num = int(input("Introduzca el tamaño de la base de la piramide (Este texto se repetira mientras el numero introducido no sea impar):"))
    if(int(num%2)==1):
        impar = True

contadorLineas=num
valorInicio=1

for i in range (1,num*2,2):
   print(" ")
   j = 0
   valorInicio=i
   while j < (int(i/2)+1):
       print(valorInicio,end=" ")
       valorInicio-=2
       j+=1
