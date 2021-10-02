package com.gmail.arkgaranin;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

  @BeforeAll
  static void setup() {
    Configuration.startMaximized = true;
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://demoqa.com";
  }
}