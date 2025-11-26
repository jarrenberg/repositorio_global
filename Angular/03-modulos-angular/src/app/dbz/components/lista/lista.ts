import { Component, Input } from '@angular/core';
import { Personaje } from '../../interfaces/personaje';

const p1:Personaje={
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
}
