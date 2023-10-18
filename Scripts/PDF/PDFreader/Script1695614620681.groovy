import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.rmi.server.LoaderHandler as LoaderHandler
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
import com.sun.org.apache.xml.internal.resolver.helpers.FileURL as FileURL
import internal.GlobalVariable as GlobalVariable
import org.apache.ivy.plugins.resolver.util.FileURLLister as FileURLLister
import org.apache.pdfbox.Loader as Loader
import org.apache.pdfbox.pdmodel.PDDocument as PDDocument
import org.apache.pdfbox.text.PDFTextStripper as PDFTextStripper
import org.openqa.selenium.Keys as Keys

//String URL = WebUI.getUrl()
File pdf = WebUI.navigateToUrl('https://staffnew.uny.ac.id/upload/197712152010122002/pengabdian/jurnal-ilmiah-hima-ian.pdf')

PDDocument document = pdf

println(document)

PDFTextStripper pdfTextStripper = new PDFTextStripper()

pdfTextStripper.setStartPage(2)

pdfTextStripper.setEndPage(2)

String text = pdfTextStripper.getText(document)

println(text)