package pageobject;

import com.codeborne.selenide.SelenideElement;
import utils.UiUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class EditAdvertisementPage {
    private final SelenideElement nameInputField = $x("//input[@name='name']");
    private final SelenideElement submitButton = $x("//button[@type='submit']");
    private final SelenideElement pageHeader = $x("//h1[contains(@class, 'title')]");

    public void typeInNameInputField(String text) {
        nameInputField.shouldBe(visible).clear();
        nameInputField.setValue(text);
    }

    public void clickOnSubmitButton() {
        UiUtils.clickWithScroll(submitButton);
    }

    public boolean isEditPage() {
        try {
            String header = pageHeader.shouldBe(visible).getText();
            return header.contains("Редактирование") || header.contains("Редактировать");
        } catch (Exception e) {
            return false;
        }
    }
}
