package ch.beatronix.smarthome.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BulbController.class);
        packages("ch.beatronix.smarthome.rest");
    }
}
