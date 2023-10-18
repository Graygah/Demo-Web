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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.delay(1)

WebUI.navigateToUrl('facebook.com')

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/input__email'), 'graygahroynando@ymail.com')

WebUI.delay(1)

WebUI.setEncryptedText(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/div__passContainer'), 
    'zkH85XJR6ydk8LJt3iH1Ig==')

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/button_Masuk'))

WebUI.delay(1)

WebUI.navigateToUrl('https://www.facebook.com/BRIofficialpage?mibextid=LQQJ4d')

WebUI.delay(3)

WebUI.sendKeys(null, Keys.chord(Keys.ESCAPE))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Facebook/Page_BANK BRI  Facebook/div_Message'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Facebook/Page_BANK BRI  Facebook/div_BANK BRI'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Facebook/Page_BANK BRI  Facebook/a_Open in Messenger'))

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Facebook/Page_Messenger  Facebook/div_Aa'), 'halo')

WebUI.delay(1)

WebUI.sendKeys(findTestObject('Object Repository/Facebook/Page_Messenger  Facebook/div_Aa'), Keys.chord(Keys.ENTER))

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Facebook/Page_Messenger  Facebook/div_Aa'), 'halo')

WebUI.delay(1)

WebUI.sendKeys(findTestObject('Object Repository/Facebook/Page_Messenger  Facebook/div_Aa'), Keys.chord(Keys.ENTER))

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Facebook/Page_Messenger  Facebook/div_Aa'), 'halo')

WebUI.delay(1)

WebUI.sendKeys(findTestObject('Object Repository/Facebook/Page_Messenger  Facebook/div_Aa'), Keys.chord(Keys.ENTER))