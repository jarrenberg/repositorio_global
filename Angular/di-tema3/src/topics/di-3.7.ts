
interface Matriculas{
    alumnos:AlumnosFP[]
}

interface AlumnosFP {
    nombre: string,
    apellidos: string,
    modulosProfesionales: string[],
    expedientes: Expedientes[]
}
interface Expedientes {
    curso: string,
    notaMedia: number
}
const exp: Expedientes = {
    curso: "Bachillerato",
    notaMedia: 8.2
};

const exp2: Expedientes = {
    curso: "ESO",
    notaMedia: 7.8 
};

const exp3:Expedientes={
    curso:"DAW",
    notaMedia:7
}

const alumno1: AlumnosFP = {
    nombre: "Jorge",
    apellidos: "Arrenberg Andrade",
    modulosProfesionales: ["DI", "IPEI", "PROG"],
    expedientes: [exp, exp2]
}

const alumno2: AlumnosFP = {
    nombre: "Atontao",
    apellidos: "do Nascimento",
    modulosProfesionales: ["PSP", "IPEI", "AD"],
    expedientes: [exp3, exp2]
}

const alumno3: AlumnosFP = {
    nombre: "Juan",
    apellidos: "Cecilia Ruiz",
    modulosProfesionales: ["PSP"],
    expedientes: [exp]
}

const matriculas:Matriculas={
    alumnos:[alumno1,alumno2,alumno3]
};

// Guardamos en una cosntante el nombre y los apellidos del segundo alumno del array de matriculas y los concatenamos
const {nombre:nombre2} = matriculas.alumnos[1];
const {apellidos:apellidos2} = matriculas.alumnos[1];
const nombreCompleto = nombre2.concat(" ").concat(apellidos2);

// Guardamos en una constante primer modulo profesional en el que esta matricula el segundo alumno del array de matriculas
const {modulosProfesionales:modulosAlumno2} = matriculas.alumnos[1]
const modulo1alumno2 = modulosAlumno2[0];

// Guardamos en constantes el curso y la nota media del segundo expediente del segundo alumno del array matriculas
const {curso:cursoExpediente2Alumno2} = matriculas.alumnos[1].expedientes[1];
const {notaMedia:notaMediaExpediente2Alumno2} = matriculas.alumnos[1].expedientes[1];

// Mostramos por consola los resultados obtenidosS
console.table({nombreCompleto,modulo1alumno2,cursoExpediente2Alumno2,notaMediaExpediente2Alumno2})
export {}