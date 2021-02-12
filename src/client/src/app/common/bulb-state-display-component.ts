import {Component, Input} from "@angular/core";
import {BulbState} from "../dto/bulb-state";

@Component({
  selector: 'bulb-state-display',
  templateUrl: './bulb-state-display-component.html'
})
export class BulbStateDisplayComponent {
  @Input() bulbState?: BulbState;

}
