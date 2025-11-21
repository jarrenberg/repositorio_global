import socket
import informacion

ip = informacion.getIp(r'Ejercicio1\datos_cliente.txt')
puerto = int(informacion.getPuerto(r'Ejercicio1\datos_cliente.txt'))

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

client_socket.connect((ip,puerto))

message = client_socket.recv(1024).decode('utf-8')
print(message)

name = input("Escribe tu nombre: ")
client_socket.sendall(name.encode('utf-8'))

greeting = client_socket.recv(1024).decode('utf-8')
print(greeting)

client_socket.close()
