package Liftoff.climbwithme.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class BlogController{

    @RequestMapping(value="")
    public String index(Model model, @AuthenticationPrincipal OidcUser user) {

        String message = "Hello World";

        if (user != null) {
            model.addAttribute("user", user.getName());
        }
        model.addAttribute("message", message);

        return "blog/index";
    }

}
