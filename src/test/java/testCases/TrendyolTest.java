package testCases;

import pages.CampaignPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.TabPage;
import utils.BaseTest;
import org.junit.Test;

public class TrendyolTest extends BaseTest {

    @Test
    public void Test() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        TabPage tabPage = new TabPage(driver);
        CampaignPage campaignPage = new CampaignPage(driver);
        ProductPage productPage = new ProductPage(driver);

        loginPage.login("personalsample0@gmail.com", "P123456.");
        loginPage.closeNotification();
        tabPage.changeTabAndCheckImage();
        campaignPage.selectRandomCampaign();
        campaignPage.checkProductImage();
        campaignPage.selectRandomProduct();
        productPage.addBasket();

    }
}
