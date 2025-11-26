import { type Alumno } from './di-3.9-modulo-instituto';
import { type Ensenyanza } from './di-3.9-modulo-instituto'
import { type Instituto } from './di-3.9-modulo-instituto'

// Metodo que te permite matricular un alumno pasado como parametro a un instituto pasado como parametro
function matricularAlumno(alumno: Alumno, instituto: Instituto) {
    const { listadoAlumnos } = instituto;
    listadoAlumnos.push(alumno);
}
// Creacion de las tres ensenyanzas
const enseyanza1: Ensenyanza = {
    nivel: 'Bachillerato',
    obligatoriedad: false,
    familiaProfesional: null,
    horario: 'Manyana'
}
const enseyanza2: Ensenyanza = {
    nivel: 'FP',
    obligatoriedad: false,
    familiaProfesional: 'FP',
    horario: 'Tarde'
}
const enseyanza3: Ensenyanza = {
    nivel: 'ESO',
    obligatoriedad: true,
    familiaProfesional: null,
    horario: 'Manyana'
}

// Creacion de los dos alumnos
const alumno1: Alumno = {
    nombre: 'Atontao Do Nascimento',
    edad: 24,
    sexo: 'Hombre',
    ensenyanza: enseyanza1,
    direccion: {
        calle: 'Avenida de los Cielos nº8',
        cp: 29999,
        localidad: 'Alcalá de Guadaira',
        provincia: 'Manchester'
    }
}
const alumno2: Alumno = {
    nombre: 'Walter Donovan',
    edad: 42,
    sexo: 'Hombre',
    ensenyanza: enseyanza2,
    direccion: {
        calle: 'Calle Remolinos nº4',
        cp: 12345,
        localidad: 'Arroyo de la Miel',
        provincia: 'Valencia'
    }
}

// Creacion del instituto
const insti1: Instituto = {
    nombre: 'IES Playamar',
    director: 'Director Directorez',
    telefono: 666999666,
    correo: 'directordirectorez@iesplayamar.es',
    direccion: {
        calle: 'Ronda Alta de Benyamina nº17',
        cp: 29620,
        localidad: 'Torremolinos',
        provincia: 'Malaga'
    },
    listadoAlumnos: [alumno1, alumno2],
    // Metodo que muestra el listado de alumnos mostrando su nombre,su nivel y su localidad
    mostrarAlumnos() {
        const { listadoAlumnos } = insti1;
        let res: string = '\n';
        listadoAlumnos.forEach((a:Alumno) => {
            const {nombre} = a;
            const {nivel} = a.ensenyanza;
            const {localidad} = a.direccion;
            res = res + 'Nombre: ' + nombre + ' | Nivel: ' + nivel + ' | Localidad: '+localidad+'\n';
        });
        return res;
    },
    // Metodo que devuelve la direccion completa del instituto
    direccionCompleta() {
        const { calle } = insti1.direccion;
        const { cp } = insti1.direccion;
        const { localidad } = insti1.direccion;
        const { provincia } = insti1.direccion;
        return 'Calle: ' + calle + '\nCP: ' + cp + '\nLocalidad: ' + localidad + '\nProvincia: ' + provincia;
    }
}

// Mostramos por consola en forma de tabla el instituto que hemos creado
console.table(insti1);
console.log('El nombre completo del instituto es:\n' + insti1.direccionCompleta())
console.log('Lista de los nombres de los alumnos del instituto:\n' + insti1.mostrarAlumnos());

// Probamos a ver si funciona el metodo anyadirAlumno
matricularAlumno(alumno2, insti1);
console.table(insti1);


