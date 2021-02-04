import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TableModule} from "primeng/table";
import {BulbConfigComponent} from "./bulb/bulb-config.component";
import {HttpClientModule} from "@angular/common/http";
import {BulbStateComponent} from "./bulb/bulb-state-component";
import {SelectButtonModule} from "primeng/selectbutton";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";

@NgModule({
  declarations: [
    AppComponent,
    BulbConfigComponent,
    BulbStateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    HttpClientModule,
    SelectButtonModule,
    FormsModule,
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
