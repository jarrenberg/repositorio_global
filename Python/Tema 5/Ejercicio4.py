"""
4. Escribe un programa que lea dos textos y compruebe si una es palíndromo de
la otra. El programa debe preguntar si se desea comprobar teniendo en cuenta
mayúsculas/minúsculas o no.
"""
def pedir_entero(mensaje:str)->int:
     valido=False
     while valido==False:
          try:
               entero = int(input(mensaje))
               valido=True
          except ValueError: 
               print("No se ha introducido entero valido")
     return entero
def es_palindromo_discrimina(s1:str,s2:str) -> bool:
    palindromo=False
    if (elimina_espacios(s1)==elimina_espacios(s2)):
        palindromo=True
    return palindromo

def es_palindromo_no_discrimina(s1:str,s2:str) -> bool:
    palindromo=False
    if (elimina_espacios(s1).lower()==elimina_espacios(s2).lower()):
        palindromo=True
    return palindromo


def elimina_espacios(s:str)-> str:
    res=""
    for letra in s:
        if letra!=" ":
            res=(f'{res}{letra}')
    return res
            

def invertir_cadena(s: str) -> str:
    resultado=""
    for letra in s:
        resultado=letra+resultado
    return resultado
def main():
    cad1=input("Introduzca una cadena de texto: ")
    cad2=input("Introduzca otra cadena de texto: ")
    mayus = pedir_entero("Introduzca 0 si desea comprobar si ambas cadenas son palindromas teniendo en cuenta las mayúsculas y 1 si no: ")
    if(mayus==0):
        if(es_palindromo_discrimina(cad1,cad2)) is True:
            print("Las cadenas son palindromas teniendo en cuenta las mayusculas y las minusculas")
        else:
            print("Las cadenas son no palindromas teniendo en cuenta las mayusculas y las minusculas")
    if(mayus==1):
        if(es_palindromo_no_discrimina(cad1,cad2) is True):
            print("Las cadenas son palindromas no teniendo en cuenta las mayusculas y las minusculas")
        else:
            print("Las cadenas son no palindromas no teniendo en cuenta las mayusculas y las minusculas")
main()
