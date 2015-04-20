package test.TokenService;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.apiServices.sharedModels.Response;
import com.checkout.apiServices.tokens.request.PaymentTokenCreate;
import com.checkout.apiServices.tokens.response.PaymentToken;
import com.checkout.exception.CKOException;

import test.TestHelper;

public class TokenServiceTests {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void CreatePaymentTokenTest() throws CKOException {
		APIClient ckoClient= new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50");
		
		PaymentTokenCreate payload= TestHelper.getPaymentTokenCreateModel();
		
		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200, tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		
	}

}
