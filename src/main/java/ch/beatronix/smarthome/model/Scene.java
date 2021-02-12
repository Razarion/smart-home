package ch.beatronix.smarthome.model;

import java.util.Map;
import java.util.Objects;

public class Scene {
    private String name;
    private int id;
    private Map<String, BulbState> bulbStates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, BulbState> getBulbStates() {
        return bulbStates;
    }

    public void setBulbStates(Map<String, BulbState> bulbStates) {
        this.bulbStates = bulbStates;
    }

    public Scene name(String name) {
        setName(name);
        return this;
    }

    public Scene id(int id) {
        setId(id);
        return this;
    }

    public Scene bulbStates(Map<String, BulbState> bulbStates) {
        setBulbStates(bulbStates);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scene scene = (Scene) o;
        return id == scene.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
