package ch.beatronix.smarthome.model;

public class Hsb {
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

    public Hsb hue(double hue) {
        setHue(hue);
        return this;
    }

    public Hsb saturation(double saturation) {
        setSaturation(saturation);
        return this;
    }

    public Hsb brightness(double value) {
        setBrightness(value);
        return this;
    }
}
