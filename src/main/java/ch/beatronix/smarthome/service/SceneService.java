package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.lamp.BulbCommandService;
import ch.beatronix.smarthome.model.PersistContainer;
import ch.beatronix.smarthome.model.Scene;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Component
public class SceneService {
    @Inject
    private PersistService persistService;
    @Inject
    private BulbService bulbService;
    @Inject
    private BulbCommandService bulbCommandService;

    public List<Scene> getScenes() {
        List<Scene> scenes = persistService.loadPersistContainer().getScenes();
        if (scenes != null) {
            return scenes;
        }
        return Collections.emptyList();
    }

    public Scene update(Scene scene) {
        PersistContainer persistContainer = persistService.loadPersistContainer();
        persistContainer.getScenes().remove(scene);
        persistContainer.getScenes().add(scene);
        persistService.savePersistContainer(persistContainer);
        return scene;
    }

    public Scene create() {
        Scene scene = new Scene().id(findNextId()).name("???");
        PersistContainer persistContainer = persistService.loadPersistContainer();
        persistContainer.getScenes().add(scene);
        persistService.savePersistContainer(persistContainer);
        return scene;
    }

    public void delete(int id) {
        PersistContainer persistContainer = persistService.loadPersistContainer();
        persistContainer.getScenes().removeIf(scene -> scene.getId() == id);
        persistService.savePersistContainer(persistContainer);
    }

    public void show(Scene scene) {
        scene.getBulbStates().forEach((bulbId, bulbState) -> {
            bulbCommandService.sendBulbService(bulbService.getBulb(bulbId), bulbState);
        });
    }

    private int findNextId() {
        return persistService.loadPersistContainer().getScenes().stream()
                .map(Scene::getId)
                .max(Integer::compare)
                .orElse(1);
    }

}
