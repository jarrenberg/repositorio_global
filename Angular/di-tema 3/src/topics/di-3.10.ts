
// Importamos la interfaz Expedientes de otro archivo
import { type Expedientes} from './di-3.7';

// Creacion de la clase Estudiante y su constructor
export class Estudiante {
    public nombre: string;
    public apellidos: string;
    public edad: number;
    public localidad: string;
    public sexo?: string;
    public expediente: Expedientes;

    constructor(nombre: string, apellidos: string, edad: number, localidad: string, expediente: Expedientes, sexo?: string) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.localidad = localidad;
        this.expediente = expediente;
        this.sexo=sexo
    }
};

// Creamos dos objetos estudiantes y los mostramos por consola
const jorge = new Estudiante('Jorge', 'Arrenberg', 23, 'Málaga', {
    curso: "Bachillerato",
    notaMedia: 8.2
},'H');
const antonio = new Estudiante('Antonio', 'Benitez', 24, 'Benalmadena', {
    curso: "Bachillerato",
    notaMedia: 10
})
console.table(jorge);
console.table(antonio);

// Creacion de la clase instituto y su constructor
export class Instituto{
    public centro:string;
    public localidad:string;
    public estudiantes:Estudiante[];

    constructor(centro:string,localidad:string,estudiantes:Estudiante[]){
        this.centro=centro;
        this.localidad=localidad;
        this.estudiantes=estudiantes;
    }
};

// Creamos una variable con el objeto Instituto y lo mostramos por consola
const playamar = new Instituto('IES Playamar','Torremolinos',[jorge,antonio]);
console.table(playamar);