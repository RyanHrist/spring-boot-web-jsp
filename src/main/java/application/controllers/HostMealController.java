package application.controllers;

import application.Database;
import application.models.Meals;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Host Meal Controller, in charge of all functionality relating to hosting a meal
 *
 * Controller mapped to "/hostmeal"
 */
@Controller
@RequestMapping(value = "/hostmeal")
public class HostMealController {

    /**
     * Generic method if a logged in user were to enter url.com/hostmeal
     * Sends user to page for creating a meal
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHostMealPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hostmeal");
        return modelAndView;
    }

    /**
     * Host Meal method is incharge of creating the meal in the database using the parameters (requestParam)
     * mentioned in the method, which are taken directly from the HTML inputs.
     * @param mealName
     * @param mealDescription
     * @param imageURL
     * @param city
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView hostMeal(@RequestParam(value = "mealName", required = false) String mealName,
                                 @RequestParam(value = "mealDescription", required = false) String mealDescription,
                                 @RequestParam(value = "imageURL", required = false) String imageURL,
                                 @RequestParam(value = "city", required = false) String city,
                                 HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        if(request.getSession().getAttribute("user") != null) {
            Connection newConnection = Database.connectDatabase();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            try{
                // TODO: Correct this to actual info provided by the form
                File pic = new File("D:\\Dropbox\\4F00\\spring-boot-web-jsp\\src\\main\\webapp\\resources\\static\\images\\i1.jpg");
                Database.insertMeal(user.getEmail(), "2018-07-07 17:00", mealName, pic, 5,
                        25.5, "Food", "Good", "2018-04-06 8:00", 1000.0, "Canada",
                        "Toronto", "123 example st", "123456");
                ResultSet rs = Database.selectMeal(newConnection, 0, user.getEmail(), "2018-07-07 17:00");
                Meals newMeal = Database.createMeal(rs);
                session.setAttribute("selectedMeal", newMeal);
                session.setAttribute("mealHost",user);
                session.setAttribute("correctURL", true);
                modelAndView.setViewName("meal");
                Database.disconnectDatabase(newConnection);
                return modelAndView;
            } catch(SQLException e) {
                modelAndView.setViewName("hostmeal");
                modelAndView.addObject("unsuccessMessage", "Error creating meal.");
                Database.disconnectDatabase(newConnection);
                return modelAndView;
            }
        }
//        modelAndView.setViewName("hostmeal");
        return modelAndView;
    }
}