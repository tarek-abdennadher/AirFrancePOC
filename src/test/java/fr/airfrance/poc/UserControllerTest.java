package fr.airfrance.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.airfrance.poc.controller.UserController;
import fr.airfrance.poc.dto.UserDto;
import fr.airfrance.poc.entity.User;
import fr.airfrance.poc.entity.UserPk;
import fr.airfrance.poc.entity.enumeration.Gender;
import fr.airfrance.poc.entity.validation.UserValidator;
import fr.airfrance.poc.mapper.UserMapper;
import fr.airfrance.poc.service.interfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends PocApplicationTests {

    @Mock
    UserService userService;

    @Autowired
    UserMapper userMapper;

    UserValidator userValidator = new UserValidator(userService);

    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    User user = new User();
    User invalidUser = new User();
    UserDto userDto = new UserDto();

    List<User> users;
    List<User> singlePageOfTen;
    List<UserDto> usersDto;
    List<UserDto> singlePageOfTenDto;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        UserPk firstUserPk = new UserPk("abdennadher", "16/05/1991", "FRANCE");
        user.setUserPk(firstUserPk);
        user.setPhoneNumber("0676337561");
        user.setGender(Gender.M);

        UserPk invalidUserPk = new UserPk("abdennadher", "16/05/2020", "USA");
        invalidUser.setUserPk(invalidUserPk);

        userDto.setUserName("abdennadher");
        userDto.setBirthdate("16/05/1991");
        userDto.setCountry("FRANCE");
        userDto.setPhoneNumber("0676337561");
        userDto.setGender(Gender.M);

        users = Arrays.asList(user,user,user,user,user,user,user,user,user,user,user,user);
        singlePageOfTen = Arrays.asList(user,user,user,user,user,user,user,user,user,user);

        userValidator = new UserValidator(userService);
        userController = new UserController(userService, userMapper, userValidator);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUserWithoutRequestParamShouldReturnAllUsers() throws Exception {
        when(userService.getAll()).thenReturn(users);
        // result without request param should return all users
        MvcResult mvcResult = mockMvc.perform(get("/user"))
                .andExpect(jsonPath("$.size()").value(12))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllUserWithRequestParamShouldReturnSinglePage() throws Exception {
        when(userService.getAll(any())).thenReturn(singlePageOfTen);
        // result with request param should return single page (10 element by default)
        MvcResult mvcResultPaging = mockMvc.perform(get("/user?paging=true"))
                .andExpect(jsonPath("$.size()").value(10))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getUserByIdTestCase() throws Exception {
        when(userService.getUserById(any(), any(), any())).thenReturn(user);

        // expect userDto as a result when birthDate request param is given
        MvcResult mvcResult = mockMvc.perform(get("/user/abdennadher?birthdate=16/05/1991"))
                .andExpect(jsonPath("$.userName").value("abdennadher"))
                .andExpect(status().isOk())
                .andReturn();

        // expect badRequest exception as a result when birthDate request param is given
        MvcResult mvcResultException = mockMvc.perform(get("/user/abdennadher"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException().getMessage())
                        .isEqualTo("Required request parameter 'birthdate' for method parameter type String is not present"))
                .andReturn();
    }

    @Test
    public void createUserShouldPass() throws Exception {
        when(userService.create(user)).thenReturn(user);
        when(userService.getUserByPk(any())).thenReturn(Optional.empty());
        when(userService.isDatePatternValid(any())).thenReturn(true);
        when(userService.getAge(any())).thenReturn(30);

        MvcResult mvcResult = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void createUserShouldFailWhenValidatorFail() throws Exception {
        when(userService.create(invalidUser)).thenReturn(invalidUser);
        when(userService.getUserByPk(any())).thenReturn(Optional.empty());
        when(userService.isDatePatternValid(any())).thenReturn(false);
        when(userService.getAge(any())).thenReturn(30);

        MvcResult mvcResult = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isExpectationFailed())
                .andReturn();
    }



}
