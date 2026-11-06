import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    LoginPage loginPage;

    @BeforeAll
    public static void setUp() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/selenide.properties"));
            Configuration.browser = properties.getProperty("selenide.browser");
            Configuration.headless = Boolean.parseBoolean(properties.getProperty("selenide.headless"));
            Configuration.remote = properties.getProperty("selenide.remote");
            Configuration.browserSize = "1920x1080";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    @BeforeEach
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage();
    }

    @AfterEach
    public void afterTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.logout();
    }

     */


}