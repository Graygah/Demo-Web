package reporting;

import java.time.LocalDateTime;
import reporting.Screenshot;

public class ReportData {
	private LocalDateTime startDate;
	private Screenshot screenshot;
	private String story;
	private String tcId;
	private String shortDesc;
	private String testCase;
	private String expectedResult;
	private String criteria;
	private String tempFolder;
	
	public ReportData() {}
	
	public ReportData(LocalDateTime startDate, Screenshot screenshot, String story, String tcId, String shortDesc, String testCase, String expectedResult, String criteria, String tempFolder) {
		this.startDate = startDate;
		this.screenshot = screenshot;
		this.story = story;
		this.tcId = tcId;
		this.shortDesc = shortDesc;
		this.testCase = testCase;
		this.expectedResult = expectedResult;
		this.criteria = criteria;
		this.tempFolder = tempFolder;
	}
	
	public LocalDateTime getStartDate() {
		return this.startDate;
	}
	
	public Screenshot getScreenshot() {
		return this.screenshot;
	}
	
	public String getStory() {
		return this.story;
	}
	
	public void setScreenshot(Screenshot screenshot) {
		this.screenshot = screenshot;
	}
	
	public String getTcId() {
		return this.tcId;
	}
	
	public String getShortDesc() {
		return this.shortDesc;
	}
	
	public String getTestCase() {
		return this.testCase;
	}
	
	public String getExpectedResult() {
		return this.expectedResult;
	}
	
	public String getCriteria() {
		return this.criteria;
	}
	
	public String getTempFolder() {
		return this.tempFolder;
	}
}