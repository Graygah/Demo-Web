import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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

TestData input = findTestData('Data Files/Custom-Fields')

WebUI.scrollToElement(findTestObject('Object Repository/Orangen/Personal-Detail/div_-- Select --'), 0)
WebUI.delay(1)
WebUI.takeScreenshot()

for (int baris=1; baris<= input.getRowNumbers(); baris++)
	{
		WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/div_-- Select --'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/div_'+input.getValue('BloodType', baris)))
		WebUI.delay(1)
		
		WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/button_Savee'))
		WebUI.verifyTextPresent(input.getValue('verify', baris), false, FailureHandling.OPTIONAL)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}