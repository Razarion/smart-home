import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BulbConfigComponent} from "./bulb/bulb-config.component";
import {SceneConfigComponent} from "./scene/scene-config.component";
import {ControlComponent} from "./control/control.component";

const routes: Routes = [
  {path: 'control', component: ControlComponent},
  {path: 'bulb-config', component: BulbConfigComponent},
  {path: 'scene-config', component: SceneConfigComponent},
  {path: '', redirectTo: '/control', pathMatch: 'full'}, // redirect to `control`
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
