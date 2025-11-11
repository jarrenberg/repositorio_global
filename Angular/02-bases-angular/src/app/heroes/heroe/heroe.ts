import { Component } from '@angular/core';

@Component({
  selector: 'app-heroe',
  imports: [],
  templateUrl: './heroe.html',
  styleUrl: './heroe.css',
  standalone: true
})
export class HeroeComponent {
  private edad: number = 15;
  private nombre: string = "Son goku";

  public get getNombreCapitalizado(): string {
    return this.nombre.toUpperCase();
  }

  public get getEdad():number{
    return this.edad;
  }

  public getDescripcionHeroe():string{
    return `Nombre: ${this.nombre}\tEdad:${this.edad}`;
  }

  public cambiarEdad():void{
    this.edad+=1;
  }
  public cambiarNombre():void{
    this.nombre=`${this.getNombreCapitalizado} ${this.getEdad}`;
  }
}
