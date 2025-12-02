from Vehiculo import Vehiculo

class Auto(Vehiculo):
    __asientos_minimo=0
    def __init__(self, matricula: str, modelo: str, ocupado: bool|None,asientos:int) -> None:
        super().__init__(matricula, modelo, ocupado)
        if asientos<= Auto.__asientos_minimo:
            raise Exception(f"La carga minima debe ser mayor a 0")
        self.asientos=asientos
    
    def __str__(self) -> str:
        return f'{super().__str__()}\tNº asientos: {self.asientos}'