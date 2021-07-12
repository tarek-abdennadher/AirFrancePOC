package fr.airfrance.poc.repository;

import fr.airfrance.poc.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> getAllBy(Pageable pageable);
    Optional<User> findByLogin(String login);

}
