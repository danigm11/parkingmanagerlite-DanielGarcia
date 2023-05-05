package com.hormigo.david.parkingmanager.user.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.Role;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
import com.hormigo.david.parkingmanager.user.domain.UserRepository;
import com.hormigo.david.parkingmanager.user.service.UserService;
import com.hormigo.david.parkingmanager.user.service.UserServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegration {

  @MockBean
  private UserService userService;
  @Value("${local.server.port}")
  private int port;
  private static ChromeDriver chromeDriver;

  @BeforeAll
  public static void prepareWebDriver() {

    System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    chromeDriver = new ChromeDriver(options);

  }

  @Test
  public void testUserList() {
    String url = ("http://localhost:" + port + "/users");
    chromeDriver.get(url);
    String titulo = chromeDriver.getTitle();
    WebElement createButton = chromeDriver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = chromeDriver.findElement(By.id("to-users-link"));
    WebElement createButton3 = chromeDriver.findElement(By.id("to-home-link"));
    WebElement createButton4 = chromeDriver.findElement(By.id("users-button-create"));
    assertAll("Comprobacion lista usuarios",
        () -> {
          assertEquals("Usuarios", titulo);
        },
        () -> {
          assertNotNull(createButton);
        },
        () -> {
          assertNotNull(createButton2);
        },
        () -> {
          assertNotNull(createButton3);
        },
        () -> {
          assertNotNull(createButton4);
        });

    chromeDriver.quit();
  }

  @Test
  public void testCreateUser() {
    String url = ("http://localhost:" + port + "/newUser");
    chromeDriver.get(url);
    String titulo = chromeDriver.getTitle();
    WebElement createButton = chromeDriver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = chromeDriver.findElement(By.id("to-users-link"));
    WebElement createButton3 = chromeDriver.findElement(By.id("to-home-link"));
    WebElement createButton4 = chromeDriver.findElement(By.id("user-create-field-email"));
    assertAll("Comprobacion crear usuarios",
        () -> {
          assertEquals("Crear nuevo usuario", titulo);
        },
        () -> {
          assertNotNull(createButton);
        },
        () -> {
          assertNotNull(createButton2);
        },
        () -> {
          assertNotNull(createButton3);
        },
        () -> {
          assertNotNull(createButton4);
        });

    chromeDriver.quit();
  }

  @Test
  public void testNavigationDeUsersAUsers() {
    String url1 = ("http://localhost:" + port + "/users");
    String url = ("http://localhost:" + port + "/users");

    chromeDriver.get(url1);
    WebElement createUserLink = chromeDriver.findElement(By.id("to-users-link"));
    createUserLink.click();
    String url2 = chromeDriver.getCurrentUrl();
    assertEquals(url, url2);
    chromeDriver.quit();
  }

  @Test
  public void testNavigationDeUsersAHome() {
    String url1 = ("http://localhost:" + port + "/users");
    String url = ("http://localhost:" + port + "/");

    chromeDriver.get(url1);
    WebElement createUserLink = chromeDriver.findElement(By.id("to-home-link"));
    createUserLink.click();
    String url2 = chromeDriver.getCurrentUrl();
    assertEquals(url, url2);
    chromeDriver.quit();
  }

  @Test
  public void testNavigationDeUsersASorteos() {
    String url1 = ("http://localhost:" + port + "/users");
    String url = ("http://localhost:" + port + "/draws");

    chromeDriver.get(url1);
    WebElement createUserLink = chromeDriver.findElement(By.id("to-draws-link"));
    createUserLink.click();
    String url2 = chromeDriver.getCurrentUrl();
    assertEquals(url, url2);
    chromeDriver.quit();
  }

  @Test
  public void testNavigationDeUsersACreateUsers() {
    String url1 = ("http://localhost:" + port + "/users");
    String url = ("http://localhost:" + port + "/newUser");

    chromeDriver.get(url1);
    WebElement createUserLink = chromeDriver.findElement(By.id("users-button-create"));
    createUserLink.click();
    String url2 = chromeDriver.getCurrentUrl();
    assertEquals(url, url2);
    chromeDriver.quit();
  }

  @Test
  public void testNavigationDeCreateUsersAHome() {
    String url1 = ("http://localhost:" + port + "/newUser");
    String url = ("http://localhost:" + port + "/");

    chromeDriver.get(url1);
    WebElement createUserLink = chromeDriver.findElement(By.id("to-home-link"));
    createUserLink.click();
    String url2 = chromeDriver.getCurrentUrl();
    assertEquals(url, url2);
    chromeDriver.quit();
  }

  @Test
  public void testNavigationDeCreateUsersAUsers() {
    String url1 = ("http://localhost:" + port + "/newUser");
    String url = ("http://localhost:" + port + "/users");

    chromeDriver.get(url1);
    WebElement createUserLink = chromeDriver.findElement(By.id("to-users-link"));
    createUserLink.click();
    String url2 = chromeDriver.getCurrentUrl();
    assertEquals(url, url2);
    chromeDriver.quit();
  }

  @Test
  public void testNavigationDeCreateUsersADraws() {
    String url1 = ("http://localhost:" + port + "/newUser");
    String url = ("http://localhost:" + port + "/draws");

    chromeDriver.get(url1);
    WebElement createUserLink = chromeDriver.findElement(By.id("to-draws-link"));
    createUserLink.click();
    String url2 = chromeDriver.getCurrentUrl();
    assertEquals(url, url2);
    chromeDriver.quit();
  }

  @Test
  public void testFormUserEmpty() {
    String url = "";
    url = ("http://localhost:" + port + "/newUser");
    chromeDriver.get(url);
    String titulo = chromeDriver.getTitle();
    WebElement createButton = chromeDriver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = chromeDriver.findElement(By.id("to-users-link"));
    WebElement createButton3 = chromeDriver.findElement(By.id("to-home-link"));
    WebElement createUserLink = chromeDriver.findElement(By.id("user-create-button-submit"));
    createUserLink.click();
    WebElement error = chromeDriver.findElement(By.id("error"));

    assertAll("Comprobacion crear usuario vacio",
        () -> {
          assertEquals("Crear nuevo usuario", titulo);
        },
        () -> {
          assertNotNull(createButton);
        },
        () -> {
          assertNotNull(createButton2);
        },
        () -> {
          assertNotNull(createButton3);
        },
        () -> {
          assertNotNull(error);
        });

    chromeDriver.quit();
  }

  @Test
  public void testFormUserEmailEmpty() {
    String url = "";
    url = ("http://localhost:" + port + "/newUser");
    chromeDriver.get(url);
    String titulo = chromeDriver.getTitle();
    WebElement createButton = chromeDriver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = chromeDriver.findElement(By.id("to-users-link"));
    WebElement createButton3 = chromeDriver.findElement(By.id("to-home-link"));
    WebElement nombre = chromeDriver.findElement(By.id("user-create-field-name"));
    nombre.sendKeys("Nombre");
    WebElement apellido1 = chromeDriver.findElement(By.id("user-create-field-lastname1"));
    apellido1.sendKeys("Apellido");

    WebElement createUserLink = chromeDriver.findElement(By.id("user-create-button-submit"));
    createUserLink.click();
    WebElement error = chromeDriver.findElement(By.id("error"));

    assertAll("Comprobacion crear usuario campo email vacio",
        () -> {
          assertEquals("Crear nuevo usuario", titulo);
        },
        () -> {
          assertNotNull(createButton);
        },
        () -> {
          assertNotNull(createButton2);
        },
        () -> {
          assertNotNull(createButton3);
        },
        () -> {
          assertNotNull(error);
        });

    chromeDriver.quit();
  }

  @Test
  public void testFormUserNameEmpty() {
    String url = "";
    url = ("http://localhost:" + port + "/newUser");
    chromeDriver.get(url);
    String titulo = chromeDriver.getTitle();
    WebElement createButton = chromeDriver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = chromeDriver.findElement(By.id("to-users-link"));
    WebElement createButton3 = chromeDriver.findElement(By.id("to-home-link"));
    WebElement nombre = chromeDriver.findElement(By.id("user-create-field-email"));
    nombre.sendKeys("correo@gmail.com");
    WebElement apellido1 = chromeDriver.findElement(By.id("user-create-field-lastname1"));
    apellido1.sendKeys("Apellido");

    WebElement createUserLink = chromeDriver.findElement(By.id("user-create-button-submit"));
    createUserLink.click();
    WebElement error = chromeDriver.findElement(By.id("error"));

    assertAll("Comprobacion crear usuario campo nombre vacio",
        () -> {
          assertEquals("Crear nuevo usuario", titulo);
        },
        () -> {
          assertNotNull(createButton);
        },
        () -> {
          assertNotNull(createButton2);
        },
        () -> {
          assertNotNull(createButton3);
        },
        () -> {
          assertNotNull(error);
        });

    chromeDriver.quit();
  }

  @Test
  public void testFormUserLastName1Empty() {
    String url = "";
    url = ("http://localhost:" + port + "/newUser");
    chromeDriver.get(url);
    String titulo = chromeDriver.getTitle();
    WebElement createButton = chromeDriver.findElement(By.id("to-draws-link"));
    WebElement createButton2 = chromeDriver.findElement(By.id("to-users-link"));
    WebElement createButton3 = chromeDriver.findElement(By.id("to-home-link"));
    WebElement nombre = chromeDriver.findElement(By.id("user-create-field-email"));
    nombre.sendKeys("correo@gmail.com");
    WebElement apellido1 = chromeDriver.findElement(By.id("user-create-field-name"));
    apellido1.sendKeys("Nombre");

    WebElement createUserLink = chromeDriver.findElement(By.id("user-create-button-submit"));
    createUserLink.click();
    WebElement error = chromeDriver.findElement(By.id("error"));

    assertAll("Comprobacion crear usuario campo nombre vacio",
        () -> {
          assertEquals("Crear nuevo usuario", titulo);
        },
        () -> {
          assertNotNull(createButton);
        },
        () -> {assertNotNull(createButton2);
        },
        () -> {
          assertNotNull(createButton3);
        },
        () -> {
          assertNotNull(error);
        });

    chromeDriver.quit();
  }
/* 
  @Test
  void testRegistroEmailRepetido() {
    String error = null;
    String url = "";
    url = ("http://localhost:" + port + "/newUser");
    chromeDriver.get(url);
    UserRepository mockedRepository = mock(UserRepository.class);

    UserService service = new UserServiceImpl(mockedRepository);
    UserDao userDao = new UserDao("email@gmail.com","nombre", "apellido1","apellido2",Role.STUDENT);
    UserDao userDao2 = new UserDao("email@gmail.com","nombre", "apellido1","apellido2",Role.STUDENT);
    try {
      service.register(userDao);
      when(mockedRepository.findByEmail("email@gmail.com")).thenReturn(new User("email@gmail.com","nombre", "apellido1","apellido2",Role.STUDENT));
    } catch (UserExistsException e) {
      error="error";
    }
    assertNotNull(error);

  }*/

  @Test
  void testRegistroEmailRepetido() {

    UserRepository mockedRepository = mock(UserRepository.class);
    UserService service = new UserServiceImpl(mockedRepository);


    UserDao userDao = new UserDao("email@gmail.com","nombre", "apellido1","apellido2",Role.STUDENT);
    when(mockedRepository.findByEmail("email@gmail.com")).thenReturn(new User("email@gmail.com","nombre", "apellido1","apellido2",Role.STUDENT));

    //Act y Assert

    assertThrows(UserExistsException.class, 
      ()->{
        service.register(userDao);
      });
      chromeDriver.quit();
}
@Test
public void testCrearUser() {


    UserRepository mockedRepository = mock(UserRepository.class);
    User user = new User("email@gmail.com","nombre", "apellido1","apellido2",Role.STUDENT);

    chromeDriver.get("http://localhost:" +port+ "/newUser");

    WebElement nombre = chromeDriver.findElement(By.id("user-create-field-name"));
    nombre.sendKeys("nombre");
    WebElement apellido = chromeDriver.findElement(By.id("user-create-field-lastname1"));
    apellido.sendKeys("apellido");
    WebElement email = chromeDriver.findElement(By.id("user-create-field-email"));
    email.sendKeys("email@gmail.com");
    chromeDriver.findElement(By.id("user-create-button-submit")).click();

    ArrayList<User> users = new ArrayList<>();
    UserService service = new UserServiceImpl(mockedRepository); 
    for (User usuario : service.getAll()) {
      users.add(usuario);
  }
    assertEquals("nombre", users.get(0).getName());
    assertEquals("apellido", users.get(0).getLastName1());
    assertEquals("email@gmail.com", users.get(0).getEmail());
    chromeDriver.quit();
}
}