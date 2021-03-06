import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyStepdefs {

    private WebDriver driver;
    private String user = "DonaldDuck61";
    private String useduser = "kalle";
    private String usermail = "Kalle@mail.com";

    @Before
    public void createBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Given("Open the webpage")
    public void openTheWebpage() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();
    }

    @Given("I have entered a valid email")
    public void iHaveEnteredAValidEmail() {
        WebElement mail = driver.findElement(By.id("email"));
        mail.sendKeys(usermail);
    }

    @And("I have entered a valid username")
    public void iHaveEnteredAValidUsername() {
        WebElement username = driver.findElement(By.id("new_username"));
        username.sendKeys(user);

    }

    @And("I have entered a valid password")
    public void iHaveEnteredAValidPassword() {
        WebElement password = driver.findElement(By.id("new_password"));
        password.sendKeys("KalleAnka7!");
    }

    @When("I click Sign up")
    public void iClickSignUp(){
        WebElement cookie =
                (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("onetrust-reject-all-handler")));
        WebElement reject = driver.findElement(By.id("onetrust-reject-all-handler"));
        reject.click();
        WebElement button =
                (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("create-account")));

        WebElement signup = driver.findElement(By.id("create-account"));
        signup.click();
    }

    @Then("I will succeed and be moved to the confirmation page")
    public void iWillSucceedAndBeMovedToTheConfirmationPage() {
        String expected = "Success | Mailchimp";
        String actual = driver.getTitle();

        driver.quit();
        Assertions.assertEquals(expected, actual);

    }

    @And("I have entered a username longer then {int} characters")
    public void iHaveEnteredAUsernameLongerThenCharacters(int arg0) {
        WebElement username = driver.findElement(By.id("new_username"));
        username.sendKeys("12345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890");
    }

    @Then("I will get too long name error")
    public void iWillGetTooLongNameError() {
        String expected = "Enter a value less than 100 characters long";
        String actual = "";
        Boolean isPresent = driver.findElements(By.cssSelector("#signup-form > fieldset > div:nth-child(2) > div > span")).size() > 0;
        if (isPresent){
            WebElement error = driver.findElement(By.cssSelector("#signup-form > fieldset > div:nth-child(2) > div > span"));
            actual = error.getText();
        }
        driver.quit();


        Assertions.assertEquals(expected, actual);
    }

    @And("I have entered a username already in use")
    public void iHaveEnteredAUsernameAlreadyInUse() {
        WebElement username = driver.findElement(By.id("new_username"));
        username.sendKeys(useduser);
    }

    @Then("I will fail and get user name already in use")
    public void iWillFailAndGetUserNameAlreadyInUse() {
        String expected = "Another user with this username already exists. Maybe it's your evil twin. Spooky.";
        String actual = "";
        Boolean isPresent = driver.findElements(By.cssSelector("#signup-form > fieldset > div:nth-child(2) > div > span")).size() > 0;
        if (isPresent){
            WebElement error = driver.findElement(By.cssSelector("#signup-form > fieldset > div:nth-child(2) > div > span"));
            actual = error.getText();
        }
        driver.quit();


        Assertions.assertEquals(expected, actual);
    }

    @Then("I will fail and get please enter value under email")
    public void iWillFailAndGetPleaseEnterValueUnderEmail() {
        String expected = "Please enter a value";
        String actual = "";
        Boolean isPresent = driver.findElements(By.cssSelector("#signup-form > fieldset > div:nth-child(1) > div > span")).size() > 0;
        if (isPresent){
            WebElement error = driver.findElement(By.cssSelector("#signup-form > fieldset > div:nth-child(1) > div > span"));
            actual = error.getText();
        }
        driver.quit();


        Assertions.assertEquals(expected, actual);
    }


}

