def menu():
    print("MENU DE OPCIONES\n")
    salir = False
    agenda = dict(Jorge='1',Raquel='123456789',Antonio='987654321')

    while(salir == False):
        print("\na) Listado de teléfonos, usando el orden por defecto.\nb) Listado de teléfonos por orden alfabético.\nc) Añadir un nuevo contacto.\nd) Modificar el teléfono de un contacto.\ne) Buscar un número de teléfono.\nf) Eliminar un contacto.\ng) Borrar el listín telefónico.\nh) Salir\n")
        acc = accion()
        match acc:
            case n if acc=="a":
                mostrarAgenda(agenda)
                
            case n if acc=="b":
                mostrarAgendaOrdenada(agenda)

            case n if acc== "c":
                numero=input("Introduzca el numero del contacto que quiere añadir: ")
                nombre=input("Introduca el nombre del contacto que quiere añadir: ")
                if(anyadirContacto(nombre,numero,agenda) is True):
                    print("Se ha añadido el contacto")
                else:
                    print("No se ha podido añadir el contacto")

            case n if acc=="d":
                nombre=input("Introduzca el nombre del contacto que quiere actualizar: ")
                numero=input("Introduzca el numero de telefono nuevo: ")
                if(actualizarContacto(nombre,numero,agenda) is True):
                    print("Se ha actualizado el contacto")
                else:
                    print("No se ha podido actualizar el contacto")

            case n if acc=="e":
                numero=input("Introduzca un numero de telefono: ")
                nombre=buscarContactoPorNumero(numero,agenda)
                if buscarContactoPorNumero==None:
                    print("No se ha encontrado un contacto con ese numero")
                else:
                    print(f"El contacto con ese numero es: {nombre}")  
            case n if acc=="f":
                nombre=input("Introduzca el nombre del contacto que desea eliminar: ")
                if(borrarContacto(nombre,agenda)):
                    print("Se ha eliminado el contacto con exito")
                else:
                    print("No se ha podido eliminar el contacto")

            case n if acc=="g":
                if borrarAgenda(agenda) is True:
                    print("Se ha eliminado la agenda")
                else:
                    print("No se ha podido eliminar la agenda")

            case n if acc=="h":
                print("Saliendo ...")
                salir = True;

            case _:
                print("No se ha introducido una accion valida")
            
def mostrarAgenda(agenda:dict):
    for key,value in agenda.items():
        print(f'Nombre: {key} - Numero: {value}')
        
def mostrarAgendaOrdenada(agenda:dict):
    lista = []
    agenda_ordenada = {}
    for key in agenda.keys():
        lista.append(key)
    lista.sort()
    for elemento in lista:
        agenda_ordenada[elemento] = agenda[elemento]
    for key,value in agenda_ordenada.items():
        print(f'Nombre: {key} - Numero: {value}')

def anyadirContacto(nombre:str,numero:str,agenda:dict)->bool:
    introducido = True
    if nombre in agenda.keys() and numero in agenda.values():
        introducido = False
    else:
        agenda.update({nombre:numero})
    return introducido

def actualizarContacto(nombre:str,numero:str,agenda:dict)->bool:
    actualizado=False
    if nombre in agenda.keys():
        agenda[nombre]=numero
        actualizado=True
    return actualizado

def buscarContactoPorNumero(numero:str, agenda:dict)->str|None:
    resultado = None
    for key, value in agenda.items():
        if value == numero:
            resultado= key
    return resultado

def borrarContacto(nombre:str,agenda:dict)->bool:
    eliminado= False
    if nombre in agenda.keys():
        agenda.pop(nombre)
        eliminado=True
    return eliminado

def borrarAgenda(agenda:dict)-> bool:
    agenda.clear()
    borrada=False
    if(len(agenda)==0):
        borrada=True
    return borrada

def accion()->str:
    letra=False       
    while(letra is False):
        accion = input("Introduzca la accion a realizar: ")
        if accion.isalpha() and len(accion) == 1:
            letra=True
            accion=accion.lower()
    return accion
    
def main():
    menu()
    
    
main()