import {Bulb} from "../dto/bulb";
import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Scene} from "../dto/scene";

@Component({
  selector: 'scene-config',
  templateUrl: './scene-config.component.html'
})
export class SceneConfigComponent {
  scenes: Scene[] = [];
  selectedScene?: Scene;

  constructor(private http: HttpClient) {
    this.http.get("/api/scene/all").subscribe(value => {
      this.scenes = <Scene[]>value;
      if (this.scenes.length > 0) {
        this.selectedScene = this.scenes[0];
      }
    });
  }

  save() {
    if (this.selectedScene === undefined) {
      return;
    }
    this.http
      .put("/api/scene/update", this.selectedScene)
      .subscribe(value => this.selectedScene = <Scene>value);
  }

  new() {
    this.http
      .post("/api/scene/create", {})
      .subscribe(value => {
        this.selectedScene = <Scene>value;
        this.scenes.push(this.selectedScene);
      });
  }

  delete() {
    if (this.selectedScene === undefined) {
      return;
    }
    this.http
      .delete("/api/scene/delete/" + this.selectedScene.id)
      .subscribe(value => {
        let index = this.scenes.findIndex(scene => this.selectedScene !== undefined && scene.id == this.selectedScene.id);
        this.scenes.splice(index, 1);
        this.selectedScene = undefined;
      });
  }
}
