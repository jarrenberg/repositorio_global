from Producto import Producto

class Electronico (Producto):
    __descuento_minimo:float=0
    __descuento_maximo:float=0.1
    
    def __init__(self,nombre:str,precio:float,descuento:float):
        if descuento < 0 or descuento> .1:
            raise Exception(f"El descuento minimo es {self.__descuento_minimo} y el maximo {self.__descuento_maximo}")
        super().__init__(nombre,precio,descuento)
    
    
    def precio_final(self)->float:
        return self.precio - (self.precio*self.descuento)

    
    def __str__(self):
        return f"Producto: {self.__class__.__name__}\tPrecio: {self.precio}\tDescuento: {self.descuento}\t\tPrecio final: {self.precio_final()}"