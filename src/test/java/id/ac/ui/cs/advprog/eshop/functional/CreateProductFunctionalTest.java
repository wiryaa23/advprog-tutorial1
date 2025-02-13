package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@DirtiesContext
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createButtonAvailable(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/list");
        WebElement createButton = driver.findElement(By.xpath("//a[contains(text(),'Create Product')]"));
        assertTrue(createButton.isDisplayed());
    }

    @Test
    void createFormPageAvailable(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        String title = driver.getTitle();
        assertTrue(title.contains("Create New Product"));
    }

    @Test
    void createProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys("55");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(baseUrl + "/product/list", currentUrl);

        boolean isContainCreatedProductName = driver.getPageSource().contains("Sampo Cap Bambang");
        assertTrue(isContainCreatedProductName);
        boolean isContainCreatedProductQuantity = driver.getPageSource().contains("55");
        assertTrue(isContainCreatedProductQuantity);
    }
}
