const apellido: string = "Arrenberg";
let nombre = "Jorge";

let puntosVida: number | string = 100;
puntosVida = "Full";

let puntosVida2: number | "Full" = "Full";

let estaVivo: boolean = true;

// nombre = 123;
console.log({ nombre, apellido, puntosVida, puntosVida2, estaVivo }); //Si usamos llaves se muestra como un objeto, con mejor formato
console.log(nombre, apellido, puntosVida, puntosVida2, estaVivo);

export {};

