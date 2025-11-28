from Coche import Coche
from Moto import Moto
from Camion import Camion
from Vehiculo import Vehiculo


def main():
    lista_vehiculos: list[Vehiculo] = []
    print("Creando 2 coches ...")
    try:
        lista_vehiculos.append(Coche("Focus", 2004))
        print("\tCoche creado ...")
        lista_vehiculos.append(Coche("Focus", 1800))
        print("\tCoche creado ...")
    except Exception as e:
        print(f"Error: {e}\n")

    print("Creando 2 motos ...")
    try:
        lista_vehiculos.append(Moto("Yamaha", 2024))
        print("\tMoto creada ...")
        lista_vehiculos.append(Moto("Sh 125", 2030))
        print("\tMoto creada ...")
    except Exception as e:
        print(f"Error: {e}\n")

    print("Crando 3 camiones")
    try:
        lista_vehiculos.append(Camion("MAN", 1995, 2))
        print("\tCamion creado ...")
        lista_vehiculos.append(Camion("SuperTruck", 2000, 0))
        print("\tCamion creado ...")
        lista_vehiculos.append(Camion("CamionErroneo", 1960, -1))
        print("\tCamion creado ...")
    except Exception as e:
        print(f"Error: {e}\n")

    for vehiculo in lista_vehiculos:
        print(f"{vehiculo}")

    print("\nConsumo de cada vehiculo a los 200km:")

    for vehiculo in lista_vehiculos:
        print(
            f"El consumo del {vehiculo.__class__.__name__} {vehiculo.modelo} es: {vehiculo.calcular_consumo(200):.2f}L")


main()
