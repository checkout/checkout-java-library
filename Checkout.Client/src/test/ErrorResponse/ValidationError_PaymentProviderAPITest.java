package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import apiServices.payment_provider.PaymentProviderService;
import apiServices.payment_provider.request.CardProviderModels;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.request.LocalPaymentProviderModel;
import apiServices.payment_provider.response.CardProvider;
import apiServices.payment_provider.response.LocalPaymentProvider;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.Response;
import exception.CKOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidationError_PaymentProviderAPITest {
	String session_tok="sess_tok_4c684b5d-3cad-4695-9061-82182cf319a2";
	 static String lpp="";
	 static String cp="";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetLocalPaymentProviderListRequest_TokenExpiredError() throws CKOException {
		Response<LocalPaymentProviderList>paymentProviderResponse=null;
		LocalPaymentProviderListModel paymentProviderPayloads=new LocalPaymentProviderListModel();
		paymentProviderPayloads.sessionToken=session_tok;
		
		PaymentProviderService api=new PaymentProviderService();
		paymentProviderResponse=api.getLocaPaymentProviderList(paymentProviderPayloads);
		
		
		assertEquals(true, paymentProviderResponse.getHasError());
		assertEquals(true, paymentProviderResponse.error.message.contains("Invalid local payment token"));					
	}	
	
	@Test
	public void testGetLocalPaymentProviderRequest_ValidationError() throws CKOException {
		Response<LocalPaymentProvider> paymentProviderResponse=null;
		LocalPaymentProviderModel paymentProviderPayloads=new LocalPaymentProviderModel();
		paymentProviderPayloads.id="lpxxx";
		paymentProviderPayloads.sessionToken=session_tok;
		
		PaymentProviderService api=new PaymentProviderService();
		paymentProviderResponse=api.getLocaPaymentProvider(paymentProviderPayloads);
		
		assertEquals(true, paymentProviderResponse.getHasError());
		assertEquals(true, paymentProviderResponse.error.message.contains("Validation error"));		
		
	}

	
	@Test
	public void testGetCardProviderRequest_ValidationError() throws CKOException {
		
		Response<CardProvider> paymentProviderResponse=null;
		CardProviderModels paymentProviderPayloads=new CardProviderModels();
		
		paymentProviderPayloads.id="cp";
		PaymentProviderService api=new PaymentProviderService();
		paymentProviderResponse=api.getCardProvider(paymentProviderPayloads);
		
		assertEquals(true, paymentProviderResponse.getHasError());
		assertEquals(true, paymentProviderResponse.error.message.contains("Validation error"));
		
	}

}
