#7. Escribe un programa que recoja la hora del día y devuelva un saludo, según las siguientes reglas:
hora = int(input("Introduzca la hora:"))
if hora>=7 and hora < 12:
    print("Buenos dias")
elif hora>=12 and hora<20:
    print("Buenas tardes")
elif(hora>=20 and hora <= 23)or(hora>=0 and hora<7):
    print("Buenas noches")
else:
    print("La hora introducida",hora,"no es valida")