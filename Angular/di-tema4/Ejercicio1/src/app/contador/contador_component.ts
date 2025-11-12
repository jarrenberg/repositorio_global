import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-contador',
  template: `
  <h1>{{title()}}</h1>
  <h1>{{titleAlter}} : {{contador}}</h1>
  <br>
  <button (click)="incrementar()">Incrementar 1</button>
  <button (click)="decrementar()">Decrementar 1</button>
  <button (click)="resetear()">Resetear</button>`
})

export class ContadorComponent {
  protected readonly title = signal('Mi primera app de Angular');
  titleAlter = 'Contador de Jorge Arrenberg';
  public contador = 0;
  incrementar(): void {
    this.contador = this.contador + 1;
  }
  decrementar(): void {
    this.contador = this.contador - 1;
  }
  resetear(): void {
    this.contador = 0;
  }
}
