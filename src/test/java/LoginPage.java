import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BaseTest {
	private final SelenideElement usernameField = $("#username");
	private final SelenideElement passwordField = $("[name='password']");
	private final SelenideElement messageField = $("h2");
	private final SelenideElement loginButton = $("button[type='submit']");
	private final SelenideElement logoutButton = $(byText("Выйти"));

	public void openPage() {
		String URL = "http://qa-stand-login.inzhenerka.tech/login";
		Selenide.open(URL);
	}

	public void setUsername(String username) {
		usernameField.setValue(username);
	}

	public void setPassword(String password) {
		passwordField.setValue(password);
	}

	public void login() {
		loginButton.click();
	}

	public void checkLoginButtonExists() {
		loginButton.exists();
		loginButton.shouldHave(text("Войти"));
	}

	public void logout() {
		logoutButton.click();
	}

	public void checkUser(String message) {
		messageField.shouldHave(text(message));
	}
}