package Liftoff.climbwithme.controllers;

import Liftoff.climbwithme.models.PartnerReq;
import Liftoff.climbwithme.models.Post;
import Liftoff.climbwithme.models.data.PartnerReqDao;
import Liftoff.climbwithme.models.data.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class BlogController{

    @Autowired
    private PostDao postDao;

    @Autowired
    private PartnerReqDao partnerReqDao;

    @RequestMapping(value="")
    public String index(Model model, @AuthenticationPrincipal OidcUser user) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("partnerReqs", partnerReqDao.findAll());

        return "blog/index";
    }

    @RequestMapping(value = "newPost", method = RequestMethod.GET)
    public String newPost(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute(new Post());
        model.addAttribute("user", user);

        return "blog/newPost";
    }

    @RequestMapping(value = "newPost", method = RequestMethod.POST)
    public String newPost(@ModelAttribute @Valid Post newPost,
                          Errors errors, @AuthenticationPrincipal OidcUser user, Model model) {
        newPost.setUser(user.getNickName());

        if (errors.hasErrors()) {

            model.addAttribute("user", user);
            return "blog/newPost";
        }

        postDao.save(newPost);
        model.addAttribute("user", user);

        return "redirect:/";
    }

    @RequestMapping(value = "newPartnerReq")
    public String newPartnerReq(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute(new PartnerReq());
        model.addAttribute("user", user);

        return "requests/newPartnerReq";
    }

    @RequestMapping(value = "newPartnerReq", method = RequestMethod.POST)
    public String newPartnerReq(@ModelAttribute @Valid PartnerReq newPartnerReq,
                          Errors errors, @AuthenticationPrincipal OidcUser user, Model model) {
        newPartnerReq.setUser(user.getNickName());


        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "requests/newPartnerReq";
        }

        partnerReqDao.save(newPartnerReq);
        model.addAttribute("user", user);

        return "redirect:/";
    }

}
