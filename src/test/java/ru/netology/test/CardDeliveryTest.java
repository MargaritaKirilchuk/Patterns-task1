package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.DataGenerator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    private Faker faker;
    private DataGenerator data = new DataGenerator();
    private String city = data.city();
    private String validDate = data.validDate();
    private String name = data.name();
    private String phone = data.phone();
    private String invalidDate = data.invalidDate();
    private String dateWithLetters = data.lettersInDate();

    @BeforeEach
    void setUpAll() {
        open("http://0.0.0.0:7777/");
    }

    @Test
    void shouldAcceptRequest() {
        $("[data-test-id=city] input").sendKeys(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(validDate);
        $("[data-test-id=name] input").sendKeys(name);
        $("[data-test-id=phone] input").sendKeys(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldReplanIfTheSameData() {
        $("[data-test-id=city] input").sendKeys(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(validDate);
        $("[data-test-id=name] input").sendKeys(name);
        $("[data-test-id=phone] input").sendKeys(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotSubmitIfInvalidDate() {
        $("[data-test-id=city] input").sendKeys(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys();
        $("[data-test-id=name] input").sendKeys(name);
        $("[data-test-id=phone] input").sendKeys(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        SelenideElement date = $("[data-test-id=date]");
        date.$(".input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNotSubmitIfLettersInDate() {
        $("[data-test-id=city] input").sendKeys(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(dateWithLetters);
        $("[data-test-id=name] input").sendKeys(name);
        $("[data-test-id=phone] input").sendKeys(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        SelenideElement date = $("[data-test-id=date]");
        date.$(".input__sub").shouldHave(exactText("Неверно введена дата"));
    }
}
