package main.java.com.quidco.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {

    WebDriver driver;

    // Constructor placed in order to make sure driver remains for this java class
    public BasePage(WebDriver driver){
        this.driver=driver;
    }

    // Locators for Quidco Sign-up journey.
    By firstName = By.id("first_name_join");
    By surname = By.id("last_name_join");
    By email = By.id("new_email_join");
    By password = By.id("new_password_join");
    By checkBoxHaveRead = By.id("agree_to_terms_label"); //By.xpath(".//*[@id='agree_to_terms_label']/span");
    By joinButton = By.id("join-quidco-submit-button");
    By welcomeLabel = By.xpath(".//*[@class='super ng-binding']");
    By letsGetStartedButton = By.xpath(".//*[@id='onboarding']/div[2]/div/div/div/div[3]/div/a");
    By accountTypeButton1 = By.xpath(".//*[@id='onboarding']/div[2]/div/div/div/div/div[3]/div/a");
    By selectCheckbox = By.xpath(".//*[@id='basic-vs-premium']/div[1]/div[2]/div/div/div[3]/div[1]/svg/use");
    By yourDetailsButton2 =By.xpath(".//*[@id='onboarding']/div[2]/div/div/div/div/div[2]/div/a[1]");
    By yourRenewalsButton3 =By.xpath(".//*[@id='onboarding']/div[2]/div/div/div/div/div[3]/div/a[1]");
    By skipSteps = By.xpath(".//*[@id='onboarding']/div[2]/div/div/div/div/div[2]/div/a[2]");
    By playNowAdd = By.xpath("html/body/div[5]/div/div[4]/button");
    By playNow_CnacelButton = By.xpath("html/body/div[2]/button");
    By retailersButton4 = By.xpath(".//*[@id='onboarding']/div[2]/div/div/div/div/div[3]/div/a");
    // By quidcoLabel = By.xpath(".//*[@id='main-nav']/div/div/div/div/div[2]/a/svg/use");
    By quidcoLabel = By.xpath(".//*[@id='main-nav']/div/div/div/div/div[2]/a/svg");

    // Locators for Quidco Sign-in and search for Argos & navigate to Argos Credit card details page.
    By homeSignInButton = By.xpath("//div[3]/a[2]");
    By sigInUsername = By.id("username");
    By sigInPassword = By.id("password");
    By keepMeLogin = By.xpath(".//*[@id='sign-in-page-form']/div/div[1]/div[3]/div[1]/label/span");
    By sigInButton = By.xpath(".//*[@id='sign-in-page-form']/div/div[1]/div[3]/input");
    By searchForRetailers = By.xpath(".//*[@id='store-q1search']");
    By searchForArgos = By.xpath(".//*[@id='store-qsearch-submit']");
    By searchForArgosResultLabel = By.id("search-results-multiple-retailers-title");
    By clickOnArgosLabel = By.xpath(".//*[@id='search-results-top-retailer-container']/ul/li/div/div/a/p");
    By clickOnArgosCreditCardLabel = By.xpath(".//*[@id='search-results-related-retailers-container']/ul/li/div/div/div/div/ul/li[2]/div/div/a/p");

    // Locators for Argos Stats to be stored in txt file.
    By representativeAPR = By.xpath(".//*[@id='retailer-template']/div[1]/div[1]/div[4]/h3");
    By onlineRate =  By.xpath(".//*[@id='bs-tour-step-0']");
    By lastUpdated = By.xpath(".//*[@id='retailer-template']/div[1]/div[1]/div[5]/div[1]/p");

    // Locators for Logout from Quidco.
    By userLoginDropdown = By.xpath(".//*[@id='main-nav']/div[2]/div/div/div/ul/div/ul/li[2]/a/div/svg");
    By logOut = By.xpath(".//*[@id='cas-sign-out']"); //.//*[@id='cas-sign-out']

    // Locators for signing-up in Gmail.
    By emailGmail =By.id("identifierId");
    By nextButtonGmail = By.xpath(".//*[@id='identifierNext']/div[2]");
    By clasname = By.className("CwaK9");
    By passwordGmail = By.name("password");
    By signInGmailId = By.xpath(".//*[@id='passwordNext']/content/span");
    //By unreadEmails = By.xpath("//*[@class='zF']");
    By unreadEmails = By.cssSelector("div.xT>div.y6>span>b");

    // Methods corresponding to above Locators to fetch the WebElements
    // from Webdriver instance passed to the constructor of this class.

    public WebElement firstName(){ return driver.findElement(firstName);}
    public WebElement surname(){ return driver.findElement(surname);}
    public WebElement email(){ return driver.findElement(email);}
    public WebElement password(){ return driver.findElement(password);}
    public WebElement checkBoxHaveRead(){ return driver.findElement(checkBoxHaveRead);}
    public WebElement joinButton(){ return driver.findElement(joinButton);}
    public WebElement welcomeLabel(){ return driver.findElement(welcomeLabel);}
    public WebElement letsGetStartedButton(){ return driver.findElement(letsGetStartedButton);}
    public WebElement accountTypeButton1(){ return driver.findElement(accountTypeButton1);}
    public WebElement selectCheckbox(){ return driver.findElement(selectCheckbox);}
    public WebElement skipSteps(){ return driver.findElement(skipSteps);}
    public WebElement playNowAdd(){ return driver.findElement(playNowAdd);}
    public WebElement playNow_CancelButton(){ return driver.findElement(playNow_CnacelButton);}
    public WebElement yourDetailsButton2(){ return driver.findElement(yourDetailsButton2);}
    public WebElement yourRenewalsButton3(){ return driver.findElement(yourRenewalsButton3);}
    public WebElement retailersButton4(){ return driver.findElement(retailersButton4);}
    public WebElement quidcoLabel(){ return driver.findElement(quidcoLabel);}

    public WebElement homeSignInButton(){ return driver.findElement(homeSignInButton);}
    public WebElement sigInUsername(){ return driver.findElement(sigInUsername);}
    public WebElement sigInPassword(){ return driver.findElement(sigInPassword);}
    public WebElement keepMeLogin(){ return driver.findElement(keepMeLogin);}
    public WebElement sigInButton(){ return driver.findElement(sigInButton);}
    public WebElement searchForRetailers(){ return driver.findElement(searchForRetailers);}
    public WebElement searchForArgos(){ return driver.findElement(searchForArgos);}
    public WebElement searchForArgosResultLabel(){ return driver.findElement(searchForArgosResultLabel);}
    public WebElement clickOnArgosLabel(){ return driver.findElement(clickOnArgosLabel);}
    public WebElement clickOnArgosCreditCardLabel(){ return driver.findElement(clickOnArgosCreditCardLabel);}

    public WebElement representativeAPR(){ return driver.findElement(representativeAPR);}
    public WebElement onlineRate(){ return driver.findElement(onlineRate);}
    public WebElement lastUpdated(){ return driver.findElement(lastUpdated);}

    public WebElement userLoginDropdown(){ return driver.findElement(userLoginDropdown);}
    public WebElement logOut(){ return driver.findElement(logOut);}

    public WebElement emailGmail(){ return driver.findElement(emailGmail);}
    public WebElement nextButtonGmail(){ return driver.findElement(nextButtonGmail);}
    public WebElement clasname(){ return driver.findElement(clasname);}
    public WebElement passwordGmail(){ return driver.findElement(passwordGmail);}
    public WebElement signInGmailId(){ return driver.findElement(signInGmailId);}
    public List<WebElement> unreadEmails(){ return driver.findElements(unreadEmails);}

    }
