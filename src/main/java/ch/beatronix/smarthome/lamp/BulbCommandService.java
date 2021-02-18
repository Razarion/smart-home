package ch.beatronix.smarthome.lamp;

import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.BulbState;
import ch.beatronix.smarthome.model.Hsb;
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
    private final ExecutorService executor = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        BulbCommandService bulbCommandService = new BulbCommandService();
        // bulbCommandService.sendBulbService(new Bulb().ip("192.168.17.11").port(55443), new BulbState().power(true));
        // bulbCommandService.sendBulbService(new Bulb().ip("192.168.17.11").port(55443), new BulbState().power(false));
        bulbCommandService.sendBulbService(new Bulb().ip("192.168.17.11").port(55443), new BulbState().hsb(new Hsb().hue(100).saturation(100).brightness(100)));
    }

    public void sendBulbService(Bulb bulb, BulbState bulbState) {
        executor.submit(() -> execute(bulb, bulbState));
    }

    public void execute(Bulb bulb, BulbState bulbState) {
        try {
            String command = "";
            if (bulbState.getPower() != null) {
                if (bulbState.getPower()) {
                    command += "{ \"id\": 1, \"method\": \"set_power\", \"params\":[\"on\", \"smooth\", 30]}\r\n";
                } else {
                    command += "{ \"id\": 1, \"method\": \"set_power\", \"params\":[\"off\", \"smooth\", 30]}\r\n";
                }
            }
            if (bulbState.getHsb() != null) {
                command += "{\"id\":1, \"method\": \"set_hsv\", \"params\":[" + bulbState.getHsb().getHue() + ", " + bulbState.getHsb().getSaturation() + ", \"smooth\", 30]}\r\n";
                command += "{\"id\":1, \"method\": \"set_bright\", \"params\":[" + bulbState.getHsb().getBrightness() + ", \"smooth\", 30]}\r\n";
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
            System.out.println(reader.readLine() + " " + bulb + " command: " + command);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
