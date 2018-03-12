package application.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import application.Database;
import application.models.Meals;
import application.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Home Controller, in charge of all functionality on the home page & also login and logout
 *
 * Controller mapped to "", "/" which is the home page of the website
 */
@Controller
public class HomeController {

    /**
     * General method which returns the home screen with all meals in the database listed
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView getHome(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        // TODO: setup all meals.
        ModelAndView modelAndView = new ModelAndView();
        getAllMeals(request);

        modelAndView.setViewName("/home");
        return modelAndView;
    }

    /**
     * Search method on the home page which is used to search the entered query to match anything similar to
     * the description, the city, country, or category of a meal
     * It then sets "foundMeals" array list into all meals found through the query
     * TODO: make the search more indepth
     * @param searchQuery
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = {"/search/{searchQuery}", "/search/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView search(@PathVariable(value = "searchQuery", required = false) String searchQuery,
                               HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();

        String sql;
        if(searchQuery == null)
            sql = "SELECT * FROM Meals";
        else
            sql = "SELECT * FROM Meals WHERE description LIKE'%" + searchQuery + "%' OR city LIKE '%" + searchQuery + "%'" +
                    "OR country LIKE '%" + searchQuery + "%' OR category LIKE '%" + searchQuery + "%'";

        ResultSet rs = statement.executeQuery(sql);
        ArrayList<Meals> foundMeals = new ArrayList<>();
        Meals meal = null;
        while (rs.next()) {
            meal = new Meals();
            meal.setDescription(rs.getString("description"));
            meal.setImage(rs.getString("mpicture"));
            meal.setMealID(rs.getInt("mealid"));
            // TODO: Create a new meal for each result found, then figure out a way to display them in front end.
            foundMeals.add(meal);
        }
        session.setAttribute("foundMeals", foundMeals);

        if (meal == null) {
            modelAndView.addObject("nothingFound", "No meals have been found matching '"
                    + searchQuery + "'.");
        }

        modelAndView.setViewName("/home");
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }

    /**
     * Get all meals in the database and places them into "foundMeals" array list
     * TODO: narrow this search
     * @param request
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void getAllMeals(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();

        String getAllMeals = "SELECT * FROM Meals";
        ResultSet allMealsSet = statement.executeQuery(getAllMeals);
        ArrayList<Meals> foundMeals = new ArrayList<>();
        Meals meal;
        while (allMealsSet.next()) {
            meal = new Meals();
            meal.setDescription(allMealsSet.getString("description"));
            meal.setImage(allMealsSet.getString("mpicture"));
            meal.setMealID(allMealsSet.getInt("mealid"));
            // TODO: Create a new meal for each result found, then figure out a way to display them in front end.
            foundMeals.add(meal);
        }
        session.setAttribute("foundMeals", foundMeals);
        Database.disconnectDatabase(newConnection);
    }

    /**
     * User login method, takes the loginname and username and ensures that there is a match in the database,
     * used on the home page as it is a popup without a unique URL
     * Key note: activates the session (keep track of user login)
     * @param userLogin
     * @param userPassword
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView loginUser(@RequestParam(value = "loginUsername", required = false) String userLogin,
                                  @RequestParam(value = "loginPassword", required = false) String userPassword,
                                  HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();
        getAllMeals(request);

        if (userLogin != null && userPassword != null) {
            String sql = "SELECT * FROM Users where email='" + userLogin + "' and pass='" + userPassword + "'";
            ResultSet rs = statement.executeQuery(sql);
            User user = null;
            while (rs.next()) {
                // TODO: Set the UserController variables to everything in database like following:
                // TODO: Also create all the static variables in the UserController (everything in DB).
                user = new User();
                user.setName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
            }
            if (user != null) {
                session.setAttribute("user", user);
                System.out.println("Login Success");
            } else {
                session.setAttribute("loginFail", "Please enter a correct username and password.");
                System.out.println("Login failed");
            }
        }

        modelAndView.setViewName("/home");
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }

    /**
     * Logs the user out deactivating the session they were previously in.
     * @param request
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping("logout")
    public ModelAndView logoutUser(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        HttpSession session = request.getSession();
        session.invalidate();
        getAllMeals(request);
        return modelAndView;
    }
}

