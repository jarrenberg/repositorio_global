import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPageComponent } from './pages/main-page/main-page';
import { AddPersonaje } from './components/add-personaje/add-personaje';
import { ListaDBZ } from './components/lista/lista';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [MainPageComponent,ListaDBZ,AddPersonaje],
  exports:[MainPageComponent],
  imports: [
    CommonModule,FormsModule
  ]
})
export class DbzModule { }
