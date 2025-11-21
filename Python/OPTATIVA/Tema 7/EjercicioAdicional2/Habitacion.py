from abc import ABC,abstractmethod

class Habitacion(ABC):
    @abstractmethod
    def __init__(self,precio:float,numero_noches:int):
        super().__init__()
        if numero_noches<=0:
            raise Exception("No se puede reservar 0 o menos noches")
        self.precio=precio
        self.numero_noches=numero_noches
        
    def __str__(self):
        return f"Tipo: {self.__class__.__name__}\t\tPrecio base: {self.precio}€\tNumero noches: {self.numero_noches}"