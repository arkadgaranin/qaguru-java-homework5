package com.gmail.arkgaranin.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.gmail.arkgaranin.components.Calendar;
import org.assertj.core.api.SoftAssertions;

import java.io.File;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationPage {

  private final static String FORM_TITLE = "Student Registration Form";
  private final static String RESULTS_TITLE = "Thanks for submitting the form";

  private SelenideElement
      formTitle = $(".practice-form-wrapper"),
      firstNameInput = $("#firstName"),
      lastNameInput = $("#lastName"),
      emailInput = $("#userEmail"),
      mobileNumberInput = $("#userNumber"),
      subjectsDropdown = $("#subjectsInput"),
      selectPictureBtn = $("#uploadPicture"),
      addressTextarea = $("#currentAddress"),
      stateDropdown = $("#react-select-3-input"),
      cityDropdown = $("#react-select-4-input"),
      resultsPopup = $("[role=dialog]"),
      resultsPopupTitle = $(".modal-title");

  private Calendar calendar = new Calendar();

  public void openPage() {
    open("/automation-practice-form");
    formTitle.shouldHave(text(FORM_TITLE));
  }

  public RegistrationPage typeFirstName(String firstName) {
    firstNameInput.setValue(firstName);
    return this;
  }

  public RegistrationPage typeLastName(String lastName) {
    lastNameInput.setValue(lastName);
    return this;
  }

  public RegistrationPage typeEmail(String email) {
    emailInput.setValue(email);
    return this;
  }

  public RegistrationPage selectGender(String gender) {
    $(format("[name=gender][value=%s]", gender)).parent().click();
    return this;
  }

  public RegistrationPage typeMobileNumber(String mobileNumber) {
    mobileNumberInput.setValue(mobileNumber);
    return this;
  }

  public RegistrationPage setDateOfBirth(String day, String month, String year) {
    calendar.setDate(day, month, year);
    return this;
  }

  public RegistrationPage selectSubjects(String subj1, String subj2, String subj3) {
    subjectsDropdown.setValue(subj1).pressEnter();
    subjectsDropdown.setValue(subj2).pressEnter();
    subjectsDropdown.setValue(subj3).pressEnter();
    return this;
  }

  public RegistrationPage selectHobbies(String hobbyNumber) {
    $(format("[for=hobbies-checkbox-%s]", hobbyNumber)).scrollTo().click();
    return this;
  }

  public RegistrationPage uploadPicture() {
    selectPictureBtn.uploadFile(new File("src/test/resources/image.png"));
    return this;
  }

  public RegistrationPage typeCurrentAddress(String address) {
    addressTextarea.setValue(address);
    return this;
  }

  public void selectStateAndCity(String state, String city) {
    stateDropdown.setValue(state).pressEnter();
    cityDropdown.setValue(city).pressEnter();
  }

  public void submitForm() {
    $("#submit").scrollTo().click();
  }

  public void checkResultsPopupTitle() {
    resultsPopup.shouldBe(visible);
    resultsPopupTitle.shouldHave(text(RESULTS_TITLE));
  }

  public void checkResultsPopupValue(Map<String, String> expectedData) {
    ElementsCollection popupResults = $$(".table-responsive tbody tr").snapshot();

    SoftAssertions softly = new SoftAssertions();

    for (SelenideElement popupResult : popupResults) {
      String line = popupResult.$("td").text();
      String expectedValue = expectedData.get(line);
      String actualValue = popupResult.$("td", 1).text();

      softly.assertThat(actualValue)
          .as(format("Result in line %s was %s, but expected %s", line, actualValue, expectedValue))
          .isEqualTo(expectedValue);
    }
    softly.assertAll();
  }
}