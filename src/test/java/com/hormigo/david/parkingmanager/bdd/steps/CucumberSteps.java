package com.hormigo.david.parkingmanager.bdd.steps;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.bdd.CucumberConfiguration;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserRepository;
import com.hormigo.david.parkingmanager.user.service.UserService;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
public class CucumberSteps extends CucumberConfiguration {

    @MockBean
    private UserRepository mockedRepository;
    @InjectMocks
    private UserService mockedUserService = mock(UserService.class);
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

    @Given("un administrador esta en el formulario de creación")
    public void openUserCreateForm()
    {
        driver.get("http://localhost:" + port + "/newUser");
    }

    @Given("el correo no esta asignado a otro usuario")
    public void mockUserDoesNotExists(){
        when(mockedUserService.userExists(anyString())).thenReturn(false);
    }

    @Given("relleno el campo {} con {}") 
    public void fillField(String fieldName,String value) {

        WebElement field = driver.findElement(By.id(getFieldIdFromName(fieldName)));
        field.sendKeys(value);

    }

    private String getFieldIdFromName(String name){
        String id = "";
        switch (name) {
            case "correo":
                id = "user-create-field-email";
                break;
            case "nombre":
                id = "user-create-field-name";
                break;
            case "primer apellido":
                id = "user-create-field-lastname1";
                break;
            case "segundo apellido":
                id = "user-create-field-lastname2";
                break;
            case "crear usuario":
                id = "user-create-button-submit";
                break;
            default:
                break;
        }
        return id;
    }


    @When("el usuario hace click sobre el botón de Usuarios")
    public void clickUserButton(){
        driver.findElement(By.id("to-users-link")).click();

    }
    @When("pulso el botón de {}")
    public void clickElement(String element){
        driver.findElement(By.id(getFieldIdFromName(element))).click();
    }
    @Then("se muestra la lista de usuarios")
    public void navigateToUsersList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/users"));
        
    }
    @Then("se ha persistido el usuario en la base de datos")
    public void saveHasBeenCalled(){
        verify(mockedRepository).save(any(User.class));
    }


}
