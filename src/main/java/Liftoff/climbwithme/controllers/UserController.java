package Liftoff.climbwithme.controllers;

import Liftoff.climbwithme.models.User;
import Liftoff.climbwithme.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute(new User());
        return "user/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user,
                      Errors errors, String verify) {
        if (errors.hasErrors()) {
            return "user/signup";
        }

        userDao.save(user);
        return "blog/index";
    }
}
