import {Component} from "@angular/core";

@Component({
  selector: 'bulb-state',
  templateUrl: './bulb-state-component.html'
})
export class BulbStateComponent {
  powerOptions: any[] = [{label: 'Off', value: 'off'}, {label: 'On', value: 'on'}];
  power: string = "off";
}
