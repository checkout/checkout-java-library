package test.ChargeService;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import test.CustomerHelper;
import test.LocalPaymentHelper;
import test.TestHelper;
import test.TokenHelper;
import apiServices.cards.response.Card;
import apiServices.charges.ChargeService;
import apiServices.charges.request.CardCharge;
import apiServices.charges.request.CardIdCharge;
import apiServices.charges.request.CardTokenCharge;
import apiServices.charges.request.ChargeCapture;
import apiServices.charges.request.ChargeDefaultCard;
import apiServices.charges.request.ChargeRefund;
import apiServices.charges.request.ChargeUpdate;
import apiServices.charges.request.LocalPaymentCharge;
import apiServices.charges.request.LocalPaymentRequestModel;
import apiServices.charges.response.Charge;
import apiServices.customers.response.Customer;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.Response;
import apiServices.tokens.TokenService;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;
import exception.CKOException;


public class ChargeServiceTests {
	
	@BeforeClass
	public static void setUp() throws Exception {		
		
//		Response<Customer> customerResponse = CustomerHelper.CreateCustomerRequestTest();
//		
//		customer_id=customerResponse.getType().id;
//		card_id=customerResponse.getType().cards.data.get(0).id;
//		email=customerResponse.getType().email;			
//		
	}
	
	@Test
	public void testVerifyChargeByPaymentTokenRequest() throws CKOException {		
		ChargeService chargeService = new ChargeService();		
		
		String paymentTken ="charge_B5A9CAAD175V76BD3DC9";
		
		Response<Charge> chargeResponse = chargeService.verifyCharge(paymentTken);
		
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);			
		
		assertNotNull(chargeResponse.model.id);
	}
	
	/*	
	@Test
	public void testChargeWithFullCardRequest() throws CKOException {		
		ChargeService chargeService = new ChargeService();		
	
		CardCharge chargesPayload =TestHelper.getCardChargeModel();
		
		Response<Charge> chargesResponse= chargeService.chargeWithCard(chargesPayload);
		
		assertEquals(false, chargesResponse.hasError);
		assertEquals(200, chargesResponse.httpStatus);			
		assertEquals(chargesPayload.currency,chargesResponse.model.currency);
		assertEquals(chargesPayload.value,chargesResponse.model.value);
		
		assertEquals(false, chargesResponse.hasError);
		assertEquals(200, chargesResponse.httpStatus);
		assertEquals(chargesPayload.card.name,chargesResponse.model.card.name);
		assertEquals(chargesPayload.card.expiryMonth,chargesResponse.model.card.expiryMonth);
		assertEquals(chargesPayload.card.expiryYear,chargesResponse.model.card.expiryYear);
		assertEquals(chargesPayload.currency,chargesResponse.model.currency);
		assertEquals(chargesPayload.value,chargesResponse.model.value);
	}


	 	@Test
	public void testChargeWithNoCardDetailsRequest() throws CKOException {
		ChargeService chargeService = new ChargeService();
		Response<Customer> customerResponse = CustomerHelper.CreateCustomerRequestTest();
		
		String customer_id=customerResponse.getType().id;
	
		ChargeDefaultCard chargesPayload=new ChargeDefaultCard();
		chargesPayload.customerId=customer_id;
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.autoCapture="N";
		
		Response<Charge> chargesResponse= chargeService.chargeWithDefaultCustomerCard(chargesPayload);
		
		assertEquals(false, chargesResponse.hasError);
		assertEquals(200, chargesResponse.httpStatus);			
		assertEquals(chargesPayload.currency,chargesResponse.getType().currency);
		assertEquals(chargesPayload.value,chargesResponse.getType().value);
	}

	@Test
	public void testChargeWithCardIdRequest() throws CKOException {				
		Response<Charge> chargesResponse=null;
		CardIdCharge chargesPayload=new CardIdCharge();
		chargesPayload.customerId=customer_id;
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardId=card_id;
		
		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithCardId(chargesPayload);
		
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(chargesPayload.currency,chargesResponse.getType().currency);
		assertEquals(chargesPayload.value,chargesResponse.getType().value);	
		
	}


	@Test
	public void testChargeWithCardTokenRequest() throws CKOException {
		Response<Charge> chargesResponse=null;
		CardTokenCharge chargesPayload=new CardTokenCharge();
		Response<CardToken> cardToken=TokenHelper.CreateCardToken();
		
		chargesPayload.customerId=customer_id;
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardToken=cardToken.getType().id;
		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithCardToken(chargesPayload);
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());			
		assertEquals(chargesPayload.currency,chargesResponse.getType().currency);
		assertEquals(chargesPayload.value,chargesResponse.getType().value);
	
	}

	
	public void testCreateLocalPaymentChargeRequest() throws CKOException {
		Response<Charge> chargesResponse=null;
		Response<PaymentToken> sessionTokenResponse=TokenHelper.CreateSessionToken();
		LocalPaymentCharge chargesPayload=new LocalPaymentCharge();
		chargesPayload.email="5065@test.com";
		chargesPayload.sessionToken=sessionTokenResponse.getType().id;
		
		LocalPaymentRequestModel localpayment=new LocalPaymentRequestModel();		
		chargesPayload.localPayment=localpayment;
		Response<LocalPaymentProviderList> lpHelper=LocalPaymentHelper.GetLocalPaymentProviderListRequest();		
		
		localpayment.lppId=lpHelper.getType().data.get(0).id;
		
		Map<String,String> userData=new HashMap<String,String>();		
		localpayment.userData=userData;
		userData.put("walletId", "79680053811");
		
		ChargeService api=new ChargeService();
		chargesResponse=api.createLocalPaymentCharge(chargesPayload);
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(chargesPayload.email,chargesResponse.getType().email);
	}

	@Test
	public void testRefundCardChargeRequest() throws CKOException {
		Response<Charge> chargesResponse=null;
		ChargeRefund chargesPayload=new ChargeRefund();
		chargesPayload.chargeId=charge_id;
		chargesPayload.value=100;
		
		ChargeService api=new ChargeService();
		chargesResponse=api.RefundCardChargeRequest(chargesPayload);
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(true,chargesResponse.getType().refunded);

	}

	@Test
	public void testCaptureCardChargeRequest() throws CKOException {
		Response<Charge> chargesResponse=null;
		ChargeCapture chargesPayload=new ChargeCapture();
		chargesPayload.chargeId=charge_id;
		chargesPayload.value=100;
		ChargeService api=new ChargeService();
		chargesResponse=api.captureCardCharge(chargesPayload);		
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(true,chargesResponse.getType().captured);
		
	}

	@Test
	public void testUpdateCardChargeRequest() throws CKOException {
		Response<Charge> chargesResponse=null;
		ChargeUpdate chargesPayload=new ChargeUpdate();
		
		chargesPayload.chargeId=charge_id;
		chargesPayload.description="30% off";
		ChargeService api=new ChargeService();
		chargesResponse=api.updateCardCharge(chargesPayload);
			
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(chargesPayload.description,chargesResponse.getType().description);
	}
*/
}
