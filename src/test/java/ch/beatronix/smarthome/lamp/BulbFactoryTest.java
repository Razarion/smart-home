package ch.beatronix.smarthome.lamp;

import ch.beatronix.smarthome.model.Bulb;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BulbFactoryTest {
    String TEST_1 = """
            HTTP/1.1 200 OK
            Cache-Control: max-age=3600
            Date:
            Ext:
            Location: yeelight://192.168.17.3:55443
            Server: POSIX UPnP/1.0 YGLC/1
            id: 0x000000001170297e
            model: color4
            fw_ver: 18
            support: get_prop set_default set_power toggle set_bright set_scene cron_add cron_get cron_del start_cf stop_cf set_ct_abx adjust_ct set_name set_adjust adjust_bright set_rgb set_hsv set_music set_wrgb
            power: on
            bright: 91
            color_mode: 3
            ct: 4271
            rgb: 16748326
            hue: 29
            sat: 85
            name:
            """;

    @Test
    void create() {
        Bulb bulb = BulbFactory.create(TEST_1);
        assertEquals("0x000000001170297e", bulb.getId());
        assertEquals("192.168.17.3", bulb.getIp());
        assertEquals(55443, bulb.getPort());
    }
}