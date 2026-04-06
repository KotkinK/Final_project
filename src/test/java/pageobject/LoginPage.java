package pageobject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class LoginPage {
    private final SelenideElement emailInputField = $x("//input[@name='email']");
    private final SelenideElement passwordInputField = $x("//input[@name='password']");
    private final SelenideElement loginButton = $x("//button[text()='Войти']");
    private final SelenideElement noAccountButton = $x("//button[text()='Нет аккаунта']");

    public void waitForLoginForm() {
        emailInputField.shouldBe(visible, enabled);
    }

    public void typeInEmailInputField(String email) {
        emailInputField.shouldBe(visible).setValue(email);
    }

    public void typeInPasswordInputField(String password) {
        passwordInputField.shouldBe(visible).setValue(password);
    }

    public void clickOnLoginButton() {
        loginButton.shouldBe(visible, enabled).click();
    }

    public void clickOnNoAccountButton() {
        noAccountButton.shouldBe(visible, enabled).click();
    }
}
