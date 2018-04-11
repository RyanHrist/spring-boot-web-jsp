package application.controllers;

import application.Database;
import application.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = {"/{profileId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewOtherProfile(@PathVariable("profileId") String profileId,
                                         HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        Connection newConnection = Database.connectDatabase();
        HttpSession session = request.getSession();
        session.setAttribute("profileId", profileId);
        session.setAttribute("viewingOwnProfile", false);
        User profile = new User();
        if (!profileId.equals("")) {
            ResultSet rs = Database.selectUser(newConnection, Integer.parseInt(profileId));
            if (rs.first()) {
                profile = Database.createUser(rs);
                session.setAttribute("existingProfile", true);
                session.setAttribute("userBeingViewed", profile);
            } else {
                session.setAttribute("existingProfile", false);
            }
        }
        session.setAttribute("profile", profile);
        modelAndView.setViewName("profile");
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }
}