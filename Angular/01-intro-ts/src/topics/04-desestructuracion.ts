// Interfaz que simula un reprodcutor de audio
interface ReproductorAudio{
    volumen:number,
    duracionCancion: number
    cancion:string
    detalles:Detalles
}

// Intefaz que muestra los detalles de una cancion
interface Detalles{
    autor:string,
    anyo:number
}

// Creamos un objeto de reproductor de audio
const reproductorAudio:ReproductorAudio = {
    volumen: 50,
    duracionCancion: 3.45,
    cancion: "Numa Numa Ye",
    detalles: {
        autor:"Ben Yart",
        anyo:2022
    }
}

// Guardamos el nombre de la cancion que esta usando ahora mismo
const nombreCancion:string = reproductorAudio.cancion;

// Guardamos el nombre de la cancion de otra forma, en este caso de forma desestructurada
const {autor:autorCancion} = reproductorAudio.detalles;

const {volumen,detalles} = reproductorAudio;
const {autor} = detalles;

// Mostramos ambos resultado por consola
console.log(nombreCancion);
console.log(autorCancion);
console.log(volumen),
console.log(autor)

// Ejemplo de desestructuracion de Arrays

// Creamos un array y mostramos el personaje 4 por consola
const dbz:string[]=["Goku","Piccolo","Bulma"];
console.log('Personaje 4:',dbz[3] || 'No hay personaje') // podemos usar las barras || como si fuera un if False

// Guardamos un personaje en una constante y lo mostramos por consola
const pj = dbz[5] || 'No hay personaje';
console.log('Personaje 5:',dbz[4] || 'No hay personaje')

// Guardamos los personajes del array en constantes  y las mostramos por consola
const [pj1,pj2,pj3]=dbz;
console.log('Personaje 1: ',pj1);
console.log('Personaje 2: ',pj2);
console.log('Personaje 3: ',pj3);

export{}