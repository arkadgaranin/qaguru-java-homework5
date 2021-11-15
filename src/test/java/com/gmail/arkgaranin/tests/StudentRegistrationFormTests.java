package com.gmail.arkgaranin.tests;

import com.github.javafaker.Faker;
import com.gmail.arkgaranin.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
    //gender = "Male",
    gender = faker.demographic().sex(),
            mobileNumber = faker.phoneNumber().subscriberNumber(10),
            dayOfBirth = String.valueOf(faker.number().numberBetween(10, 30)),
            monthOfBirth = "April",
            yearOfBirth = "1990",
            subject1 = "English",
            subject2 = "Computer Science",
            subject3 = "History",
            hobbyToSelect1 = "1",
            hobbyToSelect2 = "3",
            hobbyResult1 = "Sports",
            hobbyResult2 = "Music",
            picture = "image.png",
            currentAddress = faker.address().fullAddress(),
            state = "Haryana",
            city = "Karnal";

    Map<String, String> expectedData = new HashMap<>() {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", email);
        put("Gender", gender);
        put("Mobile", mobileNumber);
        put("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth);
        put("Subjects", subject1 + ", " + subject2 + ", " + subject3);
        put("Hobbies", hobbyResult1 + ", " + hobbyResult2);
        put("Picture", picture);
        put("Address", currentAddress);
        put("State and City", state + " " + city);
    }};

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
                .selectSubjects(subject1, subject2, subject3)
                .selectHobbies(hobbyToSelect1)
                .selectHobbies(hobbyToSelect2)
                .uploadPicture()
                .typeCurrentAddress(currentAddress)
                .selectStateAndCity(state, city);

        // Submit Registration Form
        registrationPage.submitForm();

        // Проверка появления попапа регистрации и данных в нем
        registrationPage.checkResultsPopupTitle();
        registrationPage.checkResultsPopupValue(expectedData);
    }
}