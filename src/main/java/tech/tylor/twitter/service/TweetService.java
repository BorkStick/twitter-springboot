package tech.tylor.twitter.service;

import tech.tylor.twitter.model.Tweet;
import tech.tylor.twitter.model.TweetDisplay;
import tech.tylor.twitter.model.User;

import java.util.List;

public interface TweetService {

    List<TweetDisplay> findAll();
    List<TweetDisplay> findAllByUser(User user);
    List<TweetDisplay> findAllByUsers(List<User> users);
    List<TweetDisplay> findAllWithTags(String tag);
    void save(Tweet tweet);
    void handleTags(Tweet tweet);
    List<TweetDisplay> formatTweets(List<Tweet> tweets);
    void addTagLinks (List<Tweet> tweets);
    void shortLinks(List<Tweet> tweets);

    List<TweetDisplay> formatTimestamps(List<Tweet> tweets);

}