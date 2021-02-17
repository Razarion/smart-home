import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TableModule} from "primeng/table";
import {BulbConfigComponent} from "./bulb/bulb-config.component";
import {HttpClientModule} from "@angular/common/http";
import {BulbStateComponent} from "./common/bulb-state-component";
import {SelectButtonModule} from "primeng/selectbutton";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {CheckboxModule} from "primeng/checkbox";
import {SliderModule} from 'primeng/slider';
import {InputTextModule} from 'primeng/inputtext';
import {SceneConfigComponent} from "./scene/scene-config.component";
import {ControlComponent} from "./control/control.component";
import {DropdownModule} from 'primeng/dropdown';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SceneStateConfigComponent} from "./scene/scene-state-config.component";
import {BulbStateDisplayComponent} from "./common/bulb-state-display-component";
import {PanelModule} from 'primeng/panel';
import {MenuModule} from "primeng/menu";

@NgModule({
  declarations: [
    AppComponent,
    BulbConfigComponent,
    BulbStateComponent,
    BulbStateDisplayComponent,
    SceneConfigComponent,
    SceneStateConfigComponent,
    ControlComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    HttpClientModule,
    SelectButtonModule,
    CheckboxModule,
    FormsModule,
    ButtonModule,
    SliderModule,
    InputTextModule,
    DropdownModule,
    BrowserAnimationsModule,
    PanelModule,
    MenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
