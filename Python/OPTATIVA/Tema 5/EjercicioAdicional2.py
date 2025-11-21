"""
2. Definir una función que, al recibir una cadena de texto, cuente cuántas palabras hay y
devuelva dicho valor.
"""
def palabras_en_string(s:str)->int:
    lista = s.split(" ")
    return len(lista)

def main():
    cadena = input("Introduzca una cadena de texto: ")
    print(f"El numero de palabras en la cadena {cadena} es {palabras_en_string(cadena)}")


main()