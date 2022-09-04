import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrendyolBasket_Page {
    private WebDriver driver;

    public TrendyolBasket_Page(WebDriver driver) {
        this.driver=driver;
    }

    //Ürünlerimizi sepete attık ve fiyatlarını alıp bir listeye ekledik.
    //Eklediğimiz ürünlerin isimlerini alıp yazdırdık
    //Ürünlerin fiyatlarını küçükten büyüğe doğru sıralayarak consola yazdırdık
    //Sepetteki total fiyat ile eklediğimiz ürünlerin toplam fiyatını karşılaştırdık
    public  void findGames() throws InterruptedException {
        driver.getWindowHandle();
        driver.findElement(By.id("Group-38")).click();
        Thread.sleep(1000);
        WebElement searchBox=driver.findElement(By.cssSelector(".search-box"));
        searchBox.sendKeys("Bilgin Türkiye");
        Thread.sleep(1000);
        WebElement searchButton=driver.findElement(By.cssSelector(".search-icon"));
        searchButton.click();
    }
    public  void addBasket() throws InterruptedException {

        List<WebElement> games=driver.findElements(By.xpath("//span[starts-with(@class,'prdct-desc-cntnr-name') and @title='Bilgin Türkiye Zeka Oyunu']"));
        WebDriverWait wait=new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.visibilityOfAllElements(games));
        Actions gameAction=new Actions(driver);
        for (WebElement gm:games) {
            gameAction.moveToElement(gm).perform();
            System.out.println(gm.getText());
            gm.click();
            Thread.sleep(1000);

            List<String> pagelist=new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(pagelist.get(1));

            WebElement addToBasket=driver.findElement(By.cssSelector(".add-to-basket"));
            Thread.sleep(1000);
            addToBasket.click();
            driver.close();
            driver.switchTo().window(pagelist.get(0));
        }
        Thread.sleep(1000);
        WebElement basketButton=driver.findElement(By.cssSelector("div.account-nav-item.basket-preview > a"));
        basketButton.click();
        Thread.sleep(1000);

        ArrayList<Integer> integList=new ArrayList<Integer>();

        List<WebElement> gameNames=driver.findElements(By.cssSelector("pb-item"));
        for (WebElement name:gameNames)
        {
            String nm= name.getText();
            System.out.println(nm);
        }
        List<WebElement> gamePrice=driver.findElements(By.cssSelector(".pb-basket-item-price"));

        int ttotale = 0;
        for (WebElement price:gamePrice) {
            String end_price=price.getText().substring(0,3);
            int gameprice = Integer.parseInt(end_price);
            ttotale=ttotale+gameprice;
            integList.add(gameprice);
            Thread.sleep(1000);
        }

        for (int i = 0; i < integList.size(); i++) {
           Collections.sort(integList);
          System.out.println("gamePrices = " + integList.get(i));
        }

        WebElement totalPrice=driver.findElement(By.xpath("//*[@id=\"pb-container\"]/aside/div/div[2]/ul/li[1]/strong"));
        String total=totalPrice.getText().substring(0,3);
        int totalprice = Integer.parseInt(total);
        System.out.println("Total price :" + totalprice);
        Assert.assertEquals(ttotale,totalprice);
        System.out.println("***..........Total price is true.............***");
    }
}
