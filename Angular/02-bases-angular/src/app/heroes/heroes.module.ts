import { NgModule } from "@angular/core";
import { HeroeComponent } from './heroe/heroe';
import { ListaComponent } from './lista/lista';

@NgModule({
  declarations: [
      HeroeComponent,ListaComponent
    ],
    exports:[HeroeComponent,ListaComponent]
})
export class HeroesModule{
}
