package webpages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;
import utilities.TestUtil;

public class HomePage extends TestBase implements HeaderPet {
	public static String petImagepath=TestUtil.resourceFolder+"/shotsAndImages/" +"pet-image.png";
	//Considering welcome will be displayed on home page
	@FindBy(xpath = "//*[contains(text(),'welcome') or contains(text(),'Welcome') ]//../..//img[@class='img-responsive']")
	WebElement imageHomePage;
	
	@FindBy(xpath=veterinarians)
	WebElement Veterinarians;
	
	@FindBy(xpath = ownerHeader)
	WebElement findOwners;
	
	
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyImageisDisplayed(){
		return imageHomePage.isDisplayed();
	}
	public void downloadPetImage() throws IOException {
		String logoSRC = imageHomePage.getAttribute("src");
	     URL imageURL = new URL(logoSRC);
	     BufferedImage saveImage = ImageIO.read(imageURL);    
	     ImageIO.write(saveImage, "png", new File(petImagepath));
	}

	public FindOwnerPage goTofindOwnerPage(){
		findOwners.click();
		return new FindOwnerPage();
	}
	public VetsPage goToVetsPage(){
		Veterinarians.click();
		return new VetsPage();
	}

	
	
	

}