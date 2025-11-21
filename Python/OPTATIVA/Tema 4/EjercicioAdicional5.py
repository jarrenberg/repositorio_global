"""
Ejercicio 5 — Números primos
Escribe una función es_primo(n: int) -> bool que determine si n es primo.
Luego, pide un entero N (>1) y muestra todos los números primos hasta N inclusive en una
sola línea.
Optimiza es_primo para que solo pruebe divisores hasta la raíz cuadrada de n.
"""
from math import sqrt

def es_primo(n: int) -> bool:
     primo=True
     i=2
     while (primo==True) and (i<=(sqrt(n))):
          if(n%i==0):
               primo=False
          i=i+1
     return primo
def primos_hasta_n (n:int) -> list[int]:
     resultado = []
     for i in range (2,n+1):
          if(es_primo(i)==True):
               resultado.append(i)
     return resultado

def main ():
     try:
          num = 0
          while num<=1:
               num = int(input("Introduzca un numero entero mayor a 1: "))
          if es_primo(num)==True:
               print(f"El numero introducido {num} es primo")
          else:
               print(f"El numero introducido {num} no es primo")
          print(f"Lista de los numeros primos hasta {num}: {primos_hasta_n(num)}")
     except ValueError:
          print("Se ha introducido un valor distinto a un entero mayor que 1")
main()