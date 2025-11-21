"""
Ejercicio 7 — Figuras con asteriscos
Escribe funciones para dibujar: (a) un triángulo rectángulo de altura h, (b) una escalera de h
peldaños,
(c) un rectángulo de ancho w y alto h. Pide las dimensiones y valida que sean enteros
positivos.
Ejemplo de triángulo h=4:
*
**
***
****
"""
import string


def menu ():
     print("Menu de dibujo:")
     print("a. Triangulo de altura n")
     print("b. Escalera de n peldaños")
     print("c. Rectangulo de ancho n y alto m")
     print("d. Salir")

def triangulo (h:int):
     espacios=int((h/2)+1)
     numAsteriscos=1
     for i in range (int((h/2)+1)):
          for j in range (espacios):
               print(" ",end="")
          espacios-=1
          print("*"*numAsteriscos)
          numAsteriscos+=2

def escalera (h:int):
     for i in range (1,h+1):
          print("*"*i,end="")
          print()
          
def rectangulo(h: int, b: int):
    for i in range(0, h):
        for j in range(0, b):
            print("*", end=" ")
        print()

def pedir_entero(mensaje:str)->int:
     valido=False
     while valido==False:
          try:
               entero = int(input(mensaje))
               valido=True
          except ValueError: 
               print("No se ha introducido entero valido")
     return entero

def main ():
     valida=False
     while(valida==False):
          menu()
          try:
               accion = input("Introduzca una accion a realizar: ").lower()
               match accion:
                    case n if accion == "a":
                         altura = 2
                         while(altura%2==0):
                              altura=pedir_entero("Introduzca la altura del triangulo: ")
                         triangulo(altura)
                         
                    case n if accion == "b":
                         peldanyos = pedir_entero("Introduzca el numero de pelaños de la escalera: ")
                         escalera(peldanyos)

                    case n if accion == "c":
                         base = pedir_entero("Introduzca la base del rectangulo: ")
                         altura = pedir_entero("Introduzca la altura del rectangulo: ")
                         rectangulo(altura,base)
                           
                    case n if accion == "d":
                         valida=True
                    case _:
                         print("Se ha introducido un parametro no valido en el menu")
                         
                         
          except:
               print("No se ha introducido una accion valida")

main()