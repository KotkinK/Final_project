package pageObject;

import com.codeborne.selenide.SelenideElement;
import utils.UiUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CreateAdvertisementPage {
    private final SelenideElement nameInputField = $x("//input[@name='name']");
    private final SelenideElement submitButton = $x("//button[@type='submit']");
    private final SelenideElement pageHeader = $x("//h1[contains(text(), 'Новое')]");

    public void waitForFormLoad() {
        nameInputField.shouldBe(visible);
    }

    public void typeInNameInputField(String text) {
        nameInputField.shouldBe(visible).setValue(text);
    }

    public void clickOnSubmitButton() {
        UiUtils.clickWithScroll(submitButton);
    }
}
