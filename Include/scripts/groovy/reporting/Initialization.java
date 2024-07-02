package reporting;

import java.io.IOException;
import java.time.LocalDateTime;

import com.kms.katalon.core.configuration.RunConfiguration;
import com.kms.katalon.core.testdata.TestData;
import com.kms.katalon.core.testdata.TestDataFactory;

//import internal.GlobalVariable;
import reporting.Screenshot;
//import dto.Mbank;

public class Initialization {
	private int dataNumber;
//	private Mbank mbank;
	private LocalDateTime startDate;
	private Screenshot screenshot;
	private String authType;
	private String businessUnit;
	private String username;
	private String password;
	private String story;
	private String tcFileName;
	private String tcId;
	private String tempFolder;
	private TestData testData;
	
	public Initialization() {}
	
	public void initialize(String tcFileName) throws IOException {

		this.startDate			= LocalDateTime.now();
		this.tcFileName			= tcFileName.trim();
		String[] fileNameSplit	= this.tcFileName.split(" ");
		this.testData			= TestDataFactory.findTestData("/Data Files/" + fileNameSplit[fileNameSplit.length - 1]);
		this.dataNumber			= getRowNumberOnTestData();
		this.tempFolder			= RunConfiguration.getProjectDir() + "/Data Files/Image/Temp";
		this.screenshot			= new Screenshot(this.tempFolder);
//		this.mbank				= new Mbank();
		this.businessUnit		= this.testData.getValue("BUSINESS_UNIT", this.dataNumber).trim();
		this.story				= this.testData.getValue("STORY", this.dataNumber).trim();
		this.tcId				= this.testData.getValue("TC_ID", this.dataNumber).trim();
		this.username			= this.testData.getValue("USERNAME", this.dataNumber).trim();
		this.password			= this.testData.getValue("PASSWORD", this.dataNumber).trim();
		this.authType			= this.testData.getValue("AUTHENTICATION_TYPE", this.dataNumber).trim();
	}
	
	public int getRowNumberOnTestData() {
		int index		= 0;
		int rowNumber	= 0;
		try {
			rowNumber	= this.testData.getRowNumbers();
			for (int i = 1; i <= rowNumber; i++) {
				String tcExcel = this.testData.getValue("TC_ID", i);
				
				String[] stringArray = this.tcFileName.split(" ");
				if (stringArray[stringArray.length - 1].equals(tcExcel)) {
					index = i;
					break;
				} else if (i == testData.getRowNumbers()) {
					System.out.println("\nTest Case not found, please check your Test Case name and Excel Data. Make sure it is contained in Excel Data.\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}
	
	public int getDataNumber() {
		return this.dataNumber;
	}
	
	public String getStory() {
		return this.story;
	}
		
	public String getTcId() {
		return this.tcId;
	}
	
	public LocalDateTime getStartDate() {
		return this.startDate;
	}
	
	public String getTempFolder() {
		return this.tempFolder;
	}
	
	public Screenshot getScreenshot() {
		return this.screenshot;
	}
	
	public TestData getTestData() {
		return this.testData;
	}
	
//	public Mbank getMbank() {
//		return this.mbank;
//	}
	
	public String getAuthenticationType() {
		return this.authType;
	}
	
	public String getBusinessUnit() {
		return this.businessUnit;
	}
		
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}