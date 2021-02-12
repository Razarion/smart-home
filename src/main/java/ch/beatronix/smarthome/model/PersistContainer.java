package ch.beatronix.smarthome.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistContainer {
    private Map<String, String> idNames;
    private List<Scene> scenes;

    public Map<String, String> getIdNames() {
        if (idNames == null) {
            idNames = new HashMap<>();
        }
        return idNames;
    }

    public void setIdNames(Map<String, String> idNames) {
        this.idNames = idNames;
    }

    public List<Scene> getScenes() {
        if (scenes == null) {
            scenes = new ArrayList<>();
        }
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}
