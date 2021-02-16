package ch.beatronix.smarthome.model;

public class SimpleScene {
    private String name;
    private int id;

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

    public SimpleScene name(String name) {
        setName(name);
        return this;
    }

    public SimpleScene id(int id) {
        setId(id);
        return this;
    }
}
