export interface Instituto {
    nombre: string,
    director: string,
    telefono: number | string,
    correo: string,
    direccion: Direccion,
    listadoAlumnos: Alumno[],
    mostrarAlumnos: () => string;
    direccionCompleta: () => string;
}
export interface Alumno {
    nombre: string,
    edad: number,
    sexo: string,
    ensenyanza: Ensenyanza,
    direccion: Direccion
}
export interface Ensenyanza {
    nivel: string,
    obligatoriedad: boolean,
    familiaProfesional: 'FP' | null
    horario: 'Manyana' | 'Tarde' | 'Telematico'
}
export interface Direccion {
    calle: string,
    cp: number,
    localidad: string,
    provincia: string
}

export { }