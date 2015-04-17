package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.sharedModels.Response;
import apiServices.tokens.TokenService;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.PaymentToken;
import exception.CKOException;

public class ValidationError_TokensAPITest {

	@Before
	public void setUp() throws Exception {
	}	

	@Test
	public void testCreatePaymentTokenRequest_InvalidCurrency() throws CKOException {
		
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		tokenPayload.value=6;
		tokenPayload.currency="kde";

		Response<PaymentToken> tokenResponse = new TokenService().createPaymentToken(tokenPayload);
		assertEquals(true, tokenResponse.hasError);
		assertEquals(true,tokenResponse.error.message.contains("currency"));
		assertNotNull(tokenResponse.error.eventId);
	}
	
	@Test
	public void testCreatePaymentTokenRequest_ValidationError() throws CKOException {
		
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		tokenPayload.currency="usd";
		
		Response<PaymentToken> tokenResponse = new TokenService().createPaymentToken(tokenPayload);
		assertEquals(true, tokenResponse.hasError);
		assertEquals(true, tokenResponse.error.errorCode.equals("70000"));
		assertEquals(true,tokenResponse.error.message.contains("Validation"));
		assertNotNull(tokenResponse.error.errors);
	}
	

}
