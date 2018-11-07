package webpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class FindOwnerPage extends TestBase	{

	@FindBy(id = "lastName")
	WebElement ownerlastName;	
	
	@FindBy(xpath="//button[contains(text(),'Find') and contains(text(),'Owner')]")
	WebElement findOwner;
	
	@FindBy(xpath="//a[contains(text(),'Add') and contains(text(),'Owner')]")
	WebElement addOwner;
	
	public FindOwnerPage() {
		PageFactory.initElements(driver, this);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T searchOwnerlastName(String lastName) {
		ownerlastName.sendKeys(lastName);
		findOwner.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(getCurrenturl().contains("last"))
		return (T) new FindOwnerSearchPage();
		else
			return (T) new OwnerInfoPage();
	}
	public CreateOwnerPage addOwner(){
		addOwner.click();
		
		return new CreateOwnerPage();
	}
	
}
