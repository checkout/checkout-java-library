package test.tokenservice;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.api.services.shared.Response;
import com.checkout.api.services.token.request.PaymentTokenCreate;
import com.checkout.api.services.token.response.PaymentToken;

import test.TestHelper;

public class TokenServiceTests {
	
	APIClient ckoClient;
	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient(TestHelper.secretKey,true);
		
	}
	
	@Test
	public void CreatePaymentTokenTest() throws IOException   {
		PaymentTokenCreate payload= TestHelper.getPaymentTokenCreateModel();
		
		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200, tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		
	}

}
