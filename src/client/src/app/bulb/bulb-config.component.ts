import {Bulb} from "../dto/bulb";
import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'bulb-config',
  templateUrl: './bulb-config.component.html'
})
export class BulbConfig {
  bulbs: Bulb[] = [];

  constructor(private http: HttpClient) {
    this.http.get("http://localhost:8080/bulb/all").subscribe(value => this.bulbs = <Bulb[]>value);
  }

}
