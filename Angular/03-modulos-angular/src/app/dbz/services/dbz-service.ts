import { Injectable } from '@angular/core'
import { Personaje } from '../interfaces/personaje'
import {v4 as uuid} from  'uuid'

const p1: Personaje = {
  id: uuid(),
  nombre: "Goku",
  fuerza: 1000
}

const p2: Personaje = {
  id: uuid(),
  nombre: "Vegeta",
  fuerza: 800
}

const p3: Personaje = {
  id: uuid(),
  nombre: "Thor",
  fuerza: 95
}

const p4: Personaje = {
  id: uuid(),
  nombre: "Bruce Lee",
  fuerza: 85
}

const p5: Personaje = {
  id: uuid(),
  nombre: "Hércules",
  fuerza: 100
}

const p6: Personaje = {
  id: uuid(),
  nombre: "Superman",
  fuerza: 99
}

const p7: Personaje = {
  id: uuid(),
  nombre: "Conan",
  fuerza: 90
}

const p8: Personaje = {
  id: uuid(),
  nombre: "Mike Tyson",
  fuerza: 88
}

const p9: Personaje = {
  id: uuid(),
  nombre: "He-Man",
  fuerza: 92
}
const p10: Personaje = {
  id: uuid(),
  nombre: "Hafthor Bjornsson",
  fuerza: 97
}
@Injectable({providedIn: 'root'})
export class DBZService {
  constructor() { }
    public personajes: Personaje[] = [p1, p2, p3, p4, p5, p6, p7, p8, p9, p10];

  public addPersonaje(personajeParam: Personaje) {
    const newPersonaje:Personaje={id:uuid(),nombre:personajeParam.nombre,fuerza:personajeParam.fuerza}
    console.log(newPersonaje);
    this.personajes.push(newPersonaje)
  }

  public deletePersonajeById(id:string){
    this.personajes=this.personajes.filter(personaje=>personaje.id!==id);
  }

}
