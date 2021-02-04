import {Bulb} from "../dto/bulb";
import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";

// const LOCAL_URL_PREFIX: string = "http://localhost:8080";
const LOCAL_URL_PREFIX: string = "";

@Component({
  selector: 'bulb-config',
  templateUrl: './bulb-config.component.html'
})
export class BulbConfig {
  bulbs: Bulb[] = [];

  constructor(private http: HttpClient) {
    this.http.get(LOCAL_URL_PREFIX + "/api/bulb/all").subscribe(value => this.bulbs = <Bulb[]>value);
  }

  runSsdp($event: MouseEvent) {
    this.http.post(LOCAL_URL_PREFIX + "/api/bulb/run-ssdp", null).subscribe(value => this.bulbs = <Bulb[]>value);
  }
}
