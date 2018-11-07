package webpages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;
import utilities.Owner;
import utilities.TestUtil;

public class FindOwnerSearchPage extends TestBase {
	public static String reportName = "OwnerReport";
	public static String reportPath=TestUtil.resourceFolder + "/dataSheets/" + reportName + ".xlsx";
	int colCount = 5;
	@FindBy(xpath = "//*[@id='vets']//*[contains(text(),'Name')]")
	WebElement nameHeader;

	@FindBy(xpath = "//*[@id='vets']//*[contains(text(),'Name')]//following-sibling::th[1]")
	WebElement addressHeader;

	@FindBy(xpath = "//*[@id='vets']//*[contains(text(),'Name')]//following-sibling::th[2]")
	WebElement cityHeader;

	@FindBy(xpath = "//*[@id='vets']//*[contains(text(),'Name')]//following-sibling::th[3]")
	WebElement telephoneHeader;

	@FindBy(xpath = "//*[@id='vets']//*[contains(text(),'Name')]//following-sibling::th[4]")
	WebElement petsHeader;

	@FindBy(xpath = "//*[@id='vets']//tbody//child::tr")
	List<WebElement> ownerTable;

	@FindBy(xpath = "//*[@id='vets']//tbody//tr//td")
	List<WebElement> alltableElements;

	public FindOwnerSearchPage() {
		PageFactory.initElements(driver, this);
	}

	public List<Owner> getAllOwnersdata() {
		List<Owner> ownerList = new LinkedList<>();
		String val = "";
		
		List<String> values = new LinkedList<>();
		for (int i = 0; i < alltableElements.size(); i++) {
			executeJavaScript("arguments[0].scrollIntoView(true);",alltableElements.get(i));
			val = getWebElementText(alltableElements.get(i));
			System.out.println(val);
			values.add(val);
			if (values.size() % colCount == 0) {
				Owner owner = new Owner(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
				owner.printInfo();
				ownerList.add(owner);
				values.clear();
			}

		}

		return ownerList;

	}

	public boolean verifyOwnerTitles() {
		boolean nameCheck = getWebElementText(nameHeader).equals("Name");
		boolean addresCheck = getWebElementText(addressHeader).equals("Address");
		boolean citycheck = getWebElementText(cityHeader).equals("City");
		boolean telephoneCheck = getWebElementText(telephoneHeader).equals("Telephone");
		boolean petCheck = getWebElementText(petsHeader).equals("Pets");
		if (nameCheck && addresCheck && citycheck && telephoneCheck && petCheck) {
			return true;
		}
		return false;

	}

	public boolean reportAllOwner() {
		try {
			TestUtil.createwriteExcel(getAllOwnersdata(), reportName);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(reportPath);
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}

}
