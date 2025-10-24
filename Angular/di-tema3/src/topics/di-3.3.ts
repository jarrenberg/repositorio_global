// Creamos la interfaz mediante la que se regiran las variables alumno
interface Alumno{
    nombre:string,
    apellidos:string,
    nacionalidad:string,
    anyoNacimiento:Date,
    numTelefono:number|string,
    email:string,
    modulosMatriculados:string[],
    convalidaIPE:boolean|string,
    nivelPadel?:string | number  // La interrogacion indica opcionalidad del atributo
    centroProcedencia?:string  // La interrogacion indica opcionalidad del atributo
}

// Creamos un alumno de ejemplo
const alumno1:Alumno={
nombre:"Jorge",
apellidos:"Arrenberg Andrade",
nacionalidad:"Española",
anyoNacimiento: new Date("2002-10-12"),
numTelefono:622024488,
email:"correoseguro696@gmail.com",
modulosMatriculados:["IPEI 2","Desarrollo de Interfaces","Python","Acceso a Datos"],
convalidaIPE:true,
nivelPadel:3
}
// Mostramos al alumno creado anteriormente por pantalla
console.table(alumno1);

// Creamos un alumno de ejemplo
const alumno2:Alumno={
nombre:"Raquel",
apellidos:"Aller Ceron",
nacionalidad:"Española",
anyoNacimiento: new Date("2000-05-23"),
numTelefono:666666666,
email:"raqueelaller@hotmail.com",
modulosMatriculados:["IPEI 2","Desarrollo de Interfaces","Python","Acceso a Datos","TFG"],
convalidaIPE:false,
nivelPadel:"Bajo",
centroProcedencia:"San Jose"
}
// Mostramos al alumno creado anteriormente por pantalla
console.table(alumno2);
