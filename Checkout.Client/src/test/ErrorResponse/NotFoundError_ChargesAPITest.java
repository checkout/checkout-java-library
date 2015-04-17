package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.cards.response.Card;
import apiServices.charges.ChargeService;
import apiServices.charges.request.CardCharge;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class NotFoundError_ChargesAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testChargeWithFullCardRequest() throws CKOException {		
		Response<Charge> chargesResponse=null;
		CardCharge chargesPayload =new CardCharge();
		
		chargesPayload.customerId="cust_fb3ab486-49c5-4e0c-bcc1-8299c2309cf3";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		Card cardContent=new Card();
		chargesPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";

		ChargeService api=new ChargeService();
		chargesResponse=api.chargeWithCard(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("Customer not found"));
		
	}

}
