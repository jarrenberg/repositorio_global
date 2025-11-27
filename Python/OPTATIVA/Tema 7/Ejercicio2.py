from Ejercicio1 import Persona

def menu(agenda:dict):
    salir:bool=False
    while(salir is False):
        print("MENU DE OPCIONES")
        print("a) Listado de contactos por orden alfabético.\nb) Añadir un nuevo contacto.\nc) Modificar un contacto.\nd) Buscar un número de teléfono.\ne) Eliminar un contacto.\nf) Salir")
        accion = input()
        accion = accion.lower()
        match accion:
            case n if accion=="a":
                print(agendaPorOrdenAlfabetico(agenda))
            case n if accion=="b":
                nombre=input("Introduzca el nombre del contacto: ")
                direccion = input("Introduzca la direccion del contacto: ")
                telefono = input("Introduzca el telefono del contacto: ")
                p=Persona(nombre,direccion,telefono)
                if anyadirContacto(p,agenda) is True:
                    print("Se ha insertado el contacto")
                else:
                    print("No se ha insertado el contacto")
                    
            case n if accion=="c":
                nombreContacto=input("Introduzca el nombre del contacto el cual le gustaria actualizar: ")
                if modificarContacto(nombreContacto,agenda) is True:
                    print(f"Se ha modificado el contacto con el nombre de contacto: {nombreContacto}")
                else:
                    print(f"No se ha modificado ningun contacto con el nombre de contacto: {nombreContacto}")
                
            case n if accion=="d":
                telefono=input("Introduzca un numero de telefono: ")
                if buscarPorNumeroDeTelefono(telefono,agenda) == None:
                    print("No existe un contacto asignado a dicho numero de telefono")
                else:
                    print(f"El contacto asignado al numero de telefono {telefono} es: {buscarPorNumeroDeTelefono(telefono,agenda)}")
            case n if accion=="e":
                nombreContacto=input("Introduzca el nombre del contacto que te gustaria eliminar: ")
                if eliminarContacto(nombreContacto,agenda) is False:
                    print(f"No se ha podido eliminar al contacto {nombreContacto.upper()}")
                else:
                    print(f"Se ha eliminado al contacto {nombreContacto.upper()}")
                    
            case n if accion=="f":
                print("Saliendo ...")
                salir=True
            case _:
                print("No has introducido una accion correcta")
  
def agendaPorOrdenAlfabetico(agenda:dict)->str:
    resultado:str=""
    contenedor:dict= agenda.copy()
    c = dict(sorted(contenedor.items()))
    for key,value in c.items():
        resultado=f"{resultado}Nombre:{key}\tPersona: {value}\n"
    return resultado
         
def anyadirContacto(p:Persona,agenda:dict)->bool:
    insertado=False
    tamanyo_inicial=len(agenda)
    agenda[p.nombre.upper()]=p
    tamanyo_final=len(agenda)
    if tamanyo_final>tamanyo_inicial:
        insertado=True
    return insertado

def modificarContacto(nombreContacto:str,agenda:dict)->bool:
    modificado=False
    for key,value in agenda.items():
        if key==nombreContacto.upper():
            nombre=input("Introduzca el nuevo nombre del contacto: ")
            direccion = input("Introduzca la nueva direccion del contacto: ")
            telefono = input("Introduzca el nuevo telefono del contacto: ")  
            value.nombre=nombre
            value.direccion=direccion
            value.telefono=telefono
            modificado=True
    return modificado

def buscarPorNumeroDeTelefono(telefono:str,agenda:dict)->str|None:
    resultado=None
    for key,value in agenda.items():
        if value.telefono == telefono:
            resultado=key
    return resultado

def eliminarContacto(nombre:str,agenda:dict)->bool:
    eliminado=False
    try:
        value=agenda.pop(nombre.upper())
        eliminado=True
    except:
        eliminado=False
    return eliminado
    
def main():
    agenda:dict[str,Persona]={"JORGE":Persona("jorge","calle cnbl","622024489"),"RAQUEL":Persona("raque","calle psoe","123456789")}
    menu(agenda)
    
    
    
main()