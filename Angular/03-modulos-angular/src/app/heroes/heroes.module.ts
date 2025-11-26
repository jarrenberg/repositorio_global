import { NgModule } from "@angular/core";
import { HeroeComponent } from './heroe/heroe';
import { ListaComponent } from './lista/lista';
import { CommonModule } from "@angular/common";

@NgModule({
  declarations: [
      HeroeComponent,ListaComponent
    ],
    exports:[HeroeComponent,ListaComponent],
    imports:[CommonModule]
})
export class HeroesModule{
}
