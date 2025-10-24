"""
Ejercicio 17 — Contador de caracteres específicos
Función contar_caracter(s: str, c: str) -> int que devuelva cuántas veces aparece el carácter c
en s.
Pista: recorre la cadena con un bucle y suma 1 cuando ch == c.
"""
def contar_caracter(s: str, c: str) -> int:
    veces=0
    for letra in s:
        if(letra==c):
            veces=veces+1
    return veces

def main ():
    cadena = input("Introduzca una cadena de texto: ")
    caracter = input("Introduzca el caracter a comparar: ")[0]
    print(f"El caracter '{caracter}' aparece {contar_caracter(cadena,caracter)} vez/veces en la cadena '{cadena}'")    
    
main()
