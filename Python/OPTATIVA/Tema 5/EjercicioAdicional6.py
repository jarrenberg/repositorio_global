"""
6. Definir la función
# interior : (list[A]) -> list[A]
# tal que interior(xs) es la lista obtenida eliminando los extremos de
# la lista xs. Por ejemplo,
# interior([2, 5, 3, 7, 3]) == [5, 3, 7]
"""
def interior (lista:list)->list:
    resultado=lista.copy()
    resultado.pop(0)
    resultado.pop(-1)
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
    print("La lista con los extremos recortados es: ",interior(lista))
    
main()