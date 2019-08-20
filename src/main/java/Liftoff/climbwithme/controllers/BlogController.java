package Liftoff.climbwithme.controllers;

import Liftoff.climbwithme.models.Post;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class BlogController{

    @Autowired
    private PostDao postDao;

    @RequestMapping(value="")
    public String index(Model model, @AuthenticationPrincipal OidcUser user) {

        String message = "Hello World";

        if (user != null) {
            model.addAttribute("user", user.getName());
        }
        model.addAttribute("message", message);

        return "blog/index";
    }

    @RequestMapping(value = "newPost", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public String newPost(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute(new Post());
        model.addAttribute("user", user);

        return "blog/newPost";
    }

    @RequestMapping(value = "newPost", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public String newPost(@ModelAttribute @Valid Post newPost,
                          Errors errors, @RequestParam @AuthenticationPrincipal OidcUser user, Model model) {
        if (errors.hasErrors()) {
            return "blog/index";
        }
        newPost.setUser(user.getEmail());
        postDao.save(newPost);

        return "redirect:/blog/index";
    }

}
