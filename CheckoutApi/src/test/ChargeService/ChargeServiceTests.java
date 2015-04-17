package test.ChargeService;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import test.ChargeHelper;
import test.CustomerHelper;
import test.LocalPaymentHelper;
import test.TokenHelper;
import apiServices.cards.response.Card;
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
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;



public class ChargeServiceTests {
	static String customer_id="";
	static String card_id="";
	static String email="";
    static CheckoutClient ckoClient;

	
	@BeforeClass
	public static void setUp() throws Exception {		
		ckoClient= new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
		Response<Customer> customerResponse = CustomerHelper.createCustomerRequestTest();
		
		customer_id=customerResponse.getType().id;
		card_id=customerResponse.getType().cards.data.get(0).id;
		email=customerResponse.getType().email;			
		
	}

	@Test
	public void testChargeWithFullCardRequest() throws JsonSyntaxException, IOException {		
		Response<Charge> chargesResponse=null;
		CardCharge chargesPayload =new CardCharge();
		
		chargesPayload.customerId=customer_id;
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		Card cardContent=new Card();
		chargesPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="5313581000123430";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="257";

		
		chargesResponse=ckoClient.ChargeService.chargeWithCard(chargesPayload);		
		
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());
		assertEquals("Approved",chargesResponse.getType().responseMessage);
		assertEquals(chargesPayload.card.name,chargesResponse.getType().card.name);
		assertEquals(chargesPayload.card.expiryMonth,chargesResponse.getType().card.expiryMonth);
		assertEquals(chargesPayload.card.expiryYear,chargesResponse.getType().card.expiryYear);
		assertEquals(chargesPayload.currency,chargesResponse.getType().currency);
		assertEquals(chargesPayload.value,chargesResponse.getType().value);
		
	}

	@Test
	public void testChargeWithCardIdRequest() throws JsonSyntaxException, IOException{				
		Response<Charge> chargesResponse=null;
		CardIdCharge chargesPayload=new CardIdCharge();
		chargesPayload.customerId=customer_id;
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardId=card_id;
		
		chargesResponse=ckoClient.ChargeService.chargeWithCardId(chargesPayload);
		
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals("Approved",chargesResponse.getType().responseMessage);
		assertEquals(chargesPayload.currency,chargesResponse.getType().currency);
		assertEquals(chargesPayload.value,chargesResponse.getType().value);	
		
	}

	@Test
	public void testChargeWithDefaultCardRequest() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		ChargeDefaultCard chargesPayload=new ChargeDefaultCard();
		chargesPayload.customerId=customer_id;
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		
		chargesResponse=ckoClient.ChargeService.chargeWithDefaultCustomerCard(chargesPayload);
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals("Approved",chargesResponse.getType().responseMessage);
		assertEquals(chargesPayload.currency,chargesResponse.getType().currency);
		assertEquals(chargesPayload.value,chargesResponse.getType().value);
	}

	@Test
	public void testChargeWithCardTokenRequest() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		CardTokenCharge chargesPayload=new CardTokenCharge();
		Response<CardToken> cardToken=TokenHelper.createCardToken();
		
		chargesPayload.customerId=customer_id;
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardToken=cardToken.getType().id;
		
		chargesResponse=ckoClient.ChargeService.chargeWithCardToken(chargesPayload);
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals("Approved",chargesResponse.getType().responseMessage);
		assertEquals(chargesPayload.currency,chargesResponse.getType().currency);
		assertEquals(chargesPayload.value,chargesResponse.getType().value);
	
	}

	
	public void testCreateLocalPaymentChargeRequest() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		Response<PaymentToken> sessionTokenResponse=TokenHelper.createSessionToken();
		LocalPaymentCharge chargesPayload=new LocalPaymentCharge();
		chargesPayload.email="5065@test.com";
		chargesPayload.sessionToken=sessionTokenResponse.getType().id;
		
		LocalPaymentRequestModel localpayment=new LocalPaymentRequestModel();		
		chargesPayload.localPayment=localpayment;
		Response<LocalPaymentProviderList> lpHelper=LocalPaymentHelper.getLocalPaymentProviderListRequest();		
		
		localpayment.lppId=lpHelper.getType().data.get(0).id;
		
		Map<String,String> userData=new HashMap<String,String>();		
		localpayment.userData=userData;
		userData.put("walletId", "79680053811");
		
		chargesResponse=ckoClient.ChargeService.createLocalPaymentCharge(chargesPayload);
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(chargesPayload.email,chargesResponse.getType().email);
	}

	@Test
	public void testRefundCardChargeRequest() throws JsonSyntaxException, IOException {
		
		Response<Charge> chargesResponse=ChargeHelper.chargeWithFullCardRequest();
		ChargeRefund chargesPayload=new ChargeRefund();
		chargesPayload.chargeId=chargesResponse.getType().id;
		chargesPayload.value=100;
		
		chargesResponse=ckoClient.ChargeService.refundCardChargeRequest(chargesPayload);
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(true,chargesResponse.getType().refunded);

	}

	@Test
	public void testCaptureCardChargeRequest() throws JsonSyntaxException, IOException{
		
		Response<Charge> chargesResponse=ChargeHelper.chargeWithFullCardRequest();
		ChargeCapture chargesPayload=new ChargeCapture();
		chargesPayload.chargeId=chargesResponse.getType().id;;
		chargesPayload.value=100;
		
		chargesResponse=ckoClient.ChargeService.captureCardCharge(chargesPayload);		
		
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(true,chargesResponse.getType().captured);
		
	}

	@Test 
	public void testUpdateCardChargeRequest() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=ChargeHelper.chargeWithFullCardRequest();;
		ChargeUpdate chargesPayload=new ChargeUpdate();
		
		chargesPayload.chargeId=chargesResponse.getType().id;;
		chargesPayload.description="30% off";
		
		chargesResponse=ckoClient.ChargeService.updateCardCharge(chargesPayload);
			
		assertEquals(false, chargesResponse.getHasError());
		assertEquals(200, chargesResponse.getHttpStatus());	
		assertEquals(chargesPayload.description,chargesResponse.getType().description);
	}

}
