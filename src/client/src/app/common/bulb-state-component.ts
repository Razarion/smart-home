import {Component, EventEmitter, Output} from "@angular/core";
import {BulbState, Hsb} from "../dto/bulb-state";
// @ts-ignore
import {debounce} from 'throttle-debounce';


@Component({
  selector: 'bulb-state',
  templateUrl: './bulb-state-component.html'
})
export class BulbStateComponent {
  powerOptions: any[] = [{label: 'Off', value: false}, {label: 'On', value: true}];
  power: boolean = false;
  @Output() onChange = new EventEmitter<BulbState>();
  color: any;
  colorTemperature: any;
  debounceFunc: any;

  constructor() {
    let self = this;
    this.debounceFunc = debounce(100, () => {
      this.createAndFireBulbState(self)
    });

  }

  change() {
    this.debounceFunc();
  }

  createAndFireBulbState(self: BulbStateComponent) {
    let bulbState: BulbState = new BulbState();
    bulbState.power = self.power;
    if (self.color !== undefined) {
      bulbState.hsb = new Hsb(Math.min(self.color.h, 359), self.color.s, Math.max(self.color.b, 1));
    }
    if (self.colorTemperature !== undefined) {
      bulbState.colorTemperature = self.colorTemperature;
    }
    self.onChange.emit(bulbState)
  }
}
