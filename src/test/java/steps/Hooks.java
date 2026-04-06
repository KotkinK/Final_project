package steps;

import context.TestContext;
import io.cucumber.java.*;
import api.ApiClient;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.response.Response;
import pageObject.AdvertisementPage;
import pageObject.CreateAdvertisementPage;
import pageObject.HomePage;
import pageObject.ProfilePage;

import static io.restassured.RestAssured.given;

public class Hooks {

    private final TestContext context;

    private final HomePage homePage = new HomePage();
    private final ProfilePage profilePage = new ProfilePage();
    private final AdvertisementPage advertisementPage = new AdvertisementPage();

    public Hooks(TestContext context) {
        this.context = context;
    }

    @After(order = 1)
    public void cleanup() {
        if (context.getListing() != null && context.getUser() != null) {
            try {
                ApiClient.deleteListing(
                        context.getUser().getAccessToken(),
                        context.getListing().getId()
                );
            } catch (Exception e) {
                System.out.println("Cleanup error ignored: " + e.getMessage());
            }
        }
    }

    @After(order = 0)
    public void close() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
        context.clear();
    }
}