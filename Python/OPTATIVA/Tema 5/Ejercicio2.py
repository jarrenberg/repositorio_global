"""
2. Repite el ejercicio anterior, pero ahora lo que se leen son textos. La condición
de finalización será la cadena vacía.
"""
def orden_introducido(lista:list)->str:
    res="-"
    for cadena in lista:
        res=(f'{res} {cadena} ')
    return res

def orden_creciente(lista:list)->str:
    lista.sort()
    res="-"
    for cadena in lista:
        res=(f'{res}{cadena} ')
    return res

def orden_decreciente(lista:list)->str:
    lista.sort(reverse=True)
    res="-"
    for cadena in lista:
        res=(f'{res} {cadena} ')
    return res

def pedir_cadenas()->list:
    lista = []
    n="0"
    while n!="":
        n = input("Introduzca una cadena de texto: ")
        if n!=0:
            lista.append(n)
    return lista

def main():
    lista = pedir_cadenas()
    print("Cadenas en el orden introducido:",orden_introducido(lista))
    print("Cadenas en orden creciente:",orden_creciente(lista))
    print("Cadenas en orden decreciente:",orden_decreciente(lista))
    
main()