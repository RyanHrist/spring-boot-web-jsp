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
        public ModelAndView showRegistrationPage(ModelAndView modelAndView){
            modelAndView.setViewName("account");
            return modelAndView;
        }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam("name") String name1,
                                     @RequestParam("email") String email1,
                                     @RequestParam("pass" ) String pass,
                                     @RequestParam("location") String location1,
                                     @RequestParam("num") String ccnum1,
                                     @RequestParam("cctype") String cctype1,
                                     @RequestParam("ccdigits") String ccdigits1,

                                     HttpServletRequest request) throws SQLException, ClassNotFoundException {

        ModelAndView modelAndView = new ModelAndView();


        try {
            Database.updateUser(email1, pass, name1, null, location1, null,
                    null, null, null, null, ccnum1, ccdigits1, null,
                    null, null, null, null, null);
        }catch(SQLException e) {
        modelAndView.setViewName("/account");
        modelAndView.addObject("unsuccessMessage", "unable to update information");
        return modelAndView;
        }
        return modelAndView;
    }


}
