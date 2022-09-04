import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Trendyoltest_page {
    private  WebDriver driver;

    public Trendyoltest_page(WebDriver driver) {this.driver=driver;
    }

    //Trendyol sitesinde Bilgin Türkiye oyununu ara.
    //İsmi Bilgin Türkiye olan her ürünün sayfasına git
    //Hem ürün satıcı bilgilerini hem de fiyatını al
    //Fiyatı belirli bir fiyat ile karşılaştır
    public void writeToSearchBox() throws InterruptedException {
        driver.getWindowHandle();
        driver.findElement(By.id("Group-38")).click();
        WebElement searchBox=driver.findElement(By.cssSelector(".search-box"));
        searchBox.sendKeys("Bilgin Türkiye");
        Thread.sleep(1000);
        WebElement searchButton=driver.findElement(By.cssSelector(".search-icon"));
        searchButton.click();
    }
    public  void getGamesText() throws InterruptedException {
        List<WebElement>  gameText=driver.findElements(By.xpath("//span[starts-with(@class,'prdct-desc-cntnr-name') and @title='Bilgin Türkiye Zeka Oyunu']"));
        WebDriverWait wait=new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.visibilityOfAllElements(gameText));
        Actions gameImages=new Actions(driver);
        for (WebElement gtx:gameText) {
            gameImages.moveToElement(gtx).perform();
            System.out.println(gtx.getText());
            gtx.click();
            Thread.sleep(1000);
            List<String> pagelist=new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(pagelist.get(1));

            Thread.sleep(1000);
            WebElement sellerName=driver.findElement(By.cssSelector(".merchant-box-wrapper"));
            wait.until(ExpectedConditions.visibilityOf(sellerName));
            System.out.println(sellerName.getText());
            Thread.sleep(1000);
            WebElement sellerPrice=driver.findElement(By.cssSelector(".prc-dsc"));
            String price=sellerPrice.getText().substring(0,3);
            int gamePrice = Integer.parseInt(price);
            if(gamePrice<=158)
            {
                System.out.println("The price: "+ gamePrice + "  *The price of the product is ideal ");
            }
            else {System.out.println("The price: "+ gamePrice + " *It is very expensive ");
            }
            driver.close();
            driver.switchTo().window(pagelist.get(0));
        }
    }
}
