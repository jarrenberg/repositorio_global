import socket
import random
import informacion

ip = informacion.getIp(r'Ejercicio1\datos_server.txt')
puerto = int(informacion.getPuerto(r'Ejercicio1\datos_server.txt'))

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = (ip, puerto)
server_socket.bind(server_address)


server_socket.listen(1)
print(f"servidor escuchando en {server_address}")

while True:
    client_socket, client_address = server_socket.accept()
    print(f"conexión aceptada de {client_address}")
    response = "Buenas tardes"
    client_socket.sendall(response.encode('utf-8'))
    data = client_socket.recv(1024).decode('utf-8')
    print(f"Datos recibidos: {data}")

    client_socket.close()
