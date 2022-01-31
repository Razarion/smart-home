package ch.beatronix.smarthome.service;

import ch.beatronix.smarthome.lamp.BulbCommandService;
import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.BulbState;
import ch.beatronix.smarthome.model.Hsb;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Random;

@Component
public class RandomService {
    // private static final Logger logger = LoggerFactory.getLogger(RandomService.class);
    @Inject
    private BulbService bulbService;
    @Inject
    private BulbCommandService bulbCommandService;
    private final Random random = new Random();

    // @Scheduled(fixedDelay = 500)
    public void random1() {
        Bulb bulb = bulbService.getBulbs().get((int) Math.round(bulbService.getBulbs().size() * random.nextDouble()));
        bulbCommandService.executeAsync(bulb, new BulbState().power(true).hsb(
                new Hsb()
                        .hue(360.0 * random.nextDouble())
                        .saturation(100.0)
                        .brightness(100.0 * random.nextDouble())));
    }

    // @Scheduled(fixedDelay = 5000)
    public void random2() {
        bulbService.getBulbs().forEach(bulb -> bulbCommandService.executeAsync(bulb, new BulbState().power(true).hsb(
                new Hsb()
                        .hue(360.0 * random.nextDouble())
                        .saturation(100.0 * random.nextDouble())
                        .brightness(100.0 * random.nextDouble()))));
    }

    // @Scheduled(fixedDelay = 5000)
    public void random3() {
        Hsb hsb = new Hsb()
                .hue(360.0 * random.nextDouble())
                .saturation(100.0 * random.nextDouble())
                .brightness(100.0 * random.nextDouble());
        bulbService.getBulbs().forEach(bulb -> bulbCommandService.executeAsync(bulb, new BulbState().power(true).hsb(hsb)));
    }
}
