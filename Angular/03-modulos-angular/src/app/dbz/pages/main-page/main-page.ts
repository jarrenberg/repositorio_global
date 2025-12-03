import { Component } from '@angular/core';
import { DBZService } from '../../services/dbz-service';
import { Personaje } from '../../interfaces/personaje';


@Component({
  selector: 'app-dbz-main-page',
  templateUrl: './main-page.html',
  styleUrl: './main-page.css',
  standalone: false
})

export class MainPageComponent {
  constructor(private DBZService: DBZService) { }
   get personajes():Personaje[]{
    return this.DBZService.personajes
  }

  public onDeletePersonaje(id:string){
    this.DBZService.deletePersonajeById(id)
  }

  public onNewPersonaje(personaje:Personaje){
    this.DBZService.addPersonaje(personaje)
  }
}
