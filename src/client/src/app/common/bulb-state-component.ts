import {Component, EventEmitter, Output} from "@angular/core";
import {BulbState, Hsb} from "../dto/bulb-state";

@Component({
  selector: 'bulb-state',
  templateUrl: './bulb-state-component.html'
})
export class BulbStateComponent {
  powerOptions: any[] = [{label: 'Off', value: false}, {label: 'On', value: true}];
  power: boolean = false;
  @Output() onChange = new EventEmitter<BulbState>();
  bulbState: BulbState = new BulbState();
  color: any;

  changePower(power: boolean) {
    this.bulbState.power = power;
    this.onChange.emit(this.bulbState)
  }

  changeHsb() {
    this.bulbState.hsb = new Hsb(Math.min(this.color.h, 359), this.color.s, Math.max(this.color.b, 1));
    this.onChange.emit(this.bulbState)
  }
}
