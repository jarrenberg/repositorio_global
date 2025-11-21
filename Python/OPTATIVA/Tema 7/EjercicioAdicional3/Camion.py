from Vehiculo import Vehiculo

class Camion(Vehiculo):
    __consumo=20
    __consumo_extra_por_tonelada=.2
    
    def __init__(self, modelo:str, anyo:float,toneladas:int):
        super().__init__(modelo, anyo, self.__consumo)
        if toneladas<0:
            raise Exception ("No puede existir un camion con carga negativa")
        self.toneladas=toneladas
            
    def calcular_consumo(self,kilometros:float)->float:
        return (((self.consumo_cada_100km/100) + ((self.consumo_cada_100km/100)*self.toneladas*self.__consumo_extra_por_tonelada))*kilometros)
    
    def __str__(self):
        return f"{super().__str__()}\tToneladas: {self.toneladas}"