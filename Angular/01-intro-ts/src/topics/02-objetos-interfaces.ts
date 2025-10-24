// let habilidades = ["Velocidad", "Magia", "Curacion"];
// let habilidadesAmpliadas = ["Velocidad", "Magia", "Curacion", true, 123];
// let habilidades2: string[] = ["Velocidad", "Magia", "Curacion"];
// let habilidadesAmpliadas2: (string | boolean | number)[] = ["Velocidad", "Magia", "Curacion", true, 123];

const personaje = {
    nombre: "Goku",
    pv: 100,
    habilidades: ["Fuerza", "Velocidad"]
}

console.log(personaje);

interface Personaje {
    nombre: string,
    pv: number,
    habilidades: string[],
    procedencia?: string
}

const saiyan: Personaje ={
nombre:"Vegeta",
pv:500,
habilidades:["Guerrero del Espacio","Fuerza","Transformacion"],
procedencia:"Planeta Vegeta"
}
saiyan.procedencia="Tierra";
console.log(saiyan);
console.table(saiyan);

export{};