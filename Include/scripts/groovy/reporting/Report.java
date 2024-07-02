package reporting;

import reporting.ReportData;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import com.kms.katalon.core.configuration.RunConfiguration;
import com.kms.katalon.core.testdata.TestData;
import com.kms.katalon.core.testdata.TestDataFactory;

import internal.GlobalVariable;

import java.util.List;
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;


public class Report implements IEventHandler{
	protected Document document;
	private List<Screenshot> screenshotList;
	private String story;
	private String tempFolder;
	private String tcID;
	private String shortDESC;
	private String testCASE;
	private String expectedRESULT;
	private String criteria;
	private Image bniLogo;
	private PdfFont pdfFont;
	private int pageNumber;
	private TestData projectInfo;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	// multi test case
		private List<String> tcIDs;
		private List<String> shortDESCs;
		private List<String> testCASEs;
		private List<String> expectedRESULTs;
		private List<String> criterias;
		private List<List<Screenshot>> screenshotLists;
		private List<String> status;
	
	public Report() {
		this.screenshotList = new ArrayList<Screenshot>();
		
		//multitestcase
				this.screenshotLists = new ArrayList<>();
				this.tcIDs = new ArrayList<String>();
				this.shortDESCs = new ArrayList<String>();
				this.testCASEs = new ArrayList<String>();
				this.expectedRESULTs = new ArrayList<String>();
				this.criterias = new ArrayList<String>();
				this.status = new ArrayList<String>();
	}
	
	
	
