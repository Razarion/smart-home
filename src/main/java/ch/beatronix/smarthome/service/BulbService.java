package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.lamp.BulbCommandService;
import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.BulbState;
import ch.beatronix.smarthome.model.PersistContainer;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class BulbService {
    @Inject
    private BulbCommandService bulbCommandService;
    @Inject
    private PersistService persistService;
    private final Set<Bulb> bulbs = new HashSet<>();

    public List<Bulb> getBulbs() {
        return new ArrayList<>(bulbs);
    }

    public void onBulbDiscovered(Bulb bulb) {
        bulbs.add(bulb);
        PersistContainer persistContainer = persistService.loadPersistContainer();
        bulb.setName(persistContainer.getIdNames().get(bulb.getId()));
    }

    public void bulbStates(BulbState bulbState, List<String> bulbIds) {
        bulbIds.forEach(bulbId -> bulbCommandService.sendBulbService(getBulb(bulbId), bulbState));
    }

    public void saveBulbNames(Map<String, String> bulbNames) {
        PersistContainer persistContainer = persistService.loadPersistContainer();
        persistContainer.setIdNames(bulbNames);
        persistService.savePersistContainer(persistContainer);
        bulbNames.forEach((id, name) -> getBulb(id).setName(name));
    }

    public Bulb getBulb(String bulbId) {
        return bulbs.stream()
                .filter(bulb -> bulb.getId().equals(bulbId))
                .findFirst()
                .orElse(null);
    }
}
