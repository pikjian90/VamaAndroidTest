package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhoneNumberPage extends BasePage{
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    private By selectCountry = By.xpath("//android.widget.EditText[1]");
    private By phoneCode = By.xpath("//android.widget.EditText[2]");
    private By phoneNumber = By.xpath("//android.widget.EditText[3]");
    private By submitBtn = By.xpath("//android.widget.Button");
    private By invalidCountryCode = By.xpath("//android.widget.TextView[contains(@text,'Invalid country code')]");
    private By validationTextViewMessage = By.xpath("//android.widget.TextView[contains(@text,'invalid') " +
            "or contains(@text,'API') " +
            "or contains(@text,'Please provide valid Country Code')" +
            "or contains(@text,'This is not a valid phone number')]");
    public String getCountryName(){
        return find(selectCountry).getText();
    }

    public String getPhoneCode(){
        return find(phoneCode).getText();
    }
    public String getPhoneNumber(){
        return find(phoneNumber).getText();
    }

    public String getInvalidCountryCode(){
        return find(invalidCountryCode).getText();
    }

    public String getValidationTextViewMessage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(validationTextViewMessage));
        return find(validationTextViewMessage).getText();
    }

//    public String getTooManyRequestValidationMessage(){
//        wait.until(ExpectedConditions.presenceOfElementLocated(tooManyRequestValidationMessage));
//        return find(tooManyRequestValidationMessage).getText();
//    }

    public void setPhoneCode(String phoneCodeValue){
        set(phoneCode, phoneCodeValue );
    }

    public void setPhoneNumber(String phoneCodeValue){
        set(phoneNumber, phoneCodeValue );
    }

    public SmsAuthenticationPage clickSubmitButton(){
        click(submitBtn);
        return new SmsAuthenticationPage();
    }

    public SelectStatePage gotoSelectStatePage() throws InterruptedException {
        click(selectCountry);
        Thread.sleep(1000);
        return new SelectStatePage();
    }

}
