package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import test.ChargeHelper;
import apiServices.charges.ChargeService;
import apiServices.charges.request.ChargeCapture;
import apiServices.charges.request.ChargeRefund;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;
import exception.CKOException;


public class InvalidChargeAmountError_ChargesAPITest {

	static String charge_id = "";

	@BeforeClass
	public static void setUp() throws Exception {
		Response<Charge> charge = ChargeHelper.ChargeWithFullCardRequest();
		charge_id=charge.Model().id;
	}

	@Test
	public void testRefundCardChargeRequest_MoreThanAmount() throws CKOException {
		Response<Charge> chargesResponse = null;
		ChargeRefund chargesPayload = new ChargeRefund();
		chargesPayload.chargeId = charge_id;
		chargesPayload.value = 200;

		ChargeService api = new ChargeService();
		chargesResponse = api.RefundCardChargeRequest(chargesPayload);

		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Amount Not equal to Original Amount"));

	}
	
	@Test
	public void testRefundCardChargeRequest_LessThanAmount() throws CKOException {
		Response<Charge> chargesResponse = null;
		ChargeRefund chargesPayload = new ChargeRefund();
		chargesPayload.chargeId = charge_id;
		chargesPayload.value = 50;

		ChargeService api = new ChargeService();
		chargesResponse = api.RefundCardChargeRequest(chargesPayload);

		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Amount Not equal to Original Amount"));

	}

	@Test
	public void testCaptureCardChargeRequest_LessThanAmount() throws CKOException {
		Response<Charge> chargesResponse = null;
		ChargeCapture chargesPayload = new ChargeCapture();
		chargesPayload.chargeId = charge_id;
		chargesPayload.value = 50;
		ChargeService api = new ChargeService();
		chargesResponse = api.captureCardCharge(chargesPayload);

		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Amount Not equal to Original Amount"));

	}


}
