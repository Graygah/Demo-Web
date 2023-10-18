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

TestData input = findTestData('Data Files/Contact-Details')

WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/a_My Info'))
WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/Orangen/Contact-Detail/a_Contact Details'))

WebUI.delay(1)
WebUI.takeScreenshot()

for (int baris=1; baris<= input.getRowNumbers(); baris++)
	{
		
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Street 1_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Street 1_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_Street 1_oxd-input oxd-input--active'), input.getValue('Address1', baris))
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Street 2_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Street 2_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_Street 2_oxd-input oxd-input--active'), input.getValue('Address2', baris))
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_City_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_City_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_City_oxd-input oxd-input--active'), input.getValue('City', baris))
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_StateProvince_oxd-input oxd-input--focus'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_StateProvince_oxd-input oxd-input--focus'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_StateProvince_oxd-input oxd-input--focus'), input.getValue('Province', baris))
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_ZipPostal Code_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_ZipPostal Code_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_ZipPostal Code_oxd-input oxd-input--active'), input.getValue('PostalCode', baris))
		WebUI.delay(1)
//		WebUI.click(findTestObject('Object Repository/Orangen/Contact-Detail/div_-- Select --'))
//		WebUI.delay(1)
//		WebUI.click(findTestObject('Object Repository/Contact-Details/Country_'+input.getValue('Country', baris)))
//		WebUI.delay(1)
		WebUI.takeScreenshot()
		
//		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
//		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
//		WebUI.delay(1)
//		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work_oxd-input oxd-input--active'), input.getValue('HomeTelephone', baris))
//		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Mobile_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Mobile_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_Mobile_oxd-input oxd-input--active'), input.getValue('MobilePhone', baris))
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work_oxd-input oxd-input--active'), input.getValue('WorkTelephone', baris))
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work Email_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work Email_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_Work Email_oxd-input oxd-input--active'), input.getValue('WorkEmail', baris))
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Other Email_oxd-input oxd-input--active'), Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(findTestObject('Object Repository/Orangen/Contact-Detail/input_Other Email_oxd-input oxd-input--active'), Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Orangen/Contact-Detail/input_Other Email_oxd-input oxd-input--active'), input.getValue('OtherEmail', baris))
		WebUI.delay(1)
		WebUI.takeScreenshot()
		
		
		WebUI.click(findTestObject('Object Repository/Orangen/Contact-Detail/button_Save'))
		WebUI.verifyTextPresent(input.getValue('verify', baris), false, FailureHandling.OPTIONAL)
		WebUI.delay(1)
		WebUI.takeScreenshot()
	}
	