package com.hormigo.david.parkingmanager.bdd.steps;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
  private int port;
  private static ChromeDriver driver;

  @BeforeAll
  public static void prepareWebDriver() {

    System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);

  }

  @Given("un usuario esta en la pagina inicial")
  public void openHome() {
    driver.get("http://localhost:" + port + "/");

  }

  @When("el usuario hace click sobre el botón de Usuarios")
  public void clickUserButton() {
    driver.findElement(By.id("to-users-link")).click();

  }

  @Then("se muestran todos los usuarios del sistema")
  public void navigateToUsersList() {
    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("/users"));
  }

  @When("el usuario hace click sobre el botón de Sorteos")
  public void clickDrawButton() {
    driver.findElement(By.id("to-draws-link")).click();

  }

  @Then("se muestran todos los sorteos del sistema")
  public void navigateToDrawsList() {
    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("/draws"));
  }

  @Given("un usuario esta en la lista de sorteos")
  public void openDrawCreateForm() {
    driver.get("http://localhost:" + port + "/draws");
  }

  @When("el usuario hace click sobre el botón de crear Sorteos")
  public void clickDrawCreateButton() {
    driver.findElement(By.id("newdraw")).click();

  }

  @Then("se muestra el formulario de creación de sorteos")
  public void navigateToDrawsForm() {
    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("/newDraw"));
  }

  @Given("un usuario esta en la lista de usuarios")
  public void openUserCreateForm() {
    driver.get("http://localhost:" + port + "/users");
  }

  @When("el usuario hace click sobre el botón de crear Usuarios")
  public void clickUserCreateButton() {
    driver.findElement(By.id("users-button-create")).click();

  }

  @Then("se muestra el formulario de creación de usuarios")
  public void navigateToUsersForm() {
    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("/newUser"));
  }

  @Then("se muestra la página de la lista de usuarios")
  public void showUserList() {
    
    String titulo = driver.getTitle();
    WebElement createButton = driver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = driver.findElement(By.id("to-users-link"));
    WebElement createButton3 = driver.findElement(By.id("user-list-table"));
    WebElement createButton4 = driver.findElement(By.id("users-button-create"));
    assertAll("Comprobacion lista usuarios",
        () -> {
          assertEquals("Usuarios", titulo);
        },
        () -> {
          assert(createButton2.isDisplayed());
        },
        () -> {
          assert(createButton.isDisplayed());
        },
        () -> {
          assert(createButton3.isDisplayed());
        },
        () -> {
          assert(createButton4.isDisplayed());
        });
  }
  @Then("se muestra la página de la lista de sorteos")
  public void showDrawList() {
    String url = ("http://localhost:" + port + "/draws");
    driver.get(url);
    String titulo = driver.getTitle();
    WebElement createButton = driver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = driver.findElement(By.id("to-users-link"));
    WebElement createButton3 = driver.findElement(By.id("draw-list-table"));
    WebElement createButton4 = driver.findElement(By.id("newdraw"));
    
    assertAll("Comprobacion lista sorteos",
        () -> {
          assertEquals("Sorteos", titulo);
        },
        () -> {
          assert(createButton.isDisplayed());
        },
        () -> {
          assert(createButton2.isDisplayed());
        },
        () -> {
          assert(createButton3.isDisplayed());
        },
        () -> {
          assert(createButton4.isDisplayed());
        });
  }
  @Given("un usuario esta en la pagina de creación de sorteos")
  public void openDrawForm() {
    driver.get("http://localhost:" + port + "/newDraw");

  }
  @Given("un usuario esta en la pagina de creación de usuarios")
  public void openUserForm() {
    driver.get("http://localhost:" + port + "/newUser");

  }
  @Then("se muestra la página del formulario de creación de sorteos")
  public void showDrawForm() {
    
    String titulo = driver.getTitle();
    WebElement createButton = driver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = driver.findElement(By.id("to-users-link"));
    WebElement createButton3 = driver.findElement(By.id("draw-field-description"));
    WebElement createButton4 = driver.findElement(By.id("draw-button-submit"));
    
    assertAll("Comprobacion formulario de sorteos",
        () -> {
          assertEquals("Crear nuevo sorteo", titulo);
        },
        () -> {
          assert(createButton.isDisplayed());
        },
        () -> {
          assert(createButton2.isDisplayed());
        },
        () -> {
          assert(createButton3.isDisplayed());
        },
        () -> {
          assert(createButton4.isDisplayed());
        });
  }
  @Then("se muestra la página del formulario de creación de usuarios")
  public void showUserForm() {
    
    String titulo = driver.getTitle();
    WebElement createButton = driver.findElement(By.id("user-create-button-submit"));
    WebElement createButton2 = driver.findElement(By.id("user-create-field-lastname1"));
    WebElement createButton3 = driver.findElement(By.id("user-create-field-email"));
    WebElement createButton4 = driver.findElement(By.id("user-create-field-name"));
    WebElement createButton5 = driver.findElement(By.id("user-create-field-lastname1"));
    WebElement createButton6 = driver.findElement(By.id("user-create-field-lastname2"));
    
    assertAll("Comprobacion formulario de usuarios",
        () -> {
          assertEquals("Crear nuevo usuario", titulo);
        },
        () -> {
          assert(createButton.isDisplayed());
        },
        () -> {
          assert(createButton2.isDisplayed());
        },
        () -> {
          assert(createButton3.isDisplayed());
        },
        () -> {
          assert(createButton4.isDisplayed());
        },
        () -> {
          assert(createButton5.isDisplayed());
        },
        () -> {
          assert(createButton6.isDisplayed());
        });
  }
  @Then("se muestra la página principal")
  public void showIndex() {
    
    String titulo = driver.getTitle();
    WebElement createButton = driver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = driver.findElement(By.id("to-users-link"));
    WebElement createButton3 = driver.findElement(By.id("to-home-link"));
    
    assertAll("Comprobacion inicio",
        () -> {
          assertEquals("CPIFP Los Camaleones", titulo);
        },
        () -> {
          assert(createButton.isDisplayed());
        },
        () -> {
          assert(createButton2.isDisplayed());
        },
        () -> {
          assert(createButton3.isDisplayed());
        });
  }
}
