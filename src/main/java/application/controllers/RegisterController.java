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
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

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
                                     @RequestParam("passwordconfirm") String password2,
                                     @RequestParam("country") String country,
                                     @RequestParam("bday") Date bday,

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



        User user = new User();
        user.setEmail(email);
        user.setName(firstName + " " + lastName);
        user.setPassword(password);
        user.setCountry(country);
        user.setDateOfBirth(bday);


        try{
            // SQL update
            statement.executeUpdate(update);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/profile");

            System.out.println(user.getName());
            System.out.println(update);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            Database.disconnectDatabase(newConnection);
            return modelAndView;
        } catch(SQLException e) {
            // TODO: Front end team: create a popup that says that email is already used.
            modelAndView.setViewName("/register");
            modelAndView.addObject("unsuccessMessage", "This email already exists.");
            Database.disconnectDatabase(newConnection);
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