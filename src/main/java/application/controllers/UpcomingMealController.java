package application.controllers;

import application.Database;
import application.models.Meals;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class UpcomingMealController {

    @RequestMapping(value = "/upcoming_meals")
    public ModelAndView hostMeal(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        if (request.getSession().getAttribute("user") != null) {
            Connection newConnection = Database.connectDatabase();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            ResultSet rs = Database.selectAttending(newConnection, 0, user.getEmail());
            ArrayList<Integer> mealIds = new ArrayList<>();
            while (rs.next()) {
                mealIds.add(rs.getInt("mealid"));
            }
            ArrayList<Meals> upcomingMeals = new ArrayList<>();
            for (Integer id : mealIds) {
                rs = Database.selectMeal(newConnection, id, null, null);
                if (rs.first()) {
                    Meals meal = Database.createMeal(rs);
                    upcomingMeals.add(meal);
                }
            }
            session.setAttribute("upcomingMeals", upcomingMeals);
            modelAndView.setViewName("upcomingMeals");
            Database.disconnectDatabase(newConnection);
        } else {
            modelAndView.setViewName("upcomingMeals");
        }
        return modelAndView;
    }
}