package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
import exception.CKOException;
import exception.ValidationFailException;

public class RequiredPropertyError_ChargesAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testChargeWithFullCardRequest_RequiredPropertyError() throws CKOException {		
		boolean hasPropertyError = false;
		CardCharge chargesPayload =new CardCharge();
		Card cardContent=new Card();
		chargesPayload.card=cardContent;
		ChargeService api=new ChargeService();
		
		try{
		api.chargeWithCard(chargesPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}				
	}

	@Test
	public void testChargeWithCardIdRequest_RequiredPropertyError() throws CKOException {				
		boolean hasPropertyError = false;
		CardIdCharge chargesPayload=new CardIdCharge();
		
		ChargeService api=new ChargeService();
		try{
		api.chargeWithCardId(chargesPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true,chargesPayload.errorList.contains("email/customerId is required"));
		assertEquals(true,chargesPayload.errorList.contains("currency is required"));
		assertEquals(true,chargesPayload.errorList.contains("amount is required"));
		assertEquals(true,chargesPayload.errorList.contains("cardId is required"));

		
	}

	@Test
	public void testChargeWithNoCardDetailsRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;
		ChargeDefaultCard chargesPayload=new ChargeDefaultCard();		
		ChargeService api=new ChargeService();
		
		try{
		api.chargeWithDefaultCustomerCard(chargesPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true,chargesPayload.errorList.contains("email/customerId is required"));
		assertEquals(true,chargesPayload.errorList.contains("currency is required"));
		assertEquals(true,chargesPayload.errorList.contains("amount is required"));
	}

	@Test
	public void testChargeWithCardTokenRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;
		CardTokenCharge chargesPayload=new CardTokenCharge();
		
		ChargeService api=new ChargeService();
		try{
		api.chargeWithCardToken(chargesPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true,chargesPayload.errorList.contains("email/customerId is required"));
		assertEquals(true,chargesPayload.errorList.contains("currency is required"));
		assertEquals(true,chargesPayload.errorList.contains("amount is required"));
		assertEquals(true,chargesPayload.errorList.contains("cardToken is required"));

	
	}

	@Test
	public void testCreateLocalPaymentChargeRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;
		LocalPaymentCharge chargesPayload=new LocalPaymentCharge();
		
		LocalPaymentRequestModel localpayment=new LocalPaymentRequestModel();		
		chargesPayload.localPayment=localpayment;
		
		ChargeService api=new ChargeService();
		try{
		api.createLocalPaymentCharge(chargesPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true,chargesPayload.errorList.contains("email is required"));
		assertEquals(true,chargesPayload.errorList.contains("sessionToken is required"));
		assertEquals(true,chargesPayload.errorList.contains("localPayment IppId is required"));


	}

	@Test
	public void testRefundCardChargeRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;

		ChargeRefund chargesPayload=new ChargeRefund();
		ChargeService api=new ChargeService();
		try{
		api.RefundCardChargeRequest(chargesPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true,chargesPayload.errorList.contains("chargeId is required"));
		
	}

	@Test
	public void testCaptureCardChargeRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;
		ChargeCapture chargesPayload=new ChargeCapture();
		ChargeService api=new ChargeService();
		
		try{
		api.captureCardCharge(chargesPayload);
		}catch(ValidationFailException e){			
			hasPropertyError = true;
		}
		
		assertEquals(true, hasPropertyError);
		assertEquals(true,chargesPayload.errorList.contains("chargeId is required"));
		
	}

	@Test
	public void testUpdateCardChargeRequest_RequiredPropertyError() throws CKOException {	
		boolean hasPropertyError = false;
		ChargeUpdate chargesPayload=new ChargeUpdate();		
		ChargeService api=new ChargeService();
		
		try{
		api.updateCardCharge(chargesPayload);
		}catch(ValidationFailException e){			
			hasPropertyError = true;
		}		
		assertEquals(true, hasPropertyError);
		assertEquals(true,chargesPayload.errorList.contains("chargeId is required"));
	}

}
