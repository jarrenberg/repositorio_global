"""
Ejercicio 6 — Conversor de temperaturas
Implementa funciones c_a_f(c) y f_a_c(f) para convertir entre Celsius y Fahrenheit.
Pide al usuario el sentido de la conversión y el valor numérico, y muestra el resultado con 2
decimales.
Repite hasta que el usuario escriba 'fin'. Controla entradas inválidas.
"""
def celsius_a_farenheit (c:float) -> float:
     return round(((c*9/5)+32),2)
def farenheit_a_celsius (f:float) -> float:
     return round(((f-32)*5/9),2)

def main():
     salir=False
     while(salir==False):
          print("Menu:")
          print("a) Pasar de Celsius a Farenheit")
          print("b) Pasar de Fareneheit a Celsius")
          print("Introduzca 'fin' para salir.")
          accion = input()
          if(accion.isalpha==True):
               accion=accion.lower()
               if(accion == "fin"):
                    salir=True
               else:
                    try:
                         match accion:
                              case n if accion=="a":
                                   n = float(input("Introduzca el valor de la temperatura: "))
                                   temp = celsius_a_farenheit(n)
                                   print(f"{n}ºC es equivalente a {temp}ºF")
                              case n if accion=="b":
                                   n = float(input("Introduzca el valor de la temperatura: "))
                                   temp = farenheit_a_celsius(n)
                                   print(f"{n}ºf es equivalente a {temp}ºC")
                              case _:
                                   print("Se ha introducido una accion no valida")
                    except ValueError:
                         print("No se ha introducido un numero valido")
main()
          
