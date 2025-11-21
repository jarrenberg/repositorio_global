#Ejercicio1
import math
import random

#Menu que muestra las opciones que puede ejecutar el programa
def mostrarmenu():
    print("MENU DE OPCIONES")
    print("a) Mostrar un rombo")
    print("b) Adivinar un numero")
    print("c) Resolver una ecuacion de segundo grado")
    print("d) Tabla de números")
    print("e) Cálculo del número factorial de un número")
    print("f) Cálculo de un número de la sucesión de Fibonacci  ")
    print("g) Tabla de multiplicar")
    print("h) Salir")
    resultado = input()
    if(resultado.isalpha):
        resultado=resultado.lower()
    return resultado

#Funcion que mediante la introduccion de un numero impar (base) muestra un rombo de amplitud base
def rombo():
    impar=False
    base = 0
    while impar == False:
        base = int(input("Introduzca el tamaño de la base de la piramide (Este texto se repetira mientras el numero introducido no sea impar):"))
        if(int(base%2)==1):
            impar = True
    espacios=int((base/2)+1)
    numAsteriscos=1
    for i in range (int((base/2)+1)):
        for j in range (espacios):
            print(" ",end="")
        espacios-=1
        print("*"*numAsteriscos)
        numAsteriscos+=2
    espacios = 2
    numAsteriscos = base - 2
    for i in range (base-2,0,-1):
        for j in range (espacios):
            print(" ",end="")
        espacios+=1
        print("*"*numAsteriscos)
        numAsteriscos-=2

#Funcion que mediante la introduccion de la introduccion de un numero de filas (f) y columnas (c)
#muestra una tabla fxc con numeros aleatorios en cada posicion
def tabla():
    filas = int(input("Introduzca el numero de filas:"))
    columnas = int(input("Introduzca las columnas:"))

    for i in range (filas):
        for j in range (columnas):
            print(random.randint((-10*i),(10*j)),end="  ")
        print("")

#Funcion que muestra la sucesion de Fibonacci hasta el numero introducido como parametro (n)
def fib(n):
    a, b = 0, 1
    while a <= n:
        print(a, end=' ')
        a, b = b, a+b
    print()

#Funcion que muestra la tabla de multiplicar del numero introducido como parametro (n)
def multiplicar(n):
    print(f"TABLA DEL {n}:")
    for i in range (1,11):
        print(f"{n}x{i}={n*i}")

#Funcion que muestra el factorial del numero introducido como parametro (n)
def factorial(n):
    resultado=1
    for i in range (n,0,-1):
        resultado = resultado*i
    return resultado

#Funcion que devuelve los resultados de realizar una ecucaion de segundo grado con los coeficientes introducidos
#por el usuario (a,b,c)
def ecuacion(a,b,c):
    r1=0
    r2=0
    try:
        p1 = ((b)*(b)) - (4*(a*c))
        p2 = (math.sqrt(p1))
        p3 = -b + p2
        r1 = p3 / (2*a)
    except ValueError:
        print("Se ha intentado hacer la raiz cuadrada de un numero negativo")
    
    try:
        p1 = ((b)*(b)) - (4*(a*c))
        p2 = (math.sqrt(p1))
        p3 = -b - p2
        r2 = p3 / (2*a)
    except ValueError:
        print("Se ha intentado hacer la raiz cuadrada de un numero negativo")
    
    if(r1==0 and r2==0):
        print("La ecuacion no tiene resultados reales")
    if(r1==0 and r2!=0):
        print(f"El resultado es {r2}")
    if(r2==0 and r1!=0):
        print(f"El resultado es {r1}")
    if(r1!=0 and r2!=0):
        print(f"Los resultados son {r1} y {r2}")

#Funcion que permite averiguar un numero entero indicando al usuario si el numero introducido es menor o mayor
def averiguar():
    resultado = random.randint(0,100)
    i=101
    while(i!=resultado):
        i = int(input("Introduzca un numero:"))
        if(i<resultado):
            print("El numero introducido es menor que el numero a averiguar")
        if(i>resultado):
            print("El numero introducido es mayor que el numero a averiguar")
    print(f"Felicidades, acertaste el numero {resultado}")

#Menu que le indica al usuario que accion tomar y llama a las funciones encargadas de realizar dichas acciones
def main():
    try:
        salir = False
        while (salir is False):
            print("")
            print("")
            accion = mostrarmenu()
            match accion:
                case n if accion == "a":
                    rombo()
                case n if accion == "b":
                    averiguar()
                case n if accion == "c":
                    a=0
                    while(a==0):
                        a = int(input("Introduce el termino elevado a 2 (no puede ser 0):"))
                        b = int(input("Introduce el termino elevado a 1:"))
                        c = int(input("Introduce el termino independiente:"))
                    ecuacion(a,b,c)
                case n if accion == "d":
                    tabla()
                case n if accion == "e":
                    n = int(input("Introduce el numero cuyo factorial te gustaría conocer:"))
                    print(f"El factorial del numero {n} es {factorial(n)}")
                case n if accion == "f":
                    n = int(input("Introduce un numero para saber la sucesion de Fibonacci hasta dicho numero:"))
                    fib(n)
                case n if accion == "g":
                    n = int(input("Introduzca un numero del cual le gustaria conocer su tabla de multiplicar:"))
                    multiplicar(n)

                case n if accion == "h":
                    salir=True
                case _:
                    print("El valor introducido no es valido")
    except ValueError:
        print("Se ha producido un error en la introduccion de un numero")


#LLamada al menu que ejecuta el programa
main()
