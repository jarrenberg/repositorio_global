import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-contador',
  templateUrl: './contador.component.html',
  styleUrls: ['./contador.component.css'],
  standalone:false
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
