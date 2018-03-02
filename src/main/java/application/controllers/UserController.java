package application.controllers;

import application.Database;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;

@Controller
@RequestMapping("/registration")
public class UserController {

    public static boolean loggedIn;
    public static String userFirstName;
    public static String userLastName;
    public static String userEmail;
    // TODO: Add all


    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("emailsignup") String email,
                                     @RequestParam("firstname") String firstName,
                                     @RequestParam("lastname") String lastName,
                                     @RequestParam("passwordsignup") String password) throws SQLException, ClassNotFoundException {

        ModelAndView modelAndView = new ModelAndView();

        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();

        String name = firstName + " " + lastName;
        String update = "INSERT Users\n" +
                "set    email='" + email + "', pass = '" + password +"',\n" +
                "	username='" + name + "', description=null, country=null, currency=null, ppicture=null,\n" +
                "    dob='2012-01-01', gender=null, userlang=null, ccnum=null, cccvv=null, cccountry=null,\n" +
                "    ccprovince=null, cccity=null, ccaddress=null, ccpostal=null, ccexp='2020-5-2'\n";

        ResultSet rs=statement.executeQuery("select * from Users");
        String email1, pass, username, country;
        Date dob;

        while(rs.next()) {
            email1 = rs.getString("email");
            pass = rs.getString("pass");
            username = rs.getString("username");
            country = rs.getString("country");
            dob = rs.getDate("dob");

            System.out.println("Email: " + email1);
            System.out.println("Password: " + pass);
            System.out.println("Username: " + username);
            System.out.println("Country: " + country);
            System.out.println("Dare of Birth: " + dob);
            System.out.println();
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        try{
            // SQL update
            statement.executeUpdate(update);
            // Adds an object to the view ---- this is broken since update, hardcoding static variables now
            modelAndView.addObject("user", user);
            // Attempt at starting session, TODO
            modelAndView.setViewName("redirect:/profile");

            loggedIn = true;
            userFirstName = firstName;
            userLastName = lastName;
            userEmail = email;

            return modelAndView;
        } catch(SQLException e) {
            // TODO: Front end team: create a popup that says that email is already used.
            modelAndView.setViewName("/register");
            modelAndView.addObject("unsuccessMessage", "This email already exists.");
            return modelAndView;
        }
    }

    @ExceptionHandler
    public static boolean ensureProperEmailFormat(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean ensureProperPasswordFormat(String password) {
        return true;
    }

    public static boolean validateFormat(String email, String password) {
        if(ensureProperEmailFormat(email) && ensureProperPasswordFormat(password)) {
            return true;
        }

        return false;
    }

    public static void logUserOut() {
        loggedIn = false;
    }

    public static void logUserIn(String email, String firstName, String lastName) {
        userEmail = email;
        userFirstName = firstName;
        userLastName = lastName;
        loggedIn = true;
    }

}
