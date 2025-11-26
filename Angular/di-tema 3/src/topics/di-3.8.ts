interface Producto {
	descripcion: string;
	precio: number;
}

const telefono: Producto = {
	descripcion: 'iPhone 15 Pro',
	precio: 1000
}

const tablet: Producto = {
	descripcion: 'iPad Air 3',
	precio: 500
}

interface CalculaImpuestoOpciones {
	impuesto: number;
	productos: Producto[];
}

// 1. Desestructuración al recibir parámetros en funciones
function calculaImpuesto({ productos, impuesto }: CalculaImpuestoOpciones): [number, number] {
	let total = 0;

	// 3. Desestructuración al acceder a datos en estructuras de control
	productos.forEach(({ precio }) => {
		total += precio;
	});

	return [total, total * impuesto];
}

const carritoCompra = [telefono, tablet];
const impuesto = 0.21;

const resultado = calculaImpuesto({
	productos: carritoCompra,
	impuesto: impuesto
})

// 2. Desestructuración al crear variable a partir de función
const [total, impuestoCalculado] = calculaImpuesto({
	productos: carritoCompra,
	impuesto: impuesto
});

console.log('Total', resultado[0]);
console.log('Impuesto', resultado[1]);

console.log('Total', total);
console.log('Impuesto',impuestoCalculado);


export {};

