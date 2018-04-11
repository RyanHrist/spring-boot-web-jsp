package application.controllers;

import application.Database;
import application.models.Meals;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView bookMealConfirmation(@PathVariable("mealId") String mealId, HttpServletRequest request)
            throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        Connection newConnection = Database.connectDatabase();
        User currentUser = (User) session.getAttribute("user");
        String currentUserEmail = currentUser.getEmail();
        try {
            ResultSet rs = Database.selectMeal(newConnection, Integer.parseInt(mealId),null, null);
            ResultSet rs2 = Database.selectAttending(newConnection, Integer.parseInt(mealId), null);
            int count = 0;
            rs2.first();
            do {
                count++;
            } while(rs2.next());
            Meals meal = Database.createMeal(rs);
            if (meal.getCapacity() <= count){
                throw new SQLException();
            } else {
                if (!Database.insertAttending(Integer.parseInt(mealId), currentUserEmail)) throw new SQLException();
                session.setAttribute("bookedMeal", "Congratulations on booking a meal!");
            }
        }catch(SQLException e) {
            if (Database.selectMeal(newConnection, Integer.parseInt(mealId), currentUserEmail, null) != null) session.setAttribute("bookedMeal", "Booking failed: cannot attend your own meal");
            else if(Database.selectAttending(newConnection, Integer.parseInt(mealId), currentUserEmail) != null) session.setAttribute("bookedMeal", "Booking failed: already attending this meal");
            else session.setAttribute("bookedMeal", "Booking failed: meal is at capacity");
            modelAndView.setViewName("redirect:/upcoming_meals");
        }
        modelAndView.setViewName("redirect:/upcoming_meals");
        Database.disconnectDatabase(newConnection);
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
        ResultSet rs = Database.selectMeal(newConnection, Integer.parseInt(mealId), null, null);
        if(rs.first()) {
            session.setAttribute("correctURL", true);
            selectedMeal = Database.createMeal(rs);
            rs = Database.selectUser(newConnection, selectedMeal.getWithHost());
            if (rs.first()) {
                mealHost = Database.createUser(rs);
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