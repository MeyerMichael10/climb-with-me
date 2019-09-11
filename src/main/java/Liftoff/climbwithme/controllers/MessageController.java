package Liftoff.climbwithme.controllers;

import Liftoff.climbwithme.models.Message;
import Liftoff.climbwithme.models.data.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value="view-all", method = RequestMethod.GET)
    public String viewAll(Model model, @AuthenticationPrincipal OidcUser user) {

        Iterable<Message> allMessages = messageDao.findAll();
        Iterable<Message> messages = messageDao.findAllByRecipient(user.getNickName());

        model.addAttribute("user", user);
        model.addAttribute("messages", messages);

        return "messages/view-all";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newMessage(Model model, @AuthenticationPrincipal OidcUser user) {

        model.addAttribute(new Message());
        model.addAttribute("user", user);

        return "messages/new";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newMessage(@ModelAttribute @Valid Message message, Errors errors,
                             @AuthenticationPrincipal OidcUser user, Model model) {

        message.setUser(user.getNickName());

        if (errors.hasErrors()) {
            return "messages/new";
        }

        messageDao.save(message);

        return "redirect:/";
    }

    @RequestMapping(value = "respond/{recipient}", method = RequestMethod.GET)
    public String newRespond(Model model, @PathVariable String recipient,
                             @AuthenticationPrincipal OidcUser user) {

        String messageRecipient = recipient;

        model.addAttribute(new Message(messageRecipient));
        model.addAttribute("user", user);
        model.addAttribute("recipient", recipient);

        return "messages/respond";
    }

    @RequestMapping(value = "respond", method = RequestMethod.POST)
    public String newRespond(@ModelAttribute @Valid Message message, Errors errors,
                             @AuthenticationPrincipal OidcUser user, Model model) {

        message.setUser(user.getNickName());

        if (errors.hasErrors()) {
            return "messages/respond";
        }

        messageDao.save(message);

        return "redirect:/";
    }
}
