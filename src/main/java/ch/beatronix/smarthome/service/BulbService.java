package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.lamp.BulbCommandService;
import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.BulbState;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BulbService {
    @Inject
    private BulbCommandService bulbCommandService;
    private final Set<Bulb> bulbs = new HashSet<>();

    public List<Bulb> getBulbs() {
        return new ArrayList<>(bulbs);
    }

    public void onBulbDiscovered(Bulb bulb) {
        bulbs.add(bulb);
    }

    public void bulbStates(BulbState bulbState, List<String> bulbIds) {
        bulbs.stream()
                .filter(bulb -> bulbIds.contains(bulb.getId()))
                .forEach(bulb -> bulbCommandService.sendBulbService(bulb, bulbState));
    }
}
