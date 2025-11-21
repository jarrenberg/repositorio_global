"""
Ejercicio 8 — Tabla de multiplicar extendida
Pide dos enteros a y b (a ≤ b). Muestra, mediante una función imprimir_tablas(a, b),
las tablas de multiplicar desde a hasta b, cada una de 1 a 10. Formatea con columnas
alineadas.
"""
def pedir_entero(mensaje:str)->int:
     valido=False
     while valido==False:
          try:
               entero = int(input(mensaje))
               valido=True
          except ValueError: 
               print("No se ha introducido entero valido")
     return entero

def imprimir_tablas (a:int,b:int):
    for i in range (a,b+1):
            print(f"Tabla del {i}")
            for j in range (1,11):
                print(f"{i}x{j}={i*j}")
            print()

def main():
        a = pedir_entero("Intoduzca un entero (a): ")
        b = pedir_entero("Introduzca otro entero (b): ")
        while(a>b):
            b = pedir_entero("El segundo entero (b)  no puede ser menor que el primero (a), vuelva a introducir el segundo numero: ")
        imprimir_tablas(a,b)

main()