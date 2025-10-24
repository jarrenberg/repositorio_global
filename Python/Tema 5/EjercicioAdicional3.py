"""
3. Definir una función que devuelva la suma dos números. Utilizar esa función para sumar tres
números.

"""
def suma (a:int,b:int)->int:
    return a+b
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
    n1 = pedir_entero("Introduzca un numero entero: ")
    n2 = pedir_entero("Introduzca otro numero entero: ")
    n3 = pedir_entero("Introduzca un ultimo numero entero: ")
    print("La suma de los tres numeros enteros introducidos es: ",suma(n1,suma(n2,n3)))
    
main()