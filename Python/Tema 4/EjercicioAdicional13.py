"""
Ejercicio 13 — Contar vocales y consonantes (sin listas)
Escribe una función contar_vocales_consonantes(s: str) -> tuple[int, int] que recorra la
cadena y cuente
cuántas vocales y cuántas consonantes tiene. Ignora espacios y signos.
Pista: usa 'aeiouAEIOU' y .isalpha() para detectar letras.
"""
def contar_vocales_consonantes(s: str) -> tuple[int, int]:
    vocales = ["a","e","i","o","u"]
    contador_vocales= 0
    contador_consonantes=0
    for letra in s:
        if(letra.isalpha()):
            if(letra.lower() in vocales):
                contador_vocales=contador_vocales+1
            else:
                contador_consonantes=contador_consonantes+1
    return (contador_vocales,contador_consonantes)

def main ():
    cadena = input("Introduzca una cadena de texto: ")
    resultado = contar_vocales_consonantes(cadena)
    print(f"En la cadena de texto '{cadena}' hay {resultado[0]} vocales y {resultado[1]} consonantes ")

main()
