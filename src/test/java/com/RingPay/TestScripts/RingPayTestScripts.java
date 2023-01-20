package com.RingPay.TestScripts;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utility.Utilities;
import com.android.RingPayPages.RingLoginPage;
import com.extent.ExtentReporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.Utilities;

public class RingPayTestScripts{

	private com.business.RingPay.RingPayBusinessLogic ringPayBusiness;
	public static PropertyFileReader prop=new PropertyFileReader(".\\properties\\testData.properties");
	
	@BeforeTest
	public void Before() throws InterruptedException {
		Utilities.relaunch = true;
		ringPayBusiness = new com.business.RingPay.RingPayBusinessLogic("ring");
	}
	//yepppp
	@Test(priority = 0)
	@Parameters({"SignUpBtn","permission"})
    public void  User_Playstore_Flow(String SignUpBtn, String permission) throws Exception {
		ringPayBusiness.User_Play_Store_Flow(SignUpBtn, permission);
	} 
	
	@Test(dependsOnMethods = "User_Playstore_Flow")
	@Parameters({"Month","Date","Year","Gender"})
	public void userRegistrationFlow(String month, String date, String year,String gender) throws Exception {
		ringPayBusiness.User_Registration_Flow(month,date,year,gender);
	}
	
	@Test(priority = 2)
	public void promocodeFlow() throws Exception {
		ringPayBusiness.promoCodeFlowModule();
	}
	
	@Test(priority=3)
	public void offerFlow() throws Exception {
		ringPayBusiness.offerScreenModule();
	}
	
	@Test(priority=4)
	public void ageCheck() throws Exception {
		ringPayBusiness.ageCriteriaFailedCheck();
	}
	
	/*@Test(priority = 5)
    @Parameters({"baseURLMockUser","gender","encrypted_name","portalEmail","portalPassword","portalOTP"})
    public void WebRingApp(String url,String gender,String encrypted_name,String portalEmail,String portalPassword,String portalOTP) throws Exception {
		ringPayBusiness.kycSkipped(url,gender,encrypted_name,portalEmail,portalPassword,portalOTP);
    }
	
	 @Test(priority = 6)
	 @Parameters({"baseURLMockUser","gender","encrypted_name","portalEmail","portalPassword","portalOTP"})
	 public void WebRingApp1(String url,String gender,String encrypted_name,String portalEmail,String portalPassword,String portalOTP) throws Exception {
		 ringPayBusiness.bannerLogicOnHomePage(url,gender,encrypted_name,portalEmail,portalPassword,portalOTP);
	 }*/
	
	 @Test(priority = 8)
	 public void transactionSetPin() throws Exception {
		 ringPayBusiness.transactionSetPin();
	 }
	 
	 @Test(priority = 9)
	 public void repaymentmultipleCase() throws Exception {
		 ringPayBusiness.repaymentMultipleCases();
	 }
	 
	 @Test(priority = 10)
	 public void bankTransferFlow() throws Exception{
		 ringPayBusiness.BankTransferModule("5", "Akash");
	 }
	 
	@Test(priority = 7)
	public void addAddresFlow() throws Exception {
		ringPayBusiness.addAddressFlow();
	}
	
	@Test(priority = 11)
	public void userScanAndPayFlow() throws Exception {
		ringPayBusiness.userScanAndPayTransactions();
	}
	
	@Test(priority = 12)
	public void merchantFlow() throws Exception {
		ringPayBusiness.merchantFlow();
	}
	
	
	@Test(priority = 13)
	public void ringPayLogin() throws Exception {
		ringPayBusiness.mockUserAPI();
	}//hello
	
