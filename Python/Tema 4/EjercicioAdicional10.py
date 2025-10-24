"""
Ejercicio 10 — Normaliza nombres (cadenas)
Escribe una función normaliza_nombre(s: str) -> str que:
- Elimine espacios al inicio/fin y reduzca espacios múltiples internos a uno.
- Convierta a "Title Case" (primera letra de cada palabra en mayúscula, resto minúsculas),
sin usar .title() directamente.
Entrada de ejemplo: " aNa péRez loPEz " → "Ana Pérez Lopez"
Pista: separa por espacios, filtra vacíos, y vuelve a unir con ' '. Usa slicing y
.lower()/.upper().
"""
def normaliza_nombre(s: str) -> str:
    lista = s.split(" ");
    res=""
    for palabra in lista:
        if palabra!="":
            p_minus=palabra.lower()
            p_cap=p_minus.capitalize()
            res=res+p_cap+" "
    return res

def main():
    cadena=input("Introduzca una cadena de texto para normalizarla: ")
    print(normaliza_nombre(cadena))
    
main()