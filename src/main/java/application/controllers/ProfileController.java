package application.controllers;

import application.Database;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(value = {"/", ""}, method = {RequestMethod.GET})
    public ModelAndView viewOwnProfile(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        session.setAttribute("viewingOwnProfile", true);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam("hostname") String hostname1 ,
                                   @RequestParam("location") String location1,
                                   @RequestParam("language") String language1,
                                   @RequestParam("description") String description1,
                                   HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        Connection newConnection = Database.connectDatabase();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
//        User user = new User();
//        user.setName(hostname1);
//        user.setCountry(location1);
//        user.setLanguage(language1);
//        //user.set(hostrating1);
//        user.setUserDescription(description1);
//        user.setImage(image1);
        try {
            Database.updateUser(user.getEmail(), null, hostname1, description1, location1, null,
                    null, null, null, language1, null, null, null,
                    null, null, null, null, null);
            ResultSet rs = Database.selectUser(newConnection, user.getEmail());
            user = Database.createUser(rs);
            session.setAttribute("user", user);
        } catch (SQLException e) {
            modelAndView.addObject("unsuccessMessage", "unable to update information");
            return modelAndView;
        }
        modelAndView.setViewName("/profile");
        return modelAndView;
    }
}