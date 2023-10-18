import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.swing.plaf.basic.BasicButtonListener.Actions

import com.google.common.collect.FilteredEntryMultimap.Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.click(findTestObject('Object Repository/Orangen/Logout/span_paul mambo'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Orangen/Logout/a_Logout'))

WebUI.delay(1)
//
//Actions action = new Actions(driver)
//
//action.sendKeys(Keys.ESCAPE)
//
//WebUI.delay(3)

WebUI.closeBrowser()