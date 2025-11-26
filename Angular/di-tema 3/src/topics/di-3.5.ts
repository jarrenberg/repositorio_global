/*
    ===== Código de TypeScript =====
*/

// Para solucionar el problema inicial crearemos dos interfaces, direccion y SuperHero
const superHeroe: SuperHero = {
    name: 'Spiderman',
    age: 30,
    address: {
        calle: 'Main St',
        pais: 'USA',
        ciudad: 'NY'
    },
    showAddress() {
        return this.name + ', ' + this.address.ciudad + ', ' + this.address.pais;
    }
}


const address = superHeroe.showAddress();
console.log( address );

// Creamos una interfaz direccion con los parametros que nos aparecen en la linea 9 y usando los mismos tipos
interface Direccion{
    calle:string,
    pais:string,
    ciudad:string
}
// Creamos una interfaz SuperHero con los parametros que nos aparecen en la linea 6 y usando los mismos tipos
// Tambien añadimos el metodo que falta showAddress() que nos sireve para mostrar por consola la informacion de la direccion
interface SuperHero{
    name:string,
    age:number,
    address:Direccion
    showAddress: () => string;
}



export {};