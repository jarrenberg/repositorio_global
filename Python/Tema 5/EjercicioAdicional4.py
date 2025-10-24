"""
4. Definir la función
# rota : (int, List[A]) -> List[A]
# tal que rota(n, xs) es la lista obtenida poniendo los n primeros
# elementos de xs al final de la lista. Por ejemplo,
# rota(1, [3, 2, 5, 7]) == [2, 5, 7, 3]
# rota(2, [3, 2, 5, 7]) == [5, 7, 3, 2]
# rota(3, [3, 2, 5, 7]) == [7, 3, 2, 5]
"""
def rota (index:int, lista:list)->list:
    resultado=lista.copy()
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

def main ():
    n=1
    lista=[]
    while n!=0:
        n = pedir_entero("Introduzca un entero para añadirlo a la lista: ")
        if n!=0:
            lista.append(n)
    print("Mostando la lista rotada 1 posicion: ")
    print(rota(1,lista))
    print("Mostando la lista rotada 2 posiciones: ")
    print(rota(2,lista))
    print("Mostando la lista rotada 3 posiciones: ")
    print(rota(3,lista))

main()