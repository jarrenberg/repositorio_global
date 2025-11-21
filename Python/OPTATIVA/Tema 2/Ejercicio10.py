# 10.Escribe un programa que a partir de información de un donante determine si puede donar sangre. Las condiciones para donar son:
# a. No se debe donar en ayunas.
# b. Edad: Comprendida entre los 18 y 65 años.
# c. Peso: Superior a 50kg.
# d. Tensión arterial: dentro de límites adecuados para la extracción.
    # i. Tensión diastólica (baja): entre 50mm Hg y 100 mm Hg
    # ii. Tensión sistólica (alta): entre 90mm y 180mm Hg
# e. Pulso (frecuencia cardiaca): entre 50 y 110 pulsaciones
# f. Valores de hemoglobina:
    # i. En hombres: superior a 13,5 gramos por litro
    # ii. En mujeres: superior a 12,5 gramos por litro.
# g. Plaquetas: más de 150.000 cc
# h. Proteínas totales: más de 6 gr/dl.
ayunas=input("¿Se encuentra usted en ayunas (SI/NO)?: ")
edad= int(input("Introduzca su edad: "))
peso = float(input("Introduzca su peso en kg: "))
tensionDiastolica= int(input("Introduzca su tension diastolica: "))
tensionSistolica= int(input("Introduzca su tension sistolica: "))
pulso = int(input("Introduzca su pulso: "))
sexo = input("Introduzca su sexo (H/M): ")
hemoglobina = float(input("Introduzca el valor de su hemoglobina (g/L): "))
plaquetas = int(input("Introduzca el recuento de plaquetas: "))
proteinas = int(input("Introduzca su recuento de proteina en gr/dL: "))

'''
if (ayunas=="NO") and ((edad>=18)and(edad<=65)) and (peso>50)and(tensionDiastolica>50 and tensionDiastolica<100)and (tensionSistolica>90 and tensionSistolica<180) and (pulso>50 and pulso<110)and(sexo=="H" and hemoglobina>13.5) or (sexo=="M" and hemoglobina>12.5)and(plaquetas>150000)and (proteinas>6):
    print("Usted puede donar sangre")
else:
   print("Usted no puede donar sangre")
'''
if(ayunas=="NO"):
    if ((edad>=18)and(edad<=65)):
        if(peso>50):
            if(tensionDiastolica>50 and tensionDiastolica<100):
                if (tensionSistolica>90 and tensionSistolica<180):
                    if(pulso>50 and pulso<110):
                        if ((sexo=="H" and hemoglobina>13.5) or (sexo=="M" and hemoglobina>12.5)):
                            if (plaquetas>150000):
                                if(proteinas>6):
                                    print("Usted puede donar sangre")
                                else:
                                    print("Usted no puede donar sangre")
                            else:
                                print("Usted no puede donar sangre")
                        else:
                            print("Usted no puede donar sangre")
                    else:
                        print("Usted no puede donar sangre")
                else:
                    print("Usted no puede donar sangre")
            else:
                print("Usted no puede donar sangre")
        else:
            print("Usted no puede donar sangre")
    else:
        print("Usted no puede donar sangre")
else:
    print("Usted no puede donar sangre")
'''

'''