import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeroeComponent } from "./heroes/heroe/heroe";
import { CommonModule } from '@angular/common';
import { ListaComponent } from './heroes/lista/lista';

@Component({
  standalone: true,
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
  imports: [HeroeComponent,ListaComponent]
})
export class App {
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
