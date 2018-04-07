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
import javax.xml.crypto.Data;

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
        ModelAndView modelAndView = new ModelAndView();
        getAllMeals(request);
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    /**
     * Search method on the home page which is used to search the entered query to match anything similar to
     * the description, the city, country, or category of a meal
     * It then sets "foundMeals" array list into all meals found through the query
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
        ResultSet rs = Database.selectSimilarMeal(newConnection, searchQuery);
        ArrayList<Meals> foundMeals = new ArrayList<>();
        if (rs.first()){
            ArrayList<Meals> selectedMeals = Database.createMealsList(rs);
            for (Meals m : selectedMeals) {
                if (!m.mealHappened()) {
                    foundMeals.add(m);
                }
            }
        } else {
            modelAndView.addObject("nothingFound", "No meals have been found matching '"
                    + searchQuery + "'.");
        }
        session.setAttribute("foundMeals", foundMeals);
        modelAndView.setViewName("/home");
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }

    /**
     * Get all meals in the database and places them into "foundMeals" array list
     * @param request
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void getAllMeals(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        Connection newConnection = Database.connectDatabase();
        ResultSet allMealsSet = Database.selectAllMeals(newConnection);
        allMealsSet.first();
        ArrayList<Meals> allMeals = Database.createMealsList(allMealsSet);
        ArrayList<Meals> foundMeals = new ArrayList<>();
        for (Meals m : allMeals){
            if (!m.mealHappened()){
                foundMeals.add(m);
            }
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
        getAllMeals(request);

        if (userLogin != null && userPassword != null) {
            if (Database.login(userLogin, userPassword)){
                ResultSet rs = Database.selectUser(newConnection, userLogin);
                rs.first();
                User user = Database.createUser(rs);
                session.setAttribute("user", user);
            } else {
                session.setAttribute("loginFail", "Please enter a correct username and password.");
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