	@Test(priority = 14)
	public void ringInstaWhitelist() throws Exception{
		ringPayBusiness.instaLoanWhitelistLogic();
	}
	
	
	//================================================digi surrogate related POOL Offer test cases =======================================
	@Test(priority=15)
	public void ringPolicy_TC_1() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_01(prop.getproperty("pool_1_v16"));
	}
	
	@Test(priority=16)
	public void ringPolicy_TC_2() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_02(prop.getproperty("pool_2_v16"));
	}
	
	@Test(priority=17)
	public void ringPolicy_TC_5() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_05(prop.getproperty("pool_2_v16"));
	}
	
	@Test(priority=18)
	public void ringPolicy_TC_6() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_06(prop.getproperty("pool_2_equals"));
	}
	
	@Test(priority=19)
	public void ringPolicy_TC_7() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_07(prop.getproperty("pool_2_v16"));
	}
	
	@Test(priority=20)
	public void ringPolicy_TC_8() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_08(prop.getproperty("pool_3_v16"));
	}
	
	
	//================================================cibil surrogate related POOL Offer test cases =======================================
	@Test(priority = 21)
	public void ringPolicy_TC_3() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_03(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority = 22)
	public void ringPolicy_TC_4() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_04(prop.getproperty("pool_2_v17"));
	}
	
	@Test(priority = 23)
	public void ringPolicy_TC_9() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_09(prop.getproperty("pool_2_v17"));
	}
	
	@Test(priority = 24)
	public void ringPolicy_TC_10() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_10(prop.getproperty("pool_2_v17_equals"));
	}
	
	@Test(priority = 25)
	public void ringPolicy_TC_11() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_11(prop.getproperty("pool_2_v17_equals"));
	}
	
	@Test(priority = 26)
	public void ringPolicy_TC_12() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_12(prop.getproperty("pool_2_v17_greater"));
	}
	
	@Test(priority = 27)
	public void ringPolicy_TC_28() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_28(prop.getproperty("ltbc1"),prop.getproperty("pool_2_v17_greater"));
	}
	
	@Test(priority = 28)
	public void ringPolicy_TC_29() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_29(prop.getproperty("bc1"),prop.getproperty("pool_2_v17_greater"));
	}
	
	@Test(priority = 29)
	public void ringPolicy_TC_30() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_30(prop.getproperty("bc2"),prop.getproperty("pool_2_v17_greater"));
	}
	
	@Test(priority = 30)
	public void ringPolicy_TC_31() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_31(prop.getproperty("l1"),prop.getproperty("pool_2_v17_greater"));
	}
	
	@Test(priority = 31)
	public void ringPolicy_TC_32() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_32(prop.getproperty("l2"),prop.getproperty("pool_2_v17_greater"));
	}
	
	@Test(priority = 32)
	public void ringPolicy_TC_33() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_33(prop.getproperty("l3"),prop.getproperty("pool_2_v17_greater"));
	}
	
	//================================================age preference related POOL Offer test cases =======================================
	@Test(priority = 33)
	public void ringPolicy_TC_158_digi() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_158_digi(prop.getproperty("age_digi"));
	}
	
	@Test(priority = 34)
	public void ringPolicy_TC_158_L1() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_158_L1(prop.getproperty("age_digi"),prop.getproperty("l1"));
	}
	
	@Test(priority = 35)
	public void ringPolicy_TC_159_BC1() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_158_L1(prop.getproperty("age_digi"),prop.getproperty("bc1"));
	}
	
	@Test(priority=36)
	public void ringPolicy_TC_160() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_160(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=37)
	public void ringPolicy_TC_62() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_62(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=37)
	public void ringPolicy_TC_63() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_63(prop.getproperty("pool_1_v17"));
	}
	@Test(priority=37)
	public void ringPolicy_TC_64() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_64(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=38)
	public void ringPolicy_TC_65() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_65(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=39)
	@Parameters({"baseURLMockUser","gender","encrypted_name"})
	public void ringPolicy_TC_70(String url, String gender, String encryptedName) throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_70(url, gender, encryptedName);
	}
	
	@Test(priority=40)
	@Parameters({"baseURLMockUser","gender","encrypted_name"})
	public void ringPolicy_TC_74(String url, String gender, String encryptedName) throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_74(url, gender, encryptedName);
	}
	
	@Test(priority=41)
	public void ringPolicy_TC_170() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_170(prop.getproperty("kla"),prop.getproperty("cc_flag_negative"),prop.getproperty("kla_flag_positive"));
	}
	
	@Test(priority=42)
	public void ringPolicy_TC_171() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_171(prop.getproperty("pool_1_v17"),prop.getproperty("cc_flag_negative"),prop.getproperty("kla_flag_positive"));
	}
	
	@Test(priority=43)
	public void ringPolicy_TC_172() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_172(prop.getproperty("kla"),prop.getproperty("cc_flag_positive"),prop.getproperty("kla_flag_negative"));
	}
	
	@Test(priority=44)
	public void ringPolicy_TC_173() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_173(prop.getproperty("pool_1_v17"),prop.getproperty("cc_flag_positive"),prop.getproperty("kla_flag_negative"));
	}
	
	@Test(priority=45)
	public void ringPolicy_TC_174() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_174(prop.getproperty("kla"),prop.getproperty("cc_flag_positive"),prop.getproperty("kla_flag_positive"));
	}
	
	@Test(priority=46)
	public void ringPolicy_TC_165() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_165(prop.getproperty("band_6_7"),prop.getproperty("l1"), prop.getproperty("cc_flag_positive"),prop.getproperty("kla_flag_negative"));
	}
	
	@Test(priority=47)
	public void ringPolicy_TC_166() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_166(prop.getproperty("band_6_7"),prop.getproperty("bc1"), prop.getproperty("cc_flag_positive"),prop.getproperty("kla_flag_negative"));
	}
	
	@Test(priority=48)
	public void ringPolicy_TC_131() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_131(prop.getproperty("band_9_20"), prop.getproperty("ltbc1"), prop.getproperty("cc_flag_negative"),prop.getproperty("kla_flag_negative"));
	}
	
	@Test(priority=54)
	public void ringPolicy_TC_99() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_99(prop.getproperty("l3_band_no_offer"), prop.getproperty("no_offer"));
	}
	
	@Test(priority=56)
	public void ringPolicy_TC_103() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_103(prop.getproperty("bc1"));
	}
	
	@Test(priority=57)
	public void ringPolicy_TC_105() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_105(prop.getproperty("firstBand"), prop.getproperty("l2"));
	}
	@Test(priority=58)
	public void ringPolicy_TC_106() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_106(prop.getproperty("lastBand"), prop.getproperty("bc1"));
	}
	
	@Test(priority=59)
	public void ringPolicy_TC_107() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_107(prop.getproperty("lastBand"));
	}
	
	@Test(priority=60)
	public void ringPolicy_TC_40() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_40(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=61)
	public void ringPolicy_TC_41() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_41(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=62)
	public void ringPolicy_TC_42() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_42(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=63)
	public void ringPolicy_TC_43() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_43(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=64)
	public void ringPolicy_TC_44() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_44(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=65)
	public void ringPolicy_TC_45() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_45(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=66)
	public void ringPolicy_TC_46() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_46(prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=67)
	public void ringPolicy_TC_167() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_167(prop.getproperty("ltbc1"),prop.getproperty("band_18_greater"));
	}
	
	@Test(priority=68)
	public void ringPolicy_TC_168() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_168(prop.getproperty("l1"), prop.getproperty("band_18_greater"));
	}
	
	@Test(priority=69)
	public void ringPolicy_TC_169() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_169(prop.getproperty("l3"), prop.getproperty("pool_1_v17"));
	}
	
	@Test(priority=70)
	public void ringPolicy_TC_120() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_120(prop.getproperty("payAmount"),null);
	}
	
	@Test(priority=71)
	public void ringPolicy_TC_121() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_121(prop.getproperty("payAmount"));
	}
	
	@Test(priority=72)
	public void ringPolicy_TC_125() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_125();
	}
	
	@Test(priority=72)
	public void ringPolicy_TC_126() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_126();
	}
	
	/*@Test(priority=73)
	public void ringPolicy_TC_151() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_151();
	}*/
	
	@Test(priority=74)
	public void ringPolicy_TC_141() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_141();
	}
	
	@Test(priority=75)
	public void ringPolicy_TC_142() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_142();
	}
	
	@Test(priority=76)
	public void ringPolicy_TC_143() throws Exception{
		ringPayBusiness.TC_Ring_Customer_Seg_143();
	}
	/*@Test(priority = 73)
	public void instaOptionalJourney() throws Exception{
		ringPayBusiness.instaLoanWhitelistLogic();	
	}
	
	@Test(priority = 74)
	public void instaMandatoryJourney() throws Exception{
		ringPayBusiness.instaLoanWhitelistLogic();	
	}
	
	@Test(priority = 75)
	public void instaOfferScreen() throws Exception{
		ringPayBusiness.instaLoanWhitelistLogic();	
	}
	
	@Test(priority = 76)
	public void instaTxnHistory() throws Exception{
		ringPayBusiness.instaLoanWhitelistLogic();
	}*/
	/*@AfterTest
	public void ringAppQuit() throws Exception{
		ringPayBusiness.TearDown();
	}*/
}
