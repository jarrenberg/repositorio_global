from Habitacion import Habitacion
class Suite(Habitacion):
    __precio_base=150
    def __init__(self,numero_noches:float):
        super().__init__(self.__precio_base,numero_noches)
        self.descuento_larga_estancia=False
        if(numero_noches>3):
            self.descuento_larga_estancia=True

        
    def __str__(self):
        return f"{super().__str__()}\tLarga estancia: {self.descuento_larga_estancia}\tPrecio Final: {self.precio_final()}€"
    
    def precio_final(self)->float:
        precio_final = self.precio*self.numero_noches
        if self.descuento_larga_estancia is True:
            precio_final=(self.precio*self.numero_noches)*0.9
        return precio_final
            