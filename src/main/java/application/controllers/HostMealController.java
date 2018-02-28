package application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HostMealController {

    @RequestMapping("/host_meal")
    public String hostMeal() {
        return "hostMeal";
    }
}
