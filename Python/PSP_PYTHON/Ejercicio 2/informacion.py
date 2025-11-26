import os

@staticmethod
def  getIp(filename:str)->str:
    resultado=""
    if os.path.exists(filename) is True:
        file=open(file=filename,mode='r')
        Lines = file.readlines()
        count = 0
        for line in Lines:
            count += 1
            if line.__contains__("ip"):
                parametros=line.split("=")
                c=parametros[1].split("\n")
                resultado=c[0]

    return resultado

@staticmethod
def  getPuerto(filename:str)->int:
    resultado=""
    if os.path.exists(filename) is True:
        file=open(file=filename,mode='r')
        Lines = file.readlines()
        count = 0
        for line in Lines:
            count += 1
            if line.__contains__("puerto"):
                parametros=line.split("=")
                c=parametros[1].split("\n")
                resultado=c[0]
    return int(resultado)