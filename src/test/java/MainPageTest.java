import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;

    @BeforeAll
    public static void Init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("incognito");
        options.addArguments("--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
        options.addArguments("window-size=1200,730");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
       // driver.manage().window().maximize();
        //       driver.get("https://ikk.hu/");
    }

    @Test
    public void testClickPopup() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.click(mainPage.POPUP);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        mainPage.click(mainPage.KV_BUTTON);

        js.executeScript("window.scrollBy(0,+350)", "");
        Thread.sleep(500);
        js.executeScript("window.scrollBy(0,-350)", "");
        Thread.sleep(500);
        js.executeScript("window.scrollBy(0,+650)", "");
        Thread.sleep(500);
        js.executeScript("window.scrollBy(0,-350)", "");
        Thread.sleep(800);
    }

    @Test
    public void testClickNews()  throws InterruptedException{
        MainPage mainPage = new MainPage(driver);
        mainPage.click(mainPage.POPUP);
       // Allure.addAttachment("First click image", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Thread.sleep(200);
        mainPage.click(mainPage.HIREK_MENU);
    //    Allure.addAttachment("Second click image", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Thread.sleep(200);
        boolean actual = mainPage.findNews(mainPage.NEWS_LIST, mainPage.HIR);
        Assertions.assertTrue(actual);
    }


    @Test
    public void testListMaker() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.click(mainPage.POPUP);
        List<String> actual = mainPage.listMaker();
        List<String> expected = new ArrayList<>();
        expected.add("Érettségi után is vár a szakképzés!");
        expected.add("Robotolimpia");
        expected.add("Pont Ott Parti");
        expected.add("Megérkezett a Szakmavilág applikációja");
        expected.add("Támogatónk");
        Assertions.assertEquals(expected, actual);
    }


    @AfterEach
    public void closing()
     //       throws InterruptedException
    {
    //    Thread.sleep(1000);
        if (driver != null) {
            driver.quit();
        }
    }
}
