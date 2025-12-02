from abc import ABC, abstractmethod
from typing import Literal


class Empleado (ABC):
    __id_generator = 0

    def __init__(self, nombre: str,sueldo:float):
        self._nombre: str = nombre
        self._sueldo: float = sueldo
        self._id: int = Empleado.__id_generator
        Empleado.__id_generator = Empleado.__id_generator+1

    def calcular_salario(self) -> Literal[0]:
        return 0

    def __str__(self) -> str:
        return f"ID: {self._id}\tNombre: {self._nombre}"
