import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Personaje } from '../../interfaces/personaje';


@Component({
  selector: 'app-lista-dbz',
  templateUrl: './lista.html',
  styleUrl: './lista.css',
  standalone:false
})
export class ListaDBZ {
  @Input()
  public listaPersonajes:Personaje[]=[]

  @Output()
  public onDeletePersonaje: EventEmitter<string> = new EventEmitter();

  public getID(id:string){

    this.onDeletePersonaje.emit(id);
  }
}
