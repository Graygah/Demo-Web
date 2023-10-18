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

TestData input = findTestData('Data Files/Demo Web')

for (int baris=1; baris<= input.getRowNumbers(); baris++)
{
	WebUI.openBrowser('')
	WebUI.navigateToUrl(GlobalVariable.url)
	WebUI.maximizeWindow()
	WebUI.delay(2)
	WebUI.takeScreenshot()
	WebUI.setText(findTestObject('Object Repository/Orangen/Login/input_Username_username'), input.getValue('username', baris))
	WebUI.delay(2)
	WebUI.setText(findTestObject('Object Repository/Orangen/Login/input_Password_password'), input.getValue('password', baris))
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/button_Login'))
	WebUI.delay(1)
	WebUI.verifyTextPresent(input.getValue('verify', baris), false, FailureHandling.OPTIONAL)
	WebUI.takeScreenshot()
	WebUI.delay(1)
	
	/*
	 * WebUI.click(findTestObject('Object Repository/Orangen/Add-Admin/b_Admin'))
	 * 
	 * WebUI.delay(4)
	 * 
	 * WebUI.click(findTestObject('Object Repository/Orangen/Add-Admin/Button_add'))
	 * 
	 * WebUI.delay(2)
	 * 
	 * WebUI.setText(findTestObject('Object
	 * Repository/Add-Admin/input__systemUseremployeeNameempName'),
	 * input.getValue('employeeName', baris))
	 * 
	 * WebUI.delay(2)
	 * 
	 * WebUI.click(findTestObject('Object Repository/Orangen/Add-Admin/strong_Admin A'))
	 * 
	 * WebUI.delay(2)
	 * 
	 * WebUI.setText(findTestObject('Object Repository/Orangen/Add-Admin/Input_Username'),
	 * input.getValue('user', baris))
	 * 
	 * WebUI.delay(2)
	 * 
	 * WebUI.setText(findTestObject('Object Repository/Orangen/Add-Admin/Input_Password'),
	 * input.getValue('password', baris))
	 * 
	 * WebUI.delay(2)
	 * 
	 * WebUI.setText(findTestObject('Object
	 * Repository/Add-Admin/Input_ConfirmPassword'), input.getValue('password',
	 * baris))
	 * 
	 * WebUI.delay(2)
	 * 
	 * 
	 * WebUI.click(findTestObject('Object Repository/Orangen/Add-Admin/Button_Save'))
	 * 
	 * WebUI.delay(1)
	 * 
	 * WebUI.verifyTextPresent(input.getValue('verify', baris), false)
	 */
}