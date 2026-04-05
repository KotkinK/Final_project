package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.*;
import pageObject.*;

public class CommonSteps {

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final AdvertisementPage adPage = new AdvertisementPage();
    private final CreateAdvertisementPage createPage = new CreateAdvertisementPage();
    private final EditAdvertisementPage editPage = new EditAdvertisementPage();

    public CommonSteps(TestContext context) {}

    @Given("Открыта главная страница")
    public void openHomePage() {
        homePage.open();
    }

    @When("пользователь нажимает кнопку {string}")
    public void clickButton(String buttonText) {

        switch (buttonText) {
            case "Вход и регистрация":
                homePage.clickOnLoginAndRegistrationButton();
                break;
            case "Войти":
                loginPage.clickOnLoginButton();
                break;
            case "Нет аккаунта":
                loginPage.clickOnNoAccountButton();
                break;
            case "Создать аккаунт":
                registrationPage.clickOnCreateAccountButton();
                break;
            case "Разместить объявление":
                homePage.clickOnPostAnAdvertisementButton();
                break;
            case "Опубликовать":
                createPage.clickOnSubmitButton();
                break;
            case "Сохранить изменения":
                editPage.clickOnSubmitButton();
                break;
            case "Удалить":
                adPage.clickOnDeleteAdvertisementButton();
                break;
            case "Применить":
                homePage.clickOnApplyButton();
                break;
            case "Редактировать объявление":
                adPage.clickOnEditAdvertisementButton();
                break;
            default:
                throw new IllegalArgumentException("Неизвестная кнопка: " + buttonText);
        }
    }
}