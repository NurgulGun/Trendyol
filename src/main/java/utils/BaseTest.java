package utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Scanner;

public class BaseTest {

    protected WebDriver driver;
    private String URL = "https://www.trendyol.com";
    private int browserIndex;

    public BaseTest(WebDriver driver) {
        this.driver = driver;
    }
    public BaseTest() {
    }

    @Before
    public void startBrowser() throws InterruptedException {


        System.out.println("** Lütfen browser seçiniz: ** \n--------------------------\n * Chrome  -> 1 \n * Firefox -> 2 \n--------------------------\n");

        do {
            Scanner reader = new Scanner(System.in);
            browserIndex = reader.nextInt();

            // Browser seçimi parametrik olarak alınır.
            // Seçilen browser'a uygun driver oluşturulur.
            if (browserIndex == 1) {
                System.out.println("** Chrome browser açılıyor **");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
                driver = new ChromeDriver(options);

            }
            else if(browserIndex == 2) {
                System.out.println("** Firefox browser açılıyor **");

                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("dom.webnotifications.enabled", false);
                System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
                driver = new FirefoxDriver(options);

            }
            else {
                System.out.println("** Lütfen 1 veya 2 giriniz! **");
            }
        } while (browserIndex != 1 && browserIndex != 2);

        driver.get(URL);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.findElement(By.className("homepage-popup-gender")).click();
        System.out.println("** Popup kapatıldı **");

    }

    @After
    public void quit() {
        driver.quit();
    }


}
