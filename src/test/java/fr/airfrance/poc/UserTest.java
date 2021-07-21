package fr.airfrance.poc;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends PocApplicationTests {

    @Autowired
    UserRepository userRepository;
}
