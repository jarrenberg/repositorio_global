"""
Ejercicio 15 — Calculadora de potencias con menú
Muestra un menú con opciones:
a) Cuadrado b) Cubo c) Potencia n d) Salir
Según la opción, pide un número (y, si aplica, el exponente) y muestra el resultado.
Pista: usa un bucle while y una función calcular_potencia(base, exp).
"""
def menu ():
    print("Menu Potencias:")
    print("a) Cuadrado")
    print("b) Cubo")
    print("c) Potencia n")
    print("d. Salir")
    
def pedir_entero(mensaje:str)->int:
     valido=False
     while valido==False:
          try:
               entero = int(input(mensaje))
               valido=True
          except ValueError: 
               print("No se ha introducido entero valido")
     return entero
   

def cuadrado(base:int)->int:
    return pow(base,2)

def cubo(base:int)->int:
    return pow(base,3)

def exponente_n (base:int,exp:int)->int:
    return pow(base,exp)

def main():
    salir=False
    while(salir==False):
        menu()
        accion = input("Introduzca la accion a realizar: ")
        if(accion=="d"):
            print("Saliendo ...")
            salir=True
        else:
            match accion:
                case n if accion =="a":
                    base = pedir_entero("Introduzca la base de la potencia al cuadrado: ")
                    print(f"El resultado de elevar {base} al cuadrado es {cuadrado(base)}")
                case n if accion =="b":
                    base = pedir_entero("Introduzca la base de la potencia al cubo: ")
                    print(f"El resultado de elevar {base} al cubo es {cubo(base)}")
                case n if accion =="c":
                    base = pedir_entero("Introduzca la base de la potencia: ")
                    exponente = pedir_entero("Introduzca la base de la potencia: ")
                    print(f"El resultado de elevar {base} a {exponente} es {exponente_n(base,exponente)}")
                case _:
                    print("El comando no es valido")
            print("")

main()
                    
