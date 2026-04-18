package com.gt.demo.web.pages.technical;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.WebDriver;

public class TheInternetShadowDomPage extends BaseWebPage {

  public TheInternetShadowDomPage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/shadowdom");
  }

  public String firstSlottedText() {
    String script =
        "const host=document.querySelector('my-paragraph');"
            + "if(!host||!host.shadowRoot){return null;}"
            + "const slot=host.shadowRoot.querySelector('slot[name=\"my-text\"]');"
            + "if(!slot){return null;}"
            + "const nodes=slot.assignedNodes();"
            + "if(!nodes||nodes.length===0){return null;}"
            + "return nodes.map(n=>n.textContent.trim()).join(' ').trim();";
    Object result = js(script);
    return result == null ? "" : result.toString().trim();
  }
}

