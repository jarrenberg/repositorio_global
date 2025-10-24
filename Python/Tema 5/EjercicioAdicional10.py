"""
10. Las dimensiones de los rectángulos puede representarse
# por pares; por ejemplo, (5,3) representa a un rectángulo de base 5 y
# altura 3.
#
# Definir la función
# mayorRectangulo : (tuple[float, float], tuple[float, float])
# -> tuple[float, float]
# tal que mayorRectangulo(r1, r2) es el rectángulo de mayor área entre
# r1 y r2. Por ejemplo,
# mayorRectangulo((4, 6), (3, 7)) == (4, 6)
# mayorRectangulo((4, 6), (3, 8)) == (4, 6)
# mayorRectangulo((4, 6), (3, 9)) == (3, 9)
# ---------------------------------------------------------------------
"""
def mayorRectangulo(rect1:tuple[float,float],rect2:tuple[float,float]):
    if((rect1[0]*rect1[1]) > (rect2[0]*rect2[1])):
        resultado=rect1
    else:
        resultado=rect2
    return resultado
def main():
    h1=pedir_entero("Introduzca la altura del primer rectangulo: ")
    b1=pedir_entero("Introduzca la base del primer rectangulo: ")
    h2=pedir_entero("Introduzca la altura del segundo rectangulo: ")
    b2=pedir_entero("Introduzca la base del segundo rectangulo: ")
    print(f'El mayor rectangulo de los dos es: {mayorRectangulo([h1,b1],[h2,b2])}')
    

def pedir_entero(mensaje:str)->int:
     valido=False
     while valido==False:
          try:
               entero = int(input(mensaje))
               valido=True
          except ValueError: 
               print("No se ha introducido entero valido")
     return entero

main()