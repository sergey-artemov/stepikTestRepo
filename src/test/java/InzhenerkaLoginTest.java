import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class InzhenerkaLoginTest {
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

    @Test
    void herokuTest() {
        // 1. Открыть страницу
        open("https://the-internet.herokuapp.com/");

        //   $("#username").setValue(username);
//        $(By.name("password")).setValue(pass);
        // 3. Нажимаем "Вход"
        $("button[type='submit']").click();

        // 4. ПРОВЕРКА (Assertion)
        // Мы ищем элемент body или заголовок, который содержит приветствие
        $("body").shouldHave(text("Имя: Линус Торвальдс"));

        // 5. Выход из системы
        // Используем поиск по тексту "Выйти" — это надежнее, чем искать по классам стилей (.mb-3)
        $(byText("Выйти")).click();

        // 6. Проверка выхода (снова видна кнопка "Вход")
        $("button[type='submit']").shouldBe(visible);


    }

}