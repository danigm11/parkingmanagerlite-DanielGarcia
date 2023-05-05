package com.hormigo.david.parkingmanager.home;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.user.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageTest {


    @MockBean
    private UserService userService;
    @Value("${local.server.port}")
    private int port;
    private static ChromeDriver chromeDriver;
    
    @BeforeAll
    public static void prepareWebDriver() {

        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        chromeDriver = new ChromeDriver(options);
        
    }

    @Test
    public void testIndex(){
      String url ="";
      url = ("http://localhost:"+port+"/");
      chromeDriver.get(url);
      String titulo = chromeDriver.getTitle();
      WebElement createButton = chromeDriver.findElement(By.id("to-draws-link"));
      WebElement createButton2 = chromeDriver.findElement(By.id("to-users-link"));
      WebElement createButton3 = chromeDriver.findElement(By.id("to-home-link"));

      
      assertAll("Comprobacion index", 
      () -> {assertEquals("CPIFP Los Camaleones", titulo);},
      () -> {assertNotNull(createButton);},
      () -> {assertNotNull(createButton2);},
      () -> {assertNotNull(createButton3);});

      chromeDriver.quit();
    }
    @Test
    public void testNavigationIndexUserList() {
      String url1 = ("http://localhost:"+port+"/");
      String url = ("http://localhost:"+port+"/users");

      chromeDriver.get(url1);
      WebElement createUserLink = chromeDriver.findElement(By.id("to-users-link"));
      createUserLink.click();
      String url2 = chromeDriver.getCurrentUrl();

      assertEquals(url, url2);
      chromeDriver.quit();
    }
    @Test
    public void testNavigationIndexDrawList() {
      String url1 = ("http://localhost:"+port+"/");
      String url = ("http://localhost:"+port+"/draws");

      chromeDriver.get(url1);
      WebElement createUserLink = chromeDriver.findElement(By.id("to-draws-link"));
      createUserLink.click();
      String currentUrl = chromeDriver.getCurrentUrl();

      assertEquals(url, currentUrl);
      chromeDriver.quit();
    }
    @Test
    public void testNavigationIndexInicio() {
      String url1 = ("http://localhost:"+port+"/");
      String url = ("http://localhost:"+port+"/");

      chromeDriver.get(url1);
      WebElement createUserLink = chromeDriver.findElement(By.id("to-home-link"));
      createUserLink.click();
      String currentUrl = chromeDriver.getCurrentUrl();

      assertEquals(url, currentUrl);
      chromeDriver.quit();
    }
}