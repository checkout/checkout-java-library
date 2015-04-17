package test.PaymentProviderService;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import test.TokenHelper;
import apiServices.payment_provider.PaymentProviderService;
import apiServices.payment_provider.request.CardProviderModels;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.request.LocalPaymentProviderModel;
import apiServices.payment_provider.response.CardProvider;
import apiServices.payment_provider.response.CardProviderList;
import apiServices.payment_provider.response.LocalPaymentProvider;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.Response;
import apiServices.tokens.response.PaymentToken;
import exception.CKOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PaymentProviderTests {
	
	 static String lpp="";
	 static String cp="";
		
	@Before
	public  void setUp() throws Exception {
		
	}
	
	@Test
	public void testGetLocalPaymentProviderListRequest() throws CKOException {
		Response<LocalPaymentProviderList>paymentProviderResponse=null;
		
		Response<PaymentToken> sessionTokenResponse=TokenHelper.CreateSessionToken();
		
		LocalPaymentProviderListModel paymentProviderPayloads=new LocalPaymentProviderListModel();
		
		paymentProviderPayloads.sessionToken=sessionTokenResponse.Model().id;
//		paymentProviderPayloads.countryCodes="CN";
//		paymentProviderPayloads.name="Alipay";
		
		
		PaymentProviderService api=new PaymentProviderService();
		paymentProviderResponse=api.getLocaPaymentProviderList(paymentProviderPayloads);
		lpp=paymentProviderResponse.Model().data.get(0).id;
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());					
	}	
	
	@Test
	public void testGetLocalPaymentProviderRequest() throws CKOException {
		Response<LocalPaymentProvider> paymentProviderResponse=null;
		LocalPaymentProviderModel paymentProviderPayloads=new LocalPaymentProviderModel();
		
		Response<PaymentToken> sessionTokenResponse=TokenHelper.CreateSessionToken();

		paymentProviderPayloads.id=lpp;
		paymentProviderPayloads.sessionToken=sessionTokenResponse.Model().id;
		
		PaymentProviderService api=new PaymentProviderService();
		paymentProviderResponse=api.getLocaPaymentProvider(paymentProviderPayloads);
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());
		
	}
	
	@Test
	public void testGetCardProviderListRequest() throws CKOException {
		Response<CardProviderList> paymentProviderResponse=null;
		PaymentProviderService api=new PaymentProviderService();
		paymentProviderResponse=api.getCardProviderList();
		
		cp=paymentProviderResponse.Model().data.get(0).id;
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());			
	}
	
	@Test
	public void testGetCardProviderRequest() throws CKOException {
		
		Response<CardProvider> paymentProviderResponse=null;
		CardProviderModels paymentProviderPayloads=new CardProviderModels();
		
		paymentProviderPayloads.id=cp;
		PaymentProviderService api=new PaymentProviderService();
		paymentProviderResponse=api.getCardProvider(paymentProviderPayloads);
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());			
	}
	
	
	
	

	

}
