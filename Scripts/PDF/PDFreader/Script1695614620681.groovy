import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.rmi.server.LoaderHandler

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
import internal.GlobalVariable

import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.openqa.selenium.Keys as Keys

//String URL = WebUI.getUrl()


File pdf = new File("PDF//manual-qa-ebook-jan-2023.pdf");

PDDocument document = Loader.loadPDF(pdf);

println(document)

PDFTextStripper pdfTextStripper = new PDFTextStripper()

pdfTextStripper.setStartPage(1)

pdfTextStripper.setEndPage(3)

String text = pdfTextStripper.getText(document)

print(text)

if(text.contains("\nFor instance, e-commerce platforms work on multiple operating \nsystems using the same backend. Every time the platform is ")) {
	println("ada")
} else { 
	println("gak ada")
}