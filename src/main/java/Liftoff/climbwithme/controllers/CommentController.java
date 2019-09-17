package Liftoff.climbwithme.controllers;

import Liftoff.climbwithme.models.Comment;
import Liftoff.climbwithme.models.Post;
import Liftoff.climbwithme.models.data.CommentDao;
import Liftoff.climbwithme.models.data.PostDao;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "view/{postId}", method = RequestMethod.GET)
    public String viewComments(@AuthenticationPrincipal OidcUser user, @PathVariable int postId,
                               Model model) {

        Iterable<Post> allPosts = postDao.findAll();
        Optional<Post> parent = postDao.findById(postId);
        Post post = parent.get();

        Iterable<Comment> relevantComments = commentDao.findAllByParentId(postId);

        model.addAttribute("user", user);
        model.addAttribute("post", post);
        model.addAttribute("parentUser", post.getUser());
        model.addAttribute("relevantComments", relevantComments);
        return "comments/view";
    }

    @RequestMapping(value = "add/{postId}", method = RequestMethod.GET)
    public String addComment(Model model, @PathVariable int postId,
                             @AuthenticationPrincipal OidcUser user) {

        Iterable<Post> allPosts = postDao.findAll();
        Optional<Post> parent = postDao.findById(postId);
        Post post = parent.get();

        model.addAttribute(new Comment(post));
        model.addAttribute("parent", post);
        model.addAttribute("user", user);

        return "comments/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addComment(@ModelAttribute @Valid Comment comment, Errors errors,
                             @AuthenticationPrincipal OidcUser user,
                             Model model) {

        Post post = comment.getParent();
        comment.setUser(user.getNickName());
        model.addAttribute("post", post);

        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "comments/add";
        }
        model.addAttribute("user", user);
        commentDao.save(comment);

        return "redirect:/comments/view/" + post.getId();
    }

}
