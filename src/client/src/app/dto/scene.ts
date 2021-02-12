import {BulbState} from "./bulb-state";

export interface Scene {
  name: string;
  id: number;
  bulbStates: any;
}
