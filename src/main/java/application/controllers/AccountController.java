package application.controllers;

import application.Database;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value="", method = RequestMethod.GET)
    public ModelAndView showAccountPage(ModelAndView modelAndView){
        modelAndView.setViewName("account");
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam("name") String name,
                                     @RequestParam("email") String email,
                                     @RequestParam("pass" ) String pass,
                                     @RequestParam("location") String country,
                                     @RequestParam("num") String ccnum,
                                     @RequestParam("ccdigits") String cccvv,
                                     HttpServletRequest request) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Database.updateUser(email, pass, name, null, country, null,
                    null, null, null, null, ccnum, cccvv, null,
                    null, null, null, null, null);
        }catch(SQLException e) {
            modelAndView.setViewName("/account");
            modelAndView.addObject("unsuccessMessage", "unable to update information");
            return modelAndView;
        }
        return modelAndView;
    }
}
