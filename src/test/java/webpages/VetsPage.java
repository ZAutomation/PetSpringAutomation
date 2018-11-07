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
import utilities.TestUtil;
import utilities.Vets;

public class VetsPage extends TestBase {
	int colCount=2;
	static String reportName="VetsReport";
	public static String reportPath=TestUtil.resourceFolder+"/dataSheets/" + reportName +".xlsx";
	@FindBy(xpath="//*[@id='vets']//tbody//tr//td")
	List<WebElement> allVetsTable;
	
	public VetsPage() {
		PageFactory.initElements(driver, this);
	}

	public List<Vets> getAllVetsData() {
		List<Vets> vetsList = new LinkedList<>();
		String val = "";
		List<String> values = new LinkedList<>();
		for (int i = 0; i < allVetsTable.size(); i++) {
			val = allVetsTable.get(i).getText();
			values.add(val);
			if (values.size() % colCount == 0) {
				Vets vets = new Vets(values.get(0), values.get(1));
				vets.printInfo();
				vetsList.add(vets);
				values.clear();
			}
			

		}
		
		return vetsList;

	}
	
	public boolean reportAllVets()  {
		try {
			TestUtil.createwriteExcel(getAllVetsData(),reportName);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f= new File(reportPath);
		
		if(f.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
}
