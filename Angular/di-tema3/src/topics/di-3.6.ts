// Interfaz que regula las caracteristicas del alumno de FP
interface AlumnoFP{
    nombre:string,
    apellidos:string,
    anyoNacimiento:number,
    nacionalidad:string,
    matricula:Matricula
}

// Interfaz que regula las caracteristicas de las matriculas de los alumnos de FP
interface Matricula{
    modulos:string[],
    cicloFormativo:string,
    centroEducativo:CentroEducativo
}

// Interfaz que regula los centros educativos donde se matriculan los alumnos de FP
interface CentroEducativo{
    centro:string,
    localidad:string,
    codigoCentro:number,
    telefono:string
}

// Creamos un objeto alumno1 usando la interfaz AlumnoFP con datos aleatorios
const alumno1:AlumnoFP={
    nombre: "Jorge",
    apellidos: "Arrenberg",
    anyoNacimiento: 2002,
    nacionalidad: "Española",
    matricula: {
        modulos: ["SGE","DI","PSP","ADs"],
        cicloFormativo: "DAM",
        centroEducativo: {
            centro: "IES Playamar",
            localidad: "Torremolinos",
            codigoCentro: 666,
            telefono: "666999666"
        }
    }
}

// Creamos un objeto alumno2 usando la interfaz AlumnoFP con datos aleatorios
const alumno2:AlumnoFP={
    nombre: "Atontao",
    apellidos: "do Nascimento",
    anyoNacimiento: 1998,
    nacionalidad: "Brasileño",
    matricula: {
        modulos: ["FOL","DI","IN"],
        cicloFormativo: "DAW",
        centroEducativo: {
            centro: "CFP Alan Turing",
            localidad: "Campanillas",
            codigoCentro: 999,
            telefono: "999666999"
        }
    }
}

// Mostramos ambos objetos como tablas
console.table(alumno1);
console.table(alumno2)

// Guardamos los atributos especificos solicitados del alumno1 y los mostramos en forma de tabla
const {nombre:nombre1} = alumno1;
const {apellidos:apellidos1} = alumno1;
const nombreCompleto1 = nombre1+" "+apellidos1
const {cicloFormativo:cicloFormativo1} = alumno1.matricula;
const{centro:centro1} = alumno1.matricula.centroEducativo;
console.table({nombreCompleto1,centro1,cicloFormativo1})

// Guardamos los atributos especificos solicitados del alumno2 y los mostramos en forma de tabla
const {nombre:nombre2} = alumno2;
const {apellidos:apellidos2} = alumno2;
const nombreCompleto2 = nombre2+" "+apellidos2
const {cicloFormativo:cicloFormativo2} = alumno2.matricula;
const{centro:centro2} = alumno2.matricula.centroEducativo;
console.table({nombreCompleto2,centro2,cicloFormativo2});
