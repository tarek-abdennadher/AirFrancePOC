package fr.airfrance.poc;

import fr.airfrance.poc.user.UserControllerTest;
import fr.airfrance.poc.user.UserServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserServiceTest.class, UserControllerTest.class})

public class TestSuite {

}
