"""
1. Escribe un programa que recoja números de teclado hasta que se introduce un
cero. Luego debe mostrar la secuencia de números de tres modos:
a. En el orden en que se introdujeron.
b. En orden creciente.
c. En orden decreciente.
Ejemplo: si se introduce 4 3 5 2 0, debe mostrarse:
- 4 3 5 2
- 2 3 4 5
- 5 4 3 2
"""
def orden_introducido(lista:list)->str:
    res="-"
    for numero in lista:
        res=(f'{res} {numero} ')
    return res

def orden_creciente(lista:list)->str:
    lista.sort()
    res="-"
    for numero in lista:
        res=(f'{res} {numero} ')
    return res

def orden_decreciente(lista:list)->str:
    lista.sort(reverse=True)
    res="-"
    for numero in lista:
        res=(f'{res} {numero} ')
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

def pedir_numeros()->list:
    n=1
    lista = []
    while n!=0:
        n= pedir_entero("Introduzca un numero entero: ")
        if n!=0:
            lista.append(n)
    return lista

def main():
    lista = pedir_numeros()
    print(orden_introducido(lista))
    print(orden_creciente(lista))
    print(orden_decreciente(lista))
    
main()