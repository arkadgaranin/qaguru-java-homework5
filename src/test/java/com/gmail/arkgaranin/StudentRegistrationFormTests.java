package com.gmail.arkgaranin;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests {

  String firstName = "Arkadiy";
  String lastName = "Garanin";
  String email = "arkgaranin@gmail.com";
  String mobileNumber = "9772601157";

  @BeforeAll
  static void setup() {
    Configuration.startMaximized = true;
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://demoqa.com";
  }

  @Test
  void fillingRegistrationFormTest() {

    open("/automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

    $("#firstName").setValue(firstName);
    $("#lastName").setValue(lastName);
    $("#userEmail").setValue(email);
    $("[for='gender-radio-1']").click();
    $("#userNumber").setValue(mobileNumber);

    // Заполнение даты рождения кликами
    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").selectOption("April");
    $(".react-datepicker__year-select").selectOption("1990");
    $("[aria-label='Choose Thursday, April 26th, 1990']").click();

    $("#subjectsInput").setValue("En").pressEnter();
    $("#subjectsInput").setValue("Co").pressEnter();

    $("[for='hobbies-checkbox-1']").click();
    $("[for='hobbies-checkbox-2']").click();
    $("[for='hobbies-checkbox-3']").click();

    $("#uploadPicture").uploadFile(new File("src/test/resources/Bart.png"));

    $("#currentAddress").setValue("Moscow region, Odintsovo city, Severnaya street");
    $("#react-select-3-input").setValue("Ha").pressEnter();
    $("#react-select-4-input").setValue("Ka").pressEnter();

    // Submit Registration Form
    $("#submit").scrollTo().click();

    // Проверка регистрационных данных в попапе
    $(".modal-title").shouldHave(text("Thanks for submitting the form"));
    $(".table-responsive").shouldHave(text(firstName + " " + lastName), text(email), text("Male"),
        text(mobileNumber), text("26 April,1990"), text("English, Computer Science"), text("Sports, Reading, Music"),
        text("Bart.png"), text("Moscow region, Odintsovo city, Severnaya street"), text("Haryana Karnal"));
  }
}