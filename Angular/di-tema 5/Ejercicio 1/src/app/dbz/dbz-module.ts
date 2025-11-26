import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPageComponent } from './pages/main-page/main-page';
import {  ListaDBZ } from './components/lista/lista';
import { AddPersonaje } from './components/add-personaje/add-personaje';


@NgModule({
  declarations: [MainPageComponent,ListaDBZ,AddPersonaje],
  exports:[MainPageComponent],
  imports: [
    CommonModule
  ]
})
export class DbzModule { }
