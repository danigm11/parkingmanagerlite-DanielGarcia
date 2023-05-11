package com.hormigo.david.parkingmanager.bdd.steps;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.bdd.CucumberConfiguration;
import com.hormigo.david.parkingmanager.user.service.UserService;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
public class CucumberSteps extends CucumberConfiguration {

    @MockBean
    private UserService userService;
    @Value("${local.server.port}")
    private  int port;
    private static ChromeDriver driver;
    @BeforeAll
    public static void prepareWebDriver() {

        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        
    }

    @Given("un usuario esta en la pagina inicial")
    public void openHome() {
        driver.get("http://localhost:" + port + "/");


    }




    @When("el usuario hace click sobre el botón de Usuarios")
    public void clickUserButton(){
        driver.findElement(By.id("to-users-link")).click();

    }

    @Then("se muestran todos los usuarios del sistema")
    public void navigateToUsersList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/users"));
    }

    @When("el usuario hace click sobre el botón de Sorteos")
    public void clickDrawButton(){
        driver.findElement(By.id("to-draws-link")).click();

    }
    @Then("se muestran todos los sorteos del sistema")
    public void navigateToDrawsList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/draws"));
    }

    @Given("un usuario esta en la lista de sorteos")
    public void openDrawCreateForm()
    {
        driver.get("http://localhost:" + port + "/draws");
    }

    @When("el usuario hace click sobre el botón de crear Sorteos")
    public void clickDrawCreateButton(){
        driver.findElement(By.id("newdraw")).click();

    }
    @Then("se muestra el formulario de creación de sorteos")
    public void navigateToDrawsForm(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/newDraw"));
    }
    @Given("un usuario esta en la lista de usuarios")
    public void openUserCreateForm()
    {
      driver.get("http://localhost:" + port + "/users");
    }

    @When("el usuario hace click sobre el botón de crear Usuarios")
    public void clickUserCreateButton(){
        driver.findElement(By.id("users-button-create")).click();

    }
    @Then("se muestra el formulario de creación de usuarios")
    public void navigateToUsersForm(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/newUser"));
    }
    
}
