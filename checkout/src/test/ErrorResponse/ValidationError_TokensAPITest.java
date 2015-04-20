package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.apiServices.sharedModels.Response;
import com.checkout.apiServices.tokens.request.PaymentTokenCreate;
import com.checkout.apiServices.tokens.response.PaymentToken;
import com.checkout.exception.CKOException;

public class ValidationError_TokensAPITest {

	@Before
	public void setUp() throws Exception {
	}	

	@Test
	public void testCreatePaymentTokenRequest_InvalidCurrency() throws CKOException {
		APIClient ckoClient= new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50");
		
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		tokenPayload.value=6;
		tokenPayload.currency="kde";

		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(tokenPayload);
		assertEquals(true, tokenResponse.hasError);
		assertEquals(true,tokenResponse.error.message.contains("currency"));
		assertNotNull(tokenResponse.error.eventId);
	}
	
	@Test
	public void testCreatePaymentTokenRequest_ValidationError() throws CKOException {
		APIClient ckoClient= new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50");
		
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		tokenPayload.currency="usd";
		
		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(tokenPayload);
		assertEquals(true, tokenResponse.hasError);
		assertEquals(true, tokenResponse.error.errorCode.equals("70000"));
		assertEquals(true,tokenResponse.error.message.contains("Validation"));
		assertNotNull(tokenResponse.error.errors);
	}
	

}
