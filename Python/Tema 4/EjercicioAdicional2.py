"""
Ejercicio 2 — Calculadora básica con funciones
Implementa cuatro funciones: sumar(a, b), restar(a, b), multiplicar(a, b) y dividir(a, b).
En un bucle, pide una operación (+, -, *, /) y dos números, muestra el resultado y sigue
preguntando hasta que el usuario teclee 'salir'. Controla división por cero y entradas
inválidas.
No uses eval().
"""
def suma (a,b):
     res = 0
     try:
          n1 = int(a)
          n2 = int(b)
          res=n1+n2
     except ValueError:
          print("Algun parametro de los introducidos no es un numero entero")
     return res

def resta (a,b):
     res = 0
     try:
          n1 = int(a)
          n2 = int(b)
          res=n1-n2
     except ValueError:
          print("Algun parametro de los introducidos no es un numero entero")
     return res

def multiplicacion (a,b):
     res = 0
     try:
          n1 = int(a)
          n2 = int(b)
          res=n1*n2
     except ValueError:
          print("Algun parametro de los introducidos no es un numero entero")
     return res

def division (a,b):
     try:
          n1 = int(a)
          n2 = int(b)
          return n1/n2
     except ValueError:
          print("Algun parametro de los introducidos no es un numero entero")
     except ZeroDivisionError:
          print("No se puede dividir entre 0")

def main():
     fin=False
     while fin==False:
          n1=0
          n2=0
          try:
               n1 = int(input("Introduzca un numero entero (n1): "))
               n2 = int(input("Introduzca un numero entero (n2): "))
               accion = input("Introduzca la operacion que desea realizar (Escriba salir para terminar el programa): ")
               match accion:
                    case n if accion == "+":
                         print(f"La suma de {n1} y {n2} es {suma(n1,n2)}")
                    case n if accion == "-":
                         print(f"La resta de {n1} y {n2} es {resta(n1,n2)}")
                    case n if accion == "*":
                         print(f"La multiplicacion de {n1} y {n2} es {multiplicacion(n1,n2)}")
                    case n if accion == "/":
                         if(division(n1,n2)!=None):
                              print(f"La division de {n1} y {n2} es {division(n1,n2)}")
                    case n if accion == "salir":
                         fin=True
                    case _:
                         print("No se ha introducido una operacion correcta")
          except ValueError:
               print("No se ha introducido un numero entero")
          
                         
main()