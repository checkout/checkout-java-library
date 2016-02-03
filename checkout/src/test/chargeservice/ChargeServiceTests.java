package test.chargeservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.api.services.card.request.CardCreate;
import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.charge.request.BaseCharge;
import com.checkout.api.services.charge.request.BaseChargeInfo;
import com.checkout.api.services.charge.request.CardCharge;
import com.checkout.api.services.charge.request.CardIdCharge;
import com.checkout.api.services.charge.request.CardTokenCharge;
import com.checkout.api.services.charge.request.ChargeCapture;
import com.checkout.api.services.charge.request.ChargeRefund;
import com.checkout.api.services.charge.request.ChargeVoid;
import com.checkout.api.services.charge.request.DefaultCardCharge;
import com.checkout.api.services.charge.response.Capture;
import com.checkout.api.services.charge.response.Charge;
import com.checkout.api.services.charge.response.ChargeHistory;
import com.checkout.api.services.charge.response.Refund;
import com.checkout.api.services.charge.response.Void;
import com.checkout.api.services.customer.response.Customer;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.google.gson.JsonSyntaxException;

import test.TestHelper;

public class ChargeServiceTests {
	
	APIClient ckoClient;

	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50",true);
	}
	
	@Test
	public void verifyChargeByPaymentToken() throws JsonSyntaxException, IOException {		
		
		String paymentToken ="pay_tok_4bf11f31-ae5f-4ac6-a942-2105f0f41860";// payment token for the JS charge
		
		Response<Charge> chargeResponse = ckoClient.chargeService.verifyCharge(paymentToken);
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);			
		
		assertNotNull(chargeResponse.model.id);
	}
	
	@Test
	public void chargeWithCardToken() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
				
		String cardToken ="card_tok_220E97F3-4DA3-4F09-B7AE-C633D8D5E3E2";// card token for charge
		
		CardTokenCharge payload = TestHelper.getCardTokenChargeModel(cardToken);
		
		Response<Charge> chargeResponse = ckoClient.chargeService.chargeWithCardToken(payload);
		Charge charge = chargeResponse.model;
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);			
		assertEquals(payload.transactionIndicator,charge.transactionIndicator);
		
		validateBaseCharge(payload, charge);	
	}
	
	@Test
	public void createChargeWithCard() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		CardCharge payload =TestHelper.getCardChargeModel();
		
		Response<Charge> chargeResponse= ckoClient.chargeService.chargeWithCard(payload);
		Charge charge = chargeResponse.model;
				
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);		
		assertEquals(payload.transactionIndicator,charge.transactionIndicator);
		
		validateBaseCharge(payload, charge);	
		validateCard(payload.card, charge.card);
	}
	
	@Test
	public void createChargeWithCardId() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
	
		Response<Customer> customerResponse =  ckoClient.customerService.createCustomer(TestHelper.getCustomerCreateModel());
		String cardId = customerResponse.model.cards.data.get(0).id;
		String customerEmail = customerResponse.model.email;
		
		CardIdCharge payload = TestHelper.getCardIdChargeModel(cardId, customerEmail);
				
		Response<Charge> chargeResponse= ckoClient.chargeService.chargeWithCardId(payload);
		Charge charge = chargeResponse.model;
				
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);		
		assertEquals(payload.transactionIndicator,charge.transactionIndicator);
		
		validateBaseCharge(payload, charge);	
	}

	@Test
	public void createChargeWithCustomerDefaultCard() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		

		Response<Customer> customerResponse =  ckoClient.customerService.createCustomer(TestHelper.getCustomerCreateModel());
		
		DefaultCardCharge payload =TestHelper.getDefaultCardChargeModel(customerResponse.model.email);
		
		Response<Charge> chargeResponse= ckoClient.chargeService.chargeWithDefaultCustomerCard(payload);
		Charge charge = chargeResponse.model;
				
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);		
		assertEquals(payload.transactionIndicator,charge.transactionIndicator);
		
		validateBaseCharge(payload, charge);
	}
	
	
	@Test
	public void getCharge() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		CardCharge payload =TestHelper.getCardChargeModel();
		Response<Charge> createChargeResponse= ckoClient.chargeService.chargeWithCard(payload);
		
		Response<Charge> chargeResponse= ckoClient.chargeService.getCharge(createChargeResponse.model.id);
		Charge charge = chargeResponse.model;
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);		
		assertEquals(createChargeResponse.model.transactionIndicator,charge.transactionIndicator);
		
		validateBaseCharge(payload, charge);	
		validateCard(payload.card, charge.card);
	}
	
	@Test
	public void updateCharge() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		
		Response<Charge> createChargeResponse= ckoClient.chargeService.chargeWithCard(TestHelper.getCardChargeModel());
		
		Response<OkResponse> chargeResponse= ckoClient.chargeService.updateCharge(createChargeResponse.model.id,TestHelper.getChargeUpdateModel());
		
		
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);		
		assertEquals(chargeResponse.model.message,"ok");
		}
	
	@Test
	public void voidCharge() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		
		Response<Charge> createChargeResponse= ckoClient.chargeService.chargeWithCard(TestHelper.getCardChargeModel());
		
		ChargeVoid payload =TestHelper.getChargeVoidModel();
		Response<Void> voidResponse= ckoClient.chargeService.voidCharge(createChargeResponse.model.id,payload);
		
		
		assertEquals(false, voidResponse.hasError);
		assertEquals(200, voidResponse.httpStatus);		
		assertEquals(voidResponse.model.status.toLowerCase(),"voided");
		validateBaseChargeInfo(payload,voidResponse.model);
	}
	
	@Test
	public void captureChargeWithNoParameters() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		CardCharge cardChargePayload =TestHelper.getCardChargeModel();
		Response<Charge> createChargeResponse= ckoClient.chargeService.chargeWithCard(cardChargePayload);
		
		Response<Capture> captureResponse= ckoClient.chargeService.captureCharge(createChargeResponse.model.id,null);
		
		assertEquals(false, captureResponse.hasError);
		assertEquals(200, captureResponse.httpStatus);		
		assertEquals(captureResponse.model.status.toLowerCase(),"captured");
		validateBaseChargeInfo(cardChargePayload,captureResponse.model);
	}
	
	@Test
	public void captureChargeWithParameters() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		Response<Charge> createChargeResponse= ckoClient.chargeService.chargeWithCard(TestHelper.getCardChargeModel());
		
		ChargeCapture cardChargePayload =TestHelper.getChargeCaptureModel();
		cardChargePayload.value = createChargeResponse.model.value;
				
		Response<Capture> captureResponse= ckoClient.chargeService.captureCharge(createChargeResponse.model.id,cardChargePayload);
		
		assertEquals(false, captureResponse.hasError);
		assertEquals(200, captureResponse.httpStatus);	
		assertEquals(cardChargePayload.value,Integer.toString(captureResponse.model.value));
		assertEquals(captureResponse.model.status.toLowerCase(),"captured");
		validateBaseChargeInfo(cardChargePayload,captureResponse.model);
	}
	
	@Test
	public void refundChargeWithParameters() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		
		Response<Charge> createChargeResponse= ckoClient.chargeService.chargeWithCard(TestHelper.getCardChargeModel());
		Response<Capture> captureResponse= ckoClient.chargeService.captureCharge(createChargeResponse.model.id,null);
		
		ChargeRefund refundPayload =TestHelper.getChargeRefundModel();
		Response<Refund> refundResponse= ckoClient.chargeService.refundRequest(captureResponse.model.id,refundPayload);
		
		assertEquals(false, refundResponse.hasError);
		assertEquals(200, refundResponse.httpStatus);	
		assertEquals(refundResponse.model.status.toLowerCase(),"refunded");
		validateBaseChargeInfo(refundPayload,refundResponse.model);
	}
	
	@Test
	public void createChargeWithCard3ds() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		CardCharge payload =TestHelper.getCardChargeModel3ds();
		
		Response<Charge> chargeResponse= ckoClient.chargeService.chargeWithCard(payload);
		Charge charge = chargeResponse.model;
				
		assertEquals(false, chargeResponse.hasError);
		assertEquals(200, chargeResponse.httpStatus);
		
		validateBaseCharge3ds(payload, charge);	
	}
	
	@Test
	public void getChargeHistory() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {
        
        Response<Charge> createChargeResponse= ckoClient.chargeService.chargeWithCard(TestHelper.getCardChargeModel());
		
		ChargeVoid payload =TestHelper.getChargeVoidModel();
		Response<Void> voidResponse= ckoClient.chargeService.voidCharge(createChargeResponse.model.id,payload);

		Response<ChargeHistory> response = ckoClient.chargeService.getChargeHistory(voidResponse.model.id);

        assertNotNull(response);
        assertEquals(200, response.httpStatus);
        assertEquals(response.model.charges.length, 2);

        assertEquals(response.model.charges[0].id, voidResponse.model.id);
        assertEquals(response.model.charges[1].id, createChargeResponse.model.id);
    }
	
	@Test
    public void getChargeWithMultipleHistory() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {
        
        // charge
        CardCharge payload = TestHelper.getCardChargeModel();
        Response<Charge> chargeResponse = ckoClient.chargeService.chargeWithCard(payload);
        Charge charge = chargeResponse.model;

        // capture
        ChargeCapture chargeCaptureModel = TestHelper.getChargeCaptureModel();
        Response<Capture> captureResponse = ckoClient.chargeService.captureCharge(charge.id, chargeCaptureModel);

        // refund
        ChargeRefund chargeRefundModel = TestHelper.getChargeRefundModel();
        Response<Refund> refundResponse = ckoClient.chargeService.refundRequest(captureResponse.model.id, chargeRefundModel);

        Response<ChargeHistory> response = ckoClient.chargeService.getChargeHistory(chargeResponse.model.id);

        assertEquals(false, chargeResponse.hasError);
        assertEquals(200, chargeResponse.httpStatus);
        assertEquals(response.model.charges.length, 3);

        assertEquals(response.model.charges[0].id, refundResponse.model.id);
        assertEquals(response.model.charges[1].id, captureResponse.model.id);
        assertEquals(response.model.charges[2].id, chargeResponse.model.id);

        assertEquals(chargeResponse.model.id, captureResponse.model.originalId);
        assertEquals(refundResponse.model.originalId, captureResponse.model.id);
    }
	
	private void validateBaseCharge3ds(BaseCharge payload, Charge charge) {
		assertNotNull(charge.id);
		assertNotNull(charge.redirectUrl);
		assertEquals(charge.responseCode,"10000");
		assertEquals(payload.chargeMode,charge.chargeMode);
	}
	
	/*
	 * Checks if the card returned for charge is same as the payload card
	 * 
	 */
	private void validateCard(CardCreate payload, Card chargeCard) {
		assertNotNull(chargeCard.id);
		assertNotNull(chargeCard.customerId);
		assertEquals(chargeCard.paymentMethod.toLowerCase(),"visa");
		assertNotNull(chargeCard.fingerprint);
		assertTrue(payload.number.endsWith(chargeCard.last4));
		assertEquals(payload.name,chargeCard.name);
		assertEquals(payload.expiryMonth,chargeCard.expiryMonth);
		assertEquals(payload.expiryYear,chargeCard.expiryYear);
		assertEquals(payload.billingDetails.addressLine1, chargeCard.billingDetails.addressLine1);
		assertEquals(payload.billingDetails.addressLine2, chargeCard.billingDetails.addressLine2);
		assertEquals(payload.billingDetails.city, chargeCard.billingDetails.city);
		assertEquals(payload.billingDetails.country, chargeCard.billingDetails.country);
		assertEquals(payload.billingDetails.phone.countryCode, chargeCard.billingDetails.phone.countryCode);
		assertEquals(payload.billingDetails.phone.number, chargeCard.billingDetails.phone.number);
		assertEquals(payload.billingDetails.postcode, chargeCard.billingDetails.postcode);
		assertEquals(payload.billingDetails.state, chargeCard.billingDetails.state);
		
	}

	/*
	 * Checks if the card charge information for baseCharge matches the payload
	 * 
	 */
	private void validateBaseCharge(BaseCharge payload, Charge charge) {
		assertNotNull(charge.id);
		assertNotNull(charge.created);
		assertNotNull(charge.status);
		assertNotNull(charge.metadata);
		assertNotNull(charge.products);
		assertEquals(charge.responseCode,"10000");
		assertEquals(payload.trackId,charge.trackId);
		assertEquals(payload.value,charge.value);
		assertEquals(payload.currency,charge.currency.toLowerCase());
		assertEquals(payload.description,charge.description);
		assertEquals(payload.email,charge.email);
		assertEquals(payload.chargeMode,charge.chargeMode);
		assertEquals(payload.customerIp,charge.customerIp);
		assertEquals(payload.autoCapture,charge.autoCapture);
		assertEquals(payload.autoCapTime,charge.autoCapTime);
		assertEquals(payload.udf1, charge.udf1);
		assertEquals(payload.udf2, charge.udf2);
		assertEquals(payload.udf3, charge.udf3);
		assertEquals(payload.udf4, charge.udf4);
		assertEquals(payload.udf5, charge.udf5);
	}
	

	private void validateBaseChargeInfo(BaseChargeInfo payload, Void charge) {
		assertNotNull(charge.id);
		assertNotNull(charge.originalId);
		assertNotNull(charge.created);
		assertNotNull(charge.value);
		assertNotNull(charge.currency);
		assertNotNull(charge.metadata);
		assertNotNull(charge.products);
		assertEquals(charge.responseCode,"10000");
		assertEquals(payload.trackId,charge.trackId);
		assertEquals(payload.description,charge.description);
		assertEquals(payload.udf1, charge.udf1);
		assertEquals(payload.udf2, charge.udf2);
		assertEquals(payload.udf3, charge.udf3);
		assertEquals(payload.udf4, charge.udf4);
		assertEquals(payload.udf5, charge.udf5);
	}
}
