package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.*;

public class SelectStatePage extends BasePage{
    private By searchBtn = By.xpath("//android.view.View[@content-desc='Search']");
    private By searchText = By.xpath("//android.widget.EditText");
    private By searchStateResultList = By.xpath("//android.widget.TextView");

    public void searchState(String stateName) throws InterruptedException {
        click(searchBtn);
        set(searchText, stateName);
        Thread.sleep(1000);
    }

    public List<WebElement> getSearchedStateList(){
        List<WebElement> webElementList = new ArrayList<>();
        for(WebElement state : findAll(searchStateResultList)){
            if(!state.getText().startsWith("+") && !state.getText().contains("Select State")){
                webElementList.add(state);
            }
        }
        return webElementList;
    }

    public String getFirstStateName(){
//        System.out.println("getFirstStateName: "+ getSearchedStateList().get(0).getText());
        return getSearchedStateList().get(0).getText();
    }

    public String getFirstStateCode(){
        List<WebElement> webElementList = new ArrayList<>();
        for(WebElement state : findAll(searchStateResultList)){
            if(state.getText().startsWith("+")){
                webElementList.add(state);
            }
        }
        return webElementList.get(0).getText();
    }

    public PhoneNumberPage clickFirstStateResult(){
        getSearchedStateList().get(0).click();
        return new PhoneNumberPage();
    }

    public Set<String> scrollDownToStateTextAndGetStateList(String text) throws InterruptedException {
        Set<String> stateList = new HashSet<>();
        List<WebElement> states = new ArrayList<>();
        while(driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'"+ text +"')]")).size() == 0){
            final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            var start = new Point(658, 2550);
            var end = new Point (705, 780);
            var swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipe));
            Thread.sleep(500);

            states = getSearchedStateList();
            for(WebElement state : states){
                String statesName = state.getText();
                if(!statesName.startsWith("+") && !statesName.contains("Select State")){
                    stateList.add(statesName.substring(6));
                }
            }
        }
        for (Object stateName : stateList.toArray()) {
            System.out.println(stateName.toString());

        }

        return stateList;
    }

    public void scrollDownAndSelectStateText(String text) throws InterruptedException {
        while(driver.findElements(By.xpath("//*[contains(@text,'"+ text +"')]")).size() == 0){
            final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            var start = new Point(658, 2550);
            var end = new Point (705, 780);
            var swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipe));
            Thread.sleep(500);
        }
        click(By.xpath("//*[contains(@text,'"+ text +"')]"));
    }
}
