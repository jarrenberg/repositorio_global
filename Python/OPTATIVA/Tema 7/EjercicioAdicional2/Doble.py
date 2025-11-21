from Habitacion import Habitacion
class Doble(Habitacion):
    __precio_base=75
    def __init__(self,numero_noches:float,suplemento_desayuno:bool):
        super().__init__(self.__precio_base,numero_noches)
        self.suplemento_desayuno=suplemento_desayuno
        
    def __str__(self):
        return f"{super().__str__()}\tSuplemento Desayuno: {self.suplemento_desayuno}\tPrecio final: {self.precio_final()}€"
    
    def precio_final(self)->float:
        precio_final = self.precio*self.numero_noches
        if self.suplemento_desayuno is True:
            precio_final=(self.precio+10)*self.numero_noches
        return precio_final
            