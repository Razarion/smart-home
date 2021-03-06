package ch.beatronix.smarthome.model;

import java.util.Objects;

public class Bulb {
    private String name;
    private String id;
    private String ip;
    private int port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Bulb name(String name) {
        setName(name);
        return this;
    }

    public Bulb id(String id) {
        setId(id);
        return this;
    }

    public Bulb ip(String ip) {
        setIp(ip);
        return this;
    }

    public Bulb port(int port) {
        setPort(port);
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
        Bulb bulb = (Bulb) o;
        return id.equals(bulb.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bulb{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
