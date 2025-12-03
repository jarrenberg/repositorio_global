from Material import Material
from Revista import Revista
from Libro import Libro


def main():
    salir = False
    # Diccionario que simulara la biblioteca y donde se almacenaran los materiales
    biblioteca: dict[int, Material] = {}

    # Creamos objetos y los añadimos a la biblioteca para hacer mas facil el debugging
    print('AÑADIENDO OBJETOS DE PRUEBA ...')
    try:
        print("\tAÑADIENDO LIBRO ...")
        biblioteca[0] = Libro(0, 'Cumbres borrascosas',
                              'Jorge', 1950, 'No ficcion', 100)
        print("\tAÑADIENDO LIBRO ...")
        biblioteca[1] = Libro(1, 'El padrino',
                              'Mario Puzo', 1960, 'Ficcion', 200)
        print("\tAÑADIENDO REVISTA ...")

        biblioteca[2] = Revista(2, 'Muy interesante',
                                'Anonimo', 2024, 12, 'enero')
        print("\tAÑADIENDO REVISTA ...")

        biblioteca[3] = Revista(3, 'Natioinal Geographic',
                                'Anonimo', 2025, 1, 'MARZo')
    except Exception as e:
        print(f'Error: {e}')

    # Bucle que se repite mostranod el menu y sus opciones hasta que se escribe la letra f
    while salir == False:
        print('\n\n*** MENU ***')
        print('a) Agregar material')
        print('b) Listar materiales')
        print('c) Buscar material por ID')
        print('d) Eliminar material')
        print('e) Generar estadisticas')
        print('f) Salir')

        # Pedimos al usuario que introduzca una accion, la guardamos en una variable y la convertimos a minuscula
        accion = input('\nIntroduzca la accion: ')
        if accion.isalpha() is True:
            accion.lower()

        # Dependiendo de si se cumple la condicion tomaremos un camimo u otro en el match
        match accion:
            # Pedimos al usuario que nos indique si quiere introudcir un libro o una revista
            # guardamos los atributos correspondientes y creamos un objeto del tipo indicado
            # Luego lo guardamos en el diccionario que simula la bibliotecas
            case n if accion == 'a':
                tipo = pedir_entero_rango(
                    'Introduzca el tipo que quiere insertar (0:Libro || 1:Revista): ', 0, 1)
                id = pedir_entero('Introduzca el id: ')
                titulo = input("Introduzca el titulo: ")
                autor = input('Introduzca el autor: ')
                anyo_publicacion = pedir_entero(
                    "Introduzca el año de publicacion: ")
                if tipo == 0:
                    genero = input("Introduzca el genero: ")
                    numero_paginas = pedir_entero(
                        "Introduzca el numero de paginas: ")
                    try:
                        libro = Libro(id, titulo, autor,
                                      anyo_publicacion, genero, numero_paginas)
                        biblioteca[libro.id] = libro
                        print("Insertado!")
                    except Exception as e:
                        print(f'Error: {e}')
                if tipo == 1:
                    numero_edicion = pedir_entero(
                        "Introduzca el numero de edicion: ")
                    mes_de_publicacion = input(
                        "Introduzca el mes de publicacion: ")
                    try:
                        revista = Revista(id, titulo, autor,
                                          anyo_publicacion, numero_edicion, mes_de_publicacion)
                        biblioteca[revista.id] = revista
                        print("Insertado!")
                    except Exception as e:
                        print(f'Error: {e}')

            # Recorremos los valores del diccionario que simula la biblioteca y mostramos su informacion
            case n if accion == 'b':
                print("\nListado de materiales:")
                for value in biblioteca.values():
                    print(f'\t{value.__str__()}')

            # Pedimos el id del material y comprobamos si existe en la biblioteca
            # En caso de ser afirmativo mostramos su informacion y en caso de ser negativo mostramos que no existe ningun material con dicho id
            case n if accion == 'c':
                id: int = pedir_entero("Introduzca el id del material: ")
                existe = False
                for key, value in biblioteca.items():
                    if key == id:
                        existe = True
                        print(f'\t{value.__str__()}')
                if existe is False:
                    print(f"No existe un material con el id ({id})")

            # Pedimos el id del material y comprobamos si existe en la biblioteca
            # En caso de ser afirmativo eliminamos el material de la biblioteca y la lista con todas las ids de los materiales
            # En caso de ser negativo mostramos que no existe ningun material con dicho id
            case n if accion == 'd':
                id: int = pedir_entero(
                    "Introduzca el id del material a eliminar: ")
                existe = False
                for key in biblioteca.keys():
                    if key == id:
                        existe = True
                if existe is True:
                    biblioteca.pop(id)
                    Material.lista_id.remove(id)
                    print("Eliminado!")
                else:
                    print(
                        f'No se ha podido eliminar el objeto con la id ({id}) porque no existe ningun material con dicho identificador')

            # Llamamos al metodo que nos muestra las estadisticas de la biblioteca y se la pasamos como parametro
            case n if accion == 'e':
                generar_estadisticas(biblioteca)

            # Cambiamos el valor de la variable salir a True para salir del bucle y mostramos que el programa ha finalizado
            case n if accion == 'f':
                salir = True
                print("PROGRAMA FINALIZADO")

            # Mensaje que salta cuando no se introduce una opcion valida (caso default)
            case _:
                print("No se ha introducido una accion valida")

# Metodo que devuelve un entero entre el parametro minimo y el maximo


def pedir_entero_rango(mensaje: str, min: int, max: int) -> int:
    valido = False
    while valido == False:
        try:
            entero = int(input(mensaje))
            if entero <= max and entero >= min:
                valido = True
        except ValueError:
            print("No se ha introducido entero valido")
    return entero

# Metodo que devuelve un entero


def pedir_entero(mensaje: str) -> int:
    valido = False
    while valido == False:
        try:
            entero = int(input(mensaje))
            valido = True
        except ValueError:
            print("No se ha introducido entero valido")
    return entero

# Metodo que muestra las estadisticas del diccionario pasado como parametro


def generar_estadisticas(biblioteca: dict[int, Material]):
    objetos_registrados: int = len(biblioteca)
    libros_registrados = 0
    revistas_registradas = 0
    paginas_totales = 0
    media_paginas = 0.0
    for value in biblioteca.values():
        if isinstance(value, Libro):
            libros_registrados = libros_registrados+1
            paginas_totales = paginas_totales+value.numero_paginas
        if isinstance(value, Revista):
            revistas_registradas = revistas_registradas+1
    if libros_registrados != 0:
        media_paginas: float = paginas_totales/libros_registrados

    print("\nEstadisticas:")
    print(f"\tTotal de materiales registrados: {objetos_registrados}")
    print(f"\t\tTotal de libros registrados: {libros_registrados}")
    print(f"\t\tTotal de revistas registradas: {revistas_registradas}")
    print(f"\tPromedio de paginas en los libros: {media_paginas:.2f}")


main()
