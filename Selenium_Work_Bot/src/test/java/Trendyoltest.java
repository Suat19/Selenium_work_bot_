import Utility.BaseTest;
import org.junit.jupiter.api.Test;

public class Trendyoltest extends BaseTest {

    Trendyoltest_page trendyoltest_page=new Trendyoltest_page(driver);

    @Test
    public  void search_test() throws InterruptedException
    {
        trendyoltest_page.writeToSearchBox();
       trendyoltest_page.getGamesText();
    }
}