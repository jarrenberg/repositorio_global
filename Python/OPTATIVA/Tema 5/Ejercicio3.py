"""
3. Escribe un programa lea un texto y determine si es un palíndromo. Procura
crear una función palindromo(s) -> Bool.
NOTA: una cadena es palíndromo si se lee igual de izquierda a derecha que
de derecha a izquierda. Por ejemplo, la palabra OSO es un palíndromo.
"""
def es_palindromo(s:str) -> bool:
    palindromo=False
    if (elimina_espacios(s).lower()==elimina_espacios(invertir_cadena(s)).lower()):
        palindromo=True
    return palindromo

def elimina_espacios(s:str)-> str:
    res=""
    for letra in s:
        if letra!=" ":
            l = letra.format()
            res=(f'{res}{l}')
    return res
            

def invertir_cadena(s: str) -> str:
    resultado=""
    for letra in s:
        resultado=letra+resultado
    return resultado

def main():
    cadena = input("Introduzca una cadena de texto: ")
    if(es_palindromo(cadena) is True):
        print("La cadena de texto introducida es palindroma")
    else:
        print("La cadena de texto introducida no es palindroma")
main()