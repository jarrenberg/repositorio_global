from abc import ABC,abstractmethod
        
class Producto(ABC):
    __total_productos=0
    
    @abstractmethod
    def __init__(self,nombre:str,precio:float,descuento:float):
        super().__init__()
        if precio<=0:
            raise Exception("El precio no puede ser nulo o negativo")

        if descuento<0:
            raise Exception("El precio no puede ser negativo")
        
        self.nombre=nombre
        self.precio=precio
        self.descuento=descuento     
        Producto.__total_productos += 1

    @classmethod
    def get_total_productos(cls) -> int:
        return cls.__total_productos
