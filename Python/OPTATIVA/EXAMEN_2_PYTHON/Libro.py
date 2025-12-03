from Material import Material


class Libro(Material):

    __generos: list[str] = ['Ficcion', 'No Ficcion', 'Terror', 'Ciencia']
    __minimo_numero_paginas = 1

    def __init__(self, id: int, titulo: str, autor: str, anyo_publicacion: int, genero: str, numero_paginas: int) -> None:
        if numero_paginas < Libro.__minimo_numero_paginas:
            raise Exception(
                f'No puede haber un libro con un numero de paginas menor a {Libro.__minimo_numero_paginas}')
        if Libro.comprobar_genero(genero) is False:
            raise Exception(
                f'El genero introducido ({genero}) no es valido')
        self.numero_paginas: int = numero_paginas
        self.genero: str = genero.capitalize()
        super().__init__(id, titulo, autor, anyo_publicacion)

    @staticmethod
    def comprobar_genero(genero: str) -> bool:
        valido = False
        for item in Libro.__generos:
            if item.lower() == genero.lower():
                valido = True
        return valido

    def __str__(self) -> str:
        return f'Tipo: {self.__class__.__name__}\t{super().__str__()}\t\tGenero: {self.genero}\t\tNumero de paginas: {self.numero_paginas}'
