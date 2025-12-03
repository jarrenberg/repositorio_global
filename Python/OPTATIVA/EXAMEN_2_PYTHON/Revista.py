from Material import Material


class Revista(Material):

    __meses: list[str] = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                          'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
    __numero_edicion_minimo = 1

    def __init__(self, id: int, titulo: str, autor: str, anyo_publicacion: int, numero_edicion: int, mes_de_publicacion: str) -> None:
        if numero_edicion < Revista.__numero_edicion_minimo:
            raise Exception(
                f'No puede haber una revista con un numero de edicion menor a {Revista.__numero_edicion_minimo}')
        if Revista.comprobar_mes(mes_de_publicacion) is False:
            raise Exception(
                f'El mes introducido ({mes_de_publicacion}) no es valido')
        self.numero_edicion: int = numero_edicion
        self.mes_de_publicacion: str = mes_de_publicacion.capitalize()
        super().__init__(id, titulo, autor, anyo_publicacion)

    @staticmethod
    def comprobar_mes(mes: str) -> bool:
        valido = False
        for item in Revista.__meses:
            if item.lower() == mes.lower():
                valido = True
        return valido

    def __str__(self) -> str:
        return f'Tipo: {self.__class__.__name__}\t{super().__str__()}\t\tNumero de edicion: {self.numero_edicion}\t\tMes de publicacion: {self.mes_de_publicacion}'
