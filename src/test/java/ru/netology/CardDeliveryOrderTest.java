package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryOrderTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void validCityNameDateAndPhoneNumber() {
        $("[data-test-id=city] input").setValue("Москва");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000).shouldHave(exactText("Успешно! Встреча успешно забронирована на " + date));
    }

    @Test
    public void notValidCity() {
        $("[data-test-id=city] input").setValue("Лопушки");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void notValidName() {
        $("[data-test-id=city] input").setValue("Москва");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Хазова Любовь!");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void notValidPhoneNumber() {
        $("[data-test-id=city] input").setValue("Москва");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("11111111111+");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void notСheckbox() {
        $("[data-test-id=city] input").setValue("Москва");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[class='button__text']").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    public void emptyFieldCity() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void emptyFieldName() {
        $("[data-test-id=city] input").setValue("Москва");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void emptyFieldPhoneNumber() {
        $("[data-test-id=city] input").setValue("Москва");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}