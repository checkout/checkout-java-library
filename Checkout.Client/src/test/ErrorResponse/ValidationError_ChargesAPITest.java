package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;
import apiServices.tokens.response.CardToken;
import exception.CKOException;


public class ValidationError_ChargesAPITest {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testChargeWithFullCardRequest_InvalidCardNumber() throws CKOException {		
		Response<Charge> chargesResponse=null;
		CardCharge chargesPayload =new CardCharge();
		
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		Card cardContent=new Card();
		chargesPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="5555559890554444";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2018";
		cardContent.cvv="100";

		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithCard(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("Invalid Card Number"));
		
	}
	
	@Test
	public void testChargeWithCardIdRequest_ValidationError() throws CKOException {				
		Response<Charge> chargesResponse=null;
		CardIdCharge chargesPayload=new CardIdCharge();
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardId="card_6894fb41-5ec5-4644-9696-0aa9174d4e89";
		
		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithCardId(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("No Account / No Customer (Token incorrect or invalid)"));
	}
	
	
	@Test
	public void testChargeWithNoCardDetailsRequest_InvalidCustomerId() throws CKOException {
		Response<Charge> chargesResponse=null;
		ChargeDefaultCard chargesPayload=new ChargeDefaultCard();
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-ds0468e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithDefaultCustomerCard(chargesPayload);
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.errors.contains("Invalid customer id"));
			
	}
	
	@Test
	public void testChargeWithNoCardDetailsRequest_InvalidCurrency() throws CKOException {
		Response<Charge> chargesResponse=null;
		ChargeDefaultCard chargesPayload=new ChargeDefaultCard();
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		chargesPayload.currency="eje";
		chargesPayload.value=100;
		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithDefaultCustomerCard(chargesPayload);
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("Invalid currency"));
			
	}
	
	@Test
	public void testChargeWithCardTokenRequest_InvalidCustomerId() throws CKOException {
		Response<Charge> chargesResponse=null;
		CardTokenCharge chargesPayload=new CardTokenCharge();
		Response<CardToken> cardToken=TokenHelper.CreateCardToken();
		
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d046s8e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardToken=cardToken.Model().id;
		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithCardToken(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.errors.contains("Invalid customer id"));
		
	}
	
	@Test
	public void testChargeWithCardTokenRequest_InvalidTokenError() throws CKOException {
		Response<Charge> chargesResponse=null;
		CardTokenCharge chargesPayload=new CardTokenCharge();
		
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardToken="card_tok_dd103a38-812db-4cee-b940-baad783421d5";
		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithCardToken(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.errors.contains("Invalid value for 'token'"));
		
	}
		
	@Test
	public void testRefundCardChargeRequest_TransactionError() throws CKOException {
		Response<Charge> chargesResponse=null;
		ChargeRefund chargesPayload=new ChargeRefund();
		chargesPayload.chargeId="charge_b5341cbabd2610t55672c";
		chargesPayload.value=100;
		
		ChargeService api=new ChargeService();
		chargesResponse=api.RefundCardChargeRequest(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Transaction Not Found"));	
		
	}

	@Test
	public void testCaptureCardChargeRequest_TransactionError() throws CKOException {
		Response<Charge> chargesResponse=null;
		ChargeCapture chargesPayload=new ChargeCapture();
		chargesPayload.chargeId="charge_b5341cba2bd260t55672c";
		chargesPayload.value=100;
		ChargeService api=new ChargeService();
		chargesResponse=api.captureCardCharge(chargesPayload);		
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Transaction Not Found"));	
		
		
	}

	@Test
	public void testUpdateCardChargeRequest_TransactionError() throws CKOException {
		Response<Charge> chargesResponse=null;
		
		ChargeUpdate chargesPayload=new ChargeUpdate();
		chargesPayload.chargeId="charge_8ab11dbabd26560w556719";
		chargesPayload.description="zwp";
		ChargeService api=new ChargeService();
		chargesResponse=api.updateCardCharge(chargesPayload);
			
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("An error has occurred"));	
		
	}
	

}
