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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import java.time.LocalDateTime
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.testobject.WindowsTestObject.LocatorStrategy
import reporting.Initialization
import reporting.ReportData
import internal.GlobalVariable
import reporting.Report
import reporting.Screenshot

//=========================================================================================================================//
//Template Report Design//
//=========================================================================================================================//
LocalDateTime endDate

Report report

Initialization initialization = new Initialization()

LocalDateTime startDate = LocalDateTime.now()

String story = 'IOS BNI MOBILE BANKING'

String tcId = 'TC-CATEW-664'

String shortDesc = 'Normal Transaksi TopUp Danadari Menu Lengkap'

String testCase = 'Nasabah Melakukan Transaksi E-Wallet Dana (critical)'

String expectedResult = 'Transaksi berhasil'

String criteria = 'Normal'

String tempFolder = RunConfiguration.getProjectDir() + '/Data Files/Image/Temp'

Screenshot screenshot = new Screenshot(tempFolder)

ReportData reportData = new ReportData(startDate, screenshot, story, tcId, shortDesc, testCase, expectedResult, criteria, 
tempFolder)

//runscript

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.saucedemo.com/')

//long startTime = System.nanoTime()
//
//long endTime = System.nanoTime()
//
//println(endTime)
//
//long responseTime = (endTime - startTime) / 1000000
//
//println(responseTime)
//
//double responseTimeInSecond = ((responseTime) as double) / 1000000000
//
//println(responseTimeInSecond)
WebUI.maximizeWindow()

//TestData input = findTestData('Data Files/Login-SauceDemo')
//for (int baris = 1; baris <= input.getRowNumbers(); baris++) {
WebUI.setText(findTestObject('Object Repository/Sauce/Login-SauceDemo/Input_Username'), GlobalVariable.usersauce)

WebUI.setText(findTestObject('Object Repository/Sauce/Login-SauceDemo/Input_Password'), GlobalVariable.passsauce)

WebUI.click(findTestObject('Object Repository/Sauce/Login-SauceDemo/Button_Login'))

//
//WebUI.takeScreenshot()
//
//
WebUI.waitForElementPresent(findTestObject('Object Repository/Sauce/Login-SauceDemo/Div_Products'), 0)

WebUI.takeScreenshot()
WebUI.closeBrowser()
Report.createReport(LocalDateTime.now(), reportData)