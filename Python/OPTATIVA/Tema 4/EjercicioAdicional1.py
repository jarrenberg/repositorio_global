"""
Ejercicio 1 — Par o impar con validación
Escribe una función es_par(n: int) -> bool que devuelva True si n es par y False si es impar.
A continuación, en un bucle, pide números al usuario hasta que introduzca 'fin'. Por cada
número,
muestra si es par o impar. Valida que la entrada sea un entero (si no lo es, vuelve a pedirla).
"""
def es_par(n:int)-> bool:
    par = True
    if(n%2==0):
        par=True
    else:
        par=False
    return par


def main():
    fin=False
    while fin==False:
        accion = input("Introduzca el numero:").lower()
        if(accion=="fin"):
            fin=True
        else:
            try:
                num=int(accion)
                if(es_par(num) is True):
                    print(f"El numero {num} es par")
                else:
                    print(f"El numero {num} es impar")
            except ValueError:
                print("No se ha introducido un numero entero")
main()



