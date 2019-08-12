package Liftoff.climbwithme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("blog")
public class BlogController{

    @RequestMapping(value="")
    public String index(Model model) {

        String message = "Hello World";

        model.addAttribute("message", message);

        return "blog/index";
    }

}
