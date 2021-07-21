package fr.airfrance.poc.service.implementations;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.entity.UserPk;
import fr.airfrance.poc.exception.ResourceNotFoundException;
import fr.airfrance.poc.repository.UserRepository;
import fr.airfrance.poc.service.interfaces.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     This is the userService implementation to manage User entity
 * </p>
 *
 * @author TarekAbdennadher
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * <p>This method fetch all database users</p>
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * <p>
     * This method fetch all database users by pages
     * </p>
     *
     * @param pageable
     * @return
     */
    @Override
    public List<User> getAll(Pageable pageable) {
        return userRepository.getAllBy(pageable);
    }

    /**
     * <p>
     *     This method fetch all users with a given userName
     * </p>
     * @param userName
     * @return
     */
    @Override
    public List<User> getAllByUserName(String userName) {
        return userRepository.findByUserPkUserName(userName);
    }

    /**
     * <p>
     *     This method allow to get user by embeddedId (userName, birthdate, country)
     * </p>
     * @param userName
     * @param birthdate
     * @param country
     * @return
     */
    @Override
    public User getUserById(String userName, String birthdate, String country) {
        UserPk userPk = new UserPk(userName, birthdate, country);
        return userRepository.findById(userPk).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * <p>
     * This method create valid user (valid user respect all validation requirements
     * </p>
     *
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
