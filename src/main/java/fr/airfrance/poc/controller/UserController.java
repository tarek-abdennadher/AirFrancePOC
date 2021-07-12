package fr.airfrance.poc.controller;

import fr.airfrance.poc.dto.UserDto;
import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.entity.validation.UserValidator;
import fr.airfrance.poc.mapper.UserMapper;
import fr.airfrance.poc.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private UserMapper userMapper;
    private UserValidator userValidator;

    public UserController(UserService userService, UserMapper userMapper, UserValidator userValidator) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    /**
     * <p>
     *     this method allow to return all users
     * </p>
     * @return
     */

    @GetMapping({"", "/"})
    public List<UserDto> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(defaultValue = "false") Boolean paging) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return paging ?
                userMapper.toDtoList(userService.getAll(pageable)) :
                userMapper.toDtoList(userService.getAll());
    }

    /**
     * <p>
     *     This method allow to get user by login and throw exception if user doesn't exist
     * </p>
     * @param login
     * @return
     */
    @GetMapping("/{login}")
    public UserDto getUserByLogin(@PathVariable("login") String login) {
        return userService.getUserByLogin(login).map(userMapper::toDto).orElseThrow(ClassCastException::new);
    }

    /**
     * <p>
     *     This method allow to create user
     * </p>
     * @param dto
     * @param bindingResult
     * @return
     */
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto, BindingResult bindingResult) {
        User user = userMapper.toEntity(dto);
        LOGGER.debug("REST request to save User : {}", dto);
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.debug(bindingResult.toString());
            return new ResponseEntity("user validations failed: \n" + bindingResult.getAllErrors().stream()
                    .map(e -> " -"+e.getDefaultMessage()).collect(Collectors.joining("\n")), HttpStatus.EXPECTATION_FAILED);
        }
        try {
            user =userService.create(user);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity(userMapper.toDto(user), HttpStatus.OK);
    }
}