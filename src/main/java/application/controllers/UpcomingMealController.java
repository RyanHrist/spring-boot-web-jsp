package application.controllers;

import application.Database;
import application.models.Meals;
import application.models.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class UpcomingMealController {

    @RequestMapping(value="/upcoming_meals")
    public ModelAndView hostMeal(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        if(request.getSession().getAttribute("user") != null) {
            Connection newConnection = Database.connectDatabase();
            Statement statement = newConnection.createStatement();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String getMeals = "SELECT * FROM Meals WHERE hemail='"+user.getEmail()+"'";
            ResultSet rs=statement.executeQuery(getMeals);

            ArrayList<Meals> upcomingMeals = new ArrayList<>();

            while (rs.next()) {
                Meals meal = new Meals();
                meal.setImage(rs.getString("mpicture"));
                meal.setDescription(rs.getString("description"));
                upcomingMeals.add(meal);
            }

            session.setAttribute("upcomingMeals", upcomingMeals);
            modelAndView.setViewName("upcomingMeals");
        } else {
            modelAndView.setViewName("upcomingMeals");
        }
        return modelAndView;
    }

}
