"""
Ejercicio 3 — Contador de vocales
Define una función contar_vocales(cadena: str) -> dict que devuelva un diccionario con el
número
de apariciones de cada vocal (a, e, i, o, u) en la cadena (sin diferenciar
mayúsculas/minúsculas).
Pide al usuario una frase y muestra el conteo ordenado de mayor a menor frecuencia.
"""
def contar_vocales(cadena)->dict:
     contador_a=0
     contador_e=0
     contador_i=0
     contador_o=0
     contador_u=0
     res = {"contador_a":contador_a,"contador_e":contador_e,"contador_i":contador_i,"contador_o":contador_o,"contador_u":contador_u}
     
     for letra in (cadena):
          if(letra=="a"):
               contador_a=contador_a+1
               res["contador_a"]=contador_a 
          if(letra=="e"):
               contador_e=contador_e+1
               res["contador_e"]=contador_e
          if(letra=="i"):
               contador_i=contador_i+1
               res["contador_i"]=contador_i
          if(letra=="o"):
               contador_o=contador_o+1
               res["contador_o"]=contador_o 
          if(letra=="u"):
               contador_u=contador_u+1
               res["contador_u"]=contador_u
     return res     

def main():
     cadena = input("Introduzca una cadena de texto: ")
     cadena_minus=""
     for letra in (cadena):
          if letra.isalpha() is True:
               cadena_minus=cadena_minus+letra.lower()
     res = contar_vocales(cadena_minus)
     print(f'Veces que sale la letra a: {res["contador_a"]}')
     print(f'Veces que sale la letra e: {res["contador_e"]}')
     print(f'Veces que sale la letra i: {res["contador_i"]}')
     print(f'Veces que sale la letra o: {res["contador_o"]}')
     print(f'Veces que sale la letra u: {res["contador_u"]}')      
     
main()
