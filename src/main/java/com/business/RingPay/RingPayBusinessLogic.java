package com.business.RingPay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.android.RingPayPages.AddAddresPage;
import com.android.RingPayPages.AddAddressPage;
import com.android.RingPayPages.FeeDetailsPage;
import com.android.RingPayPages.GmailLoginPage;
import com.android.RingPayPages.HomPageNew;
import com.android.RingPayPages.HomePage;
import com.android.RingPayPages.InstaLoanPage;
import com.android.RingPayPages.KailashApp;
import com.android.RingPayPages.KycDocument;
import com.android.RingPayPages.MerchantOfferPage;
import com.android.RingPayPages.MobileLoginPage;
import com.android.RingPayPages.OfferPage;
import com.android.RingPayPages.PAN_DetailsPage;
import com.android.RingPayPages.PayEarlyPaymentPage;
import com.android.RingPayPages.PaymentPage;
import com.android.RingPayPages.PermissionPage;
import com.android.RingPayPages.PromoCodeOfferPage;
import com.android.RingPayPages.RingLoginPage;
import com.android.RingPayPages.RingPayMerchantFlowPage;
import com.android.RingPayPages.RingPayMerchantFlowPage_New;
import com.android.RingPayPages.RingPromoCodeLogin;
import com.android.RingPayPages.RingUserDetailPage;
import com.android.RingPayPages.SignUP_LoginPage;
import com.android.RingPayPages.TermsAndConditionPage;
import com.android.RingPayPages.TestingPortalWebPage;
import com.android.RingPayPages.TransactionPIN;
import com.android.RingPayPages.UserRegistrationNew;
import com.android.RingPayPages.UserRegistrationPage;
import com.android.RingPayPages.BankTransferModule;
import com.android.RingPayPages.DigiWeb;
import com.driverInstance.CommandBase;
import com.epam.ta.reportportal.ws.annotations.In;
import com.excel.ExcelFunctions;
import com.extent.ExtentReporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class RingPayBusinessLogic extends Utilities {

	public RingPayBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	}

	RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();
	private int timeout;
	
	public long age;
	private String keyLogin;
	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String mothersName;
	private String email;
	private String gender;
	private String year;
	private String monthByName;
	private String date;
	private String panCard;
	private String aadharFrontImage;
	private String aadharBackImage;
	private String panImage;
	private String middleName;
	
	SoftAssert softAssertion = new SoftAssert();
	boolean launch = "" != null;
	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
	static LoggingUtils logger = new LoggingUtils();

	/** Test data from property file. */
	public static PropertyFileReader prop=new PropertyFileReader(".\\properties\\testData.properties");
	
	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;
	public String amount[] = { "1001", "0", "1" };
	public static boolean relaunchFlag = false;
	public static boolean appliTools = false;
	public String noTxt;
	public static boolean PopUp = false;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;

	}

	/**
	 * Initiate Property File.
	 *
	 * @param byLocator the by locator
	 */

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void TearDown() {
		logger.info("App tear Down");
		getDriver().quit();
	}
//=============================================User Play Store flow Start===============================================================
	public void User_Play_Store_Flow(String key, String permission) {
		extent.HeaderChildNode("User Play store Flow Module");
try {
		switch (permission) {
		case "Let'sRing":
			extent.extentLogger("PASS","TC_Ring_Core_01 - To Verify the Login screen when user opens the app by clicking on App Icon");
			extent.extentLogger("PASS","TC_Ring_Core_190 - To Verify if scanner requires Camera permission");
			extent.extentLoggerPass("TC_Ring_Core_02","TC_Ring_Core_02 - To verify When User selects Enable Permission option");
			break;
		case "MobileNo":
				enablePermissions();
			break;
		default:
			break;
			
		}

		switch (key) {
		case "make Payment":
				waitTime(4000);
				verifyElementPresent(RingLoginPage.objMakePaymentLetsRingItBtn, "Make Payment page");
				click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
				//click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
				click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
				type1(RingPayMerchantFlowPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
				verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn,getText(RingLoginPage.objMakePaymentPageProceedBtn));
			break;
		case "MobileNo":
			Aclick(RingLoginPage.objLoginLink,"Sign up login link");
			break;
		default:
			break;
			
		}
		userPlayStoreLoginFlow(prop.getproperty("lessThanTenMob"), prop.getproperty("moreThanTenMob"), prop.getproperty("specialCharMob"), prop.getproperty("spaceMob"), prop.getproperty("validMob"), prop.getproperty("invalidOtp"), prop.getproperty("lessOtp"));
	}
	catch(Exception e) {
		e.printStackTrace();
}
	}
	public void userPlayStoreLoginFlow(String lessThanTenMob, String moreThanTenMob, String specialCharMob, String spaceMob, String validMob, String invalidOtp, String lessOtp) throws Exception {
		
		explicitWaitVisibility(RingLoginPage.objLoginMobile,10);
		String mobileTxt = getText(RingLoginPage.objLoginMobile);
		String googleTxt = getText(RingLoginPage.objLoginGoogle);
		String facebookTxt = getText(RingLoginPage.objLoginFacebook);
		//String termsTxt = getText(RingLoginPage.objTermsLink_PrivacyFooter);

		softAssertion.assertEquals(mobileTxt, "Continue with Mobile");
		softAssertion.assertEquals(googleTxt, "Continue with Google");
		softAssertion.assertEquals(facebookTxt, "Continue with Facebook");

		extent.extentLoggerPass("TC_Ring_Core_03","TC_Ring_Core_03 - To verify User Selects signup/Login option under Don't have a QR Code?");

		loginMobile();
		extent.extentLoggerPass("TC_Ring_Core_04", "TC_Ring_Core_04 - To Verify when user Continue with mobile option");
		extent.extentLoggerPass("TC_Ring_Core_05", "TC_Ring_Core_05 - To Verify the Verify mobile screen");
		
		explicitWaitVisibility(RingLoginPage.objCountryCode,10);
		extent.extentLoggerPass("TC_Ring_Core_06", "TC_Ring_Core_06 - To Verify user should able to see  \"Enter your mobile number\" followed by +91.");
		
		logger.info("Verify mobile number with <10 digits");
		mobileNoValidation2(lessThanTenMob);
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		String errorMsg = getText(RingLoginPage.objMobError);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_07","TC_Ring_Core_07 - To Verify User enter mobile number less than 10 digit & Click on Proceed Button");
		
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		logger.info("Verify mobile number with >10 digits");
		mobileNoValidation2(moreThanTenMob);
		String otpAutoRead = getText(RingLoginPage.OtpAutoRead);
		softAssertion.assertEquals(otpAutoRead, "Auto Reading OTP");
		extent.extentLoggerPass("TC_Ring_Core_08","TC_Ring_Core_08 - To Verify User enter mobile number more than 10 digit");
		Back(1);
		logger.info("Verify mobile number with special characters");
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		mobileNoValidation2(specialCharMob);
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_10","TC_Ring_Core_10 - To Verify User tries enter punctuations or special character in field");

		logger.info("Verify mobile number with alphabets");
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		mobileNoValidation2("8712aa");
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_09","TC_Ring_Core_09 - To Verify User tries enter alphabets in field");
		
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		logger.info("Verify mobile number with space in between");
		mobileNoValidation2(spaceMob);
		explicitWaitVisibility(RingLoginPage.objMobError, 10);
		softAssertion.assertEquals(errorMsg, " Please enter a valid mobile number");
		extent.extentLoggerPass("TC_Ring_Core_11","TC_Ring_Core_11 - To Verify User tries enter space between the number");

		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		
		logger.info("Verify mobile number with entering valid number");
		mobileNoValidation2(validMob);
		explicitWaitVisibility(RingLoginPage.OtpAutoRead, 10);
		extent.extentLoggerPass("TC_Ring_Core_15", "TC_Ring_Core_15 - To Verify OTP  auto reading time");
		
		softAssertion.assertEquals(otpAutoRead, "Auto Reading OTP");
		extent.extentLoggerPass("TC_Ring_Core_12", "TC_Ring_Core_12 - To verify User tries enters mobile number without country code.");
		extent.extentLoggerPass("TC_Ring_Core_13", "TC_Ring_Core_13 - To Verify User tries enter valid mobile number");

		
		WebElement resendOtp = findElement(RingLoginPage.resendOtpTxt);
		String clickable = getAttributValue("clickable", RingLoginPage.resendOtpTxt);
		softAssertion.assertEquals("false", clickable);
		extent.extentLoggerPass("TC_Ring_Core_17","TC_Ring_Core_17 - To Verify the text given below the OTP number box when the timer is in progress");

		explicitWaitClickable(RingLoginPage.resendOtpTxt, 10);
		extent.extentLoggerPass("TC_Ring_Core_18","TC_Ring_Core_18 - To Verify the text given below the OTP number box when the timer is completed.");

		String focused_before = getAttributValue("focused", RingLoginPage.objOtpTxtField1);
		System.out.println(focused_before);
		softAssertion.assertEquals("false", focused_before);
		extent.extentLoggerPass("TC_Ring_Core_19","TC_Ring_Core_19 - To Verify the OTP number box behaviour when the timer is started.");

		explicitWaitVisibility(RingLoginPage.resendOtpTxt, 10);
		String focused_after = getAttributValue("clickable", RingLoginPage.objOtpTxtField1);
		System.out.println(focused_after);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		softAssertion.assertEquals("true", focused_after);
		extent.extentLoggerPass("TC_Ring_Core_20","TC_Ring_Core_20 - To Verify the OTP number box behaviour when the timer is completed.");

		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, invalidOtp, "Enter OTP");
		explicitWaitVisibility(RingLoginPage.OtpError, 10);
		logger.info("OTP Error message");
		String otpErrorTxt = getText(RingLoginPage.OtpError);
		softAssertion.assertEquals(otpErrorTxt, "Please enter a valid OTP");
		extent.extentLoggerPass("TC_Ring_Core_21", "TC_Ring_Core_21 - To Verify User enter invalid OTP");

		clearField(RingLoginPage.objOtpTxtField1, "Enter OTP");
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, lessOtp, "Enter OTP");
		boolean Otp_flag = verifyElementNotPresent(RingLoginPage.OtpError, 10);
		softAssertion.assertEquals(false, Otp_flag);
		extent.extentLoggerPass("TC_Ring_Core_24","TC_Ring_Core_24 - To Verify if user enters less than 6 digit number");

		explicitWaitClickable(RingLoginPage.resendOtpTxt, 10);
		extent.extentLoggerPass("TC_Ring_Core_25", "TC_Ring_Core_25 - To Verify Resend OTP should clickable");
		clearField(RingLoginPage.objOtpTxtField1, "Enter OTP");

		waitTime(3000);
		Back(1);
		Back(1);

		waitTime(3000);
		clearField(RingLoginPage.objMobTextField, "Mobile Text Field");
		String blockMobileNo = "9" + RandomIntegerGenerator(9);
		mobileNoValidation2(blockMobileNo);

		
		explicitWaitVisibility(MobileLoginPage.txtResendOtp, 20);
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 1 Time");
		String getAutoreadValidation = getText(MobileLoginPage.txtAutoReadingOTP);
		
		softAssertion.assertEquals("Auto Reading OTP", getAutoreadValidation);
		logger.info("Auto Reading OTP Validate " + getAutoreadValidation);
		extent.extentLoggerPass("Auto Reading OTP", "Auto Reading OTP Validate Successfully");
		/*explicitWaitVisibility(MobileLoginPage.txtOtpTimeStamp, 20);
		String otpTimeStamp = getText(MobileLoginPage.txtOtpTimeStamp);
		otpTimeStamp = otpTimeStamp.substring(0, 5);
		System.out.println("OTP Time Stamp::" + otpTimeStamp);*/
		waitTime(1000);
		String otpBoxDisable = getAttributValue("focused", MobileLoginPage.enterOTPNumberFiled);
		System.out.println("Element Focus:: " + otpBoxDisable);
		//softAssertion.assertEquals(" 00:0", otpTimeStamp);
		if (otpBoxDisable.equals("false")) {
			softAssertion.assertEquals("false", otpBoxDisable);
			logger.info("OTP Box Disable");
			extent.extentLoggerPass("OTP Box Disable", "OTP Box Disable");
		} else {
			logger.info("OTP Box Enabled");
			extent.extentLoggerFail("OTP Box Enable", "OTP Box Enabled");
		}

		extent.extentLoggerPass("TC_Ring_Core_26", "TC_Ring_Core_26 - To Verify Resend OTP option");

		// Attemp 2
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 2 Time");
		
		String getAutoreadValidation1 = getText(MobileLoginPage.txtAutoReadingOTP);
		softAssertion.assertEquals("Auto Reading OTP", getAutoreadValidation1);
		logger.info("Auto Reading OTP Validate " + getAutoreadValidation1);
		extent.extentLoggerPass("Auto Reading OTP", "Auto Reading OTP Validate Successfully");

		// Attemp 3
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 3 Time");

		// Attemp 4
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 4 Time");
		

		// Attemp 5
		explicitWaitVisibility(MobileLoginPage.txtResendOtp,20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, RandomIntegerGenerator(6), "Enter OTP");
		verifyElementPresentAndClick(MobileLoginPage.txtResendOtp, "Resend Button 5 Time");
		if (verifyElementDisplayed(MobileLoginPage.txtResendOtp)) {
			Aclick(MobileLoginPage.txtResendOtp, "Resend Button 5 Time");
		}

		explicitWaitVisibility(RingLoginPage.txtBlockPhoneNoPopupMessage, 20);
		String popupMessageValidationOfBlockNumber = getText(RingLoginPage.txtBlockPhoneNoPopupMessage);
		logger.info("Expected: " + popupMessageValidationOfBlockNumber);
		softAssertion.assertEquals("Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.",popupMessageValidationOfBlockNumber);
		logger.info("Temprory Block Your Number For 2 Minutes Validated PopUp Message");
		extent.extentLoggerPass("PopUp Of Block User For 2 Minutes","Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.");
		Aclick(RingLoginPage.btnOkGotIt, "Button Ok, Got It!");
		//Aclick(KailashApp.btnOkGotItOtp,"Ok got it button");
//		trueCallerPopup();
		//getDriver().closeApp();
		extent.extentLoggerPass("TC_Ring_Core_34","TC_Ring_Core_34 To verify when user click Skip/Cancel option on truecaller popup ");
		extent.extentLoggerPass("TC_Ring_Core_27","TC_Ring_Core_27 To Verify user enters wrong otp and attempts for 5th time  by clicking on Resend button ");
		waitTime(4000);
		// TC30
		explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 20);
		String verifyMoblieNumberEnter = getText(RingLoginPage.objVerifyMobHeader);
		softAssertion.assertEquals("Verify Mobile", verifyMoblieNumberEnter);
		logger.info(verifyMoblieNumberEnter);
		extent.extentLoggerPass("Verify Mobile Page", "Verify Mobile Page is visible");
		// logger.info("TC 30----------------->PASSED");
		
		waitTime(4000);
		Aclick(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, blockMobileNo, "Mobile text field");
		System.out.println(blockMobileNo);
		waitTime(5000);
		Aclick(RingLoginPage.objOkGotitBtn, "Button Ok, Got It!");
		//Aclick(KailashApp.btnOkGotItLogin,"Ok got it button");
		extent.extentLoggerPass("TC_Ring_Core_28","TC_Ring_Core_28 To Verify when user click on Ok, Got It! on the bottom sheet ");
		// TC31
		click(RingLoginPage.objMobTextField, "Mobile Number Field");
		clearField(RingLoginPage.objMobTextField, "Mobile Number Field");

		logger.info("Expected: " + popupMessageValidationOfBlockNumber);
		softAssertion.assertEquals("Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.",popupMessageValidationOfBlockNumber);
		logger.info("Temprory Block Your Number For 2 Minutes Validated PopUp Message");
		extent.extentLoggerPass("PopUp Of Block User For 2 Minutes","Your Ring account has been temporarily blocked due to security reasons. Try again after 2 minutes.");	
		logger.info("TC_Ring_Core_31 To Verify when user enter the same mobile number which is blocked------>PASSED");
		extent.extentLoggerPass("TC_Ring_Core_29","TC_Ring_Core_29 To Verify when user enter the same mobile number which is blocked------>PASSED");

		waitTime(4000);
		explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
		logger.info("Verify Mobile Header");
		String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
		softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
		waitTime(4000);
		Aclick(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, "9" + RandomIntegerGenerator(9), "Mobile text field");
		waitTime(5000);
		enterOtp("888888");
		// TC32
		explicitWaitVisibility(MobileLoginPage.btnReadAndAccept, 10);
		String txtReadAndAccept = getText(MobileLoginPage.btnReadAndAccept);
		logger.info(txtReadAndAccept);
		softAssertion.assertEquals("Read & Accept", txtReadAndAccept);
		extent.extentLoggerPass("TC_Ring_Core_30", "TC_Ring_Core_30 To Verify user enters valid OTP---->PASSED");

		explicitWaitVisibility(RingLoginPage.objRingPermissionsHeader, 10);

		waitTime(7000);
		/*Swipe("up", 1);
		explicitWaitVisibility(RingLoginPage.ckycCheckBox, 10);
		Aclick(RingLoginPage.ckycCheckBox, "Terms and Conditions checkbox");

		Aclick(RingLoginPage.objReadAcceptBtn, "Read & Accept button");
		waitTime(1000);
		String kycError = getText(UserRegistrationPage.objToast);
		softAssertion.assertEquals("Please accept terms and conditions", kycError);
		extent.extentLoggerPass("TC_Ring_Core_52","TC_Ring_Core_52 To Verify if user only checked in whatsapp notification check box and Continue with read & accept");

		extent.extentLoggerPass("TC_Ring_Core_54","TC_Ring_Core_54 To Verify when user clicks on read and accept with unchecked CKYC consent");

		Aclick(RingLoginPage.ckyCheckBox1, "Terms and Conditions checkbox");
		Swipe("DOWN", 1);*/
		waitTime(3000);
		//50 to 54
		if (verifyElementPresent(RingLoginPage.objRingPermissionsHeader, "RingPay permissions")) {

			logger.info("Ring Pay Permissions page (SMS, LOCATION & PHONE)");
			String ringPermissionTxt = getText(RingLoginPage.objRingPermissionsHeader);
			Assert.assertEquals(ringPermissionTxt, "Permissions");

			Aclick(RingLoginPage.objReadAcceptBtn, "Read & Accept button");

			explicitWaitVisibility(RingLoginPage.objLocAccess, 10);

			Aclick(RingLoginPage.objLocDeny, "Location Deny option");
			explicitWaitVisibility(RingLoginPage.objPhoneAccess,10);
			Aclick(RingLoginPage.objPhoneAccess, "Phone access option");
			explicitWaitVisibility(RingLoginPage.objSMSAccess,10);
			Aclick(RingLoginPage.objSMSAccess, "SMS access option");
	        explicitWaitVisibility(RingLoginPage.objPermDenial,10);
	        extent.extentLoggerPass("TC_Ring_Core_55","TC_Ring_Core_55 To Verify when user refuse the location access");
	        extent.extentLoggerPass("TC_Ring_Core_56","TC_Ring_Core_56 To Verify when user refuse the SMS access");
	        extent.extentLoggerPass("TC_Ring_Core_57","TC_Ring_Core_57 To Verify when user refuse the contact access");
	        extent.extentLoggerPass("TC_Ring_Core_58","TC_Ring_Core_58 To Verify When user refuse to give permission access");
	        
	        Aclick(RingLoginPage.objPermCancel,"Cancel Button");
	        explicitWaitClickable(RingLoginPage.objReadAcceptBtn,10);
	        Aclick(RingLoginPage.objReadAcceptBtn, "Read & Accept button");
	        
	        explicitWaitVisibility(RingLoginPage.objLocAccess, 10);
	        Aclick(RingLoginPage.objLocAccess,"Location Access");
	        
		}
		
		logger.info("TC_Ring_Core_59 To verify when users allow all the permission");
		extent.extentLoggerPass("TC_Ring_Core_59", "TC_Ring_Core_59 To verify when users allow all the permission");
		softAssertion.assertAll();
	}
