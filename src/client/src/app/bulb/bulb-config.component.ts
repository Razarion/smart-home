import {Bulb} from "../dto/bulb";
import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BulbState} from "../dto/bulb-state";

@Component({
  selector: 'bulb-config',
  templateUrl: './bulb-config.component.html'
})
export class BulbConfigComponent {
  bulbs: Bulb[] = [];
  selectedControlCheckbox: string[] = [];

  constructor(private http: HttpClient) {
    this.http.get("/api/bulb/all").subscribe(value => this.bulbs = <Bulb[]>value);
  }

  runSsdp() {
    this.http.post("/api/bulb/run-ssdp", null).subscribe(value => this.bulbs = <Bulb[]>value);
  }

  onBulbStateChange(bulbState: BulbState) {
    let bulbStateList = {
      bulbState: bulbState,
      bulbIds: this.selectedControlCheckbox
    };
    this.http.post("/api/bulb/bulb-states", bulbStateList).subscribe();
  }

  saveBulbs() {
    let bulbNames: any = {};
    this.bulbs.forEach(bulb => bulbNames[bulb.id] = bulb.name);

    this.http.post("/api/bulb/save-bulb-names", bulbNames).subscribe(value => this.bulbs = <Bulb[]>value);
  }
}
