"""
Ejercicio 18 — Detección de número capicúa
Función es_capicua(n: int) -> bool que determine si el número leído de izquierda a derecha
es igual que de derecha a izquierda.
Pista: convierte a cadena y compara con su inversa.
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
 
def invertir_cadena(s: str) -> str:
    resultado=""
    for letra in s:
        resultado=letra+resultado
    return resultado
 
def es_capicua(n: int) -> bool:
    capicua=False
    numero_str = str(n)
    if (numero_str == invertir_cadena(numero_str)):
        capicua=True
    return capicua

def main():
    num = pedir_entero("Introduzca un numero entero:")
    if(es_capicua(num)):
        print(f"El numero introducido {num} es capicua")
    else:
        print(f"El numero introducido {num} no es capicua")        
main()