package pageObject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class AdvertisementPage {
    private final SelenideElement editAdvertisementButton = $x("//button[contains(text(), 'Редактировать')]");
    private final SelenideElement deleteAdvertisementButton = $x("//button[contains(text(), 'Удалить')]");

    public void clickOnEditAdvertisementButton() {
        editAdvertisementButton.shouldBe(visible, enabled).click();
    }

    public void clickOnDeleteAdvertisementButton() {
        deleteAdvertisementButton.shouldBe(visible, enabled).click();
    }
}
