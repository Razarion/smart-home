import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {SimpleScene} from "../dto/simple-scene";

@Component({
  selector: 'control',
  templateUrl: './control.component.html',
  styleUrls: ['./control.component.css']
})
export class ControlComponent {
  simpleScenes: SimpleScene[] = [];

  constructor(private http: HttpClient) {
    this.http.get("/api/scene/all-simple-scenes").subscribe(value => {
      this.simpleScenes = <SimpleScene[]>value;
    });
  }

  activateScene(sceneId: number) {
    this.http.put("/api/scene/activate-scene", sceneId).subscribe();
  }
}
