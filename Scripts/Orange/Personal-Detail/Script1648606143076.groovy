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

TestData input = findTestData('Data Files/Personal-Details')

WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/a_My Info'))
WebUI.delay(1)
WebUI.takeScreenshot()
WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/a_Personal Details'))
WebUI.delay(1)
WebUI.takeScreenshot()
WebUI.delay(1)
WebUI.takeScreenshot()

for (int baris=1; baris<= input.getRowNumbers(); baris++) 
{
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_firstName'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_firstName'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_firstName'), input.getValue('FirstName', baris))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_middleName'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_middleName'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_middleName'), input.getValue('MiddleName', baris))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_lastName'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_lastName'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Full Name_lastName'), input.getValue('LastName', baris))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Id_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Id_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_Employee Id_oxd-input oxd-input--active'), input.getValue('EmployeeID', baris))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Other Id_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Other Id_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_Other Id_oxd-input oxd-input--active'), input.getValue('OtherID', baris))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_concat(Driver, , s License Number)_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_concat(Driver, , s License Number)_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_concat(Driver, , s License Number)_oxd-input oxd-input--active'), input.getValue('LicenseNumber', baris))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_License Expiry Date_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_License Expiry Date_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_License Expiry Date_oxd-input oxd-input--active'), input.getValue('LicenseExp', baris))
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/input_SSN Number_oxd-input oxd-input--active'))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_SSN Number_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_SSN Number_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_SSN Number_oxd-input oxd-input--active'), input.getValue('SSNnumber', baris))
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_SIN Number_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_SIN Number_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_SIN Number_oxd-input oxd-input--active'), input.getValue('SINnumber', baris))
	WebUI.delay(1)
	WebUI.takeScreenshot()
	WebUI.scrollToElement(findTestObject('Object Repository/Orangen/Personal-Detail/input_SSN Number_oxd-input oxd-input--active'), 0)
//	WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/Status'))
//	WebUI.delay(1)
//	WebUI.click(findTestObject('Object Repository/Personal-Detail/div_'+input.getValue('MaritalStatus', baris)))
//	WebUI.delay(1)
//	WebUI.click(findTestObject('Object Repository/Personal-Detail/span_'+input.getValue('Gender', baris)))
//	WebUI.delay(1)
//	WebUI.click(findTestObject('null'))
//	WebUI.delay(1)
//	WebUI.click(findTestObject('Object Repository/Personal-Details/Nationality_'+input.getValue('Nationality', baris)))
//	WebUI.delay(1)
//	WebUI.sendKeys(findTestObject('null'), Keys.chord(Keys.CONTROL, 'a'))
//	WebUI.sendKeys(findTestObject('null'), Keys.chord(Keys.BACK_SPACE))
//	WebUI.delay(1)
//	WebUI.setText(findTestObject('null'), input.getValue('DateBirth', baris))
//	WebUI.delay(1)
//	WebUI.click(findTestObject('null'))
//	WebUI.delay(1)
//	WebUI.sendKeys(findTestObject('null'), Keys.chord(Keys.CONTROL, 'a'))
//	WebUI.sendKeys(findTestObject('null'), Keys.chord(Keys.BACK_SPACE))
//	WebUI.delay(1)
//	WebUI.setText(findTestObject('null'), input.getValue('NickName', baris))
//	WebUI.delay(1)
	WebUI.takeScreenshot()
	if(input.getValue('Smoker', baris) == 'Yes')
		{
			WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/label_Yes'))
			WebUI.delay(1)
			WebUI.takeScreenshot()
		}
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Military Service_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(findTestObject('Object Repository/Orangen/Personal-Detail/input_Military Service_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(1)
	WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/input_Military Service_oxd-input oxd-input--active'), input.getValue('MilitaryService', baris))
	WebUI.delay(1)
	WebUI.takeScreenshot()

	WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/button_Save'))
	WebUI.verifyTextPresent(input.getValue('verify', baris), false, FailureHandling.OPTIONAL)
	WebUI.delay(1)
	WebUI.takeScreenshot()
}