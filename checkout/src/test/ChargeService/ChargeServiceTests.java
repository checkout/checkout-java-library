package test.ChargeService;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.apiServices.charges.response.Charge;
import com.checkout.apiServices.sharedModels.Response;
import com.checkout.exception.CKOException;

public class ChargeServiceTests {
	
	@BeforeClass
	public static void setUp() throws Exception {		
		
	}
	
	@Test
	public void testVerifyChargeByPaymentTokenRequest() throws CKOException {		
		APIClient ckoClient= new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50");

		String paymentTken ="charge_C0D98A4D175U76BD3DE6";
		
		Response<Charge> chargeResponse = ckoClient.chargeService.verifyCharge(paymentTken);
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);			
		
		assertNotNull(chargeResponse.model.id);
	}
}
