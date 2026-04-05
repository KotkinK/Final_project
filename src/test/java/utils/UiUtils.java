package utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class UiUtils {

    public static void scrollToElement(SelenideElement element) {
        element.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public static void clickWithScroll(SelenideElement element) {
        scrollToElement(element);
        element.shouldBe(visible, enabled).click();
    }
}