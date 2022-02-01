package ch.beatronix.smarthome.lamp;

import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.BulbState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class BulbCommandService {
    private int idGenerator = 1;
    private static final Logger logger = LoggerFactory.getLogger(BulbCommandService.class);
    private final ExecutorService executor = Executors.newFixedThreadPool(20);

    public void executeAsync(Bulb bulb, BulbState bulbState) {
        executor.submit(() -> execute(bulb, bulbState));
    }

    public void execute(Bulb bulb, BulbState bulbState) {
        try {
            String command = "";
            if (bulbState.getPower() != null) {
                if (bulbState.getPower()) {
                    command += "{ \"id\": " + generateId() + ", \"method\": \"set_power\", \"params\":[\"on\", \"smooth\", 30]}\r\n";
                } else {
                    command += "{ \"id\": " + generateId() + ", \"method\": \"set_power\", \"params\":[\"off\", \"smooth\", 30]}\r\n";
                }
            }
            if (bulbState.getHsb() != null) {
                command += "{\"id\":" + generateId() + ", \"method\": \"set_hsv\", \"params\":[" + bulbState.getHsb().getHue() + ", " + bulbState.getHsb().getSaturation() + ", \"smooth\", 30]}\r\n";
                command += "{\"id\":" + generateId() + ", \"method\": \"set_bright\", \"params\":[" + bulbState.getHsb().getBrightness() + ", \"smooth\", 30]}\r\n";
            }
            if (bulbState.getColorTemperature() != null) {
                command += "{\"id\":" + generateId() + ", \"method\": \"set_ct_abx\", \"params\":[" + bulbState.getColorTemperature() + ", \"smooth\", 30]}\r\n";
            }

            if (command.isBlank()) {
                return;
            }
            Socket socket = new Socket(bulb.getIp(), bulb.getPort());
            OutputStream output = socket.getOutputStream();
            output.write(command.getBytes(StandardCharsets.UTF_8));
            output.flush();
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // Only read one result line. Ignore NOTIFICATION message
            logger.info(reader.readLine() + " " + bulb + " command: " + command);
            socket.close();
        } catch (Exception e) {
            logger.info("Send command failed", e);
        }
    }

    private String generateId() {
        idGenerator++;
        if (idGenerator < 1) {
            idGenerator = 1;
        } else if (idGenerator > 10000) {
            idGenerator = 1;
        }
        return Integer.toString(idGenerator);
    }
}
