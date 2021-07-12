package fr.airfrance.poc;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends PocApplicationTests {

    @Autowired
    UserRepository userRepository;

    private User firstUser;

    @Before
    public void setUp() {
        if (userRepository.findAll().size() > 0)
            userRepository.deleteAll();
        firstUser = new User("user1","admin","first", "user","16/05/1991","France");
    }

    @Test
    public void saveUserTestCase() {
        firstUser = userRepository.save(firstUser);
        assertThat(firstUser.getId()).isNotNull();
    }

    @Test
    public void findByLoginTestCase() {
        firstUser = userRepository.save(firstUser);
        assertThat(userRepository.findByLogin("admin").equals(firstUser.getLogin()));
    }
}
