package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SmsAuthenticationPage extends BasePage{
    private By smsEditText = By.xpath("//android.widget.EditText");
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


    public ChatsPage setSmsEditText(String smsValue) throws InterruptedException {
        //set(smsEditText, smsValue);

        //Manually Input SMS authentication from other phone
        Thread.sleep(30000);
//        wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//android.widget.TextView[@text='Chats'])[1]")));
        return new ChatsPage();
    }
}
