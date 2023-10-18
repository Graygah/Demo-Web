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
import com.kms.katalon.core.testobject.ConditionType as ConditionType

TestData input = findTestData('Data Files/01-Omni Notes')

Mobile.startApplication('D:\\apk mobile\\Omni Notes_v6.1.0_apkpure.com.apk', false)
//String app_id = GlobalVariable.app_url

//Mobile.startApplication(app_id, false)

for (int baris = 1; baris <= input.getRowNumbers(); baris++) {
    if (Mobile.verifyElementVisible(findTestObject('Object Repository/Omni Notes/android.widget.Button - Allow'), 15, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.Button - Allow'), 0)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('Object Repository/Omni Notes/android.widget.buttonNext'), 15, FailureHandling.OPTIONAL)) {
        boolean next = true

        while (next == true) {
            Mobile.delay(1)

            Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.buttonNext'), 0)

            next = Mobile.verifyElementVisible(findTestObject('Object Repository/Omni Notes/android.widget.buttonNext'), 
                15, FailureHandling.OPTIONAL)
        }
    }
    
    Mobile.delay(1)

    Mobile.takeScreenshot('D:\\Halaman Utama.png')

    Mobile.delay(1)

    Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.ImageButtonTambah'), 0)

    Mobile.delay(1)

    Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.ImageButtonTextNote'), 0)

    Mobile.delay(1)

    Mobile.takeScreenshot()

    //    Mobile.delay(1)
    //    Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.ImageButtonCamera'), 0, FailureHandling.OPTIONAL)
    //    Mobile.tapAtPosition(554, 1978)
    //
    //    Mobile.delay(3)
    //
    //    Mobile.tapAtPosition(554, 1978)
    //    Mobile.tap(findTestObject('Object Repository/Omni Notes/android.view.View button camera emu'), 0, FailureHandling.OPTIONAL)
    //    Mobile.waitForElementPresent(findTestObject('Object Repository/Omni Notes/android.widget.ImageButton Check'), 2, FailureHandling.OPTIONAL)
    //
    //    Mobile.waitForElementPresent(findTestObject('Object Repository/Omni Notes/android.widget.ImageButton done'), 2, FailureHandling.OPTIONAL)
    //
    //    Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.Button - OK'), 2, FailureHandling.OPTIONAL)
    //
    //    Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.ImageButton done'), 2, FailureHandling.OPTIONAL)
    //
    //    Mobile.delay(1)
    //
    //    Mobile.takeScreenshot()
    Mobile.delay(1)

    Mobile.setText(findTestObject('Object Repository/Omni Notes/android.widget.EditText - Title'), input.getValue('title', 
            baris), 0)

    Mobile.delay(1)

    Mobile.setText(findTestObject('Object Repository/Omni Notes/android.widget.EditText - Content'), input.getValue('content', 
            baris), 0)

    Mobile.delay(1)

    Mobile.takeScreenshot()

    Mobile.delay(1)

    Mobile.tap(findTestObject('Object Repository/Omni Notes/android.widget.ImageButtonBack'), 0)
}