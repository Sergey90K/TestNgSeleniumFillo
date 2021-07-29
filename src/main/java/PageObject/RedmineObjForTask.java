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

    String user = "User";
    String password = "Password";
    String confirmPassword = "Confirm Password";
    String name = "Name";
    String lastName = "LastName";
    String email = "Email";
    String ircNick = "IRC nick";

    public ArrayList<String> ollNameOfField() {
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

    public void validationsOfReferences(String inUrl, String xpasRef, String urlRef) {
        WebDriver driver = new ChromeDriver();
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        try {
            driver.get(inUrl);
            Thread.sleep(2000);
            WebElement webElement = driver.findElement(By.xpath(xpasRef));
            webElement.click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.getCurrentUrl(), urlRef);
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public void allValidationsOfReferences(String inUrl, ArrayList<String> arrayListXpas, ArrayList<String> arrayListUrl) {
        if (arrayListUrl.size() == arrayListXpas.size()) {
            for (int i = 0; i < arrayListXpas.size(); i++) {
                String strXpas = arrayListXpas.get(i);
                String strUrl = arrayListUrl.get(i);
                this.validationsOfReferences(inUrl, strXpas, strUrl);
            }

        } else {
            System.out.println("the number of links does not match");
        }
    }

    public void addValueInField(String [] innerData ,String inUrl, ArrayList<String> allFieldXpas,String lickButton, String errorField) {
        WebDriver driver = new ChromeDriver();
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        String path;
        try {
            driver.get(inUrl);
            Thread.sleep(2000);
            for (int j = 0; j < allFieldXpas.size(); j++) {
                WebElement webElement = driver.findElement(By.xpath(allFieldXpas.get(j)));
                webElement.sendKeys(innerData[j]);
            }
            Thread.sleep(1000);
            WebElement okButton = driver.findElement(By.xpath(lickButton));
            okButton.click();
            Thread.sleep(3000);
            WebElement errorFild = driver.findElement(By.xpath(errorField));
            boolean rez = errorFild.isDisplayed();
            Thread.sleep(1000);
            WebDriver webDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
            Assert.assertEquals(rez, true);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public void testInputField(String inputUrl, ArrayList<String> allFieldXpas, String lickButton, String errorField) {
        WebDriver driver = new ChromeDriver();
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Fillo fillo = new Fillo();
        ArrayList<String> nameFieldList = this.ollNameOfField();
        String path;
        this.createSheets();

        try {
            Connection connection = fillo.getConnection("InputData.xlsx");
            String strQuery = "Select * from UserData";
            Recordset recordset = connection.executeQuery(strQuery);
            int i = 0;


            while (recordset.next()) {
                String strForWriter = "";
                driver.get(inputUrl);
                Thread.sleep(2000);
                for (int j = 0; j < allFieldXpas.size(); j++) {
                    WebElement webElement = driver.findElement(By.xpath(allFieldXpas.get(j)));
                    webElement.sendKeys(recordset.getField(nameFieldList.get(j)));
                    strForWriter = strForWriter + "'" + recordset.getField(nameFieldList.get(j)) + "',";
                    i++;
                }
                WebElement okButton = driver.findElement(By.xpath(lickButton));
                okButton.click();
                Thread.sleep(3000);
                WebElement errorFild = driver.findElement(By.xpath(errorField));
                boolean rez = errorFild.isDisplayed();
                Thread.sleep(1000);
                WebDriver webDriver = new Augmenter().augment(driver);
                File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
                path = "./target/screenshots/" + source.getName();
                FileUtils.copyFile(source, new File(path));
                Assert.assertEquals(rez, true);
                strForWriter = strForWriter + "'error message is displayed'";
                this.createDataInSheets(strForWriter);
            }
            driver.quit();
            Thread.sleep(1000);
            recordset.close();
            connection.close();

        } catch (InterruptedException | FilloException | IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void createSheets() {
        String filePath = "OutputData.xlsx";
        try {
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);
            connection.createTable("Sheet1", new String[]{"User", "Password", "ConfirmPass", "Name", "LastName", "Email", "IRC nick", "Result"});
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDataInSheets(String innerStr) {
        //String query = "INSERT INTO \"Sheet4\"(\"User\", \"Password\", \"ConfirmPass\", \"Name\",\"LastName\",\"Email\", \"IRC nick\",\"Result\" ) VALUES('user','pass','conf pass', 'name', 'last name', 'email', 'Irc nick', 'rezalt')";
        String query = "INSERT INTO \"Sheet1\"(\"User\", \"Password\", \"ConfirmPass\", \"Name\",\"LastName\",\"Email\", \"IRC nick\",\"Result\" ) VALUES(" + innerStr + ")";
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

    public void testSearchField (String inUrl, String xpasRef, String innerData){
        WebDriver driver = new ChromeDriver();
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        try { driver.get(inUrl);
            Thread.sleep(2000);
            WebElement webElement = driver.findElement(By.xpath(xpasRef));
            Thread.sleep(2000);
            webElement.sendKeys(innerData);
            String str = webElement.getAttribute("value");
            Assert.assertEquals(str, innerData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }




}
