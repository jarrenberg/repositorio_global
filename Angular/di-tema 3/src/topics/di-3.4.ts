const n1: number = 2;
const n2: number = 6;

function sumar(num1: number, num2?: number) {
    let res: number = 0;
    if (typeof num2 !== "undefined") {
        res = num1 + num2;
    } else {
        res = num1 + num1;
    }
    return res;
}

function restar(num1: number, num2?: number) {
    let res: number = 0;
    if (typeof num2 !== "undefined") {
        res = num1 - num2;
    } else {
        res = num1 - num1;
    }
    return res;
}

function multiplicar(num1: number, num2?: number) {
    let res: number = 0;
    if (typeof num2 !== "undefined") {
        res = num1 * num2;
    } else {
        res = num1 * num1;
    }
    return res;
}

function dividir(num1: number, num2?: number) {
    let res: number = 0;
    if (typeof num2 !== "undefined") {
        res = num1 / num2;
    } else {
        res = num1 / num1;
    }
    return res;
}

const resultado1: number = sumar(n1);
const resultado2: number = restar(n1, n2);
const resultado3: number = multiplicar(n1, n2);
const resultado4: number = dividir(n1, n2);
console.log(resultado1, resultado2, resultado3, resultado4);

export {};