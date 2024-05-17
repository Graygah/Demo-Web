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
import org.apache.poi.ss.usermodel.Sheet as Sheet
import org.apache.poi.ss.usermodel.Workbook as Workbook
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.testobject.ConditionType as ConditionType

TestData input = findTestData('Data Files/Checkout')

for (int baris = 1; baris <= 1; baris++) {
    WebUI.delay(1)

    for (int barang = 1; barang <= input.getRowNumbers(); barang++) {
        TestObject button = new TestObject().addProperty('xpath', ConditionType.EQUALS, "/html/body/div/div/div/div[2]/div/div/div/div[$barang]/div[2]/div[2]/button")

        WebUI.click(button)
    }
    
    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/a_1'))

    WebUI.delay(1)

    String Data = './Excel/Sauce-checkout.xlsx'

    Workbook excel = ExcelKeywords.getWorkbook(Data)

    Sheet sheet = excel.getSheet('Sheet1')

    for (int value = 1; value <= input.getRowNumbers(); value++) {
        TestObject object = new TestObject().addProperty('xpath', ConditionType.EQUALS, "/html/body/div/div/div/div[2]/div/div[1]/div[$value+2]/div[2]/div[2]/div")

        String price = WebUI.getText(object)

        println(price)

        for (int kol = 5; kol <= 5; kol++) {
            ExcelKeywords.setValueToCellByIndex(sheet, value, kol, price)

            ExcelKeywords.saveWorkbook(Data, excel)
        }
    }
    
    for (int bar = 1; bar <= input.getRowNumbers(); bar++) {
        isbreak = false

        Exp = input.getValue('expected result', bar)

        for (int row = 1; row <= input.getRowNumbers(); row++) {
            Res = input.getValue('actual result', row)

            if (Exp == Res) {
                for (int brs = bar; brs <= bar; brs++) {
                    for (int kol = 6; kol <= 6; kol++) {
                        ExcelKeywords.setValueToCellByIndex(sheet, brs, kol, 'pass')

                        ExcelKeywords.saveWorkbook(Data, excel)
                    }
                }
                
                isbreak = true

                break //
            } //        TestObject object = new TestObject().addProperty('xpath', ConditionType.EQUALS, "/html/body/div/div/div/div[2]/div/div[1]/div[$bar+2]/div[2]/div[2]/div")
            else {
                for (int brs = bar; brs <= bar; brs++) {
                    for (int kol = 6; kol <= 6; kol++) {
                        ExcelKeywords.setValueToCellByIndex(sheet, brs, kol, 'fail')

                        ExcelKeywords.saveWorkbook(Data, excel)
                    }
                }
            }
        }
    }
    
    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/button_Checkout'))

    WebUI.delay(1)

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_lastName'), input.getValue(
            'lastname', baris))

    WebUI.delay(1)

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_postalCode'), input.getValue(
            'postalcode', baris))

    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/button_continue'))

    WebUI.verifyTextPresent('Error: First Name is required', false, FailureHandling.OPTIONAL)

    WebUI.takeScreenshot()

    WebUI.refresh()

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_firstName'), input.getValue(
            'firstname', baris))

    WebUI.delay(1)

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_postalCode'), input.getValue(
            'postalcode', baris))

    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/button_continue'))

    WebUI.verifyTextPresent('Error: Last Name is required', false, FailureHandling.OPTIONAL)

    WebUI.takeScreenshot()

    WebUI.refresh()

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_firstName'), input.getValue(
            'firstname', baris))

    WebUI.delay(1)

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_lastName'), input.getValue(
            'lastname', baris))

    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/button_continue'))

    WebUI.verifyTextPresent('Error: Postal Code is required', false, FailureHandling.OPTIONAL)

    WebUI.takeScreenshot()

    WebUI.refresh()

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_firstName'), input.getValue(
            'firstname', baris))

    WebUI.delay(1)

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_lastName'), input.getValue(
            'lastname', baris))

    WebUI.delay(1)

    WebUI.setText(findTestObject('Object Repository/Sauce/Checkout/input_Checkout Your Information_postalCode'), input.getValue(
            'postalcode', baris))

    WebUI.takeScreenshot()

    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/button_continue'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/button_Finish'))

    WebUI.delay(1)

    WebUI.takeScreenshot()

    WebUI.verifyTextPresent(input.getValue('message', baris), false, FailureHandling.OPTIONAL)

    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Sauce/Checkout/button_Back Home'))
}