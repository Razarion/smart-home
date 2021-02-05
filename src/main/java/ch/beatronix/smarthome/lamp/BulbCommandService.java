package ch.beatronix.smarthome.lamp;

import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.model.BulbState;
import ch.beatronix.smarthome.model.Hsv;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Component
public class BulbCommandService {

    public static void main(String[] args) {
        BulbCommandService bulbCommandService = new BulbCommandService();
        // bulbCommandService.sendBulbService(new Bulb().ip("192.168.17.11").port(55443), new BulbState().power(true));
        // bulbCommandService.sendBulbService(new Bulb().ip("192.168.17.11").port(55443), new BulbState().power(false));
        bulbCommandService.sendBulbService(new Bulb().ip("192.168.17.11").port(55443), new BulbState().hsv(new Hsv().hue(100).saturation(100).brightness(100)));
    }

    public void sendBulbService(Bulb bulb, BulbState bulbState) {
        try {
            String command = null;
            if (bulbState.getPower() != null) {
                if (bulbState.getPower()) {
                    command = "{ \"id\": 1, \"method\": \"set_power\", \"params\":[\"on\", \"smooth\", 30]}\r\n";
                } else {
                    command = "{ \"id\": 1, \"method\": \"set_power\", \"params\":[\"off\", \"smooth\", 30]}\r\n";
                }
            }
            if (bulbState.getHsv() != null) {
                command = "{\"id\":1, \"method\": \"set_hsv\", \"params\":[" + bulbState.getHsv().getHue() + ", " + bulbState.getHsv().getSaturation() + ", \"smooth\", 30]}\r\n";
                command += "{\"id\":1, \"method\": \"set_bright\", \"params\":[" + bulbState.getHsv().getBrightness() + ", \"smooth\", 30]}\r\n";
            }

            if (command == null) {
                return;
            }
            Socket socket = new Socket(bulb.getIp(), bulb.getPort());
            OutputStream output = socket.getOutputStream();
            output.write(command.getBytes(StandardCharsets.UTF_8));
            output.flush();
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // Only read one result line. Ignore NOTIFICATION message
            System.out.println(reader.readLine() + " " + bulb);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}