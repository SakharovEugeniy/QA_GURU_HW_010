import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открываем домашнюю страницу GitHub")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("В поле поиска вводим {repo}")
    public void searchRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("В списке выбираем репозиторий {repo}")
    public void selectRepoFromList(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Кликаем по вкладке Issues")
    public void selectIssuesTab() {
        $("[data-content='Issues']").click();
    }

    @Step("Проверяем что ишью с номером number: {0} называется issueName: {1}")
    public void checkThatIssueMatchesTheNumber(String number, String issueName) {
        $("#" + number).shouldHave(text(issueName));
    }
}
