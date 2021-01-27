package utils;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class BasePage {

    protected WebDriver driver;

    public BasePage (WebDriver driver) {
        this.driver = driver;
    }

    public void scrollDown (int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixel + ")");
    }

    public void scrollUp (int pixel ) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-" + pixel + ")");
    }

    public void checkPageURLWithAssertion (String currentURL, String selectedURL) {
        Assert.assertEquals("** Açılan sayfa doğru değildir! ** ",selectedURL,currentURL);
    }

    public void checkPageURLWithLogger (String currentURL, String selectedURL) {
        if (currentURL.equals(selectedURL)) {
            System.out.println("Doğru sayfaya yönlendirildi  ->  " + currentURL);
        }
        else {
            System.out.println("** Açılan sayfa doğru değildir! ** \nSeçilen URL: " + selectedURL + "\nAçılan URL: " + currentURL);
        }
    }

    public void scrollHeightByJS() {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollUpTop () {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0)");
    }

    public void scrollIntoViewElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public int getDocumentBodyScrollHeightTypeInt() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return  ((Number) js.executeScript("return document.body.scrollHeight")).intValue();
    }

    public boolean checkImageState (WebElement element) {
        return (Boolean) ((JavascriptExecutor)driver).executeScript
                ("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);

    }

    public void waitUntilDocumentReadyState() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public int randomInt (int index) {
        Random random = new Random();
        return random.nextInt(index);
    }

    public void clickByJS (WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }
}
