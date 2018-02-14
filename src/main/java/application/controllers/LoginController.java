package application.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class LoginController {

    @Value("${login.info}")
    private String info = "Login";

    @RequestMapping("/login")
    public String welcome(Map<String, Object> model) {
        //model.put("message", this.info);
        return "login";
    }

    public static boolean checkPasswordAndUser(String email, String password) {
        return true;
    }
}
