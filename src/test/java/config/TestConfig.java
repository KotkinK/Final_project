package config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestConfig {

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://qa-desk.stand.praktikum-services.ru/";
        Configuration.timeout = 15000;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.browserSize = "1920x1080";
    }
}
