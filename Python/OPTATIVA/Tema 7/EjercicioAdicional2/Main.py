from Individual import Individual
from Doble import Doble
from Suite import Suite

def main():
    lista_habitaciones:list=[]
    print("Creando 3 habitaciones individuales distintas ...")
    try:
        lista_habitaciones.append(Individual(3))
        lista_habitaciones.append(Individual(1))
        lista_habitaciones.append(Individual(-4))
    except Exception as e:
        print(f"Error: {e}\n")
        
    print("Creando 3 habitaciones dobles distintas ...")
    try:
        lista_habitaciones.append(Doble(4,False))
        lista_habitaciones.append(Doble(2,True))
        lista_habitaciones.append(Doble(5,True))
    except Exception as e:
        print(f"Error: {e}\n")
        
    print("Creando 3 suites distintas ...")
    try:
        lista_habitaciones.append(Suite(4))
        lista_habitaciones.append(Suite(2))
        lista_habitaciones.append(Suite(-1))
    except Exception as e:
        print(f"Error: {e}\n")
        
    print("Mostrando las habitaciones ...\n")
    for habitacion in lista_habitaciones:
        print(habitacion)

main()