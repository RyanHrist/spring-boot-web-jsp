package application.controllers;

import application.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.Map;

@Controller
public class RegisterController {

    @RequestMapping("/registration")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String registerUser(Model model, @RequestParam("emailsignup") String email,
                               @RequestParam("firstname") String firstName,
                               @RequestParam("lastname") String lastName,
                               @RequestParam("passwordsignup") String password)
            throws SQLException, ClassNotFoundException {


        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();

        String name = firstName + " " + lastName;
        String update = "INSERT Users\n" +
                "set    email='" + email + "', pass = '" + password +"',\n" +
                "	username='" + name + "', description=null, country=null, currency=null, ppicture=null,\n" +
                "    dob='2012-01-01', gender=null, userlang=null, ccnum=null, cccvv=null, cccountry=null,\n" +
                "    ccprovince=null, cccity=null, ccaddress=null, ccpostal=null, ccexp='2020-5-2'\n";
        statement.executeUpdate(update);
        System.out.print(update);


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

        System.out.println("coming in controller    " + email +" : "+ password);
        model.addAttribute("message", "Hello Spring MVC Framework!");
        if(email.equals("rh12wb@brocku.ca")) // TODO: if email is not used in database, success
            return "successRegisterPopup";
        else
            return "unsuccessRegisterPopup";


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
