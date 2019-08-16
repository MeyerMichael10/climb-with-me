package Liftoff.climbwithme.controllers;

import Liftoff.climbwithme.models.User;
import Liftoff.climbwithme.models.data.UserDao;
import Liftoff.climbwithme.service.SecurityService;
import Liftoff.climbwithme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;


    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute("user", new User());
        return "user/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user,
                      Errors errors, String verify) {
        if (errors.hasErrors()) {
            return "user/signup";
        }

        userService.save(user);

        securityService.autoLogin(user.getUsername(), user.getVerifyPassword());

        return "redirect:/blog/index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null){
            model.addAttribute("error", "Your username or password is incorrect!");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out succesfully");
        }

        return "user/login";
    }
}
