package tech.tylor.twitter.repository;


import tech.tylor.twitter.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    Tag findByPhrase(String phrase);

}