import { Component, EventEmitter, Output } from '@angular/core';
import { Personaje } from '../../interfaces/personaje';

@Component({
  selector: 'app-add-personaje',
  templateUrl: './add-personaje.html',
  styleUrl: './add-personaje.css',
  standalone: false
})
export class AddPersonaje {

  @Output()
  public onNewPersonaje: EventEmitter<Personaje> = new EventEmitter();

  public personaje: Personaje = {
    nombre: '',
    fuerza: 0
  }
  public addPersonaje() {
    console.log(this.personaje)
    if (this.personaje.nombre === '' || this.personaje.fuerza === 0) {
      return
    } else {
      this.onNewPersonaje.emit(this.personaje)
    }
    this.personaje.nombre = ""
    this.personaje.fuerza = 0

  }
}
