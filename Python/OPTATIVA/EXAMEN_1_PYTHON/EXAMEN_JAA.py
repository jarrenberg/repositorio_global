def mostrarmenu()->str:
    print("\n\nMENU DE OPCIONES")
    print("a) Reemplazar vocales de una frase ")
    print("b) Mensaje cuando el numero introducido no sea mayor que el primero")
    print("c) Encontrar la primera palabra más larga")
    print("d) Mostrar rectángulo con números impares entre 0 y 100")
    print("e) Contar la aparición de cada carácter en una palabra")
    print("f) Salir")
    resultado = input()
    if(resultado.isalpha):
        resultado=resultado.lower()
    return resultado

def reemplazarVocalesFrase(frase:str, caracter:str)->str:
    container=frase
    resultado=""
    if len(caracter)!=1:
        resultado="La longitud del caracter sustitutorio no es valida"
    else:    
        vocales=["a","e","i","o","u"]
        for letra in container:
            if letra.lower() in vocales:
                resultado=f'{resultado}{caracter}'
            else:
                resultado=f'{resultado}{letra}'
    return resultado

def avisarNumeroMayorQuePrimero()->list:
    primero=pedir_entero("Introduzca un numero entero: ")
    lista = list()
    lista.append(primero)
    numUser=(primero-1)
    while numUser<=primero:
        numUser=pedir_entero("Introduzca un numero entero: ")
        lista.append(numUser)
    print("FIN")
    print(f"El ultimo numero introducido {numUser} es mayor que el primero {primero}")
    return lista

def encontrarLaPrimeraPalabraMasLarga(cadena:str)->str:
    resultado:str
    if len(cadena)<1:
        resultado="No existe una palabra mas larga porque la cadena esta vacia"
    else:
        lista_palabras = cadena.split(" ")
        longitudmaxima=-1
        for palabra in lista_palabras:
            if len(palabra)>longitudmaxima:
                resultado=palabra
                longitudmaxima=len(palabra)
    return resultado

def mostrarRectanguloImpares0a100(columnas:int,filas:int):
    numero = 99
    for i in range(filas):
        for i in range(columnas):
            print(numero,end=" ")
            numero=numero-2
        print("")


def contadorAparicionCaracterPorPalabra(cadena:str):
    lista_letras = list()
    lista_veces = list()
    for letra in cadena:
        if letra not in lista_letras:
            lista_letras.append(letra)
            lista_veces.append(1)
        else:
            lista_letras.index(letra)
            lista_veces[lista_letras.index(letra)]=lista_veces[lista_letras.index(letra)]+1          
    for letra in lista_letras:
        print(f'La letra {letra} aparece {lista_veces[lista_letras.index(letra)]} veces en la frase: {cadena}')
    
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
    try:
        salir=False
        while salir == False:
            accion = mostrarmenu()
            match accion:
                case n if accion == "a":
                    frase = input("Introduzca una cadena de texto: ")
                    caracter = input("Introduzca el caracter sustitutorio: ")
                    frase_cambiada = reemplazarVocalesFrase(frase,caracter)
                    print(f'{frase_cambiada} ')
                case n if accion == "b":
                    lista = avisarNumeroMayorQuePrimero()                    
                    print(f'La lista de numeros introducidos es: {lista}')
                case n if accion == "c":
                    frase = input("Introduzca una cadena de texto: ")
                    palabra= encontrarLaPrimeraPalabraMasLarga(frase)
                    if palabra!="No existe una palabra mas larga porque la cadena esta vacia":
                        print(f"La palabra mas larga es: {palabra}")
                    else:
                        print(palabra)
                case n if accion == "d":
                    columnas = pedir_entero("Introduzca el numero de columnas del rectangulo: ")
                    filas = pedir_entero("Introduzca el numero de filas del rectangulo: ")
                    mostrarRectanguloImpares0a100(columnas,filas)
                case n if accion == "e":
                    frase = input("Introduzca una cadena de texto: ")
                    contadorAparicionCaracterPorPalabra(frase)
                case n if accion == "f":
                    salir=True
                    print("Saliendo del programa ...")
                case _:
                    print("El valor introducido no es valido")
    except ValueError:
        print("Se ha producido un error en la introduccion de un numero")
        
main()