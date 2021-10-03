package com.gmail.arkgaranin.tests;

import com.gmail.arkgaranin.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests extends BaseTest {

  RegistrationPage registrationPage = new RegistrationPage();

  String firstName = "Arkadiy",
      lastName = "Garanin",
      email = "yiyopog631@carpetd.com",
      gender = "Male",
      mobileNumber = "9771234567",
      dayOfBirth = "26",
      monthOfBirth = "April",
      yearOfBirth = "1990",
      subject1 = "English",
      subject2 = "Computer Science",
      hobbyToSelect1 = "1",
      hobbyToSelect2 = "3",
      hobbyResult1 = "Sports",
      hobbyResult2 = "Music",
      picture = "Bart.png",
      currentAddress = "MO Odintsovo",
      state = "Haryana",
      city = "Karnal";

  @Test
  void fillRegistrationFormTest() {
    // Открытие стр-цы формы регистрации
    registrationPage.openPage();
    // Заполнение формы регистрации
    registrationPage
        .typeFirstName(firstName)
        .typeLastName(lastName)
        .typeEmail(email)
        .selectGender(gender)
        .typeMobileNumber(mobileNumber)
        .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
        .selectSubjects(subject1, subject2)
        .selectHobbies(hobbyToSelect1)
        .selectHobbies(hobbyToSelect2)
        .uploadPicture()
        .typeCurrentAddress(currentAddress)
        .selectStateAndCity(state, city);

    // Submit Registration Form
    registrationPage.submitForm();

    // Проверка появления попапа регистрации и заголовка в нем
    registrationPage.checkResultsPopupTitle();

    // Проверка регистрационных данных в попапе
    registrationPage
        .checkResultsPopupValue("Student Name", firstName + " " + lastName)
        .checkResultsPopupValue("Student Email", email)
        .checkResultsPopupValue("Gender", gender)
        .checkResultsPopupValue("Mobile", mobileNumber)
        .checkResultsPopupValue("Date of Birth", dayOfBirth)
        .checkResultsPopupValue("Subjects", subject1 + ", " + subject2)
        .checkResultsPopupValue("Hobbies", hobbyResult1 + ", " + hobbyResult2)
        .checkResultsPopupValue("Picture", picture)
        .checkResultsPopupValue("Address", currentAddress)
        .checkResultsPopupValue("State and City", state + " " + city);
  }
}