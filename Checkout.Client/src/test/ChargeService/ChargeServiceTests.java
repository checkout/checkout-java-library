package test.ChargeService;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import apiServices.charges.ChargeService;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class ChargeServiceTests {
	
	@BeforeClass
	public static void setUp() throws Exception {		
		
	}
	
	@Test
	public void testVerifyChargeByPaymentTokenRequest() throws CKOException {		
		ChargeService chargeService = new ChargeService();		
		
		String paymentTken ="charge_C0D98A4D175U76BD3DE6";
		
		Response<Charge> chargeResponse = chargeService.verifyCharge(paymentTken);
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);			
		
		assertNotNull(chargeResponse.model.id);
	}
}
