package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.*;
import pageObject.*;
import api.ApiClient;
import utils.TestData;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Condition.*;

public class AdvertisementSteps {

    private final TestContext context;

    private final HomePage homePage = new HomePage();
    private final ProfilePage profilePage = new ProfilePage();
    private final CreateAdvertisementPage createPage = new CreateAdvertisementPage();
    private final EditAdvertisementPage editPage = new EditAdvertisementPage();

    public AdvertisementSteps(TestContext context) {
        this.context = context;
    }

    @Given("объявление создано")
    public void createAd() {
        String name = TestData.generateUniqueAdName();
        context.setListing(
                ApiClient.createListing(
                        context.getUser().getAccessToken(),
                        name
                )
        );
    }

    @When("пользователь вводит название объявления {string}")
    public void enterAdName(String name) {
        createPage.waitForFormLoad();
        createPage.typeInNameInputField(name);
    }

    @When("пользователь вводит в поиск название созданного объявления")
    public void search() {
        homePage.typeInSearchAdvertisementInputField(
                context.getListing().getName()
        );
        homePage.clickOnApplyButton();
    }

    @When("пользователь нажимает на карточку объявления")
    public void clickCard() {
        try {
            homePage.clickOnAdvertisementCard();
        } catch (Exception e) {
            profilePage.clickOnAdvertisementCard();
        }
    }

    @When("пользователь нажимает кнопку редактирования объявления")
    public void clickEditCard() {
        try {
            homePage.clickOnEditAdvertisementCardButton();
        } catch (Exception e) {
            profilePage.clickOnEditAdvertisementCardButton();
        }
    }

    @When("пользователь вводит новое название {string}")
    public void enterNewName(String name) {
        editPage.typeInNameInputField(name);
    }

    @Then("пользователь переходит в профиль")
    public void goToProfile() {
        homePage.clickOnUserProfileIconButton();
        profilePage.waitForPageLoad();
        refresh();
    }

    @Then("в профиле отображается созданное объявление")
    public void checkAdExists() {
        profilePage.getAdvertisementCardTitle()
                .shouldBe(visible)
                .shouldNotBe(empty);
    }

    @Then("объявление не отображается в профиле")
    public void checkAdNotExists() {
        profilePage.getAdvertisementCard()
                .shouldNot(exist);
    }

    @Then("открывается страница редактирования объявления")
    public void checkEditPage() {
        assert editPage.isEditPage();
    }

    @Then("название карточки объявления изменилось на {string}")
    public void checkUpdatedName(String name) {
        profilePage.getAdvertisementCardTitle()
                .shouldBe(visible)
                .shouldHave(text(name));
    }
}