package application.controllers;

import application.Database;
import application.models.Meals;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
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
            System.out.print(imageURL + " " + mealDescription);
            Connection newConnection = Database.connectDatabase();
            Statement statement = newConnection.createStatement();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");


            Meals newMeal = new Meals();
            String insert = "INSERT Meals SET mtitle='"+mealName+"', description = '"+ mealDescription + "', mpicture = '" + imageURL + "'"
                    + ", hemail='"+user.getEmail()+"'";
            newMeal.setDescription(mealDescription);
            newMeal.setImage(imageURL);
            System.out.println(insert);

            try{
                // SQL update
                statement.executeUpdate(insert);
                // TODO: GET UNIQUE MEAL ID
//                int id =statement.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
//                System.out.println("SUCCESS");
//                modelAndView.setViewName("meal/" + id);
                modelAndView.setViewName("home");
                session.setAttribute("hostedMeal", newMeal);
                // Attempt at starting session, TODO
                //TODO ADD MEALS TO THE USERS HOSTED MEALS
                Database.disconnectDatabase(newConnection);
                return modelAndView;
            } catch(SQLException e) {
                // TODO: Front end team: create a popup that says that email is already used.
                modelAndView.setViewName("hostmeal");
                modelAndView.addObject("unsuccessMessage", "Error creating meal.");
                Database.disconnectDatabase(newConnection);
                return modelAndView;
            }

        }

        modelAndView.setViewName("hostmeal");
        return modelAndView;
    }
}
