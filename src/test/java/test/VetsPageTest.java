package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilities.TestUtil;
import webpages.HomePage;
import webpages.VetsPage;

public class VetsPageTest extends TestBase {

	HomePage homePage;
	VetsPage vetsPage;

	public VetsPageTest() {
		super();
	}

	@BeforeClass
	public void clearUp() {
		TestUtil.deleteFile(HomePage.petImagepath);
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	/* Report all vets and create a Excel Sheet */
	@Test(priority = 1)
	public void verifyVetsReport() {
		vetsPage = homePage.goToVetsPage();
		Assert.assertTrue(vetsPage.reportAllVets(), "Vets Report not generated");
		// Complete VetsData is saved as excel sheet, you can view all in console as
		// well -DataSheets folder

	}

	@AfterMethod
	public void cleanUp() {
		exitDriver();
	}
}
