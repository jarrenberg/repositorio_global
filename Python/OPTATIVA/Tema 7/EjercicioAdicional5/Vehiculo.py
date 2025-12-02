from abc import abstractmethod,ABC

class Vehiculo(ABC):

    def __init__(self,matricula:str,modelo:str,ocupado:bool|None) -> None:
        super().__init__()
        if ocupado==None:
            raise Exception(f"Estado no valido")
        self.matricula=matricula
        self.modelo=modelo
        self.ocupado=ocupado

    def __str__(self) -> str:
        estado = "Si" if self.ocupado else "No"
        return f'Matricula: {self.matricula}\tModelo: {self.modelo}\t¿Esta ocupado?: {estado}'