from Vehiculo import Vehiculo

class Moto(Vehiculo):
    __consumo:3
    
    def __init__(self, modelo:str, anyo:float):
        super().__init__(modelo, anyo, self.__consumo)
            
    def calcular_consumo(self,kilometros:float)->float:
        return (self.consumo_cada_100km/100)*kilometros
    
    def __str__(self):
        return f"{super().__str__()}"