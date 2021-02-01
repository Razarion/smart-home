package ch.beatronix.smarthome.rest;

import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.service.BulbService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.List;

@Path("/bulb")
public class BulbController {
    @Inject
    private BulbService bulbService;

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Bulb> getBulbs() {
        return bulbService.getBulbs();
    }
}
