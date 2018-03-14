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
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

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
        Statement statement = newConnection.createStatement();
        User currentUser = (User) session.getAttribute("user");
        String currentUserEmail = currentUser.getEmail();
        String confirmBooking = "INSERT Attending SET gemail='"+currentUserEmail+"', mealid='"+mealId+"'";
        try {
            // SQL update
            statement.executeUpdate(confirmBooking);
            session.setAttribute("bookedMeal", "Congratulations on booking a meal!");
            modelAndView.setViewName("redirect:/upcoming_meals");

        }catch(SQLException e) {
            // TODO: Front end team: create a popup that says that email is already used.
            session.setAttribute("bookedMeal", "Unfortunately there was an error booking your meal!");
            modelAndView.setViewName("redirect:/upcoming_meals");
            Database.disconnectDatabase(newConnection);
            return modelAndView;
        }
        modelAndView.setViewName("upcomingMeals");
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
        Statement statement = newConnection.createStatement();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        ParsePosition p = new ParsePosition(0);

        ResultSet rs;
        String getMeals = "SELECT * FROM Meals WHERE mealid='"+mealId+"'";
        rs=statement.executeQuery(getMeals);
        if(rs.next()) {
            // DOES THE MEAL ID EXIST:
            session.setAttribute("correctURL", true);
            // SELECTED MEAL INFORMATION:
            selectedMeal.setMealID(rs.getInt("mealid"));
            selectedMeal.setCapacity(rs.getInt("capacity"));
            selectedMeal.setWithHost(rs.getString("hemail"));
            selectedMeal.setDate(rs.getString("dom"));
            selectedMeal.setMealTitle(rs.getString("mtitle"));
            selectedMeal.setImage(rs.getString("mpicture"));
            selectedMeal.setPrice(rs.getDouble("pricepp"));
            selectedMeal.setCategory(rs.getString("cetegory"));
            selectedMeal.setDescription(rs.getString("description"));
            selectedMeal.setCancelBy(rs.getString("cancelationtime"));
            selectedMeal.setCancelationFee(rs.getDouble("cancelationfee"));
            selectedMeal.setCountry(rs.getString("country"));
            selectedMeal.setCity(rs.getString("city"));
            selectedMeal.setAddress(rs.getString("address"));
            selectedMeal.setPostal(rs.getString("postal"));
            String getHost = "SELECT * FROM Users WHERE email='"+rs.getString("hemail")+"'";
            rs = statement.executeQuery(getHost);
            if (rs.next()) {
                // HOST OF MEAL INFORMATION:
                mealHost.setName(rs.getString("username"));
                mealHost.setUserID(rs.getInt("userid"));
                mealHost.setEmail(rs.getString("email"));
                mealHost.setPassword(rs.getString("password"));
                mealHost.setName(rs.getString("username"));
                mealHost.setUserDescription(rs.getString("description"));
                mealHost.setCountry(rs.getString("country"));
                mealHost.setCurrency(rs.getString("currency"));
                mealHost.setImage(rs.getString("ppicture"));
                mealHost.setDateOfBirth(df.parse(rs.getString("dob"),p));
                mealHost.setGender(rs.getString("gender"));
                mealHost.setLanguage(rs.getString("language"));
                mealHost.setCcnumber(rs.getString("ccnum"));
                mealHost.setCccvv(rs.getString("cccvv"));
                mealHost.setCccountry(rs.getString("cccountry"));
                mealHost.setCcprovince(rs.getString("ccprovince"));
                mealHost.setCccity(rs.getString("city"));
                mealHost.setCcadress((rs.getString("ccaddress")));
                mealHost.setCcpostal((rs.getString("ccpostal")));
                mealHost.setCcexp(df.parse(rs.getString("ccexp"),p));
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
