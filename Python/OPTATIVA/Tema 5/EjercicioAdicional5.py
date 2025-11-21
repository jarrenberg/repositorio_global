"""
5. Definir la función
# rango : (List[int]) -> List[int]
# tal que rango(xs) es la lista formada por el menor y mayor elemento
# de xs.
# rango([3, 2, 7, 5]) == [2, 7]
"""
def rango(lista:list)->list:
    contenedor = lista.copy()
    contenedor.sort()
    menor=contenedor.pop(0)
    mayor=contenedor.pop(-1)
    resultado=[menor,mayor]
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
    resultado= rango(lista)
    print(f"El numero menor de la lista es {resultado[0]} y el mayor es {resultado[1]}" )
    
main()