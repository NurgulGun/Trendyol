package pages;

import utils.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By loginContainer = By.cssSelector("div.account-nav-item.user-login-container");
    By emailInput = By.id("login-email");
    By passwordInput = By.id("login-password-input");
    By submitBtn = By.cssSelector("button.q-primary.q-fluid.q-button-medium.q-button.submit");
    By popupCloseBtn = By.id("Combined-Shape");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) throws InterruptedException {

        Thread.sleep(3000);
        driver.findElement(loginContainer).click();
        System.out.println("** Login sayfası açılıyor **");
        Thread.sleep(3000);
        driver.findElement(emailInput).sendKeys(email);
        System.out.println("** Email girildi **");
        driver.findElement(passwordInput).sendKeys(password);
        System.out.println("** Şifre girildi **");
        driver.findElement(submitBtn).click();
        System.out.println("** Login işlemi gerçekleştiriliyor **");
        Thread.sleep(3000);

        Assert.assertTrue("", driver.getPageSource().contains("Hesabım"));

    }

    public void closeNotification() throws InterruptedException {

        // Login sonrası gelen bilgilendirme popupı varsa kapatılır.

        Thread.sleep(2000);
        boolean isExist;
        try {
            driver.findElement(popupCloseBtn);
            isExist = true;
        } catch (NoSuchElementException e) {
            isExist = false;
        }
        if(isExist) {
            driver.findElement(popupCloseBtn).click();
            System.out.println("** Bilgilendirme popup'ı kapatıldı **");
        }

    }

}
