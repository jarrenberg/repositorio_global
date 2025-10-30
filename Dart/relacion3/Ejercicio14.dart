/*14- Haremos lo mismo con las funciones aplicables a un Map: isEmpty, isNotEmpty,
containsKey, remove, putIfAbsent, y los atributos entries, values, keys, length. y el
siguiente Map inicial:
Map<String,String> grupo Whatsapp = { ‘Pepe’: ‘111111111’,
‘María’:’222222222’,
’Ana’:’3333333’}
1. incluir persona
2. eliminar persona
3. buscar persona
4. mostrar componentes grupo
5. salir*/
import 'dart:io';

void main(List<String> args) {
  menu();
}

void menu() {
  Map<String, String> grupoWhatsapp = {
    'Pepe': '1',
    'María': '222222222',
    'Ana': '3333333',
  };
  bool salir = false;
  while (!salir) {
    print("\n\n*** MENU PERSONAS ***");
    int accion = devolverEnteroValido(
      "1. incluir persona\n2. eliminar persona\n3. buscar persona\n4. mostrar lista de personas\n5. salir",
    );
    switch (accion) {
      case 1:
        int tamanyoInicial = grupoWhatsapp.length;
        stdout.write("Introduzca el nombre del nuevo contacto:");
        String nombre = stdin.readLineSync()!;
        stdout.write("Introduzca el telefono del nuevo contacto:");
        String telefono = stdin.readLineSync()!;
        grupoWhatsapp.addEntries({MapEntry(nombre, telefono)});
        if (grupoWhatsapp.length > tamanyoInicial) {
          print(
            "Se ha introducido el contacto Nombre: $nombre - Telefono: $telefono",
          );
        } else {
          print(
            "No se ha introducido el contacto Nombre: $nombre - Telefono: $telefono porque ya existe",
          );
        }
        break;
      case 2:
        int buscar = -1;
        while (buscar < 0 || buscar > 1) {
          buscar = devolverEnteroValido(
            "Introduzca 0 si quiere eliminar a una persona por su nombre o 1 si quiere eliminarla por su numero de telefono: ",
          );
        }
        if (buscar == 0) {
          stdout.write("Introduzca el nombre del contacto:");
          String nombre = stdin.readLineSync()!;
          if (buscarContactoPorNombre(grupoWhatsapp, nombre) == null) {
            print(
              "El nombre $nombre no tiene asignado ningun numero de telefono",
            );
          } else {
            print(
              "El nombre $nombre tiene asignado el numero de telefono ${buscarContactoPorNombre(grupoWhatsapp, nombre)}",
            );
          }
        } else {
          stdout.write("Introduzca el telefono del contacto:");
          String telefono = stdin.readLineSync()!;
          if (buscarContactoPorNumeroTelefono(grupoWhatsapp, telefono) ==
              null) {
            print(
              "El numero de telefono $telefono no tiene asignado ningun nombre",
            );
          } else {
            print(
              "El numero de telefono $telefono tiene asignado el nombre ${buscarContactoPorNumeroTelefono(grupoWhatsapp, telefono)}",
            );
          }
        }
        break;
      case 3:
        int buscar = -1;
        while (buscar < 0 || buscar > 1) {
          buscar = devolverEnteroValido(
            "Introduzca 0 si quiere buscar a una persona por su nombre o 1 si quiere buscarla por su numero de telefono: ",
          );
        }
        if (buscar == 0) {
          stdout.write("Introduzca el nombre del contacto:");
          String nombre = stdin.readLineSync()!;
          if (buscarContactoPorNombre(grupoWhatsapp, nombre) == null) {
            print(
              "El nombre $nombre no tiene asignado ningun numero de telefono",
            );
          } else {
            print(
              "El nombre $nombre tiene asignado el numero de telefono ${buscarContactoPorNombre(grupoWhatsapp, nombre)}",
            );
          }
        } else {
          stdout.write("Introduzca el telefono del contacto:");
          String telefono = stdin.readLineSync()!;
          if (buscarContactoPorNumeroTelefono(grupoWhatsapp, telefono) ==
              null) {
            print(
              "El numero de telefono $telefono no tiene asignado ningun nombre",
            );
          } else {
            print(
              "El numero de telefono $telefono tiene asignado el nombre ${buscarContactoPorNumeroTelefono(grupoWhatsapp, telefono)}",
            );
          }
        }
        break;
      case 4:
        grupoWhatsapp.forEach((clave, valor) {
          print("Nombre: $clave - Telefono: $valor");
        });
        break;
      case 5:
        print("Saliendo ...");
        salir = true;
        break;
      default:
        print("La accion introducida no es valida");
    }
  }
}

int devolverEnteroValido(String mensaje) {
  bool numValido = false;
  int n = 0;
  while (!numValido) {
    stdout.write("${mensaje}\n");
    try {
      n = int.parse(stdin.readLineSync()!);
      numValido = true;
    } catch (ex) {
      print(ex.toString());
    }
  }
  return n;
}

String? buscarContactoPorNombre(Map mapa, String nombre) {
  String? resultado = null;
  mapa.forEach((clave, valor) {
    if (clave.toLowerCase() == nombre.toLowerCase()) {
      resultado = valor;
    }
  });
  return resultado;
}

String? buscarContactoPorNumeroTelefono(Map mapa, String telefono) {
  String? resultado = null;
  mapa.forEach((clave, valor) {
    if (valor == telefono) {
      resultado = clave;
    }
  });
  return resultado;
}

bool eliminarContactoPorNombre(Map mapa ,String nombre){
  bool eliminado = false;
  for(var contacto in mapa.entries){
    if(contacto.key.toLowerCase()==nombre.toLowerCase()){
      mapa.remove(contacto.key);
    }
  }
  return eliminado;
}

bool eliminarContactoPorNumeroTelefono(Map mapa ,String nombre){
  bool eliminado = false;
  return eliminado;
}
