"""
Ejercicio 14 — Conversión de segundos a formato h:m:s
Crea una función convertir_tiempo(segundos: int) -> str que convierta una cantidad de
segundos
en formato horas:minutos:segundos.
Ejemplo: 3665 → '1:01:05'.
Pista: usa división entera // y módulo %.
"""
def conversor (tiempo:int) -> str:
    segs=tiempo
    mins=0
    hrs=0

    segs=tiempo%60
    tiempo=tiempo//60
    if tiempo > 0:
        mins=tiempo%60
        tiempo=tiempo//60
        if tiempo>0:
            hrs=tiempo%60
    res=(f'{hrs}h:{mins}m:{segs}s')
    return res


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
    positivo=False
    while positivo is False:
        entero=pedir_entero("Introduzca un numero entero y positivo de segundos: ")
        if entero>=0:
            positivo=True
    print("El numero introducido equivale a: ",conversor(entero))
    
main()