import { calendario } from './di-3.12';

export function mostrarElementosArray<T>(argument:T[]):void{
   let posicion:number = 1;
    argument.forEach(elemento => {
        console.log(`Posicion: ${posicion}\nValor: `,elemento);
        posicion=posicion+1;
    });
}

mostrarElementosArray(calendario.getPartidos());
export{};

