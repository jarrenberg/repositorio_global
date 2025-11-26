import { Component } from '@angular/core';
import { DbzModule } from './dbz/dbz-module';

@Component({
  standalone: true,
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
  imports: [DbzModule]
})
export class App {

}
