package ch.beatronix.smarthome.lamp;

import ch.beatronix.smarthome.model.Bulb;
import ch.beatronix.smarthome.service.BulbService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

@Component
public class Ssdp {
    public static final String DISCOVER_MESSAGE_ROOT_DEVICE = "M-SEARCH * HTTP/1.1\r\nHOST: 239.255.255.250:1982\r\nMAN: \"ssdp:discover\"\r\nST: wifi_bulb\r\n";
    public static final int SSDP_SEARCH_PORT = 1982;
    @Inject
    private BulbService bulbService;

    public static void main(String[] args) {
        Ssdp ssdp = new Ssdp();
        ssdp.bulbService = new BulbService();
        ssdp.discover();
    }

    @PostConstruct
    public void discover() {
        try {
            // DatagramSocket socket = new DatagramSocket(43210, InetAddress.getLocalHost()); // PC
            DatagramSocket socket = new DatagramSocket(43210, InetAddress.getByName("0.0.0.0")); // RaspberryPi

            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        byte[] buf = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);
                        Bulb bulb = BulbFactory.create(new String(packet.getData()));
                        System.out.println("Received: " + bulb);
                        bulbService.onBulbDiscovered(bulb);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();

            byte[] pack = DISCOVER_MESSAGE_ROOT_DEVICE.getBytes(StandardCharsets.UTF_8);
            DatagramPacket p = new DatagramPacket(pack, pack.length);
            p.setAddress(InetAddress.getByName("239.255.255.250"));
            p.setPort(SSDP_SEARCH_PORT);
            System.out.println("---- start ssdp scan ----");
            socket.send(p);

            Thread.sleep(5000);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
