package tech.tylor.twitter.controller;

import tech.tylor.twitter.model.Tweet;
import tech.tylor.twitter.model.TweetDisplay;
import tech.tylor.twitter.model.User;
import tech.tylor.twitter.service.TweetService;
import tech.tylor.twitter.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TweetController {

    private UserService userService;
    private TweetService tweetService;

    public TweetController(UserService userService, TweetService tweetService) {
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @GetMapping(value = {"/meeps", "/"})
    public String getFeed(@RequestParam(value="filter", required = false) String filter, Model model) {
        User loggedInUser = userService.getLoggedInUser();
        List<TweetDisplay> tweets = tweetService.findAll();

        if (filter == null) {
            filter = "all";
        }

        if (filter.equalsIgnoreCase("following")) {
            List<User> following = loggedInUser.getFollowing();
            tweets = tweetService.findAllByUsers(following);
            model.addAttribute("filter", "following");
        } else {
            tweets = tweetService.findAll();
            model.addAttribute("filter", "all");
        }
        model.addAttribute("tweetList", tweets);
        return "feed";
    }

    @GetMapping("/meeps/new")
    public String getTweetForm(Model model) {
        model.addAttribute("tweet", new Tweet());
        return "new-tweet";
    }

    @PostMapping("/meeps")
    public String submitTweetForm(@Valid Tweet tweet, BindingResult bindingResult, Model model){
        User user = userService.getLoggedInUser();
        if(!bindingResult.hasErrors()) {
            tweet.setUser(user);
            tweetService.save(tweet);
            model.addAttribute("successMessage", "Meep successfully created!");
            model.addAttribute("tweet", new Tweet());
        }
        return "new-tweet";
    }

    @GetMapping(value = "/meeps/{tag}")
    public String getTweetsByTag(@PathVariable String tag, Model model) {
        List<TweetDisplay> tweets = tweetService.findAllWithTags(tag);
        model.addAttribute("tweetList", tweets);
        model.addAttribute("tag", tag);
        return "tagged-tweets";
    }


}
