package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.model.PersistContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class PersistService {
    public PersistContainer loadPersistContainer() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = getFile();
            System.out.println("Read from: " + file);
            PersistContainer persistContainer = mapper.readValue(file, PersistContainer.class);
            if (persistContainer == null) {
                persistContainer = new PersistContainer();
                savePersistContainer(persistContainer);
            }
            return persistContainer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePersistContainer(PersistContainer persistContainer) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = getFile();
            mapper.writeValue(file, persistContainer);
            System.out.println("Written to: " + file);
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
