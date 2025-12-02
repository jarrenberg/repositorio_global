import re


class Persona():
    __edad_maxima = 130

    def __init__(self, nombre: str, apellidos: str, dni: str, edad: int) -> None:
        if edad < 0 or edad > Persona.__edad_maxima:
            raise Exception(
                f'La edad no puede ser negativa o mayor a {Persona.__edad_maxima}')
        if len(nombre) == 0 or nombre == None:
            raise Exception(f'El nombre no puede estar vacio')

        if len(apellidos) == 0 or apellidos == None:
            raise Exception(f'El apellido no puede estar vacio')

        self.nombre = nombre
        self.apellidos = apellidos
        self.dni = dni
        self.edad = edad

        def __comprobar_dni(dni: str) -> bool:
            valido: bool = False
            dni_regex = r"^\d{8}[A-Z]$"
            if re.match(dni_regex, dni):
                valido = True
            return valido
