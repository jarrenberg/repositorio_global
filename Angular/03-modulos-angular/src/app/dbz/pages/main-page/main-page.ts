import { Component } from '@angular/core';
import { Personaje } from '../../interfaces/personaje';

const p1: Personaje = {
  nombre: "Goku",
  fuerza: 1000
}

const p2: Personaje = {
  nombre: "Vegeta",
  fuerza: 800
}

const p3: Personaje = {
  nombre: "Thor",
  fuerza: 95
}

const p4: Personaje = {
  nombre: "Bruce Lee",
  fuerza: 85
}

const p5: Personaje = {
  nombre: "Hércules",
  fuerza: 100
}

const p6: Personaje = {
  nombre: "Superman",
  fuerza: 99
}

const p7: Personaje = {
  nombre: "Conan",
  fuerza: 90
}

const p8: Personaje = {
  nombre: "Mike Tyson",
  fuerza: 88
}

const p9: Personaje = {
  nombre: "He-Man",
  fuerza: 92
}
const p10: Personaje = {
  nombre: "Hafthor Bjornsson",
  fuerza: 97
}

@Component({
  selector: 'app-dbz-main-page',
  templateUrl: './main-page.html',
  styleUrl: './main-page.css',
  standalone: false
})

export class MainPageComponent {
  public personajes: Personaje[] = [p1, p2, p3, p4, p5, p6, p7, p8, p9, p10];

  public onMetodoNewPersonaje(personaje:Personaje){
    console.log(personaje);
    this.personajes.push(personaje)
  }
}
