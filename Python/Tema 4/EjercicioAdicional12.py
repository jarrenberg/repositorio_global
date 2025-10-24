"""
Ejercicio 12 — Repetición controlada de texto
Pide una palabra y un número entero n. Escribe una función repetir_palabra(palabra: str, n:
int) -> str
que devuelva la palabra repetida n veces separada por guiones.
Ejemplo: repetir_palabra('sol', 3) → 'sol-sol-sol'.
Pista: usa un bucle o multiplicación de cadenas y cuida el separador final.
"""

def repetir_palabra(palabra: str, n: int) -> str:
    resultado = ""
    i=0
    while (i<n):
        if(i!=(n-1)):
            resultado = resultado+palabra+"-"
        else:
            resultado = resultado+palabra
        i=i+1
    return resultado
def pedir_entero(mensaje:str)->int:
     valido=False
     while valido==False:
          try:
               entero = int(input(mensaje))
               valido=True
          except ValueError: 
               print("No se ha introducido entero valido")
     return entero

def main():
    repeticiones = pedir_entero("Introduzca el numero de repeticiones: ")
    palabra = input("Introduzca una palabra: ")
    print(repetir_palabra(palabra,repeticiones))
    

main()