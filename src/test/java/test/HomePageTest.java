package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilities.TestUtil;
import webpages.HomePage;
import webpages.VetsPage;

public class HomePageTest extends TestBase {
	HomePage homePage;

	public HomePageTest() {
		super();
	}

	@BeforeClass
	public void clearUp() {
		TestUtil.deleteFile(VetsPage.reportPath);
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();

	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() throws IOException {
		boolean imageStatus = homePage.verifyImageisDisplayed();
		homePage.downloadPetImage();
		Assert.assertTrue(imageStatus,
				"Image is not present , you can try to check downloaded image(pet-image) in shotsAndImages");
	}

	@AfterMethod
	public void cleanUp() {
		exitDriver();
	}

}
