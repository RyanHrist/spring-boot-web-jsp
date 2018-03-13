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
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

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
        Statement statement = newConnection.createStatement();
        HttpSession session = request.getSession();
        session.setAttribute("profileId", profileId);
        session.setAttribute("viewingOwnProfile", false);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        ParsePosition p = new ParsePosition(0);
        User profile = new User();//(User) session.getAttribute("user");
        if(!profileId.equals("")) {
            String sql = "SELECT * FROM Users WHERE userid='" + profileId + "'";
            ResultSet rs=statement.executeQuery(sql);
            System.out.println(sql);
            if(rs.next()) {
                // TODO: MAIN - fill in the rest of the profiles user info from Users table
                profile.setUserID(rs.getInt("userid"));
                profile.setEmail(rs.getString("email"));
                profile.setPassword(rs.getString("password"));
                profile.setName(rs.getString("username"));
                profile.setUserDescription(rs.getString("description"));
                profile.setCountry(rs.getString("country"));
                profile.setCurrency(rs.getString("currency"));
                profile.setImage(rs.getString("ppicture"));
                profile.setDateOfBirth(df.parse(rs.getString("dob"),p));
                profile.setGender(rs.getString("gender"));
                profile.setLanguage(rs.getString("language"));
                profile.setCcnumber(rs.getString("ccnum"));
                profile.setCccvv(rs.getString("cccvv"));
                profile.setCccountry(rs.getString("cccountry"));
                profile.setCcprovince(rs.getString("ccprovince"));
                profile.setCccity(rs.getString("city"));
                profile.setCcadress((rs.getString("ccaddress")));
                profile.setCcpostal((rs.getString("ccpostal")));
                profile.setCcexp(df.parse(rs.getString("ccexp"),p));
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
