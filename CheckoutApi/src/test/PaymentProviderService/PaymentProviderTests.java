package test.PaymentProviderService;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.google.gson.JsonSyntaxException;
import controllers.CheckoutClient;
import test.TokenHelper;
import apiServices.payment_provider.request.CardProviderModels;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.request.LocalPaymentProviderModel;
import apiServices.payment_provider.response.CardProvider;
import apiServices.payment_provider.response.CardProviderList;
import apiServices.payment_provider.response.LocalPaymentProvider;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.Response;
import apiServices.tokens.response.PaymentToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PaymentProviderTests {
	
	 static String lpp="";
	 static String cp="";
	 static CheckoutClient ckoClient;
	 
	@BeforeClass
	public  static void setUp() throws Exception {
		ckoClient= new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
	}
	
	@Test
	public void testGetLocalPaymentProviderListRequest() throws JsonSyntaxException, IOException  {
		Response<LocalPaymentProviderList>paymentProviderResponse=null;
		
		Response<PaymentToken> sessionTokenResponse=TokenHelper.createSessionToken();
		
		LocalPaymentProviderListModel paymentProviderPayloads=new LocalPaymentProviderListModel();
		
		paymentProviderPayloads.sessionToken=sessionTokenResponse.getType().id;
		
		
		paymentProviderResponse=ckoClient.PaymentProviderService.getLocaPaymentProviderList(paymentProviderPayloads);
		lpp=paymentProviderResponse.getType().data.get(0).id;
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());					
	}	
	
	@Test
	public void testGetLocalPaymentProviderRequest() throws JsonSyntaxException, IOException  {
		Response<LocalPaymentProvider> paymentProviderResponse=null;
		LocalPaymentProviderModel paymentProviderPayloads=new LocalPaymentProviderModel();
		
		Response<PaymentToken> sessionTokenResponse=TokenHelper.createSessionToken();

		paymentProviderPayloads.id=lpp;
		paymentProviderPayloads.sessionToken=sessionTokenResponse.getType().id;		
		
		paymentProviderResponse=ckoClient.PaymentProviderService.getLocaPaymentProvider(paymentProviderPayloads);
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());
		
	}
	
	@Test
	public void testGetCardProviderListRequest() throws JsonSyntaxException, IOException  {
		Response<CardProviderList> paymentProviderResponse=null;
		
		paymentProviderResponse=ckoClient.PaymentProviderService.getCardProviderList();
		
		cp=paymentProviderResponse.getType().data.get(0).id;
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());			
	}
	
	@Test
	public void testGetCardProviderRequest() throws JsonSyntaxException, IOException {
		
		Response<CardProvider> paymentProviderResponse=null;
		CardProviderModels paymentProviderPayloads=new CardProviderModels();
		
		paymentProviderPayloads.id=cp;
		
		paymentProviderResponse=ckoClient.PaymentProviderService.getCardProvider(paymentProviderPayloads);
		
		assertEquals(false, paymentProviderResponse.getHasError());
		assertEquals(200, paymentProviderResponse.getHttpStatus());			
	}
	
	
	
	

	

}
