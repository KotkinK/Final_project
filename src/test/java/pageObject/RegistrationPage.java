package pageObject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class RegistrationPage {
    private final SelenideElement emailInputField = $x("//input[@name='email']");
    private final SelenideElement passwordInputField = $x("//input[@name='password']");
    private final SelenideElement passwordConfirmInputField = $x("//input[@name='submitPassword']");
    private final SelenideElement createAccountButton = $x("//button[text()='Создать аккаунт']");
    private final SelenideElement errorMessage = $x("//span[text()='Ошибка']");

    public void typeInEmailInputField(String email) {
        emailInputField.shouldBe(visible).setValue(email);
    }

    public void typeInPasswordInputField(String password) {
        passwordInputField.shouldBe(visible).setValue(password);
    }

    public void typeInConfirmPasswordField(String password) {
        passwordConfirmInputField.shouldBe(visible).setValue(password);
    }

    public void clickOnCreateAccountButton() {
        createAccountButton.shouldBe(visible, enabled).click();
    }

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }
}
