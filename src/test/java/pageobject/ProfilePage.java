package pageobject;

import com.codeborne.selenide.SelenideElement;
import utils.UiUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    private final SelenideElement myAdvertisementsHeader = $x("//h1[contains(text(), 'Мои объявления')]");
    private final SelenideElement advertisementCard = $x("//div[contains(@class, 'card')]");
    private final SelenideElement editAdvertisementCardButton = $x("//*[contains(@class, 'editButton')]");
    private final SelenideElement advertisementCardTitle = $x("//div[contains(@class, 'about')]//h2");

    public void waitForPageLoad() {
        myAdvertisementsHeader.shouldBe(visible);
    }

    public void clickOnAdvertisementCard() {
        UiUtils.clickWithScroll(advertisementCard);
    }

    public void clickOnEditAdvertisementCardButton() {
        UiUtils.clickWithScroll(editAdvertisementCardButton);
    }

    public SelenideElement getAdvertisementCardTitle() {
        return advertisementCardTitle;
    }

    public SelenideElement getAdvertisementCard() {
        return advertisementCard;
    }
}
