import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Personaje } from '../../interfaces/personaje';
import { v4 as uuid } from 'uuid';

const p1:Personaje={
  id:uuid(),
  nombre:"Bulma",
  fuerza:10
}

@Component({
  selector: 'app-lista-dbz',
  templateUrl: './lista.html',
  styleUrl: './lista.css',
  standalone:false
})
export class ListaDBZ {
  @Input()
  public listaPersonajes:Personaje[]=[p1]

  @Output()
  public onDeletePersonaje: EventEmitter<string> = new EventEmitter();

  public getPos(id:string){

    this.onDeletePersonaje.emit(id);
  }
}
