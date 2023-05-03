package com.hormigo.david.parkingmanager.user.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.user.domain.Role;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @MockBean
    private UserService userService;
    @Value("${local.server.port}")
    private  int port;
    private static ChromeDriver chromeDriver;
    @BeforeAll
    public static void prepareWebDriver() {

        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        chromeDriver = new ChromeDriver(options);
        
    }


    @Test
    public void checkUserListIsDisplayed(){
        List<User> users = new ArrayList<>();
        users.add(new User("david@correo","David","Hormigo","Ramírez",Role.PROFESSOR));
        when(userService.getAll()).thenReturn(users);



        String url = "http://localhost:"+ port + "/users";
        chromeDriver.get(url);
        String actualTitle = chromeDriver.getTitle();
        WebElement createButton = chromeDriver.findElement(By.id("users-button-create"));
        WebElement actualHeading = chromeDriver.findElement(By.id("users-title"));
        WebElement table = chromeDriver.findElement(By.className("table"));
        String actualHeadingText = actualHeading.getText();
        
        assertAll("Comprobar que la pagina de lista de usuarios se muestra",
            () -> {assertNotNull(createButton);},
            () -> {assertEquals("Usuarios",actualTitle);},
            () -> {assertEquals("Usuaros",actualHeadingText);},
            () -> {assertNotNull(table);}
        );
        
        

        chromeDriver.quit();


    }
    

}
