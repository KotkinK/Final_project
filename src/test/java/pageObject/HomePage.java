package pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import utils.UiUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class HomePage {
    public static final String MAIN_URL = "https://qa-desk.stand.praktikum-services.ru/";

    private final SelenideElement loginAndRegistrationButton = $x("//button[contains(text(), 'Вход') or contains(text(), 'Регистрация')]");
    private final SelenideElement userProfileIconButton = $(".circleSmall");
    private final SelenideElement postAnAdvertisementButton = $x("//button[contains(text(), 'Разместить')]");
    private final SelenideElement searchAdvertisementInputField = $x("//input[@name='name']");
    private final SelenideElement applyButton = $x("//button[contains(text(), 'Применить')]");
    private final SelenideElement editAdvertisementCardButton = $(".editButton");
    private final SelenideElement advertisementCard = $x("//div[contains(@class, 'card')]");

    public void open() {
        Selenide.open(MAIN_URL);

        postAnAdvertisementButton.shouldBe(visible);
    }

    public void clickOnLoginAndRegistrationButton() {
        loginAndRegistrationButton.shouldBe(visible, enabled).click();
    }

    public void clickOnUserProfileIconButton() {
        userProfileIconButton.shouldBe(visible, enabled).click();
    }

    public void clickOnPostAnAdvertisementButton() {
        postAnAdvertisementButton.shouldBe(visible, enabled).click();
    }

    public void clickOnAdvertisementCard() {
        UiUtils.clickWithScroll(advertisementCard);
    }

    public void typeInSearchAdvertisementInputField(String text) {
        searchAdvertisementInputField.shouldBe(visible).setValue(text);
    }

    public void clickOnApplyButton() {
        applyButton.shouldBe(visible, enabled).click();
    }

    public void clickOnEditAdvertisementCardButton() {
        editAdvertisementCardButton.shouldBe(visible, enabled).click();
    }

    public boolean waitForUserProfileIcon() {
        try {
            userProfileIconButton.shouldBe(visible);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
