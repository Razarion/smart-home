package ch.beatronix.smarthome.rest;

import ch.beatronix.smarthome.model.Bulb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.List;

@Path("/bulb")
public class BulbController {

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Bulb> getBulbs() {
        return Arrays.asList(new Bulb().name("Küche").id("38274230").ip("127.123.2.1").port(1976),
                new Bulb().name("Gang").id("5345345").ip("127.123.1.1").port(1976),
                new Bulb().name("Zimmer").id("7745323").ip("127.33.2.1").port(1976),
                new Bulb().name("Stube").id("905789").ip("127.123.34.12").port(1976));
    }
}
