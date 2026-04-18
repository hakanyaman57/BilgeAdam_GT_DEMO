package com.gt.demo.web.pages.technical;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TheInternetIframePage extends BaseWebPage {
  private static final By IFRAME = By.id("mce_0_ifr");
  private static final By EDITOR_BODY = By.id("tinymce");

  public TheInternetIframePage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/iframe");
  }

  public void writeText(String value) {
    switchToFrame(IFRAME);
    js(
        "const editor=document.getElementById('tinymce');"
            + "editor.innerHTML='';"
            + "editor.textContent=arguments[0];",
        value);
    defaultContent();
  }

  public String editorText() {
    switchToFrame(IFRAME);
    WebElement editorBody = visible(EDITOR_BODY);
    String value = editorBody.getText().trim();
    defaultContent();
    return value;
  }
}
