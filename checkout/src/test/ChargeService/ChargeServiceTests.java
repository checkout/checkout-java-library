package test.ChargeService;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import checkout.Checkout;
import checkout.exception.CKOException;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;

public class ChargeServiceTests {
	
	@BeforeClass
	public static void setUp() throws Exception {		
		
	}
	
	@Test
	public void testVerifyChargeByPaymentTokenRequest() throws CKOException {		
		Checkout ckoClient= new Checkout("sk_CC937715-4F68-4306-BCBE-640B249A4D50");

		String paymentTken ="charge_C0D98A4D175U76BD3DE6";
		
		Response<Charge> chargeResponse = ckoClient.chargeService.verifyCharge(paymentTken);
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);			
		
		assertNotNull(chargeResponse.model.id);
	}
}
