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
import java.sql.Statement;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(value = {"/", ""}, method = {RequestMethod.GET})
    public ModelAndView viewProfile(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        session.setAttribute("viewingOwnProfile", true);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value = {"/{profileId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewProfile(@PathVariable("profileId") String profileId,
                              HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();
        HttpSession session = request.getSession();
        session.setAttribute("profileId", profileId);
        session.setAttribute("viewingOwnProfile", false);

        System.out.println("THIS FAR1");
        User profile = new User();//(User) session.getAttribute("user");
        if(!profileId.equals("")) {
            System.out.println("BUT FURTHER NOW");
            String sql = "SELECT * FROM Users WHERE userid='" + profileId + "'";
            ResultSet rs=statement.executeQuery(sql);
            System.out.println(sql);
            if(rs.next()) {
                // TODO: MAIN - fill in the rest of the profiles user info from Users table
                profile.setName(rs.getString("username"));
                profile.setUserDescription(rs.getString("description"));
                profile.setCccity(rs.getString("cccity"));
                profile.setCountry(rs.getString("cccountry"));
                profile.setProfilePicture(rs.getString("ppicture"));
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
