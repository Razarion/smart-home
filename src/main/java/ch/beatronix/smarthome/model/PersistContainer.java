package ch.beatronix.smarthome.model;

import java.util.Map;

public class PersistContainer {
    private Map<String, String> idNames;

    public Map<String, String> getIdNames() {
        return idNames;
    }

    public void setIdNames(Map<String, String> idNames) {
        this.idNames = idNames;
    }

    public PersistContainer idNames(Map<String, String> idNames) {
        setIdNames(idNames);
        return this;
    }
}
