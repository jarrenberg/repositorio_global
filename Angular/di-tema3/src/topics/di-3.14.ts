import type { Direccion } from './di-3.9-modulo-instituto';

class Persona {
    private nombre: string;
    private apellidos: string;
    private genero: 'Hombre' | 'Mujer';
    private telefono: number | string;
    private direccion: Direccion;
    constructor(nombre: string, apellidos: string, genero: 'Hombre' | 'Mujer', telefono: number | string, direccion: Direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;

    }

    public getNombre(): string {
        return this.nombre;
    }

    public getApellidos(): string {
        return this.apellidos;
    }

    public getGenero(): string {
        return this.genero;
    }

    public getTelefono(): string | number {
        return this.telefono;
    }

    public getDireccion(): Direccion {
        return this.direccion;
    }
    public toString(): string {
        return `Nombre Completo: ${this.nombre} ${this.apellidos}, Genero: ${this.genero}, Telefono: ${this.telefono}, Direccion: ${this.direccion}`;
    }
};

export class Jugador extends Persona {
    private categoria: number;
    constructor(nombre: string, apellidos: string, genero: 'Hombre' | 'Mujer', telefono: number | string, direccion: Direccion, categoria: number) {
        super(nombre, apellidos, genero, telefono, direccion);
        if (categoria >= 1 && categoria <= 6) {
            this.categoria = categoria;
        } else {
            throw new Error("La categoría ${categoria} no es válida. Debe estar entre 1 y 6.");
        }
    };

    public getCategoria(): number {
        return this.categoria;
    }

    public toString(): string {
        return `${super.toString()}, Categoria: ${this.categoria}\n`;
    }
};

export class Arbitro extends Persona {
    private numeroColegiado: number;
    constructor(nombre: string, apellidos: string, genero: 'Hombre' | 'Mujer', telefono: number | string, direccion: Direccion, numeroColegiado: number) {
        super(nombre, apellidos, genero, telefono, direccion);
        this.numeroColegiado = numeroColegiado;
    };

    public getNumeroColegiado(): number {
        return this.numeroColegiado;
    }
    public toString(): string {
        return `${super.toString()}, Numero de colegiado: ${this.numeroColegiado}\n`;
    }
};

export class Equipo {
    private miembros: Jugador[];
    constructor(miembros: Jugador[]) {
        if (miembros.length == 2) {
            this.miembros = miembros;
        } else {
            throw new Error("Los equipos deben tener 2 jugadores");
        }
    }

    public getMiembrosEquipo() {
        return this.miembros;
    }
};

export class Partido {
    private equipos: Equipo[];
    private fecha: Date;
    private hora: number;
    private recinto: string;
    private pista: string;
    private arbitro: Arbitro;

    constructor(equipos: Equipo[], fecha: Date, hora: number, recinto: string, pista: string, arbitro: Arbitro) {
        if (equipos.length != 2) {
            throw new Error("El numero de equipos que participan debe ser 2");
        } else {
            this.equipos = equipos;
            this.fecha = fecha;
            this.hora = hora;
            this.recinto = recinto;
            this.pista = pista;
            this.arbitro = arbitro;
        }
    }

    public getEquipos(): Equipo[] {
        return this.equipos;
    }

    public getFecha(): Date {
        return this.fecha;
    }

    public getHora(): number {
        return this.hora;
    }

    public getRecinto(): string {
        return this.recinto;
    }

    public getPista(): string {
        return this.pista;
    }

    public getArbitro(): Arbitro {
        return this.arbitro;
    }
    public toString(): string {
        const resultado: string = `Equipo 1 = ${this.equipos[0].getMiembrosEquipo().toString()}, Equipo 2 = ${this.equipos[1].getMiembrosEquipo().toString()}, Fecha: ${this.fecha.getDate()} ${this.fecha.getMonth() + 1} ${this.fecha.getFullYear()},
         Hora: ${this.hora}, Recinto: ${this.recinto}, Pista: ${this.pista}, Arbitro: ${this.arbitro.toString()}`;
        return resultado;
    }

};

export class Calendario {
    private partidos: Partido[];

    constructor(partidos: Partido[]) {
        this.partidos = partidos;
    }

