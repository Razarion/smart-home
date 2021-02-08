package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.lamp.BulbCommandService;
import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.BulbState;
import ch.beatronix.smarthome.model.PersistContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = getFile();
            System.out.println("Read from: " + file);
            PersistContainer persistContainer = mapper.readValue(file, PersistContainer.class);
            if (persistContainer == null) {
                return;
            }
            if (persistContainer.getIdNames() == null) {
                return;
            }
            bulb.setName(persistContainer.getIdNames().get(bulb.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bulbStates(BulbState bulbState, List<String> bulbIds) {
        bulbs.stream()
                .filter(bulb -> bulbIds.contains(bulb.getId()))
                .forEach(bulb -> bulbCommandService.sendBulbService(bulb, bulbState));
    }

    public void saveBulbNames(Map<String, String> bulbNames) {

        try {
            PersistContainer persistContainer = new PersistContainer();
            persistContainer.setIdNames(bulbNames);
            ObjectMapper mapper = new ObjectMapper();
            File file = getFile();
            mapper.writeValue(file, persistContainer);
            System.out.println("Written to: " + file);
            bulbNames.forEach((id, name) -> {
                bulbs.stream().filter(bulb -> bulb.getId().equals(id)).forEach(bulb -> bulb.setName(name));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getFile() {
        try {
            String appPath = BulbService.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            return new File(appPath, "smart-home-config.json");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
