package test.TokenService;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.sharedModels.Response;
import apiServices.tokens.TokenService;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.PaymentToken;
import test.TestHelper;
import exception.CKOException;

public class TokenServiceTests {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void CreatePaymentTokenTest() throws CKOException {
		
		TokenService tokenService = new TokenService();
		
		PaymentTokenCreate payload= TestHelper.getPaymentTokenCreateModel();
		
		Response<PaymentToken> tokenResponse = tokenService.createPaymentToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200, tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		
	}

}
