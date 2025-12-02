from Empleado import Empleado


class Comisionista(Empleado):
    __porcentaje_minimo = 0
    __porcentaje_maximo = 1

    def __init__(self, nombre: str, salario: float, porcentaje_ventas: float):
        if (porcentaje_ventas < self.__porcentaje_minimo or porcentaje_ventas > self.__porcentaje_maximo):
            raise Exception(
                f'El porcentaje de ventas minimo es {self.__porcentaje_minimo} y el maximo es {self.__porcentaje_maximo}')
        self._salario: float = self.calcular_salario(
            salario, porcentaje_ventas)
        super().__init__(nombre,self._salario)

    def calcular_salario(self, salario: float, porcentaje_ventas: float) -> float:
        return salario + (salario*porcentaje_ventas)

    def __str__(self) -> str:
        return f'{super().__str__()}\tSalario: {self._salario}'
