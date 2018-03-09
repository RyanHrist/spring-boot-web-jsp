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
import javax.servlet.http.HttpServletResponse;
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


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("emailsignup") String email,
                                     @RequestParam("firstname") String firstName,
                                     @RequestParam("lastname") String lastName,
                                     @RequestParam("passwordsignup") String password,
                                     HttpServletRequest request) throws SQLException, ClassNotFoundException {

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


//        String email1, pass, username, country;
//        Date dob;
        User user = null;
        while(rs.next()) {
            user = new User();
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("pass"));
            user.setName(rs.getString("username"));
        /*
        TODO: SET user.set FOR ALL VALUES FROM SQL
        Done by nik
        */

            user.setDateOfBirth(rs.getDate("dob"));

            //rs.get country and gender and language isnt a method
            ///user.setCountry(rs.getCountry("country"));
            ///user.setCurrency(rs.getCurrency("currency"));
            ///user.setLanguage(rs.getLanguage("userlang"));
            ///user.setGender(rs.getGender("Gender"));

            user.setCountry(rs.getString("country"));
            user.setCurrency(rs.getString("currency"));
            user.setLanguage(rs.getString("userlang"));
            user.setGender(rs.getString("Gender"));

            //credit card info
            user.setCcadress(rs.getString("ccadress"));
            user.setCccity(rs.getString("cccity"));
            user.setCccountry(rs.getString("cccountry"));
            user.setCcprovince(rs.getString("ccprovince"));
            user.setCcpostal(rs.getString("ccpostal"));
            user.setCcvv(rs.getString("cccvv"));
            user.setCcexp(rs.getString("ccexp"));
            user.setCcnumber(rs.getString("ccnum"));

            user.setUserDescription(rs.getString("description"));
        }

        try{
            // SQL update
            statement.executeUpdate(update);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/profile");

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

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

}
