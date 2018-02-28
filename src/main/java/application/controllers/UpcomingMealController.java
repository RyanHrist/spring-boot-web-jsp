package application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UpcomingMealController {

    @RequestMapping("/upcoming_meals")
    public String hostMeal() {
        return "upcomingMeals";
    }

}
