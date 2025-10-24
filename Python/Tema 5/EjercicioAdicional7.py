"""
7. # Definir la función
# finales : (int, list[A]) -> list[A]
# tal que finales(n, xs) es la lista formada por los n finales
# elementos de xs. Por ejemplo,
# finales(3, [2, 5, 4, 7, 9, 6]) == [7, 9, 6]
"""
def finales(index:int,lista:list)->list:
    resultado = []
    i = -1
    max_pos = -index
    while i >= max_pos:
        resultado.append(lista[i])
        i -= 1
    resultado.reverse()
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
    index = pedir_entero("Introduzca el numero de elementos a la nueva lista: ")
    print(f"Los {index} ultimos elementos de la lista {lista} son {finales(index,lista)}")
    
    
main()