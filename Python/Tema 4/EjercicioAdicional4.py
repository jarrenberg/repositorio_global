"""
Ejercicio 4 — Máximo hasta 'fin'
Crea una función leer_enteros_hasta_fin() -> list[int] que lea enteros desde teclado hasta
que el usuario
introduzca 'fin'. Después, muestra el máximo y el mínimo de la lista con otra función
calcular_extremos(numeros: list[int]) -> tuple[int, int]. Si la lista queda vacía, indica que no
hay datos.
"""
def leer_enteros_hasta_fin() -> list[int]:
     fin = False
     resultado = []
     while(fin==False):
          n = input("Introduzca un entero o 'fin': ")
          if(n=="fin"):
               fin=True
               return resultado
          else:
               try:
                    n_entero = int(n)
                    resultado.append(n_entero)
               except ValueError:
                    print("No se ha introducido un numero entero")

def calcular_extremos (numeros:list[int])->tuple[int,int]:
     if len(numeros)==0:
          print("La lista introducida está vacía")
     else:
          numeros.sort()
          print(numeros)
          min = numeros[0]
          max = numeros[-1]
          return (max,min)
     
def main ():
     lista = leer_enteros_hasta_fin()
     tupla = calcular_extremos(lista)
     if(tupla is not None):
          print(f"El numero maximo introducido es {tupla[0]} y el minimo {tupla[1]}")

main()