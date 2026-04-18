package com.gt.demo.web.pages.dom;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectorsHubPracticePage extends BaseWebPage {
  private static final By PAGE_BREADCRUMB =
      By.xpath("//h6[contains(normalize-space(.), 'Shadow DOM')]");
  private static final By FIRST_IFRAME = By.cssSelector("iframe");

  public SelectorsHubPracticePage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl);
    visible(PAGE_BREADCRUMB);
    waitUntilJsCondition(
        "return !!document.querySelector('#userName')"
            + " && !!document.querySelector('#userName').shadowRoot"
            + " && !!document.querySelector('#userName').shadowRoot.querySelector('#app2')"
            + " && !!document.querySelector('#userName').shadowRoot.querySelector('#app2').shadowRoot");
  }

  public String fillOpenShadowInput(String value) {
    Object result =
        js(
            "const host=document.querySelector('#userName');"
                + "if(!host||!host.shadowRoot){return null;}"
                + "const input=host.shadowRoot.querySelector('#kils');"
                + "if(!input){return null;}"
                + "input.value=arguments[0];"
                + "input.dispatchEvent(new Event('input',{bubbles:true}));"
                + "return input.value;",
            value);
    return result == null ? "" : result.toString();
  }

  public String fillNestedShadowInput(String value) {
    Object result =
        js(
            "const host=document.querySelector('#userName');"
                + "if(!host||!host.shadowRoot){return null;}"
                + "const nestedHost=host.shadowRoot.querySelector('#app2');"
                + "if(!nestedHost||!nestedHost.shadowRoot){return null;}"
                + "const input=nestedHost.shadowRoot.querySelector('#pizza');"
                + "if(!input){return null;}"
                + "input.value=arguments[0];"
                + "input.dispatchEvent(new Event('input',{bubbles:true}));"
                + "return input.value;",
            value);
    return result == null ? "" : result.toString();
  }

  public String readOpenShadowInput() {
    Object result =
        js(
            "const host=document.querySelector('#userName');"
                + "if(!host||!host.shadowRoot){return null;}"
                + "const input=host.shadowRoot.querySelector('#kils');"
                + "return input?input.value:null;");
    return result == null ? "" : result.toString();
  }

  public void switchToAnyIframeAndBack() {
    switchToFrame(FIRST_IFRAME);
    defaultContent();
  }
}

