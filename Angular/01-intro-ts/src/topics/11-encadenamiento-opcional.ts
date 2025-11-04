export interface Pasajero{
    nombre:string;
    hijos?:string[];
}

const pasajero1:Pasajero = {
    nombre:"Antonio"
}

const pasajero2:Pasajero={
    nombre:"Pilar",
    hijos:["Hijo1","Hijo2","Hijo3"],
}

console.log(pasajero1.hijos);
console.log(pasajero2.hijos);
const imprimirHijos = (pasajero:Pasajero)=>{
    const numeroHijos = pasajero.hijos?.length || 0
    console.log(pasajero.nombre);
    console.log(numeroHijos);
}

imprimirHijos(pasajero1);
imprimirHijos(pasajero2);