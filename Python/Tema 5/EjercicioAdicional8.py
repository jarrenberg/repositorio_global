"""
8. # Ejercicio 13. Definir la función
# segmento : (int, int, list[A]) -> list[A]
# tal que segmento(m, n, xs) es la lista de los elementos de xs
# comprendidos entre las posiciones m y n. Por ejemplo,
# segmento(3, 4, [3, 4, 1, 2, 7, 9, 0]) == [1, 2]
# segmento(3, 5, [3, 4, 1, 2, 7, 9, 0]) == [1, 2, 7]
# segmento(5, 3, [3, 4, 1, 2, 7, 9, 0]) == []
# ---------------------------------------------------------------------
"""
def segmento(inicio:int,fin:int,lista:list)->list:
    cont=lista.copy()
    min=inicio-1
    max=fin
    resultado = cont[min:max]
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
    inicio=pedir_entero("Introduzca el inicio del segmento: ")
    fin=inicio
    while fin<= inicio:
        fin = pedir_entero("Introduzca el fin del segmento: ")
    print("El segmento escogido de la lista es:",segmento(inicio,fin,lista))
main()