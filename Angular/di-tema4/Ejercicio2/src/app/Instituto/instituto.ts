import { Component } from '@angular/core';

@Component({
  selector: 'app-instituto',
  imports: [],
  templateUrl: './instituto.html',
  styleUrl: './instituto.css',
})
export class Instituto {
  private centro: string = "IES Playamar"
  private localidad: string = "Torremolinos"
  private familiasProfesionales: string[] = ["Informática y Comunicaciones", "Transporte", "Mantenimiento de Vehículos"]
  private linkImagen: string = "https://iesplayamar.es/wp-content/uploads/2021/09/logo-ies-playamar.png"

  public get getCentro() {
    return this.centro;
  }

  public get getLocalidad() {
    return this.localidad;
  }

  public get getFamiliasProfesionales() {
    return this.familiasProfesionales;
  }

  public get getLinkImagen() {
    return this.linkImagen;
  }

  public getCentroCapitalized(): string {
    return this.centro.toUpperCase();
  }

  public cambiarCentro(): void {
    if (this.centro == "IES Playamar") {
      this.centro = "IES Jacaranda";
      this.familiasProfesionales = ["Hosteleria", "Turismo"];
      this.localidad = "Churriana";
      this.linkImagen = "https://pbs.twimg.com/profile_images/3059231666/21dd362313c922734bf3297b69b2da14.jpeg";

    } else {
      this.centro = "IES Playamar";
      this.familiasProfesionales = ["Informática y Comunicaciones", "Transporte", "Mantenimiento de Vehículos"];
      this.localidad = "Torremolinos";
      this.linkImagen = "https://iesplayamar.es/wp-content/uploads/2021/09/logo-ies-playamar.png";
    }
  }
}
