export class Persona {
    public nombre: string;
    public direccion: string;
    public altura?: number;
    private edad: number;

    constructor() {
        this.nombre = 'Jorge Arrenberg';
        this.direccion = 'Calle Alucinante nº99';
        this.edad = 23;
    }
};
console.log(new Persona());

export class PersonaCutre {
    public nombre: string;
    public direccion: string;
    public altura?: number;
    private edad: number;

    constructor(nombre: string, direccion: string, edad: number = 0, altura?: number) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.altura = altura;
        this.edad = edad;
    }
}

const gohan = new PersonaCutre('Gohan', 'Tierra', 18, 170);
console.table(gohan);

export class PersonaGuay {
    constructor(public nombre: string, public direccion: string, private edad: number = 0, public altura?: number) {

    }
}

const vegeta = new PersonaGuay('Vegeta','Planeta Vegeta',23);
console.table(vegeta);