    public getPartidos(): Partido[] {
        return this.partidos;
    }
}

const jugadores: Jugador[] = [];

// Equipo 1
try {
    const jugador1 = new Jugador('Jorge', 'Arrenberg Andrade', 'Hombre', 123456789, {
        calle: "Calle Maravillosa nº12",
        cp: 29000,
        localidad: "Malaga",
        provincia: "Malaga"
    }, 2);
    jugadores.push(jugador1);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando jugador1:', e.message);
    }
}

try {
    const jugador2 = new Jugador('Ronaldo', 'Nazario', 'Hombre', '987654321', {
        calle: "Fabela do Futebol",
        cp: 12345,
        localidad: "Sao Paulo",
        provincia: "Sao Paulo"
    }, 4);
    jugadores.push(jugador2);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando jugador2:', e.message);
    }
}

// Equipo 2
try {
    const jugador3 = new Jugador('Lionel', 'Messi', 'Hombre', 555111222, {
        calle: "Avenida del Gol nº10",
        cp: 8000,
        localidad: "Barcelona",
        provincia: "Cataluña"
    }, 3);
    jugadores.push(jugador3);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando jugador3:', e.message);
    }
}

try {
    const jugador4 = new Jugador('Alexia', 'Putellas', 'Mujer', 555333444, {
        calle: "Calle del Dribling nº5",
        cp: 8001,
        localidad: "Barcelona",
        provincia: "Cataluña"
    }, 5);
    jugadores.push(jugador4);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando jugador4:', e.message);
    }
}

// Equipo 3
try {
    const jugador5 = new Jugador('Karim', 'Benzema', 'Hombre', '555555666', {
        calle: "Plaza del Gol nº7",
        cp: 28001,
        localidad: "Madrid",
        provincia: "Madrid"
    }, 2);
    jugadores.push(jugador5);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando jugador5:', e.message);
    }
}

try {
    const jugador6 = new Jugador('Aitana', 'Bonmatí', 'Mujer', '555777888', {
        calle: "Calle del Pase nº15",
        cp: 46001,
        localidad: "Valencia",
        provincia: "Valencia"
    }, 6);
    jugadores.push(jugador6);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando jugador6:', e.message);
    }
}

const equipos: Equipo[] = [];

try {
    const equipo1 = new Equipo([jugadores[0], jugadores[1]]);
    equipos.push(equipo1);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando equipo1:', e.message);
    }
}

try {
    const equipo2 = new Equipo([jugadores[2], jugadores[3]]);
    equipos.push(equipo2);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando equipo2:', e.message);
    }
}

try {
    const equipo3 = new Equipo([jugadores[4], jugadores[5]]);
    equipos.push(equipo3);
} catch (e) {
    if (e instanceof Error) {
        console.error('Error creando equipo3:', e.message);
    }
}

// Crear árbitros
const arbitro1 = new Arbitro(
    'Carlos',
    'Ramos Blanco',
    'Hombre',
    '600123456',
    {
        calle: "Calle del Silbato nº3",
        cp: 28002,
        localidad: "Madrid",
        provincia: "Madrid"
    },
    12345
);

const arbitro2 = new Arbitro(
    'María',
    'López Fernández',
    'Mujer',
    '600654321',
    {
        calle: "Avenida del Fair Play nº8",
        cp: 41001,
        localidad: "Sevilla",
        provincia: "Sevilla"
    },
    67890
);

// Crear partidos
const partidos: Partido[] = [];
const partido1 = new Partido(
    [equipos[0], equipos[1]],
    new Date('2024-02-15'),
    18,
    'Polideportivo Municipal',
    'Pista Central',
    arbitro1
);
partidos.push(partido1);

const partido2 = new Partido(
    [equipos[1], equipos[2]],
    new Date('2024-02-20'),
    20,
    'Club Deportivo Local',
    'Pista 1',
    arbitro2
);
partidos.push(partido2);

const partido3 = new Partido(
    [equipos[0], equipos[2]],
    new Date('2024-02-25'),
    19,
    'Polideportivo Municipal',
    'Pista 2',
    arbitro1
);
partidos.push(partido3);

export const calendario = new Calendario(partidos);