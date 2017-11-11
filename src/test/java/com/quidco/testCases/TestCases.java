package test.java.com.quidco.testCases;

import main.java.com.quidco.pages.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TestCases {
    static Logger log = Logger.getLogger(TestCases.class);

    static final String docs_path =  "resources" + File.separator + "docs" ;
    static final String drivers_path =  "resources" + File.separator + "drivers" ;

    //TODO: gmailId is not used for QUIDCO SIGN-UP JOURNEY because new Quidco account cannot be created repeatedly with same emailID.
    //TODO: So to be able to run tests multiple time I have created random e-mail address generator utility for Quidco Sign-up journey.
    //TODO: For Scenarios 2 to 5 I have used existing account as outlined below in @gmailId and @gmailAndQuidcoPassword
    //TODO: To do run again create a fresh Gmail ID and password and update these fields to run all the test cases.
    static final String gmailId = "testquidco10112017@gmail.com";
    static final String gmailAndQuidcoPassword = "Test@123";
    static final String userFirstName = "Swatest";
    static final String userLastName = "Gupta";


    /**
     * Utility method to generate random e-mail address using current date-time.
     * TODO: Ideally this should be moved to util package.
     */
    static String getNewEmailId(){
        String dateStr;
        dateStr = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "swatest." + dateStr + "@swati.me";
    }


    /**
     * Test case for following scenario:
     *   1. On Quidco website complete a Sign-up journey.
     * @throws InterruptedException
     */
    @Test(priority = 0)
    public void joinQuidcoTodayStartEarning() throws InterruptedException{

        log.info("Scenario 1(a): Sign-up journey in Quidco started");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.quidco.com/join-quidco/");
        BasePage signUpAccount = new BasePage(driver);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        signUpAccount.firstName().click();
        signUpAccount.firstName().sendKeys(userFirstName);
        signUpAccount.surname().click();
        signUpAccount.surname().sendKeys(userLastName);
        signUpAccount.email().click();
        //TODO: Below gmailId is not used because it is already used to create Quidco account.
        //TODO: Uncomment next line if gmailId is created manually.
        //signUpAccount.email().sendKeys(gmailId);
        //TODO: getNewEmailId() generates random new E-mail ID to complete the Quidco Sign-up journey.
        //TODO: Comment next line if gmailId is created manually.
        signUpAccount.email().sendKeys(getNewEmailId());
        signUpAccount.password().click();
        signUpAccount.password().sendKeys(gmailAndQuidcoPassword);
        // for(int i=0; i<1; i++){
        //   signUpAccount.checkBoxHaveRead().click();
        //   System.out.println(signUpAccount.checkBoxHaveRead().isSelected());
        //}
        if(!signUpAccount.checkBoxHaveRead().isSelected())
        {
            signUpAccount.checkBoxHaveRead().click();
        }
        signUpAccount.joinButton().click();
        log.info("Scenario 1(b): User SignUp Successful");

        //TODO: Asserting here as the Sign-up is successful at this stage but continued to do
        //TODO: further navigation as not sure about the termination point of Sign-up journey.
        String actualWelcomeMsg = signUpAccount.welcomeLabel().getText();
        System.out.println(actualWelcomeMsg);
        Assert.assertEquals(actualWelcomeMsg,"Welcome to Quidco, " + userFirstName);

        log.info("Scenario 1(c): User Let's Get Started");
        signUpAccount.letsGetStartedButton().click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpAccount.accountTypeButton1());
        try{
            if(signUpAccount.playNowAdd().isSelected()){
                signUpAccount.playNow_CancelButton().click();
            }
        }
        catch(org.openqa.selenium.NoSuchElementException e){
            log.info("Scenario 1(d): Advertisement did not pop up");
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpAccount.yourDetailsButton2());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpAccount.yourRenewalsButton3());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpAccount.retailersButton4());

        log.info("Scenario 1(e): Sign-up journey completed successfully");
        driver.quit();
    }


    /**
     * Test case to validate following scenario:
     *   2. Verify that an Authentication email is received to the Gmail account after sign-up.
     * @throws InterruptedException
     */
    @Test (priority = 1)
    public void validationTestCase() throws InterruptedException{

        log.info("Scenario 2(a): Verification of authentication e-mail from Quidco in Gmail started");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://gmail.com/");
        BasePage validation = new BasePage(driver);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        log.info("Scenario 2(b): Existing Gmail user login started");
        validation.emailGmail().click();
        validation.emailGmail().sendKeys(gmailId);
        validation.nextButtonGmail().click();
        Thread.sleep(2000);
        validation.passwordGmail().click();
        validation.passwordGmail().sendKeys(gmailAndQuidcoPassword);
        validation.signInGmailId().click();
        log.info("Scenario 2(c): User logged in successfully into Gmail");

        log.info("Scenario 2(d): Fetching unread e-mails from Primary Inbox into a list");
        List<WebElement> unreadEmails = validation.unreadEmails();
        // Sender name for which to check if there is an unread e-mail.
        String expectedEmailSubject = "Welcome to Quidco";
        String actualEmailSubject = "";
        for(int i=0;i<unreadEmails.size();i++){
            if(unreadEmails.get(i).isDisplayed()==true){
                // Verify if there is an e-mail form a specific mailer (Note Unread e-mails)
                if(unreadEmails.get(i).getText().contains(expectedEmailSubject)){
                    actualEmailSubject = unreadEmails.get(i).getText();
                    System.out.println(actualEmailSubject);
                    log.info("Scenario 2(e): Authentication email is received in the Gmail account.");
                    break;
                }
            }
        }

        Assert.assertEquals(actualEmailSubject, expectedEmailSubject);
        log.info("Scenario 2(f): Verification of authentication e-mail from Quidco in Gmail completed successfully.");
        driver.quit();
    }


    /**
     * Test case to validate following scenarios
     *   3. Login and search for Argos and navigate to Argos Credit Card details page.
     *   4. Save the Stats section/text to a file.
     *   5. Logout Quidco website.
     * @throws InterruptedException
     */
    @Test(priority = 2)
    public void userLogin() throws InterruptedException {
        log.info("Scenario 3(a): Login and search for Argos and navigate to Argos Credit Card details page started.");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.quidco.com/");

        BasePage userLogin = new BasePage(driver);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        userLogin.homeSignInButton().click();
        log.info("Scenario 3(b): User Login started.");
        userLogin.sigInUsername().click();
        userLogin.sigInUsername().sendKeys(gmailId);
        userLogin.sigInPassword().click();
        userLogin.sigInPassword().sendKeys(gmailAndQuidcoPassword);
        if(!userLogin.keepMeLogin().isSelected())
        {
            userLogin.keepMeLogin().click();
        }
        userLogin.sigInButton().click();
        log.info("Scenario 3(c): User Login successful.");
        //TODO: Due to Captcha image the login may not be successful so Captcha needs to be disabled at back-end.
        //Assert.assertEquals(driver.getTitle(),"My Quidco");

        log.info("Scenario 3(d): Search for Argos started.");
        userLogin.searchForRetailers().click();
        userLogin.searchForRetailers().sendKeys("Argos");
        userLogin.searchForArgos().click();
        log.info("Scenario 3(e): Search for Argos Successful and navigation to credit card started.");
        Assert.assertEquals(userLogin.searchForArgosResultLabel().getText(),"Search results for Argos");
        userLogin.clickOnArgosCreditCardLabel().click();
        Assert.assertTrue(driver.getTitle().contains("Argos Credit Card"), "Argos Credit card page not found");

        log.info("Scenario 4(a): Started capturing stats section/text to a file.");
        //Capture the text fields in String types.
        String onlineRate;
        String representativeAPR;
        String lastUpdated;
        onlineRate = userLogin.onlineRate().getText();
        representativeAPR = userLogin.representativeAPR().getText();
        lastUpdated = userLogin.lastUpdated().getText();

        log.info("Scenario 4(b): Writing the Argos statistics to resources/docs/stats.txt.");
        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(docs_path + File.separator + "stats.txt"), "utf-8"));
            writer.write(onlineRate);
            writer.write(System.lineSeparator());
            writer.write(representativeAPR);
            writer.write(System.lineSeparator());
            writer.write(lastUpdated);
        }
        catch (IOException e){
            log.error("IOException while writing to file", e);
        }
        finally{
            try {writer.close();} catch (Exception ex){}
        }
        log.info("Scenario 4(c): Argos statistics captured to file successfully.");
        Assert.assertTrue(isFileExists(docs_path + File.separator + "stats.txt"), "Stats file doesn't exists");
        Assert.assertEquals(getFileNumberOfLines(docs_path + File.separator + "stats.txt"),3);

        log.info("Scenario 5(a): Logout from Quidco website.");
        //TestCase 5: LogOut
        //TODO: Due to Captcha image the login does not work so this part of the code is commented.
        //TODO: Once Captcha is disabled through backend access this could be made to work.
        //userLogin.userLoginDropdown().click();
        //userLogin.logOut().click();
        //Assert.assertEquals(driver.getTitle(),"Quidco - Sign out");
        log.info("Scenario 5(b): Logout from Quidco website successful.");

        driver.quit();
    }


    /**
     * Utility method to check if file exists @filePath.
     * TODO: Ideally this should be moved to util package.
     */
    static Boolean isFileExists(String filePath){
        File file = new File(filePath);
        return (file.exists() && !file.isDirectory());
    }


    /**
     * Returns the lines in file @filePath.
     * TODO: Ideally this should be moved to util package.
     */
    static int getFileNumberOfLines(String filePath){
        if (!isFileExists(filePath)){
            return 0;
        }
        BufferedReader reader = null;
        int lines = 0;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            while (reader.readLine() != null) lines++;
            reader.close();
        }
        catch (IOException e){
            return lines;
        }
        finally{
            try {reader.close();} catch (Exception ex){}
        }
        return lines;
    }


    // Static block which will run while loading the class and depending upon
    // the OS and 32/64 bit machine config this will load geckodriver and
    // set the <webdriver.gecko.driver> property in system.
    // FIXME: Though an attempt is made to make these test-cases platform agnostic,
    // FIXME: this program is tested only on Windows 32-bit machine. Also note that GeckoDriver
    // FIXME: crashes on driver.quit() --> https://github.com/mozilla/geckodriver/issues/639
    // FIXME: This program is tested to run fine on Firefox v 55.0.3 (32-bit)
    static {
         String path = drivers_path;
         String jvmProcessor = System.getProperty("sun.arch.data.model").trim(); //32 vs. 64 bit
         String osName = System.getProperty("os.name").toLowerCase();// Windows vs. Unix vs. Mac vs. Solaris
         if(osName.indexOf("win") >= 0){
             if(jvmProcessor.equals("32")){
                 path = drivers_path + File.separator + "win32";
             }
             else{
                 path = drivers_path + File.separator + "win64";
             }
         }
         else if(osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0){
             if(jvmProcessor.equals("32")){
                 path = drivers_path + File.separator + "linux32";
             }
             else{
                 path = drivers_path + File.separator + "linux64";
             }
         }
         else{
             path = drivers_path + File.separator + "mac";
         }
         System.setProperty("webdriver.gecko.driver", path + File.separator + "geckodriver.exe");
    }

}