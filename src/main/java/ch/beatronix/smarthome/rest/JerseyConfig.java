package ch.beatronix.smarthome.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BulbController.class);
        register(SceneController.class);
        register(CorsFilter.class);
    }
}
