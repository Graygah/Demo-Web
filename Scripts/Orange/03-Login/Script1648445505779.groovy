import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.ConcurrentHashMap.KeySetView

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration as RunCofiguration

TestData input = findTestData('Data Files/Demo Web')

for (int baris=1; baris<= input.getRowNumbers(); baris++)
{
	WebUI.openBrowser('')
	
	WebUI.navigateToUrl(GlobalVariable.url)
	
	WebUI.maximizeWindow()
	
	WebUI.delay(2)
	
	WebUI.setText(findTestObject('null'), input.getValue('username', baris))
	
	WebUI.delay(2)
	
	WebUI.setText(findTestObject('null'), input.getValue('password', baris))
	
	WebUI.delay(2)
	
	WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/button_Login'))
	
	WebUI.delay(2)
	
	WebUI.click(findTestObject('null'))
		
	WebUI.delay(2)
}

TestData data = findTestData('Data Files/Emplee')

for (int row=1; row<= data.getRowNumbers(); row++)
{
	
	WebUI.click(findTestObject('null'))
	
	WebUI.delay(2)

	
	WebUI.setText(findTestObject('null'), data.getValue('firstname', row))
	
	WebUI.delay(2)
	
	WebUI.setText(findTestObject('null'), data.getValue('middlename', row))
	
	WebUI.delay(2)
	
	WebUI.setText(findTestObject('null'), data.getValue('lastname', row))
	
	WebUI.sendKeys(findTestObject('null'), Keys.chord(Keys.CONTROL,'a'))
	
	WebUI.delay(2)
	
	WebUI.sendKeys(findTestObject('null'), Keys.chord(Keys.BACK_SPACE))
	
	WebUI.delay(2)
	
	WebUI.setText(findTestObject('null'), data.getValue('id', row))
	
	WebUI.delay(2)
	
	String userDir = RunCofiguration.getProjectDir()
	
	WebUI.uploadFile(findTestObject('null'), userDir + GlobalVariable.pathfoto)
	
	if(data.getValue('CreateLogin', row) == 'Yes')
	{
		WebUI.click(findTestObject('null'))
		
		WebUI.delay(2)
		
		WebUI.setText(findTestObject('null'), data.getValue('username', row))
		
		WebUI.delay(2)
		
		WebUI.setText(findTestObject('null'), data.getValue('password', row))
		
		WebUI.delay(2)
		
		WebUI.setText(findTestObject('null'), data.getValue('password', row))
		
		WebUI.delay(2)
		
		WebUI.click(findTestObject('null'))
		
		WebUI.delay(2)
		
		WebUI.click(findTestObject('Object Repository/Add-Employee/Button_'+data.getValue('status', row)))
		
	}
	
	
	 WebUI.delay(2)
	 
	 WebUI.click(findTestObject('null'))
	 
}