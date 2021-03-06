package fr.airfrance.poc.user;

import fr.airfrance.poc.PocApplicationTests;
import fr.airfrance.poc.dto.UserDto;
import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.entity.UserPk;
import fr.airfrance.poc.entity.enumeration.Gender;
import fr.airfrance.poc.exception.ResourceNotFoundException;
import fr.airfrance.poc.repository.UserRepository;
import fr.airfrance.poc.service.implementations.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * <p>
 *     This class test main userService methods
 * </p>
 */
public class UserServiceTest extends PocApplicationTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService =new UserServiceImpl(userRepository);

    User firstUser = new User();
    User secondUser = new User();
    User invalidUser = new User();

    /**
     * <p>
     *     Set up initial values before every test execution
     * </p>
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        UserPk firstUserPk = new UserPk("abdennadher", "16/05/1991", "FRANCE");
        firstUser.setUserPk(firstUserPk);
        firstUser.setPhoneNumber("0676337561");
        firstUser.setGender(Gender.M);

        UserPk secondUserPk = new UserPk("abdennadher", "13/08/1996", "FRANCE");
        secondUser.setUserPk(secondUserPk);
        secondUser.setPhoneNumber("0616777531");
        secondUser.setGender(Gender.M);

        UserPk invalidUserPk = new UserPk("abdennadher", "13-08-1996", "FRANCE");
        invalidUser.setUserPk(invalidUserPk);

    }

    /**
     * <p>
     *     This method allow to test getAll method
     * </p>
     */
    @Test
    public void getAllUserTestCase() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(firstUser, secondUser));
        assertThat(userService.getAll().size()).isEqualTo(2);
    }

    /**
     * <p>
     *     This method allow to test getAllByUserName method
     * </p>
     */
    @Test
    public void getAllByUserNameTestCase() {
        when(userRepository.findByUserPkUserName(any())).thenReturn(Arrays.asList(firstUser, secondUser));
        assertThat(userService.getAllByUserName("abdennadher").size()).isEqualTo(2);
    }

    /**
     * <p>
     *     This method allow to test getUserById if exist and throw ResourceNotFound exception if it doesn't
     * </p>
     */
    @Test
    public void getUserByTestCase() {
        // user exist
        when(userRepository.findById(any())).thenReturn(Optional.of(firstUser));
        assertThat(userService.getUserById("abdennadher","16/05/1991", "FRANCE").getUserPk()).isEqualTo(firstUser.getUserPk());

        //user doesn't exist
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        Assert.assertThrows(ResourceNotFoundException.class, ()-> userService.getUserById("jack","16/05/1991", "FRANCE"));
    }

    /**
     * <p>
     *     This method allow to test createUser method
     * </p>
     */
    @Test
    public void createUserTestCase() {
        when(userRepository.save(firstUser)).thenReturn(firstUser);
        assertThat(userService.create(firstUser)).isNotNull();
    }

    /**
     * <p>
     *     This method allow to test birthdate format validation
     * </p>
     */
    @Test
    public void datePatternValidationTestCase() {
        // valid pattern
        assertThat(userService.isDatePatternValid(firstUser)).isTrue();

        // invalid pattern
        assertThat(userService.isDatePatternValid(invalidUser)).isFalse();
    }

    /**
     * <p>
     *     This method allow to test age calculation method
     * </p>
     */
    @Test
    public void calculateAge() {
        // The age must be 30 at 2021 and more from 2022 and above
        assertThat(userService.getAge(firstUser)).isGreaterThanOrEqualTo(30);
    }


}
