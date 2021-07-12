package fr.airfrance.poc.service.interfaces;

import fr.airfrance.poc.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    List<User> getAll(Pageable pageable);

    Optional<User> getUserByLogin(String login);

    User create(User user);
}
