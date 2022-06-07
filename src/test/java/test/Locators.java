package test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testPackege.Table;

import javax.xml.crypto.Data;
import java.util.concurrent.TimeUnit;

public class Locators {
    Table table = new Table();
    WebDriver driver;

    @FindBy(css = "#email")
    private WebElement inputEmail;
    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement inputPassword;
    @FindBy(xpath = "//a[@id='login-submit']")
    private WebElement logInButton;
    @FindBy(xpath = "//a[@title='Добавить сотрудника']")
    private WebElement titleAddEmployee;
    @FindBy(xpath = "//span[@id='button-1037-btnInnerEl']")
    private WebElement importEmployeesButton;
    @FindBy(css = "#myModalLabel")
    private WebElement modalWindowTitleImportEmployees;
    @FindBy(css = "#file")
    private WebElement choiceFileButton;
    @FindBy(xpath = "//button[@id='yaware-modal-button-0']")
    private WebElement importButton;
    @FindBy(xpath = "//*[@id=\"modal\"]/div/div/div[2]/div[1]/ul/li[1]/ul/li")
    private WebElement firsEmployeeInList;
    @FindBy(css = "#modal > div > div > div.modal-body > label")
    private WebElement sendEmailCheckBox;
    @FindBy(css = "#yaware-modal-button-0")
    private WebElement confirmImportButton;
    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement successfulMessage;
    @FindBy(xpath = "//a[@class=\"grid-icon grid-icon-send-invite\"][1]")
    private WebElement sendInviteEmailAgainButton;
    @FindBy(xpath = "//*[@id=\"toast-container\"]/div/div[2]")
    private WebElement successfulInviteMessage;
    @FindBy(xpath = "//a[@class=\"grid-icon fa fa-link\"][1]")
    private WebElement copiedLinkButton;
    @FindBy(xpath = "//*[@id=\"macos-download-link\"]")
    private WebElement copiedLinkForMacInDropDown;
    @FindBy(xpath = "//a[@class=\"grid-icon grid-icon-hidden-invite\"][1]")
    private WebElement deleteInviteFromList;
    @FindBy(xpath = "//*[@id=\"yaware-modal-button-0\"]")
    private WebElement confirmDeleteInviteButton;
	@FindBy(xpath="//*[@role=\"gridcell\"][3]")
	private WebElement dataOfInvitation;

    public Locators(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logIn(String email, String password) {
        inputEmail.click();
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        logInButton.click();
    }

    public void importEmployee() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(titleAddEmployee));
        importEmployeesButton.click();
        wait.until(ExpectedConditions.visibilityOf(modalWindowTitleImportEmployees));
        wait.until(ExpectedConditions.elementToBeClickable(choiceFileButton)).sendKeys("/Users/roman/IdeaProjects/testForYaware/Employee_list.csv");
        importButton.click();
    }

    public void checkValidImport() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        String text1 = firsEmployeeInList.getText().replace("(", "").replace(")", "");
        String text2 = table.getInfoFromTable().toString().replace(",", "").replace("[", "");
        if (text2.contains(text1)) {
            sendEmailCheckBox.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            confirmImportButton.click();
            wait.until(ExpectedConditions.visibilityOf(successfulMessage));
            Assert.assertTrue(successfulMessage.isDisplayed());
        } else System.out.println("Table contents are not imported");
    }

    public void checkInviteEmail() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String firstData = dataOfInvitation.getText();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        sendInviteEmailAgainButton.click();
        wait.until(ExpectedConditions.visibilityOf(successfulInviteMessage));
        Assert.assertTrue(successfulInviteMessage.isDisplayed());
        String secondData = dataOfInvitation.getText();
        Assert.assertEquals(firstData.equals(secondData),false);
    }

    public void checkCopiedLink() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        copiedLinkButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(copiedLinkForMacInDropDown)).click();
        wait.until(ExpectedConditions.visibilityOf(successfulInviteMessage));
        Assert.assertTrue(successfulInviteMessage.isDisplayed());
    }

    public void checkDeleteInviteFromList() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        deleteInviteFromList.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteInviteButton)).click();
        wait.until(ExpectedConditions.visibilityOf(successfulInviteMessage));
        Assert.assertTrue(successfulInviteMessage.isDisplayed());
    }
}
