export class Hsv {
  public hue: number;
  public saturation: number;
  public brightness: number;

  constructor(hue: number, saturation: number, brightness: number) {
    this.hue = hue;
    this.saturation = saturation;
    this.brightness = brightness;
  }
}

export class BulbState {
  public power?: boolean;
  public hsv?: Hsv;
}