//===========================================User Play store Flow end===============================================================
//=========================================User Registration Flow Start=============================================================
	public void User_Registration_Flow(String month, String date, String year, String gender){
		extent.HeaderChildNode("User Registration Flow Module");
		try {
		waitTime(60000);
		logger.info("User Registration Details Page");
		extent.extentLogger("INFO", "User Registration Details Page");
		explicitWaitVisibility(UserRegistrationNew.objUserDetailsHeader, 10);
		hideKeyboard();
		explicitWaitVisibility(UserRegistrationNew.objFirstName, 10);
		explicitWaitVisibility(UserRegistrationNew.objLastName, 10);
		explicitWaitVisibility(UserRegistrationPage.objUserDOB, 10);
		explicitWaitVisibility(UserRegistrationNew.objMothersName,10);
		explicitWaitVisibility(UserRegistrationNew.objGenderSelect, 10);

		extent.extentLoggerPass("TC_Ring_Core_73", "TC_Ring_Core_73 - To verify the 'User Details' screen");

		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		String firstNameErrorTxt = getText(UserRegistrationNew.objFirstNameError);
		String lastNameErrorTxt = getText(UserRegistrationNew.objLastNameError);
		String dobErrorTxt = getText(UserRegistrationNew.objDOBError);
		//Swipe("Up",1);
		explicitWaitVisibility(UserRegistrationNew.objGender,10);
		String genderErrorTxt = getText(UserRegistrationNew.objGender);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		softAssertion.assertEquals("Please select Date of Birth", dobErrorTxt);
		softAssertion.assertEquals("Please Select Gender", genderErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_76","TC_Ring_Core_76 - To verify the response by clicking on 'Continue' button when all required fields are empty");
		extent.extentLoggerPass("TC_Ring_Core_109","TC_Ring_Core_109 - To verify whether the user is not able to 'Continue' when the 'Gender' is not selected from the drop down");

		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "Huss", "Last Name text field");
		hideKeyboard();
		dateOfBirth(month, date, year);
		Swipe("up", 2);
		click(UserRegistrationNew.objGenderSelect, "Gender select");
		explicitWaitVisibility(RingLoginPage.objGender, 10);
		click(RingLoginPage.objGender, "Male gender");
		
		waitTime(3000);
		Swipe("down", 2);
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_77","TC_Ring_Core_77 - To verify the user is not able to 'Continue' with by keeping 'First Name' field empty with all valid details");

		explicitWaitVisibility(UserRegistrationNew.objFirstName,10);
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, generateRandomString(1), "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_78","TC_Ring_Core_78 - To verify the user is not able to 'Continue' with entering only 'First Name' initial in first name field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Xyz123", "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_79","TC_Ring_Core_79 - To verify the user is not able to 'Continue' with alphanumeric characters entered in 'First Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Xyz123:+$", "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_80","TC_Ring_Core_80 - To verify the user is not able to 'Continue' with special characters entered in 'First Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "  ", "First Name text field");
		hideKeyboard();
		explicitWaitClickable(UserRegistrationNew.objProceed, 10);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objFirstNameError, 10);
		softAssertion.assertEquals("Please enter valid first name", firstNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_81","TC_Ring_Core_81 - To verify the user is not able to 'Continue' with <Space> entered in 'First Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, generateRandomString(5), "First Name text field");
		hideKeyboard();

		Aclick(UserRegistrationNew.objLastName, "Last Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_84","TC_Ring_Core_84 - To verify the user is not able to 'Continue' with by keeping 'Last Name' field empty with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, generateRandomString(1), "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_85","TC_Ring_Core_85 - To verify the user is not able to 'Continue' with entering only 'Last Name' initial in Last name field with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, "pqr123", "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_86","TC_Ring_Core_86 - To verify the user is not able to 'Continue' with alphanumeric characters entered in 'Last Name' field with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, "pqr123+=", "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_87","TC_Ring_Core_87 - To verify the user is not able to 'Continue' with special characters entered in 'Last Name' field with all valid details");

		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		type(UserRegistrationNew.objLastName, "  ", "Last Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objLastNameError, 10);
		softAssertion.assertEquals("Please enter valid last name", lastNameErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_88","TC_Ring_Core_88 - To verify the user is not able to 'Continue' with <Space> entered in 'Last  Name' field with all valid details");

		clearField(UserRegistrationNew.objFirstName, "First Name text field");
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Shak", "First Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "Shak", "Last name text field");
		explicitWaitVisibility(UserRegistrationNew.objMothersName, 10);
		click(UserRegistrationNew.objMothersName, "Mother's Name Field");
		type(UserRegistrationNew.objMothersName, "Mom", "Mother's Name field");
		hideKeyboard();
		click(UserRegistrationNew.objUserEmail,"Email text field");
		Aclick(UserRegistrationNew.objNoneOfAbove, "None of the above button");
		type(UserRegistrationNew.objUserEmail,generateRandomString(4) + "@gmail.com","Email text field");
		hideKeyboard();
		waitTime(3000);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(3000);
		String toast_85 = getText(UserRegistrationNew.objToast);
		System.out.println(toast_85);
		logger.info(toast_85);
		extent.extentLogger("Toast Message", toast_85);
		softAssertion.assertEquals("First and Last name should be different", toast_85);
		extent.extentLoggerPass("TC_Ring_Core_90","TC_Ring_Core_90 - To verify the user is not able to 'Continue' with entering same 'First Name' and  'Last Name'");
		
		waitTime(3000);
		clearField(UserRegistrationNew.objFirstName, "First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "Shak Shak", "First Name text field");
		waitTime(3000);
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss", "First Name text field");

		explicitWaitVisibility(UserRegistrationNew.objMothersName, 10);
		click(UserRegistrationNew.objMothersName, "Mother's Name Field");
		type(UserRegistrationNew.objMothersName, "Mom", "Mother's Name field");

		waitTime(3000);
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(3000);
		String toast_86 = getText(UserRegistrationNew.objToast);
		System.out.println(toast_86);
		logger.info(toast_86);
		extent.extentLogger("Toast Message", toast_86);
		softAssertion.assertEquals("Enter valid First Name ", toast_86);
		extent.extentLoggerPass("TC_Ring_Core_91","TC_Ring_Core_91 - To verify the user is not able to 'Continue' with entering same 'First Name' repeatedly in same field");

		clearField(UserRegistrationNew.objFirstName, "First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		Aclick(UserRegistrationNew.objFirstName, "Last name text field");
		type(UserRegistrationNew.objFirstName, "shak", "First Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss huss", "First Name text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(3000);
		String toast_87 = getText(UserRegistrationNew.objToast);
		System.out.println(toast_87);
		logger.info(toast_87);
		extent.extentLogger("Toast Message", toast_87);
		softAssertion.assertEquals("Enter valid Last Name ", toast_87);
		extent.extentLoggerPass("TC_Ring_Core_92","TC_Ring_Core_92 - To verify the user is not able to 'Continue' with entering same 'Last Name' repeatedly in same field");

		explicitWaitVisibility(UserRegistrationNew.objMothersName,10);
		extent.extentLoggerPass("TC_Ring_Core_93","TC_Ring_Core_93 - To verify new field is added after Last Name field");
		
		clearField(UserRegistrationNew.objFirstName,"First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		hideKeyboard();
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objFirstName, "First name text field");
		type(UserRegistrationNew.objFirstName, "shak", "First Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss", "First Name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_94","TC_Ring_Core_94 - To verify the user is not able to 'Proceed' with by keeping 'Mother's Name' field empty with all valid details");
		
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,generateRandomString(1),"Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_95","TC_Ring_Core_95 - To verify the user is not able to 'Proceed' with entering only 'Mother's Name' initial in Mother's Name field with all valid details");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,"M12","Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_96","TC_Ring_Core_96 - To verify the user is not able to 'Proceed' with alphanumeric characters entered in 'Mother's Name' field with all valid details");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,"M12$%","Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Register Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_97","TC_Ring_Core_97 - To verify the user is not able to 'Proceed' with special characters entered in 'Mother's Name' field with all valid details");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName," ","Mother's name text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objMotherError,10);
		extent.extentLoggerPass("TC_Ring_Core_98","TC_Ring_Core_98 - To verify the user is not able to 'Proceed' with <Space> entered in 'Mother's Name' field with all valid details");
		
		clearField(UserRegistrationNew.objFirstName,"First Name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		hideKeyboard();
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		
		clearField(UserRegistrationNew.objMothersName,"Mother's name text field");
		Aclick(UserRegistrationNew.objMothersName,"Mother's name text field");
		type(UserRegistrationNew.objMothersName,"mom","Mother's name text field");
		clearField(UserRegistrationNew.objLastName, "Last Name text field");
		Aclick(UserRegistrationNew.objLastName, "Last name text field");
		type(UserRegistrationNew.objLastName, "huss", "last Name text field");
		Aclick(UserRegistrationNew.objFirstName,"First Name text field");
		type(UserRegistrationNew.objLastName, "shakss", "First Name text field");
		hideKeyboard();
		Aclick(UserRegistrationNew.objUserEmail,"Email text field");
		clearField(UserRegistrationNew.objUserEmail,"Email text field");
		hideKeyboard();
		waitTime(3000);
		Aclick(UserRegistrationNew.objProceed, "proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objEmailError,10);
		softAssertion.assertEquals("Please enter valid email address", getText(UserRegistrationNew.objEmailError));
		extent.extentLoggerPass("TC_Ring_Core_103","TC_Ring_Core_103 - To verify the user is not able to 'Continue' with by keeping 'Email Address' field empty with all valid details");

		Aclick(UserRegistrationNew.objUserEmail, "Email text field");
		type(UserRegistrationNew.objUserEmail, "huss^^@gmail.com", "Email text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		hideKeyboard();
		waitTime(3000);
		explicitWaitVisibility(UserRegistrationNew.objEmailError1, 10);
		String emailErrorTxt = getText(UserRegistrationNew.objEmailError1);
		softAssertion.assertEquals("Please enter valid email id", emailErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_104","TC_Ring_Core_104 - To verify the user is not able to 'Continue' with by entering 'Email Address' in invalid format OR with special characters with all valid details");

		clearField(UserRegistrationNew.objUserEmail, "Email text field");
		type(UserRegistrationNew.objUserEmail, "huss  @gmail.com", "Email text field");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objEmailError1, 10);
		softAssertion.assertEquals("Please enter valid email id", emailErrorTxt);
		extent.extentLoggerPass("TC_Ring_Core_105","TC_Ring_Core_105 - To verify the user is not able to 'Continue' with <Space> entered in 'Email Address' field with all valid details");

		hideKeyboard();
		clearField(UserRegistrationNew.objUserEmail, "Email text field");
		String email = RandomStringGenerator(4)+"@example.com";
		type(UserRegistrationNew.objUserEmail,email , "Email text field");
		setWifiConnectionToONOFF("Off");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		explicitWaitVisibility(UserRegistrationNew.objInternetError, 10);
		String intError = getText(UserRegistrationNew.objInternetError);
		softAssertion.assertEquals(" Check your connection & try again ", intError);
		extent.extentLoggerPass("TC_Ring_Core_96","TC_Ring_Core_96 - To verify the user is getting 'Check internet connection' screen after clicking on 'Continue' button when the Device internet connection is down");

		setWifiConnectionToONOFF("On");
		explicitWaitClickable(UserRegistrationNew.objGotItBtn, 10);
		Aclick(UserRegistrationNew.objGotItBtn, "Okay Got It button");
		explicitWaitVisibility(UserRegistrationNew.objUserDetailsHeader, 10);
		extent.extentLoggerPass("TC_Ring_Core_97","TC_Ring_Core_97 - To verify the user is able to 'Continue' after 'Okay Got It' once the device internet connection Up");

		hideKeyboard();
		userDetails();
		dateOfBirth("Oct", "12", "1995");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(5000);
		//instaNewCommunicationAddress();
		addAddress("21","assa","safas","ra","560102");
		waitTime(5000);
		
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresent(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		
		waitTime(5000);
		if (verifyElementPresent(UserRegistrationPage.objSetPin, "Set Pin Page")) {
			waitTime(5000);
			logger.info("Navigated to MPIN Page");
			extent.extentLoggerPass("MPIN Page", "Navigated to MPIN Page");
			waitTime(5000);
			verifyElementPresent(TermsAndConditionPage.objSetPin, getText(TermsAndConditionPage.objSetPin));
			softAssertion.assertEquals(getText(TermsAndConditionPage.objSetPin), "Set Pin");
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		}
		
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
		ringPayLogout();
		

		extent.extentLoggerPass("TC_Ring_Core_83","TC_Ring_Core_83 - To verify the user is able to 'Continue' with entering valid 'First Name' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_89","TC_Ring_Core_89 - To verify the user is able to 'Continue' with entering valid 'Last Name' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_99","TC_Ring_Core_99 - To verify the user is able to 'Proceed' with entering valid 'Mother's Name' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_106","TC_Ring_Core_106 - To verify the user is able to 'Continue' with entering valid 'Email Address' with all valid details");
		extent.extentLoggerPass("TC_Ring_Core_110","TC_Ring_Core_110 - To verify whether the user is able to 'Continue' when the 'Gender' is selected from the drop down");
		extent.extentLoggerPass("TC_Ring_Core_115","TC_Ring_Core_115 - To verify when user successfully enters all valid details and clicks on continue button");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
//============================================User Registration Flow End============================================================================================
//===============================================PromoCode Flow Start===============================================================================================
	public void promoCodeFlowModule() throws Exception {
		extent.HeaderChildNode("PromoCode Flow");
		getDriver().resetApp();
		
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		 /*------------------------------WEB----------------------------*/
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		String projectPath=System.getProperty("user.dir");
		System.out.println(projectPath);
		//getWebDriver().get("");
        getWebDriver().get(projectPath+"//Mock_Files/qrcode.png");
        waitTime(10000);
        BrowsertearDown();
        
        /*------------------------------Android----------------------------*/
        setPlatform("Android");
		initDriver();
		waitTime(5000);
		
		User_Play_Store_Flow(prop.getproperty("PaymentPage"),prop.getproperty("Permission"));
		waitTime(60000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		addAddress("12","asaaw","saass","ddj","560102");
		
		verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
		softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
		/*verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
		verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
		verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
		verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
		verifyElementPresent(PromoCodeOfferPage.objRBIMsg , getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMsg));*/
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		waitTime(40000);
		verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
		waitTime(20000);
	    verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
	    if(verifyElementPresent2(UserRegistrationPage.objNoBtn, "No Button")) {
	    	verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
	    }
	    String sHome = getText(HomePage.objHome);
	    softAssertion.assertEquals(sHome, "Home");
	    ringPayLogout();
	    logger.info("TC_Ring_Core_71 - To verify  user Scans the Promo Code QR");
	    extent.extentLoggerPass("TC_Ring_Core_71", "To verify  user Scans the Promo Code QR");
	    
	    logger.info("TC_Ring_Core_72 - Repeat  TC_Ring_Core_4 to TC_Ring_Core_59 steps Number  for login flow");
	    extent.extentLoggerPass("TC_Ring_Core_72", "Repeat  TC_Ring_Core_4 to TC_Ring_Core_59 steps Number  for login flow");
	}
//===============================================PromoCode Flow end================================================================
//===============================================Offer Module Start================================================================
		public void offerScreenModule() throws Exception {
			extent.HeaderChildNode("RingPay OfferScreen");
		
			merchantOfferPageValidation();
			playStorePromocodeOfferPageValidation();
			
			logger.info("TC_Ring_Core_175 - To verify the 'Offer' screen on this page Accept and Pay button is displayed(Merchant flow)");
			extent.extentLoggerPass("TC_Ring_Core_175", "To verify the 'Offer' screen on this page Accept and Pay button is displayed(Merchant flow)");
			
			logger.info("TC_Ring_Core_176 - To verify the 'Offer' screen on this page Accept offer button is displayed(Playstore Promocode flow) ");
			extent.extentLoggerPass("TC_Ring_Core_176", "To verify the 'Offer' screen on this page Accept offer button is displayed(Playstore Promocode flow) ");
			
			logger.info("TC_Ring_Core_177 - To verify the response by Clicking on 'Fee Details' link from limit approval banner");
			extent.extentLoggerPass("TC_Ring_Core_177", "To verify the response by Clicking on 'Fee Details' link from limit approval banner");
			
			logger.info("TC_Ring_Core_178 - To verify the response by clicking on t&c link");
			extent.extentLoggerPass("TC_Ring_Core_178", "To verify the response by clicking on t&c link");
			
			logger.info("TC_Ring_Core_179 - To verify the user is not able to proceed to pay amount without selecting t&c conditions checkbox by clicking on 'Accept & Pay' button");
			extent.extentLoggerPass("TC_Ring_Core_179", "To verify the user is not able to proceed to pay amount without selecting t&c conditions checkbox by clicking on 'Accept & Pay' button");
			
			logger.info("TC_Ring_Core_180 - To verify the user is able to pay amount by selecting t&c checkbox and clicking on 'Accept & Pay' button");
			extent.extentLoggerPass("TC_Ring_Core_181", "To verify the user is able to pay amount by selecting t&c checkbox and clicking on 'Accept & Pay' button");
			
			logger.info("TC_Ring_Core_181 - To verify the user is getting check internet connection screen after clicking on Accept and Pay button when the device internet connection is down");
			extent.extentLoggerPass("TC_Ring_Core_181", "To verify the user is getting check internet connection screen after clicking on Accept and Pay button when the device internet connection is down");
			
			logger.info("TC_Ring_Core_182 - To verify the user is able to make payment by clicking on Retry/Refresh and by clicking on Accept & Pay button once the device internet connection is Up");
			extent.extentLoggerPass("TC_Ring_Core_182", "To verify the user is able to make payment by clicking on Retry/Refresh and by clicking on Accept & Pay button once the device internet connection is Up");
			
			logger.info("TC_Ring_Core_183 - To verify the user turns off location on Accept and Pay screen");
			extent.extentLoggerPass("TC_Ring_Core_183", "To verify the user turns off location on Accept and Pay screen");
			
			logger.info("TC_Ring_Core_184 - To  Verify when user selects allow option on location popup");
			extent.extentLoggerPass("TC_Ring_Core_184", "To  Verify when user selects allow option on location popup");
			
			logger.info("TC_Ring_Core_185 - To verify when user select not now option on location popup");
			extent.extentLoggerPass("TC_Ring_Core_185", "To verify when user select not now option on location popup");
			
			logger.info("TC_Ring_Core_186 - To verify when user kill or refresh app on accept and pay page");
			extent.extentLoggerPass("TC_Ring_Core_186",  "To verify when user kill or refresh app on accept and pay page");
			
			logger.info("TC_Ring_Core_187 - To verify the user turns off location on Accept offer screen");
			extent.extentLoggerPass("TC_Ring_Core_187", "To verify the user turns off location on Accept offer screen");
			
			logger.info("TC_Ring_Core_188 - To Verify when user click on Accept offer/Accept and pay button");
			extent.extentLoggerPass("TC_Ring_Core_188", "To Verify when user click on Accept offer/Accept and pay button");
			
			softAssertion.assertAll();
		}
	
//============================================Offer Screen Merchant Flow=====================================================================================
		public void merchantOfferPageValidation() throws Exception {
			getDriver().resetApp();
			waitTime(5000);
			if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
				enablePermissions();
			}
			verifyElementPresent(RingLoginPage.objScanQRPage, getText(RingLoginPage.objScanQRPage));

			 /*------------------------------WEB----------------------------*/
			waitTime(5000);
			setPlatform("Web");
			System.out.println("platform changed to web");
			String pf = getPlatform();
			System.out.println(pf);
			waitTime(5000);
			new RingPayBusinessLogic("ring");
			waitTime(10000);
			String projectPath=System.getProperty("user.dir");
			System.out.println(projectPath);
	        getWebDriver().get(projectPath+"//Mock_Files/qrcode.png");
	        waitTime(10000);
	        BrowsertearDown();
	        
	        /*------------------------------Android----------------------------*/
	        setPlatform("Android");
			initDriver();
			waitTime(5000);
			
			click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			//click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
			type1(RingPayMerchantFlowPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
			verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn,getText(RingLoginPage.objMakePaymentPageProceedBtn));
			verifyIsElementDisplayed(RingPayMerchantFlowPage.objLoginPageHeader,getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
			softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
			logger.info("User redirected to Signup/Login Screen");
			extent.extentLogger("login", "User redirected to Signup/Login Screen");
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
			loginMobile();
			
			mobileNoValidation1("9" + RandomIntegerGenerator(9));
			enterOtp(prop.getproperty("OTP"));
			readAndAccept();
			waitTime(60000);
			userDetails();
			dateOfBirth("Feb", "10", "1996");
			verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
			//instaNewCommunicationAddress();
			addAddress("12","asaaw","saass","ddj","560102");
			waitTime(5000);
			verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
			softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
			/*verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
			verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
			verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
			verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
			verifyElementPresent(PromoCodeOfferPage.objRBIMsg , getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMsg));*/
			verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
			verifyElementPresentAndClick(PromoCodeOfferPage.objFeeDetail, getText(PromoCodeOfferPage.objFeeDetail));
			verifyElementPresent(PromoCodeOfferPage.objFeeDetailPage, getText(PromoCodeOfferPage.objFeeDetailPage));
			click(PromoCodeOfferPage.objFeeDetailsCrossBtn, "Cross Button");
			
			/*verifyElementPresent(PromoCodeOfferPage.objTermsAndCondition,getTextVal(PromoCodeOfferPage.objTermsAndCondition, "Text"));
			click(PromoCodeOfferPage.objTermsAndCondition, "Terms And Conditions");
			waitTime(5000);
			verifyElementPresent(TermsAndConditionPage.objTAndCondition,getTextVal(TermsAndConditionPage.objTAndCondition, "Text"));
			Back(1);
			
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			System.out.println(getText(PromoCodeOfferPage.objToastMsg));
			logger.info(getText(PromoCodeOfferPage.objToastMsg));
			extent.extentLoggerPass("Toast Msg", getText(PromoCodeOfferPage.objToastMsg));*/
			//getDriver().closeApp();
			verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			setWifiConnectionToONOFF("Off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
			waitTime(5000);
			verifyElementPresent(PromoCodeOfferPage.objCheckInternetConnectionPage, getText(PromoCodeOfferPage.objCheckInternetConnectionPage));
			setWifiConnectionToONOFF("On");
			click(TermsAndConditionPage.objOkBtn, "Button Okay, Got It!");
			
			verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
			setLocationConnectionToONOFF("off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			verifyElementPresent(PromoCodeOfferPage.objLocationPopup, getText(PromoCodeOfferPage.objLocationPopup));
			verifyElementPresent(PromoCodeOfferPage.objLocationAllowBtn, getText(PromoCodeOfferPage.objLocationAllowBtn));
			verifyElementPresent(PromoCodeOfferPage.objLocationNotNowBtn, getText(PromoCodeOfferPage.objLocationNotNowBtn));

			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationAllowBtn, getText(PromoCodeOfferPage.objLocationAllowBtn));
			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationOkBtn, "OK Button");
			verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
			softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
			
			setLocationConnectionToONOFF("off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			verifyElementPresent(PromoCodeOfferPage.objLocationPopup, getText(PromoCodeOfferPage.objLocationPopup));
			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationNotNowBtn, getText(PromoCodeOfferPage.objLocationNotNowBtn));

			setLocationConnectionToONOFF("on");
			
			getDriver().runAppInBackground(Duration.ofSeconds(5));
			waitTime(5000);
			setLocationConnectionToONOFF("Off");
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			verifyElementPresentAndClick(PromoCodeOfferPage.objLocationNotNowBtn, getText(PromoCodeOfferPage.objLocationNotNowBtn));
			verifyElementPresentAndClick(PromoCodeOfferPage.objEnableLocBtn, getText(PromoCodeOfferPage.objEnableLocation));
			setLocationConnectionToONOFF("on");
			Back(1);
			
			waitTime(5000);
			verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			waitTime(15000);
			verifyElementPresent(TermsAndConditionPage.objSetPin, getText(TermsAndConditionPage.objSetPin));
			softAssertion.assertEquals(getText(TermsAndConditionPage.objSetPin), "Set Pin");
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
			waitTime(35000);
			verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
			
			waitTime(5000);
			verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
			if(verifyElementPresent2(UserRegistrationPage.objNoBtn, "No Button")) {
		    	verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
		    }
			String sHome = getText(HomePage.objHome);
			softAssertion.assertEquals(sHome, "Home");
			ringPayLogout();
			
		}
//===============================================Play Store Offer====================================================================	
	public void playStorePromocodeOfferPageValidation() throws Exception {
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
			
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		//instaNewCommunicationAddress();
		addAddress("12","asaaw","saass","ddj","560102");
		waitTime(5000);
		
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresent(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		
		setLocationConnectionToONOFF("off");
		waitTime(5000);
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		
		waitTime(5000);
		if (verifyElementPresent(UserRegistrationPage.objSetPin, "Set Pin Page")) {
			setLocationConnectionToONOFF("on");
			waitTime(5000);
			logger.info("Navigated to MPIN Page");
			extent.extentLoggerPass("MPIN Page", "Navigated to MPIN Page");
			waitTime(15000);
			verifyElementPresent(TermsAndConditionPage.objSetPin, getText(TermsAndConditionPage.objSetPin));
			softAssertion.assertEquals(getText(TermsAndConditionPage.objSetPin), "Set Pin");
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		}
		
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
		ringPayLogout();
		
	}
//===================================================Offer Module End==============================================================================
//====================================To verify Age criteria failed checks (22 to 60) Start========================================================
	public void ageCriteriaFailedCheck() throws Exception {
		extent.HeaderChildNode("Age Criteria Check");
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
			
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		ageSelect("lessthan18", "Oct", "12", "2010", 2010, 12);
		
		ageSelect("greaterthan55", "Oct", "12", "1966", 1966, 12);
		
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		loginMobile();
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		waitTime(3000);
		enterOtp(prop.getproperty("OTP"));
		waitTime(40000);
		userDetails();
		ageSelect("greaterthanequalto18 || lessthanequalto55", "Oct", "12", "1995", 1995, 12);

		logger.info("TC_Ring_Customer_Seg_57 - To verify users provided age <22 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_57", "To verify users provided age <22 years");
		
		logger.info("TC_Ring_Customer_Seg_58 - To verify users provided age >60 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_58", "To verify users provided age >60 years");
		
		logger.info("TC_Ring_Customer_Seg_59 - To verify users provided age exact 22 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_59","To verify users provided age exact 22 years");
		
		logger.info("TC_Ring_Customer_Seg_60 - To verify users provided age exact 60 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_60", "To verify users provided age exact 60 years");
		
		logger.info("TC_Ring_Customer_Seg_61 - To verify users age between 22 to 60 years");
		extent.extentLoggerPass("TC_Ring_Customer_Seg_61", "To verify users age between 22 to 60 years");
	}

	public void userDetails() throws Exception {
		//extent.HeaderChildNode("Age Criteria");
		waitTime(4000);
		explicitWaitVisibility(RingUserDetailPage.objFirstName, 30);
		click(RingUserDetailPage.objFirstName, "First Name Field");
		type(RingUserDetailPage.objFirstName, "Sunil", "First Name field");

		explicitWaitVisibility(RingUserDetailPage.objLastName, 10);
		click(RingUserDetailPage.objLastName, "Last Name Field");
		type(RingUserDetailPage.objLastName, "Chatla", "Last Name field");

		explicitWaitVisibility(RingUserDetailPage.objMothersName, 10);
		click(RingUserDetailPage.objMothersName, "Mother's Name Field");
		type(RingUserDetailPage.objMothersName, "Mom", "Mother's Name field");
		hideKeyboard();

		explicitWaitVisibility(RingUserDetailPage.objEmail, 10);
		click(RingUserDetailPage.objEmail, "Email Field");
		click(RingUserDetailPage.objNone, "None of the Above Button");
		String email = generateRandomString(8) + "@gmail.com";
		type(RingUserDetailPage.objEmail, email, "Email Filed");
		hideKeyboard();
		waitTime(2000);
		click(RingLoginPage.objGender, "Gender male is selected");
		click(RingLoginPage.objGender, "Gender male is selected");
	}

	public void ageSelect(String key, String Month, String Date, String Year, int year, int date) throws Exception {
		extent.HeaderChildNode("Age Criteria");

		switch (key) {
		case "lessthan18":
			dateOfBirth(Month, Date, Year);

			age = ageCal(year, date);
			break;

		case "greaterthan55":
			dateOfBirth(Month, Date, Year);

			age = ageCal(year, date);
			break;

		case "greaterthanequalto18 || lessthanequalto55":
			dateOfBirth(Month, Date, Year);

			age = ageCal(year, date);
			break;

		default:
			logger.info("invalid age!!");
			break;
		}
		
		waitTime(3000);
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");

		if (age < 18) {
			waitTime(10000);
			logger.warn("The present age is <18 therefore the age is: " + age);
			extent.extentLoggerWarning("age", "The present age is <18 therefore the age is: " + age);
			waitTime(10000);
			verifyElementPresent(RingUserDetailPage.objAgeLessThan18Notification,getText(RingUserDetailPage.objAgeLessThan18Notification));
			logger.info("Age Criteria Failed");
			extent.extentLogger("Age Verification", "Age Criteria Failed");

		} else if (age > 55) {

			logger.warn("The present age is >55 therefore the age is: " + age);
			extent.extentLoggerWarning("age", "The present age is >55 therefore the age is: " + age);
			waitTime(25000);
			verifyElementPresent(RingUserDetailPage.objSorryMsg, getText(RingUserDetailPage.objSorryMsg));
			logger.info("Age Criteria Failed");
			extent.extentLogger("Age Verification", "Age Criteria Failed");
			verifyElementPresentAndClick(RingUserDetailPage.objHamburgerTab, "Hamburger Tab");
			verifyElementPresentAndClick(RingUserDetailPage.objProfileTabCompletedPercentage,"Profile Completed Percentage tab");
			verifyElementPresentAndClick(RingUserDetailPage.objLogoutBtn, "Logout Button");

			click(RingLoginPage.objYesBtn, "Yes confirmation button");
			logger.info("User is successfully logged out");
			extent.extentLoggerPass("Logout confirmation", "User is successfully logged out");


		} else if (age >= 18 || age <= 55) {
			waitTime(10000);
			//instaNewCommunicationAddress();
			addAddress("12","asaaw","saass","ddj","560102");
			logger.info("The present age is >=18 & <=55 and therefor the age is: " + age);
			extent.extentLogger("age", "The present age is >=18 & <=55 and therefor the age is: " + age);
			logger.info("Age Criteria Passed");
			extent.extentLogger("Age Verification", "Age Criteria Passed");
			
			instaLoancongratsScreen();
			instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
			verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
			String sHome = getText(HomePage.objHome);
			softAssertion.assertEquals(sHome, "Home");
			ringPayLogout();
		}
	}
	
	public void dateOfBirth(String month, String date, String year) throws Exception {
		explicitWaitClickable(UserRegistrationPage.objUserDOB, 10);
		Aclick(UserRegistrationPage.objUserDOB, "DOB field");
		if (!verifyElementDisplayed(UserRegistrationPage.objGenderCancelBtn)) {
	           click(UserRegistrationPage.objUserDOB, "DOB field");
	    }

		explicitWaitVisibility(UserRegistrationPage.objDatePickerMonth, 10);
		Aclick(UserRegistrationPage.objDatePickerMonth, "Month field");
		clearField(UserRegistrationPage.objDatePickerMonth, "Month field");
		type(UserRegistrationPage.objDatePickerMonth, month, "Month field");

		Aclick(UserRegistrationPage.objDatePickerDate, "Date field");
		clearField(UserRegistrationPage.objDatePickerDate, "Date field");
		type(UserRegistrationPage.objDatePickerDate, date, "Date field");

		Aclick(UserRegistrationPage.objDatePickerYear, "Year field");
		clearField(UserRegistrationPage.objDatePickerYear, "Year field");
		type(UserRegistrationPage.objDatePickerYear, year, "Year field");

		Aclick(UserRegistrationPage.objOK, "OK button");

	}

//============================================age Criteria check End====================================================================	
//===========================Pan bottom sheet case if pan received from bureau and KYC get skipped Start================================	
	public HashMap mockUserAPI(String url, String gender, String encrypted_name) {
		HashMap map=new HashMap();
	ValidatableResponse response = Utilities.MockuserAPI(url, gender, encrypted_name);
	String firstName = response.extract().jsonPath().getString("data.data.first_name");
	System.out.println("First Name= " + firstName);
	String middleName = response.extract().jsonPath().getString("data.data.middle_name");
	System.out.println("Middle Name= " + middleName);
	String lastName = response.extract().jsonPath().getString("data.data.last_name");
	System.out.println("Last Name =" + lastName);
	String panCard = response.extract().jsonPath().getString("data.data.pan");
	System.out.println("Pan Card= " + panCard);
	String mobileNumber = response.extract().jsonPath().getString("data.data.mobile_number");
	System.out.println("Mobile Number= " + mobileNumber);
	String imei = response.extract().jsonPath().getString("data.data.imei");
	System.out.println("IMEI= " + imei);
	String motherName = response.extract().jsonPath().getString("data.data.mother_name");
	System.out.println("Mother Name= " + motherName);
	String email = response.extract().jsonPath().getString("data.data.email");
	System.out.println("Email= " + email);
	String genders = response.extract().jsonPath().getString("data.data.gender");
	System.out.println("Gender= " + genders);
	String otp = response.extract().jsonPath().getString("data.data.otp");
	System.out.println(otp);
	String dob = response.extract().jsonPath().getString("data.data.dob");
	System.out.println(dob);
	String[] dateSplit = dob.split("-");
	String year = dateSplit[0];
	System.out.println("years---" + year);
	String month = dateSplit[1];
	System.out.println("month---" + month);
	String date = dateSplit[2];
	System.out.println("date---" + date);
	int monthNumber = Integer.parseInt(month);
	String monthByName = Month.of(monthNumber).getDisplayName(TextStyle.SHORT, Locale.US);
	System.out.println(monthByName);
	String roomno=response.extract().jsonPath().getString("data.data.present_address.room_number");
	System.out.println(roomno);
	String address1=response.extract().jsonPath().getString("data.data.present_address.line_1");
	System.out.println(address1);
	String address2=response.extract().jsonPath().getString("data.data.present_address.line_2");
	System.out.println(address2);
	String landmark=response.extract().jsonPath().getString("data.data.present_address.landmark");
	System.out.println(landmark);
	String pin=response.extract().jsonPath().getString("data.data.present_address.pincode");
	System.out.println(pin);
	
	// User Details
	
	map.put("firstName", firstName);
	map.put("middleName", middleName);
	map.put("lastName", lastName);
	map.put("panCard", panCard);
	map.put("mobileNumber", mobileNumber);
	map.put("imei", imei);
	map.put("motherName", motherName);
	map.put("email", email);
	map.put("genders", genders);
	map.put("otp", otp);
	map.put("year", year);
	map.put("month", monthByName);
	map.put("date", date);
	
	//New Communication Address
	map.put("roomNo", roomno);
	map.put("address1",address1);
	map.put("address2", address2);
	map.put("landmark", landmark);
	map.put("pin", pin);

	return map;
	}
	
	public void userDetailsFromAPI(String firstName, String lastName, String email, String motherName, String gender)
            throws Exception {
        extent.HeaderChildNode("Age Criteria");

        explicitWaitVisibility(RingUserDetailPage.objFirstName, 10);
        click(RingUserDetailPage.objFirstName, "First Name Field");
        type(RingUserDetailPage.objFirstName, firstName, "First Name field");

        explicitWaitVisibility(RingUserDetailPage.objLastName, 10);
        click(RingUserDetailPage.objLastName, "Last Name Field");
        type(RingUserDetailPage.objLastName, lastName, "Last Name field");
        hideKeyboard();
  
        Aclick(UserRegistrationPage.objMothersName, "Mother's Name Text Field");
        type(UserRegistrationPage.objMothersName, motherName, "Mother's Name Text Field");
        hideKeyboard();
        explicitWaitVisibility(RingUserDetailPage.objEmail, 10);
        click(RingUserDetailPage.objEmail, "Email Field");
        if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above popup")) {
            Aclick(RingLoginPage.objNoneBtn, "None of the above");
        }
        
        
        type(RingUserDetailPage.objEmail, email, "Email Filed");
        hideKeyboard();
        click(RingLoginPage.objGender, "Gender male is selected");
        click(RingLoginPage.objGender, "Gender male is selected");
    }
	
	public void dateOfBirthForAPI(String month, String date, String year) throws Exception {
		explicitWaitClickable(UserRegistrationPage.objUserDOB, 10);
		Aclick(UserRegistrationPage.objUserDOB, "DOB field");
		if (!verifyElementDisplayed(UserRegistrationPage.objGenderCancelBtn)) {
			Aclick(UserRegistrationPage.objUserDOB, "DOB field");
		}

		Aclick(UserRegistrationPage.objDatePickerYear, "Year field");
		clearField(UserRegistrationPage.objDatePickerYear, "Year field");
		type(UserRegistrationPage.objDatePickerYear, year, "Year field");

		explicitWaitVisibility(UserRegistrationPage.objDatePickerMonthSH, 10);
		Aclick(UserRegistrationPage.objDatePickerMonthSH, "Month field");
		clearField(UserRegistrationPage.objDatePickerMonthSH, "Month field");
		type(UserRegistrationPage.objDatePickerMonthSH, month, "Month field");

		Aclick(UserRegistrationPage.objDatePickerDateSH, "Date field");
		clearField(UserRegistrationPage.objDatePickerDateSH, "Date field");
		type(UserRegistrationPage.objDatePickerDateSH, date, "Date field");

		Aclick(UserRegistrationPage.objOK, "OK button");

	}
	
	public void addAddress(String room,String address1,String address2,String landmark,String pin) throws Exception {
		   extent.HeaderChildNode("RingPay App Merchant Flow");
		   explicitWaitVisibility(AddAddressPage.objAddCurrentAddressHeader, 30);
		   Aclick(AddAddressPage.objRoomNoField,"Room");
		   type(AddAddressPage.roomNopulkit,room,"Room");
		   Aclick(AddAddressPage.objAddressLineOneTextField,"AddressLine1");
		   type(AddAddressPage.objAddressLineOneTextField,address1,"AddressLine1");
		   Aclick(AddAddressPage.objAddressLineTwoTextField,"AddressLine2");
		   type(AddAddressPage.objAddressLineTwoTextField,address2,"AddressLine2");
		   /*Aclick(AddAddressPage.objLandmarkTextFields,"Landmark");
		   type(AddAddressPage.objLandmarkTextFieldspulkit,landmark,"Landmark");
		   hideKeyboard();*/
		   Aclick(AddAddressPage.objPinCodeField,"PinCode");
		   type(AddAddressPage.objPinCodeFieldpulkit,pin,"PinCode");
		   hideKeyboard();
		   explicitWaitVisibility(AddAddressPage.objSubmitBtn,10);
		   click(AddAddressPage.objSubmitBtn,"Submit button");
		   waitTime(5000);
}
	
	public void kycSkipped(String url, String gender, String encrypted_name, String portalEmail, String portalPassword,String portalOTP) throws Exception {
		extent.HeaderChildNode("RingPay KYC_Skip");
		getDriver().resetApp();
		HashMap mockUserDetails=mockUserAPI(url, gender, encrypted_name);
		
		String firstName=(String) mockUserDetails.get("firstName");
		String middleName=(String) mockUserDetails.get("middleName");
		String lastName=(String) mockUserDetails.get("lastName");
		String mobileNumber=(String) mockUserDetails.get("mobileNumber");
		String otp=(String) mockUserDetails.get("otp");
		String panCard=(String) mockUserDetails.get("panCard");
		String email=(String) mockUserDetails.get("email");
		String motherName=(String) mockUserDetails.get("motherName");
		String genders=(String) mockUserDetails.get("genders");
		String year=(String) mockUserDetails.get("year");
		String monthByName=(String) mockUserDetails.get("month");
		String date=(String) mockUserDetails.get("date");
		
		String roomno=(String) mockUserDetails.get("roomNo");
		String address1=(String) mockUserDetails.get("address1");
		String address2=(String) mockUserDetails.get("address2");
		String landmark=(String) mockUserDetails.get("landmark");
		String pin=(String) mockUserDetails.get("pin");
		
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		click(RingLoginPage.objLoginLink, "Signup/Login link");
		loginMobile();    
		mobileNoValidation1(mobileNumber);
		waitTime(5000);
		enterOtp(otp);
		readAndAccept();
	
		waitTime(40000);
		userDetailsFromAPI(firstName, lastName, email, motherName, genders);
		dateOfBirthForAPI(monthByName, date, year);
	
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		
		waitTime(5000);
		type(TestingPortalWebPage.emailField, portalEmail, "Email ID Entered");
		waitTime(1000);
		JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
		explicitWaitVisibility(TestingPortalWebPage.loginBtn, 10);
		clearField(TestingPortalWebPage.passwordField, "");
		waitTime(2000);
		type(TestingPortalWebPage.passwordField, portalPassword, "Portal Password");
		waitTime(2000);
		clearField(TestingPortalWebPage.otpField, "");
		waitTime(2000);
		type(TestingPortalWebPage.otpField, portalOTP, "OTP Entered");
		waitTime(2000);
		JSClick(TestingPortalWebPage.loginBtn, "Login Button");
		explicitWaitVisibility(TestingPortalWebPage.usersTab, 10);
		JSClick(TestingPortalWebPage.usersTab, "User Tab");
		explicitWaitVisibility(TestingPortalWebPage.dropDownUserRef, 10);
		JSClick(TestingPortalWebPage.dropDownUserRef, "Drop Down");
		selectByVisibleTextFromDD(TestingPortalWebPage.dropDownUserRef, "Mobile Number");
		type(TestingPortalWebPage.enterMobileNo, mobileNumber, "Portal Mobile Number");
		JSClick(TestingPortalWebPage.searchIcon, "Search Icon");
		waitTime(5000);
		String getReferenceNumber = "";
		for (int i = 0; i <= 2; i++) {
			try {
				getReferenceNumber = getWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered ']/tbody/tr[1]/td[1]")).getText();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Reference----------------------------" + getReferenceNumber);
		waitTime(2000);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		System.out.println("drag done");
		waitTime(2000);
		JSClick(TestingPortalWebPage.othersTab, "Other Tab");
		waitTime(1500);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		waitTime(2000);
		explicitWaitVisibility(TestingPortalWebPage.panNSDLDataTab, 20);
		
		JSClick(TestingPortalWebPage.panNSDLDataTab, "PAN NSDL Data");
		explicitWaitVisibility(TestingPortalWebPage.addNewIcon, 10);
		JSClick(TestingPortalWebPage.addNewIcon, "Add New Icon");
		explicitWaitVisibility(TestingPortalWebPage.firstName, 20);
		type(TestingPortalWebPage.firstName, firstName, "first Name");
		type(TestingPortalWebPage.middleName,middleName, "middle name");
		type(TestingPortalWebPage.lastName,lastName, "lst");
		type(TestingPortalWebPage.pan, panCard, "Pan No");
		JSClick(TestingPortalWebPage.panStatus, "pan statis");
		selectByVisibleTextFromDD(TestingPortalWebPage.panStatus, "Valid");
		JSClick(TestingPortalWebPage.panNsdlSubmitBtn, "Submit Button");
		
		
		ValidatableResponse responseSkipKyc = Utilities.skip_kyc("https://testing-gateway.test.paywithring.com/api/v1/update-upload-equifax/updateUploadEquifax",getReferenceNumber, panCard, firstName, middleName, lastName, encrypted_name);
		String message = responseSkipKyc.extract().jsonPath().getString("message");
		System.out.println("Skip_Kyc-------------" + message);
		
		BrowsertearDown();
		
		waitTime(10000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
	
		waitTime(10000);
		
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress(roomno,address1,address2,landmark,pin);
			 click(AddAddressPage.objSubmitButton,"Submit");	
		}
		waitTime(20000);
		explicitWaitVisibility(PAN_DetailsPage.btnyesThisIsMyPlan, 20);
		System.out.println(getText(PAN_DetailsPage.btnyesThisIsMyPlan));
		
		softAssertion.assertEquals("Confirm this is your PAN & name", getText(PAN_DetailsPage.txtTittle));
		softAssertion.assertEquals(panCard,getText(PAN_DetailsPage.PanNo));
		softAssertion.assertEquals(firstName+" "+middleName+" "+lastName, getText(PAN_DetailsPage.UserNameOfPAN));
		
		extent.extentLoggerPass("Popup Validation", "Hand Icon,Pan Number,Name of Pan Holder is visible_TC_Ring_Core_167");
		
		Back(2);
		logger.info("Back Button Pressed 2 Times");
		extent.extentLoggerPass("Back Button click", "Validated by doing back popup is not disappear_TC_Ring_Core_168");
		waitTime(5000);
		setWifiConnectionToONOFF("Off");
		waitTime(5000);
		Aclick(PAN_DetailsPage.btnnoThisIsNotMine, "No, This Is Not Mine");
		explicitWaitVisibility(PAN_DetailsPage.txtNoInternet, 20);
		softAssertion.assertEquals(" Check your connection & try again ", getText(PAN_DetailsPage.txtCheckyourInternet));
		logger.info("Internet Connection Validation");
		extent.extentLoggerPass("Validate Internet Connection Off", "Check Your Internet Connection_TC_Ring_Core_171");
		waitTime(5000);
		setWifiConnectionToONOFF("On");
		waitTime(8000);
		Aclick(PAN_DetailsPage.btnOkaygotIt, "Okay Got It");
		waitTime(5000);
		explicitWaitVisibility(PAN_DetailsPage.txtTittle, 20);
		waitTime(5000);
		setWifiConnectionToONOFF("Off");
		waitTime(5000);
		Aclick(PAN_DetailsPage.btnyesThisIsMyPlan, "Yes, This is My Pan");
		explicitWaitVisibility(PAN_DetailsPage.txtNoInternet, 20);
		softAssertion.assertEquals(" Check your connection & try again ", getText(PAN_DetailsPage.txtCheckyourInternet));
		logger.info("Internet Connection Validation");
		extent.extentLoggerPass("Validate Internet Connection Off", "Check Your Internet Connection_TC_Ring_Core_173");
		waitTime(5000);
		setWifiConnectionToONOFF("On");
		waitTime(8000);
		Aclick(PAN_DetailsPage.btnOkaygotIt, "Okay Got It");
		waitTime(25000);
		explicitWaitVisibility(PAN_DetailsPage.txtTittle, 20);
		Aclick(PAN_DetailsPage.btnnoThisIsNotMine, "No This is not Mine Button");
		explicitWaitVisibility(OfferPage.offerTitle, 20);
		logger.info("Offer Page Open When we Click on 'No this is not mine' Button");
		softAssertion.assertEquals("Accept Offer", getText(OfferPage.btnAcceptOffer));
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when network up then press_TC_Ring_Core_172");
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when Press we press on 'No this is not mine' Button_TC_Ring_Core_169");
		explicitWaitVisibility(OfferPage.btnAcceptOffer, 10);
		Aclick(OfferPage.ckbAcceptTermandcondition, "check box for term and conditions");
		Aclick(OfferPage.btnAcceptOffer, "Accept Offer");
		explicitWaitVisibility(TransactionPIN.txtTitleOfSetPin , 20);
		Aclick(TransactionPIN.objEnterPin , "Enter Pin");
		type(TransactionPIN.objEnterPin , "1111", "Enter Pin");
		Aclick(TransactionPIN.objReEnterPin, "Re-Enter Pin");
		type(TransactionPIN.objReEnterPin, "1111", "Re-Enter Pin");
		Aclick(TransactionPIN.objSubmit,"Submit Button");
		if (checkElementExist(HomePage.objAdCloseBtn, "Check")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
		
		explicitWaitVisibility(HomePage.homeTab, 20);
		ringPayLogout();
		
	HashMap mockUserDetailsForYesBtn=mockUserAPI(url, gender, encrypted_name);
		
		String firstNameForYesBtn=(String) mockUserDetailsForYesBtn.get("firstName");
		String middleNameForYesBtn=(String) mockUserDetailsForYesBtn.get("middleName");
		String lastNameForYesBtn=(String) mockUserDetailsForYesBtn.get("lastName");
		String mobileNumberForYesBtn=(String) mockUserDetailsForYesBtn.get("mobileNumber");
		String otpForYesBtn=(String) mockUserDetailsForYesBtn.get("otp");
		String panCardForYesBtn=(String) mockUserDetailsForYesBtn.get("panCard");
		String emailForYesBtn=(String) mockUserDetailsForYesBtn.get("email");
		String motherNameForYesBtn=(String) mockUserDetailsForYesBtn.get("motherName");
		String gendersForYesBtn=(String) mockUserDetailsForYesBtn.get("genders");
		String yearForYesBtn=(String) mockUserDetailsForYesBtn.get("year");
		String monthByNameForYesBtn=(String) mockUserDetailsForYesBtn.get("month");
		String dateForYesBtn=(String) mockUserDetailsForYesBtn.get("date");
		
		String roomnoForYesBtn=(String) mockUserDetailsForYesBtn.get("roomNo");
		String address1ForYesBtn=(String) mockUserDetailsForYesBtn.get("address1");
		String address2ForYesBtn=(String) mockUserDetailsForYesBtn.get("address2");
		String landmarkForYesBtn=(String) mockUserDetailsForYesBtn.get("landmark");
		String pinForYesBtn=(String) mockUserDetailsForYesBtn.get("pin");
		
		click(RingLoginPage.objLoginLink, "Signup/Login link");
		loginMobile();    
		mobileNoValidation1(mobileNumberForYesBtn);
		enterOtp(otp);
		waitTime(40000);
	
		userDetailsFromAPI(firstNameForYesBtn, lastNameForYesBtn, emailForYesBtn, motherNameForYesBtn, gendersForYesBtn);
		dateOfBirthForAPI(monthByNameForYesBtn, dateForYesBtn, yearForYesBtn);
		
	
        setPlatform("Web");
        new RingPayBusinessLogic("ring");
		
		waitTime(10000);
		type(TestingPortalWebPage.emailField, portalEmail, "Email ID Entered");
		waitTime(3000);
		JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
		explicitWaitVisibility(TestingPortalWebPage.loginBtn, 10);
		clearField(TestingPortalWebPage.passwordField, "");
		waitTime(2000);
		type(TestingPortalWebPage.passwordField, portalPassword, "Portal Password");
		waitTime(2000);
		clearField(TestingPortalWebPage.otpField, "");
		waitTime(2000);
		type(TestingPortalWebPage.otpField, portalOTP, "OTP Entered");
		waitTime(2000);
		JSClick(TestingPortalWebPage.loginBtn, "Login Button");
		explicitWaitVisibility(TestingPortalWebPage.usersTab, 10);
		JSClick(TestingPortalWebPage.usersTab, "User Tab");
		explicitWaitVisibility(TestingPortalWebPage.dropDownUserRef, 10);
		JSClick(TestingPortalWebPage.dropDownUserRef, "Drop Down");
		selectByVisibleTextFromDD(TestingPortalWebPage.dropDownUserRef, "Mobile Number");
		type(TestingPortalWebPage.enterMobileNo, mobileNumberForYesBtn, "Portal Mobile Number");
		JSClick(TestingPortalWebPage.searchIcon, "Search Icon");
		waitTime(5000);
		String getReferenceNumber1 = "";
		for (int i = 0; i <= 2; i++) {
			try {
				getReferenceNumber1 = getWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered ']/tbody/tr[1]/td[1]")).getText();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Referance----------------------------" + getReferenceNumber1);
		waitTime(2000);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		System.out.println("drag done");
		waitTime(2000);
		JSClick(TestingPortalWebPage.othersTab, "Other Tab");
		waitTime(1500);
		ScrollToTheElement(TestingPortalWebPage.dragDown);
		waitTime(2000);
		explicitWaitVisibility(TestingPortalWebPage.panNSDLDataTab, 20);
		
		JSClick(TestingPortalWebPage.panNSDLDataTab, "PAN NSDL Data");
		explicitWaitVisibility(TestingPortalWebPage.addNewIcon, 10);
		JSClick(TestingPortalWebPage.addNewIcon, "Add New Icon");
		explicitWaitVisibility(TestingPortalWebPage.firstName, 20);
		type(TestingPortalWebPage.firstName, firstNameForYesBtn, "first Name");
		type(TestingPortalWebPage.middleName,middleNameForYesBtn, "middle name");
		type(TestingPortalWebPage.lastName,lastNameForYesBtn, "Last Name");
		type(TestingPortalWebPage.pan, panCardForYesBtn, "Pan No");
		JSClick(TestingPortalWebPage.panStatus, "pan statis");
		selectByVisibleTextFromDD(TestingPortalWebPage.panStatus, "Valid");
		JSClick(TestingPortalWebPage.panNsdlSubmitBtn, "Submit Button");
		BrowsertearDown();
		
		ValidatableResponse responseSkipKyc1 = Utilities.skip_kyc("https://testing-gateway.test.paywithring.com/api/v1/update-upload-equifax/updateUploadEquifax",getReferenceNumber1, panCardForYesBtn, firstNameForYesBtn, middleNameForYesBtn, lastNameForYesBtn, encrypted_name);
		String message1 = responseSkipKyc1.extract().jsonPath().getString("message");
		System.out.println("Skip_Kyc-------------" + message1);
		
		
	    setPlatform("Android");
	    initDriver();
	       
	    verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		
		
		waitTime(10000);
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress(roomnoForYesBtn,address1ForYesBtn,address2ForYesBtn,landmarkForYesBtn,pinForYesBtn);
			click(AddAddressPage.objSubmitButton,"Submit");
		}
		
		
		explicitWaitVisibility(PAN_DetailsPage.btnyesThisIsMyPlan, 20);
		click(PAN_DetailsPage.btnyesThisIsMyPlan, "Yes, This is my PAN Button");
		
		explicitWaitVisibility(OfferPage.offerTitle, 20);
		logger.info("Offer Page Open When we Click on 'Yes, This is my PAN");
		softAssertion.assertEquals("Accept Offer", getText(OfferPage.btnAcceptOffer));
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when network up then press_TC_Ring_Core_170");
		extent.extentLoggerPass("Offer Page Visible", "Offer Page visible when Press we press on 'Yes, This is my PAN' Button_TC_Ring_Core_174");

		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"), prop.getproperty("InstaLoanMPIN"));
		if (checkElementExist(HomePage.objAdCloseBtn, "Check")) {
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
		}
		ringPayLogout();
	}	
//=====================Pan bottom sheet case if pan received from bureau and KYC get skipped End=========================================================	
//========================================Banner logic to HomePage Start=============================================================================
	public void bannerLogicOnHomePage(String url, String gender, String encrypted_name,String portalEmail,String portalPassword,String portalOTP) throws Exception {
		
		extent.HeaderChildNode("Banner logic on Home screen");
		getDriver().resetApp();
		/*------------------------------Data From API----------------------------*/
		
		HashMap mockUserDetails=mockUserAPI(url, gender, encrypted_name);
		String firstName=(String) mockUserDetails.get("firstName");
		String middleName=(String) mockUserDetails.get("middleName");
		String lastName=(String) mockUserDetails.get("lastName");
		String mobileNumber=(String) mockUserDetails.get("mobileNumber");
		String otp=(String) mockUserDetails.get("otp");
		String panCard=(String) mockUserDetails.get("panCard");
		String email=(String) mockUserDetails.get("email");
		String motherName=(String) mockUserDetails.get("motherName");
		String genders=(String) mockUserDetails.get("genders");
		String year=(String) mockUserDetails.get("year");
		String monthByName=(String) mockUserDetails.get("month");
		String date=(String) mockUserDetails.get("date");
		
		String roomno=(String) mockUserDetails.get("roomNo");
		String address1=(String) mockUserDetails.get("address1");
		String address2=(String) mockUserDetails.get("address2");
		String landmark=(String) mockUserDetails.get("landmark");
		String pin=(String) mockUserDetails.get("pin");
		
		/*------------------------------Front End----------------------------*/

		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		
		click(RingLoginPage.objLoginLink, "Signup/Login link");
		loginMobile();    
		mobileNoValidation1(mobileNumber);
		enterOtp(otp);
		readAndAccept();
	
		waitTime(40000);
		
		String userName=prop.getproperty("userName");
		String userLastName=prop.getproperty("lastName");
		String userMotherName=prop.getproperty("motherName");
		String userGender=prop.getproperty("gender");
		String userBirthDate=prop.getproperty("dateOfBirthDate");
		String userBirthMonth=prop.getproperty("dateOfBirthMonth");
		String userBirthYear=prop.getproperty("dateOfBirthYear");
		userDetailsFromAPI(userName, userLastName, userName+userLastName+new Random().nextInt(10000)+"@example.com", userMotherName, userGender);
		dateOfBirthForAPI(userBirthMonth, userBirthDate, userBirthYear);
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		waitTime(30000);
		if (verifyElementPresent(AddAddressPage.objAddCurrentAddressHeader, "address Page")) {
			addAddress(roomno,address1,address2,landmark,pin);
			click(AddAddressPage.objSubmitButton,"Submit");
		}
		explicitWaitVisibility(OfferPage.btnAcceptOffer, 10);
		Aclick(OfferPage.ckbAcceptTermandcondition, "check box for term and conditions");
		Aclick(OfferPage.btnAcceptOffer, "Accept Offer");
		explicitWaitVisibility(TransactionPIN.txtTitleOfSetPin , 20);
		Aclick(TransactionPIN.objEnterPin , "Enter Pin");
		type(TransactionPIN.objEnterPin , "1111", "Enter Pin");
		Aclick(TransactionPIN.objReEnterPin, "Re-Enter Pin");
		type(TransactionPIN.objReEnterPin, "1111", "Re-Enter Pin");
		Aclick(TransactionPIN.objSubmit,"Submit Button");
		if (verifyElementPresent(HomePage.objAdCloseBtn, "")) {
			
			explicitWaitVisibility(HomePage.objAdCloseBtn, 20);
			Aclick(HomePage.objAdCloseBtn, "Ad Cross Button");
			explicitWaitVisibility(HomePage.homeTab, 20);
		}
		
		if(verifyElementPresent(HomePage.purpleBannerVerifyNow,"Searching Purple Banner")){
		boolean purpleBanner=swipeElementAndroid(HomePage.bannerTray,HomePage.purpleBannerVerifyNow);
		logger.info("Purple Banner--Verify KYC Doucment");
		softAssertion.assertEquals("Verify your document to get", getText(HomePage.purpleBannerText1));
		extent.extentLoggerPass("Verify your document to get", "Purple Banner is visible with text 'Verify your document to get higher limit & cashback up to ₹100 Verify Now' ----TC_Ring_Core_260");
		Aclick(HomePage.purpleBannerVerifyNow, "KYC Documents Page");
		explicitWaitVisibility(KycDocument.kycHeader, 30);
		logger.info("KYC Documents Page Open");
		softAssertion.assertEquals("KYC Documents", getText(KycDocument.kycHeader));
		extent.extentLoggerPass("KYC Document Page", "After click on verify now of purple banner,Kyc Document page Open_TC_Ring_Core_261");
		Back(1);
		explicitWaitVisibility(KycDocument.txtPopupWhileBackBtn, 20);
		Aclick(KycDocument.yesBtn, "Yes Button");
		explicitWaitVisibility(HomePage.homeTab, 20);
		}
		
		swipeElementAndroid(HomePage.bannerTray,HomePage.lightBlueBannerTransferNow);
		
		waitTime(10000);
		explicitWaitVisibility(HomePage.lightBlueBannerTransferNow, 20);
		logger.info("Ligh Blue Banner----Transfer Now");
		softAssertion.assertEquals("Transfer your RING", getText(HomePage.lightBluetext2));
		extent.extentLoggerPass("Cash Crunch? Transfer your RING limit to your bank account", "Light Blue Banner is visible with text 'Cash Crunch? Transfer your RING limit to your bank account Transfer Now'");
		boolean green=swipeElementAndroid(HomePage.bannerTray,HomePage.greenBannerText1);
		System.out.println(green);
		waitTime(10000);

		if (green==true) {
			logger.info("micro payment flag is enabled for user from Configuration");
			System.out.println(getText(HomePage.greenBannerPayNow));
			extent.extentLoggerPass("micro payment flag", "micro payment flag is enabled for user from Configuration-----------TC_Ring_Core_265");
			extent.extentLoggerPass("RINGGGG! Transactions below", "Green Banner is visible with text 'RINGGGG! Transactions below 200 are absolutely FREE! Pay Now'--------------TC_Ring_Core_266");
			Aclick(HomePage.greenBannerPayNow, "Pay Now");
			
			explicitWaitVisibility(HomePage.objScanAnyQRToPay,10);
			String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
			softAssertion.assertEquals("Scan any QR code to pay",sScanAnyQRCode);
			verifyElementPresent(HomePage.objScanAnyQRToPay,getTextVal(HomePage.objScanAnyQRToPay,"Text"));
			
			//Scan here
			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            String projectPath=System.getProperty("user.dir");
            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
            waitTime(15000);
            BrowsertearDown();
            
            /*------------------------------Android----------------------------*/
            setPlatform("Android");
            initDriver();
			
			if(verifyElementPresent(PaymentPage.objAmountTextField,"Amount Text Field")) {
				   Aclick(PaymentPage.objAmountTextField, "Amount Field");
				   type(PaymentPage.objAmountTextField, "100", "Amount Field");
			}
			hideKeyboard();
			waitTime(2000);
			Aclick(PaymentPage.objPayNowBtn2, "Pay Now Button");
			waitTime(30000);
			if(verifyElementPresent(PaymentPage.objConfirmPayment,getTextVal(PaymentPage.objConfirmPayment,"Text"))){
				   Aclick(PaymentPage.objEditTransactionPin,"Edit Pin Field");
				   type(PaymentPage.objEditTransactionPin,"1111","Edit Pin Field");
				   Aclick(PaymentPage.objContinue,"Continue Button");
			}
			if(verifyElementPresent(PaymentPage.objPaymentDone,getTextVal(PaymentPage.objPaymentDone,"Text"))){
				   String sPaymentDone = getText(PaymentPage.objPaymentDone);
				   softAssertion.assertEquals("Payment Done!", sPaymentDone);
				   verifyElementPresent(PaymentPage.objGreenTickMark,"Payment Done green tick mark");
				   verifyElementPresent(PaymentPage.objRupeesLogo,getTextVal(PaymentPage.objRupeesLogo,"is Rupees Logo"));
				   verifyElementPresent(PaymentPage.objMerchantNameReceipt,getTextVal(PaymentPage.objMerchantNameReceipt,"Text"));
				   verifyElementPresent(PaymentPage.objTaxDateAndTime,getTextVal(PaymentPage.objTaxDateAndTime,"is Tax date and time"));
				   verifyElementPresent(PaymentPage.objSeekBar,"Seek Bar");
				   verifyElementPresent(PaymentPage.objAvailableLimit,getTextVal(PaymentPage.objAvailableLimit,"Text"));
				   verifyElementPresent(PaymentPage.ObjAvailableLimitAmount,getTextVal(PaymentPage.ObjAvailableLimitAmount,"is Available Limit Amount"));
				   verifyElementPresent(PaymentPage.objGoToHomePage,getTextVal(PaymentPage.objGoToHomePage,"Text"));
				   System.out.println("-----------------------------------------------------------");
				   Aclick(PaymentPage.objGoToHomePage, "Goto HomePage");
				   waitTime(5000);
				  if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp,"Text")) {
					Aclick(HomePage.noBtn, "No Button");
				  }
				}
		}else {
			logger.warn("micro payment flag is Not enabled for user from Configuration");
		}
		
		/*------------------------------WEB----------------------------*/
		
		setPlatform("Web");
		new RingPayBusinessLogic("ring");
		waitTime(3000);
		type(TestingPortalWebPage.emailField, portalEmail, "Email ID Entered");
		waitTime(3000);
		JSClick(TestingPortalWebPage.continueBtn, "Continue Button");
		explicitWaitVisibility(TestingPortalWebPage.loginBtn, 10);
		clearField(TestingPortalWebPage.passwordField, "");
		waitTime(2000);
		type(TestingPortalWebPage.passwordField, portalPassword, "Portal Password");
		waitTime(2000);
		clearField(TestingPortalWebPage.otpField, "");
		waitTime(2000);
		type(TestingPortalWebPage.otpField, portalOTP, "OTP Entered");
		waitTime(2000);
		JSClick(TestingPortalWebPage.loginBtn, "Login Button");
		explicitWaitVisibility(TestingPortalWebPage.usersTab, 10);
		JSClick(TestingPortalWebPage.usersTab, "User Tab");
		explicitWaitVisibility(TestingPortalWebPage.dropDownUserRef, 10);
		JSClick(TestingPortalWebPage.dropDownUserRef, "Drop Down");
		selectByVisibleTextFromDD(TestingPortalWebPage.dropDownUserRef, "Mobile Number");
		type(TestingPortalWebPage.enterMobileNo, mobileNumber, "Portal Mobile Number");
		JSClick(TestingPortalWebPage.searchIcon, "Search Icon");
		waitTime(5000);
		String getReferenceNumber = "";
		for (int i = 0; i <= 2; i++) {
			try {
				getReferenceNumber = getWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered ']/tbody/tr[1]/td[1]")).getText();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Referance----------------------------" + getReferenceNumber);
		
		BrowsertearDown();
		
		/*------------------------------WEB----------------------------*/
		
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/sign-in");
        waitTime(3000);
        explicitWaitVisibility(TestingPortalWebPage.emailFieldNewPortal, 20);
        type(TestingPortalWebPage.emailFieldNewPortal, "shashi.thakur@collabera.com", "Email Entered");
        JSClick(TestingPortalWebPage.continueNewButton,"Continue Button");
        explicitWaitVisibility(TestingPortalWebPage.tabBNPL, 20);
        JSClick(TestingPortalWebPage.tabBNPL, "BNPL Link");
        explicitWaitVisibility(TestingPortalWebPage.tabBNPL_Line, 20);
        JSClick(TestingPortalWebPage.tabBNPL_Line, "BNPL Line Link");
        explicitWaitVisibility(TestingPortalWebPage.iconBulkUpload, 20);
        waitTime(2000);
        JSClick(TestingPortalWebPage.iconBulkUpload, "Bulk Upload Icon");
        explicitWaitVisibility(TestingPortalWebPage.menuBulkuploadcashbackWhitelisting, 20);
        JSClick(TestingPortalWebPage.menuBulkuploadcashbackWhitelisting, "Bulk Upload Cashback WhiteListing");
        clickElementWithWebLocator(TestingPortalWebPage.uploadExcelFile);
        waitTime(3000);
        System.out.println(System.getProperty("user.dir"));
        String projectPath=System.getProperty("user.dir");
        //Enter Data into Excel
        
        /*------------------------------Write In Excel----------------------------*/
       
       
        ExcelFunctions ex=new ExcelFunctions();
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,0,getReferenceNumber);
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,1,prop.getproperty("cashbackPercent"));
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,2,prop.getproperty("billingMaxCap"));
		ex.writeXLSXFile(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx",1,4,prop.getproperty("expireDate"));
        waitTime(5000);//2022-11-25
        Robot rb = new Robot();
        // copying File path to Clipboard
        StringSelection str = new StringSelection(projectPath+"\\Mock_Files\\BULK_CASHBACK_WHITELISTING.xlsx");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        
        // release Contol+V for pasting
        
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        waitTime(5000);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        System.out.println("completed");
		waitTime(5000);
		JSClick(TestingPortalWebPage.submitBtn, "Submit Button");
		waitTime(10000);
		BrowsertearDown();

		/*------------------------------Android----------------------------*/
		
		setPlatform("Android");
		initDriver();
		Swipe("DOWN", 1);
		boolean yellow=swipeElementAndroid(HomePage.bannerTray,HomePage.txt1yellowBanner(prop.getproperty("cashbackPercent")));
		if (yellow==true) {
			logger.info("Yellow Banner--CashBack Banner is visible");
			softAssertion.assertEquals("Up to "+prop.getproperty("cashbackPercent")+"%* cashback on all transactions", getText(HomePage.txt1yellowBanner(prop.getproperty("cashbackPercent"))));
			extent.extentLoggerPass("CashBack Banner", "CashBack Banner is Visible--------------TC_Ring_Core_263");
			Aclick(HomePage.objScanAndPay, "Scan Button");
			
		//	Scan here
			/*-------------------Web-----------------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
            waitTime(25000);
            BrowsertearDown();
            
            /*------------------Android---------------------------------*/
            setPlatform("Android");
            initDriver();
            
			waitTime(30000);
			explicitWaitVisibility(PaymentPage.objAmountTextField, 30);
			if(verifyElementPresent(PaymentPage.objAmountTextField,"Amount Text Field")) {
				   Aclick(PaymentPage.objAmountTextField, "Amount Field");
				   type(PaymentPage.objAmountTextField, "80", "Amount Field");
			}
			Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
			waitTime(20000);
			if(verifyElementPresent(PaymentPage.objConfirmPayment,getTextVal(PaymentPage.objConfirmPayment,"Text"))){
				   Aclick(PaymentPage.objEditTransactionPin,"Edit Pin Field");
				   type(PaymentPage.objEditTransactionPin,"1111","Edit Pin Field");
				   Aclick(PaymentPage.objContinue,"Continue Button");
			}
			if(verifyElementPresent(PaymentPage.objPaymentDone,getTextVal(PaymentPage.objPaymentDone,"Text"))){
				   String sPaymentDone = getText(PaymentPage.objPaymentDone);
				   softAssertion.assertEquals("Payment Done!", sPaymentDone);
				   verifyElementPresent(PaymentPage.objGreenTickMark,"Payment Done green tick mark");
				   verifyElementPresent(PaymentPage.objRupeesLogo,getTextVal(PaymentPage.objRupeesLogo,"is Rupees Logo"));
				   verifyElementPresent(PaymentPage.objMerchantNameReceipt,getTextVal(PaymentPage.objMerchantNameReceipt,"Text"));
				   verifyElementPresent(PaymentPage.objTaxDateAndTime,getTextVal(PaymentPage.objTaxDateAndTime,"is Tax date and time"));
				   verifyElementPresent(PaymentPage.objSeekBar,"Seek Bar");
				   verifyElementPresent(PaymentPage.objAvailableLimit,getTextVal(PaymentPage.objAvailableLimit,"Text"));
				   verifyElementPresent(PaymentPage.ObjAvailableLimitAmount,getTextVal(PaymentPage.ObjAvailableLimitAmount,"is Available Limit Amount"));
				   verifyElementPresent(PaymentPage.objGoToHomePage,getTextVal(PaymentPage.objGoToHomePage,"Text"));
				   System.out.println("-----------------------------------------------------------");
				   Aclick(PaymentPage.objGoToHomePage, "Goto HomePage");
				   logger.info("Payment Completed");
				   extent.extentLoggerPass("Payment Done Through Yellow Banner", "After coming yellow banner through scan button transection completed--------------TC_Ring_Core_264");
				   waitTime(5000);
				   if (verifyElementPresent2(HomePage.txtAreYouEnjoyingApp,"Text")) {
						Aclick(HomePage.noBtn, "No Button");
					  }
			}
		}
		else {
			logger.info("Yellow Banner not visible");
			extent.extentLogger("Yellow Banner", "Yellow Banner not visible");
		}	
	}
//===========================================Banner logic to HomePage end=========================================================	
//===============================================Transaction Set pin Start========================================================

	public void transactionSetPin() throws Exception {
		extent.HeaderChildNode("Transaction Set Pin Page Validation");
		
		promoCodeFlowSetPin();
		setPinMerchantFlowWithLocationOff();
		SetPinMerchantFlowWithLocationOn();
	}
//=========================================Promocode transaction Set pin=============================================================
	public void promoCodeFlowSetPin() throws Exception {
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		
		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		//instaNewCommunicationAddress();
		addAddress("12","asaaw","saass","ddj","560102");
		waitTime(5000);
	
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresent(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));

		if (verifyElementPresent(UserRegistrationPage.objSetPin, getTextVal(UserRegistrationPage.objSetPin, "Test"))) {
		   String sSetPin = getText(UserRegistrationPage.objSetPin);
		   softAssertion.assertEquals(sSetPin, "Set Pin");
		   logger.info("TC_Ring_Core_190,Navigated to SET PIN screen,after clicking on Accept Offer");
		   extent.extentLoggerPass("TC_Ring_Core_190", "Navigated to SET PIN screen,after clicking on Accept Offer");
		   System.out.println("-----------------------------------------------------------");
		}
		transactionSetPinForPromoCodeFlow();
	}

	public void transactionSetPinForPromoCodeFlow() throws Exception {
		if (verifyElementPresent(UserRegistrationPage.objEnterPin1, "Enter Pin first Element")) {
			Aclick(UserRegistrationPage.objEnterPin1, "Enter pin first Element");
			type(UserRegistrationPage.objEnterPin1, "1", "Enter pin first Element");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			hideKeyboard();
			verifyElementPresent(UserRegistrationPage.objErrorMessage, getTextVal(UserRegistrationPage.objErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objErrorMessage, "Please enter new Pin");
			logger.info("TC_Ring_Core_192,Please enter new Pin Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_192", "TC_Ring_Core_192,Please enter new Pin Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(UserRegistrationPage.objEnterPin2, "Enter Pin Second Element")) {
			Aclick(UserRegistrationPage.objEnterPin2, "Enter pin Second Element");
			type(UserRegistrationPage.objEnterPin2, "1", "Enter pin Second Element");
			hideKeyboard();
			verifyElementPresent(UserRegistrationPage.objErrorMessage, getTextVal(UserRegistrationPage.objErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objErrorMessage, "Please enter new Pin");
			logger.info("TC_Ring_Core_193,Please enter new Pin Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_193", "TC_Ring_Core_193,Please enter new Pin Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(UserRegistrationPage.objEnterPin3, "Enter Pin Third Element")) {
			Aclick(UserRegistrationPage.objEnterPin3, "Enter pin Third Element");
			type(UserRegistrationPage.objEnterPin3, "1", "Enter pin Third Element");
			hideKeyboard();
			verifyElementPresent(UserRegistrationPage.objErrorMessage, getTextVal(UserRegistrationPage.objErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objErrorMessage, "Please enter new Pin");
			logger.info("TC_Ring_Core_194,Please enter new Pin Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_194", "TC_Ring_Core_194,Please enter new Pin Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}
		Aclick(UserRegistrationPage.objEnterPin4, "Enter pin Fourth Element");
		type(UserRegistrationPage.objEnterPin4, "1", "Enter pin Fourth Element");
		if (verifyElementPresent(UserRegistrationPage.objHide, "asterisk")) {
			for (int nPin = 1; nPin >= 4; nPin++) {
				String sAsterisk = getText(UserRegistrationPage.objHide(nPin));
				softAssertion.assertEquals(sAsterisk, "*");
			}
			logger.info("TC_Ring_Core_195,Pin should displayed as asterisk mark");
			extent.extentLoggerPass("TC_Ring_Core_195", "TC_Ring_Core_195,Pin should displayed as asterisk mark");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(UserRegistrationPage.objEnterPin, "Enter pin Data")) {
			String sEnteredPin = getText(UserRegistrationPage.objEnterPin);
			int nEnterPin = Integer.parseInt(sEnteredPin);
			softAssertion.assertEquals(sEnteredPin, 1111, "Return True When Both are Integers");
			logger.info("TC_Ring_Core_191, Entered Pin " + nEnterPin + " is an Integer");
			extent.extentLoggerPass("TC_Ring_Core_191", "TC_Ring_Core_191,Entered Pin " + nEnterPin + " is an Integer");
			System.out.println("-----------------------------------------------------------");
		}

		if (verifyElementPresent(UserRegistrationPage.objReEnterPin, "Re-Enter Pin")) {
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, prop.getproperty("InValidPin"), "Re-Enter Pin");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			verifyElementPresent(UserRegistrationPage.objSorryErrorMessage, getTextVal(UserRegistrationPage.objSorryErrorMessage, "Error Message"));
			softAssertion.assertEquals(UserRegistrationPage.objSorryErrorMessage, "Sorry! Pin does not match, please enter again");
			logger.info("TC_Ring_Core_196,Sorry! Pin does not match, please enter again Error Message is displayed");
			extent.extentLoggerPass("TC_Ring_Core_196", "TC_Ring_Core_196,Sorry! Pin does not match, please enter again Error Message is displayed");
			System.out.println("-----------------------------------------------------------");
		}

		if (verifyElementPresent(UserRegistrationPage.objReEnterPin, "Re-Enter Pin")) {
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			clearField(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, prop.getproperty("ValidPin"), "Re-Enter Pin");
			setLocationConnectionToONOFF("Off");

			explicitWaitVisibility(UserRegistrationPage.objSubmit, 10);
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			click(UserRegistrationPage.objGotIt, "OK Got it Button");
			click(RingLoginPage.objEnterPin, "Mpin Field");
			type(RingLoginPage.objEnterPin, "1111", "MPIN Field");
			click(RingUserDetailPage.objReEnterPin, "Re-Enter Pin Field");
			type(RingUserDetailPage.objReEnterPin, "1111", "Re-Enter pin Field");
			Aclick(UserRegistrationPage.objSubmit, "Submit Button");
			if (verifyElementPresent(UserRegistrationPage.objTransactionPinSet, "Transaction Set pin")) {
				verifyElementPresent(UserRegistrationPage.objTransactionPinSet, getTextVal(UserRegistrationPage.objTransactionPinSet, "Text"));
				logger.info("TC_Ring_Core_197, Transaction pin is set successfully");
				extent.extentLoggerPass("TC_Ring_Core_197", "TC_Ring_Core_197,Transaction pin is set successfully");
				System.out.println("-----------------------------------------------------------");
			}
			setLocationConnectionToONOFF("on");
			waitTime(10);
			explicitWaitVisibility(HomePage.objCloseButton, 10);
			Aclick(HomePage.objCloseButton, "Close Button");
			String sHome = getText(HomePage.objHome);
			softAssertion.assertEquals(sHome, "Home");
			logger.info("TC_Ring_Core_199, Transaction pin is set successfully with location off");
			extent.extentLoggerPass("TC_Ring_Core_199", "TC_Ring_Core_199,Transaction pin is set successfully with location off");
			System.out.println("-----------------------------------------------------------");
		}

		ringPayLogout();
	}
//=========================================Promocode transaction Set pin End===========================================================	
//==========================================Merchantflow transaction set pin===========================================================

	public void setPinMerchantFlowWithLocationOff() throws Exception {
		   extent.HeaderChildNode("SetPin Merchant Flow Without Location");
		   merchantPage();
		   setLocationConnectionToONOFF("Off");
		   Aclick(UserRegistrationPage.objSubmit, "Submit Button");
		   if (verifyElementPresent(UserRegistrationPage.objAllowLocationPopup, "Location Permission Required Text")) {
		      verifyElementPresent(UserRegistrationPage.objAllowLocationPopup,getTextVal(UserRegistrationPage.objAllowLocationPopup,"Text"));
		      verifyElementPresent(UserRegistrationPage.objAllowLocationBtn,getTextVal(UserRegistrationPage.objAllowLocationBtn,"Text"));
		      verifyElementPresent(UserRegistrationPage.objNotNowLocationBtn, getTextVal(UserRegistrationPage.objNotNowLocationBtn, "Text"));
		      Aclick(UserRegistrationPage.objNotNowLocationBtn, "Not Now Button");
		      waitTime(10);
		      Back(1);
		      explicitWaitVisibility(HomePage.objCloseButton, 10);
		      Aclick(HomePage.objCloseButton, "Close Button");
		      String sHome = getText(HomePage.objHome);
		      softAssertion.assertEquals(sHome, "Home");
		      logger.info("TC_Ring_Core_201, Transaction pin is not set and Navigated to Home Page");
		      extent.extentLoggerPass("TC_Ring_Core_201", "TC_Ring_Core_201,Transaction pin is not set and Navigated to Home Page");
		      System.out.println("-----------------------------------------------------------");
		      ringPayLogout();
		   }
		   setLocationConnectionToONOFF("On");
		}
	
	public void SetPinMerchantFlowWithLocationOn() throws Exception {
		   extent.HeaderChildNode("SetPin Merchant Flow Without Location");
		   merchantPage();
		   setLocationConnectionToONOFF("Off");
		   Aclick(UserRegistrationPage.objSubmit, "Submit Button");
		   if (verifyElementPresent(UserRegistrationPage.objAllowLocationPopup, "Location Permission Required Text")) {
		      verifyElementPresent(UserRegistrationPage.objAllowLocationPopup, getTextVal(UserRegistrationPage.objAllowLocationPopup, "Text"));
		      verifyElementPresent(UserRegistrationPage.objAllowLocationBtn, getTextVal(UserRegistrationPage.objAllowLocationBtn, "Text"));
		      verifyElementPresent(UserRegistrationPage.objNotNowLocationBtn, getTextVal(UserRegistrationPage.objNotNowLocationBtn, "Text"));
		      logger.info("TC_Ring_Core_198 , Location permission Required Popup is displayed");
		      extent.extentLoggerPass("TC_Ring_Core_198", "TC_Ring_Core_198 , Location permission Required Popup is displayed");
		      System.out.println("-----------------------------------------------------------");
		      Aclick(UserRegistrationPage.objAllowLocationBtn, "Allow Button on Location Permission Required");
		      Aclick(UserRegistrationPage.objOkBtn, "Ok Button");
		      Aclick(UserRegistrationPage.objSubmit, "Submit");
		      logger.info("TC_Ring_Core_200, Transaction pin is set successful also user completes the first transaction");
		      extent.extentLoggerPass("TC_Ring_Core_200", "TC_Ring_Core_200, Transaction pin is set successful also user completes the first transaction");
		      System.out.println("-----------------------------------------------------------");
		      waitTime(10000);
		      verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
		      waitTime(5000);
		      verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
//		      verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
		      String sHome = getText(HomePage.objHome);
		      softAssertion.assertEquals(sHome, "Home");
		   }
		   ringPayLogout();
		}
	
	public void merchantPage() throws Exception {
		//getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresent(RingLoginPage.objScanQRPage, getText(RingLoginPage.objScanQRPage));

		/*------------------------------WEB----------------------------*/
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		String projectPath=System.getProperty("user.dir");
		System.out.println(projectPath);
		//getWebDriver().get("");
        getWebDriver().get(projectPath+"//Mock_Files/qrcode.png");
        waitTime(10000);
        BrowsertearDown();
        
        /*------------------------------Android----------------------------*/
        setPlatform("Android");
		initDriver();
		waitTime(5000);

		click(RingLoginPage.objMakePaymentLetsRingItBtn, getText(RingLoginPage.objMakePaymentLetsRingItBtn));
		click(RingLoginPage.objMakePaymentLetsRingItBtn, getText(RingLoginPage.objMakePaymentLetsRingItBtn));
		click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
		type(RingPayMerchantFlowPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
		verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn, getText(RingLoginPage.objMakePaymentPageProceedBtn));
		verifyIsElementDisplayed(RingPayMerchantFlowPage.objLoginPageHeader, getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA, getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA, getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA, getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();

		mobileNoValidation1("9" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(40000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		waitTime(6000);
		instaNewCommunicationAddress();
		waitTime(5000);
		verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
		softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
		verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
		verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
		verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
		verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
		verifyElementPresent(PromoCodeOfferPage.objRBIMsg, getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMsg));
		verifyElementPresent(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn, getText(TermsAndConditionPage.objAcceptText));

		verifyElementPresentAndClick(PromoCodeOfferPage.objFeeDetail, getText(PromoCodeOfferPage.objFeeDetail));
		verifyElementPresent(PromoCodeOfferPage.objFeeDetailPage, getText(PromoCodeOfferPage.objFeeDetailPage));
		click(PromoCodeOfferPage.objFeeDetailsCrossBtn, "Cross Button");

		verifyElementPresent(PromoCodeOfferPage.objTermsAndCondition, getTextVal(PromoCodeOfferPage.objTermsAndCondition, "Text"));
		click(PromoCodeOfferPage.objTermsAndCondition, "Terms And Conditions");
		waitTime(5000);
		verifyElementPresent(TermsAndConditionPage.objTAndCondition, getTextVal(TermsAndConditionPage.objTAndCondition, "Text"));
		Back(1);

		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn, getText(TermsAndConditionPage.objAcceptText));
		waitTime(1000);
		System.out.println(getText(PromoCodeOfferPage.objToastMsg));
		logger.info(getText(PromoCodeOfferPage.objToastMsg));
		extent.extentLoggerPass("Toast Msg", getText(PromoCodeOfferPage.objToastMsg));
		waitTime(5000);

		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn, getText(TermsAndConditionPage.objAcceptText));

		if (verifyElementPresent(UserRegistrationPage.objSetPin, getTextVal(UserRegistrationPage.objSetPin, "Test"))) {
			String sSetPin = getText(UserRegistrationPage.objSetPin);
			softAssertion.assertEquals(sSetPin, "Set Pin");
			logger.info("TC_Ring_Core_189,Navigated to SET PIN screen,after clicking on Accept and Pay");
			extent.extentLoggerPass("TC_Ring_Core_189", "TC_Ring_Core_189,Navigated to SET PIN screen,after clicking on Accept and Pay");
			Aclick(UserRegistrationPage.objEnterPin, "Enter Pin");
			type(UserRegistrationPage.objEnterPin, "1111", "Enter Pin");
			Aclick(UserRegistrationPage.objReEnterPin, "Re-Enter Pin");
			type(UserRegistrationPage.objReEnterPin, "1111", "Re-Enter Pin");
		}
	}
//=============================================Transaction Set Pin End ==========================================================
//=========================================Check Payment multiple Cases Start====================================================
	public void repaymentMultipleCases() throws Exception {
		extent.HeaderChildNode("Check payment page multiple cases");
		getDriver().resetApp();
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		mobileNoValidation1(prop.getproperty("editMob"));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();

		//above code not required if user already inside homepage
		explicitWaitVisibility(HomPageNew.objAdHeader, 10);
		Aclick(HomPageNew.objAdCloseBtn, "AD Close button");
		explicitWaitClickable(HomPageNew.objPayEarlyBtn, 10);
		Aclick(HomPageNew.objPayEarlyBtn, "Pay Early Button");
		explicitWaitVisibility(HomPageNew.objRepaymentHeader, 10);
		waitTime(3000);
		Aclick(HomPageNew.objAmtToBeRadio, "Amount to be paid radio button");
		waitTime(3000);
		hideKeyboard();
		explicitWaitVisibility(HomPageNew.objNetBankingOption, 10);
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objFirstError, 10);
		String firstErrorTxt = getText(HomPageNew.objFirstError);
		softAssertion.assertEquals("Please enter amount", firstErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_243","TC_Ring_Payment_243 - Verify payment page by keeping other amount field empty");

		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "0", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objSecondError, 10);
		String secondErrorTxt = getText(HomPageNew.objSecondError);
		softAssertion.assertEquals("Minimum amount should be ₹1", secondErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_244","TC_Ring_Payment_244 - Verify payment page by entering amount as 0 in other amount field");

		clearField(HomPageNew.objAmtRepayText, "Amount repay text field");

		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "7777", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objThirdError, 10);
		String thirdErrorTxt = getText(HomPageNew.objThirdError);
		softAssertion.assertEquals("Amount is greater than payable amount.", thirdErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_245","TC_Ring_Payment_245 - Verify payment page by entering amount greater than Total Payable Amount");

		clearField(HomPageNew.objAmtRepayText, "Amount repay text field");
		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "10,20,", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objFourthError, 10);
		String fourthErrorTxt = getText(HomPageNew.objFourthError);
		softAssertion.assertEquals("Please enter valid amount", fourthErrorTxt);
		extent.extentLoggerPass("TC_Ring_Payment_246","TC_Ring_Payment_246 - Verify payment page by entering invalid amount as \"10,20,\" in other amount field");

		clearField(HomPageNew.objAmtRepayText, "Amount repay text field");
		Aclick(HomPageNew.objAmtRepayText, "Amount repay text field");
		type(HomPageNew.objAmtRepayText, "17", "Amount repay text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		waitTime(3000);
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objNetbanking, 30);
		Aclick(HomPageNew.objNetbanking, "Netbanking");
		explicitWaitVisibility(HomPageNew.objSelectBankHeader, 10);
		Aclick(HomPageNew.objSBIBank, "SBI bank");
		Aclick(HomPageNew.objPayNowBtn, "Pay Now Button");
		explicitWaitVisibility(HomPageNew.objPayFail, 10);
		extent.extentLoggerPass("TC_Ring_Payment_247","TC_Ring_Payment_247 - Verify payment page by entering amount less than 20rs if Partner is RAZORPAY or PAYNIMO");
		Aclick(HomPageNew.objTryAgain, "Try Again button");
		Back(3);

		explicitWaitVisibility(HomPageNew.objRepaymentHeader, 10);
		Aclick(HomPageNew.objAmtRepayText, "Amount to be paid text field");
		clearField(HomPageNew.objAmtRepayText, "Amount to be paid text field");
		type(HomPageNew.objAmtRepayText, "20", "Amount to be paid text field");
		hideKeyboard();
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		waitTime(3000);
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objNetbanking, 10);
		extent.extentLoggerPass("TC_Ring_Payment_248","TC_Ring_Payment_248 - Verify payment page by entering amount equal to 20rs");
		Aclick(HomPageNew.objNetbanking, "Netbanking");
		explicitWaitVisibility(HomPageNew.objSelectBankHeader, 10);
		Aclick(HomPageNew.objSBIBank, "SBI bank");
		Aclick(HomPageNew.objPayNowBtn, "Pay Now Button");
		explicitWaitVisibility(HomPageNew.objSuccessBtn, 10);
		Aclick(HomPageNew.objSuccessBtn, "Success Button");
		explicitWaitVisibility(HomPageNew.objRepaySuccess, 10);
		Aclick(HomPageNew.objHomePage, "Go to homepage button");
		explicitWaitClickable(HomPageNew.objPayEarlyBtn, 10);
		Aclick(HomPageNew.objPayEarlyBtn, "Pay Early Button");
		explicitWaitVisibility(HomPageNew.objRepaymentHeader, 10);
		waitTime(3000);
		Aclick(HomPageNew.objViewDetails, "View Details");
		explicitWaitVisibility(HomPageNew.objPayDate, 10);
		explicitWaitVisibility(HomPageNew.objTotSpends, 10);
		explicitWaitVisibility(HomPageNew.objTransacFee, 10);
		explicitWaitVisibility(HomPageNew.objTotAmt, 10);
		extent.extentLoggerPass("TC_Ring_Payment_249","TC_Ring_Payment_249 - To Verify user clicks on View Details on Payment screen");
		Back(2);
		explicitWaitVisibility(RingLoginPage.objAvailLimitHeader, 10);
		ringPayLogout();
	}
//=============================================Check Payment multiple Cases End===================================================	
//================================================Bank transfer Flow Start========================================================
	public void BankTransferModule(String accountNo, String name) throws Exception
    {
        extent.HeaderChildNode("RingPay  Transfer Flow");
        getDriver().resetApp();
        if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
        verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		mobileNoValidation1(RandomIntegerGenerator(10));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(60000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		//instaNewCommunicationAddress();
		addAddress("12","asaaw","saass","ddj","560102");
		waitTime(5000);
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("NewMPIN"),prop.getproperty("NewMPIN"));
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
        
        click(BankTransferModule.objMoretTab, "Clicked On More Button");
        if (verifyElementPresent(BankTransferModule.objBankTransfer,getTextVal(BankTransferModule.objBankTransfer, "Module"))) {
            click(BankTransferModule.objBankTransfer, "Bank Transfer Module");
            logger.info("Navigated to Add Bank Account Page");
            extent.extentLogger("Account Page", "Navigated to Add Bank Account Page");
            extent.extentLogger("TC_Ring_Core_250", "TC_Ring_Core_235, To Verify when user clicks on bank transfer ");
            waitTime(2000);
            extent.extentLogger("TC_Ring_Core_257", "'TC_Ring_Core_257' To verify user enter valid account No and invalid account holder name as per user name");
            addbankAccount(accountNo, name);

           String buttonName = getText(BankTransferModule.objOkayBtn);
            if (buttonName.equalsIgnoreCase("okay") || buttonName.equalsIgnoreCase("Re-Enter Bank Details")) {
                click(BankTransferModule.objOkayBtn, getTextVal(BankTransferModule.objOkayBtn, "Button"));
                explicitWaitVisibility(BankTransferModule.objAddNewBankAccountBtn, 10);
                click(BankTransferModule.objAddNewBankAccountBtn,getTextVal(BankTransferModule.objAddNewBankAccountBtn, "Button"));
                addbankAccount("1", "Sunil");
                extent.extentLogger("TC_Ring_Core_258", "'TC_Ring_Core_258'To verify user enter invalid account No and Valid account holder name as per user name");
                click(BankTransferModule.objOkayBtn, getTextVal(BankTransferModule.objOkayBtn, "Button"));
                extent.extentLogger("TC_Ring_Core_259", "'TC_Ring_Core_259' To Verify user clicks on Okay got button error validation screen ");
            }

           explicitWaitVisibility(BankTransferModule.objMoretTab, 10);
            Aclick(BankTransferModule.objMoretTab, "Clicked On More Button");
            explicitWaitVisibility(BankTransferModule.objBankTransfer, 10);
            Aclick(BankTransferModule.objBankTransfer, "Bank Transfer Module");
            click(BankTransferModule.objAddNewBankAccountBtn,getTextVal(BankTransferModule.objAddNewBankAccountBtn, "Button"));
            extent.extentLogger("TC_Ring_Core_253", "'TC_Ring_Core_253' To verify user enter valid account No and Valid account holder name as per user name");
            addbankAccount("5", "Sunil");
            extent.extentLogger("TC_Ring_Core_257", "'TC_Ring_Core_257' To verify user enter valid account No and invalid account holder name as per user name");
            explicitWaitVisibility(BankTransferModule.objMakePaymentPage, 10);
            verifyElementPresent(BankTransferModule.objMakePaymentPage, "Make Payment Page Displayed");

           click(BankTransferModule.objEnterAmountTextField, "Amount Text Field");
            type(BankTransferModule.objEnterAmountTextField, "1", "Amount Text Field");
            logger.info(getTextVal(BankTransferModule.objTransactionFeeMsg, "Fee Message is Displayed"));
            extent.extentLogger("Fee Message",getTextVal(BankTransferModule.objTransactionFeeMsg, "Fee Message is Displayed"));
            click(BankTransferModule.objPayNowBtn, "Pay Now Button");
            extent.extentLogger("TC_Ring_Core_254,TC_Ring_Core_255","To verify user continue on make payment page");

           // Confirm payment Page
            verifyElementPresent(BankTransferModule.objEnterPinPage, "Confirm Payment Page");
            logger.info("User Navigaed to Confirm Payment Page");
            extent.extentLogger("Enter Pin", "User Navigaed to Confirm Payment Page");
            explicitWaitVisibility(BankTransferModule.objEnterPinTextField, 10);
            click(BankTransferModule.objEnterPinTextField, "Enter Pin Field");
            type(BankTransferModule.objEnterPinTextField, "1234", "Enter Pin Field");
            click(BankTransferModule.objProceedBtn, "Proceed Button");
            extent.extentLogger("TC_Ring_Core_256","TC_Ring_Core_241 To Verify user should able to continue with valid Pin");
 
            verifyElementPresent(BankTransferModule.objStatusPage, "Payment Status Page");
            Aclick(BankTransferModule.objHomePageBtn, "Go to Homepage");
            waitTime(2000);
            if (checkElementExist1(BankTransferModule.objEnjoyingPopup,"Are you Enjoying pop up")) {
                click(BankTransferModule.objYesBtn, "Yes Button");
                click(BankTransferModule.objRateExperience, "Rate Your Experience pop up");
            }
            ringPayLogout();
        } 
    }
	public void addbankAccount(String accountNo, String name) throws Exception
    {
        explicitWaitVisibility(BankTransferModule.objIfscTextField, 20);
        click(BankTransferModule.objIfscTextField, "IFSC Code Text Field");
        type(BankTransferModule.objIfscTextField, "SBIN4566777", "IFSC Code Text Field");
        logger.warn(getTextVal(BankTransferModule.objIFSCErrorMsg, "Error Message"));
        extent.extentLoggerWarning("Error Message", getTextVal(BankTransferModule.objIFSCErrorMsg, "Error Message"));
        clearField(BankTransferModule.objIfscTextField, "IFSC Code Text Field");
        type(BankTransferModule.objIfscTextField, "SBIN0003456", "IFSC Code Text Field");
        logger.info("Branch Name AutoPopulated "+getText(BankTransferModule.objBranchName));
        extent.extentLogger("BranchName", "Branch Name AutoPopulated "+getText(BankTransferModule.objBranchName));
        extent.extentLogger("TC_Ring_Core_251,TC_Ring_Core_252", "TC_Ring_Core_251,TC_Ring_Core_252 To verify User enter valid and invalid IFSC code ");
        
        explicitWaitVisibility(BankTransferModule.objAccountNumberField, 10);
        click(BankTransferModule.objAccountNumberField, "Account Number Field");
        accountNo = accountNo + RandomIntegerGenerator(5);
        type(BankTransferModule.objAccountNumberField, accountNo, "Account Number Text Field");
        
        explicitWaitVisibility(BankTransferModule.ObjConfirmAccountNumberField, 10);
        click(BankTransferModule.ObjConfirmAccountNumberField, "Confirm Account Number Field");
        type(BankTransferModule.ObjConfirmAccountNumberField, accountNo, "Account Number Text Field"); 
        hideKeyboard();
        waitTime(2000);
        Swipe("Up",2);
        waitTime(2000);
        click(BankTransferModule.objAccountHolderName, "Account Holder Name");
        type(BankTransferModule.objAccountHolderName, name, "Account Number Text Field");        
        hideKeyboard();        
        doubleTap(BankTransferModule.objAccountTypeField, 2,"Account Type Field");
        explicitWaitVisibility(BankTransferModule.objSavings, 10);
        click(BankTransferModule.objSavings, "Savings Type");
//        if(!checkElementExist(BankTransferModule.objSavings))
//        {
//            click(BankTransferModule.objAccountTypeField, "Account Type");
//            click(BankTransferModule.objSavings, "Savings Type");
//        }    
        click(BankTransferModule.objContinueButton, "Continue Button");
        explicitWaitVisibility(BankTransferModule.objBottomSheetPopup, 10);
        verifyElementExist(BankTransferModule.objBottomSheetPopup, "Verify Bank Details Pop Up");
        click(BankTransferModule.objConfirmBtn, "Confirm Button");
    }	
//===============================================Bank transfer flow ends==========================================================	
//===============================================Add Address Flow Start===============================================================
	public void addAddressFlow() throws Exception {
        extent.HeaderChildNode("RingPay Add Address Flow");
        getDriver().resetApp();
        waitTime(4000);
        if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
        verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
		softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
		logger.info("User redirected to Signup/Login Screen");
		extent.extentLogger("login", "User redirected to Signup/Login Screen");
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
		verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
		loginMobile();
		mobileNoValidation1("7" + RandomIntegerGenerator(9));
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(60000);
		userDetails();
		dateOfBirth("Feb", "10", "1996");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(15000);
        
        if(verifyElementPresent(AddAddresPage.objAddressHeader,"Add Current Address Page Header")){
        	extent.extentLoggerPass("TC_Ring_Core_116", "TC_Ring_Core_116 - To verfiy Address page");
            verifyElementExist(AddAddresPage.objRoomNoTextField, getTextVal(AddAddresPage.objRoomNoTextField, "Text Field"));
            click(AddAddresPage.objRoomNoTextField, getTextVal(AddAddresPage.objRoomNoTextField,"Text Field"));
            type(AddAddresPage.objRoomNoTextField, "", getTextVal(AddAddresPage.objRoomNoTextField, "Text Field"));
            click(AddAddresPage.objSubmitButton, "Submit Button");
            logger.info(getTextVal(AddAddresPage.objMandatoryWarnMessage, "Warning Message Displayed"));
            extent.extentLogger("Rooom No", getTextVal(AddAddresPage.objMandatoryWarnMessage, "Warning Message"));
            type(AddAddresPage.objRoomNoTextField, "@#%^&#$", getTextVal(AddAddresPage.objRoomNoTextField,"Text Field"));
            logger.info(getTextVal(AddAddresPage.objRoomNoTextField, "Special Character Warning Message"));
            extent.extentLogger("Rooom No", getTextVal(AddAddresPage.objRoomNoTextField, "Special Character Warning Message"));
            clearField(AddAddresPage.objRoomNoField, getTextVal(AddAddresPage.objRoomNoTextField,"Text Field"));
            type(AddAddresPage.objRoomNoTextField, "86", getTextVal(AddAddresPage.objRoomNoTextField, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_117", "TC_Ring_Core_117 - To verify room no and building name field with valid data");
            
            verifyElementExist(AddAddresPage.objAddressLineOneTextField, getTextVal(AddAddresPage.objAddressLineOneTextField, "Text Field"));
            click(AddAddresPage.objAddressLineOneTextField, getTextVal(AddAddresPage.objAddressLineOneTextField,"Text Field"));
            type(AddAddresPage.objAddressLineOneTextField, "", getTextVal(AddAddresPage.objAddressLineOneTextField, "Text Field"));
            type(AddAddresPage.objAddressLineOneTextField, "@#%^&#$", getTextVal(AddAddresPage.objAddressLineOneTextField,"Text Field"));
            logger.info(getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            extent.extentLogger("Address Line 1", getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objAddressLineOneField, getTextVal(AddAddresPage.objAddressLineOneField,"Text Field"));
            type(AddAddresPage.objAddressLineOneTextField, "44, Borivali", getTextVal(AddAddresPage.objAddressLineOneTextField, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_118", "TC_Ring_Core_118 - To verify address 1 field with valid data");
            
            verifyElementExist(AddAddresPage.objAddressLineTwoTextField, getTextVal(AddAddresPage.objAddressLineTwoTextField, "Field"));
            click(AddAddresPage.objAddressLineTwoTextField, getTextVal(AddAddresPage.objAddressLineTwoTextField,"Text Field"));
            type(AddAddresPage.objAddressLineTwoTextField, "", getTextVal(AddAddresPage.objAddressLineTwoTextField, "Text Field"));
            type(AddAddresPage.objAddressLineTwoTextField, "@#%^&#$", getTextVal(AddAddresPage.objAddressLineTwoTextField, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objSpclCharWarnMessage, "Warning Message"));
            extent.extentLogger("Address Line 2", getTextVal(AddAddresPage.objSpclCharWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objAddressLineTwoField, getTextVal(AddAddresPage.objAddressLineTwoField,"Text Field"));
            type(AddAddresPage.objAddressLineTwoField, "Vasa Street", getTextVal(AddAddresPage.objAddressLineTwoField, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_119", "TC_Ring_Core_119 - To verify address 2 field with valid data");
            
            verifyElementExist(AddAddresPage.objLandmarkTextField, getTextVal(AddAddresPage.objLandmarkTextField, "Field"));
            click(AddAddresPage.objLandmarkTextField, getTextVal(AddAddresPage.objLandmarkTextField,"Text Field"));
            type(AddAddresPage.objLandmarkTextField, "", getTextVal(AddAddresPage.objLandmarkTextField, "Text Field"));
            type(AddAddresPage.objLandmarkTextField, "@#%^&#$", getTextVal(AddAddresPage.objLandmarkTextField, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            extent.extentLogger("Landmark Field", getTextVal(AddAddresPage.objMinThreeCharWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objLandMarkClr, getTextVal(AddAddresPage.objLandMarkClr,"Text Field"));
            type(AddAddresPage.objLandMarkClr, "Das Gupta street", getTextVal(AddAddresPage.objLandMarkClr, "Text Field"));
            extent.extentLoggerPass("TC_Ring_Core_120", "TC_Ring_Core_120 - To verify Landmark field with valid data");
            hideKeyboard();

           verifyElementExist(AddAddresPage.objPincodeTextField, getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            click(AddAddresPage.objPincodeTextField, getTextVal(AddAddresPage.objPincodeTextField,"Text Field"));
            Swipe("up", 2);
            type(AddAddresPage.objPincodeTextField, "", getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            type(AddAddresPage.objPincodeTextField, "255552", getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objInvalidPinCode, "Warning Message"));
            extent.extentLogger("Pincode Field", getTextVal(AddAddresPage.objInvalidPinCode, "Warning Message"));
            clearField(AddAddresPage.objpincodeClr, getTextVal(AddAddresPage.objPincodeTextField,"Text Field"));
            type(AddAddresPage.objpincodeClr, "252", getTextVal(AddAddresPage.objpincodeClr, "Text Field"));
            logger.info(getTextVal(AddAddresPage.objSixDigitWarnMessage, "Warning Message"));
            extent.extentLogger("Pincode Field", getTextVal(AddAddresPage.objSixDigitWarnMessage, "Warning Message"));
            clearField(AddAddresPage.objpincodeClr, getTextVal(AddAddresPage.objPincodeTextField,"Text Field"));
            type(AddAddresPage.objPincodeTextField, "560076", getTextVal(AddAddresPage.objPincodeTextField, "Text Field"));
            hideKeyboard();
            
            verifyElementExist(AddAddresPage.objCityTextField, getTextVal(AddAddresPage.objCityTextField, "Field"));
            explicitWaitVisibility(AddAddresPage.objPopulatedCity, 10);
            logger.info(getTextVal(AddAddresPage.objPopulatedCity, "City Name autoPopulated after entering pincode"));
            extent.extentLogger("CityName", getTextVal(AddAddresPage.objPopulatedCity, "City Name autoPopulated after entering pincode"));
                
            verifyElementExist(AddAddresPage.objStateTextField, getTextVal(AddAddresPage.objStateTextField, "Field"));
            explicitWaitVisibility(AddAddresPage.objPopulatedState, 10);
            logger.info(getTextVal(AddAddresPage.objPopulatedState, "State Name autoPopulated after entering pincode"));
            extent.extentLogger("StateName", getTextVal(AddAddresPage.objPopulatedState, "State Name autoPopulated after entering pincode"));
            extent.extentLoggerPass("TC_Ring_Core_121,TC_Ring_Core_122,", "TC_Ring_Core_121,TC_Ring_Core_122 - To verify pincode field with invalid and valid pincode");
            
            explicitWaitVisibility(AddAddresPage.objSubmitButton, 10);
            Aclick(AddAddresPage.objSubmitButton, "Submit Button");
            extent.extentLoggerPass("TC_Ring_Core_123", "TC_Ring_Core_123 - To verfiy user clicks on submit button after entering valid address details");
        }else {
            logger.warn("Address Page is not diaplyed");
            extent.extentLoggerWarning("Address Page", "Address Page is not displayed");
        }
        waitTime(5000);
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("NewMPIN"),prop.getproperty("NewMPIN"));
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");  
		ringPayLogout();
    }		
//================================================Add Address Flow End=================================================================================================
//===============================================User Scan and Pay Start===============================================================================================	
	public void userScanAndPayTransactions() throws Exception {
		extent.HeaderChildNode("SetPin Merchant Flow Without Location");
		getDriver().resetApp();
		onBoarding();
		explicitWaitVisibility(HomePage.objHome, 10);
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals("Home", sHome);
		if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {

			String scanQrEnabled = getAttributValue("enabled", HomePage.objScanAndPay);
			softAssertion.assertEquals("true", scanQrEnabled);
			Aclick(HomePage.objScanAndPay, "Scan And Pay");

			logger.info("TC_Ring_Core_203 , To Verify Scan and Pay button available on bottom of the  HomeScreen is enable and clickable");
			extent.extentLoggerPass("TC_Ring_Core_203","TC_Ring_Core_203, To Verify Scan and Pay button available on bottom of the  HomeScreen is enable and clickable");
			System.out.println("-----------------------------------------------------------");

			explicitWaitVisibility(HomePage.objScanAnyQRToPay, 10);
			String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
			softAssertion.assertEquals("Scan any QR code to pay", sScanAnyQRCode);
			verifyElementPresent(HomePage.objScanAnyQRToPay, getTextVal(HomePage.objScanAnyQRToPay, "Text"));
			logger.info("TC_Ring_Core_204, Camera Scanner should get opened to Scan and Pay");
			extent.extentLoggerPass("TC_Ring_Core_204","TC_Ring_Core_204,Camera Scanner should get opened to Scan and Pay");
			System.out.println("-----------------------------------------------------------");

			//Scan here
			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            String projectPath=System.getProperty("user.dir");
            getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
            waitTime(10000);
            BrowsertearDown();
            
            /*------------------------------Android----------------------------*/
            setPlatform("Android");
            initDriver();

			if(verifyIsElementDisplayed(RingPayMerchantFlowPage.objCreditAtZeroPopUp, getTextVal(RingPayMerchantFlowPage.objCreditAtZeroPopUp,"Pop up"))){
				logger.info("TC_Ring_Core_191, Credit at zero Charges popup is Displayed");
				extent.extentLoggerPass("TC_Ring_Core_191","TC_Ring_Core_191,Credit at zero Charges popup is Displayed");
				System.out.println("-----------------------------------------------------------");
			}else{
				logger.info("TC_Ring_Core_191, Credit at zero Charges popup is not Displayed");
				extent.extentLoggerPass("TC_Ring_Core_191","TC_Ring_Core_191,Credit at zero Charges popup is not Displayed");
				System.out.println("-----------------------------------------------------------");
			}

			explicitWaitVisibility(PaymentPage.objUseCreditLimitText, 30);
			if (verifyElementPresent(PaymentPage.objUseCreditLimitText, getTextVal(RingPayMerchantFlowPage.objUseCreditLimitText, "Text"))) {
				verifyElementPresent(PaymentPage.objMerchantName, getTextVal(RingPayMerchantFlowPage.objPayTypeMethod, "is Merchant Name"));
				verifyElementPresent(PaymentPage.objUpiID, getTextVal(RingPayMerchantFlowPage.objUpiID, "is UPI Id"));
				Back(1);
				verifyElementPresent(PaymentPage.objRupeesLogo, getTextVal(PaymentPage.objRupeesLogo, "Rupees Logo"));
				verifyElementPresent(PaymentPage.objTransactionFeeMsg, getTextVal(PaymentPage.objTransactionFeeMsg, "Text"));
				verifyElementPresent(PaymentPage.objDisabledPayNowButton, "Disabled pay Now Button");
				hideKeyboard();
				logger.info("TC_Ring_Core_207, Payment Screen Validated");
				extent.extentLoggerPass("TC_Ring_Core_207", "TC_Ring_Core_207,Payment Screen Validated");
				System.out.println("-----------------------------------------------------------");

			}
			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, prop.getproperty("HigherAmount_Merchant"), "Amount Field");
				verifyElementPresent(RingPayMerchantFlowPage.objTransactionMsg, getTextVal(RingPayMerchantFlowPage.objTransactionMsg, "Error Message"));
				String sErrorMessage1 = getText(RingPayMerchantFlowPage.objTransactionMsg);
				softAssertion.assertEquals(sErrorMessage1, "You have entered a higher amount than your available limit. Re-enter amount.");
				hideKeyboard();
				logger.info("TC_Ring_Core_208, You have entered a higher amount than your available limit. Re-enter amount. Error message should be displayed");
				extent.extentLoggerPass("TC_Ring_Core_208", "TC_Ring_Core_208,You have entered a higher amount than your available limit. Re-enter amount. Error message should be displayed");
				System.out.println("-----------------------------------------------------------");
			}
			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, prop.getproperty("ZeroAmount_Merchant"), "Amount Field");
				verifyElementPresent(RingPayMerchantFlowPage.objTransactionMsg, getTextVal(RingPayMerchantFlowPage.objTransactionMsg, "Error Message"));
				String sErrorMessage2 = getText(RingPayMerchantFlowPage.objTransactionMsg);
				softAssertion.assertEquals(sErrorMessage2, "Please enter amount greater than 0");
				hideKeyboard();
				logger.info("TC_Ring_Core_209, Please enter amount greater than 0 Error Message is Displayed");
				extent.extentLoggerPass("TC_Ring_Core_209", "TC_Ring_Core_209, Please enter amount greater than 0 Error Message is Displayed");
				System.out.println("-----------------------------------------------------------");
			}
			if (verifyElementPresent(PaymentPage.objAmountTextField, "Amount Text Field")) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, prop.getproperty("Amount_Merchant"), "Amount Field");
				verifyElementPresent(PaymentPage.objTransactionMsg, getTextVal(PaymentPage.objTransactionMsg, "Message"));
				String sPayNow = getAttributValue("enabled", PaymentPage.objTransactionMsg);
				softAssertion.assertEquals("true", sPayNow);
				logger.info("TC_Ring_Core_210, Pay Now button should be enabled after entering the valid amount");
				extent.extentLoggerPass("TC_Ring_Core_210", "TC_Ring_Core_210, Pay Now button should be enabled after entering the valid amount");
				System.out.println("-----------------------------------------------------------");
				hideKeyboard();
			}

			if (verifyElementPresent(PaymentPage.objPayNowBtn, "Pay Now Button")) {
				doubleTap(PaymentPage.objPayNowBtn2, 1, "Pay Now Button");
				waitTime(15000);
				String sConfirmPayment = getText(PaymentPage.objConfirmPayment);
				softAssertion.assertEquals("Confirm Payment", sConfirmPayment);
				logger.info("TC_Ring_Core_211, Navigated to Confirm Payment Screen to enter the Security PIN for transaction");
				extent.extentLoggerPass("TC_Ring_Core_211", "TC_Ring_Core_211, Navigated to Confirm Payment Screen to enter the Security PIN for transaction");
				System.out.println("-----------------------------------------------------------");
				if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
					Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
					type(PaymentPage.objEditTransactionPin, "1234", "Edit Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					verifyElementPresent(PaymentPage.objIncorrectPin, getTextVal(PaymentPage.objIncorrectPin, "Error Message"));
					String sIncorrectPin = getText(PaymentPage.objIncorrectPin);
					softAssertion.assertEquals("Incorrect PIN", sIncorrectPin);
					logger.info("TC_Ring_Core_212, Incorrect PIN Error message should be Displayed");
					extent.extentLoggerPass("TC_Ring_Core_212", "TC_Ring_Core_212, Incorrect PIN Error message should be Displayed");
					System.out.println("-----------------------------------------------------------");
				}
				if (verifyElementPresent(PaymentPage.objForgotPin, getTextVal(PaymentPage.objForgotPin, "Text"))) {
					Aclick(PaymentPage.objForgotPin, "Forget PIN?");
					verifyElementPresent(PaymentPage.objChangePin, getTextVal(PaymentPage.objChangePin, "text"));
					String sChangePin = getText(PaymentPage.objChangePin);
					softAssertion.assertEquals("Change Pin", sChangePin);
					logger.info("TC_Ring_Core_213, Navigated to Change Pin Screen");
					extent.extentLoggerPass("TC_Ring_Core_213", "TC_Ring_Core_213, Navigated to Change Pin Screen");
					System.out.println("-----------------------------------------------------------");
				}
				if (verifyElementPresent(PaymentPage.objChangePin, getTextVal(PaymentPage.objChangePin, "Text"))) {
					Aclick(PaymentPage.objEnterNewPin, "Enter New Pin");
					type(PaymentPage.objEnterNewPin, "1111", "Enter New Pin Field");
					Aclick(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					type(PaymentPage.objReEnterNewPin, "1111", "Re-Enter New Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					if (verifyElementPresent(PaymentPage.objPleaseEnterOtp, getTextVal(PaymentPage.objPleaseEnterOtp, "Text"))) {
						String sErrorMessage = getText(PaymentPage.objPleaseEnterOtp);
						softAssertion.assertEquals("Please enter OTP", sErrorMessage);
						logger.info("TC_Ring_Core_214, Please enter OTP error message is displayed");
						extent.extentLoggerPass("TC_Ring_Core_214", "TC_Ring_Core_199, Please enter OTP error message is displayed");
						System.out.println("-----------------------------------------------------------");
					}
					Swipe("DOWN", 1);
					Aclick(PaymentPage.objEnterOtp, "Enter OTP Field");
					type(PaymentPage.objEnterOtp, "123456", "Enter OTP Field");
					hideKeyboard();
					Aclick(PaymentPage.objEnterNewPin, "Enter New Pin");
					type(PaymentPage.objEnterNewPin, "1111", "Enter New Pin Field");
					hideKeyboard();
					Aclick(PaymentPage.objContinue, "Continue Button");
					verifyElementPresent(PaymentPage.objInvalidOtpMsg, getTextVal(PaymentPage.objInvalidOtpMsg, "Text"));
					String sInvalidOtpErrorMessage = getText(PaymentPage.objInvalidOtpMsg);
					softAssertion.assertEquals("Invalid Otp, Please enter correct otp.", sInvalidOtpErrorMessage);
					logger.info("TC_Ring_Core_215, Invalid Otp, Please enter correct otp. Error Message is displayed");
					extent.extentLoggerPass("TC_Ring_Core_215", "TC_Ring_Core_215, Invalid Otp, Please enter correct otp. Error Message is displayed");
					System.out.println("-----------------------------------------------------------");
					Aclick(PaymentPage.objEnterOtp, "Enter OTP Field");
					clearField(PaymentPage.objEnterOtp, "Enter OTP Field");
					type(PaymentPage.objEnterOtp, "888888", "Enter OTP Field");
					hideKeyboard();
					Aclick(PaymentPage.objEnterNewPin, "Enter New Pin");
					type(PaymentPage.objEnterNewPin, "1111", "Enter New Pin Field");
					hideKeyboard();
					explicitWaitVisibility(PaymentPage.objReEnterNewPin, 10);
					Aclick(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					clearField(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					type(PaymentPage.objReEnterNewPin, "1234", "Re-Enter New Pin Field");
					hideKeyboard();
					Aclick(PaymentPage.objContinue, "Continue Button");
					String sEnterSamePinMessage = getText(PaymentPage.objEnterSamePin);
					softAssertion.assertEquals("Please enter same PIN in both fields", sEnterSamePinMessage);
					logger.info("TC_Ring_Core_216, Please enter same PIN in both fields Error Message is displayed");
					extent.extentLoggerPass("TC_Ring_Core_216", "TC_Ring_Core_216, Please enter same PIN in both fields Error Message is displayed");
					System.out.println("-----------------------------------------------------------");
					Aclick(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					clearField(PaymentPage.objReEnterNewPin, "Re-Enter New Pin");
					type(PaymentPage.objReEnterNewPin, "1111", "Re-Enter New Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					explicitWaitVisibility(PaymentPage.objConfirmPayment, 10);
					softAssertion.assertEquals("Confirm Payment", sConfirmPayment);
					logger.info("TC_Ring_Core_218, Navigated back to Confirm Payment Screen from Change Pin Screen");
					extent.extentLoggerPass("TC_Ring_Core_218", "TC_Ring_Core_218, Navigated back to Confirm Payment Screen from Change Pin Screen");
					System.out.println("-----------------------------------------------------------");
				}
				if (verifyElementPresent(PaymentPage.objConfirmPayment, getTextVal(PaymentPage.objConfirmPayment, "Text"))) {
					Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
					type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
					Aclick(PaymentPage.objContinue, "Continue Button");
					if (verifyElementPresent(PaymentPage.objPaymentDone, getTextVal(PaymentPage.objPaymentDone, "Text"))) {
						String sPaymentDone = getText(PaymentPage.objPaymentDone);
						softAssertion.assertEquals("Payment Done!", sPaymentDone);
						verifyElementPresent(PaymentPage.objGreenTickMark, "Payment Done green tick mark");
						verifyElementPresent(PaymentPage.objRupeesLogo, getTextVal(PaymentPage.objRupeesLogo, "is Rupees Logo"));
						verifyElementPresent(PaymentPage.objMerchantNameReceipt, getTextVal(PaymentPage.objMerchantNameReceipt, "Text"));
						verifyElementPresent(PaymentPage.objTaxDateAndTime, getTextVal(PaymentPage.objTaxDateAndTime, "is Tax date and time"));
						verifyElementPresent(PaymentPage.objSeekBar, "Seek Bar");
						verifyElementPresent(PaymentPage.objAvailableLimit, getTextVal(PaymentPage.objAvailableLimit, "Text"));
						verifyElementPresent(PaymentPage.ObjAvailableLimitAmount, getTextVal(PaymentPage.ObjAvailableLimitAmount, "is Available Limit Amount"));
						verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"));
						logger.info("TC_Ring_Core_219, Validation of Payment Done Screen");
						extent.extentLoggerPass("TC_Ring_Core_219", "TC_Ring_Core_219, Validation of Payment Done Screen");
						System.out.println("-----------------------------------------------------------");
					}
					if (verifyElementPresent(PaymentPage.objGoToHomePage, getTextVal(PaymentPage.objGoToHomePage, "Text"))) {
						Aclick(PaymentPage.objGoToHomePage, "Go To HomePage");
					    explicitWaitVisibility(UserRegistrationPage.objNoBtn, 20);
					    verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
						logger.info("TC_Ring_Core_221, Navigation to HomeScreen");
						extent.extentLoggerPass("TC_Ring_Core_221", "TC_Ring_Core_221, Navigation to HomeScreen");
						System.out.println("-----------------------------------------------------------");
					}
				}
			}
		}
		paymentDeclinedFlow();
		homeScreenValidation();
		ringPayLogout();
	}
	public void homeScreenValidation() throws Exception {
		extent.HeaderChildNode("Home Screen Validation");
		explicitWaitVisibility(HomePage.objHome, 10);
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
		if (verifyElementPresent(HomePage.objHome, "Home")) {
			verifyElementPresent(HomePage.objAvailableLimit, getTextVal(HomePage.objAvailableLimit, "Text"));
			verifyElementPresent(HomePage.ObjAvailableLimitAmount, getTextVal(HomePage.ObjAvailableLimitAmount, "is Available Limit Amount"));
			verifyElementPresent(HomePage.objCurrentSpends, getTextVal(HomePage.objCurrentSpends, "Text"));
			verifyElementPresent(HomePage.ObjCurrentSpendsAmount, getTextVal(HomePage.ObjCurrentSpendsAmount, "is Current Spends Amount"));
			verifyElementPresent(HomePage.objRecentTransactions, getTextVal(HomePage.objRecentTransactions, "Text"));
			verifyElementPresent(HomePage.objViewAll, getTextVal(HomePage.objViewAll, "Text"));
			verifyElementPresent(HomePage.objHome, getTextVal(HomePage.objHome, "Text"));
			verifyElementPresent(HomePage.objTransactions, getTextVal(HomePage.objTransactions, "Text"));
			verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button");
			verifyElementPresent(HomePage.objRewards, getTextVal(HomePage.objRewards, "Text"));
			verifyElementPresent(HomePage.objMore, getTextVal(HomePage.objMore, "Text"));
			logger.info("TC_Ring_Core_202, Home Screen Validation");
			extent.extentLoggerPass("TC_Ring_Core_202", "TC_Ring_Core_202,Home Screen Validation");
			System.out.println("-----------------------------------------------------------");
		}
		Swipe("DOWN", 1);
		if (verifyElementPresent(HomePage.objPayEarly, getTextVal(HomePage.objPayEarly, "Text"))) {
			String sPayEarly = getText(HomePage.objPayEarly);
			softAssertion.assertEquals("Pay Early", sPayEarly);
			Aclick(HomePage.objPayEarly, "Pay Early");
			if (verifyElementPresent(PayEarlyPaymentPage.objPayEarlyPayment, getTextVal(PayEarlyPaymentPage.objPayEarlyPayment, "Text"))) {
				String sPayment = getText(PayEarlyPaymentPage.objPayEarlyPayment);
				softAssertion.assertEquals("Pay Early", sPayment);
				logger.info("TC_Ring_Core_224, Navigated to Payment Page to Pay Early");
				extent.extentLoggerPass("TC_Ring_Core_224", "TC_Ring_Core_224, Navigated to Payment Page to Pay Early");
				System.out.println("-----------------------------------------------------------");

				verifyElementPresent(PayEarlyPaymentPage.objAmountDue, getTextVal(PayEarlyPaymentPage.objAmountDue, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objAmountToBePaid, getTextVal(PayEarlyPaymentPage.objAmountToBePaid, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objChoosePaymentMethod, getTextVal(PayEarlyPaymentPage.objChoosePaymentMethod, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objPayViaUPIApps, getTextVal(PayEarlyPaymentPage.objPayViaUPIApps, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objGooglePay, getTextVal(PayEarlyPaymentPage.objGooglePay, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objPhonePe, getTextVal(PayEarlyPaymentPage.objPhonePe, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objPaytm, getTextVal(PayEarlyPaymentPage.objPaytm, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objAmazonPay, getTextVal(PayEarlyPaymentPage.objAmazonPay, "text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objBHIM, getTextVal(PayEarlyPaymentPage.objBHIM, "Text to Google pay Application"));
				verifyElementPresent(PayEarlyPaymentPage.objUPIAppsConvenienceFee, getTextVal(PayEarlyPaymentPage.objUPIAppsConvenienceFee, ""));
				verifyElementPresent(PayEarlyPaymentPage.objAddUPIId, getTextVal(PayEarlyPaymentPage.objAddUPIId, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objAddUPIIdConvenienceFee, getTextVal(PayEarlyPaymentPage.objAddUPIIdConvenienceFee, ""));
				verifyElementPresent(PayEarlyPaymentPage.objNetBankingAndDebitCard, getTextVal(PayEarlyPaymentPage.objNetBankingAndDebitCard, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objNetBankingAndDebitCardConvenienceFee, getTextVal(PayEarlyPaymentPage.objNetBankingAndDebitCardConvenienceFee, ""));
				Swipe("UP", 1);
				verifyElementPresent(PayEarlyPaymentPage.objBankTransfer, getTextVal(PayEarlyPaymentPage.objBankTransfer, "Text"));
				verifyElementPresent(PayEarlyPaymentPage.objPaymentViaUPI, getTextVal(PayEarlyPaymentPage.objPaymentViaUPI, "Text"));
				logger.info("TC_Ring_Core_225, Validation of Pay Early Payment Screen");
				extent.extentLoggerPass("TC_Ring_Core_225", "TC_Ring_Core_225, Validation of Pay Early Payment Screen");
				System.out.println("-----------------------------------------------------------");
			}
		}
		Back(1);
		explicitWaitVisibility(HomePage.objCurrentSpends, 10);
		if (verifyElementPresent(HomePage.objCurrentSpends, getTextVal(HomePage.objCurrentSpends, "Text"))) {
			String sCurrentSpends = getText(HomePage.objCurrentSpends);
			softAssertion.assertEquals("Current Spends", sCurrentSpends);
			verifyElementPresent(HomePage.ObjCurrentSpendsAmount, getTextVal(HomePage.ObjCurrentSpendsAmount, "is Current Spends"));
			verifyElementPresent(HomePage.objPayEarly, getTextVal(HomePage.objPayEarly, "Text"));
			logger.info("TC_Ring_Core_223, Validation of Current Spends Banner");
			extent.extentLoggerPass("TC_Ring_Core_223", "TC_Ring_Core_223, Validation of Current Spends Banner");
			System.out.println("-----------------------------------------------------------");
		}
		if (verifyElementPresent(HomePage.objViewAll, "Scan And Pay Button")) {
			Aclick(HomePage.objViewAll, "View All Button");
			explicitWaitVisibility(HomePage.objTransactionHistory, 10);
			if (verifyElementPresent(HomePage.objTransactionHistory, getTextVal(HomePage.objTransactionHistory, "Text"))) {
				String sTransactionHistory = getText(HomePage.objTransactionHistory);
				softAssertion.assertEquals("Transaction History", sTransactionHistory);
				logger.info("TC_Ring_Core_222, Navigated to Transaction History List Screen");
				extent.extentLoggerPass("TC_Ring_Core_222", "TC_Ring_Core_222, Navigated to Transaction History List Screen");
				System.out.println("-----------------------------------------------------------");
			}
		}
		Back(1);
	}

	public void paymentDeclinedFlow() throws Exception {
		extent.HeaderChildNode("Payment declined Flow");
		if (verifyElementPresent(HomePage.objScanAndPay, "Scan And Pay Button")) {
			Aclick(HomePage.objScanAndPay, "Scan And pay Button");
			explicitWaitVisibility(HomePage.objScanAnyQRToPay, 10);
			String sScanAnyQRCode = getText(HomePage.objScanAnyQRToPay);
			softAssertion.assertEquals("Scan any QR code to pay", sScanAnyQRCode);
			
			//Scan here
			/*------------------------------WEB----------------------------*/
			Utilities.setPlatform = "Web";
            new CommandBase("Chrome");
            waitTime(4000);
            String projectPath=System.getProperty("user.dir");
            getWebDriver().get(projectPath+"\\Mock_Files\\blockedqr.png");
            waitTime(15000);
            BrowsertearDown();
            
            /*------------------------------Android----------------------------*/
            setPlatform("Android");
            initDriver();
			
			explicitWaitVisibility(PaymentPage.objUseCreditLimitText, 10);
			if (verifyElementPresent(PaymentPage.objUseCreditLimitText, getTextVal(RingPayMerchantFlowPage.objUseCreditLimitText, "Text"))) {
				Aclick(PaymentPage.objAmountTextField, "Amount Field");
				clearField(PaymentPage.objAmountTextField, "Amount Field");
				type(PaymentPage.objAmountTextField, amount[2], "Amount Field");
				Aclick(PaymentPage.objPayNowBtn, "Pay Now Button");
				String sConfirmPayment = getText(PaymentPage.objConfirmPayment);
				softAssertion.assertEquals("Confirm Payment", sConfirmPayment);
				Aclick(PaymentPage.objEditTransactionPin, "Edit Pin Field");
				type(PaymentPage.objEditTransactionPin, "1111", "Edit Pin Field");
				Aclick(PaymentPage.objContinue, "Continue Button");
			}
			explicitWaitVisibility(PaymentPage.objPaymentFailed, 10);
			if (verifyElementPresent(PaymentPage.objPaymentFailed, getTextVal(PaymentPage.objPaymentFailed, "Text"))) {
				String sPaymentDeclined = getText(PaymentPage.objPaymentFailed);
				softAssertion.assertEquals("Payment failed", sPaymentDeclined);
				logger.info("TC_Ring_Core_220, Payment failed Screen is displayed");
				extent.extentLoggerPass("TC_Ring_Core_220", "TC_Ring_Core_220, Payment failed Screen is displayed");
				System.out.println("-----------------------------------------------------------");
				Back(1);
			}
		}
	}
	public String onBoarding() throws Exception {
		extent.HeaderChildNode("User Play store Flow Module");
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		explicitWaitVisibility(RingLoginPage.objLoginLink, 10);
		Aclick(RingLoginPage.objLoginLink, "Signup/Login link");
		explicitWaitVisibility(RingLoginPage.objLoginMobile, 10);
		Aclick(RingLoginPage.objLoginMobile, "Continue with Mobile option");
		waitTime(3000);
		verifyElementPresentAndClick(RingLoginPage.objNoneBtn, "None of the above button");
		waitTime(3000);
		String nMobileNumber = "6"+RandomIntegerGenerator(9);
		click(RingLoginPage.objMobTextField, "Mobile Number Field");
		type(RingLoginPage.objMobTextField, nMobileNumber, "Mobile Number Field");
		enterOtp("888888");
		readAndAccept();
		waitTime(30000);
		
		userDetails();
		dateOfBirth(prop.getproperty("dateOfBirthMonth"), prop.getproperty("dateOfBirthDate"), prop.getproperty("dateOfBirthYear"));
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		addAddress("asasa","asaas","asasa","asq","560102");
		waitTime(5000);
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("ValidPin"),prop.getproperty("ValidPin"));
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String sHome = getText(HomePage.objHome);
		softAssertion.assertEquals(sHome, "Home");
		return nMobileNumber;
	}
//===============================================User Scan and Pay end===========================================================	
//=================================================Merchant Flow====================================================================
	public void merchantFlow() throws Exception {
        extent.HeaderChildNode("RingPay App Merchant Flow");
        getDriver().resetApp();
        if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
        
        explicitWaitVisibility(RingPayMerchantFlowPage_New.objScanQRCodeText, 10);
        verifyIsElementDisplayed(RingPayMerchantFlowPage_New.objScanQRCodeText,getTextVal(RingPayMerchantFlowPage.objScanQRCodeText, "Text"));
        softAssertion.assertEquals(getText(RingPayMerchantFlowPage_New.objScanQRCodeText), "Scan any QR to get started");
        verifyElementPresent(RingPayMerchantFlowPage_New.obDontHaveQRCodeText,getTextVal(RingPayMerchantFlowPage_New.obDontHaveQRCodeText, "Text"));
        verifyElementPresent(RingPayMerchantFlowPage_New.objSignUpORLoginLink,getTextVal(RingPayMerchantFlowPage_New.objSignUpORLoginLink, "Text"));
        logger.info("Scanning the QR Code");
        extent.extentLogger("QR Code", "Scanning the QR Code");
      //Scan here
		/*------------------------------WEB----------------------------*/
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        String projectPath=System.getProperty("user.dir");
        getWebDriver().get(projectPath+"\\Mock_Files\\qrcode.png");
        waitTime(15000);
        BrowsertearDown();
        
        /*------------------------------Android----------------------------*/
        setPlatform("Android");
        initDriver();
        if (checkElementExist(RingPayMerchantFlowPage_New.objFreedomPopup, "Freedom Pop Up"))        
        {
            click(RingPayMerchantFlowPage_New.objLetsRingItBtn, getText(RingPayMerchantFlowPage_New.objLetsRingItBtn));
            click(RingPayMerchantFlowPage_New.objLetsRingItBtn, getText(RingPayMerchantFlowPage_New.objLetsRingItBtn));
        }
        verifyElementPresent(RingPayMerchantFlowPage_New.objMakePaymentPage, "Navigated to Make Payment Page");
	       verifyElementPresent(RingPayMerchantFlowPage_New.objPayTypeMethod,getTextVal(RingPayMerchantFlowPage_New.objPayTypeMethod, "Payment Method"));
	       verifyElementPresent(RingPayMerchantFlowPage_New.objUpiId, getTextVal(RingPayMerchantFlowPage_New.objUpiId, "UPI Id"));
	       click(RingPayMerchantFlowPage_New.objAmountTextField, "Amount Text Field");
	       waitTime(2000);
	       iskeyboardShown("Numeric");
	       for (int i = 0; i <= amount.length; i++) {
	           type1(RingPayMerchantFlowPage_New.objAmountTextField, amount[i], "Amount Field");
	           String validationText = getTextVal(RingPayMerchantFlowPage_New.objTransactionMsg, "Text is Displayed");
	           if (validationText.contains("You can pay up to")) {
	               break;
	           }
	           logger.warn(validationText);
	           extent.extentLoggerWarning("validation", validationText);
	           clearField(RingPayMerchantFlowPage_New.objAmountTextField, "Amount text field");
	       }
	       click(RingPayMerchantFlowPage_New.objProceedBtn, "Proceed Button");
	
	       verifyIsElementDisplayed(RingPayMerchantFlowPage_New.objLoginPageHeader,getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
	       softAssertion.assertEquals(getText(RingPayMerchantFlowPage_New.objLoginPageHeader), "Sign up/Login");
	       logger.info("User redirected to Signup/Login Screen");
	       extent.extentLogger("login", "User redirected to Signup/Login Screen");
	       verifyElementPresent(RingPayMerchantFlowPage_New.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage_New.objContinueWithMobileCTA, "CTA"));
	       verifyElementPresent(RingPayMerchantFlowPage_New.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage_New.objContinueWithGoogleCTA, "CTA"));
	       verifyElementPresent(RingPayMerchantFlowPage_New.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage_New.objContinueWithFacebookCTA, "CTA"));
	
	       extent.extentLoggerPass("TC_Ring_Core_60", "TC_Ring_Core_60-To Verify the Login screen when user open the app");
	       extent.extentLoggerPass("TC_Ring_Core_61", "TC_Ring_Core_61-To verify When User selects Enable Permission option");
	       extent.extentLoggerPass("TC_Ring_Core_62", "TC_Ring_Core_62-To verify when user Scans the QR  code");
	       extent.extentLoggerPass("TC_Ring_Core_63", "TC_Ring_Core_63-To verify the Screen when the Pop up disappers");
	       extent.extentLoggerPass("TC_Ring_Core_64", "TC_Ring_Core_64-To verify the merchant details ");
	       extent.extentLoggerPass("TC_Ring_Core_65", "TC_Ring_Core_65-To verify the First Transcation  fee message ");
	       extent.extentLoggerPass("TC_Ring_Core_66","TC_Ring_Core_66-To Verify  when User cliks on  enter transaction amount on screen ");
	       extent.extentLoggerPass("TC_Ring_Core_67","TC_Ring_Core_67-To verify when user  tries to enter amount more than first transaction limit");
	       extent.extentLoggerPass("TC_Ring_Core_68", "TC_Ring_Core_68-To Verify when user tries to enter 0 amount");
	       extent.extentLoggerPass("TC_Ring_Core_69","TC_Ring_Core_69-To Verify user enters valid amt. and clicks on the Pay now button on merchant detail page");
	       
	       userPlayStoreLoginFlow(prop.getproperty("lessThanTenMob"), prop.getproperty("moreThanTenMob"), prop.getproperty("specialCharMob"), prop.getproperty("spaceMob"), prop.getproperty("validMob"), prop.getproperty("invalidOtp"), prop.getproperty("lessOtp"));
		   waitTime(40000);
		   userDetails();
		   dateOfBirth("Feb", "10", "1996");
		   click(UserRegistrationNew.objProceed, "Proceed Button");
		   waitTime(10000);
		   instaNewCommunicationAddress();
			
		   verifyElementPresent(PromoCodeOfferPage.objMerchantOfferHeader, getText(PromoCodeOfferPage.objMerchantOfferHeader));
		   softAssertion.assertEquals(getText(PromoCodeOfferPage.objMerchantOfferHeader), "Woohoo, Sunil!");
		   verifyElementPresent(PromoCodeOfferPage.objUnlockRingLimit, getText(PromoCodeOfferPage.objUnlockRingLimit) + " of " + getText(PromoCodeOfferPage.objApprovedRinglimitAmt));
		   verifyElementPresent(PromoCodeOfferPage.objPayingType, getText(PromoCodeOfferPage.objPayingType));
		   verifyElementPresent(PromoCodeOfferPage.objUPIId, getText(PromoCodeOfferPage.objUPIId));
		   verifyElementPresent(PromoCodeOfferPage.objMerchantPayAmt, getText(PromoCodeOfferPage.objMerchantPayAmt));
		   verifyElementPresent(PromoCodeOfferPage.objRBIMsg , getText(PromoCodeOfferPage.objRBIMsg) + getText(PromoCodeOfferPage.objRBIMsg));
		   verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		   verifyElementPresentAndClick(TermsAndConditionPage.objAcceptAndPayBtn,getText(TermsAndConditionPage.objAcceptText));
			
		   instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
		   waitTime(20000);
		   verifyElementPresentAndClick(PromoCodeOfferPage.objGoToHomePage, getText(PromoCodeOfferPage.objGoToHomePage));
		   waitTime(20000);
		   verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		   explicitWaitVisibility(UserRegistrationPage.objNoBtn, 20);
		   verifyElementPresentAndClick(UserRegistrationPage.objNoBtn, getText(UserRegistrationPage.objNoBtn));
		   String sHome = getText(HomePage.objHome);
		   softAssertion.assertEquals(sHome, "Home");
		   ringPayLogout();
   }
//================================================Merchant Flow End=================================================================
	
	public void cameraPermission() throws Exception {
		explicitWaitVisibility(RingLoginPage.objCamPermHeader, 30);
		verifyElementPresent(RingLoginPage.objCamPermHeader, "Camera Permission required");
		String camPermHeaderTxt = getText(RingLoginPage.objCamPermHeader);
		softAssertion.assertEquals(camPermHeaderTxt, "Camera Permission required");
		logger.info("Camera Permission required popup");
	}

	public void enablePermissions() throws Exception {
		explicitWaitVisibility(RingLoginPage.objCamPermPopUp, 30);
		Aclick(RingLoginPage.objCamPermPopUp, "Enable permissions button");
		logger.info("Foreground allow camera permissions");
		extent.extentLoggerPass("Foreground allow camera permissions", "Foreground allow camera permissions options");
		explicitWaitVisibility(RingLoginPage.objAllowCamera, 10);
		Aclick(RingLoginPage.objAllowCamera, "While using the app foreground camera permission option");

		explicitWaitVisibility(RingLoginPage.objScanQRPage, 10);
		logger.info("Scan any QR to get started");
		String qrCodeHeader = getText(RingLoginPage.objScanQRPage);
		softAssertion.assertEquals(qrCodeHeader, "Scan any QR to get started");
	}

	public void loginMobile() throws Exception {
		explicitWaitVisibility(RingLoginPage.objLoginMobile, 10);
		Aclick(RingLoginPage.objLoginMobile, "Continue with Mobile option");

//		trueCallerPopup();
//		if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above button")) {
//			String noneOfAboveTxt = getText(RingLoginPage.objNoneBtn);
//			softAssertion.assertEquals(noneOfAboveTxt, "NONE OF THE ABOVE");
//			Aclick(RingLoginPage.objNoneBtn, "None of the above");
//
//			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
//			logger.info("Verify Mobile Header");
//			String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
//			softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
//			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
//			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
//			String verifyMobHeaderTxt1 = getText(RingLoginPage.objVerifyMobHeader);
//			softAssertion.assertEquals(verifyMobHeaderTxt1, "Verify Mobile");
//			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
//		}else {
			waitTime(3000);
			verifyElementPresentAndClick(RingLoginPage.objNoneBtn, "None of the above button");
			waitTime(3000);
			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
			String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
			softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
			explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
			logger.info("Verify Mobile Header");
			String verifyMobHeaderTxt1 = getText(RingLoginPage.objVerifyMobHeader);
			softAssertion.assertEquals(verifyMobHeaderTxt1, "Verify Mobile");
			explicitWaitVisibility(RingLoginPage.objMobTextField, 10);
//		}

	}

	public void trueCallerPopup() throws Exception {
		if (verifyElementPresent(RingLoginPage.objTruSkipBtn, "True caller popup")) {
			Aclick(RingLoginPage.objTruSkipBtn, "True caller skip button");
		}
		if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above")) {
			Aclick(RingLoginPage.objNoneBtn, "None of the above");
		}
	}

	public String mobileNoValidation(String mobNo) throws Exception {
		// public String noTxt;
		waitTime(4000);
		if (verifyElementPresent(RingLoginPage.objTruSkipBtn, "True caller popup")) {
			Aclick(RingLoginPage.objTruSkipBtn, "True caller skip button");
		}
		if (verifyElementPresent(RingLoginPage.objNoneBtn, "None of the above button")) {
			String noneOfAboveTxt = getText(RingLoginPage.objNoneBtn);
			softAssertion.assertEquals(noneOfAboveTxt, "NONE OF THE ABOVE");
			Aclick(RingLoginPage.objNoneBtn, "None of the above");
		}
		explicitWaitVisibility(RingLoginPage.objVerifyMobHeader, 10);
		logger.info("Verify Mobile Header");
		String verifyMobHeaderTxt = getText(RingLoginPage.objVerifyMobHeader);
		softAssertion.assertEquals(verifyMobHeaderTxt, "Verify Mobile");
		waitTime(4000);
		Aclick(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, mobNo, "Mobile text field");
		System.out.println(mobNo);
		waitTime(5000);
		String noTxt = getText(RingLoginPage.objMobTextField);
		if (!verifyIsElementDisplayed(RingUserDetailPage.objRegisterBtn)) {
			logger.info("Navigated to OTP Page");
		} else {
			Aclick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		}
		return noTxt;
	}

	public void mobileNoValidation1(String mobNo) throws Exception {
		click(RingLoginPage.objMobTextField, "Mobile text field");
		type(RingLoginPage.objMobTextField, mobNo, "Mobile text field");
//		String noTxt = getText(RingLoginPage.objMobTextField);
//		waitTime(5000);
//		if (!verifyElementExist(RingUserDetailPage.objRegisterBtn, "Proceed Button")) {
//			logger.info("Navigated to OTP Page");
//		} else {
//			click(RingUserDetailPage.objRegisterBtn, "Proceed Button");
//		}
//		return noTxt;
	}
	
	public String mobileNoValidation2(String mobNo) throws Exception {
        waitTime(4000);
        Aclick(RingLoginPage.objMobTextField, "Mobile text field");
        type(RingLoginPage.objMobTextField, mobNo, "Mobile text field");
        System.out.println(mobNo);
        waitTime(3000);
        String noTxt = getText(RingLoginPage.objMobTextField);
        if (mobNo.length()>=10) {
            logger.info("Navigated to OTP Page");
        } else {
            click(RingLoginPage.objProceedBtn, "Proceed Button");
        }
        return noTxt;
    }

	public void enterOtp(String otp) throws Exception {
		explicitWaitClickable(RingLoginPage.resendOtpTxt, 20);
		Aclick(RingLoginPage.objOtpTxtField1, "Otp text field");
		type(RingLoginPage.objOtpTxtField1, otp, "Enter OTP");
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void gmailLogin(String userId, String password) throws Exception {
		extent.HeaderChildNode("User Play store Flow Module");
		cameraPermission();
		enablePermissions();
		explicitWaitVisibility(SignUP_LoginPage.continueWithGmail, 20);
		Aclick(SignUP_LoginPage.continueWithGmail, "Continue With Gmail Account");
		if (verifyElementPresent(GmailLoginPage.addAnotherAccount, "Add Another Account")) {
			Aclick(GmailLoginPage.addAnotherAccount, "Add Another Account");
		}

		explicitWaitVisibility(GmailLoginPage.enterEmailID, 20);
		Aclick(GmailLoginPage.enterEmailID, "Enter Email Field");
		type(GmailLoginPage.enterEmailID, userId, "Entered Email ID");
		Aclick(GmailLoginPage.nextButton, "Next Button");
		explicitWaitVisibility(GmailLoginPage.enterPassword, 20);
		// Aclick(GmailLoginPage.enterPassword, "Enter Password Field");
		type(GmailLoginPage.enterPassword, password, "Password");
		Aclick(GmailLoginPage.nextButton, "Next Button");

		if (verifyElementPresent(GmailLoginPage.txtKeepYourAccountUpdate,
				"Keep Your account updated with phone number")) {
			scrollToBottomOfPage();
			Aclick(GmailLoginPage.btnYesImIn, "Yes I'm in");
		}

		explicitWaitVisibility(GmailLoginPage.btnIAgree, 20);
		Aclick(GmailLoginPage.btnIAgree, "I Agree Button");
		explicitWaitVisibility(GmailLoginPage.txtGoogleServices, 20);

		Aclick(GmailLoginPage.btnMore, "More Button");
		explicitWaitVisibility(GmailLoginPage.btnAccept, 20);
		Aclick(GmailLoginPage.btnAccept, "Accept Button");
		waitTime(10000);
	}
	/**
	 * Business method for RingPay Application Logout
	 * 
	 */

	public void ringPayLogout() throws Exception {
		extent.HeaderChildNode("RingPay Logout");

		explicitWaitVisibility(RingLoginPage.objTopMenu, 15);
		click(RingLoginPage.objTopMenu, "Top left menu button");

		explicitWaitVisibility(RingLoginPage.objProfileSelect, 10);
		click(RingLoginPage.objProfileSelect, "Profile Select Button");

		explicitWaitVisibility(RingLoginPage.objLogoutBtn, 10);

		click(RingLoginPage.objLogoutBtn, "Logout Button");

		logger.info("Logout popup comes up");
		extent.extentLoggerPass("Logout popup", "Logout popup comes up");

		explicitWaitVisibility(RingLoginPage.objYesBtn, 10);
		click(RingLoginPage.objYesBtn, "Yes confirmation button");

		logger.info("User is successfully logged out");
		extent.extentLoggerPass("Logout confirmation", "User is successfully logged out");

	}

	public void hamburgerTab() throws Exception {
		verifyElementPresentAndClick(RingUserDetailPage.objHamburgerTab, "Hamburger Tab");
		verifyElementPresentAndClick(RingUserDetailPage.objProfileTabCompletedPercentage,
				"Profile Completed Percentage tab");
		verifyElementPresentAndClick(RingUserDetailPage.objLogoutBtn, "Logout Button");
		logger.info("Are you sure you want to Logout?");
		explicitWaitVisibility(RingUserDetailPage.objLogOutYesBtn, 10);
		click(RingUserDetailPage.objLogOutYesBtn, "Yes Button");
	}

//==============================================InstaLoan Onboarding==========================================================================
	public void mockUserAPI() throws ClassNotFoundException, SQLException {
		extent.HeaderChildNode("DB");
		ValidatableResponse response = Utilities.MockuserAPI(
				"https://testing-gateway.test.paywithring.com/api/v1/mock-data/user", "male",
				"hkot6SltTdCXN78W3Wyc6+Fsgl6pnQB+ApULxNGovYM=");
		firstName = response.extract().jsonPath().getString("data.data.first_name");
		System.out.println("First Name= " + firstName);
		middleName = response.extract().jsonPath().getString("data.data.middle_name");
		System.out.println("Middle Name= " + middleName);
		lastName = response.extract().jsonPath().getString("data.data.last_name");
		System.out.println("Last Name =" + lastName);
		panCard = response.extract().jsonPath().getString("data.data.pan");
		System.out.println("Pan Card= " + panCard);
		mobileNumber = response.extract().jsonPath().getString("data.data.mobile_number");
		System.out.println("Mobile Number= " + mobileNumber);
		mothersName = response.extract().jsonPath().getString("data.data.mother_name");
		System.out.println("Mother Name= " + mothersName);
		email = response.extract().jsonPath().getString("data.data.email");
		System.out.println("Email= " + email);
		gender = response.extract().jsonPath().getString("data.data.gender");
		System.out.println("Gender= " + gender);
		String otp = response.extract().jsonPath().getString("data.data.otp");
		System.out.println(otp);
		String dob = response.extract().jsonPath().getString("data.data.dob");
		System.out.println(dob);
		String[] dateSplit = dob.split("-");
		year = dateSplit[0];
		System.out.println("years---" + year);
		String month = dateSplit[1];
		System.out.println("month---" + month);
		date = dateSplit[2];
		System.out.println("date---" + date);
		int monthNumber = Integer.parseInt(month);
		monthByName = Month.of(monthNumber).getDisplayName(TextStyle.SHORT, Locale.US);
		System.out.println(monthByName);
		aadharFrontImage = response.extract().jsonPath().getString("data.data.aadhaar_front_image.asset_path");
		System.out.println("Front Aadhar Image---->" + aadharFrontImage);
		aadharBackImage = response.extract().jsonPath().getString("data.data.aadhaar_back_image.asset_path");
		System.out.println("Back Aadhar Image---->" + aadharBackImage);
		panImage = response.extract().jsonPath().getString("data.data.pan_image.asset_path");
		System.out.println("Pan Card Image---->" + panImage);
		System.out.println("--------------------------------------------------------------------------------------");
//		executeQuery("SELECT * FROM db_tradofina.instaloan_whitelisted_users;");
//		executeUpdate("Update db_tradofina.instaloan_whitelisted_users set approved_amount='000' where id=48","SELECT * FROM db_tradofina.instaloan_whitelisted_users;");
//		executeInsert();
	}

	public void instaLoanWhitelistLogic() throws Exception {
		extent.HeaderChildNode("WhiteList Logic");
		loginOnboarding("1");
		mobileNoValidation1(mobileNumber);
		enterOtp(prop.getproperty("OTP"));
		readAndAccept();
		waitTime(60000);
		instaUserDetails(firstName,lastName,mothersName,email,gender);
		instaNewCommunicationAddress();
		waitTime(10000);
		instaKycDocument();
		instaPanCardDetails(prop.getproperty("RingAdminEmail"),prop.getproperty("RingAdminPassword"),prop.getproperty("RingAdminOTP"),"OPTIONAL","13000","2311","2319");
		panCardDetails();
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("InstaLoanMPIN"),prop.getproperty("InstaLoanMPIN"));
	}

	public void loginOnboarding(String amount) throws Exception {
		if (verifyElementPresent(RingLoginPage.objCamPermPopUp, "Enable permissions button")) {
			enablePermissions();
		}
		verifyElementPresent(RingLoginPage.objScanQRPage, getText(RingLoginPage.objScanQRPage));
		if (verifyElementPresent(RingLoginPage.objMakePayment, "Make Payment Page")) {
			keyLogin = getText(RingLoginPage.objMakePayment);
			System.out.println(keyLogin);
		} else {
			keyLogin = getText(RingLoginPage.objScanQRPage);
			System.out.println(keyLogin);
		}

		switch (keyLogin) {
		case "Say hello to freedom!!":
			click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			click(RingLoginPage.objMakePaymentLetsRingItBtn,getText(RingLoginPage.objMakePaymentLetsRingItBtn));
			click(RingPayMerchantFlowPage.objAmountTextField, "Enter Amount Field");
			type1(RingPayMerchantFlowPage.objAmountTextField, amount, "Amount Field");
			verifyElementPresentAndClick(RingLoginPage.objMakePaymentPageProceedBtn,getText(RingLoginPage.objMakePaymentPageProceedBtn));
			verifyIsElementDisplayed(RingPayMerchantFlowPage.objLoginPageHeader,getTextVal(RingPayMerchantFlowPage.objLoginPageHeader, "Page"));
			softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
			logger.info("User redirected to Signup/Login Screen");
			extent.extentLogger("login", "User redirected to Signup/Login Screen");
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
			loginMobile();
			break;

		case "Scan any QR to get started":
			verifyElementPresentAndClick(RingLoginPage.objSignUpLoginBt, getText(RingLoginPage.objSignUpLoginBt));
			softAssertion.assertEquals(getText(RingPayMerchantFlowPage.objLoginPageHeader), "Sign up/Login");
			logger.info("User redirected to Signup/Login Screen");
			extent.extentLogger("login", "User redirected to Signup/Login Screen");
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithMobileCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithMobileCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithGoogleCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithGoogleCTA, "CTA"));
			verifyElementPresent(RingPayMerchantFlowPage.objContinueWithFacebookCTA,getTextVal(RingPayMerchantFlowPage.objContinueWithFacebookCTA, "CTA"));
			loginMobile();
			break;
		default: logger.info("Application crashed!!");
				 extent.extentLoggerFail("App Crash", "Application crashed!!");
			break;
		}
	}
	
	public void readAndAccept() throws Exception {
		
		click(RingLoginPage.objReadAcceptBtn, "Read & Accept button");
		if (verifyElementPresent(RingLoginPage.objlocationPopup, "RingPay permissions popup")) {

			logger.info("Ring Pay Permissions page (SMS, LOCATION & PHONE)");
			String ringPermissionTxt = getText(RingLoginPage.objlocationPopup);
			Assert.assertEquals(ringPermissionTxt, "Allow Ring Debug to access this device’s location?");

			explicitWaitVisibility(RingLoginPage.objLocAccess, 10);

			click(RingLoginPage.objLocAccess, "Location Access option");
			click(RingLoginPage.objPhoneAccess, "Phone access option");
			click(RingLoginPage.objSMSAccess, "SMS access option");
		}
	}
	
	public void instaUserDetails(String firstName, String lastName, String mothersName, String email, String gender) throws Exception {
		explicitWaitVisibility(RingUserDetailPage.objFirstName, 10);
		click(RingUserDetailPage.objFirstName, "First Name Field");
		type(RingUserDetailPage.objFirstName,firstName , "First Name field");

		explicitWaitVisibility(RingUserDetailPage.objLastName, 10);
		click(RingUserDetailPage.objLastName, "Last Name Field");
		type(RingUserDetailPage.objLastName, lastName, "Last Name field");

		hideKeyboard();
		explicitWaitVisibility(RingUserDetailPage.objMothersName, 10);
		click(RingUserDetailPage.objMothersName, "Mother's Name Field");
		type(RingUserDetailPage.objMothersName, mothersName, "Mother's Name field");
		
		hideKeyboard();
		explicitWaitVisibility(RingUserDetailPage.objEmail, 10);
		click(RingUserDetailPage.objEmail, "Email Field");
		click(RingUserDetailPage.objNone, "None of the Above Button");
		type(RingUserDetailPage.objEmail, email, "Email Filed");
		hideKeyboard();
		dateOfBirth(monthByName, date, year);
		click(RingLoginPage.objGender, "Gender male is selected");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
	}
	
	public void instaLoancongratsScreen() throws Exception {
		verifyElementPresent(RingLoginPage.objCongratsPage, getText(RingLoginPage.objCongratsPage));
		verifyElementPresent(RingLoginPage.objApprovedRingLimit, getText(RingLoginPage.objApprovedRingLimit) + getText(RingLoginPage.objApprovedRinglimitAmt));
		verifyElementPresentAndClick(RingLoginPage.objIAcceptCheckBox, "I accept the Ring’s Terms & Conditions & IT Act 2000 checkbox");
		verifyElementPresentAndClick(RingLoginPage.objAcceptOfferBtn, getText(RingLoginPage.objAcceptOfferBtn));
	}
	public void instaLoanSetPin(String enterMPIN , String ReEnterMPIN) throws Exception {
		verifyElementPresent(RingLoginPage.objSetPinPage, getText(RingLoginPage.objSetPinPage));
		waitTime(4000);
		Aclick(RingLoginPage.objEnterPin, "Mpin Field");
		type(RingLoginPage.objEnterPin, enterMPIN, "MPIN Field");
		Aclick(RingUserDetailPage.objReEnterPin, "Re-Enter Pin Field");
		type(RingUserDetailPage.objReEnterPin, ReEnterMPIN, "Re-Enter pin Field");
		verifyElementPresentAndClick(RingLoginPage.objSetinSubmitBtn, getText(RingLoginPage.objSetinSubmitBtn));
	}
	public void instaNewCommunicationAddress() throws Exception {
		if(verifyElementPresent2(AddAddressPage.objAddressHeader, "New Communication Address Page Header")) {
		click(AddAddressPage.objRoomNoTextField, "Room No Field");
		type(AddAddressPage.objRoomNoTextField, "86", "Room No. Field");
			
		click(AddAddressPage.objAddressLineOneTextField, "Address Line 1 Field");
		type(AddAddressPage.objAddressLineOneField, "44, Borivali", "Address Line 1");
			
		click(AddAddressPage.objAddressLineTwoField, "Address Line 2 Field");
		type(AddAddressPage.objAddressLineTwoField, "Vasa Street",getTextVal(AddAddressPage.objAddressLineTwoField, "Text Field"));
		
		hideKeyboard();
		click(AddAddressPage.objLandmarkTextFields, "Landmark Field");
		type(AddAddressPage.objLandmarkTextFields, "Das Gupta street",getTextVal(AddAddressPage.objLandmarkTextFields, "Text Field"));
		
		hideKeyboard();
		Swipe("up", 2);
		click(AddAddressPage.objPinCodeField, "Pincode Field");
		type(AddAddressPage.objPinCodeField, "400080", getTextVal(AddAddressPage.objPinCodeField, "Text Field"));

		hideKeyboard();
		explicitWaitVisibility(AddAddressPage.objCityTextField, 10);
		logger.info(getTextVal(AddAddressPage.objCityTextField, "City Name autoPopulated after entering pincode"));
		extent.extentLogger("CityName",getTextVal(AddAddressPage.objCityTextField, "City Name autoPopulated after entering pincode"));

		explicitWaitVisibility(AddAddressPage.objStateTextField, 10);
		logger.info(getTextVal(AddAddressPage.objStateTextField, "State Name autoPopulated after entering pincode"));
		extent.extentLogger("CityName",getTextVal(AddAddressPage.objStateTextField, "State Name autoPopulated after entering pincode"));
			
		explicitWaitVisibility(AddAddressPage.objSubmitButton, 10);
		click(AddAddressPage.objSubmitButton, "Submit Button");
		} else {
			logger.warn("Address Page is not displyed");
			extent.extentLoggerWarning("Address Page", "Address Page is not displyed");
		}
	}
	
	public void instaKycDocument() throws Exception {
		verifyElementPresent(InstaLoanPage.objKYCHeader, getText(InstaLoanPage.objKYCHeader));
		verifyElementPresentAndClick(InstaLoanPage.objKYCFrontAadhar, "Upload Front Aadhar");
		if(verifyElementPresent(InstaLoanPage.objCaptureAadharFrontImage, "Capture Aadhar Front Page")) {
			
			setPlatform("Web");
			System.out.println("platform changed to web");
			String pf = getPlatform();
			System.out.println(pf);
			waitTime(5000);
			new RingPayBusinessLogic(null);
			waitTime(10000);
			getWebDriver().get(aadharFrontImage);
			waitTime(10000);
			
			setPlatform("Android");
			initDriver();
			waitTime(5000);
			click(InstaLoanPage.objCaptureBtn, "Clicked on Capture Button");
			click(InstaLoanPage.objKYCCaptureContinueBtn, getText(InstaLoanPage.objKYCCaptureContinueBtn));
			setPlatform("Web");
			String pft = getPlatform();
			System.out.println(pft);
			initDriver();
			waitTime(10000);

			getWebDriver().get(aadharBackImage);
			waitTime(10000);
			
			setPlatform("Android");
			initDriver();
			waitTime(5000);
			click(InstaLoanPage.objBackAadharCaptureBtn, "Clicked on Capture Button");
			click(InstaLoanPage.objKYCCaptureContinueBtn, getText(InstaLoanPage.objKYCCaptureContinueBtn));
			
			setPlatform("Web");
			String pf2 = getPlatform();
			System.out.println(pf2);
			initDriver();
			waitTime(10000);
			BrowsertearDown();
			waitTime(10000);
			
			setPlatform("Android");
			initDriver();
			waitTime(5000);
			verifyElementPresent(InstaLoanPage.objKYCCAptureSelfie, getText(InstaLoanPage.objKYCCAptureSelfie));
			waitTime(5000);
			verifyElementPresentAndClick(InstaLoanPage.objSelficeCaptureBtn, "Camera Capture Button");
			verifyElementPresentAndClick(InstaLoanPage.objKYCCaptureContinueBtn, getText(InstaLoanPage.objKYCCaptureContinueBtn));
			verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
		}
	}
	public void instaPanCardDetails(String ringAdminEmail,String ringAdminPassword,String ringAdminOTP,String eligible_type, String approved_amount,String offer_id,String repeat_offer_id) throws Exception {
		
		waitTime(5000);
		setPlatform("Web");
		System.out.println("platform changed to web");
		String pf = getPlatform();
		System.out.println(pf);
		waitTime(5000);
		new RingPayBusinessLogic("ring");
		waitTime(10000);
		
		
		click(InstaLoanPage.objEmail, "Email Field");
		type(InstaLoanPage.objEmail, ringAdminEmail, "Email Field");
		verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
		click(InstaLoanPage.objPasswordField, "Password Field");
		clearField(InstaLoanPage.objPasswordField, "Password Field");
		type(InstaLoanPage.objPasswordField, ringAdminPassword, "Password Field");
		click(InstaLoanPage.objOTPField, "OTP Field");
		clearField(InstaLoanPage.objOTPField, "OTP Field");
		type(InstaLoanPage.objOTPField, ringAdminOTP, "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
		verifyElementPresentAndClick(InstaLoanPage.objUserTab, getText(InstaLoanPage.objUserTab));
		selectByVisibleTextFromDD(InstaLoanPage.objSearchUser, "Mobile Number");
		click(InstaLoanPage.objSearchTerm, "Search Item Field");
		type(InstaLoanPage.objSearchTerm, mobileNumber, "Search Item Field");
		click(InstaLoanPage.objSearchBtn, "Search Button");
		waitTime(10000);
		String userReferenceNo = getText(InstaLoanPage.objUserReferenceNo);
		System.out.println(userReferenceNo);
		waitTime(10000);
		ScrollToTheElement(InstaLoanPage.dragDown);
		waitTime(5000);
		executeInsert(userReferenceNo,eligible_type,approved_amount,offer_id,repeat_offer_id);
		executeQuery("SELECT * FROM db_tradofina.instaloan_whitelisted_users Where user_reference_number = '"+userReferenceNo+"';");
		
		waitTime(5000);
		JSClick(InstaLoanPage.objOthersTab, "Other Tab");
		ScrollToTheElement(InstaLoanPage.dragDown);
		JSClick(InstaLoanPage.objPanNSDLData, "PanNSDL Tab");
		JSClick(InstaLoanPage.objAddPanCard, "Add PanCard");
		
		click(InstaLoanPage.objNameField, "First Name Field");
		type(InstaLoanPage.objNameField, firstName, "First Name Field");
		click(InstaLoanPage.objMiddleNameField, "Middle Name Field");
		type(InstaLoanPage.objMiddleNameField, middleName, "Middle Name Field");
		click(InstaLoanPage.objLastNameField, "Last Name Field");
		type(InstaLoanPage.objLastNameField, lastName, "Last Name Field");
		click(InstaLoanPage.objPanNoField, "PanNo. Field");
		type(InstaLoanPage.objPanNoField, panCard, "PanNo. Field");
		selectByVisibleTextFromDD(InstaLoanPage.objPanStatus, "Valid");
		verifyElementPresentAndClick(InstaLoanPage.objSubmitBtn, "Submit Button");
		
		BrowsertearDown();
		waitTime(10000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
	}
	public void panCardDetails() throws Exception {
		verifyElementPresent(InstaLoanPage.objPanNoHeader, getText(InstaLoanPage.objPanNoHeader));
		verifyElementPresentAndClick(InstaLoanPage.objPanNoEnter, "Pan Card Number Field");
		type(InstaLoanPage.objPanNoEnter, panCard, "Pan Card Number Field");
		verifyElementPresentAndClick(RingUserDetailPage.objRegisterBtn, "Proceed Button");
	}
	
	
	
	public String oldAdminPanelGBGrid() throws Exception{
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://ci-api-gateway.test.ideopay.in/admin/login");
        
        click(InstaLoanPage.objEmail, "Email Field");
		type(InstaLoanPage.objEmail, "shakir.muchumarri@collabera.com", "Email Field");
		verifyElementPresentAndClick(InstaLoanPage.objContinueBtn, getText(InstaLoanPage.objContinueBtn));
		click(InstaLoanPage.objPasswordField, "Password Field");
		clearField(InstaLoanPage.objPasswordField, "Password Field");
		type(InstaLoanPage.objPasswordField, "123456", "Password Field");
		click(InstaLoanPage.objOTPField, "OTP Field");
		clearField(InstaLoanPage.objOTPField, "OTP Field");
		type(InstaLoanPage.objOTPField, "888888", "OTP Field");
		verifyElementPresentAndClick(InstaLoanPage.objLoginBtn, getText(InstaLoanPage.objLoginBtn));
		verifyElementPresentAndClick(InstaLoanPage.objPurple,getText(InstaLoanPage.objPurple));
		verifyElementPresentAndClick(InstaLoanPage.objGBGrid,getText(InstaLoanPage.objGBGrid));
		selectByVisibleTextFromDD(InstaLoanPage.objModelVersion,"FRESH-V17");
		click(InstaLoanPage.objSubmitFilter,"Submit filter");
		verifyElementPresentAndClick(InstaLoanPage.objEditGrid,"Edit Grid");
		ScrollToTheElement(InstaLoanPage.objBC1RejectBand);
		click(InstaLoanPage.objBC1RejectBand,getText(InstaLoanPage.objBC1RejectBand));
		waitTime(2000);
		click(InstaLoanPage.objUpdateBtn,"Update reject template");
		explicitWaitVisibility(InstaLoanPage.objPassword,10);
		type(InstaLoanPage.objPassword,"123456","password field");
		String reject_score = getText(InstaLoanPage.objRejectAppScore);
		click(InstaLoanPage.objUpdateSubmit,"Update the reject band submit");
		
		return reject_score;
        
	}
	public void newAdminPanel_appScore(String app_score) throws Exception{
		extent.HeaderChildNode("Updating app score");
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
		getWebDriver().get("https://new-admin-panel.test.ideopay.in/configuration?search=app_score&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(3000);
		click(InstaLoanPage.objEditConfBtn,"Edit Configuration Buton");
		waitTime(2000);
		waitTime(2000);
		clearWebField(InstaLoanPage.objMetaValue);
		waitTime(2000);
		type(InstaLoanPage.objMetaValue,app_score,"app score meta value field");
		waitTime(2000);
		click(InstaLoanPage.objSubmit,"Submit button");
		waitTime(2000);
	}
	
	public String regularOfferReusable(String cibil_score,String app_score,boolean age_flag) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for regular offer test cases");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		BrowsertearDown();
		newAdminPanel_appScore(app_score);
		BrowsertearDown();
		cibilDummy(cibil_score,userRefNo);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		if(age_flag) {
			dateOfBirth("MAY","04","2002");
		}
		else {
			dateOfBirth("MAY","09","1994");
		}
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		//getDriver().resetApp();
		return userRefNo;
		
	}
	public String RingPolicy_BlackBox(String appScore, boolean flag) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for BlackBox pool test cases");
		 String pool_pref="";
		newAdminPanel_appScore(appScore);
		BrowsertearDown();
		waitTime(5000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "6" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		waitTime(30000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		if(flag) {
			dateOfBirth("MAY","04","2002");
		}
		else {
			dateOfBirth("MAY","04","1997");
		}
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		BrowsertearDown();
		getDriver().resetApp();
	    return pool_pref;
	}
	
	public String txnFeeCashFee(String appScore) throws Exception{
		extent.HeaderChildNode("Ring Policy - Txn fee & Cash fee test cases");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		newAdminPanel_appScore(appScore);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		
		dateOfBirth("MAY","09","1994");
		
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(30000);
		
		return userRefNo;
	}
	public String RingPolicy_Digi(String appScore, boolean age_pref_flag,String ccAmt, Boolean digi_reliability) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for BlackBox pool test cases for digi users");
		 String pool_pref="";
		newAdminPanel_appScore(appScore);
		BrowsertearDown();
		waitTime(5000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "6" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		digiReliability(userRefNo,ccAmt,digi_reliability);
		BrowsertearDown();
		waitTime(5000);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		if(age_pref_flag) {
			dateOfBirth("DEC","05","2002");
		}
		else {
			dateOfBirth("MAY","07","1996");
		}
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		
	    getDriver().resetApp();
		return userRefNo;
	}
	
	public void digiReliability(String userRefNo,String ccAmount, Boolean digi_reliability) throws Exception{
		getWebDriver().get("https://data.test.ideopay.in/admin#!/test/update-digiscore");
		explicitWaitVisibility(DigiWeb.userName,10);
		type(DigiWeb.userName,prop.getproperty("digiUserName"),"User name field");
		click(DigiWeb.loginBtn,"Login button");
		waitTime(2000);
		type(DigiWeb.password,prop.getproperty("digiPassword"),"Password field");
		type(DigiWeb.otp,prop.getproperty("OTP"),"Otp field");
		click(DigiWeb.loginBtn1,"Login button");
		waitTime(3000);
		getWebDriver().get("https://data.test.ideopay.in/admin#!/test/update-digiscore");
		explicitWaitVisibility(DigiWeb.userRefNo,10);
		type(DigiWeb.userRefNo,userRefNo,"User Ref No");
		if(digi_reliability) {
			selectByVisibleTextFromDD(DigiWeb.digiSelect,prop.getproperty("digi_reliable"));
		}
		else {
			selectByVisibleTextFromDD(DigiWeb.digiSelect,"No");
		}
		type(DigiWeb.salaryV1,prop.getproperty("salary_v1"),"Salary V1");
		type(DigiWeb.incomeV1,prop.getproperty("income_v1"),"Income V1");
		type(DigiWeb.incomeV2,prop.getproperty("digi_income_v2"),"Income V2");
		if(ccAmount!=null) {
			ScrollToTheElement(DigiWeb.ccWithdraw);
			waitTime(2000);
			type(DigiWeb.ccWithdraw,ccAmount,"CC Withdraw Amount");
		}
		
		
		type(DigiWeb.socialScore,prop.getproperty("social_score"),"Social Score");
		type(DigiWeb.timeStamp,prop.getproperty("timeStamp"),"Time Stamp");
		ScrollToTheElement(DigiWeb.custNameMatch);
		waitTime(2000);
		selectByVisibleTextFromDD(DigiWeb.custNameMatch,prop.getproperty("customer_name_match"));
		selectByVisibleTextFromDD(DigiWeb.dupNoSelect,prop.getproperty("duplicate_imei"));
		ScrollToTheElement(DigiWeb.updateDigiScoreBtn);
		waitTime(2000);
		click(DigiWeb.updateDigiScoreBtn,"Update digi score button");
		waitTime(15000);
		
	}
	
	public String blockUsers(String url, String gender, String encryptedName,String flag) throws Exception{
		HashMap map = mockUserAPI(url, gender,encryptedName);
		String mobNo = map.get("mobileNumber").toString();
		String imei = map.get("imei").toString();
		String pan_card = map.get("panCard").toString();
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		mobileNoValidation2(mobNo);
		//executeInsert_block(mobNo,"mobile","test");
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		BrowsertearDown();
		waitTime(5000);
		setPlatform("Android");
		initDriver();
		waitTime(10000);
		userDetails();
		dateOfBirth("MAY","07","1996");
		if(flag.equals("mobile_block")) {
			executeInsert_block(mobNo,"mobile","test");
			Aclick(UserRegistrationNew.objProceed, "Proceed Button");
			waitTime(15000);
		}
		else if(flag.equals("imei_block")) {
			executeInsert_block(imei,"imei","test");
			Aclick(UserRegistrationNew.objProceed, "Proceed Button");
			waitTime(15000);
		}
		else if(flag.equals("pan_block")) {
			executeInsert_block(pan_card,"pan","test");
			Aclick(UserRegistrationNew.objProceed, "Proceed Button");
			waitTime(15000);
		}
		
		else if(flag.equals("after_block")) {
			Aclick(UserRegistrationNew.objProceed, "Proceed Button");
			waitTime(15000);
			executeInsert_block(mobNo,"mobile","test");
		}
		
		return userRefNo;
		
	}
	
	public String kla_cc_offer(String app_score,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for kla_cc offer test cases");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		waitTime(3000);
		newAdminPanel_appScore(app_score);
		customDataPoints(cc_flag,kla_flag,userRefNo);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		getDriver().resetApp();
		return userRefNo;
		
	}
	
	public String lowSegmentationOffer(String appScore,String cibil_score,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for low_segmentation offer test cases");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		waitTime(3000);
		newAdminPanel_appScore(appScore);
		customDataPoints_policy("8000","8000",userRefNo);
		cibilDummy(cibil_score,userRefNo);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		//getDriver().resetApp();
		return userRefNo;
	}
	
	public String repeatGrid(String amt) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for low_segmentation offer test cases");
		System.out.println(calendarDate(15));
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		newAdminPanel_appScore("0.007666832");
		/*Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);*/
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		waitTime(3000);
		cibilDummy(prop.getproperty("ltbc1"), userRefNo);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		dateOfBirth(prop.getproperty("dateOfBirthMonth"), prop.getproperty("dateOfBirthDate"), prop.getproperty("dateOfBirthYear"));
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(10000);
		addAddress("asasa","asaas","asasa","asq","560102");
		waitTime(5000);
		instaLoancongratsScreen();
		instaLoanSetPin(prop.getproperty("ValidPin"),prop.getproperty("ValidPin"));
		waitTime(5000);
		verifyElementPresentAndClick(RingUserDetailPage.objCrossBtn, "Cross Button");
		String lineRefNo = executeQuery1("SELECT line_reference_number FROM db_tradofina.lines where user_reference_number='"+userRefNo+"';");
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,amt,"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		generateForceBill(userRefNo,lineRefNo,calendarDate(15));
		explicitWaitVisibility(BankTransferModule.objNoBtn,30);
		click(BankTransferModule.objNoBtn,"No button");
		explicitWaitClickable(HomPageNew.objPayEarlyBtn, 30);
		Aclick(HomPageNew.objPayEarlyBtn, "Pay Early Button");
		explicitWaitVisibility(HomPageNew.objRepaymentHeader, 10);
		waitTime(3000);
		hideKeyboard();
		explicitWaitVisibility(HomPageNew.objNetBankingOption, 10);
		Aclick(HomPageNew.objNetBankingOption, "Net Banking/Debit card option");
		explicitWaitVisibility(HomPageNew.objNetbanking,20);
		Aclick(HomPageNew.objNetbanking, "Netbanking");
		explicitWaitVisibility(HomPageNew.objSelectBankHeader, 10);
		Aclick(HomPageNew.objSBIBank, "SBI bank");
		Aclick(HomPageNew.objPayNowBtn, "Pay Now Button");
		explicitWaitVisibility(HomPageNew.objSuccessBtn, 10);
		Aclick(HomPageNew.objSuccessBtn, "Success Button");
		waitTime(4000);
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		if(Integer.parseInt(amt)>=50) {
			System.out.println("Repeat set");
			explicitWaitVisibility(RingLoginPage.objIAcceptCheckBox,30);
			click(RingLoginPage.objIAcceptCheckBox,"Terms & Conditions check box");
			click(UserRegistrationNew.objOkGotIt, "OK Got it Button");
		}
		else if(Integer.parseInt(amt)<50) {
			System.out.println("Repeat not set");
			verifyElementNotPresent(RingLoginPage.objIAcceptCheckBox,30);
		}
		click(RingLoginPage.objTopMenu, "Top left menu button");
		explicitWaitVisibility(HomePage.objBillHistory,10);
		click(HomePage.objBillHistory,"Bill history tab");
		explicitWaitVisibility(HomePage.objBillRefNo,10);
		String billRefNo = getText(HomePage.objBillRefNo);
		Back(2);
		return billRefNo;
	}
	
	public String noOffer(String appScore, String cibil_score) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for no offer test cases");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		waitTime(3000);
		newAdminPanel_appScore(appScore);
		cibilDummy(cibil_score,userRefNo);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		addAddress("aas","adsa","asads","askjaskjks","560102");
		getDriver().resetApp();
		return userRefNo;
		
	}
	public String pool3Reusable(String appScore, String cibil_score, String cc_flag, String kla_flag) throws Exception{
		extent.HeaderChildNode("Ring Policy - Reusable Method for pool 3 offer test cases");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		waitTime(3000);
		newAdminPanel_appScore(appScore);
		customDataPoints(cc_flag,kla_flag,userRefNo);
		cibilDummy(cibil_score,userRefNo);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		getDriver().resetApp();
		return userRefNo;	
		
	}
	
	public String gbReject(String cibil_score) throws Exception{
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		Utilities.setPlatform = "Web";
        new CommandBase("Chrome");
        waitTime(4000);
        getWebDriver().get("https://new-admin-panel.test.ideopay.in/users?selected=mobile_number&search="+mobNo+"&");
		type(InstaLoanPage.objEmail,"shakir.muchumarri@collabera.com","Email text field");
		click(InstaLoanPage.objContinue,"Continue Button");
		waitTime(10000);
		explicitWaitVisibility(InstaLoanPage.objUserRefNo,10);
		String userRefNo = getText(InstaLoanPage.objUserRefNo);
		waitTime(3000);
		
		cibilDummy(cibil_score,userRefNo);
		String rejectAppScore = oldAdminPanelGBGrid();
		newAdminPanel_appScore(rejectAppScore);
		setPlatform("Android");
		initDriver();
		waitTime(5000);
		userDetails();
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		getDriver().resetApp();
		return userRefNo;	
	}
	
	public String scanPay(String same_amt,String greater_amt) throws Exception{
		extent.HeaderChildNode("Reusable method for scan & pay with registered mobile");
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		mobileNoValidation2("7166177166");
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		explicitWaitVisibility(HomePage.objCloseButton, 10);
		Aclick(HomePage.objCloseButton, "Close Button");
		
		if(same_amt!=null && greater_amt==null) {
			click(HomePage.objScanQrBtn,"Scan QR button");
			explicitWaitVisibility(HomePage.objPaymentField,10);
			type(HomePage.objPaymentField,same_amt,"Pay amt text field");
			click(HomePage.objPayNowBtn,"Pay now button");
		//explicitWaitVisibility(PaymentPage.objEditTransactionPin,10);
			waitTime(15000);
			click(PaymentPage.objEditTransactionPin,"MPIN Field");
			type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
			click(PaymentPage.objContinue,"Continue button");
			explicitWaitVisibility(PaymentPage.objGoToHomePage,10);
			click(PaymentPage.objGoToHomePage,"Go to homepage button");
			explicitWaitVisibility(HomePage.objAvailableLimit,10);
			click(HomePage.objScanQrBtn,"Scan QR button");
			explicitWaitVisibility(HomePage.objPaymentField,10);
			type(HomePage.objPaymentField,same_amt,"Pay amt text field");
			click(HomePage.objPayNowBtn,"Pay now button");
			explicitWaitVisibility(PaymentPage.objGoToHomePage,10);
			click(PaymentPage.objGoToHomePage,"Go to homepage button");
			explicitWaitVisibility(HomePage.objFirstTransaction,10);
			click(HomePage.objFirstTransaction,"Most recent transaction");
			
			
		}
		else if(greater_amt!=null && same_amt==null) {
			
		}
		explicitWaitVisibility(HomePage.transacNumber,10);
		String txnNumber = getText(HomePage.transacNumber);
		
		return txnNumber;
		
	}
	
	public String bankPay(String same_amt) throws Exception{
		extent.HeaderChildNode("Reusable method for bank transfer with registered mobile");
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		mobileNoValidation2("7166177166");
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		explicitWaitVisibility(HomePage.objCloseButton, 10);
		Aclick(HomePage.objCloseButton, "Close Button");
		
		click(HomePage.moreTab,"More button");
		explicitWaitVisibility(BankTransferModule.objBankTransfer,10);
		click(BankTransferModule.objBankTransfer,"Bank transfer option");
		explicitWaitVisibility(BankTransferModule.objexistingBankAcc,10);
		click(BankTransferModule.objexistingBankAcc,"Existing bank account");
		waitTime(7000);
		click(BankTransferModule.objEnterAmountTextField,"Amt text field");
		type(BankTransferModule.objEnterAmountTextField,same_amt,"Amt text field");
		click(BankTransferModule.objPayNowBtn,"Pay now button");
		waitTime(5000);
		click(BankTransferModule.objEnterPinTextField,"PIN Text field");
		type(BankTransferModule.objEnterPinTextField,"1111","PIN Text field");
		click(BankTransferModule.objNextNavigation,"Next navigation button");
		explicitWaitVisibility(BankTransferModule.objStatusPage,30);
		click(BankTransferModule.objHomePageBtn,"Go to home page button");
		
		click(HomePage.moreTab,"More button");
		explicitWaitVisibility(BankTransferModule.objBankTransfer,10);
		click(BankTransferModule.objBankTransfer,"Bank transfer option");
		explicitWaitVisibility(BankTransferModule.objexistingBankAcc,10);
		click(BankTransferModule.objexistingBankAcc,"Existing bank account");
		waitTime(7000);
		click(BankTransferModule.objEnterAmountTextField,"Amt text field");
		type(BankTransferModule.objEnterAmountTextField,same_amt,"Amt text field");
		click(BankTransferModule.objPayNowBtn,"Pay now button");
		waitTime(5000);
		click(BankTransferModule.objEnterPinTextField,"PIN Text field");
		type(BankTransferModule.objEnterPinTextField,"1111","PIN Text field");
		click(BankTransferModule.objNextNavigation,"Next navigation button");
		click(BankTransferModule.objHomePageBtn,"Go to home page button");
		explicitWaitVisibility(HomePage.objFirstTransaction,10);
		click(HomePage.objFirstTransaction,"Most recent transaction");
		
		explicitWaitVisibility(HomePage.transacNumber,10);
		String txnNumber = getText(HomePage.transacNumber);
		
		return txnNumber;
		
	}
   //==================================DIGI SURROGATE POOL OFFER TEST CASES================================================= 
	public void TC_Ring_Customer_Seg_01(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_01");
		getDriver().resetApp();
		String userRefNo = RingPolicy_Digi(appScore,false,null,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && eligibility.equals("digi_surrogate")) {
			Assert.assertEquals("POOL_1", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_01","TC_Ring_Customer_Seg_01-To verify user receives offer from Pool 1 for Fresh v-16 when app score is less then 0.107666832");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_34","TC_Ring_Customer_Seg_34-To verify if user is eligible for Pool 1 does not get offer from Regular offer");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_148","TC_Ring_Customer_Seg_148-To verify user with segments L1, L2, L3 & DIGI who matches POOL 1 or POOL 2 definition");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_104","TC_Ring_Customer_Seg_104-To verify user's output value is in Accept band for Fresh v16 & Fresh v17");
			
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_37","TC_Ring_Customer_Seg_37-To verify if user is not eligible for Pool 1, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_02(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_02");
		String userRefNo = RingPolicy_Digi(appScore,false,null,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && eligibility.equals("digi_surrogate")) {
			Assert.assertNotEquals("POOL_1", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_02","TC_Ring_Customer_Seg_02-To verify user does not receives offer from Pool 1 for Fresh v-16 when app score is greater then 0.107666833");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_135","TC_Ring_Customer_Seg_135-To verify user with income v2 > 4k");
		}
		
		else{
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_05(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_05");
		String userRefNo = RingPolicy_Digi(appScore,false,null,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && eligibility.equals("digi_surrogate")) {
			Assert.assertEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_05","TC_Ring_Customer_Seg_05-To verify user receives offer from Pool 2 for Fresh v-16 when app score is greater then 0.107666832");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_06(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_06");
		String userRefNo = RingPolicy_Digi(appScore,false,null,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && eligibility.equals("digi_surrogate")) {
			Assert.assertEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_06","TC_Ring_Customer_Seg_06-To verify user receives offer from Pool 2 for Fresh v-16 when app score is equal to 0.107666833");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_07(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_07");
		String userRefNo = RingPolicy_Digi(appScore,false,null,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && eligibility.equals("digi_surrogate")) {
			Assert.assertEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_07","TC_Ring_Customer_Seg_07-To verify user receives offer from Pool 2 for Fresh v-16 when app score is less then 0.16426588");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
			
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_08(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_08");
		String userRefNo = RingPolicy_Digi(appScore,false,null,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && eligibility.equals("digi_surrogate")) {
			Assert.assertNotEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_08","TC_Ring_Customer_Seg_08-To verify user does not receives offer from Pool 2 for Fresh v-16 when app score is greater then 0.16426588");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	
	
	//================================================CIBIL surrogate POOL Offer test cases =======================================
	public void TC_Ring_Customer_Seg_03(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_03");
		getDriver().resetApp();
		String pool_pref = RingPolicy_BlackBox(appScore,false);
		if(pool_pref!="") {
			Assert.assertEquals("POOL_1", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_03","TC_Ring_Customer_Seg_03-To verify user receives offer from Pool 1 for Fresh v-17 when app score is less then 0.13826741");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_34","TC_Ring_Customer_Seg_34-To verify if user is eligible for Pool 1 does not get offer from Regular offer");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_136","TC_Ring_Customer_Seg_136-To verify user with income v2 < 4k");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_104","TC_Ring_Customer_Seg_104-To verify user's output value is in Accept band for Fresh v16 & Fresh v17");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_37","TC_Ring_Customer_Seg_37-To verify if user is not eligible for Pool 1, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_04(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_04");
		String pool_pref = RingPolicy_BlackBox(appScore,false);
		if(pool_pref!="") {
			Assert.assertNotEquals("POOL_1", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_04","TC_Ring_Customer_Seg_04-To verify user does not receives offer from Pool 1 for Fresh v-17 when app score is greater then 0.13826741");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_09(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_09");
		String pool_pref = RingPolicy_BlackBox(appScore,false);
		if(pool_pref!="") {
			Assert.assertEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_09","TC_Ring_Customer_Seg_09-To verify user receives offer from Pool 2 for Fresh v-17 when app score is greater then 0.13826741");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_10(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_10");
		String pool_pref = RingPolicy_BlackBox(appScore,false);
		if(pool_pref!="") {
			Assert.assertEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_10","TC_Ring_Customer_Seg_10-To verify user receives offer from Pool 2 for Fresh v-17 when app score is equal to 0.13826741");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	public void TC_Ring_Customer_Seg_11(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_11");
		String pool_pref = RingPolicy_BlackBox(appScore,false);
		if(pool_pref!="") {
			Assert.assertEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_11","TC_Ring_Customer_Seg_11-To verify user receives offer from Pool 2 for Fresh v-17 when app score is less then 0.184381501");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_35","TC_Ring_Customer_Seg_35-To verify if user is eligible for Pool 2 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_38","TC_Ring_Customer_Seg_38-To verify if user is not eligible for Pool 2, get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_12(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_12");
		String pool_pref = RingPolicy_BlackBox(appScore,false);
		if(pool_pref!="") {
			Assert.assertNotEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_12","To verify user does not receives offer from Pool 2 for Fresh v-17 when app score is greater then 0.184381501");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_36","TC_Ring_Customer_Seg_36-To verify if user is eligible for Pool 3 does not get offer from Regular offer");
		}
		
		else {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_39","TC_Ring_Customer_Seg_39-To verify if user is not eligible for Pool 3 ,get offer from Regular offer");
		}
	}
	
	public void TC_Ring_Customer_Seg_28(String cibil_score,String app_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_28");
		String pool_pref="";
		String refNo = regularOfferReusable(cibil_score,app_score,false);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+refNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String segment = obj.getString("gb_segment");
		String loan_amount = obj.getString("allowed_loan_amount");
		System.out.println(loan_amount);
		
		if(obj.has("offer_preference")) {
			pool_pref=obj.getString("offer_preference");
		}
		
		//for band 1 to 9 users
		if((segment.equals("LTBC1") && pool_pref=="" && loan_amount.equals(prop.getproperty("ltbc1_offer")))){
			Assert.assertEquals("LTBC1", segment);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_28","TC_Ring_Customer_Seg_28-To verify if user receive offer according to LTBC1");
			
		}
		
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_29(String cibil_score,String app_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_29");
		String pool_pref="";
		String refNo = regularOfferReusable(cibil_score,app_score,false);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+refNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String segment = obj.getString("gb_segment");
		String loan_amount = obj.getString("allowed_loan_amount");
		System.out.println(loan_amount);
		
		if(obj.has("offer_preference")) {
			pool_pref=obj.getString("offer_preference");
		}
		
		if((segment.equals("BC1") && pool_pref=="" && loan_amount.equals(prop.getproperty("bc1_offer")))){
			Assert.assertEquals("BC1", segment);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_29","TC_Ring_Customer_Seg_29-To verify if user receive offer according to BC1");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_147","TC_Ring_Customer_Seg_147-To verify user with segments BC1, BC2 & THIN who matches POOL 1 or POOL 2 definition");
		}
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_30(String cibil_score,String app_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_30");
		String pool_pref="";
		String refNo = regularOfferReusable(cibil_score,app_score,false);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+refNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String segment = obj.getString("gb_segment");
		String loan_amount = obj.getString("allowed_loan_amount");
		System.out.println(loan_amount);
		
		if(obj.has("offer_preference")) {
			pool_pref=obj.getString("offer_preference");
		}
		
		if(segment.equals("BC2") && pool_pref=="" && loan_amount.equals(prop.getproperty("bc2_offer"))){
			Assert.assertEquals("BC2", segment);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_30","TC_Ring_Customer_Seg_30-To verify if user receive offer according to BC2");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_147","TC_Ring_Customer_Seg_147-To verify user with segments BC1, BC2 & THIN who matches POOL 1 or POOL 2 definition");
		}
		
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_31(String cibil_score,String app_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_31");
		String pool_pref="";
		String refNo = regularOfferReusable(cibil_score,app_score,false);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+refNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String segment = obj.getString("gb_segment");
		String loan_amount = obj.getString("allowed_loan_amount");
		System.out.println(loan_amount);
		
		if(obj.has("offer_preference")) {
			pool_pref=obj.getString("offer_preference");
		}
		
		if((segment.equals("L1") && pool_pref=="" && loan_amount.equals(prop.getproperty("l1_offer")))){
			Assert.assertEquals("L1", segment);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_31","TC_Ring_Customer_Seg_31-To verify if user receive offer according to L1");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_148","TC_Ring_Customer_Seg_148-To verify user with segments L1, L2, L3 & DIGI who matches POOL 1 or POOL 2 definition");
		}
		
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_32(String cibil_score,String app_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_32");
		String pool_pref="";
		String refNo = regularOfferReusable(cibil_score,app_score,false);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+refNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String segment = obj.getString("gb_segment");
		String loan_amount = obj.getString("allowed_loan_amount");
		System.out.println(loan_amount);
		
		if(obj.has("offer_preference")) {
			pool_pref=obj.getString("offer_preference");
		}
		
		if((segment.equals("L2") && pool_pref=="" && loan_amount.equals(prop.getproperty("l2_offer")))){
			Assert.assertEquals("L2", segment);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_32","TC_Ring_Customer_Seg_32-To verify if user receive offer according to L2");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_148","TC_Ring_Customer_Seg_148-To verify user with segments L1, L2, L3 & DIGI who matches POOL 1 or POOL 2 definition");
		}
		
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_33(String cibil_score,String app_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_33");
		String pool_pref="";
		String refNo = regularOfferReusable(cibil_score,app_score,false);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+refNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String segment = obj.getString("gb_segment");
		String loan_amount = obj.getString("allowed_loan_amount");
		System.out.println(loan_amount);
		
		if(obj.has("offer_preference")) {
			pool_pref=obj.getString("offer_preference");
		}
		
		if((segment.equals("L3") && pool_pref=="" && loan_amount.equals(prop.getproperty("l3_offer")))){
			Assert.assertEquals("L3", segment);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_33","TC_Ring_Customer_Seg_33-To verify if user receive offer according to L3");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_148","TC_Ring_Customer_Seg_148-To verify user with segments L1, L2, L3 & DIGI who matches POOL 1 or POOL 2 definition");
		}
		
		getDriver().resetApp();
	}
	
	//================================================AGE Preference test cases =======================================

	public void TC_Ring_Customer_Seg_158_digi(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_158");
		String userRefNo = RingPolicy_Digi(appScore,true,null,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && eligibility.equals("digi_surrogate")) {
			Assert.assertEquals("AGE", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_158","TC_Ring_Customer_Seg_158-To verify user getting offer from Age preference between Age 18 to 22 for Digi, L1, L2 & L3 Segments");

		}

	}
	
	public void TC_Ring_Customer_Seg_158_L1(String appScore,String cibil_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_158");
		String userRefNo = regularOfferReusable(cibil_score,appScore,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		String segment = obj.getString("gb_segment");
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref!="") && (segment.equals("L1") || segment.equals("L2") || segment.equals("L3"))) {
			Assert.assertEquals("AGE", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_158","TC_Ring_Customer_Seg_158-To verify user getting offer from Age preference between Age 18 to 22 for Digi, L1, L2 & L3 Segments");

		}
	}
	
	public void TC_Ring_Customer_Seg_159_BC1(String appScore,String cibil_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_159");
		String userRefNo = regularOfferReusable(cibil_score,appScore,true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		String segment = obj.getString("gb_segment");
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		String eligibility = obj.getString("eligible_source");
		System.out.println(eligibility);
		if((pool_pref=="") && segment.equals("BC1")) {
			Assert.assertNotEquals("AGE", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_159","TC_Ring_Customer_Seg_159-To verify user getting offer from Age preference between Age 18 to 22 for BC1, BC2, LTBC1, NTC & THIN Segments");

		}
	}
	
	public void TC_Ring_Customer_Seg_160(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_160");
		String pool_pref = RingPolicy_BlackBox(appScore,true);
		if(pool_pref!="" && (pool_pref.equals("POOL_1") || pool_pref.equals("POOL_2"))) {
			//Assert.assertEquals("POOL_2", pool_pref);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_160","TC_Ring_Customer_Seg_160-To verify if users Age is in between 18 to 22 & user falls under the app score of POOL");
		}
		
		else if(pool_pref==""){
			extent.extentLoggerPass("TC_Ring_Customer_Seg_161","TC_Ring_Customer_Seg_161-To verify if users Age is not in between 18 to 22 & user does not fall under the app score of POOL");
		}
	}
	
	//================================================CC Cash withdrawal in last 3m test cases =======================================
   
	public void TC_Ring_Customer_Seg_62(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_62");
		
		String userRefNo = RingPolicy_Digi(appScore,false,prop.getproperty("ccAmount_positive"),true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data==null) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_62","TC_Ring_Customer_Seg_62-To verify user is Digi Reliable + CC Cash withdrawal <15k in last 3 months");
		}
	}
	
	public void TC_Ring_Customer_Seg_63(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_63");
		
		String userRefNo = RingPolicy_Digi(appScore,false,prop.getproperty("ccAmount_equals"),true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data==null) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_63","TC_Ring_Customer_Seg_63-To verify user is Digi Reliable + CC Cash withdrawal equal to 15k in last 3 months");
		}
	}

	public void TC_Ring_Customer_Seg_64(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_64");
		
		String userRefNo = RingPolicy_Digi(appScore,false,prop.getproperty("ccAmount_negative"),true);
		waitTime(3000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equalsIgnoreCase("Limit exceed for CC cash withdrawal")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_64","TC_Ring_Customer_Seg_64-To verify user is Digi Reliable + CC Cash withdrawal >15k in last 3 months");
		}
	}
	
	public void TC_Ring_Customer_Seg_65(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_65");
		
		String userRefNo = RingPolicy_Digi(appScore,false,prop.getproperty("ccAmount_negative"),false);
		waitTime(3000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data==null) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_65","TC_Ring_Customer_Seg_65-To verify user is Digi Not Reliable + CC Cash withdrawal >15k in last 3 months");
		}
	}
	
	//================================================Blocking user test cases =======================================

	public void TC_Ring_Customer_Seg_70(String url, String gender, String encryptedName) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_70");
		String userRefNo = blockUsers(url,gender,encryptedName,"mobile_block");
		explicitWaitVisibility(RingUserDetailPage.objSorryMsg,10);
		if(verifyElementExist(RingUserDetailPage.objSorryMsg,"Sorry message")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_70","TC_Ring_Customer_Seg_70-To verify adding users in block table through mobile number, pan hash & IMEI");
		}
		
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("Customer rejected from block list")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_71","TC_Ring_Customer_Seg_71-To verify adding users in block table through mobile number");
		}
		
		getDriver().resetApp();
	}
	
	
	public void TC_Ring_Customer_Seg_74(String url,String gender, String encryptedName) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_74");
		String userRefNo = blockUsers(url,gender,encryptedName,"after_block");
		
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		System.out.println(json_data);
		if((json_data==null) && verifyElementNotPresent(RingUserDetailPage.objSorryMsg,30)) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_74","TC_Ring_Customer_Seg_74-To verify homepage after adding users in block table once limit activated");
		}
		
		getDriver().resetApp();
	}
	
	//================================================KLA offer test cases =======================================
	
	public void TC_Ring_Customer_Seg_170(String appScore,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_170");
		//customDataPoints("1","0","IDEP627127792374G87Y");
		String userRefNo = kla_cc_offer(appScore,cc_flag,kla_flag);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if(pool_pref.equals("KOINO_LOOKALIKE")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_170","TC_Ring_Customer_Seg_170-To verify user receives offer from KLA if 'is_true_comp=1' and GB band does not fall for POOL criteria");
		}
	}
	
	public void TC_Ring_Customer_Seg_171(String appScore,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_171");
		//customDataPoints("1","0","IDEP627127792374G87Y");
		String userRefNo = kla_cc_offer(appScore,cc_flag,kla_flag);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if((pool_pref!="KOINO_LOOKALIKE") && (pool_pref.equals("POOL_1"))) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_171","TC_Ring_Customer_Seg_171-To verify user with 'is_true_comp=1' and GB band falls under POOL criteria");
		}
	}
	
	//================================================CC offer test cases =======================================

	public void TC_Ring_Customer_Seg_172(String appScore,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_172");
		//customDataPoints("1","0","IDEP627127792374G87Y");
		String userRefNo = kla_cc_offer(appScore,cc_flag,kla_flag);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if(pool_pref.equals("CC_PRESENCE")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_172","TC_Ring_Customer_Seg_172-To verify user receives offer from CC Presence if 'unique_cc_count_12m = 1' and GB band does not fall for POOL criteria");
		}
	}
	
	public void TC_Ring_Customer_Seg_173(String appScore,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_173");
		//customDataPoints("1","0","IDEP627127792374G87Y");
		String userRefNo = kla_cc_offer(appScore,cc_flag,kla_flag);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if((pool_pref!="CC_PRESENCE") && (pool_pref.equals("POOL_1"))) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_173","TC_Ring_Customer_Seg_173-To verify user with 'unique_cc_count_12m = 1' and GB band falls under POOL criteria");
		}
	}
	
	public void TC_Ring_Customer_Seg_174(String appScore,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_174");
		//customDataPoints("1","0","IDEP627127792374G87Y");
		String userRefNo = kla_cc_offer(appScore,cc_flag,kla_flag);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if((pool_pref=="KOINO_LOOKALIKE") ) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_174","TC_Ring_Customer_Seg_174-To verify user with 'is_true_comp=1' and 'unique_cc_count_12m = 1'");
		}
	}
	
	//================================================POOL 3 offer test cases =======================================
	public void TC_Ring_Customer_Seg_165(String appScore, String cibil_score, String cc_flag, String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_165");
		String userRefNo = pool3Reusable(appScore,cibil_score, cc_flag, kla_flag);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if((pool_pref=="POOL_3")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_165","TC_Ring_Customer_Seg_165-To verify user is of onboarding segment 1 with CC presence & app score is between band 6 to 7 & GB segment is DIGI, L1, L2 or L3");
		}
	}
	
	public void TC_Ring_Customer_Seg_166(String appScore, String cibil_score, String cc_flag, String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_166");
		String userRefNo = pool3Reusable(appScore,cibil_score, cc_flag, kla_flag);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if((pool_pref!="POOL_3") && (pool_pref=="CC_PRESENCE")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_166","TC_Ring_Customer_Seg_166-To verify user is of onboarding segment 1 with CC presence & app score is between band 6 to 7 & GB segment is BC1, BC2, LTBC1, NTC or THIN");
		}
		
		}
	
	//================================================Low Segmentation offer test cases =======================================
	
	public void TC_Ring_Customer_Seg_131(String appScore,String cibil_score,String cc_flag,String kla_flag) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_131");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount='0' where merchant_type='type2' and segment='LTBC1' and surrogate_type='EQUIFAX' and upper_band='15';","db_tradofina.bnpl_line_offers");
		String userRefNo = lowSegmentationOffer(appScore,cibil_score,cc_flag,kla_flag);
		addAddress("as","assa","afaa","asdafd","560102");
		explicitWaitVisibility(PromoCodeOfferPage.objCheckBox,20);
		click(PromoCodeOfferPage.objCheckBox,"Terms & Conditions check box");
		click(PromoCodeOfferPage.objAcceptOffer,"Accept offer button");
		
		waitTime(4000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String pool_pref="";
		
		if(obj.has("offer_preference")) {
			pool_pref = obj.getString("offer_preference");
			System.out.println(obj.getString("offer_preference"));
		}
		
		if(pool_pref.equals("LOW_SEGMENTATION")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_131","TC_Ring_Customer_Seg_131-To verify user receives offer from Low_Segmentation for Segments BC1 / BC2 / LTBC1 / THIN for band 9-20 only when 'ratio_parsed_total_sms>0.095' and 'latest_loan_history_months_count>25.5' and 'no_of_loan_entries>9.5'");
		}
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount='600' where merchant_type='type2' and segment='LTBC1' and surrogate_type='EQUIFAX' and upper_band='15';","db_tradofina.bnpl_line_offers");
		getDriver().resetApp();
	}

	//================================================No loan offer test cases =======================================
	
	public void TC_Ring_Customer_Seg_99(String appScore,String cibil_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_99");
		
		String userRefNo = noOffer(appScore,cibil_score);
		waitTime(3000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("No amount from matrix")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_99","TC_Ring_Customer_Seg_99-To verify user cibil score where the same is not defined in the offer grid");
		}
	}
	
	//================================================GB Reject/Accept test cases =======================================
	
	public void TC_Ring_Customer_Seg_103(String cibil_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_103");
		
		String userRefNo = gbReject(cibil_score);
		waitTime(3000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("GB Reject FRESH-V17")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_103","TC_Ring_Customer_Seg_103-To verify user's output value is in Reject band for Fresh v17");
		}
	}
	
	public void TC_Ring_Customer_Seg_105(String appScore,String cibil_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_105");
		
		regularOfferReusable(cibil_score,appScore,false);
		waitTime(3000);
		explicitWaitVisibility(UserRegistrationPage.objSomethingWrong,15);
		if(verifyElementPresent(UserRegistrationPage.objSomethingWrong,"Something went wrong message")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_105","TC_Ring_Customer_Seg_105-To verify user's output value is 0.0000000 in Accept band");
		}
	}
	
	public void TC_Ring_Customer_Seg_106(String appScore,String cibil_score) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_106");
		
		String userRefNo = regularOfferReusable(cibil_score,appScore,false);
		waitTime(3000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("GB Reject FRESH-V17")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_106","TC_Ring_Customer_Seg_106-To verify user's output value is 1.0000000 in Accept band");
		}
	}
	
	
	public void TC_Ring_Customer_Seg_107(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_107");
		
		String userRefNo = RingPolicy_Digi(appScore, false,null, true);
		
		String json_data = executeQuery1("SELECT isnull(rejection_reason) FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("1")) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_107","TC_Ring_Customer_Seg_107-To verify Digi surrogate users output value is in Reject band");
		}
	}
	
	
	//================================================txn fee & cash fee test cases =======================================
	
	public void TC_Ring_Customer_Seg_40(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_40");
		
		String userRefNo = txnFeeCashFee(appScore);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String txn_fee_user = obj.getString("txn_fees_percentage_with_gst");
		System.out.println(txn_fee_user);
		
		String txn_fee_bnpl_table = executeQuery1("SELECT txn_fees_percentage_with_gst FROM db_tradofina.bnpl_line_offers where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';");
		System.out.println(txn_fee_bnpl_table);
		if(txn_fee_user.equals(txn_fee_bnpl_table)) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_40","TC_Ring_Customer_Seg_40-To verify if user receives transaction fee according to the offer assigned");
		}
	}
	
	public void TC_Ring_Customer_Seg_41(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_41");
		
		String userRefNo = txnFeeCashFee(appScore);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		String cash_fee_user = obj.getString("cash_fee_percentage_with_gst");
		System.out.println(cash_fee_user);
		
		String cash_fee_bnpl_table = executeQuery1("SELECT cash_fee_percentage_with_gst FROM db_tradofina.bnpl_line_offers where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';");
		System.out.println(cash_fee_bnpl_table);
		if(cash_fee_user.equals(cash_fee_bnpl_table)) {
			extent.extentLoggerPass("TC_Ring_Customer_Seg_41","TC_Ring_Customer_Seg_41-To verify if user receives Cash fee according to the offer assigned");
		}
	}
	
	public void TC_Ring_Customer_Seg_42(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_42");
		
		String userRefNo = txnFeeCashFee(appScore);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		int cash_fee_user = Integer.parseInt(obj.getString("cash_fee_percentage_with_gst"));
		int txn_fee_user = Integer.parseInt(obj.getString("txn_fees_percentage_with_gst"));
		int offer_amt = Integer.parseInt(obj.getString("allowed_loan_amount"));
		
		/*String cash_fee_bnpl_table = executeQuery1("SELECT cash_fee_percentage_with_gst FROM db_tradofina.bnpl_line_offers where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';");
		System.out.println(cash_fee_bnpl_table);*/
		
		double tenPercentOffer = 0.1*offer_amt;
		if((((cash_fee_user+txn_fee_user)*offer_amt))/100<tenPercentOffer) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_42","TC_Ring_Customer_Seg_42-To verify if user receives transaction fee and Cash fee total less than 10 percent");
		}
	}
	
	public void TC_Ring_Customer_Seg_43(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_43");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set txn_fees_percentage_with_gst='5.00',cash_fee_percentage_with_gst='5.00' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
		
		String userRefNo = txnFeeCashFee(appScore);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		double cash_fee_user = Double.parseDouble(obj.getString("cash_fee_percentage_with_gst"));
		double txn_fee_user = Double.parseDouble(obj.getString("txn_fees_percentage_with_gst"));
		double offer_amt = Double.parseDouble(obj.getString("allowed_loan_amount"));
		
		double tenPercentOffer = 0.1*offer_amt;
		if((((cash_fee_user+txn_fee_user)*offer_amt))/100==tenPercentOffer) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_43","TC_Ring_Customer_Seg_43-To verify if user receives transaction fee and Cash fee total equal to 10 percent");
		}
		
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set txn_fees_percentage_with_gst='2.27',cash_fee_percentage_with_gst='3.49' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
	}
	
	public void TC_Ring_Customer_Seg_44(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_44");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set txn_fees_percentage_with_gst='7.00',cash_fee_percentage_with_gst='7.00' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
		
		String userRefNo = txnFeeCashFee(appScore);
		addAddress("as","assa","afaa","asdafd","560102");
		waitTime(10000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		double cash_fee_user = Double.parseDouble(obj.getString("cash_fee_percentage_with_gst"));
		double txn_fee_user = Double.parseDouble(obj.getString("txn_fees_percentage_with_gst"));
		double offer_amt = Double.parseDouble(obj.getString("allowed_loan_amount"));
		
		double tenPercentOffer = 0.1*offer_amt;
		if((((cash_fee_user+txn_fee_user)*offer_amt))/100<=tenPercentOffer) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_43","TC_Ring_Customer_Seg_43-To verify if user receives transaction fee and Cash fee total equal to 10 percent");
		}
		
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set txn_fees_percentage_with_gst='2.27',cash_fee_percentage_with_gst='3.49' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
		getDriver().resetApp();
	}
	
	//================================================Max limit amt fresh test cases =======================================
	
	public void TC_Ring_Customer_Seg_45(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_45");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount='26000.00' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
		
		String userRefNo = txnFeeCashFee(appScore);
		addAddress("as","assa","afaa","asdafd","560102");
		waitTime(10000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		double limit_amt = Double.parseDouble(obj.getString("allowed_loan_amount"));
		
		if(limit_amt<=26000.00) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_45","TC_Ring_Customer_Seg_45-To verify if user receives offer (limit amount) max upto 26000 when limit amount set as 26000");
		}
		
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount='8100.00' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
	}

	
	public void TC_Ring_Customer_Seg_46(String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_46");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount='29000.00' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
		
		String userRefNo = txnFeeCashFee(appScore);
		addAddress("as","assa","afaa","asdafd","560102");
		waitTime(10000);
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		JSONObject obj = new JSONObject(json_data);
		double limit_amt = Double.parseDouble(obj.getString("allowed_loan_amount"));
		
		if(limit_amt<=26000.00) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_46","TC_Ring_Customer_Seg_46-To verify if user receives offer (limit amount) max upto 26000 when limit amount set as greater than 26000");
		}
		
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount='8100.00' where surrogate_type='POOL_CIBIL_1' and merchant_type='type2';","db_tradofina.bnpl_line_offers");
	}
	
	//================================================KYC Re verfication test cases =======================================
	public void TC_Ring_Customer_Seg_167(String cibil_score, String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_167");
		
		String userRefNo = regularOfferReusable(cibil_score,appScore,false);
		addAddress("as","assa","afaa","asdafd","560102");
		explicitWaitVisibility(PromoCodeOfferPage.objCheckBox,20);
		click(PromoCodeOfferPage.objCheckBox,"Terms & Conditions check box");
		click(PromoCodeOfferPage.objAcceptOffer,"Accept offer button");
		waitTime(4000);
		
		String kyc_status = executeQuery1("SELECT kyc_re_verification_band FROM db_tradofina.users where user_reference_number='"+userRefNo+"';");
		if(kyc_status.equals("HIGH")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_167","TC_Ring_Customer_Seg_167-To verify kyc reverification band status for GB band =>18 & GB segment DIGI & LTBC1");
		}
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_168(String cibil_score, String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_168");
		
		String userRefNo = regularOfferReusable(cibil_score,appScore,false);
		addAddress("as","assa","afaa","asdafd","560102");
		explicitWaitVisibility(PromoCodeOfferPage.objCheckBox,20);
		click(PromoCodeOfferPage.objCheckBox,"Terms & Conditions check box");
		click(PromoCodeOfferPage.objAcceptOffer,"Accept offer button");
		
		waitTime(4000);
		
		String kyc_status = executeQuery1("SELECT kyc_re_verification_band FROM db_tradofina.users where user_reference_number='"+userRefNo+"';");
		if(kyc_status.equals("MEDIUM")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_168","TC_Ring_Customer_Seg_168-To verify kyc reverification band status for GB band =>18 & GB segment BC1, BC2, L1, L2, L3, NTC & THIN");
		}
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_169(String cibil_score, String appScore) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_169");
		
		String userRefNo = regularOfferReusable(cibil_score,appScore,false);
		addAddress("as","assa","afaa","asdafd","560102");
		explicitWaitVisibility(PromoCodeOfferPage.objCheckBox,20);
		click(PromoCodeOfferPage.objCheckBox,"Terms & Conditions check box");
		click(PromoCodeOfferPage.objAcceptOffer,"Accept offer button");
		
		waitTime(4000);
		
		String kyc_status = executeQuery1("SELECT kyc_re_verification_band FROM db_tradofina.users where user_reference_number='"+userRefNo+"';");
		if(kyc_status.equals("LOW")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_169","TC_Ring_Customer_Seg_169-To verify kyc reverification band status for GB band <18 & all GB segment");
		}
		getDriver().resetApp();
	}
	
	//================================================bnpl txn level checks test cases =======================================

	public void TC_Ring_Customer_Seg_120(String same_amt,String greater_amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_120");
		
		String txnNumber = scanPay(same_amt,greater_amt);
		String reason = executeQuery1("SELECT rejection_reason FROM db_tradofina.bnpl_transactions where bnpl_txn_reference_number='"+txnNumber+"';");
		
		if(reason.equals("Dedupe FA transaction")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_120","TC_Ring_Customer_Seg_120-To verify user doing transactions of same amount to same merchant within 5 minutes through Scan n Pay");
		}
	}
	
	public void TC_Ring_Customer_Seg_121(String same_amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_121");
		
		String txnNumber = bankPay(same_amt);
		String reason = executeQuery1("SELECT rejection_reason FROM db_tradofina.bnpl_transactions where bnpl_txn_reference_number='"+txnNumber+"';");
		
		if(reason.equals("Dedupe FA transaction")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_121","TC_Ring_Customer_Seg_121-To verify user doing transactions of same amount to same merchant within 5 minutes through Bank Transfer");
		}
	}
	
	public void TC_Ring_Customer_Seg_125() throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_125");
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		mobileNoValidation2("7166177166");
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		explicitWaitVisibility(HomePage.objCloseButton, 10);
		Aclick(HomePage.objCloseButton, "Close Button");
		
		click(HomePage.objScanQrBtn,"Scan QR button");
		explicitWaitVisibility(HomePage.objPaymentField,10);
		type(HomePage.objPaymentField,"1","Pay amt text field");
		click(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		click(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(HomePage.okGotIt,10);
		click(HomePage.okGotIt,"Ok got it button");
		
		explicitWaitVisibility(HomePage.objFirstTransaction,10);
		click(HomePage.objFirstTransaction,"Most recent transaction");
		
		explicitWaitVisibility(HomePage.transacNumber,10);
		String txnNumber = getText(HomePage.transacNumber);
		
		String reason = executeQuery1("SELECT rejection_reason FROM db_tradofina.bnpl_transactions where bnpl_txn_reference_number='"+txnNumber+"';");
		
		if(reason.equals("Area Blocked for Transaction")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_125","TC_Ring_Customer_Seg_125-To verify user doing Scan n Pay transaction from the location which is disabled for doing transaction ");
		}
		
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_126() throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_126");
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		mobileNoValidation2("7166177166");
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		explicitWaitVisibility(HomePage.objCloseButton, 10);
		Aclick(HomePage.objCloseButton, "Close Button");
		
		click(HomePage.moreTab,"More button");
		explicitWaitVisibility(BankTransferModule.objBankTransfer,10);
		click(BankTransferModule.objBankTransfer,"Bank transfer option");
		explicitWaitVisibility(BankTransferModule.objexistingBankAcc,10);
		click(BankTransferModule.objexistingBankAcc,"Existing bank account");
		waitTime(7000);
		click(BankTransferModule.objEnterAmountTextField,"Amt text field");
		type(BankTransferModule.objEnterAmountTextField,"2","Amt text field");
		click(BankTransferModule.objPayNowBtn,"Pay now button");
		waitTime(5000);
		click(BankTransferModule.objEnterPinTextField,"PIN Text field");
		type(BankTransferModule.objEnterPinTextField,"1111","PIN Text field");
		click(BankTransferModule.objNextNavigation,"Next navigation button");
		//explicitWaitVisibility(BankTransferModule.objStatusPage,30);
		//click(BankTransferModule.objHomePageBtn,"Go to home page button");
		
		explicitWaitVisibility(HomePage.okGotIt,10);
		click(HomePage.okGotIt,"Ok got it button");
		
		explicitWaitVisibility(HomePage.objFirstTransaction,10);
		click(HomePage.objFirstTransaction,"Most recent transaction");
		
		explicitWaitVisibility(HomePage.transacNumber,10);
		String txnNumber = getText(HomePage.transacNumber);
		
		String reason = executeQuery1("SELECT rejection_reason FROM db_tradofina.bnpl_transactions where bnpl_txn_reference_number='"+txnNumber+"';");
		
		if(reason.equals("Area Blocked for Transaction")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_126","TC_Ring_Customer_Seg_126-To verify user doing Bank Transfer transaction from the location which is disabled for doing transaction ");
		}
		
		getDriver().resetApp();
	}
	
	//================================================Terminate user on full repayment test cases =======================================
	
	public void TC_Ring_Customer_Seg_151() throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_151");
		executeUpdateOnly("update db_tradofina.configuration set meta_value='LTBC1', status='ACTIVE' where meta_key='DELINQUENT_EVERT_SEGMENTS';","db_tradofina.bnpl_line_offers");
		String userRefNo = regularOfferReusable(prop.getproperty("ltbc1"),prop.getproperty("pool_2_equals"),false);
		//addAddress("as","assa","afaa","asdafd","560102");
		explicitWaitVisibility(PromoCodeOfferPage.objCheckBox,20);
		click(PromoCodeOfferPage.objCheckBox,"Terms & Conditions check box");
		click(PromoCodeOfferPage.objAcceptOffer,"Accept offer button");
		
		waitTime(4000);
		String reason = executeQuery1("SELECT status FROM db_tradofina.lines where user_reference_number='"+userRefNo+"';");
		
		if(reason.equals("TERMINATED")) {
			System.out.println("yep");
			
			extent.extentLoggerPass("TC_Ring_Customer_Seg_151","TC_Ring_Customer_Seg_151-To Verify that check should get performed on segments defined in configuration (DELINQUENT_EVERT_SEGMENTS)");
		}
		
		executeUpdateOnly("update db_tradofina.configuration set meta_value='LTBC1', status='INACTIVE' where meta_key='DELINQUENT_EVERT_SEGMENTS';","db_tradofina.bnpl_line_offers");
	}
	
	//================================================Cabal check at onboarding test cases =======================================
	
	public void TC_Ring_Customer_Seg_141() throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_141");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		
		String userRefNo = executeQuery1("SELECT user_reference_number FROM db_tradofina.users where mobile_number='"+mobNo+"';");
		waitTime(15000);
		userDetails();
		customDataPoints_policy(prop.getproperty("onboarding_cabal_count_greater"),prop.getproperty("transaction_cabal_count"),userRefNo);
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("Cabal Linked User")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_141","TC_Ring_Customer_Seg_141-To verify onboarding new user whose cabal exceeds the cut off limit during Init to CA");
		}
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_142() throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_142");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		
		String userRefNo = executeQuery1("SELECT user_reference_number FROM db_tradofina.users where mobile_number='"+mobNo+"';");
		waitTime(15000);
		userDetails();
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		addAddress("as","assa","afaa","asdafd","560102");
		explicitWaitVisibility(PromoCodeOfferPage.objCheckBox,20);
		customDataPoints_policy(prop.getproperty("onboarding_cabal_count_greater"),prop.getproperty("transaction_cabal_count"),userRefNo);
		click(PromoCodeOfferPage.objCheckBox,"Terms & Conditions check box");
		click(PromoCodeOfferPage.objAcceptOffer,"Accept offer button");
		waitTime(4000);
		String json_data = executeQuery1("SELECT rejection_reason FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("Cabal Linked User")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_142","TC_Ring_Customer_Seg_142-To verify user whose cabal exceeds the cut off limit during CA to FA");
		}
		getDriver().resetApp();
	}

	public void TC_Ring_Customer_Seg_143() throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_143");
		
		enablePermissions();
		Aclick(RingLoginPage.objLoginLink,"Sign up login link");
		loginMobile();
		String mobNo = "7" + RandomIntegerGenerator(9);
		mobileNoValidation2(mobNo);
		System.out.println(mobNo);
		enterOtp("888888");
		readAndAccept();
		waitTime(3000);
		
		String userRefNo = executeQuery1("SELECT user_reference_number FROM db_tradofina.users where mobile_number='"+mobNo+"';");
		waitTime(15000);
		userDetails();
		customDataPoints_policy(prop.getproperty("onboarding_cabal_count_lesser"),prop.getproperty("transaction_cabal_count"),userRefNo);
		dateOfBirth("MAY","09","1994");
		Aclick(UserRegistrationNew.objProceed, "Proceed Button");
		waitTime(20000);
		String json_data = executeQuery1("SELECT isnull(rejection_reason) FROM db_tradofina.line_application where user_reference_number='"+userRefNo+"';");
		if(json_data.equals("1")) {
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_143","TC_Ring_Customer_Seg_143-To verify onboarding new user whose cabal is less or equal to the cut off limit");
		}
		getDriver().resetApp();
	}
	
	//================================================To verify if user is set as repeat test cases =======================================
	
	public void TC_Ring_Customer_Seg_47(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_47");
		repeatGrid(amt);
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepup_greater_amount_next"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		explicitWaitVisibility(HomePage.objFirstTransaction,10);
		click(HomePage.objFirstTransaction,"Most recent transaction");
		
		explicitWaitVisibility(HomePage.transacNumber,10);
		String txnNumber = getText(HomePage.transacNumber);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.bnpl_transactions where bnpl_txn_reference_number='"+txnNumber+"';");
		JSONObject obj = new JSONObject(json_data);
		Boolean repeatFlag = obj.getBoolean("is_repeat");
		
		if(repeatFlag==true) {
			System.out.println("yes");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_47","TC_Ring_Customer_Seg_47-To verify if user sets as repeat if user has utilized minimum amount of rs.50 from previous limit and does repayment");
		}
		getDriver().resetApp();
		
	}

	public void TC_Ring_Customer_Seg_48(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_48");
		repeatGrid(amt);
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepup_lesser_amount_next"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		explicitWaitVisibility(HomePage.objFirstTransaction,10);
		click(HomePage.objFirstTransaction,"Most recent transaction");
		
		explicitWaitVisibility(HomePage.transacNumber,10);
		String txnNumber = getText(HomePage.transacNumber);
		
		String json_data = executeQuery1("SELECT digi_data FROM db_tradofina.bnpl_transactions where bnpl_txn_reference_number='"+txnNumber+"';");
		JSONObject obj = new JSONObject(json_data);
		Boolean repeatFlag = obj.getBoolean("is_repeat");
		
		if(repeatFlag==false) {
			System.out.println("yes");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_48","TC_Ring_Customer_Seg_48-To verify if user does not get set as repeat if user has not utilized minimum amount of rs.50");
		}
		getDriver().resetApp();
	}
	
	//================================================To verify if user gets step up when set as repeat test cases =======================================
	public void TC_Ring_Customer_Seg_49(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_49");
		String billRefNo = repeatGrid(amt);
		
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepup_greater_amount_next"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		String stepupPerc = executeQuery1("SELECT dpd_prepay FROM db_tradofina.bnpl_repeat_limit_amount_grid where repeat_type='EARLY' and max_line_amount='2000';");
		String new_limit = executeQuery1("SELECT new_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		String old_limit = executeQuery1("SELECT old_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		
		if(((Double.parseDouble(old_limit)*(100.00 + Double.parseDouble(stepupPerc))/100.00) == Double.parseDouble(new_limit))){
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_49","TC_Ring_Customer_Seg_49-To verify if user gets step up according to percentage (10) mentioned according to previous loan repayment behaviour");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_50","TC_Ring_Customer_Seg_50-To verify if user gets step up only if user has utilized \"x\" percentage of amount from previous limit which is mentioned in \"STEP UP CREDIT USAGE PERCENT\" configuration");
		}	
		getDriver().resetApp();
	}
	
	//================================================To verify if user gets step down when set as repeat test cases =======================================
	public void TC_Ring_Customer_Seg_52(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_52");
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='-20' where max_line_amount='2000' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		String billRefNo = repeatGrid(amt);
		
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepup_greater_amount_next"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		String stepdownPerc = executeQuery1("SELECT dpd_prepay FROM db_tradofina.bnpl_repeat_limit_amount_grid where repeat_type='EARLY' and max_line_amount='2000';");
		String new_limit = executeQuery1("SELECT new_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		String old_limit = executeQuery1("SELECT old_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		
		if(((Double.parseDouble(old_limit)*(100.00 + Double.parseDouble(stepdownPerc))/100.00) == Double.parseDouble(new_limit))){
			System.out.println("yep");
			extent.extentLoggerPass("TC_Ring_Customer_Seg_52","TC_Ring_Customer_Seg_52-To verify if user gets step down according to percentage (-10) mentioned according to previous loan repayment behaviour");
			
		}	
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='45' where max_line_amount='2000' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		getDriver().resetApp();
	}
	
	//================================================To verify max and min value for repeat test cases =======================================
	
	public void TC_Ring_Customer_Seg_53(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_53");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '10000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='2000.00';","db_tradofina.bnpl_line_offers");
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='260.00' where max_line_amount='10000.00' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		String billRefNo = repeatGrid(amt);
		
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepup_greater_amount_next"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		String stepdownPerc = executeQuery1("SELECT dpd_prepay FROM db_tradofina.bnpl_repeat_limit_amount_grid where repeat_type='EARLY' and max_line_amount='2000';");
		String new_limit = executeQuery1("SELECT new_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		String old_limit = executeQuery1("SELECT old_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		
		if(new_limit.equals("26000.00")){
			System.out.println(new_limit);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_53","TC_Ring_Customer_Seg_53-To verify if user gets repeat limit maximum amount upto 26000 by setting value = 26000");
			
		}	
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='20.00' where max_line_amount='2000' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '2000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='10000.00';","db_tradofina.bnpl_line_offers");	
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_54(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_54");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '10000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='2000.00';","db_tradofina.bnpl_line_offers");
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='280.00' where max_line_amount='10000.00' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		String billRefNo = repeatGrid(amt);
		
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepup_greater_amount_next"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		String stepdownPerc = executeQuery1("SELECT dpd_prepay FROM db_tradofina.bnpl_repeat_limit_amount_grid where repeat_type='EARLY' and max_line_amount='2000';");
		String new_limit = executeQuery1("SELECT new_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		String old_limit = executeQuery1("SELECT old_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		
		if(new_limit.equals("26000.00")){
			System.out.println(new_limit);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_54","TC_Ring_Customer_Seg_54-To verify if user gets repeat limit maximum amount upto 26000 by setting value > 26000");
			
		}	
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='20.00' where max_line_amount='2000' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '2000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='10000.00';","db_tradofina.bnpl_line_offers");	
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_55(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_55");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '10000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='2000.00';","db_tradofina.bnpl_line_offers");
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='-99.00' where max_line_amount='10000.00' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		String billRefNo = repeatGrid(amt);
		
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepdown_amount"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		
		String new_limit = executeQuery1("SELECT new_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		
		
		if(new_limit.equals("100.00")){
			System.out.println(new_limit);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_55","TC_Ring_Customer_Seg_55-To verify if user gets repeat limit minimum amount upto 100 by setting value = 100");
			
		}	
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='20.00' where max_line_amount='2000' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '2000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='10000.00';","db_tradofina.bnpl_line_offers");	
		getDriver().resetApp();
	}
	
	public void TC_Ring_Customer_Seg_56(String amt) throws Exception{
		extent.HeaderChildNode("TC_Ring_Customer_Seg_56");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '10000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='2000.00';","db_tradofina.bnpl_line_offers");
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='-99.8' where max_line_amount='10000.00' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		String billRefNo = repeatGrid(amt);
		
		click(HomePage.objScanQrBtn,"Scan QR button");
		waitTime(10000);
		explicitWaitVisibility(HomePage.objPaymentField,30);
		type(HomePage.objPaymentField,prop.getproperty("stepdown_amount"),"Pay amt text field");
		waitTime(3000);
		Aclick(HomePage.objPayNowBtn,"Pay now button");
		waitTime(15000);
		explicitWaitVisibility(PaymentPage.objEditTransactionPin,30);
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		Aclick(PaymentPage.objEditTransactionPin,"MPIN Field");
		type(PaymentPage.objEditTransactionPin,"1111","MPIN Field");
		click(PaymentPage.objContinue,"Continue button");
		explicitWaitVisibility(PaymentPage.objGoToHomePage,30);
		click(PaymentPage.objGoToHomePage,"Go to homepage button");
		explicitWaitVisibility(HomePage.objAvailableLimit,20);
		
		
		String new_limit = executeQuery1("SELECT new_limit FROM db_tradofina.line_step_up_data where repeat_source_reference_number='"+billRefNo+"';");
		
		if(new_limit.equals("100.00")){
			System.out.println(new_limit);
			extent.extentLoggerPass("TC_Ring_Customer_Seg_56","TC_Ring_Customer_Seg_56-To verify if user gets repeat limit minimum amount upto 100 by setting value < 100");
			
		}	
		executeUpdateOnly("update db_tradofina.bnpl_repeat_limit_amount_grid set dpd_prepay='20.00' where max_line_amount='2000' and repeat_type='EARLY';","db_tradofina.bnpl_repeat_limit_amount_grid");
		executeUpdateOnly("update db_tradofina.bnpl_line_offers set limit_amount = '2000.00' where merchant_type='type2' and segment = 'LTBC1' and surrogate_type='EQUIFAX' and limit_amount='10000.00';","db_tradofina.bnpl_line_offers");	
		getDriver().resetApp();
	}
	
}