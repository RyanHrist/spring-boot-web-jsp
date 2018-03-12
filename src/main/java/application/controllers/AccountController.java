package application.controllers;

import application.Database;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
@RequestMapping("/account")
public class AccountController {

        @RequestMapping(value="", method = RequestMethod.GET)
        public ModelAndView showRegistrationPage(ModelAndView modelAndView){
            modelAndView.setViewName("account");
            return modelAndView;
        }

//    @RequestMapping("/account")
//    public String hostMeal() {
//        return "account";
//    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView registerUser(//@RequestParam("emailsignup") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("email") String email,
                                     @RequestParam("location") String location,
                                     @RequestParam("ccnum") String ccnum,
                                     @RequestParam("cctype") String cctype,
                                     @RequestParam("ccdigits") Date ccdigits,

                                     HttpServletRequest request) throws SQLException, ClassNotFoundException {

        ModelAndView modelAndView = new ModelAndView();

        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();


        //String fullName = name;
        String update = "INSERT Users\n" +
                //"set    email='" + email + "', pass = '" + password +"',\n" +
                "	username='" + name + "', description=null, country=null, currency=null, ppicture=null,\n" +
                "    dob='2012-01-01', gender=null, userlang=null, ccnum=null, cccvv=null, cccountry=null,\n" +
                "    ccprovince=null, cccity=null, ccaddress=null, ccpostal=null, ccexp='2020-5-2'\n";

        ResultSet rs=statement.executeQuery("select * from Users");


        return modelAndView;
    }


}
