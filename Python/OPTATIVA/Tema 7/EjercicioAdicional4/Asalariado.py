from Empleado import Empleado


class Asalariado(Empleado):
    def __init__(self, nombre: str, salario: float):
        if (salario < 0):
            raise Exception(f'El sueldo no puede ser negativo')
        self._salario: float = self.calcular_salario(salario)
        super().__init__(nombre,self._salario)

    def calcular_salario(self, salario: float) -> float:
        return salario

    def __str__(self) -> str:
        return f'{super().__str__()}\tSalario: {self._salario}'
