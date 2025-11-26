import socket
import random
import informacion

ip = informacion.getIp(r'Ejercicio1\datos_server.txt')
puerto = int(informacion.getPuerto(r'Ejercicio1\datos_server.txt'))

socket_servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

direccion_servidor = (ip, puerto)
socket_servidor.bind(direccion_servidor)


socket_servidor.listen(1)
print(f"servidor escuchando en {direccion_servidor}")

while True:
    client_socket, client_address = socket_servidor.accept()
    print(f"conexión aceptada de {client_address}")
    response = "Buenas tardes"
    client_socket.sendall(response.encode('utf-8'))
    data = client_socket.recv(1024).decode('utf-8')
    print(f"Datos recibidos: {data}")
    client_socket.close()
