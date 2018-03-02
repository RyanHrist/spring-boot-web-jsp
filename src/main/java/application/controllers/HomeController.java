package application.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import application.Database;
import application.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView loginUser(@RequestParam(value = "loginUsername", required = false) String userLogin,
                                  @RequestParam(value = "loginPassword", required = false) String userPassword) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();


        // Can be null because it is not required (since on homepage and always on)

        /*
        TODO: Setup login SQL code, and on login, set all "RegisterController"
         */
        /**
        if (userLogin != null && userPassword != null) {
            Connection newConnection = Database.connectDatabase();
            Statement statement = newConnection.createStatement();
            String sql = "SELECT username, pass FROM Users where username='"+userLogin+"' and pass'"+userPassword+"'";
            ResultSet rs = statement.executeQuery(sql);
            String id = null;
            while(rs.next())
            {
                id=rs.getString("id");
            }

            if (id != null) {
                System.out.println("Login Success");
                RegisterController.loggedIn = true;
            } else {
                System.out.println("Login failed");
            }
        }
         */


        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping("logout")
    public ModelAndView logoutUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        UserController.logUserOut();
        return modelAndView;
    }

}