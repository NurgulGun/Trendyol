package pages;

import utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CampaignPage extends BasePage {

    List<WebElement> campaignList;
    List<WebElement> productList;
    ArrayList<String> productURLList = new ArrayList<>();
    ArrayList<String> campaignURLList = new ArrayList<>();
    String currentURL = null;
    By campaignContainer = By.cssSelector("article > a");
    By productContainer = By.cssSelector("div.p-card-chldrn-cntnr > a");
    By productContainer2 = By.cssSelector("div.boutique-product > a");
    By productImageTag = By.className("p-card-img");
    By boutiqueProductLabel = By.className("boutique-product-label");

    public CampaignPage(WebDriver driver) {
        super(driver);
    }

    private void fillCampaignList() throws InterruptedException {
        Thread.sleep(1000);
        campaignList = driver.findElements(campaignContainer);

        for (WebElement element: campaignList) {
            campaignURLList.add(element.getAttribute("href"));
            System.out.println("** " + element.getAttribute("href") + " URL'i diziye eklendi **");
        }

    }

    private void fillProductList() throws InterruptedException {
        Thread.sleep(2000);
        productList = driver.findElements(productContainer);
        if(productList == null || productList.size() == 0) {
            productList = driver.findElements(productContainer2);
        }

        for(WebElement element: productList) {
            productURLList.add(element.getAttribute("href"));
            System.out.println("** " + element.getAttribute("href") + " URL'i diziye eklendi **");
        }
    }


    public void selectRandomCampaign() throws InterruptedException {

        fillCampaignList();
        int index = randomInt(campaignList.size());

//      Rastgele butik seçilir.
        WebElement campaign = campaignList.get(index - 1);
        scrollIntoViewElement(campaign);
        clickByJS(campaign);
        Thread.sleep(1000);
        currentURL = driver.getCurrentUrl();

//      Açılan sayfanın doğru olduğu kontrol edilir.
        checkPageURLWithAssertion(currentURL,campaignURLList.get(index - 1));

    }

    public void checkProductImage() throws InterruptedException {

//      Ürünlerin tamamının yüklenmesi için ürün sayısına göre scroll edilir.
        String[] boutiqueProductLabelSplitList = driver.findElement(boutiqueProductLabel).getText().split(" ");
        String boutiqueProductCount = boutiqueProductLabelSplitList[0].substring(1);

        do {
            scrollDown(800);
            waitUntilDocumentReadyState();
            Thread.sleep(1000);

        }while (Integer.parseInt(boutiqueProductCount) != driver.findElements(productImageTag).size());

//      Ürün image'leri kontrol edilir.
        for (WebElement element: driver.findElements(productImageTag)) {
            if (!checkImageState(element))
            {
                System.out.println("Image yüklenmedi ->  " + element.getAttribute("alt"));
            }
        }
    }

    public void selectRandomProduct() throws InterruptedException {

        fillProductList();

        int index = randomInt(productList.size());
        WebElement product = productList.get(index - 1);
        scrollIntoViewElement(product);
        System.out.println("** Seçilen ürün **\n" + product.getText());
        Thread.sleep(800);

        clickByJS(product);

        waitUntilDocumentReadyState();
        Thread.sleep(1000);
        currentURL = driver.getCurrentUrl();

        checkPageURLWithAssertion(currentURL,productURLList.get(index - 1));

    }
}
