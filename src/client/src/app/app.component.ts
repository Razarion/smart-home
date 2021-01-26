import {Component} from '@angular/core';
import {Bulb} from "./dto/bulb";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  bulbs: Bulb[] = [];

  constructor() {
    this.bulbs.push({
      id: '1932874230847234',
      name: 'Zimmer',
      ip: '192.168.2.1',
      port: 1865,
    });
  }

}
