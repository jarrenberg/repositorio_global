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
    debugger
    //Forma antonio alumno
    // let copy = Objetct.assing({}, this.personaje);

    // Mi forma
    /*
    var nombreCopia=this.personaje.nombre
    var fuerzaCopia=this.personaje.fuerza
    const copia:Personaje={
      nombre:nombreCopia,
      fuerza:fuerzaCopia
    }
    console.log(copia)
    if (copia.nombre === '' || copia.fuerza === 0) {
      return
    } else {
      this.onNewPersonaje.emit(copia)
    }*/
    console.log(this.personaje)
    if (this.personaje.nombre === '' || this.personaje.fuerza === 0) {
      return
    } else {
      this.onNewPersonaje.emit(this.personaje)
    }
    this.personaje = {
      nombre: '',
      fuerza: 0
    }

  }
}
