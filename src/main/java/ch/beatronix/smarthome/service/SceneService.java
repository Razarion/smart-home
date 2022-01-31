package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.lamp.BulbCommandService;
import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.PersistContainer;
import ch.beatronix.smarthome.model.Scene;
import ch.beatronix.smarthome.model.SimpleScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SceneService {
    private static final Logger logger = LoggerFactory.getLogger(SceneService.class);
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

    public Scene getScene(int sceneId) {
        return persistService.loadPersistContainer().getScenes()
                .stream()
                .filter(scene -> scene.getId() == sceneId)
                .findFirst()
                .orElseThrow();
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
            Bulb bulb = bulbService.getBulb(bulbId);
            if(bulb != null) {
                bulbCommandService.executeAsync(bulb, bulbState);
            } else {
                logger.warn("Bulb does not exist: " + bulbId);
            }
        });
    }

    private int findNextId() {
        return persistService.loadPersistContainer().getScenes().stream()
                .map(Scene::getId)
                .max(Integer::compare)
                .orElse(0) + 1;
    }

    public List<SimpleScene> getSimpleScenes() {
        return persistService.loadPersistContainer()
                .getScenes()
                .stream()
                .map(Scene::toSimpleScene)
                .collect(Collectors.toList());
    }
}
