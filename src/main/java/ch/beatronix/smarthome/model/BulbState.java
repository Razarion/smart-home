package ch.beatronix.smarthome.model;

public class BulbState {
    private Boolean power;
    private Hsb hsb;

    public Boolean getPower() {
        return power;
    }

    public void setPower(Boolean power) {
        this.power = power;
    }

    public Hsb getHsb() {
        return hsb;
    }

    public void setHsb(Hsb hsb) {
        this.hsb = hsb;
    }

    public BulbState power(Boolean power) {
        setPower(power);
        return this;
    }

    public BulbState hsb(Hsb hsb) {
        setHsb(hsb);
        return this;
    }
}
