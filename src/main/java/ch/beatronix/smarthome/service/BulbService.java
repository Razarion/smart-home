package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.model.Bulb;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BulbService {
    private final Set<Bulb> bulbs = new HashSet<>();

    public List<Bulb> getBulbs() {
        return new ArrayList<>(bulbs);
    }

    public void onBulbDiscovered(Bulb bulb) {
        bulbs.add(bulb);
    }
}
