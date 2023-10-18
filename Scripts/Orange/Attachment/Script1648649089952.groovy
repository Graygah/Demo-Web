import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.configuration.RunConfiguration as RunCofiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

TestData input = findTestData('Data Files/Custom-Fields')

WebUI.delay(1)

WebUI.scrollToElement(findTestObject('Object Repository/Orangen/Personal-Detail/button_Add'), 0)

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/button_Add'))

WebUI.takeScreenshot()

for (int baris = 1; baris <= input.getRowNumbers(); baris++) {
    String userDir = RunCofiguration.getProjectDir()

//    WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/div_BrowseNo file selected'))

    WebUI.delay(1)

    WebUI.uploadFile(findTestObject('Object Repository/Orangen/Personal-Detail/upload'), userDir + GlobalVariable.pathfoto)

    WebUI.delay(1)

    WebUI.setText(findTestObject('Object Repository/Orangen/Personal-Detail/textarea_Comment_oxd-textarea oxd-textarea--focus oxd-textarea--resize-vertical'), 
        input.getValue('comment', baris))

    WebUI.delay(1)

    WebUI.takeScreenshot()

    WebUI.click(findTestObject('Object Repository/Orangen/Personal-Detail/button_Saveee'))

    WebUI.verifyTextPresent(input.getValue('verify1', baris), false, FailureHandling.OPTIONAL)

    WebUI.delay(1)

    WebUI.takeScreenshot()
}