import { Component } from '@angular/core';
import { DBZService } from '../../services/dbz-service';


@Component({
  selector: 'app-dbz-main-page',
  templateUrl: './main-page.html',
  styleUrl: './main-page.css',
  standalone: false
})

export class MainPageComponent {
  constructor(public DBZService: DBZService) { }
}
