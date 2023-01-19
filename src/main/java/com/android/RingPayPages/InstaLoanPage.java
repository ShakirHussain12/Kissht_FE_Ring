package com.android.RingPayPages;

import org.openqa.selenium.By;

public class InstaLoanPage {

	//Admin panel login page
	public static By objEmail = By.xpath("//input[@name='email']");
	public static By objContinueBtn = By.xpath("//button[text()='Continue']");
	public static By objPasswordField = By.xpath("//input[@name='password']");
	public static By objOTPField = By.xpath("//input[@name='otp']");
	public static By objLoginBtn = By.xpath("//button[text()='Log In']");
	public static By objContinue = By.xpath("//*[@type='submit']");
	
	//Admin Panel Home Page User Tab
	public static By objUserTab = By.xpath("//span[text()='Users ']");
	public static By objSearchUser = By.xpath("//select[@id='search_cond']");
	public static By objSearchTerm = By.xpath("//input[@name='search_term']");
	public static By objSearchBtn = By.xpath("//button[@name='submit']");
	public static By objUserReferenceNo = By.xpath("(//th[text()='User Reference Number']/parent::tr/parent::thead/following-sibling::tbody/descendant::td)[1]");
	
	//Admin Panel Pan NSDL Tab
	public static By objScrollToPayment = By.xpath("//span[text()='Payment']");
	public static By dragfrom=By.xpath("//div[@class='mCSB_dragger_bar']");
	public static By dragDown=By.xpath("//a[@class='mCSB_buttonDown']");
	public static By objOthersTab = By.xpath("//span[text()='Others']");
	public static By objPanNSDLData = By.xpath("//span[text()='Pan Nsdl Data']");
	public static By objPanNoSerach = By.xpath("//input[@placeholder='Pan Number']");
	public static By objPanStatusSelect = By.xpath("//select[@id='pan_status']");
	public static By objAddPanCard = By.xpath("//a[@class='btn btn-success btn-xs heading-btn']");
	public static By objNameField = By.xpath("//input[@name='first_name']");
	public static By objMiddleNameField = By.xpath("//input[@name='middle_name']");
	public static By objLastNameField = By.xpath("//input[@name='last_name']");
	public static By objPanNoField = By.xpath("//input[@name='pan']");
	public static By objPanStatus = By.xpath("//select[@name='pan_status']");
	public static By objSubmitBtn = By.xpath("//input[@type='submit']");
	
	//KYC Document
	public static By objKYCHeader = By.xpath("//*[@text='KYC Documents']");
	public static By objKYCFrontAadhar = By.xpath("(//*[@text='Front']/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup)[1]");
	public static By objKYCBackAadhar = By.xpath("(//*[@text='Back']/preceding-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup)[3]");
	public static By objCaptureAadharFrontImage = By.xpath("//*[@text='Capture Aadhaar Front']");
	public static By objCaptureBtn = By.xpath("(//*[@text='Capture Aadhaar Front']/ancestor::android.view.ViewGroup/following-sibling::android.widget.ScrollView/descendant::android.view.ViewGroup)[7]");
	public static By objBackAadharCaptureBtn = By.xpath("(//*[@text='Capture Aadhaar Back']/ancestor::android.view.ViewGroup/following-sibling::android.widget.ScrollView/descendant::android.view.ViewGroup)[7]");
	public static By objKYCCaptureContinueBtn = By.xpath("//*[@text='Continue']");
	public static By objKYCCAptureSelfie = By.xpath("//*[@text='Capture Selfie']");
	public static By objSelficeCaptureBtn = By.xpath("//*[@text='Place your face within the circle']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/descendant::android.view.ViewGroup");
	
	//Pan card number
	public static By objPanNoEnter = By.xpath("//*[@text='Pan Card Number']/parent::android.view.ViewGroup/following-sibling::android.widget.EditText");
	public static By objPanNoHeader = By.xpath("//*[@text='Pan Card Number']");

	//Purple Lines option
	public static By objPurple = By.xpath("//span[text()=\"Purple\"]");
	public static By objGBGrid = By.xpath("//span[text()=\"GB Grid\"]");
	public static By objModelVersion = By.xpath("//*[@id=\"filter_model_version\"]");
	public static By objSubmitFilter = By.id("submit_filter");
	public static By objEditGrid = By.id("edit_grid");
	public static By objBC1RejectBand = By.xpath("(//*[@data-segment=\"BC1\" and @data-city=\"WL\"])[19]//div//div[@class=\"face acceptDiv show\"]");
	public static By objUpdateBtn = By.id("updateRejectTemplete");
	public static By objRejectAppScore = By.xpath("/html/body/div[3]/div/div[3]/div[1]/div[3]/div/div/div[2]/form/div[1]/div/table/tbody/tr/td[3]");
	public static By objPassword = By.id("password");
	public static By objUpdateSubmit = By.id("updateTemplete");
	
	//App score config
	public static By objEditConfBtn = By.xpath("//*[@class=\"MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-containedSizeSmall MuiButton-sizeSmall\"]");
	public static By objMetaValue = By.id("meta_value");
	public static By objSubmit = By.xpath("(//button[@type=\"submit\"])[2]");
	public static By objUserRefNo = By.xpath("(//tr[@class=\"MuiTableRow-root\"]//td)[1]");
	public static By objSearchRef = By.id("outlined-error-helper-text");
	public static By objSearch = By.xpath("(//span[text()=\"Search\"])[2]");
	
	
}
