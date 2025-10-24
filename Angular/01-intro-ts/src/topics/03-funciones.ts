// Metodo que nos devuelve el resultado sin definir el tipo de parametros
function sumarNumerosSinDefinirNada() {
    return 2 + 5;
}

// Metodo que nos devuelve el resultado de la suma de dos parametros definiendo el tipo de ambos
function sumarNumerosConTipos(a: number, b: number) {
    return a + b;
}

// Metodo que devuelve el resulado de sumar parametros con tipo definido y que nos devuele un string 
const sumarNumerosConFlecha = (a: number, b: number): string => {
    // return (a+b).toString();
    return `El resultado de sumar ${a} y ${b} es ${a + b}`;
};

// Metodo que nos permite multiplicar 2 parametros, siendo uno opcional 
function multiplicar(a: number, b: number, base?: number ) { //primero parametros obligatorios, luego opcionales y por ultimo defaults
    return a*b;
}

// Creamos una interaz personaje
interface Personaje {
    nombre: string,
    pv: number,
    muestraPV: () => void;
}

// Creamos un objeto personaje que implemente la interfaz saiyan y mostramos sus puntos de vida por consola
const saiyan:Personaje={
    nombre:"Vegeta",
    pv:500,
    muestraPV(){
        console.log(`Puntos de vida ${this.pv}`)
    }
}
saiyan.muestraPV();

// Metodo que cura una cantidad de vida a un personaje
const curarPersonaje = (personaje:Personaje,cantidad:number)=>{
    personaje.pv+=cantidad;
}

// Usamos el metodo curar personaje, lo curamos y mostramos por consola el resulado
curarPersonaje(saiyan,300);
saiyan.muestraPV();

// Mostramos por consola el resultado de usar las funciones previamente definidas
const resultado1: number = sumarNumerosSinDefinirNada();
const resultado2: number = sumarNumerosConTipos(2, 3);
const resultado3: string = sumarNumerosConFlecha(4, 7);
const resultado4 : number = multiplicar(2,3);
console.log(resultado1);
console.log(resultado2);
console.log(resultado3);
console.log(resultado4);
export { };