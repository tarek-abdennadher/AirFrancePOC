package fr.airfrance.poc.entity.validation;

import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);
    private static final int MIN_AGE = 18;
    private static Pattern FRANCE_DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

    private UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

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
            validateLogin(user, errors);
            validateBirthday(user, errors);
            validateCountry(user, errors);
        }
    }

    /**
     * <p>
     *     login validation: prohibit duplicated login
     * </p>
     * @param user
     * @param errors
     */
    private void validateLogin(User user, Errors errors) {
        for (User u : userService.getAll()) {
            if(u.getLogin().equalsIgnoreCase(user.getLogin())){
                errors.reject("login exist", "User with this login already exists");
            }
        }
    }

    /**
     * <p>
     *     accept only valid age (18 years old)
     * </p>
     * @param user
     * @param errors
     */
    private void validateBirthday(User user, Errors errors) {
        if (!FRANCE_DATE_PATTERN.matcher(user.getBirthday()).matches()) {
            errors.reject("date format error", "date must be in dd/MM/yyyy format");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthday= LocalDate.parse(user.getBirthday(), formatter);
            LocalDate today = LocalDate.now();
            int age = Period.between(birthday, today).getYears();
            if (age < MIN_AGE) {
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
        if(!(user.getCountry().equalsIgnoreCase("France"))){
            errors.reject("not supported country", "you must be in France");
        }
    }
}
