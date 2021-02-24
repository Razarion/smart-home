package ch.beatronix.smarthome.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {
    @RequestMapping({ "/control", "/scene-config", "/bulb-config" })
    public String index() {
        return "forward:/index.html";
    }

}
