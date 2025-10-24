"""
Ejercicio 19 — Convertidor de mayúsculas/minúsculas
Pide una frase y una opción:
a) Convertir a mayúsculas
b) Convertir a minúsculas
c) Capitalizar (solo primera letra en mayúscula)
Implementa funciones separadas para cada caso.
Pista: usa .upper(), .lower(), .capitalize().
"""
def menu ():
    print("Menu:")
    print("a. Convertir a mayusculas")
    print("b. Convertir a minusculas")
    print("c. Capitalizar (solo primera letra en mayuscula)")

def convertir_a_minusculas (s:str)->str:
    return s.lower()

def convertir_a_mayusculas (s:str)->str:
    return s.upper()

def capitalizar (s:str)->str:
    res =""
    lista = s.split(" ")
    for palabra in lista:
        p_cap=palabra.capitalize();
        res=res+p_cap+" "
    return res

def main():
    menu()
    accion = input("Introduzca una accion a realizar: ")
    if(accion.isalpha()):
        accion=accion.lower()
        match accion:
            case n if accion == "a":
                cadena = input("Introduzca una cadena: ")
                print(convertir_a_mayusculas(cadena))
            case n if accion == "b":
                cadena = input("Introduzca una cadena: ")
                print(convertir_a_minusculas(cadena))
            case n if accion == "c":
                cadena = input("Introduzca una cadena: ")
                print(capitalizar(cadena))
            case _: 
                print("no se ha introduido una accion valida en el menu")
    else:
        print("La accion introducida no es una letra")
        
main()