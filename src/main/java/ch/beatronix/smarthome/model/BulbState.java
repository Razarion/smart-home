package ch.beatronix.smarthome.model;

public class BulbState {
    private Boolean power;
    private Hsv hsv;

    public Boolean getPower() {
        return power;
    }

    public void setPower(Boolean power) {
        this.power = power;
    }

    public Hsv getHsv() {
        return hsv;
    }

    public void setHsv(Hsv hsv) {
        this.hsv = hsv;
    }

    public BulbState power(Boolean power) {
        setPower(power);
        return this;
    }

    public BulbState hsv(Hsv hsv) {
        setHsv(hsv);
        return this;
    }
}
