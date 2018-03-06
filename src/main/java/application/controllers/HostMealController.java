package application.controllers;

import application.Database;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class HostMealController {

    @RequestMapping(value = "/hostmeal", method = RequestMethod.GET)
    public ModelAndView getHostMealPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hostmeal");
        return modelAndView;
    }

    @RequestMapping(value = "hostmeal", method = RequestMethod.POST)
    public ModelAndView hostMeal(@RequestParam(value = "mealName", required = false) String mealName,
                                 @RequestParam(value = "mealDescription", required = false) String mealDescription,
                                 @RequestParam(value = "cancellation", required = false) String mealCancellation,
                                 @RequestParam(value = "city", required = false) String city,
                                 HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();


        if(!mealName.equals("") && !mealDescription.equals("") && !mealCancellation.equals("") && !city.equals("")) {
            Connection newConnection = Database.connectDatabase();
            Statement statement = newConnection.createStatement();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            String insert = "INSERT Meals SET description = '"+ mealDescription + "', cancelationfee = '" + mealCancellation + "'"
                    + ", hemail='"+user.getEmail()+"'";

            try{
                // SQL update
                statement.executeUpdate(insert);
                // Adds an object to the view ---- this is broken since update, hardcoding static variables now
                // Attempt at starting session, TODO
                //TODO ADD MEALS TO THE USERS HOSTED MEALS
                return modelAndView;
            } catch(SQLException e) {
                // TODO: Front end team: create a popup that says that email is already used.
                modelAndView.setViewName("hostmeal");
                modelAndView.addObject("unsuccessMessage", "Error creating meal.");
                return modelAndView;
            }

        }

        modelAndView.setViewName("hostmeal");
        return modelAndView;
    }
}
