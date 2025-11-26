import { Component } from '@angular/core';

interface InstitutoPublico{
  centro:string;
  localidad:string;
  familiasProfesionales:string[];
  imagen:string
}

let insti1:InstitutoPublico={
  centro: 'IES Playamar',
  localidad: 'Torremolinos',
  familiasProfesionales: ["Informatica y Comunicaciones","Transporte","Mantenimiento de Vehiculos"],
  imagen: 'playamar.png'
}
let insti2:InstitutoPublico={
  centro: 'IES Jacaranda',
  localidad: 'Churriana',
  familiasProfesionales: ["Hosteleria","Turismo"],
  imagen: 'jacaranda.jpeg'
}

let insti3:InstitutoPublico={
  centro: 'Centro Público de FPE Punta Europa',
  localidad: 'Algeciras',
  familiasProfesionales: ["Actividades de Gestión Administrativa","Empleo Doméstico","Habilitación para la Docencia"],
  imagen: 'punta_europa.jpg'
}

let insti4:InstitutoPublico={
  centro: 'IES Politecnico Jesus Marin',
  localidad: 'Málaga',
  familiasProfesionales: ["Administración de Empresas","Edificación y Obra Civil","Electricidad y Electrónica","Imagen y Sonido","Informática y Telecomunicaciones"],
  imagen: 'politecnico_jesus_marin.jpg'
}

let insti5:InstitutoPublico={
  centro: 'CPIFP Alan Turing',
  localidad: 'Campanillas',
  familiasProfesionales: ["Mantenimiento Electrónico","Administración de Sistemas Informáticos en Red","Desarrollo de Aplicaciones Web","Desarrollo de Aplicaciones Multiplataforma"],
  imagen: 'alan_turing.jpg'
}

let insti6: InstitutoPublico = {
  centro: 'IES Severo Ochoa',
  localidad: 'Granada',
  familiasProfesionales: ["Sanidad", "Servicios Socioculturales y a la Comunidad", "Administración y Gestión", "Informática y Comunicaciones"],
  imagen: 'severo_ochoa.jpg'
}

let insti7: InstitutoPublico = {
  centro: 'IES Torre del Rey',
  localidad: 'Pilas (Sevilla)',
  familiasProfesionales: ["Agraria", "Química", "Energía y Agua", "Seguridad y Medio Ambiente"],
  imagen: 'torre_del_rey.jpg'
}

let insti8: InstitutoPublico = {
  centro: 'IES La Rosaleda',
  localidad: 'Málaga',
  familiasProfesionales: ["Hostelería y Turismo", "Comercio y Marketing", "Imagen Personal", "Administración y Gestión"],
  imagen: 'rosaleda.jpg'
}

let insti9: InstitutoPublico = {
  centro: 'IES Fidiana',
  localidad: 'Córdoba',
  familiasProfesionales: ["Química", "Sanidad", "Seguridad y Medio Ambiente", "Energía y Agua"],
  imagen: 'fidiana.jpg'
}

let insti10: InstitutoPublico = {
  centro: 'CPIFP Los Gladiolos',
  localidad: 'Huelva',
  familiasProfesionales: ["Agraria", "Industrias Alimentarias", "Química", "Seguridad y Medio Ambiente"],
  imagen: 'gladiolos.jpg'
}

@Component({
  selector: 'app-instituto',
  imports: [],
  templateUrl: './instituto.html',
  styleUrl: './instituto.css',
})
export class InstitutoComponent {
    private listaInstitutos:InstitutoPublico[]=[insti1,insti2,insti3,insti4,insti5,insti6,insti7,insti8,insti9,insti10];
  }

