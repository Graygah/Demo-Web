package reporting;

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.openqa.selenium.Rectangle;

public class Screenshot {
	private int nameIndex;
	private List<Screenshot> screenshotList;
	private String directory;
	private String fileName;
	private String title;
	private String info;
	private String status;
	
	// Constructor For Initialization on Test Case
	public Screenshot(String directory) {
		this.nameIndex = 1;
		this.screenshotList = new ArrayList<Screenshot>();
		this.directory = directory;
		this.fileName = this.directory + "/" + this.nameIndex + ".png";
	}
	
	// Constructor For Insert Into ReportList
	private Screenshot(int nameIndex, String fileName, String title, String info, String status) {
		this.nameIndex = nameIndex;
		this.fileName = fileName;
		this.title = title;
		this.info = info;
		this.status = status;
	}
	
	public int getNameIndex() {
		return this.nameIndex;
	}
	
	public List<Screenshot> getScreenshotList() {
		return this.screenshotList;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	// +1 on nameIndex
	private void addNameIndex() {
		this.nameIndex++;
		this.fileName = this.directory + "/" + this.nameIndex + ".png";
	}
	
	// Screenshot The Whole Mobile
	public void screenshot(String title, String info, String status) {
		try {
			MobileBuiltInKeywords.takeScreenshot(this.fileName);
			this.screenshotList.add(new Screenshot(this.nameIndex, this.fileName, title, info, status));
			addNameIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Screenshot The Whole Mobile with red Rectangle on Element
	public void screenshotAddRectangleOnElement(String title, String info, String status, Rectangle rectangle) {
		try {
			MobileBuiltInKeywords.takeScreenshot(this.fileName);
			BufferedImage fullImage = ImageIO.read(new File(this.fileName));
			Graphics2D image = fullImage.createGraphics();
			
			image.setColor(Color.RED);
			image.setStroke(new BasicStroke(5f));
			image.drawRect(rectangle.getX() - 5, rectangle.getY() - 5, rectangle.getWidth() + 10, rectangle.getHeight() + 10);
			
			ImageIO.write(fullImage, "png", new File(this.fileName));
			this.screenshotList.add(new Screenshot(this.nameIndex, this.fileName, title, info, status));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			addNameIndex();
		}
	}
	
	// Screenshot The Selected Element
	public void screenshotElement(String title, String info, String status, Rectangle rectangle) {
		try {
			MobileBuiltInKeywords.takeScreenshot(this.fileName);
			BufferedImage fullImage = ImageIO.read(new File(this.fileName));
			BufferedImage croppedImage = fullImage.getSubimage(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
			ImageIO.write(croppedImage, "png", new File(this.fileName));
			this.screenshotList.add(new Screenshot(this.nameIndex, this.fileName, title, info, status));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			addNameIndex();
		}
	}
}
