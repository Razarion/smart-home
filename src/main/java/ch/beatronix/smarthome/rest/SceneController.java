package ch.beatronix.smarthome.rest;

import ch.beatronix.smarthome.model.Scene;
import ch.beatronix.smarthome.model.SimpleScene;
import ch.beatronix.smarthome.service.SceneService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/scene")
public class SceneController {
    @Inject
    private SceneService sceneService;

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Scene> getScenes() {
        return sceneService.getScenes();
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Scene update(Scene scene) {
        return sceneService.update(scene);
    }

    @POST
    @Path("/create")
    @Produces("application/json")
    public Scene create() {
        return sceneService.create();
    }

    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") int id) {
        sceneService.delete(id);
    }

    @PUT
    @Path("/show")
    public void show(Scene scene) {
        sceneService.show(scene);
    }

    @GET
    @Path("/all-simple-scenes")
    @Produces("application/json")
    public List<SimpleScene> getSimpleScenes() {
        return sceneService.getSimpleScenes();
    }

    @PUT
    @Path("/activate-scene")
    @Produces("application/json")
    public void activateScene(int sceneId) {
        sceneService.show(sceneService.getScene(sceneId));
    }

}
