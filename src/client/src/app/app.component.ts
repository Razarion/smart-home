import {Component} from '@angular/core';
import {MenuItem} from "primeng/api";
import {Router} from '@angular/router';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  menuItems: MenuItem[];

  constructor(router: Router, private http: HttpClient) {
    this.menuItems = [
      {
        label: "Control",
        command: () => router.navigate(["/control"])
      },
      {
        label: "Scene Configuration",
        command: () => router.navigate(["/scene-config"])
      },
      {
        label: "Bulb Configuration",
        command: () => router.navigate(["/bulb-config"])
      }
    ];
  }

  scan() {
    this.http.post("/api/bulb/run-ssdp", null).subscribe();
  }
}
