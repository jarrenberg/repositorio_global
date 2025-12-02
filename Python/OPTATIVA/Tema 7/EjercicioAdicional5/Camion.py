from Vehiculo import Vehiculo

class Camion(Vehiculo):
    __carga_minima=0
    def __init__(self, matricula: str, modelo: str, ocupado: bool|None,carga_maxima:int) -> None:
        super().__init__(matricula, modelo, ocupado)
        if carga_maxima<=Camion.__carga_minima:
            raise Exception(f"La carga minima debe ser mayor a 0")
        self.carga_maxima=carga_maxima
    
    def __str__(self) -> str:
        return f'{super().__str__()}\tCarga Maxima: {self.carga_maxima}'