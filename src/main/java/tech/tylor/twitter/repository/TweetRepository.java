package tech.tylor.twitter.repository;

import tech.tylor.twitter.model.Tweet;
import tech.tylor.twitter.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
    List<Tweet> findAllByOrderByCreatedAtDesc();
    List<Tweet> findAllByUserOrderByCreatedAtDesc(User user);
    List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users);

    List<Tweet> findByTags_PhraseOrderByCreatedAtDesc(String tag);
}