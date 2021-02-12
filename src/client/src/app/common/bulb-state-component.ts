import {Component, EventEmitter, Output} from "@angular/core";
import {BulbState, Hsv} from "../dto/bulb-state";

@Component({
  selector: 'bulb-state',
  templateUrl: './bulb-state-component.html'
})
export class BulbStateComponent {
  powerOptions: any[] = [{label: 'Off', value: false}, {label: 'On', value: true}];
  power: boolean = false;
  @Output() onChange = new EventEmitter<BulbState>();
  hue: number = 0;
  saturation: number = 0;
  brightness: number = 0;
  bulbState: BulbState = new BulbState();

  changePower(power: boolean) {
    this.bulbState.power = power;
    this.onChange.emit(this.bulbState)
  }

  changeHsv() {
    this.bulbState.hsv = new Hsv(this.hue, this.saturation, this.brightness);
    this.onChange.emit(this.bulbState)
  }

}