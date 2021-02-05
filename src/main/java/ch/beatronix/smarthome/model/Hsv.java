package ch.beatronix.smarthome.model;

public class Hsv {
    private double hue;
    private double saturation;
    private double brightness;

    public double getHue() {
        return hue;
    }

    public void setHue(double hue) {
        this.hue = hue;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public Hsv hue(double hue) {
        setHue(hue);
        return this;
    }

    public Hsv saturation(double saturation) {
        setSaturation(saturation);
        return this;
    }

    public Hsv brightness(double value) {
        setBrightness(value);
        return this;
    }
}
