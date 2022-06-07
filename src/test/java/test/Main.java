package test;

import org.openqa.selenium.WebDriver;
import testPackege.Table;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Driver driver = new Driver();
        Locators locators = new Locators(driver.setDriver("https://app.yaware.com.ua/invite#send-invitation"));
        Table table = new Table();

        try {
            table.createCsvFile(10);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        locators.logIn("o.babeniuk+555@yaware.com", "123456");
        locators.importEmployee();

        try {
            locators.checkValidImport();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        locators.checkInviteEmail();
        locators.checkCopiedLink();
        locators.checkDeleteInviteFromList();
    }
}
