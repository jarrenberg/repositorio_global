#2. Escribe un programa que recoja un número por teclado y muestre el día de la
#semana que es (1 = Lunes, 2 = Martes...). En caso de introducir un número
#incorrecto, mostrará el mensaje “Día de la semana incorrecto”.
dia = int(input("Introduzca el dia de la semana: "))
if dia == 1:
    print("Hoy es Lunes")
elif  dia ==2:
    print("Hoy es Martes")
elif  dia ==3:
    print("Hoy es Miercoles")
elif  dia ==4:
    print("Hoy es Jueves")
elif  dia ==5:
    print("Hoy es Viernes")
elif  dia ==6:
    print("Hoy es Sabado")
elif  dia ==7:
    print("Hoy es Domingo")
else:
    print("Dia de la semana incorrecto")