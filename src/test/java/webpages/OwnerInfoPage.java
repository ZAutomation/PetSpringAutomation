package webpages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;
import utilities.Pets;
import utilities.TestUtil;

public class OwnerInfoPage extends TestBase {

	String reportName= "PetInfo";
	@FindBy(xpath="//tbody//th[contains(text(),'Name')]//following-sibling::td")
	WebElement ownerFullName;
	
	@FindBy(xpath="//tbody//th[contains(text(),'Address')]//following-sibling::td")
	WebElement address;
	
	@FindBy(xpath="//tbody//th[contains(text(),'City')]//following-sibling::td")
	WebElement city;
	
	@FindBy(xpath="//tbody//th[contains(text(),'Telephone')]//following-sibling::td")
	WebElement telephone;

	@FindBy(xpath="//a[contains(text(),'Add') and contains(text(),'New Pet')]")
	WebElement addNewPet;
	
	@FindBy(xpath="//a[contains(text(),'Edit') and contains(text(),'Owner')]")
	WebElement editOwner;
	
	
	/*Multiple pet can be added to same owner*/
	@FindBy(xpath="//dt[contains(text(),'Name')]//following-sibling::dd[1]")
	List<WebElement> petNames;
	
	@FindBy(xpath="//dt[contains(text(),'Birth')]//following-sibling::dd[1]")
	List<WebElement> petBirthDate;
	
	@FindBy(xpath="//dt[contains(text(),'Type')]//following-sibling::dd[1]")
	List<WebElement> typePet;
	
	
	

public OwnerInfoPage() {
	PageFactory.initElements(driver, this);
}

public String getOwnerFullName() {
	 return ownerFullName.getText();
}

public String getOwneraddress() {
	return address.getText();
}

public String getOwnercity() {
	return city.getText();
}

public String getOwnerTelephone() {
	return telephone.getText();
}
public CreateNewPet navigateToaddPet() {
	addNewPet.click();
	return new CreateNewPet();
}

public List<Map<String,String>> getPetInfo() {
	HashMap<String,String> petInfo = new HashMap<>();
	List<Map<String,String>> listPets= new LinkedList<>();
	if(petNames.size()==0) {
		petInfo.put("petInfo", "empty");
		listPets.add(petInfo);
		return listPets;
	}
	
	for(int i=0; i<petNames.size();i++ ) {
		petInfo = new HashMap<>();
		petInfo.put("petName", petNames.get(i).getText());
		petInfo.put("petBirthDate", petBirthDate.get(i).getText());
		petInfo.put("typePet", typePet.get(i).getText());
		listPets.add(petInfo);
	}
	System.out.println(listPets);
	return  listPets;	
}

public boolean createReportforPets() {
	List<Map<String, String>> petInfo = getPetInfo();
	if(petInfo.get(0).containsKey("petInfo")) {
		//After adding if pet not present
		return false;
	}
	List<Pets> petLists = new LinkedList<>();
	for(Map<String, String> petMap : petInfo) {
	Pets pet = new Pets(petMap.get("petName"),getOwnerFullName(),petMap.get("typePet"),petMap.get("petBirthDate"),"");
	pet.printInfo();
	petLists.add(pet);
}
	try {
		TestUtil.createwriteExcel(petLists, reportName);
	} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
			| InvocationTargetException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	File f= new File(TestUtil.resourceFolder+"/dataSheets/" + reportName +".xlsx");
	if(f.exists()) {
		return true;
	}
	else {
		return false;
	}
}
}