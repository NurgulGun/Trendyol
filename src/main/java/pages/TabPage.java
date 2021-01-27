package pages;

import utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabPage extends BasePage {

    ArrayList<String> urlList = new ArrayList<>();
    List<WebElement> tabList;
    List<WebElement> tabInfoList;
    List<WebElement> imgList;
    String currentURL = null;


    By tabListCSS = By.cssSelector("li.tab-link");
    By tabInfoCSS = By.cssSelector("li.tab-link > a");
    By imgListCSS = By.cssSelector("span.image-container > img");


    public TabPage(WebDriver driver) {
        super(driver);
    }

    private void fillTabListAndTabInfoList() throws InterruptedException {

        Thread.sleep(2000);
        tabList =  driver.findElements(tabListCSS);
        tabInfoList = driver.findElements(tabInfoCSS);

        for (WebElement element:tabInfoList) {

            urlList.add(element.getAttribute("href"));
            System.out.println("** " + element.getAttribute("href") + " URL'i diziye eklendi **");
        }
        System.out.println("** tab list size -->  " + tabList.size());
        System.out.println("** URL list size -->  " + urlList.size());
    }


    public void changeTabAndCheckImage() throws InterruptedException {

        fillTabListAndTabInfoList();

        for(int i = 0; i<tabList.size(); i++) {

            driver.findElements(tabListCSS).get(i).click();
            System.out.println( i+1  + ". tab'e tıklandı");
            Thread.sleep(1000);
            currentURL = driver.getCurrentUrl();

//          Yanlış sayfa açılması durumunda test sonlandırılır.
            checkPageURLWithAssertion(currentURL,urlList.get(i));

//          Sayfa sonuna gidilerek tüm butiklerin yüklenmesi beklenir.
            int documentCurrentHeight;
            int documentNewHeight;

            do {
                documentCurrentHeight = getDocumentBodyScrollHeightTypeInt();
                scrollHeightByJS();
                waitUntilDocumentReadyState();
                Thread.sleep(1000);
                documentNewHeight = getDocumentBodyScrollHeightTypeInt();

            }while (documentCurrentHeight != documentNewHeight);

            imgList = driver.findElements(imgListCSS);
            System.out.println("** img list size -->  " + imgList.size());

//          Image'lerin yükenip yüklenmediği kontrol edilir.
            for (WebElement element: imgList) {
                if (!checkImageState(element))
                {
                    System.out.println("Image yüklenmedi ->  " + element.getAttribute("alt"));
                }
            }

            scrollUpTop();
            Thread.sleep(2000);
        }
    }

    public void selectRandomTab() throws InterruptedException {
        fillTabListAndTabInfoList();
        Random random = new Random();
        int index = random.nextInt(tabList.size());

//      Rastgele tab seçilir.
        WebElement tab = tabList.get(index - 1);
        tab.click();
        Thread.sleep(1000);
        currentURL = driver.getCurrentUrl();

//      Yanlış sayfa açılması durumunda test sonlandırılır.
        checkPageURLWithAssertion(currentURL,urlList.get(index - 1));

    }


}
