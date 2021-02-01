package ch.beatronix.smarthome.lamp;

import ch.beatronix.smarthome.model.Bulb;

import java.util.Arrays;

public class BulbFactory {
    private static final String ID = "id:";
    private static final String LOCATION = "Location: yeelight://";

    public static Bulb create(String dataString) {
        String[] strings = dataString.split("\n");
        String id = Arrays.stream(strings).filter(s -> s.startsWith(ID)).findFirst().orElseThrow().substring(ID.length()).trim();
        String ipAndPort = Arrays.stream(strings).filter(s -> s.startsWith(LOCATION)).findFirst().orElseThrow().substring(LOCATION.length()).trim();
        String[] ipAndPortArray = ipAndPort.split(":");
        return new Bulb().id(id).ip(ipAndPortArray[0]).port(Integer.parseInt(ipAndPortArray[1]));
    }
}
