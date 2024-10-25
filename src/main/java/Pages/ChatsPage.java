package Pages;

import org.openqa.selenium.By;

public class ChatsPage extends BasePage{
    private By ChatsPageTitle = By.xpath("(//android.widget.TextView[@text='Chats'])[1]");
    private By searchBtn = By.xpath("//android.view.View[@content-desc='Search']");
    private By invitationMessage = By.id("00000000-0000-01d1-0000-02a400000004");

    private By NavigationBarChatsBtn = By.xpath("//android.view.View[@content-desc='Chats']");
    private By NavigationBarCallsBtn = By.xpath("//android.view.View[@content-desc='Calls']");
    private By NavigationBarContactsBtn = By.xpath("//android.view.View[@content-desc='Contacts']");
    private By NavigationBarSettingBtn = By.xpath("//android.view.View[@content-desc='Settings']");

    public String getInvitationMessage(){
        return find(invitationMessage).getText();
    }

    public ChatsPage goToChatsPage(){
        click(NavigationBarChatsBtn);
        return new ChatsPage();
    }

    public CallsPage goToCallsPage(){
        click(NavigationBarCallsBtn);
        return new CallsPage();
    }

    public ContactsPage goToContactsPage(){
        click(NavigationBarContactsBtn);
        return new ContactsPage();
    }

    public SettingPage goToSettingPage(){
        click(NavigationBarSettingBtn);
        return new SettingPage();
    }



}
