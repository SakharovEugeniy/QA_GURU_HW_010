import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

@DisplayName("Проверка названия Issues c использованием только  Allure Listener")
public class AllureReportsTests extends BaseTest {

    @Test
    public void selenideSimpleTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();
        $(linkText("eroshenkoam/allure-example")).click();
        $("[data-content='Issues']").click();
        $("#issue_94").shouldHave(text("One piece"));
    }
}
