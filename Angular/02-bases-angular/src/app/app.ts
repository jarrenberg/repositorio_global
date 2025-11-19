import { Component } from '@angular/core';

import { ContadorModule } from './contador/components/contador.module';
import { HeroesModule } from './heroes/heroes.module';

@Component({
  standalone: true,
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
  imports: [ContadorModule, HeroesModule]
})
export class App {

}
