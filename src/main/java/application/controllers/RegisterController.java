package application.controllers;

import application.Database;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("emailsignup") String email,
                                     @RequestParam("firstname") String firstName,
                                     @RequestParam("lastname") String lastName,
                                     @RequestParam("passwordsignup") String password,
                                     @RequestParam("country") String country,
                                     @RequestParam("day") String day,
                                     @RequestParam("month") String month,
                                     @RequestParam("year") String year,
                                     @RequestParam("ccnum") String ccnum,
                                     @RequestParam("cccvv") String cccvv,
                                     @RequestParam("ccexpmonth") String ccexpmonth,
                                     @RequestParam("ccexpyear") String ccexpyear,
                                     HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        Connection newConnection = Database.connectDatabase();
        try {
            if (!Database.insertUser(email, password, firstName + " " + lastName, null, country,
                    null, null, year + "-" + month + "-" + day, null, null,
                    ccnum, cccvv, null, null, null, null, null,
                    ccexpyear + "-" + ccexpmonth + "-1")) throw new SQLException();
            ResultSet rs = Database.selectUser(newConnection, email);
            User user = Database.createUser(rs);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/profile");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } catch (SQLException e) {
            modelAndView.setViewName("/register");
            modelAndView.addObject("unsuccessMessage", "This email already exists.");
        }
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }
}