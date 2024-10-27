package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private By startMessagingBtn = By.className("android.widget.Button");

    public PhoneNumberPage goToPhoneNumberPage(){
        click(startMessagingBtn);
        return new PhoneNumberPage();
    }

    public boolean isStartMessageBtnDisplayed(){
        WebElement startMessaging = find(startMessagingBtn);
        return startMessaging.isDisplayed();
    }

}
