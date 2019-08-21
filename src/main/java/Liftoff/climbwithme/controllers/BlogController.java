package Liftoff.climbwithme.controllers;

import Liftoff.climbwithme.models.PartnerReq;
import Liftoff.climbwithme.models.Post;
import Liftoff.climbwithme.models.data.PartnerReqDao;
import Liftoff.climbwithme.models.data.PostDao;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.okta.spring.boot.oauth.Okta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class BlogController{

    @Autowired
    private PostDao postDao;

    @Autowired
    private PartnerReqDao partnerReqDao;

    @RequestMapping(value="")
    public String index(Model model, @AuthenticationPrincipal OidcUser user) {

        String message = "Hello World";

        if (user != null) {
            model.addAttribute("user", user.getName());
        }

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("partnerReqs", partnerReqDao.findAll());
        model.addAttribute("message", message);

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
        newPost.setUser(user.getEmail());

        if (errors.hasErrors()) {
            return "blog/newPost";
        }

        postDao.save(newPost);

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
        newPartnerReq.setUser(user.getEmail());

        if (errors.hasErrors()) {
            return "requests/newPartnerReq";
        }

        partnerReqDao.save(newPartnerReq);

        return "redirect:/";
    }

}
