"""
Ejercicio 9 — Validación de contraseña
Define una función es_contrasena_valida(s: str) -> bool que verifique:
- longitud mínima 8
- al menos una mayúscula, una minúscula, un dígito y un símbolo de: !@#$%^&*?
- no puede contener espacios
Pide contraseñas hasta que el usuario introduzca una válida y muestra un mensaje de éxito.
Pista: recorre carácter por carácter y usa .isupper(), .islower() y .isdigit().
"""
def es_contrasena_valida(s: str) -> bool:
    simbolos = ["!","@","#","$","%","^","&","*"]
    valida=False
    if(len(s)>=8):
        if(s.__contains__(" ")== False):
            mayus=False
            minus=False
            simbol=False
            for letra in s:
                if(letra.islower()):
                    minus=True
                if(letra.isupper()):
                    mayus=True
                if(letra in simbolos):
                    simbol=True
            if(minus == True) and (mayus==True) and (simbol==True):
                valida=True

    return valida

def main():
    contraseña = input("Introduzca su contraseña: ")
    if es_contrasena_valida(contraseña)==True:
        print("La contraseña es valida")
    else:
        print("La contraseña no es valida")
main()