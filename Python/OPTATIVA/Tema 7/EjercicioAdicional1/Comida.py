from Producto import Producto

class Comida (Producto):
    def __init__(self,nombre:str,precio:float):
        super().__init__(nombre,precio,0)
    
    def precio_final(self)->float:
        return self.precio - (self.precio*self.descuento)

    def __str__(self):
        return f"Producto: {self.__class__.__name__}\tPrecio: {self.precio}\tDescuento: {self.descuento}\t\tPrecio final: {self.precio_final()}"