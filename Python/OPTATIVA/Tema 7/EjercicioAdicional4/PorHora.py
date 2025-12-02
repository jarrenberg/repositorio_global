from Empleado import Empleado

class PorHora(Empleado):
    __horas_maximas=160

    def __init__(self, nombre: str,precio_hora:float,horas:int):
        if(horas>self.__horas_maximas or horas<0):
            raise Exception (f'El numero de horas debe estar entre 0 y {self.__horas_maximas}')
        self._salario:float=self.calcular_salario(horas,precio_hora)
        super().__init__(nombre,self._salario)
    
    def calcular_salario(self,horas:int,precio_hora:float) -> float:
        return precio_hora*horas
    
    def __str__(self) -> str:
        return f'{super().__str__()}\tSalario: {self._salario}'