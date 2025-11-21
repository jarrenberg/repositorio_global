# 8. Escribe un programa que recoja un mes del año (en número) y devuelva el número de días que tiene el mes. En caso de indicar un mes incorrecto deberá mostrar un mensaje de error.
mes = input("Introduce el mes:")
match mes:
    case n if mes=="Enero" or mes == "Marzo" or mes == "Mayo" or mes == "Julio" or mes == "Agosto" or mes == "Octubre" or mes == "Diciembre":
        print("El mes tiene 31 dias")
    case n if mes=="Febrero":
        print("El mes tiene 28 dias")
    case n if mes=="Abril" or mes == "Junio" or mes == "Septiembre" or mes == "Noviembre":
        print("El mes tiene 30 dias")
    case _:
        print("El mes no es valido")



