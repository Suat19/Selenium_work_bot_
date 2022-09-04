import Utility.BaseTest;
import org.junit.jupiter.api.Test;

public class TrendyolBaskettest extends BaseTest {

    TrendyolBasket_Page trendyolBasket_page=new TrendyolBasket_Page(driver);
    @Test
    public  void test_1() throws InterruptedException {
        trendyolBasket_page.findGames();
        trendyolBasket_page.addBasket();
    }

}
