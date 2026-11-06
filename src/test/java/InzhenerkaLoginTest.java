import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class InzhenerkaLoginTest extends BaseTest {
    @DisplayName("Проверка логинов")
    @ParameterizedTest
    @CsvSource({"admin,admin123", "user,user123"})
    void runLoginAdminTest(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.checkLoginButtonExists();
        loginPage.login();
        loginPage.checkUser("Привет, " + username);
        loginPage.logout();
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromCollection() {
        Stream<UserClass> inputUsers = Stream.of(new UserClass("admin", "admin123"),
                new UserClass("user", "user123"),
                new UserClass("", ""));

        return inputUsers.map(user -> dynamicTest("Проверка юзера " + user.username,
                () -> {
                    LoginPage loginPage = new LoginPage();
                    loginPage.openPage();
                    loginPage.setUsername(user.username);
                    loginPage.setPassword(user.password);
                    loginPage.checkLoginButtonExists();
                    loginPage.login();
                    loginPage.checkUser("Привет, " + user.username);
                    loginPage.logout();
                }));

    }


    @Disabled
    @Test
    @DisplayName("Проверка логина user")
    void runLoginUserTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage();
        loginPage.setUsername("user");
        loginPage.setPassword("user123");
        loginPage.checkLoginButtonExists();
        loginPage.login();
        loginPage.checkUser("Привет, user");
        loginPage.logout();
    }
}