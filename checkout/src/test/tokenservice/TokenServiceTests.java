package test.tokenservice;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.api.services.token.request.PaymentTokenCreate;
import com.checkout.api.services.token.request.PaymentTokenUpdate;
import com.checkout.api.services.token.request.VisaCheckoutTokenCreate;
import com.checkout.api.services.token.response.PaymentToken;
import com.checkout.api.services.token.response.VisaCheckoutToken;

import test.TestHelper;

public class TokenServiceTests {
	
	APIClient ckoClient;
	APIClient ckoPkClient;
	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient(TestHelper.secretKey,true);
		ckoPkClient = new APIClient(TestHelper.secretKey, TestHelper.publicKey, true);
	}
	
	@Test
	public void CreatePaymentTokenTest() throws IOException   {
		PaymentTokenCreate payload= TestHelper.getPaymentTokenCreateModel();
		
		Response<PaymentToken> tokenResponse = ckoClient.tokenService.createPaymentToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200, tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		
	}
	
	@Test
	public void UpdatePaymentTokenTest() throws IOException   {
		
		Response<PaymentToken> tokenCreateResponse = ckoClient.tokenService.createPaymentToken(TestHelper.getPaymentTokenCreateModel());
		
		PaymentTokenUpdate payload= TestHelper.getPaymentTokenUpdateModel();
		
		Response<OkResponse> tokenUpdateResponse = ckoClient.tokenService.updatePaymentToken(tokenCreateResponse.model.id, payload);
		
		assertEquals(false, tokenUpdateResponse.hasError);
		assertEquals(200, tokenUpdateResponse.httpStatus);
		assertEquals(tokenUpdateResponse.model.message,"ok");
		
	}
	
	@Test
	public void CreateVisaCheckoutToken_WithoutBinData() throws IOException {
		VisaCheckoutTokenCreate payload = TestHelper.getVisaCheckoutTokenCreateModel(false);
		
		Response<VisaCheckoutToken> tokenResponse = ckoPkClient.tokenService.createVisaCheckoutToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200,  tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		assertNull(tokenResponse.model.binData);
	}

	@Test
	public void CreateVisaCheckoutToken_WithBinData() throws IOException {
		VisaCheckoutTokenCreate payload = TestHelper.getVisaCheckoutTokenCreateModel(true);
		
		Response<VisaCheckoutToken> tokenResponse = ckoPkClient.tokenService.createVisaCheckoutToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200,  tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		assertNotNull(tokenResponse.model.binData);
	}
}
