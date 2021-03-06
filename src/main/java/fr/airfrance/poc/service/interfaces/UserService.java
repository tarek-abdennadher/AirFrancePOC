package fr.airfrance.poc.service.interfaces;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.entity.UserPk;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *     This is the service layer to manage User entity
 * </p>
 *
 * @author TarekAbdennadher
 */
public interface UserService {

    List<User> getAll();

    List<User> getAll(Pageable pageable);

    List<User> getAllByUserName(String login);

    User getUserById(String userName, String birthdate, String country);

    Optional<User> getUserByPk(UserPk userPk);

    User create(User user);

    Boolean isDatePatternValid(User user);

    int getAge(User user);
}
