package fr.airfrance.poc.entity.validation;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.repository.UserRepository;
import fr.airfrance.poc.service.interfaces.UserService;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * <p>
 *     This component check user validation criteria
 * </p>
 *
 * @author TarekAbdennadher
 */
@Component
public class UserValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);
    private static final int MIN_AGE = 18;

    private UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    /**
     * <p>
     *     User class supports check
     * </p>
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    /**
     * <p>
     *     Include login, age and country validation
     * </p>
     * @param entity
     * @param errors
     */
    @Override
    public void validate(Object entity, Errors errors) {
        LOGGER.debug("User validation {}", entity);
        if (entity instanceof User) {
            User user = (User) entity;
            requiredAttributeValidation(user, errors);
            if (!errors.hasErrors()) {
                uniqueUserValidation(user, errors);
                validateBirthday(user, errors);
                validateCountry(user, errors);
            }
        }
    }

    /**
     * <p>
     *     permit only new users
     * </p>
     * @param user
     * @param errors
     */
    private void uniqueUserValidation(User user, Errors errors) {
        Optional<User> fetchedUser = userService.getUserByPk(user.getUserPk());
        if (fetchedUser.isPresent())
            errors.reject("unique user error", "This user exist");
    }

    /**
     * <p>
     *     validate only user with required field
     * </p>
     * @param user
     * @param errors
     */
    private void requiredAttributeValidation(User user, Errors errors) {
        if (user.getUserPk().getUserName() == null)
            errors.reject("user definition error", "userName is required");
        if (user.getUserPk().getBirthdate() == null)
            errors.reject("user definition error", "birthdate is required");
        if (user.getUserPk().getCountry() == null)
            errors.reject("user definition error", "country of residence is required");
    }

    /**
     * <p>
     *     accept only valid age (18 years old)
     * </p>
     * @param user
     * @param errors
     */
    private void validateBirthday(User user, Errors errors) {
        if (!userService.isDatePatternValid(user)) {
            errors.reject("date format error", "date must be in dd/MM/yyyy format");
        }
        try {
            if (userService.getAge(user) < MIN_AGE) {
                errors.reject("age error", "you have to be older than 18");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * <p>
     *     Accept only people from France
     * </p>
     * @param user
     * @param errors
     */
    private void validateCountry(User user, Errors errors) {
        if(!(user.getUserPk().getCountry().equalsIgnoreCase("France"))){
            errors.reject("not supported country", "you must be in France");
        }
    }
}
