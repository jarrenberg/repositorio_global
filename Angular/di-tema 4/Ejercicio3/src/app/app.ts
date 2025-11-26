import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { InstitutoComponent } from './Instituto/instituto';


@Component({
  selector: 'app-root',
  imports: [InstitutoComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('IES Selector');
}
