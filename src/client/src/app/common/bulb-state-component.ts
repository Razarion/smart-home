import {Component, EventEmitter, Output} from "@angular/core";
import {BulbState, Hsb} from "../dto/bulb-state";
// import {debounce} from "debounce";

@Component({
  selector: 'bulb-state',
  templateUrl: './bulb-state-component.html'
})
export class BulbStateComponent {
  powerOptions: any[] = [{label: 'Off', value: false}, {label: 'On', value: true}];
  power: boolean = false;
  @Output() onChange = new EventEmitter<BulbState>();
  color: any;

  // change() {
    // debounce(this.createAndFireBulbState, 100); ???
  // }

  change() {
    let bulbState: BulbState = new BulbState();
    bulbState.power = this.power;
    if (this.color !== undefined) {
      bulbState.hsb = new Hsb(Math.min(this.color.h, 359), this.color.s, Math.max(this.color.b, 1));
    }
    this.onChange.emit(bulbState)
  }
}
