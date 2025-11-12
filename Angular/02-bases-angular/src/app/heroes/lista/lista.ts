import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-lista',
  imports: [CommonModule],
  templateUrl: './lista.html',
  styleUrl: './lista.css',
  standalone: true
})
export class ListaComponent {
  public nombreHeroes: string[] = ["GOKU", "VEGETA", "PICCOLO", "BULMA", "MUTEN ROSHI"];
  public nombreHeroesEliminados: string[] = [];

  public eliminarUltimoHeroe(): void {
    var resultado = this.nombreHeroes.pop();
    if (resultado != undefined) {
      this.nombreHeroesEliminados.push(resultado);
    }
  }
}
