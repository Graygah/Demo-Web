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
import groovy.lang.*
import java.lang.Runtime as Runtime
import java.lang.Process as Process
import java.lang.ProcessBuilder as ProcessBuilder
import java.lang.String as String
import java.lang.System as System
import java.io.BufferedReader as BufferedReader
import java.io.InputStreamReader as InputStreamReader
import java.io.IOException as IOException
import java.util.stream.Collectors as Collectors

//path file install python
String executable = '"C://Python310//python.exe"'

//path project python
String pyscript = 'readimage.py'

try {
	ProcessBuilder processBuilder = new ProcessBuilder(executable, pyscript)

	//processBuilder.redirectErrorStream(true)
	Process process = processBuilder.start()

	List<String> results = readProcessOutput(process.getInputStream())

	assert results != null : 'Results should not be empty'

	List<String> errors = readProcessOutput(process.getErrorStream())

	int exitCode = process.waitFor()

	println("exitCode=$exitCode")

	results.forEach({ def line ->
			println(line)
			if(line.contains('JURNAL')) {
				println("YES")
				WebUI.verifyMatch('JURNAL', 'JURNAL', false)
			} else {
				println("NO")
			}
//			GlobalVariable.Line = line.toString()
		})

	if ((errors != null) && (errors.size() > 0)) {
		println('STDERR:')

		errors.forEach({ def line ->
				println(line)
			})
	}
}
catch (IOException e) {
	e.printStackTrace()
}

private List<String> readProcessOutput(InputStream inputStream) throws IOException {
	BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))

	return output.lines().collect(Collectors.toList())
}