	private Report(Document document, LocalDateTime startDate, LocalDateTime endDate) {
		this.document = document;
		this.pageNumber = 1;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Report(List<Screenshot> screenshotList, String tcID, String tempFolder, String shortDESC, String testCASE, String expectedRESULT, String criteria) {
		this.screenshotList = screenshotList;
		this.tcID = tcID;
		this.shortDESC = shortDESC;
		this.testCASE = testCASE;
		this.expectedRESULT = expectedRESULT;
		this.criteria = criteria;
		this.tempFolder = tempFolder;
	}
	
	// multi test case
		public void addReport(List<Screenshot> screenshotList, String tcID, String tempFolder, String shortDESC, String testCASE, String expectedRESULT, String criteria) {
			this.tempFolder = tempFolder;
			this.tcIDs.add(tcID);
			this.shortDESCs.add(shortDESC);
			this.testCASEs.add(testCASE);
			this.expectedRESULTs.add(expectedRESULT);
			this.criterias.add(criteria);
			this.screenshotLists.add(screenshotList);
			this.tcID = tcID;
		}
	
//	Header & Footer Creation
	public void handleEvent(Event event) {
		PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
		Rectangle rectangle = documentEvent.getPage().getPageSize();
        Canvas canvas = new Canvas(documentEvent.getPage(), rectangle);
        
        loadBNILogo();
        this.projectInfo = TestDataFactory.findTestData("Data Files/ProjectInfo");
        PdfFont font = null;
        try {
			font = PdfFontFactory.createFont("C:\\Users\\Jamkrindo\\Downloads\\Arial.ttf");
		} catch (IOException e) {
			e.printStackTrace();
		}
        canvas.setFont(font);
        
		float headerWidth = rectangle.getWidth() - (document.getLeftMargin() + document.getRightMargin());
        float headerLeft = document.getLeftMargin();
        float headerBottom = rectangle.getHeight() - (document.getBottomMargin() * 1.5f);
        
        float footerWidth = headerWidth;
        float footerLeft = headerLeft;
        float footerBottom = document.getBottomMargin() - 10;

        
        //	---------- Header ----------
        Paragraph headerText = new Paragraph();
        headerText.setFontSize(10)
        .setTextAlignment(TextAlignment.LEFT)
        .setBold()
        .setHeight(20f)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
        .setFixedPosition(headerLeft, headerBottom, headerWidth);
        headerText.add(new Text("Automation Test Execution Documentation"));
        
        canvas.add(headerText);
        
        Image headerLogo = this.bniLogo;
        headerLogo
        .scaleToFit(63, 20)
        .setMarginLeft(rectangle.getWidth() - (document.getRightMargin() + document.getLeftMargin() + headerLogo.getImageScaledWidth()))
        .setFixedPosition(headerLeft, headerBottom);
        
        canvas.add(headerLogo);
        
        Table table = new Table(4);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER)
        .setFontSize(8);
        
        String[] headerInfo = {
        		"Project No.\n"					+ "Project Type\n"	+ "Short Description",
        		"[CR/IR/MR Number]\n"			+ "CR / IR / MR\n"	+ "[Short Description]",
        		"Tester\n"						+ "Start Date\n"	+ "End Date",
        		"[XXXXXXXXXXXXX] / [P099999]\n"	+ "[Date]\n"		+ "[Date]"
        };
        
        for (int i = 0; i < headerInfo.length; i++) {
        	Cell cell = new Cell();
        	cell
        	.setBorder(Border.NO_BORDER)
        	.setBorderTop(new SolidBorder(1))
        	.setBorderBottom(new SolidBorder(2));
        	
        	if (i % 2 == 0) {
        		cell.setWidth(0.15f * headerWidth);
        	} else {
        		cell.setWidth(0.35f * headerWidth);
        	}
        	
        	if (i % 3 == 1) {
        		cell.setBorderRight(new SolidBorder(1));
        	}
        	
        	if (i == 1) {
        		try {
					cell.add(new Paragraph(new Text(this.projectInfo.getValue("PROJECT_NO", 1) + "\n")));
					String projectInfoprojectType = projectInfo.getValue("PROJECT_TYPE", 1);
					switch (projectInfoprojectType) {
					case "CR":
						cell.add(new Paragraph()
								.add(new Text("CR").setBold())
								.add(new Text(" / IR / MR" + "\n")));
						break;
					case "IR":
						cell.add(new Paragraph()
								.add(new Text("CR / "))
								.add(new Text("IR").setBold())
								.add(new Text(" / MR" + "\n")));
						break;
					case "MR":
						cell.add(new Paragraph()
								.add(new Text("CR / IR / "))
								.add(new Text("MR" + "\n").setBold()));
						break;
					}
					cell.add(new Paragraph(new Text(this.projectInfo.getValue("SHORT_DESCRIPTION", 1))));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
        	} else if (i == 3) {
        		try {
					cell.add(new Paragraph(new Text(projectInfo.getValue("TESTER", 1) + "\n")));
					cell.add(new Paragraph(new Text(this.startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss,SSS")) + "\n")));
					cell.add(new Paragraph(new Text(this.endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss,SSS")))));
				} catch (IOException e) {
					e.printStackTrace();
				}
        	} else {
        		cell.add(new Paragraph(new Text(headerInfo[i])));        		
        	}
        	table.addCell(cell);
        }
        table.setFixedPosition(headerLeft, headerBottom - (15f + (headerLogo.getImageScaledHeight() * 1.5f)), headerWidth);
        canvas.add(table);
        
        //	---------- Footer ----------
        Paragraph copyright = new Paragraph();
        copyright
        .setFontSize(8)
        .setTextAlignment(TextAlignment.LEFT)
        .setFontColor(new DeviceRgb(102, 170, 193))
        .setFixedPosition(footerLeft, footerBottom, footerWidth)
        .add(new Text("Copyright © (" + LocalDate.now().getYear() + ") by BNI-STI. Testing Strategy Execution Form version 1.1.0"));
        
        canvas.add(copyright);
        
        Paragraph pageNumber = new Paragraph();
        pageNumber
        .setFontSize(10)
        .setTextAlignment(TextAlignment.RIGHT)
        .setFixedPosition(footerLeft, footerBottom, footerWidth)
        .add(new Text(String.valueOf(this.pageNumber)));
        
        canvas.add(pageNumber);
        this.pageNumber++;
        
        canvas.close();
	}
	
	public static void createReport(LocalDateTime endDate, ReportData reportData) throws IOException {
		Report report = new Report(reportData.getScreenshot().getScreenshotList(), reportData.getTcId(), reportData.getTempFolder(), reportData.getShortDesc(), reportData.getTestCase(), reportData.getExpectedResult(), reportData.getCriteria());
		report.setStory(reportData.getStory());
		report.createPDF(reportData.getStartDate(), endDate);
	}
	
	// multi test case
		public static void createReports(LocalDateTime endDate, List<ReportData> reportDatas) throws IOException {
			Report report = new Report();
			LocalDateTime startDate = null;
			for(ReportData reportData : reportDatas) {
				report.addReport(reportData.getScreenshot().getScreenshotList(), reportData.getTcId(), reportData.getTempFolder(), reportData.getShortDesc(), reportData.getTestCase(), reportData.getExpectedResult(), reportData.getCriteria());
				report.setStory(reportData.getStory());
				startDate = reportData.getStartDate();
			}
			report.createPDFs(startDate, endDate);
		}
	
	private void setStory(String story) {
		this.story = setCapitalize(story);
	}
	
	private String setCapitalize(String string) {
		string = string.trim().toLowerCase();
		string = string.substring(0, 1).toUpperCase() + string.substring(1);
		
		for (int i = 0; i < Integer.parseInt(String.valueOf(string.chars().count())); i++) {
			if (string.charAt(i) == ' ') {
				string = string.substring(0, i + 1) + string.substring(i + 1, i + 2).toUpperCase() + string.substring(i + 2);
			}
		}
		return string;
	}
	
	public void createPDF(LocalDateTime startDate, LocalDateTime endDate) throws IOException {
		loadBNILogo();
		this.pdfFont = PdfFontFactory.createFont("C:\\Users\\Jamkrindo\\Downloads\\Arial.ttf");
		this.projectInfo = TestDataFactory.findTestData("Data Files/ProjectInfo");
		
		//	Declare Variable
		Image image;
		ImageData imageData;
		Paragraph info;
		Paragraph title;
		String picturePath;
		
		//	PDF Variable
		AreaBreak areaBreak;
		Document document;
		PdfDocument pdfDocument;
		PdfWriter pdfWriter;
		String pdfPath;
		
		//	Initialize Variable
		pdfPath = RunConfiguration.getProjectDir() + "/Reports/" + this.tcID + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss")) + ".pdf";
		pdfWriter = new PdfWriter(pdfPath);
		pdfDocument = new PdfDocument(pdfWriter);
		document = new Document(pdfDocument);
		areaBreak = new AreaBreak();
		
		document.getPageEffectiveArea(PageSize.A4);
		
		createCoverPage(document);
		document.setTopMargin(110f);
		document.setLeftMargin(75f);
		document.setRightMargin(75f);
		document.setBottomMargin(40f);
		pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new Report(document, startDate, endDate));
		
		addSignatureAndInfo(document);
		document.add(areaBreak);
		
		Style titleStylee = new Style();
		titleStylee
		.setFontSize(10)
		.setFontColor(new DeviceRgb(102, 170, 193));
		
		document.add(addInfoTitle(titleStylee, "Test Steps", 9));
		
		//	Put Screenshot To PDF
		int index = 0;
		for(Screenshot screenshot : this.screenshotList) {
			Div div = new Div().setKeepTogether(true);
			
			//	For Title
			title = new Paragraph(String.valueOf(9 + "." + screenshot.getNameIndex()) + ". " + screenshot.getTitle() + " (" + screenshot.getStatus() + ")");
			Style titleStyle = new Style();
			titleStyle
			.setFontSize(10)
			.setMarginLeft(23);
			title.addStyle(titleStyle);
			div.add(title);
			
			//	For Image
			picturePath = screenshot.getFileName();
			imageData = ImageDataFactory.create(picturePath);
			image = new Image(imageData);
			image
			.setMaxWidth(125f)
			.setMarginLeft(titleStyle.getMarginLeft().getValue())
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
			div.add(image);
			
			//	For Info
			info = new Paragraph(screenshot.getInfo());
			Style infoStyle = new Style();
			infoStyle.setFontSize(10f)
			.setMaxWidth(document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + titleStyle.getMarginLeft().getValue()))
			.setMarginLeft(titleStyle.getMarginLeft().getValue())
			.setTextAlignment(TextAlignment.CENTER);
			info.addStyle(infoStyle);
			div.add(info);
			
			if(index != this.screenshotList.size() - 1) {
				div.add(new Paragraph(new Text("\n")));
			}
			
			document.add(div);
			index++;
		}
		
		deleteTempFolder();
		document.close();
	}
	
	// multi test case
		public void createPDFs(LocalDateTime startDate, LocalDateTime endDate) throws IOException {
			loadBNILogo();
			this.pdfFont = PdfFontFactory.createFont("C:\\Users\\Jamkrindo\\Downloads\\Arial.ttf");
			this.projectInfo = TestDataFactory.findTestData("Data Files/ProjectInfo");
			
			//	Declare Variable
			Image image;
			ImageData imageData;
			Paragraph info;
			Paragraph title;
			String picturePath;
			
			//	PDF Variable
			AreaBreak areaBreak;
			Document document;
			PdfDocument pdfDocument;
			PdfWriter pdfWriter;
			String pdfPath;
			
			//	Initialize Variable
			pdfPath = RunConfiguration.getProjectDir() + "/Reports/" + this.tcID + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss")) + ".pdf";
			pdfWriter = new PdfWriter(pdfPath);
			pdfDocument = new PdfDocument(pdfWriter);
			document = new Document(pdfDocument);
			areaBreak = new AreaBreak();
			
			document.getPageEffectiveArea(PageSize.A4);
			
			createCoverPage(document);
			document.setTopMargin(110f);
			document.setLeftMargin(75f);
			document.setRightMargin(75f);
			document.setBottomMargin(40f);
			pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new Report(document, startDate, endDate));
			
			addSignatureAndInfos(document);
			document.add(areaBreak);
			
			Style titleStylee = new Style();
			titleStylee
			.setFontSize(10)
			.setFontColor(new DeviceRgb(102, 170, 193));
			
			//	Put Screenshot To PDF
			int index = 0;
			for(List<Screenshot> screenshots : screenshotLists) {
				document.add(addInfoTitle(titleStylee, "Test Steps (" + testCASEs.get(index) + ")", index+9));
				for(Screenshot screenshot : screenshots) {
					Div div = new Div().setKeepTogether(true);
					
					//	For Title
					title = new Paragraph(String.valueOf(index+9 + "." + screenshot.getNameIndex()) + ". " + screenshot.getTitle() + " (" + screenshot.getStatus() + ")");
					Style titleStyle = new Style();
					titleStyle
					.setFontSize(10)
					.setMarginLeft(23);
					title.addStyle(titleStyle);
					div.add(title);
					
					//	For Image
					picturePath = screenshot.getFileName();
					imageData = ImageDataFactory.create(picturePath);
					image = new Image(imageData);
					image
					.setMaxWidth(125f)	//size capture
					.setMarginLeft(titleStyle.getMarginLeft().getValue())
					.setHorizontalAlignment(HorizontalAlignment.CENTER);
					div.add(image);
					
					//	For Info
					info = new Paragraph(screenshot.getInfo());
					Style infoStyle = new Style();
					infoStyle.setFontSize(10f)
					.setMaxWidth(document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + titleStyle.getMarginLeft().getValue()))
					.setMarginLeft(titleStyle.getMarginLeft().getValue())
					.setTextAlignment(TextAlignment.CENTER);
					info.addStyle(infoStyle);
					div.add(info);
					
					if(index != screenshots.size() - 1) {
						div.add(new Paragraph(new Text("\n")));
					}
					
					document.add(div);
				}
				index++;
			}
			
			
			deleteTempFolder();
			document.close();
		}
	
	private void createCoverPage(Document document) throws IOException {
		Div divTop		= setCoverDivTop(document);
		Div divMiddle	= setCoverDivMiddle(document);
		Div divBottom	= setCoverDivBottom(document);
		
		document.add(divTop);
		document.add(divMiddle);
		document.add(divBottom);
	}
	
	private float countHeightCoverDiv(float percentage, Document document) {
		return percentage * (document.getPdfDocument().getDefaultPageSize().getHeight() - (document.getTopMargin() + document.getBottomMargin()));
	}
	
	private void addSignatureAndInfo(Document document) {
		Paragraph paragraph = new Paragraph();
		paragraph.setFontSize(10);
		paragraph.setTextAlignment(TextAlignment.JUSTIFIED);
		paragraph.add(new Text("The information in this document has been duly reviewed and agreed by the following representatives of each party, continue to the next step."));
		document.add(paragraph);
		
		AreaBreak areaBreak;
		
		Table table = new Table(3);
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);
		
		String[] tableHeader = {
				"Tester/Developer", "Test Manager", "Testing Group Head",
				"Development Manager", "Requirement Manager / Business Analyst", "Project Manager"
				};
		
		int index = 0;
		for (int i = 0; i < 12; i++) {
			Cell cell = new Cell();
			cell.setWidth(0.25f * document.getPdfDocument().getDefaultPageSize().getWidth());
			cell.setFontSize(10);
			if (i % 6 < 3) {
				cell.add(new Paragraph(new Text(tableHeader[index])).setPaddingLeft(5).setPaddingRight(5).setVerticalAlignment(VerticalAlignment.MIDDLE));
				cell.setBackgroundColor(new DeviceRgb(147, 205, 220));
				index++;
			} else {
				cell.setTextAlignment(TextAlignment.CENTER);
				if (i < 6) {

					switch (i){
					case 3:
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						break;
						
					case 4:
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						break;
						
					case 5:
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						break;
					}
				}  else if(i > 6){
					
					switch (i){
					case 9:
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						break;
						
					case 10:
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						break;
						
					case 11:
						cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
						break;
					}
				}
			}
			table.addCell(cell);
		}
		document.add(table);
		
		document.add(new Paragraph(new Text("\n")));
		
		Style titleStyle = new Style();
		titleStyle
		.setFontSize(10)
		.setFontColor(new DeviceRgb(102, 170, 193));
		
		Style textStyle = new Style();
		textStyle
		.setFontSize(10)
		.setMarginLeft(23);
				
		Div info1 = addInfo1ShortDescription(titleStyle, textStyle, 1);
		document.add(info1);
		
		Div info2 = addInfo2BusinessRequirement(titleStyle, textStyle, 2);
		document.add(info2);
		
		Div info3 = addInfo3SystemImpacted(titleStyle, textStyle, 3);
		document.add(info3);
		
		Div info4 = addInfo4SystemChange(titleStyle, textStyle, 4);
		document.add(info4);
		
		areaBreak = new AreaBreak();
		document.add(areaBreak);
		
		Div info7 = addInfo7TestingScenario(titleStyle, textStyle, 5);
		document.add(info7);

		try {
			Div info73 = addInfo73AutomationTestPlan(document, textStyle, projectInfo.getRowNumbers(), 5.1f);
			document.add(info73);
			document.add(new Paragraph(new Text("\n")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		areaBreak = new AreaBreak();
		document.add(areaBreak);
		
		Div info8 = addInfo8TestExecutionPlan(document, titleStyle, textStyle, 6);
		document.add(info8);
		
		areaBreak = new AreaBreak();
		document.add(areaBreak);
		
		Div info9 = addInfo9DocumentAttributes(document, titleStyle, textStyle, 7);
		document.add(info9);
		
		areaBreak = new AreaBreak();
		document.add(areaBreak);
	
		Div info11 = addInfo11DocumentSummary(document, textStyle, 8, titleStyle);
		try {
			document.add(info11);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		document.add(new Paragraph(new Text("\n")));
		
		Div info11_2 = addInfo11_2DocumentSummary(document, textStyle);
		try {
			document.add(info11_2);
			document.add(new Paragraph(new Text("\n")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// multi test case
		private void addSignatureAndInfos(Document document) {
			Paragraph paragraph = new Paragraph();
			paragraph.setFontSize(10);
			paragraph.setTextAlignment(TextAlignment.JUSTIFIED);
			paragraph.add(new Text("The information in this document has been duly reviewed and agreed by the following representatives of each party, continue to the next step."));
			document.add(paragraph);
			
			AreaBreak areaBreak;
			
			Table table = new Table(3);
			table.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
			String[] tableHeader = {
					"Tester/Developer", "Test Manager", "Testing Group Head",
					"Development Manager", "Requirement Manager / Business Analyst", "Project Manager"
					};
			
			int index = 0;
			for (int i = 0; i < 12; i++) {
				Cell cell = new Cell();
				cell.setWidth(0.25f * document.getPdfDocument().getDefaultPageSize().getWidth());
				cell.setFontSize(10);
				if (i % 6 < 3) {
					cell.add(new Paragraph(new Text(tableHeader[index])).setPaddingLeft(5).setPaddingRight(5).setVerticalAlignment(VerticalAlignment.MIDDLE));
					cell.setBackgroundColor(new DeviceRgb(147, 205, 220));
					index++;
				} else {
					cell.setTextAlignment(TextAlignment.CENTER);
					if (i < 6) {

						switch (i){
						case 3:
							cell.add(new Paragraph(new Text("\n\n\n\n     Aditya Pradana     ")));
							cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
							break;
							
						case 4:
							cell.add(new Paragraph(new Text("\n\n\n\n      Feby Ramdhana      ")));
							cell.add(new Paragraph(new Text("\n\n\n\nGrafiaramagda Chairunnisa")));
							break;
							
						case 5:
							cell.add(new Paragraph(new Text("\n\n\n\n      Era Pratiwi       ")));
							cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
							break;
						}
					}  else if(i > 6){
						
						switch (i){
						case 9:
							cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
							break;
							
						case 10:
							cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
							break;
							
						case 11:
							cell.add(new Paragraph(new Text("\n\n\n\n________________________")));
							break;
						}
					}
				}
				table.addCell(cell);
			}
			document.add(table);
			
			document.add(new Paragraph(new Text("\n")));
			
			Style titleStyle = new Style();
			titleStyle
			.setFontSize(10)
			.setFontColor(new DeviceRgb(102, 170, 193));
			
			Style textStyle = new Style();
			textStyle
			.setFontSize(10)
			.setMarginLeft(23);
			
			areaBreak = new AreaBreak();
			document.add(areaBreak);
			
			/*
			Div tableOfContents = addTableOfContents(titleStyle, textStyle);
			document.add(tableOfContents);
			
			
			areaBreak = new AreaBreak();
			document.add(areaBreak);
			*/
					
			Div info1 = addInfo1ShortDescription(titleStyle, textStyle, 1);
			document.add(info1);
			
			Div info2 = addInfo2BusinessRequirement(titleStyle, textStyle, 2);
			document.add(info2);
			
			Div info3 = addInfo3SystemImpacted(titleStyle, textStyle, 3);
			document.add(info3);
			
			Div info4 = addInfo4SystemChange(titleStyle, textStyle, 4);
			document.add(info4);
			
			areaBreak = new AreaBreak();
			document.add(areaBreak);
			
			Div info7 = addInfo7TestingScenario(titleStyle, textStyle, 5);
			document.add(info7);

			try {
				Div info73 = addInfo73AutomationTestPlans(document, textStyle, projectInfo.getRowNumbers(), 5.1f);
				document.add(info73);
				document.add(new Paragraph(new Text("\n")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			areaBreak = new AreaBreak();
			document.add(areaBreak);
			
			Div info8 = addInfo8TestExecutionPlan(document, titleStyle, textStyle, 6);
			document.add(info8);
			
			areaBreak = new AreaBreak();
			document.add(areaBreak);
			
			Div info9 = addInfo9DocumentAttributes(document, titleStyle, textStyle, 7);
			document.add(info9);
			
			areaBreak = new AreaBreak();
			document.add(areaBreak);
		
			Div info11 = addInfo11DocumentSummarys(document, textStyle, 8, titleStyle);
			try {
				document.add(info11);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			document.add(new Paragraph(new Text("\n")));
			
			Div info11_2 = addInfo11_2DocumentSummarys(document, textStyle);
			try {
				document.add(info11_2);
				document.add(new Paragraph(new Text("\n")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	private Div setCoverDivTop(Document document) throws IOException {
		Div div = new Div();
		div.setVerticalAlignment(VerticalAlignment.MIDDLE);
		div.setHeight(countHeightCoverDiv(0.7f, document));
		div.setFont(this.pdfFont);
		
		Paragraph paragraph = new Paragraph();
		paragraph.setTextAlignment(TextAlignment.RIGHT);
		
		Text text = new Text(this.story + "\n");
		text.setFontSize(36f);
		text.setBold();
		text.setFontColor(new DeviceRgb(102, 170, 193));
		paragraph.add(text);
		
		text = new Text("BNI Mobile Banking Automation Report" + "\n");
		text.setFontSize(20f);
		text.setBold();
		text.setFontColor(new DeviceRgb(230, 111, 24));
		paragraph.add(text);
		
		
		//Untuk penambahan TC ID diCover, Sementara di matikan dulu
//		text = new Text(this.tcID);
//		text.setFontSize(16f);
//		paragraph.add(text);
		
		div.add(paragraph);
		return div;
	}
	
	private Div setCoverDivMiddle(Document document) throws IOException {
		Div div = new Div();
		div.setHeight(countHeightCoverDiv(0.15f, document));
		div.setFont(this.pdfFont);
		
		Paragraph paragraph = new Paragraph();
		
		Text text = new Text(projectInfo.getValue("SHORT_DESCRIPTION", 1));
		text.setFontSize(18f);
		paragraph.add(text);
		
		text = new Text("\nPrepared by Kelompok TSA Automation - Aditya Pradana Putra");
		text.setFontSize(12f);
		//text.setItalic();
		paragraph.add(text);
		
		text = new Text("\n" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		text.setFontSize(12f);
		paragraph.add(text);
		
		div.add(paragraph);
		return div;
	}
	
	private Div setCoverDivBottom(Document document) throws IOException {
		Div div = new Div();
		div.setHeight(countHeightCoverDiv(0.15f, document));
		div.setFont(this.pdfFont);
		div.setFontColor(new DeviceRgb(156, 156, 156));
		div.setVerticalAlignment(VerticalAlignment.BOTTOM);
		
		Paragraph paragraph = new Paragraph();
		
		paragraph.add(this.bniLogo.setHeight(30));
		
		Text text = new Text("\nCOPYRIGHT NOTICE");
		text.setFontSize(10f);
		paragraph.add(text);
		
		text = new Text("\nCopyright © (" + LocalDate.now().getYear() + ") by BNI-STI");
		text.setBold();
		text.setFontSize(10f);
		paragraph.add(text);
		
		text = new Text("\nAll right reserved. This material is confidential and proprietary to BNI-STI and no part of this material should be reproduced, published in any form by any means, electronic or mechanical including photocopy or any information storage or retrieval system nor should the material be disclosed to third parties without the express written authorization of BNI-STI.");
		text.setFontSize(8f);
		paragraph.add(text);
		paragraph.setTextAlignment(TextAlignment.JUSTIFIED);
		div.add(paragraph);
		div.setVerticalAlignment(VerticalAlignment.BOTTOM);
		
		return div;
	}
	
	private Div addInfo1ShortDescription(Style titleStyle, Style textStyle, int number) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "Short Description", number));
		
		try {
			div.add(addInfoText(textStyle, shortDESC + " - " + testCASE + "\n\n"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return div;
	}
	
	private Div addInfo2BusinessRequirement(Style titleStyle, Style textStyle, int number) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "Business/System Requirement", number));
		try {
			div.add(addInfoText(textStyle, projectInfo.getValue("SYSTEM_REQUIREMENT", 1) + "\n\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return div;
	}
	
	private Div addInfo3SystemImpacted(Style titleStyle, Style textStyle, int number) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "System Impacted", number));
		try {
			div.add(addInfoText(textStyle, projectInfo.getValue("SYSTEM_IMPACTED", 1) + "\n\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return div;
	}
	
	private Div addInfo4SystemChange(Style titleStyle, Style textStyle, int number) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "System Change", number));
		try {
			div.add(addInfoText(textStyle, projectInfo.getValue("SYSTEM_CHANGE", 1) + "\n\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return div;
	}
	
	private Div addInfo7TestingScenario(Style titleStyle, Style textStyle, int number) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "Testing Scenario", number));
		try {
			div.add(addInfoText(textStyle, projectInfo.getValue("TESTING_SCENARIO", 1)));
		} catch (IOException e) {
		}
		return div;
	}
	
	private Div addInfo73AutomationTestPlan(Document document, Style textStyle, int totalDataFilesRow, float number) {
		Div div = new Div();
		div.add(addInfoSubTitle(textStyle, "Automation Test Plan", number));
		Table table = new Table(5).addStyle(textStyle).setFontSize(9);
		div.add(addInfo7TableTemplate(document, table, "AUTOMATION_TEST_PLAN", textStyle));
		return div;
	}
	
	// multi test case
		private Div addInfo73AutomationTestPlans(Document document, Style textStyle, int totalDataFilesRow, float number) {
			Div div = new Div();
			div.add(addInfoSubTitle(textStyle, "Automation Test Plan", number));
			Table table = new Table(5).addStyle(textStyle).setFontSize(9);
			div.add(addInfo7TableTemplates(document, table, "AUTOMATION_TEST_PLAN", textStyle));
			return div;
		}
	
	private Div addInfo11_2DocumentSummary(Document document, Style textStyle) {
		Div div = new Div();
		Table table = new Table(4).addStyle(textStyle).setFontSize(9);
		div.add(addInfo11TableTemplate(document, table, "AUTOMATION_TEST_PLAN", textStyle));
		return div;
	}
	
	// multi test case
		private Div addInfo11_2DocumentSummarys(Document document, Style textStyle) {
			Div div = new Div();
			Table table = new Table(5).addStyle(textStyle).setFontSize(9);
			div.add(addInfo11TableTemplates(document, table, "AUTOMATION_TEST_PLAN", textStyle));
			return div;
		}
	
	private Div addInfo8TestExecutionPlan(Document document, Style titleStyle, Style textStyle, int number) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "Test Execution Plan", number));
		try {
			div.add(addInfoText(textStyle, projectInfo.getValue("TEST_EXECUTION_PLAN", 1)));
		} catch (IOException e) {
		}
		
		Table table = new Table(2).addStyle(textStyle);
		
		Cell firstCell = new Cell();
		firstCell
		.setFontSize(10)
		.setBackgroundColor(new DeviceRgb(147, 205, 220))
		.setBold()
		.setTextAlignment(TextAlignment.CENTER)
		.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
		.add(new Paragraph(new Text("Test Type")));
		table.addCell(firstCell);
		
		Cell secondCell = new Cell();
		secondCell
		.setFontSize(10)
		.setBackgroundColor(new DeviceRgb(147, 205, 220))
		.setBold()
		.setTextAlignment(TextAlignment.CENTER)
		.setWidth(0.8f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
		.add(new Paragraph(new Text("Description")));
		table.addCell(secondCell);
		
		Cell regressionCell = new Cell();
		regressionCell
		.setFontSize(9)
		.setBold()
		.setTextAlignment(TextAlignment.CENTER)
		.setVerticalAlignment(VerticalAlignment.MIDDLE)
		.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
		.add(new Paragraph(new Text("Regression Test")));
		table.addCell(regressionCell);
		
		Cell regressionCell2 = new Cell();
		regressionCell2
		.setFontSize(9)
		.setBold()
		.setTextAlignment(TextAlignment.JUSTIFIED)
		.setWidth(0.8f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
		.add(new Paragraph(new Text("Testing with the intent of determining if bug fixes have been successful and have not created any new problems. Also, this type of testing is done to ensure that no degradation of baseline functionality has occurred")));
		table.addCell(regressionCell2);
		
		String[] testType = {
				"Unit Test",			"Unit tests ensure that each unique path of the project performs accurately to the documented specifications and contains clearly defined inputs and expected results",
				"System Test",			"System testing ensures that the entire integrated software system meets requirements. It tests a configuration to ensure known and predictable results. System testing is based on process descriptions and flows, emphasizing pre-driven process links and integration points",
				"Integration Test",		"Testing two or more modules or functions together with the intent of finding interface defects between the modules or functions",
				"Functional Test",		"Functional test can be defined as testing two or more modules together with the intent of finding defects, demonstrating that defects are not present, verifying that the module performs its intended functions as stated in the specification and establishing confidence that a program does what it is supposed to do",
				"Performance Test",		"Testing with the intent of determining how quickly a product handles a variety of events. Automated test tools geared specifically to test and fine-tune performance are used most often for this type of testing",
				"Compatibility Test",	"Testing used to determine whether other system software components such as browsers, utilities, and competing software will conflict with the software being tested.",
				"Load Test",			"Testing with the intent of determining how well the product handles competition for system resources. The competition may come in the form of network traffic, CPU utilization or memory allocation",
				"Stress Test",			"Testing with the intent of determining how well a product performs when a load is placed on the system resources that nears and then exceeds capacity"
		};
		
		for (int i = 0; i < testType.length; i++) {
			
			if (i % 2 == 0) {
				Cell cell = new Cell();
				cell
				.setFontSize(9)
				.setTextAlignment(TextAlignment.CENTER)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
				.add(new Paragraph(new Text(testType[i])));
				table.addCell(cell);
			} else {
				Cell cell = new Cell();
				cell
				.setFontSize(9)
				.setTextAlignment(TextAlignment.JUSTIFIED)
				.setWidth(0.8f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
				.add(new Paragraph(new Text(testType[i])));
				table.addCell(cell);
			}
		}
		div.add(table);
		
		return div;
	}
	
	private Div addInfo9DocumentAttributes(Document document, Style titleStyle, Style textStyle, int number) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "Document Attributes", number));
		try {
			div.add(addInfoText(textStyle, projectInfo.getValue("DOCUMENT_ATTRIBUTES", 1)));
		} catch (IOException e) {
		}
		
		Table table = new Table(2).addStyle(textStyle);
		
		Cell firstCell = new Cell();
		firstCell
		.setFontSize(10)
		.setBackgroundColor(new DeviceRgb(147, 205, 220))
		.setBold()
		.setTextAlignment(TextAlignment.CENTER)
		.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
		.add(new Paragraph(new Text("Key")));
		table.addCell(firstCell);
		
		Cell secondCell = new Cell();
		secondCell
		.setFontSize(10)
		.setBackgroundColor(new DeviceRgb(147, 205, 220))
		.setBold()
		.setTextAlignment(TextAlignment.CENTER)
		.setWidth(0.8f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
		.add(new Paragraph(new Text("Value")));
		table.addCell(secondCell);

		String[] testType = {
				"Katalon Version",			GlobalVariable.KatalonVersion.toString(),
				"Mobile Apps",				GlobalVariable.MobileApps.toString(),
				"Mobile Apps Version",		GlobalVariable.MobileAppsVersion.toString(),
				"Bundle Identifier",		GlobalVariable.BundleIdentifier.toString(),
				"Device Type",				GlobalVariable.DeviceType.toString(),
				"Device Name",				GlobalVariable.DeviceName.toString(),
				"OS Version",				GlobalVariable.OSVersion.toString(),
				"UDID",						GlobalVariable.UDID.toString(),
				"Automation Name",			GlobalVariable.AutomationName.toString(),
				"remoteWebDriverUrl",		GlobalVariable.remoteWebDriverUrl.toString(),
				"remoteWebDriverType",		GlobalVariable.remoteWebDriverType.toString(),
				"remoteMobileDriver",		GlobalVariable.remoteMobileDriver.toString(),
				"Ukuran Layar",				GlobalVariable.UkuranLayar.toString(),
				"Resolusi",					GlobalVariable.Resolusi.toString(),
				"Kepadatan Pixel",			GlobalVariable.KepadatanPixel.toString(),
		};
		
		for (int i = 0; i < testType.length; i++) {
			
			if (i % 2 == 0) {
				Cell cell = new Cell();
				cell
				.setFontSize(9)
				.setTextAlignment(TextAlignment.JUSTIFIED)
				.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
				.add(new Paragraph(new Text(testType[i])));
				table.addCell(cell);
			} else {
				Cell cell = new Cell();
				cell
				.setFontSize(9)
				.setTextAlignment(TextAlignment.JUSTIFIED)
				.setWidth(0.8f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
				.add(new Paragraph(new Text(testType[i])));
				table.addCell(cell);
			}
		}
		div.add(table);
		
		return div;
	}
	
	private Div addInfo11DocumentSummary(Document document, Style textStyle, int number, Style titleStyle) {
		Div div = new Div();
		div.add(addInfoTitle(titleStyle, "Document Summary", number));
		Table table = new Table(3).addStyle(textStyle).setFontSize(9);
		div.add(addInfo11TableDocumentSummary(document, table, "DOCUMENT_SUMMARY", textStyle));	
		return div;
	}
	
	// multi test case
		private Div addInfo11DocumentSummarys(Document document, Style textStyle, int number, Style titleStyle) {
			Div div = new Div();
			div.add(addInfoTitle(titleStyle, "Document Summary", number));
			Table table = new Table(3).addStyle(textStyle).setFontSize(9);
			div.add(addInfo11TableDocumentSummarys(document, table, "DOCUMENT_SUMMARY", textStyle));	
			return div;
		}
	
	private Paragraph addInfoTitle(Style titleStyle, String title, int number) {
		Paragraph paragraph = new Paragraph();
		paragraph.addStyle(titleStyle);
		paragraph.add(new Text(String.valueOf(number) + ".		"));
		paragraph.add(new Text(title));
		return paragraph;
	}
	
	private Paragraph addInfoText(Style textStyle, String text) {
		Paragraph paragraph = new Paragraph();
		paragraph.addStyle(textStyle);
		paragraph.add(new Text(text));
		return paragraph;
	}
	
	private Paragraph addInfoSubTitle(Style textStyle, String title, float number) {
		Paragraph paragraph = new Paragraph();
		paragraph.addStyle(textStyle);
		paragraph.add(new Text(String.valueOf(number) + ".		"));
		paragraph.add(new Text(title));
		return paragraph;
	}
	
	private void deleteTempFolder() {
		File tempFolder = new File(this.tempFolder);
		try {
			FileUtils.forceDelete(tempFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadBNILogo() {
		String imagePath = RunConfiguration.getProjectDir() + "/Data Files/Image/BNI_logo.png";
		ImageData imageData = null;
		try {
			imageData = ImageDataFactory.create(imagePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.bniLogo = new Image(imageData);
	}
	
	private Table addInfo11TableDocumentSummary(Document document, Table table, String dataFilesHeader, Style textStyle) {
		String[] tableHeader = {"Total Passed", "Total Failed", "Total"};

		for (int i = 0; i < tableHeader.length; i++) {
			Cell cell = new Cell();
			cell
			.setFontSize(10)
			.setBackgroundColor(new DeviceRgb(147, 205, 220))
			.setBold()
			.setTextAlignment(TextAlignment.CENTER)
			.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
			.add(new Paragraph(new Text(tableHeader[i])));
			
			table.addCell(cell);
		}
		
		for (int i = 0; i < 3; i++) {
			Cell cell = new Cell();
			cell.setWidth(0.25f * document.getPdfDocument().getDefaultPageSize().getWidth());
			cell.setFontSize(10);
			cell.setTextAlignment(TextAlignment.CENTER);
			String totalRunTestCase = "1";
			
			if (i < 3) {	
				String g=null; //failed
				String h=null; //passed
				
	            for (Screenshot screenshot:this.screenshotList) {
	            	boolean d = screenshot.getStatus().contains("Failed");
	            	if (d) {
						g = "1";
						if (g=="1") {
							h = "0";
						}
	            	} else if (!d) {
						g = "0";
						if(g == "0") {
							h = "1";
						}
					} 
	            }

				switch (i){
				case 0:
					cell.add(new Paragraph(new Text(h).setFontColor(new DeviceRgb(18, 148, 6))));
					break;
					
				case 1:
					cell.add(new Paragraph(new Text(g).setFontColor(new DeviceRgb(245, 7, 7))));
					break;
					
				case 2:
					cell.add(new Paragraph(new Text(totalRunTestCase)));
					break;
				}
			}
			table.addCell(cell);
		}
		return table;
	}

	// multi test case
		private Table addInfo11TableDocumentSummarys(Document document, Table table, String dataFilesHeader, Style textStyle) {
			String[] tableHeader = {"Total Passed", "Total Failed", "Total"};

			for (int i = 0; i < tableHeader.length; i++) {
				Cell cell = new Cell();
				cell
				.setFontSize(10)
				.setBackgroundColor(new DeviceRgb(147, 205, 220))
				.setBold()
				.setTextAlignment(TextAlignment.CENTER)
				.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
				.add(new Paragraph(new Text(tableHeader[i])));
				
				table.addCell(cell);
			}
			
			Integer totalRunTestCase = 0;
			Integer totalPassed = 0;
			Integer totalFailed = 0;
			
			for (List<Screenshot> screenshots : this.screenshotLists) {
	            
		        totalRunTestCase++;
		        
		        Integer g=0; //failed
				Integer h=0; //passed
		        for (Screenshot screenshot : screenshots) {
		        	boolean d = screenshot.getStatus().contains("Failed");
		        	if (d) {
						g = 1;
						h = 0;
		        	} else {
						g = 0;
						h = 1;
					}
		        }
		        
		        if(g == 1) {
		        	totalFailed++;
		        	this.status.add("Failed");
		        } else if(h == 1) {
		        	totalPassed++;
		        	this.status.add("Passed");
		        }
	        
			}
	        
			for (int i = 0; i < 3; i++) {
				
				Cell cell = new Cell();
				cell.setWidth(0.25f * document.getPdfDocument().getDefaultPageSize().getWidth());
				cell.setFontSize(10);
				cell.setTextAlignment(TextAlignment.CENTER);
				
				switch (i){
				case 0:
					cell.add(new Paragraph(new Text(totalPassed.toString()).setFontColor(new DeviceRgb(18, 148, 6))));
					break;
					
				case 1:
					cell.add(new Paragraph(new Text(totalFailed.toString()).setFontColor(new DeviceRgb(245, 7, 7))));
					break;
					
				case 2:
					cell.add(new Paragraph(new Text(totalRunTestCase.toString())));
					break;
				}
				
				table.addCell(cell);
			
			}
			
			return table;
		}
	
	private Table addInfo7TableTemplate(Document document, Table table, String dataFilesHeader, Style textStyle) {
		String[] tableHeader = {"No.", "Skenario", "Test Case", "Expected Result", "Criteria"};
		
		for (int i = 0; i < tableHeader.length; i++) {
			Cell cell = new Cell();
			cell
			.setFontSize(9)
			.setBackgroundColor(new DeviceRgb(147, 205, 220))
			.setBold()
			.setTextAlignment(TextAlignment.CENTER)
			.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
			.add(new Paragraph(new Text(tableHeader[i])));
			
			table.addCell(cell);
		}
		
		for (int i = 0; i < 5; i++) {
			Cell cell = new Cell();
			cell.setWidth(0.25f * document.getPdfDocument().getDefaultPageSize().getWidth());
			cell.setFontSize(9);
			cell.setTextAlignment(TextAlignment.LEFT);
			
			if (i < 5) {

				switch (i){
				case 0:
					cell.add(new Paragraph(new Text(tcID)));
					break;
					
				case 1:
					cell.add(new Paragraph(new Text(shortDESC)));
					break;
					
				case 2:
					cell.add(new Paragraph(new Text(testCASE)));
					break;
					
				case 3:
					cell.add(new Paragraph(new Text(expectedRESULT)));
					break;
					
				case 4:
					cell.add(new Paragraph(new Text(criteria)));
					break;
				}
			}
			table.addCell(cell);
		}
		return table;
	}
	
	// multi test case
		private Table addInfo7TableTemplates(Document document, Table table, String dataFilesHeader, Style textStyle) {
			String[] tableHeader = {"No.", "Skenario", "Test Case", "Expected Result", "Criteria"};
			
			for (int i = 0; i < tableHeader.length; i++) {
				Cell cell = new Cell();
				cell
				.setFontSize(9)
				.setBackgroundColor(new DeviceRgb(147, 205, 220))
				.setBold()
				.setTextAlignment(TextAlignment.CENTER)
				.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
				.add(new Paragraph(new Text(tableHeader[i])));
				
				table.addCell(cell);
			}
			
			int x = 0;
			for (String tc : tcIDs) {
				for (int i = 0; i < 5; i++) {
					Cell cell = new Cell();
					cell.setWidth(0.25f * document.getPdfDocument().getDefaultPageSize().getWidth());
					cell.setFontSize(9);
					cell.setTextAlignment(TextAlignment.LEFT);
					
					if (i < 5) {

						switch (i){
						case 0:
							cell.add(new Paragraph(new Text(tc)));
							break;
							
						case 1:
							cell.add(new Paragraph(new Text(shortDESCs.get(x))));
							break;
							
						case 2:
							cell.add(new Paragraph(new Text(testCASEs.get(x))));
							break;
							
						case 3:
							cell.add(new Paragraph(new Text(expectedRESULTs.get(x))));
							break;
							
						case 4:
							cell.add(new Paragraph(new Text(criterias.get(x))));
							break;
						}
					}
					table.addCell(cell);
				}
				x++;
			}
			
			
			return table;
		}
	
	private Table addInfo11TableTemplate(Document document, Table table, String dataFilesHeader, Style textStyle) {
		String[] tableHeader = {"TC ID", "Skenario Name", "Test Case", "Test Procedure/Test Step (Status)"};
		
		for (int i = 0; i < tableHeader.length; i++) {
			Cell cell = new Cell();
			cell
			.setFontSize(8)
			.setBackgroundColor(new DeviceRgb(147, 205, 220))
			.setBold()
			.setTextAlignment(TextAlignment.LEFT)
			.setVerticalAlignment(VerticalAlignment.MIDDLE)
			.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
			.add(new Paragraph(new Text(tableHeader[i])));
			
			table.addCell(cell);
		}
		
		for (int i = 0; i < 4; i++) {
			Cell cell = new Cell();
			cell.setWidth(0.25f * document.getPdfDocument().getDefaultPageSize().getWidth());
			cell.setFontSize(8);
			cell.setTextAlignment(TextAlignment.LEFT);
			
			if (i < 4) {

				switch (i){
				case 0:
					cell.add(new Paragraph(new Text(tcID)));
					break;
					
				case 1:
					cell.add(new Paragraph(new Text(shortDESC)));
					break;
					
				case 2:
					cell.add(new Paragraph(new Text(testCASE)));
					break;
					
				case 3:
					
					Paragraph title;
					int index = 0;
					for(Screenshot screenshot : this.screenshotList) {
						Div div = new Div().setKeepTogether(true);
	
						title = new Paragraph(String.valueOf(screenshot.getNameIndex()) + ". " + screenshot.getTitle() + " (" + screenshot.getStatus() + ")");
						Style titleStyle = new Style();
						titleStyle
						.setFontSize(9);
						title.addStyle(titleStyle);
						cell.add(title);
						
						if(index != this.screenshotList.size() - 1) {
							div.add(new Paragraph(new Text("\n")));
						}
						index++;
					}
					break;
				}
			}
			table.addCell(cell);
		}
		return table;
	}
	
	// multi test case
		private Table addInfo11TableTemplates(Document document, Table table, String dataFilesHeader, Style textStyle) {
			String[] tableHeader = {"TC ID", "Skenario Name", "Test Case", "Test Procedure/Test Step", "Status"};
			
			for (int i = 0; i < tableHeader.length; i++) {
				Cell cell = new Cell();
				cell
				.setFontSize(8)
				.setBackgroundColor(new DeviceRgb(147, 205, 220))
				.setBold()
				.setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setWidth(0.2f * (document.getPdfDocument().getDefaultPageSize().getWidth() - (document.getLeftMargin() + document.getRightMargin() + textStyle.getMarginLeft().getValue())))
				.add(new Paragraph(new Text(tableHeader[i])));
				
				table.addCell(cell);
			}
			
			int x = 0;
			for (String tc : tcIDs) {
				for (int i = 0; i < 5; i++) {
					Cell cell = new Cell();
					cell.setWidth(0.2f * document.getPdfDocument().getDefaultPageSize().getWidth());
					cell.setFontSize(8);
					cell.setTextAlignment(TextAlignment.LEFT);
					cell.setBorderBottom(Border.NO_BORDER);		//thomas
					
					if (i < 5) {

						switch (i){
						case 0:
							if(this.status.get(x) == "Passed" ) {
								cell.add(new Paragraph(new Text(tc).setFontColor(new DeviceRgb(18, 148, 6))));
							} else {
								cell.add(new Paragraph(new Text(tc).setFontColor(new DeviceRgb(245, 7, 7))));
							}
							table.addCell(cell);
							break;
							
						case 1:
							if(this.status.get(x) == "Passed" ) {
								cell.add(new Paragraph(new Text(shortDESCs.get(x)).setFontColor(new DeviceRgb(18, 148, 6))));
							} else {
								cell.add(new Paragraph(new Text(shortDESCs.get(x)).setFontColor(new DeviceRgb(245, 7, 7))));
							}
							table.addCell(cell);
							break;
							
						case 2:
							if(this.status.get(x) == "Passed" ) {
								cell.add(new Paragraph(new Text(testCASEs.get(x)).setFontColor(new DeviceRgb(18, 148, 6))));
							} else {
								cell.add(new Paragraph(new Text(testCASEs.get(x)).setFontColor(new DeviceRgb(245, 7, 7))));
							}
							table.addCell(cell);
							break;
							
						case 3:
							
//							Paragraph title;
//							int index = 0;
//							for(Screenshot screenshot : this.screenshotLists.get(x)) {
//								title = new Paragraph(String.valueOf(screenshot.getNameIndex()) + ". " + screenshot.getTitle());
//								Style titleStyle = new Style();
//								titleStyle.setFontSize(9);
//								title.addStyle(titleStyle);
//								cell.add(title);
//								
//								if(index != this.screenshotList.size() - 1) {
//									div.add(new Paragraph(new Text("\n")));
//								}
//								index++;
//							}
//							break;
							
							int indexScreenshot = 1;
							for(Screenshot screenshot : this.screenshotLists.get(x)) {
								Paragraph title = new Paragraph(String.valueOf(screenshot.getNameIndex()) + ". " + screenshot.getTitle());
								Cell cellTitle = new Cell();
								cellTitle.setFontSize(8);
								if(indexScreenshot == this.screenshotLists.get(x).size()) {
									cellTitle.setBorderBottom(Border.NO_BORDER);
								}
								cellTitle.add(title);
								table.addCell(cellTitle);
								
								Paragraph status = new Paragraph(screenshot.getStatus());
								Cell cellStatus = new Cell();
								cellStatus.setFontSize(8);
								if(screenshot.getStatus() == "Passed") {	
									cellStatus.setFontColor(new DeviceRgb(18, 148, 6));
								} else {
									cellStatus.setFontColor(new DeviceRgb(245, 7, 7));
								}
								
								if(indexScreenshot == this.screenshotLists.get(x).size()) {
									cellStatus.setBorderBottom(Border.NO_BORDER);
								}
								cellStatus.add(status);
								table.addCell(cellStatus);
								
								if(indexScreenshot != this.screenshotLists.get(x).size()) {
									Cell cellTcId = new Cell();
									cellTcId.setBorderTop(Border.NO_BORDER);
									cellTcId.setBorderBottom(Border.NO_BORDER);	//thomas
									table.addCell(cellTcId);
									
									Cell cellSC = new Cell();
									cellSC.setBorderTop(Border.NO_BORDER);
									cellSC.setBorderBottom(Border.NO_BORDER);	//thomas
									table.addCell(cellSC);

									Cell cellTC = new Cell();
									cellTC.setBorderTop(Border.NO_BORDER);
									cellTC.setBorderBottom(Border.NO_BORDER);	//thomas
									table.addCell(cellTC);
								}
								indexScreenshot++;
							}
							
							//thomas
							Cell cellTcId = new Cell();
							cellTcId.setBorderTop(Border.NO_BORDER);
							table.addCell(cellTcId);
							Cell cellSC = new Cell();
							cellSC.setBorderTop(Border.NO_BORDER);
							table.addCell(cellSC);
							Cell cellTC = new Cell();
							cellTC.setBorderTop(Border.NO_BORDER);
							table.addCell(cellTC);
							Cell cellTS = new Cell();
							cellTS.setBorderTop(Border.NO_BORDER);
							table.addCell(cellTS);
							Cell cellTSS = new Cell();
							cellTSS.setBorderTop(Border.NO_BORDER);
							table.addCell(cellTSS);
							
							break;
							
						case 4:
							
//							Paragraph status;
//							int indexStatus = 0;
//							for(Screenshot screenshot : this.screenshotLists.get(x)) {
//								Div div = new Div().setKeepTogether(true);
//			
//								status = new Paragraph(String.valueOf(screenshot.getNameIndex()) + ". " + screenshot.getStatus());
//								Style titleStyleStatus = new Style();
//								titleStyleStatus.setFontSize(9);
//								
//								
//								if(screenshot.getStatus() == "Passed") {	
//									titleStyleStatus.setFontColor(new DeviceRgb(18, 148, 6));
//								} else {
//									titleStyleStatus.setFontColor(new DeviceRgb(245, 7, 7));
//								}
//								
//								status.addStyle(titleStyleStatus);
//								cell.add(status);
//								
//								if(indexStatus != this.screenshotList.size() - 1) {
//									div.add(new Paragraph(new Text("\n")));
//								}
//								indexStatus++;
//							}
							break;
							
						}
					}
//					table.addCell(cell);
				}
				x++;
			}
			
			return table;
		}
}