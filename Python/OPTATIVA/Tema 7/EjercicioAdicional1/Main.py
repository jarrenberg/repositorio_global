from Producto import Producto
from Comida import Comida
from Electronico import Electronico
from Ropa import Ropa

def main():
    lista_productos = []
    
    ## Añadiendo comida a la lista
    try:
        lista_productos.append(Comida("Fideos",1))
        print("Comida añadida ...")
        lista_productos.append(Comida("Pipas",1.5))
        print("Comida añadida ...")
        lista_productos.append(Comida("Tomates",-0.5))
        print("Comida añadida ...")
    except Exception as e:
        print("Error: ",e,"\n")
        
    ## Añadiendo electronicos a la lista
    try:
        lista_productos.append(Electronico("Iphone",1000,0.07))
        print("Electronico añadido ...")        
        lista_productos.append(Electronico("Raton",10,0.05))
        print("Electronico añadido ...")     
        lista_productos.append(Electronico("Televisor",500,1))
        print("Electronico añadido ...")     
    except Exception as e:
        print("Error: ",e,"\n")
        
    ## Añadiendo ropa a la lista
    try:
        lista_productos.append(Ropa("Pantalon",20,.1))
        print("Ropa añadida ...")
        lista_productos.append(Ropa("Camiseta",10,.15))
        print("Ropa añadida ...")
        lista_productos.append(Ropa("Cazadora",50,2))
        print("Ropa añadida ...")
    except Exception as e:
        print("Error: ",e,"\n")

    print("Mostrando todos los productos en la lista ...\n")
    for producto in lista_productos:
        print(producto)

    print(f"\nTotal de productos creados: {Producto.get_total_productos()}")
main()