package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.payment_provider.PaymentProviderService;
import apiServices.payment_provider.request.CardProviderModels;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.request.LocalPaymentProviderModel;
import exception.CKOException;
import exception.ValidationFailException;

public class RequiredPropertyError_PaymentProviderAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetLocalPaymentProviderListRequest_RequiredPropertyError() throws CKOException{
		boolean hasPropertyError = false;
		LocalPaymentProviderListModel paymentProviderPayloads=new LocalPaymentProviderListModel();		
		
		PaymentProviderService api=new PaymentProviderService();
		try {
			api.getLocaPaymentProviderList(paymentProviderPayloads);
		} catch(ValidationFailException e){			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);	
		assertEquals(true, paymentProviderPayloads.errorList.contains("The session token is required"));
	}	
	
	@Test
	public void testGetLocalPaymentProviderRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;
		LocalPaymentProviderModel paymentProviderPayloads=new LocalPaymentProviderModel();		
		
		PaymentProviderService api=new PaymentProviderService();
		try{
		api.getLocaPaymentProvider(paymentProviderPayloads);
		}catch(ValidationFailException e){			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);		
		assertEquals(true, paymentProviderPayloads.errorList.contains("The session token is required"));

	}
		
		
	@Test
	public void testGetCardProviderRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;		
		CardProviderModels paymentProviderPayloads=new CardProviderModels();
		
		PaymentProviderService api=new PaymentProviderService();
		try{
		api.getCardProvider(paymentProviderPayloads);
		}catch(ValidationFailException e){			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);		
		assertEquals(true, paymentProviderPayloads.errorList.contains("The payment provider id is required"));
	
	}
	
}
