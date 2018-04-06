package application.controllers;

import application.Database;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }

    // TODO: UPDATE - remove default dates
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("emailsignup") String email,
                                     @RequestParam("firstname") String firstName,
                                     @RequestParam("lastname") String lastName,
                                     @RequestParam("passwordsignup") String password,
                                     @RequestParam("country") String country,
                                     @RequestParam("day") String day,
                                     @RequestParam("month") String month,
                                     @RequestParam("year") String year,
                                     @RequestParam("ccexpmonth") String ccexpmonth,
                                     @RequestParam("ccexpyear") String ccexpyear,
                                     HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        Connection newConnection = Database.connectDatabase();
        String username = firstName + " " + lastName;
        String dob = year + "-" + month + "-" + day;
        String ccexp = ccexpyear + "-" + ccexpmonth + "-" + "1";
        try{
            Database.insertUser(email, password, username, null, country, null, null,
                    dob, null, null, null, null, null,
                    null, null,null, null, ccexp);
            ResultSet rs = Database.selectUser(newConnection, email);
            User newUser = Database.createUser(rs);
            modelAndView.addObject("user", newUser);
            modelAndView.setViewName("redirect:/profile");
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);
        } catch(SQLException e) {
            modelAndView.setViewName("/register");
            modelAndView.addObject("unsuccessMessage", "This email already exists.");
        }
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }

    @ExceptionHandler
    public static boolean ensureProperEmailFormat(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean ensureProperPasswordFormat(String password, String password2) {
        if (password == password2){
            return true;
        }
        else {return false;}
    }

    public static boolean validateFormat(String email, String password, String password2) {
        if(ensureProperEmailFormat(email) && ensureProperPasswordFormat(password,password2)) {
            return true;
        }

        return false;
    }

}