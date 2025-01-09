import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureWithStepTests extends BaseTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NAME = "One piece";
    private static final String ISSUE_NUMBER = "issue_94";

    @DisplayName("Проверка названия Issues c использованием  Allure лямбда steps")
    @Test
    public void selenideWithLambdaStepsTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем домашнюю страницу GitHub", () -> {
            open("https://github.com/");
        });

        step("В поле поиска вводим " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("В списке выбираем репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Кликаем по вкладке Issues", () -> {
            $("[data-content='Issues']").click();
        });

        step("Проверяем что ишью с номером " + ISSUE_NUMBER + " называется " + ISSUE_NAME,
                () -> {
                    $("#" + ISSUE_NUMBER).shouldHave(text(ISSUE_NAME));
                });
    }

    @DisplayName("Проверка названия Issues с использованием  Allure steps")
    @Test
    public void selenideWithStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps wbs = new WebSteps();

        wbs.openMainPage();
        wbs.searchRepository(REPOSITORY);
        wbs.selectRepoFromList(REPOSITORY);
        wbs.selectIssuesTab();
        wbs.checkThatIssueMatchesTheNumber(ISSUE_NUMBER, ISSUE_NAME);
    }
}
