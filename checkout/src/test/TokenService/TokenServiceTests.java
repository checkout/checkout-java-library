package test.TokenService;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import checkout.Checkout;
import checkout.exception.CKOException;
import apiServices.sharedModels.Response;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.PaymentToken;
import test.TestHelper;

public class TokenServiceTests {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void CreatePaymentTokenTest() throws CKOException {
		Checkout ckoClient= new Checkout("sk_CC937715-4F68-4306-BCBE-640B249A4D50");
		
		PaymentTokenCreate payload= TestHelper.getPaymentTokenCreateModel();
		
		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200, tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		
	}

}
