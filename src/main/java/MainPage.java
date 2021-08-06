import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.navigate().to("https://ikk.hu/");
    }

    public final By POPUP = By.xpath("//*[@id=\"__next\"]/div[5]/div/div[2]/button[2]");
    public final By KV_BUTTON = By.xpath("//*[@id=\"__next\"]/div[3]/div/div/div/div/a");
//    public final By HIREK_MENU = By.xpath("//*[@id=\"__next\"]/div[3]/div/div/div/button[2]");
    public final By HIREK_MENU = By.xpath("//*[contains(@class,'chakra-button css-17w4tfg')][1]");
    public final By HIR = By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/nav/div/div/div[3]/div[7]/div/a");
    public final By NEWS_LIST = By.xpath("//*[contains(@class,'chakra-link css-wpslp8')]");
 //   public final By NEWS_LIST = By.className("chakra-link css-wpslp8");
    public final By RIGHTBUTTON = By.xpath("//*[@id=\"__next\"]/div[4]/main/div/div[1]/div/div/div[1]/button[2]");
    public final By MAIN_TEXT = By.xpath("//*[@id=\"__next\"]/div[4]/main/div/div[1]/div/div/div[2]/div/a/h1");


    public void click(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public boolean findNews(By by, By by2)  throws InterruptedException{
        Thread.sleep(200);
        List<WebElement> newsList = driver.findElements(by);
     //   System.out.println(newsList.get(0).getText());
        for (WebElement i:newsList) {
   //         System.out.println(i.getText());
            if (i.getText().equals("Oktatói béremelés")) {
                click(by2);
                return true;
            }
        }
        return false;
    }

    public List<String> listMaker() throws InterruptedException{
        List<String> titleList = new ArrayList<>();
        do {
            click(RIGHTBUTTON);
            Thread.sleep(300);
            String text = driver.findElement(MAIN_TEXT).getText();
            if (!titleList.contains(text)) {
            titleList.add(text);
            Thread.sleep(300);
            }else {break;}
        }
        while (true);
        return titleList;
    }
}
