package Pages;

import org.openqa.selenium.By;

public class SettingPage extends BasePage{
    private By SettingsPageTitle = By.xpath(("//android.widget.TextView[@text='Settings'])[1]"));
    private By shortFormName = By.xpath(("//android.widget.TextView[1]"));
    private By fullName = By.xpath(("//android.widget.TextView[2]"));
    private By alias = By.xpath(("//android.widget.TextView[3]"));
    private By logoutButton = By.xpath("//android.view.View[@content-desc='Log Out']");
    private By confirmLogoutButton = By.xpath("//android.widget.TextView[@text='Yes, log me out']");
    private By cancelLogoutButton = By.xpath("//android.widget.TextView[@text='Cancel']");

    public String getSettingPageTitle(){
        return find(SettingsPageTitle).getText();
    }

    public String getShortFormName(){
        return find(shortFormName).getText();
    }

    public String getFullName(){
        return find(fullName).getText();
    }

    public String getAlias(){
        return find(alias).getText();
    }

    public LoginPage logout(){
        click(logoutButton);
        click(confirmLogoutButton);
        return new LoginPage();
    }




}
