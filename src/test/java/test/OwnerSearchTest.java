package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilities.TestUtil;
import webpages.FindOwnerPage;
import webpages.FindOwnerSearchPage;
import webpages.HomePage;

public class OwnerSearchTest extends TestBase {
	HomePage homePage;
	FindOwnerPage findownerHomepage;
	FindOwnerSearchPage findOwnerSearchPage;

	public OwnerSearchTest() {
		super();
	}

	@BeforeClass
	public void clearUp() {
		TestUtil.deleteFile(FindOwnerSearchPage.reportPath);
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	/* Report all owners and create a Excel Sheet */
	@Test(priority = 1)
	public void verifyOwnerReport() {
		findownerHomepage = homePage.goTofindOwnerPage();
		// It gives all the owner when search with empty string.
		findOwnerSearchPage = findownerHomepage.searchOwnerlastName("");
		Assert.assertTrue(findOwnerSearchPage.verifyOwnerTitles(), "Owner Titles are not correct");
		Assert.assertTrue(findOwnerSearchPage.reportAllOwner(), "Report not created for Owners in dataSheets");
	}

	@AfterMethod
	public void cleanUp() {
		exitDriver();
	}
}
