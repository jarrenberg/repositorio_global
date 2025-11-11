'''1. Crea una clase Persona para guardar nombre, dirección y teléfono de una
persona.'''
import re

class Persona:
    __nombre:str
    __direccion:str
    __telefono:str
    
    def __init__(self,nombre:str,direccion:str,telefono:str):
        self.__nombre=nombre
        self.__direccion=direccion
        self.__telefono=telefono
        
    @property
    def nombre(self):
        return self.__nombre    

    @property
    def direccion(self):
        return self.__direccion
    
    @property
    def telefono(self):
        return self.__telefono
    
    @nombre.setter
    def nombre(self,nombre:str):
        self.__nombre=nombre
    
    @direccion.setter
    def direccion (self,direccion:str):
        self.__direccion=direccion
    
    @telefono.setter
    def telefono (self,telefono:str):
        self.__telefono=telefono
    
    def __str__(self):
        return f'Nombre: {self.__nombre}\t\tDireccion: {self.__direccion}\t\tTelefono: {self.__telefono}'