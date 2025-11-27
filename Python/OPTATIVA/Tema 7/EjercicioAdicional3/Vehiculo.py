from abc import ABC,abstractmethod
from datetime import datetime

class Vehiculo(ABC):
    __anyo_maximo=datetime.now().year
    __anyo_minimo=1900
    __consumo_minimo=0
    @abstractmethod
    def __init__(self,modelo:str,anyo:int,consumo_cada_100km:float):
        super().__init__()
        if anyo<self.__anyo_minimo or anyo>self.__anyo_maximo:
            raise Exception(f"El año no puede ser superior a {self.__anyo_maximo} o inferior a {self.__anyo_minimo}")
        if consumo_cada_100km <self.__consumo_minimo:
            raise Exception(f"El consumo cada 100KM no puede ser negativo o 0")
        self.modelo=modelo
        self.anyo=anyo
        self.consumo_cada_100km=consumo_cada_100km
        
    def __str__(self):
        return f"Tipo: {self.__class__.__name__}\t\tModelo: {self.modelo}\tAño: {self.anyo}\tConsumo cada 100KM: {self.consumo_cada_100km}"
    
    def calcular_consumo(self,kilometros:float)->float:
        return 0