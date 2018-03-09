package application.controllers;

import application.Database;
import application.models.Meals;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
@RequestMapping("/meal")
public class MealController {

    @RequestMapping(value = "/{mealId}", method = RequestMethod.GET)
    public ModelAndView hostMeal(@PathVariable("mealId") String mealId,
                                 HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        Meals selectedMeal = new Meals();
        User mealHost = new User();
        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();

        ResultSet rs;
        String getMeals = "SELECT * FROM Meals WHERE mealid='"+mealId+"'";
        rs=statement.executeQuery(getMeals);
        if(rs.next()) {
            // DOES THE MEAL ID EXIST:
            session.setAttribute("correctURL", true);
            // SELECTED MEAL INFORMATION:
            selectedMeal.setImage(rs.getString("mpicture"));
            selectedMeal.setDescription(rs.getString("description"));
            selectedMeal.setMealTitle(rs.getString("mtitle"));

            String getHost = "SELECT * FROM Users WHERE email='"+rs.getString("hemail")+"'";
            rs = statement.executeQuery(getHost);
            if (rs.next()) {
                // HOST OF MEAL INFORMATION:
                mealHost.setName(rs.getString("username"));
            }
        } else {
            session.setAttribute("correctURL", false);
        }

        session.setAttribute("selectedMeal", selectedMeal);
        session.setAttribute("mealHost", mealHost);
        modelAndView.setViewName("meal");

        return modelAndView;
    }

}
