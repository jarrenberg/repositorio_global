import { PersonaGuay } from "./08-clases";

export class Heroe extends PersonaGuay{
    constructor( nombre: string,  direccion: string,  edad: number = 0, public apodo:string, public nombreReal:string, altura?: number){
        super(nombre,direccion,edad,altura);
        this.apodo=apodo;
        this.nombreReal=nombreReal;
    }
}
const goku = new Heroe('Goku','La Tierra',25,'El mejor Saiyan','Son Goku');
console.table(goku);