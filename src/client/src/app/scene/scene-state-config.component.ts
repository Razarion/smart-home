import {Bulb} from "../dto/bulb";
import {Component, Input, OnChanges, SimpleChanges} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BulbState} from "../dto/bulb-state";
import {Scene} from "../dto/scene";

@Component({
  selector: 'scene-state-config',
  templateUrl: './scene-state-config.component.html'
})
export class SceneStateConfigComponent implements OnChanges {
  availableBulbs: Bulb[] = [];
  sceneBulbIds: string[] = [];
  controlledBulbIds: string[] = [];
  @Input() scene?: Scene;

  constructor(private http: HttpClient) {
    this.http.get("/api/bulb/all").subscribe(value => this.availableBulbs = <Bulb[]>value);
  }

  addToScene(bulb: Bulb) {
    if (this.scene !== undefined) {
      if (this.scene.bulbStates == undefined) {
        this.scene.bulbStates = {};
      }
      this.scene.bulbStates[bulb.id] = new BulbState()
    }
    this.setupSceneBulbIds();
  }

  removeBulb(bulbId: string) {
    if (this.scene == null || this.scene.bulbStates == null) {
      return;
    }
    delete this.scene.bulbStates[bulbId];
    this.setupSceneBulbIds();
    this.controlledBulbIds.splice(this.controlledBulbIds.indexOf(bulbId), 1);
  }

  idToName(bulbId: string): string {
    let bulb = this.availableBulbs.find(bulb => bulb.id === bulbId);
    if (bulb === undefined) {
      return "???";
    }
    return bulb.name;
  }

  idToState(bulbId: string) {
    if (this.scene == null || this.scene.bulbStates == null) {
      return;
    }
    return this.scene.bulbStates[bulbId];
  }

  onBulbStateChange(bulbState: BulbState) {
    if (this.scene == null || this.scene.bulbStates == null) {
      return;
    }
    this.controlledBulbIds.forEach(bulbId => {
      if (this.scene == null || this.scene.bulbStates == null) {
        return;
      }
      this.scene.bulbStates[bulbId] = bulbState;
    });
    this.http.put("/api/scene/show", this.scene).subscribe();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.sceneBulbIds = [];
    this.controlledBulbIds = [];
    this.setupSceneBulbIds();
  }

  private setupSceneBulbIds() {
    this.sceneBulbIds = [];
    if (this.scene == null || this.scene.bulbStates == null) {
      return;
    }
    for (const [key] of Object.entries(this.scene.bulbStates)) {
      this.sceneBulbIds.push(key);
    }

  }
}
