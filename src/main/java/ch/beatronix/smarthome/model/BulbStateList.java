package ch.beatronix.smarthome.model;

import java.util.List;

public class BulbStateList {
    private BulbState bulbState;
    private List<String> bulbIds;

    public BulbState getBulbState() {
        return bulbState;
    }

    public void setBulbState(BulbState bulbState) {
        this.bulbState = bulbState;
    }

    public List<String> getBulbIds() {
        return bulbIds;
    }

    public void setBulbIds(List<String> bulbIds) {
        this.bulbIds = bulbIds;
    }
}
