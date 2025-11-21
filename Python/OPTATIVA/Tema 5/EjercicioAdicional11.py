"""
11. Definir la función
# intercambia : (tuple[A, B]) -> tuple[B, A]
# tal que intercambia(p) es el punto obtenido intercambiando las
# coordenadas del punto p. Por ejemplo,
# intercambia((2,5)) == (5,2)
# intercambia((5,2)) == (2,5)
"""
def intercambia(original:tuple)->tuple:
    return [original[1],original[0]]

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
    n1 = pedir_entero("Introduzca un entero de la tupla: ")
    n2 = pedir_entero("Introduzca otro numero de la tupla: ")
    print(f'La tupla cambiada de lugar es {intercambia([n1,n2])}')
main()