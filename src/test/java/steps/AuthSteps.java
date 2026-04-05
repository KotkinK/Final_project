package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.*;
import pageObject.*;
import api.ApiClient;
import utils.TestData;

import static com.codeborne.selenide.Condition.*;

public class AuthSteps {

    private final TestContext context;

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final RegistrationPage registrationPage = new RegistrationPage();

    public AuthSteps(TestContext context) {
        this.context = context;
    }

    @Given("пользователь уже зарегистрирован")
    @Given("пользователь уже существует")
    public void createUser() {
        String email = TestData.generateUniqueEmail();
        context.setUser(ApiClient.registerUser(email, "123"));
    }

    @Given("пользователь авторизован")
    public void authorizeUser() {

        if (context.getUser() == null) {
            createUser();
        }

        homePage.open();
        homePage.clickOnLoginAndRegistrationButton();

        loginPage.waitForLoginForm();
        loginPage.typeInEmailInputField(context.getUser().getEmail());
        loginPage.typeInPasswordInputField("123");
        loginPage.clickOnLoginButton();

        homePage.waitForUserProfileIcon();
    }

    @When("пользователь вводит свой email и пароль")
    public void enterCredentials() {
        loginPage.waitForLoginForm();
        loginPage.typeInEmailInputField(context.getUser().getEmail());
        loginPage.typeInPasswordInputField("123");
    }

    @When("пользователь вводит уникальный email и пароль {string}")
    public void enterNewCredentials(String password) {

        String email = TestData.generateUniqueEmail();

        registrationPage.typeInEmailInputField(email);
        registrationPage.typeInPasswordInputField(password);
        registrationPage.typeInConfirmPasswordField(password);
    }

    @When("пользователь вводит email ранее созданного пользователя и пароль {string}")
    public void enterExistingUser(String password) {
        registrationPage.typeInEmailInputField(context.getUser().getEmail());
        registrationPage.typeInPasswordInputField(password);
        registrationPage.typeInConfirmPasswordField(password);
    }

    @Then("иконка профиля пользователя отображается")
    public void checkProfileIcon() {
        homePage.waitForUserProfileIcon();
    }

    @Then("появляется сообщение об ошибке {string}")
    public void checkError(String text) {
        registrationPage.getErrorMessage()
                .shouldBe(visible)
                .shouldHave(text(text));
    }
}