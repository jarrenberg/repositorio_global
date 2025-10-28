export class Persona {
    private nombre: string;
    private apellidos: string;
    private edad: number;
    private localidad: string;
    private sexo?: string;

    constructor(nombre: string, apellidos: string, edad: number, localidad: string, sexo?: string) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.localidad = localidad;
        this.sexo = sexo;
    }

    public getNombre(): string {
        return this.nombre;
    }
    public getApellidos(): string {
        return this.apellidos;
    }
    public getEdad(): number {
        return this.edad;
    }
    public getLocalidad(): string {
        return this.localidad;
    }
    public getSexo(): string {
        return this.sexo ? this.sexo : 'H';
    }
};

export class Profesor extends Persona {
    private centro: string;
    private especialidad: string;
    private departamento: string;

    constructor(nombre: string, apellidos: string, edad: number, localidad: string, centro: string, especialidad: string, departamento: string, sexo?: string) {
        super(nombre, apellidos, edad, localidad, sexo);
        this.centro = centro;
        this.especialidad = especialidad;
        this.departamento = departamento;
    }
    public getCentro(): string {
        return this.centro;
    }

    public getEspecialidad(): string {
        return this.especialidad;
    }

    public getDepartamento(): string {
        return this.departamento;
    }
};

export class Alumno extends Persona {

};