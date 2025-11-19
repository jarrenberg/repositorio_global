import { Component } from '@angular/core';

@Component({
  selector: 'app-heroe',
  templateUrl: './heroe.html',
  styleUrl: './heroe.css',
  standalone: false
})
export class HeroeComponent {
  private edad: number = 15;
  private nombre: string = "Son Goku";

  public get getNombreCapitalizado(): string {
    return this.nombre.toUpperCase();
  }

  public get getNombre():string{
    return this.nombre;
  }

  public get getEdad():number{
    return this.edad;
  }

  public getDescripcionHeroe():string{
    return `Nombre: ${this.nombre}\tEdad:${this.edad}`;
  }

  public cambiarEdad():void{
    this.edad=50;
  }
  public cambiarNombre():void{
    this.nombre="Vegeta";
  }

  public restablecer(){
    this.nombre="Son Goku";
    this.edad=15;
  }
}
