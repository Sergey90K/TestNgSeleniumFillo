package PageObject;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RedmineObjForTask {

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getIrcNick() {
        return ircNick;
    }

     private String user = "User";
     private String password = "Password";
     private String confirmPassword = "Confirm Password";
     private String name = "Name";
     private String lastName = "LastName";
     private String email = "Email";
     private String ircNick = "IRC nick";

    private ArrayList<String> ollNameOfField() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(this.user);
        list.add(this.password);
        list.add(this.confirmPassword);
        list.add(this.name);
        list.add(this.lastName);
        list.add(this.email);
        list.add(ircNick);
        return list;
    }

    private WebDriver driverInitialization(){
        WebDriver driver = new ChromeDriver();
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        try {
            driver.get("https://www.redmine.org/account/register");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public void validationsOfReferences(String xpasRef, String urlRef) {
        WebDriver driver = this.driverInitialization();
        try {
            WebElement webElement = driver.findElement(By.xpath(xpasRef));
            webElement.click();
            Thread.sleep(500);
            Assert.assertEquals(driver.getCurrentUrl(), urlRef);
            driver.navigate().back();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

    public void allValidationsOfReferences(ArrayList<String> arrayListXpas, ArrayList<String> arrayListUrl) {
        if (arrayListUrl.size() == arrayListXpas.size()) {
            for (int i = 0; i < arrayListXpas.size(); i++) {
                String strXpas = arrayListXpas.get(i);
                String strUrl = arrayListUrl.get(i);
                this.validationsOfReferences(strXpas, strUrl);
            }
        } else {
            System.out.println("the number of links does not match");
        }
    }

    public void addValueInField(String [] innerData ,ArrayList<String> allFieldXpas,String lickButton, String errorField) {
        WebDriver driver = this.driverInitialization();
        try {
            Thread.sleep(500);
            for (int j = 0; j < allFieldXpas.size(); j++) {
                WebElement webElement = driver.findElement(By.xpath(allFieldXpas.get(j)));
                webElement.sendKeys(innerData[j]);
            }
            Thread.sleep(500);
            WebElement okButton = driver.findElement(By.xpath(lickButton));
            okButton.click();
            Thread.sleep(3000);
            WebElement errorFild = driver.findElement(By.xpath(errorField));
            boolean rez = errorFild.isDisplayed();
            Thread.sleep(500);
            this.takeScreenshot(driver);
            Assert.assertEquals(rez, true);
        } catch (InterruptedException  e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public void testInputField(ArrayList<String> allFieldXpas, String lickButton, String errorField) {
        WebDriver driver =this.driverInitialization();
        Fillo fillo = new Fillo();
        ArrayList<String> nameFieldList = this.ollNameOfField();
        this.createSheets();
        try {
            Connection connection = fillo.getConnection("InputData.xlsx");
            String strQuery = "Select * from UserData";
            Recordset recordset = connection.executeQuery(strQuery);
            while (recordset.next()) {
                String strForWriter = "";
                for (int j = 0; j < allFieldXpas.size(); j++) {
                    WebElement webElement = driver.findElement(By.xpath(allFieldXpas.get(j)));
                    webElement.sendKeys(recordset.getField(nameFieldList.get(j)));
                    strForWriter = strForWriter + "'" + recordset.getField(nameFieldList.get(j)) + "',";
                }
                WebElement okButton = driver.findElement(By.xpath(lickButton));
                okButton.click();
                Thread.sleep(3000);
                WebElement errorFild = driver.findElement(By.xpath(errorField));
                boolean rez = errorFild.isDisplayed();
                Thread.sleep(500);
                this.takeScreenshot(driver);
                Assert.assertEquals(rez, true);
                strForWriter = strForWriter + "'error message is displayed'";
                this.createDataInSheets(strForWriter);
            }
            driver.quit();
            Thread.sleep(500);
            recordset.close();
            connection.close();
        } catch (InterruptedException | FilloException e) {
            e.printStackTrace();
        }
    }

    private void createSheets() {
        String filePath = "OutputData.xlsx";
        try {
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);
            connection.createTable("Sheet1", new String[]{"User", "Password", "Confirm Password", "Name", "LastName", "Email", "IRC nick", "Result"});
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDataInSheets(String innerStr) {
        //String query = "INSERT INTO \"Sheet4\"(\"User\", \"Password\", \"ConfirmPass\", \"Name\",\"LastName\",\"Email\", \"IRC nick\",\"Result\" ) VALUES('user','pass','conf pass', 'name', 'last name', 'email', 'Irc nick', 'rezalt')";
        String query = "INSERT INTO \"Sheet1\"(\"User\", \"Password\", \"Confirm Password\", \"Name\",\"LastName\",\"Email\", \"IRC nick\",\"Result\" ) VALUES(" + innerStr + ")";
        String filePath = "OutputData.xlsx";
        try {
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);
            connection.executeUpdate(query);
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[][] readDataFromExcel() throws FilloException {
        Fillo fillo=new Fillo();
        this.createSheets();
        Connection connection=fillo.getConnection("InputData.xlsx");
        String strQuery="Select * from UserData";
        Recordset recordset=connection.executeQuery(strQuery);
        int i = 0;
        String [][] ollDataFromSheet = new String[7][recordset.getCount()];
        while (recordset.next()) {
            ollDataFromSheet [0][i]   = recordset.getField(user);
            ollDataFromSheet [1][i]  = recordset.getField(password);
            ollDataFromSheet [2][i]  = recordset.getField(confirmPassword);
            ollDataFromSheet [3][i]  = recordset.getField(name);
            ollDataFromSheet [4][i]  = recordset.getField(lastName);
            ollDataFromSheet [5][i]  = recordset.getField(email);
            ollDataFromSheet [6][i]  = recordset.getField(ircNick);
            i++;
        }
        recordset.close();
        connection.close();
        return ollDataFromSheet;
    }

    public void testSearchField (String xpasRef, String innerData){
        WebDriver driver = this.driverInitialization();
        try {
            WebElement webElement = driver.findElement(By.xpath(xpasRef));
            Thread.sleep(500);
            webElement.sendKeys(innerData);
            String str = webElement.getAttribute("value");
            Assert.assertEquals(str, innerData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

    private void takeScreenshot(WebDriver driver){
        String path;
        WebDriver webDriver = new Augmenter().augment(driver);
        File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        path = "./target/screenshots/" + source.getName();
        try {
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputOnlyOneField(String xpasElement, String nameField, String lickButton, String errorField){
        WebDriver driver = this.driverInitialization();
        Fillo fillo = new Fillo();
        this.createSheets();
        try {
            Connection connection = fillo.getConnection("InputData.xlsx");
            String strQuery = "Select * from UserData";
            Recordset recordset = connection.executeQuery(strQuery);
            String strForWriter = "";
            while (recordset.next()) {
                WebElement webElement = driver.findElement(By.xpath(xpasElement));
                webElement.sendKeys(recordset.getField(nameField));
                strForWriter = "'" + recordset.getField(nameField) + "'";
                WebElement okButton = driver.findElement(By.xpath(lickButton));
                okButton.click();
                Thread.sleep(3000);
                WebElement errorFild = driver.findElement(By.xpath(errorField));
                boolean rez = errorFild.isDisplayed();
                this.takeScreenshot(driver);
                Thread.sleep(500);
                this.takeScreenshot(driver);
                Assert.assertEquals(rez, true);
                strForWriter = strForWriter + ",'error message is displayed'";
                createDataInSheets(strForWriter,nameField);
                driver.navigate().back();
                Thread.sleep(1000);
                WebElement webElement1 = driver.findElement(By.xpath(xpasElement));
                webElement1.clear();
            }
            Thread.sleep(500);
            driver.quit();
            recordset.close();
            connection.close();
        } catch (InterruptedException | FilloException e) {
            e.printStackTrace();
        }
    }

    private void createDataInSheets(String innerStr, String nameField) {
        //String query = "INSERT INTO \"Sheet4\"(\"User\", \"Password\", \"ConfirmPass\", \"Name\",\"LastName\",\"Email\", \"IRC nick\",\"Result\" ) VALUES('user','pass','conf pass', 'name', 'last name', 'email', 'Irc nick', 'rezalt')";
        String query = "INSERT INTO \"Sheet1\"(\""+ nameField + "\",\"Result\" ) VALUES(" + innerStr + ")";
        String filePath = "OutputData.xlsx";
        try {
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);
            connection.executeUpdate(query);
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
