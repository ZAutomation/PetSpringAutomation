package webpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import testbase.TestBase;

public class CreateNewPet extends TestBase{

	@FindBy(id = "name")
	WebElement petName;

	@FindBy(id = "birthDate")
	WebElement petBirthDay;
	
	@FindBy(id = "type")
	WebElement typeOfBird;
	
	@FindBy(xpath="//*[contains(text(),'Owner')]//following-sibling::div")
	WebElement ownerName;
	
	@FindBy(xpath="//button[contains(text(),'Add Pet')]")
	WebElement addPet;
	
	public CreateNewPet() {
		PageFactory.initElements(driver, this);
	}
	
	public OwnerInfoPage createEntryforPet(String name, String birthDate,String type) {
		petName.sendKeys(name);
		petBirthDay.sendKeys(birthDate);
		new Select(typeOfBird).selectByValue(type);
		addPet.click();
		return new OwnerInfoPage();
		
	}
	
}
