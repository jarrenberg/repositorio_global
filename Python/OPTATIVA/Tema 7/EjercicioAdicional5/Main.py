from Vehiculo import Vehiculo
from Auto import Auto
from Camion import Camion


def main():
    empresa: dict[str, Vehiculo] = {}
    a = Auto('1234 BCD', "Focus", True, 2)
    b = Auto('3478 QWR', "Ibixa", False, 5)
    c = Camion('6547 PTR', 'MAN', False, 200)
    d = Camion('7541 MBN', 'Volvo', False, 2)
    empresa[a.matricula] = a
    empresa[b.matricula] = b
    empresa[c.matricula] = c
    empresa[d.matricula] = d

    salir = False
    while salir is False:
        print("\nMENU")
        print("a) Añadir Vehiculo")
        print("b) Consulatar datos por matricula")
        print("c) Listas todos los vehiculos")
        print("d) Cambiar estado del un vehiculo")
        print("e) Eliminar vehiculo del sistema")
        print("f) Salir")

        accion = input("\nIntroduzca una accion: ")
        match (accion):
            case n if accion == 'a':
                tipo = input(
                    "Introduzca el tipo de vehiculo a introducir (Auto/Camion): ")
                tipo = tipo.lower()
                matricula = input("Introduzca la matricula del vehiculo: ")
                modelo: str = input("Introduzca el modelo del vehiculo: ")
                estado: str = input(
                    "Introduzca el estado del vehiculo (disponible/en servicio): ")
                ocupado = None
                if estado.lower() == 'disponible':
                    ocupado = False
                if estado.lower() == 'en servicio':
                    ocupado = True
                match (tipo):
                    case n if tipo == 'auto':
                        numero_asientos = pedir_entero(
                            "Introduzca el numero de asientos del auto: ")
                        try:
                            auto = Auto(matricula, modelo,
                                        ocupado, numero_asientos)
                            empresa[auto.matricula]=auto
                        except Exception as e:
                            print(f"Error: {e}")
                    case n if tipo == 'camion':
                        carga_maxima = pedir_entero(
                            "Introduzca la carga maxima del camion: ")
                        try:
                            camion = Camion(matricula, modelo,
                                            ocupado, carga_maxima)
                            empresa[camion.matricula]=camion
                        except Exception as e:
                            print(f"Error: {e}")
                    case _:
                        print("No se ha introducido un tipo valido")

            case n if accion == 'b':
                matricula = input("Introduzca la matricula del vehiculo: ")
                existe: bool = False
                for key, value in empresa.items():
                    if key == matricula:
                        existe = True
                        print(value.__str__())
                if existe is False:
                    print("No existe un vehiculo con dicha matricula")
            case n if accion == 'c':
                for value in empresa.values():
                    print(value.__str__())

            case n if accion == 'd':
                matricula = input("Introduzca la matricula del vehiculo: ")
                existe: bool = False
                for key, value in empresa.items():
                    if key == matricula:
                        existe = True
                        if value.ocupado is True:
                            value.ocupado = False
                        else:
                            value.ocupado = True
                        print("Estado cambiado!")
                if existe is False:
                    print("No se puede cambiar el estado del vehiculo porque no existe")

            case n if accion == 'e':
                matricula = input("Introduzca la matricula del vehiculo: ")
                existe: bool = False
                for key, value in empresa.items():
                    if key == matricula:
                        existe = True
                if existe is False:
                    print("No se puede eliminar el vehiculo porque no existe")
                else:
                    empresa.pop(key)
                    print("Vehiculo eliminado")
            case n if accion == 'f':
                print("SALIENDO ...")
                salir = True
            case _:
                print("No se ha introducido una accion valida")


def pedir_entero(mensaje: str) -> int:
    valido = False
    while valido == False:
        try:
            entero = int(input(mensaje))
            valido = True
        except ValueError:
            print("No se ha introducido entero valido")
    return entero


main()
