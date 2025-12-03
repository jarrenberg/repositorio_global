from abc import ABC, abstractmethod


class Material(ABC):
    anyo_minimo: int = -5000
    lista_id: list[int] =[]

    def __init__(self, id: int, titulo: str, autor: str, anyo_publicacion: int) -> None:
        super().__init__()
        if id in Material.lista_id:
            raise Exception(
                f'No se puede crear un objeto con dicho id {id} porque ya existe uno con dicho identificador')
        if anyo_publicacion < Material.anyo_minimo:
            raise Exception(
                f'No existe un material publicado antes del {(Material.anyo_minimo)*(-1)} A.C')
        self.id: int = id
        self.titulo: str = titulo
        self.autor: str = autor
        self.anyo_publicacion: int = anyo_publicacion
        Material.lista_id.append(id)

    def __str__(self) -> str:
        return f'ID: {self.id}\t\tTitulo: {self.titulo}\t\t\tAutor: {self.autor}\t\t\tAño de publicacion: {self.anyo_publicacion}'
