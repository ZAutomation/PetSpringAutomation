package webpages;

import java.math.BigDecimal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class CreateOwnerPage extends TestBase {
	
	@FindBy(id = "firstName")
	WebElement firstName;

	@FindBy(id = "lastName")
	WebElement lastName;
	
	@FindBy(id = "address")
	WebElement address;
	
	@FindBy(id = "city")
	WebElement city;
	
	@FindBy(id = "telephone")
	WebElement telephone;
	
	@FindBy(xpath="//*[@id='add-owner-form']//button[@type='submit']")
	WebElement addOwner;
	public CreateOwnerPage() {
		PageFactory.initElements(driver, this);
	}
	
	public OwnerInfoPage CreateOwner(String fname,String lname,String addr,String cityName ,String telNum) {
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		address.sendKeys(addr);
		city.sendKeys(cityName);
		telephone.sendKeys(new BigDecimal(telNum).toPlainString());
		addOwner.click();
		return new OwnerInfoPage();
		
	}
		
}
