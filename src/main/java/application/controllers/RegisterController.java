package application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {

    String register = "register";

    @RequestMapping("/registration")
    public String register(Map<String, Object> model) {
        //model.put("test", register);
        return "register";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String registerUser(Model model, @RequestParam("emailsignup") String email,
                               @RequestParam("passwordsignup") String password) {
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
