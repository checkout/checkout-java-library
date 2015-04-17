package test.ErrorResponse;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import test.ChargeHelper;
import apiServices.charges.request.ChargeCapture;
import apiServices.charges.request.ChargeRefund;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;


public class InvalidChargeAmountError_ChargesAPITest {

	static String charge_id = "";
	static CheckoutClient ckoClient;
	@BeforeClass
	public static void setUp() throws Exception {
		ckoClient=new CheckoutClient("sk_test_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_test_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");;

		Response<Charge> charge = ChargeHelper.chargeWithFullCardRequest();
		charge_id=charge.getType().id;
	}

	@Test
	public void testRefundCardChargeRequest_MoreThanAmount() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse = null;
		ChargeRefund chargesPayload = new ChargeRefund();
		chargesPayload.chargeId = charge_id;
		chargesPayload.value = 200;		
		chargesResponse = ckoClient.ChargeService.refundCardChargeRequest(chargesPayload);

		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Amount Not equal to Original Amount"));

	}
	
	@Test
	public void testRefundCardChargeRequest_LessThanAmount() throws JsonSyntaxException, IOException  {
		Response<Charge> chargesResponse = null;
		ChargeRefund chargesPayload = new ChargeRefund();
		chargesPayload.chargeId = charge_id;
		chargesPayload.value = 50;

		
		chargesResponse = ckoClient.ChargeService.refundCardChargeRequest(chargesPayload);

		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Amount Not equal to Original Amount"));

	}

	@Test
	public void testCaptureCardChargeRequest_LessThanAmount() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse = null;
		ChargeCapture chargesPayload = new ChargeCapture();
		chargesPayload.chargeId = charge_id;
		chargesPayload.value = 50;
		
		chargesResponse = ckoClient.ChargeService.captureCardCharge(chargesPayload);

		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Amount Not equal to Original Amount"));

	}


}
