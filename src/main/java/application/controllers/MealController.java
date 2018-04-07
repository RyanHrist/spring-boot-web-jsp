package application.controllers;

import application.Database;
import application.models.Meals;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Meal Controller, in charge of all functionality relating to meal
 *
 * Controller mapped to "/meal" and "/meal/mealid"
 *
 * Meal id is from the database table Meals
 */
@Controller
@RequestMapping("/meal")
public class MealController {

    /**
     * This page, when selected takes the user to the book meal page of the specific meal selected
     * @param mealId
     * @param request
     * @return
     */
    @RequestMapping(value="/bookmeal/{mealId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView bookMeal(@PathVariable("mealId") String mealId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookmeal");
        return modelAndView;
    }

    /**
     * This page is for confirming the bookMeal, giving all important info on the meal which can be confirmed.
     * @param mealId
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value="/bookmeal/{mealId}/confirmation", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView bookMealConfirmation(@PathVariable("mealId") int mealId, HttpServletRequest request)
            throws ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        try {
            Database.insertAttending(mealId, currentUser.getEmail());
            session.setAttribute("bookedMeal", "Congratulations on booking a meal!");
            modelAndView.setViewName("redirect:/upcoming_meals");
        } catch(SQLException e) {
            // TODO: Front end team: create a popup that says that email is already used.
            session.setAttribute("bookedMeal", "Unfortunately there was an error booking your meal!");
            modelAndView.setViewName("redirect:/upcoming_meals");
            return modelAndView;
        }
        return modelAndView;
    }

    /**
     * View meal used for viewing specific meals
     * @param mealId
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/{mealId}", method = RequestMethod.GET)
    public ModelAndView viewMeal(@PathVariable("mealId") String mealId,
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
            selectedMeal.setMealID(rs.getInt("mealid"));
            selectedMeal.setWithHost(rs.getString("hemail"));
            selectedMeal.setPrice(rs.getDouble("pricepp"));
            selectedMeal.setDate(rs.getString("dom"));
            selectedMeal.setAddress("saddress");

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
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }

}