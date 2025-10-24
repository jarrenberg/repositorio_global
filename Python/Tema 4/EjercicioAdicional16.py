"""
Ejercicio 16 — Inversión de cadena (sin usar slicing inverso)
Escribe una función invertir_cadena(s: str) -> str que devuelva la cadena invertida.
Ejemplo: 'python' → 'nohtyp'.
Pista: usa un bucle que recorra la cadena al revés con índices o concatenando caracteres al
inicio.
"""

def invertir_cadena(s: str) -> str:
    resultado=""
    for letra in s:
        resultado=letra+resultado
    return resultado

def main ():
    cadena = input("Introduzca una cadena de texto para invertirla: ")
    print(f"La cadena original es '{cadena}' y la cadena invertida es '{invertir_cadena(cadena)}'")
main()