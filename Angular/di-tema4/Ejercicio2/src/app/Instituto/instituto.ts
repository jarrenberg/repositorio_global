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
  private linkImagen: string = "playamar.png"

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
      this.linkImagen = "jacaranda.jpeg";

    } else {
      this.centro = "IES Playamar";
      this.familiasProfesionales = ["Informática y Comunicaciones", "Transporte", "Mantenimiento de Vehículos"];
      this.localidad = "Torremolinos";
      this.linkImagen = "playamar.png";
    }
  }
}
