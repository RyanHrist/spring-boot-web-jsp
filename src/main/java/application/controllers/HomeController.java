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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value = "search", required = false) String searchQuery) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        if(searchQuery != null) {
            Connection newConnection = Database.connectDatabase();
            Statement statement = newConnection.createStatement();
            String sql = "SELECT * FROM Meals WHERE description LIKE'%"+searchQuery+"%' OR city LIKE '%"+searchQuery+"%'" +
                    "OR country LIKE '%"+searchQuery+"%' OR category LIKE '%"+searchQuery+"%'";
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Meals> foundMeals = new ArrayList<>();
            Meals meal = null;
            while (rs.next()) {
                meal = new Meals();
                meal.setDescription(rs.getString("description"));
                // TODO: Create a new meal for each result found, then figure out a way to display them in front end.
                foundMeals.add(meal);
            }

            if(meal == null) {
                modelAndView.addObject("nothingFound", "No meals have been found matching '" + searchQuery + "'.");
            }
        }

        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView loginUser(@RequestParam(value = "loginUsername", required = false) String userLogin,
                                  @RequestParam(value = "loginPassword", required = false) String userPassword,
                                  HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        if (userLogin != null && userPassword != null) {
            Connection newConnection = Database.connectDatabase();
            Statement statement = newConnection.createStatement();
            String sql = "SELECT * FROM Users where email='"+userLogin+"' and pass='"+userPassword+"'";
            ResultSet rs = statement.executeQuery(sql);


            User user = null;
            while(rs.next())
            {
                // TODO: Set the UserController variables to everything in database like following:
                // TODO: Also create all the static variables in the UserController (everything in DB).
                user = new User();
                user.setName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setUserDescription(rs.getString("description"));
                user.setCountry(rs.getString("country"));
                user.setCurrency(rs.getString("currency"));
                user.setDateOfBirth(rs.getString("dob"));
                user.setGender(rs.getString("gender"));
                user.setLanguage(rs.getString("language"));
                user.setCreditCardInformation(rs.getString(??));
                user.set
            }
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                System.out.println("Login Success");
            } else {
                System.out.println("Login failed");
            }
        }

        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping("logout")
    public ModelAndView logoutUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");

        // CLOSE SESSION
        HttpSession session = request.getSession();
        session.invalidate();

        return modelAndView;
    }

}
~
e32