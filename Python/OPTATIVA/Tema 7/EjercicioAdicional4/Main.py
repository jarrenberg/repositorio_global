from Empleado import Empleado
from Asalariado import Asalariado
from PorHora import PorHora
from Comisionista import Comisionista

def main():
    plantilla:list[Empleado] = []
    print("GESTION DE PLANTILLA")
    print("Creando 3 empleados asalariados:")
    try:
        e1 = Asalariado("Juan",1200)
        plantilla.append(e1)
        print(f"\tAñadido")
        e2 = Asalariado("Jorge",1400)
        plantilla.append(e2)
        print(f"\tAñadido")
        e3 = Asalariado("Marcos",-1)
        plantilla.append(e3)
        print(f"\tAñadido")
    except Exception as e:
        print(f"Error: {e}\n")

    print("Creando 3 empleados por horas:")
    try:
        e1 = PorHora("Raquel",8,80)
        plantilla.append(e1)
        print(f"\tAñadido")
        e2 = PorHora("David",9,100)
        plantilla.append(e2)
        print(f"\tAñadido")
        e3 = PorHora("Hector",10,180)
        plantilla.append(e3)
        print(f"\tAñadido")
    except Exception as e:
        print(f"Error: {e}\n")

    print("Creando 3 empleados comisionistas:")
    try:
        e1 = Comisionista("Eli",1200,0.7)
        plantilla.append(e1)
        print(f"\tAñadido")
        e2 = Comisionista("Maria Jesus",1400,0.3)
        print(f"\tAñadido")
        plantilla.append(e2)
        e3 = Comisionista("Gertru",1000,2)
        plantilla.append(e3)
        print(f"\tAñadido")
    except Exception as e:
        print(f"Error: {e}\n")

    for empleado in plantilla:
        print(empleado.__str__())

    print(f"El sueldo global de la empresa es {getSalarioGlobal(plantilla):.2f}€")

def getSalarioGlobal(plantila:list[Empleado])->float:
    resultado=0
    for empleado in plantila:
        resultado=resultado+empleado._sueldo
    return resultado

main()