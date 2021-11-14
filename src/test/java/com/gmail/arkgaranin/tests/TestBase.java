package com.gmail.arkgaranin.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

  @BeforeAll
  static void setup() {
    Configuration.browserSize = "1280x720";
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://demoqa.com";
  }
}