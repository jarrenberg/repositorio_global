"""
Ejercicio 11 — Contador de dígitos pares e impares
Escribe una función contar_pares_impares(n: int) -> tuple[int, int] que recorra los dígitos de
un número
y devuelva cuántos son pares y cuántos impares.
Ejemplo: n = 4827 → (2 pares, 2 impares)
Pista: usa // y % para extraer dígitos. Convierte temporalmente a positivo si es negativo.
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

def contar_pares_impares(n: int) -> tuple[int, int]:
    if(n<0):
        n=n*(-1)
    i=1
    num_pares=0
    num_impares=0
    res=n
    while(res//(10)!=0):
        if((res%(10))%2==0):
            num_pares=num_pares+1
        else:
            num_impares=num_impares+1
        res=res//(10)
        i=i+1
    if(res%2==0):
        num_pares=num_pares+1
    else:
        num_impares=num_impares+1
    return [num_pares,num_impares]
    

def main():
    n = pedir_entero("Introduzca un numero entero: ")
    resultado=contar_pares_impares(n)
    print(f"Hay {resultado[0]} numero pares y {resultado[1]} numeros impares")
main()