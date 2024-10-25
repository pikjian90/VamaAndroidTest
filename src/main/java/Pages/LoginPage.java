package Pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    private By startMessagingBtn = By.className("android.widget.Button");

    public PhoneNumberPage goToPhoneNumberPage(){
        click(startMessagingBtn);
        return new PhoneNumberPage();
    }

}
