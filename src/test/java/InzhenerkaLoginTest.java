import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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

        $("body").shouldHave(text("Welcome to the-internet"));

        $(byText("A/B Testing")).click();
        $("body").shouldHave(text("Also known as split testing."));
        Selenide.back();
        $("body").shouldHave(text("Welcome to the-internet"));

        //------ CHECKBOXES  ------------
        $(byText("Checkboxes")).click();
        $("body").shouldHave(text("Checkboxes"));

        ElementsCollection checkboxes = $$("input[type='checkbox']");
        SelenideElement checkbox1 = checkboxes.get(0);
        SelenideElement checkbox2 = checkboxes.get(1);

        checkbox1.shouldNot(checked);
        checkbox2.should(checked);

        checkbox1.click();
        checkbox2.click();

        checkbox1.should(checked);
        checkbox2.shouldNot(checked);
        Selenide.back();

        //-------------------
/*
        //------ BASIC AUTH  ------------
        $(byText("Basic Auth")).click();
        open("admin:admin@https://the-internet.herokuapp.com/basic_auth");
        $("p").shouldHave(text("Congratulations! You must have the proper credentials."));
        Selenide.back();
        $("body").shouldHave(text("Welcome to the-internet"));
*/
        $(byText("Radio Buttons")).click();
        $("body").shouldHave(text("Radio Buttons"));

        $(byText("Favorite color")).shouldBe(visible);
        $(byText("Favorite sport")).shouldBe(visible);
        $("input[value='red']").shouldBe(enabled);
        $("input[value='blue']").shouldBe(enabled);
        $("input[value='green']").shouldBe(enabled);

        $("input[value='hockey']").shouldBe(disabled);

        $("#red").shouldBe(visible);
        $("#green").should(visible);

        $("input[value='blue']").click();
        $("input[value='football']").click();

        $("#result").shouldHave(text("Color: blue, Sport: football"));
        Selenide.back();

        // ---- FILE UPLOAD ---------
        $(byText("File Upload")).click();
        $("body").shouldHave(text("File Uploader"));
        $("p").shouldHave(text("Choose a file on your system and then click upload. Or, drag and drop a file into the area below."));
       SelenideElement fileUpload = $("#file-upload");
       SelenideElement submit2 = $("button[type='submit']");
        SelenideElement submit = $("#file-submit");
       fileUpload.shouldBe(visible);
       fileUpload.shouldBe(enabled);
       submit.shouldBe(visible);
       fileUpload.uploadFromClasspath("test.txt");
       submit.click();
        $("#uploaded-files").shouldHave(text("test.txt"));
        Selenide.back();
         //---
        fileUpload.uploadFromClasspath("wrongType.bat");
        submit.click();
        $("h3").shouldHave(text("File type not allowed"));
        $("#upload-error").shouldHave(text(".bat files can't be uploaded"));
        $(byText("Back to the File Uploader")).shouldBe(visible);
        $(byText("Back to the File Uploader")).click();
        $("#file-upload").shouldBe(visible);









/*

        // 3. Нажимаем "Вход"
//        $("button[type='submit']").click();

        // 4. ПРОВЕРКА (Assertion)
        // Мы ищем элемент body или заголовок, который содержит приветствие
        $("body").shouldHave(text("Имя: Линус Торвальдс"));

        // 5. Выход из системы
        // Используем поиск по тексту "Выйти" — это надежнее, чем искать по классам стилей (.mb-3)
        $(byText("Выйти")).click();

        // 6. Проверка выхода (снова видна кнопка "Вход")
        $("button[type='submit']").shouldBe(visible);
*/

    }

}