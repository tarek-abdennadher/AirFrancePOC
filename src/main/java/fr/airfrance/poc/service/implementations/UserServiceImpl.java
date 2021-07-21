package fr.airfrance.poc.service.implementations;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.repository.UserRepository;
import fr.airfrance.poc.service.interfaces.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * This method fetch user by login
     * </p>
     *
     * @param login
     * @return
     */
    @Override
    public List<User> getAllByUserName(String login) {
        return userRepository.findByUserPkUserName(login);
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
