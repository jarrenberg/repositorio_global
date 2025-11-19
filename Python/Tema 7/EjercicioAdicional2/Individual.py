from Habitacion import Habitacion

class Individual(Habitacion):
    __precio_base=50
    def __init__(self,numero_noches:float):
        super().__init__(self.__precio_base,numero_noches)
    
    def precio_final(self):
        return self.precio*self.numero_noches   
     
    def __str__(self):
        return f"{super().__str__()}\tPrecio final: {self.precio_final()}€"