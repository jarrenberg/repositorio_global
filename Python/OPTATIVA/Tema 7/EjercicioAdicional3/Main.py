import Coche
import Moto
import Camion

def main():
    lista_coches=[]
    print("Creando 2 coches ...")
    try:
        lista_coches.append(Coche("Focus",2004))
        lista_coches.append(Coche("Focus",1800))
    except Exception as e:
        print(f"Error: {e}\n")
        
    print("Creando 2 motos ...")
    try:
        lista_coches.append(Moto("Sh 125",2030))
        lista_coches.append(Moto("Yamaha",2025))
    except Exception as e:
        print(f"Error: {e}\n")
        
    print("Crando 3 camiones")
    try:
        lista_coches.append(Camion("MAN",1995,2))
        lista_coches.append(Camion("SuperTruck",2000,0))
        lista_coches.append(Camion("CamionErroneo",1960,-1))
    except Exception as e:
        print(f"Error: {e}\n")
        
    for vehiculo in lista_coches:
        print(f"{vehiculo}")
        
        
        
        
main()