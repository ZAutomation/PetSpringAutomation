package test;

import static org.testng.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilities.Owner;
import utilities.TestUtil;
import webpages.CreateNewPet;
import webpages.CreateOwnerPage;
import webpages.FindOwnerPage;
import webpages.FindOwnerSearchPage;
import webpages.HomePage;
import webpages.OwnerInfoPage;


public class CreateOwnerPageTest extends TestBase {
	HomePage homePage;
	FindOwnerPage findownerHomepage;
	FindOwnerSearchPage findOwnerSearchPage;
	CreateOwnerPage createOwnerPage;
	OwnerInfoPage ownerInfoPage;
	CreateNewPet createNewPet;
	OwnerInfoPage ownerInfopage;
	Object o;

	public CreateOwnerPageTest() {
		super();
	}
	

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	@DataProvider(name = "OwnerPetData")
	public static Object[][] getAllPetData() {
		Object[][] testData = TestUtil.getTestData("OwnerPetInfo");
		return testData;

	}

	/* Creating owner and Pets and verifying all*/
	@Test(priority = 1, dataProvider = "OwnerPetData")
	public void createAndVerifyOwner(String fName, String lName, String address, String city, String phone,
			String completePetInfo) {
		findownerHomepage = homePage.goTofindOwnerPage();
		createOwnerPage = findownerHomepage.addOwner();
		ownerInfoPage = createOwnerPage.CreateOwner(fName, lName, address, city, phone);
		createNewPet = ownerInfoPage.navigateToaddPet();
		createAllPet(completePetInfo);
		homePage.goTofindOwnerPage();
		o = findownerHomepage.searchOwnerlastName(lName);
		verifyOwnerandPet(fName, lName, address, city, phone, completePetInfo);

	}

	@AfterMethod
	public void cleanUp() {
		exitDriver();
	}

	/* Common method and verification related to Creating Owner and Pet */

	/**
	 * @param fName
	 * @param lName
	 * @param address
	 * @param city
	 * @param phone
	 * @param completePetInfo
	 */
	private void verifyOwnerandPet(String fName, String lName, String address, String city, String phone,
			String completePetInfo) {
		if (o instanceof FindOwnerSearchPage) {
			findOwnerSearchPage = (FindOwnerSearchPage) o;
			List<Owner> allOwnersdata = findOwnerSearchPage.getAllOwnersdata();
			verifyOwnerCreation(fName, lName, address, city, phone, allOwnersdata);
		} else if (o instanceof OwnerInfoPage) {
			ownerInfopage = (OwnerInfoPage) o;
			verifySingleOwner(fName, lName, address, city, phone, completePetInfo);
		}
	}

	/**
	 * @param fName
	 * @param lName
	 * @param address
	 * @param city
	 * @param phone
	 * @param completePetInfo
	 */
	private void verifySingleOwner(String fName, String lName, String address, String city, String phone,
			String completePetInfo) {

		Assert.assertEquals(ownerInfopage.getOwnerFullName(), fName + " " + lName, "Name is not matching");
		Assert.assertEquals(ownerInfopage.getOwneraddress(), address, "Address is not matching");
		Assert.assertEquals(ownerInfopage.getOwnercity(), city, "City is not matching");
		Assert.assertEquals(ownerInfopage.getOwnerTelephone(), new BigDecimal(phone).toPlainString(),
				"TelNum is not matching");
		List<Map<String, String>> petInfos = ownerInfopage.getPetInfo();
		List<Map<String, String>> petData = getPetData(completePetInfo);
		Assert.assertEquals(petInfos, petData, "All pet Information is matched");
	}

	/**
	 * @param fName
	 * @param lName
	 * @param address
	 * @param city
	 * @param phone
	 * @param allOwnersdata
	 */
	private void verifyOwnerCreation(String fName, String lName, String address, String city, String phone,
			List<Owner> allOwnersdata) {
		int i = 0;
		for (; i < allOwnersdata.size(); i++) {
			if (allOwnersdata.get(i).getName().toString().split(" ")[1].equals(lName)) {
				Assert.assertEquals(allOwnersdata.get(i).getName(), fName + " " + lName, "Name is not matching");
				Assert.assertEquals(allOwnersdata.get(i).getAddress(), address, "Address is not matching");
				Assert.assertEquals(allOwnersdata.get(i).getCity(), city, "City is not matching");
				Assert.assertEquals(allOwnersdata.get(i).getTelephone(), new BigDecimal(phone).toPlainString(),
						"TelNum is not matching");
			}
		}
		assertNotEquals(i, 0, "No entry found after adding owner");
	}

	/**
	 * @param completePetInfo
	 */
	private void createAllPet(String completePetInfo) {
		List<Map<String, String>> petInfotoAdd = getPetData(completePetInfo);
		for (Map<String, String> petInfotoAdd1 : petInfotoAdd) {
			createNewPet.createEntryforPet(petInfotoAdd1.get("petName"), petInfotoAdd1.get("petBirthDate"),
					petInfotoAdd1.get("typePet"));
			ownerInfoPage.navigateToaddPet();
		}
	}

	/**
	 * @param completePetInfo
	 * @return
	 */
	private List<Map<String, String>> getPetData(String completePetInfo) {
		List<Map<String, String>> petInfotoAdd = TestUtil.getPetInfotoAdd(completePetInfo);
		return petInfotoAdd;
	}

}
