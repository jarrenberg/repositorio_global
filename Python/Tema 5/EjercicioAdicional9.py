"""
9. Definir la función
# extremos : (int, list[A]) -> list[A]
# tal que extremos(n, xs) es la lista formada por los n primeros
# elementos de xs y los n finales elementos de xs. Por ejemplo,
# extremos(3, [2, 6, 7, 1, 2, 4, 5, 8, 9, 2, 3]) == [2, 6, 7, 9, 2, 3]
"""
def extremos (extremo:int,lista:list)->list:
    resultado=[]
    if (extremo*2)>len(lista):
        resultado=[]
    else: 
        cont=lista.copy()
        resultado.append(cont[0:extremo])
        resultado.append(cont[(extremo*(-1)):])
    print(resultado)
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
    n=1
    lista=[]
    while n!=0:
        n = pedir_entero("Introduzca un entero para añadirlo a la lista: ")
        if n!=0:
            lista.append(n)
    limite=pedir_entero("Introduzca el limite del segmento: ")
    print(f"La lista con el segmento limitado es : {extremos(limite, lista)}")


main()
    