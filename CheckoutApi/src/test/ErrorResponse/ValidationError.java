package test.ErrorResponse;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import test.TokenHelper;
import apiServices.cards.request.CardCreate;
import apiServices.cards.request.CardDelete;
import apiServices.cards.request.CardGet;
import apiServices.cards.request.CardListGet;
import apiServices.cards.response.Card;
import apiServices.cards.response.CardList;
import apiServices.charges.request.CardCharge;
import apiServices.charges.request.CardIdCharge;
import apiServices.charges.request.CardTokenCharge;
import apiServices.charges.request.ChargeCapture;
import apiServices.charges.request.ChargeDefaultCard;
import apiServices.charges.request.ChargeRefund;
import apiServices.charges.request.ChargeUpdate;
import apiServices.charges.response.Charge;
import apiServices.customers.request.CustomerGet;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.payment_provider.request.CardProviderModels;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.request.LocalPaymentProviderModel;
import apiServices.payment_provider.response.CardProvider;
import apiServices.payment_provider.response.LocalPaymentProvider;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;

public class ValidationError {
	String session_tok="sess_tok_4c684b5d-3cad-4695-9061-82182cf319a2";
	 static String lpp="";
	 static String cp="";
	 static CheckoutClient ckoClient;
	    
	 @BeforeClass
	public static void setUp() throws Exception {
	ckoClient= new CheckoutClient("sk_test_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_test_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");

	}

	@Test
	public void createCardTest_ValidationError() throws JsonSyntaxException, IOException{	
		Response<CardResponseModel> cardResponse = null;
		CardCreate cardsPayload=new CardCreate();
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="testamde2";
		cardContent.number="4444555555554444";
		cardContent.expiryMonth="08";
		cardContent.expiryYear="2016";
		cardContent.cvv="123";
		cardsPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		
		cardResponse=ckoClient.CardService.createCard(cardsPayload);
				
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true, cardResponse.error.message.contains("Invalid Card Number"));	
		
	}
	
	
	@Test
	public void getCardTest_InvalidCustomerError() throws JsonSyntaxException, IOException{
		Response<CardResponseModel> cardResponse = null;
		CardGet cardsPayload =new CardGet();
		cardsPayload.customerId="cust_0c6021c5-f595-49a9-855d-7ddd1a42852";
		cardsPayload.cardId="card_6894fb41-5ec5-4644-9696-0aa9174d4e89";
		
		cardResponse=ckoClient.CardService.getCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(true,cardResponse.error.errors.contains("Invalid customer id"));
	}
	@Test
	public void getCardTest_InvalidCardError() throws JsonSyntaxException, IOException{
		Response<CardResponseModel> cardResponse = null;
		CardGet cardsPayload =new CardGet();
		cardsPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		cardsPayload.cardId="card_b1303dffb-2a28-465b-ba8b-a6e82bd17341";
		
		cardResponse=ckoClient.CardService.getCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true,cardResponse.error.errors.contains("Invalid card id"));
	}
	
	@Test
	public void getCardListTest_ValidationError() throws JsonSyntaxException, IOException{
		Response<CardList> cardResponse = null;
		CardListGet cardsPayload =new CardListGet();
		cardsPayload.customerId="cust_0c6021c5-f595-49a9-855d-7ddd1a42852";
		
		cardResponse=ckoClient.CardService.getCardList(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true,cardResponse.error.errors.contains("Invalid customer id"));
		
	}
	
	@Test
	public void deleteCardTest_ValidationError() throws JsonSyntaxException, IOException {
		
		Response<DeleteResponse> cardResponse = null;	
		CardDelete cardsPayload=new CardDelete();
		cardsPayload.customerId="cust_4dacf511e-c3d0-41ea-a2b4-113a13b88972";
		cardsPayload.cardId="card_a724de76-a853-4e6c-aa9b-c527002b78f8";
		
		cardResponse=ckoClient.CardService.deleteCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true,cardResponse.error.errors.contains("Invalid customer id"));
		
	}
	
	@Test
	public void testChargeWithFullCardRequest_InvalidCardNumber() throws JsonSyntaxException, IOException {		
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

		chargesResponse=ckoClient.ChargeService.chargeWithCard(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("Invalid Card Number"));
		
	}
	
	@Test
	public void testChargeWithCardIdRequest_ValidationError() throws JsonSyntaxException, IOException  {				
		Response<Charge> chargesResponse=null;
		CardIdCharge chargesPayload=new CardIdCharge();
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardId="card_6894fb41-5ec5-4644-9696-0aa9174d4e89";
		
	
		chargesResponse=ckoClient.ChargeService.chargeWithCardId(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("No Account / No Customer (Token incorrect or invalid)"));
	}
	
	
	@Test
	public void testChargeWithNoCardDetailsRequest_InvalidCustomerId() throws JsonSyntaxException, IOException  {
		Response<Charge> chargesResponse=null;
		ChargeDefaultCard chargesPayload=new ChargeDefaultCard();
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-ds0468e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		
		chargesResponse=ckoClient.ChargeService.chargeWithDefaultCustomerCard(chargesPayload);
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.errors.contains("Invalid customer id"));
			
	}
	
	@Test
	public void testChargeWithNoCardDetailsRequest_InvalidCurrency() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		ChargeDefaultCard chargesPayload=new ChargeDefaultCard();
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		chargesPayload.currency="eje";
		chargesPayload.value=100;
		
		chargesResponse=ckoClient.ChargeService.chargeWithDefaultCustomerCard(chargesPayload);
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("Invalid currency"));
			
	}
	
	@Test
	public void testChargeWithCardTokenRequest_InvalidCustomerId() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		CardTokenCharge chargesPayload=new CardTokenCharge();
		Response<CardToken> cardToken=TokenHelper.createCardToken();
		
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d046s8e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardToken=cardToken.getType().id;
		
		chargesResponse=ckoClient.ChargeService.chargeWithCardToken(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.errors.contains("Invalid customer id"));
		
	}
	
	@Test
	public void testChargeWithCardTokenRequest_InvalidTokenError() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		CardTokenCharge chargesPayload=new CardTokenCharge();
		
		chargesPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		chargesPayload.cardToken="card_tok_dd103a38-812db-4cee-b940-baad783421d5";
		
		chargesResponse=ckoClient.ChargeService.chargeWithCardToken(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.errors.contains("Invalid value for 'token'"));
		
	}
		
	@Test
	public void testRefundCardChargeRequest_TransactionError() throws JsonSyntaxException, IOException  {
		Response<Charge> chargesResponse=null;
		ChargeRefund chargesPayload=new ChargeRefund();
		chargesPayload.chargeId="charge_b5341cbabd2610t55672c";
		chargesPayload.value=100;
		
		chargesResponse=ckoClient.ChargeService.refundCardChargeRequest(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Transaction Not Found"));	
		
	}

	@Test
	public void testCaptureCardChargeRequest_TransactionError() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		ChargeCapture chargesPayload=new ChargeCapture();
		chargesPayload.chargeId="charge_b5341cba2bd260t55672c";
		chargesPayload.value=100;
		
		chargesResponse=ckoClient.ChargeService.captureCardCharge(chargesPayload);		
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("Transaction Not Found"));	
		
		
	}

	@Test
	public void testUpdateCardChargeRequest_TransactionError() throws JsonSyntaxException, IOException {
		Response<Charge> chargesResponse=null;
		
		ChargeUpdate chargesPayload=new ChargeUpdate();
		chargesPayload.chargeId="charge_8ab11dbabd26560w556719";
		chargesPayload.description="zwp";
		
		chargesResponse=ckoClient.ChargeService.updateCardCharge(chargesPayload);
			
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(true, chargesResponse.error.message.contains("An error has occurred"));	
		
	}
	
	@Test
	public void UpdateCustomerRequestTest() throws JsonSyntaxException, IOException {
		CustomerUpdate customerPayload = new CustomerUpdate();
		customerPayload.name="grace";
		customerPayload.email="zwp1031@sina.com";
		customerPayload.phoneNumber="07592331690";
		customerPayload.customerId="cust_1b84sc22e-b548-47dc-91d5-e8d1f4a84ae7";

		
		Response<Customer> customerResponse = ckoClient.CustomerService.updateCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true,customerResponse.error.errors.contains("Invalid customer id"));

	}

	@Test
	public void GetCustomerRequestTest() throws JsonSyntaxException, IOException {

		CustomerGet customerPayload = new CustomerGet();
		customerPayload.customerId="cust_7fad3d2ce-8575-4c34-b427-d3ef90917994";

		
		Response<Customer> customerResponse = null;

		customerResponse = ckoClient.CustomerService.getCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true,customerResponse.error.errors.contains("Invalid customer id"));

	}
	
	@Test
	public void testGetLocalPaymentProviderListRequest_TokenExpiredError() throws JsonSyntaxException, IOException{
		Response<LocalPaymentProviderList>paymentProviderResponse=null;
		LocalPaymentProviderListModel paymentProviderPayloads=new LocalPaymentProviderListModel();
		paymentProviderPayloads.sessionToken=session_tok;
		
		
		paymentProviderResponse=ckoClient.PaymentProviderService.getLocaPaymentProviderList(paymentProviderPayloads);
		
		
		assertEquals(true, paymentProviderResponse.getHasError());
		assertEquals(true, paymentProviderResponse.error.message.contains("Invalid local payment token"));					
	}	
	
	@Test
	public void testGetLocalPaymentProviderRequest_ValidationError() throws JsonSyntaxException, IOException{
		Response<LocalPaymentProvider> paymentProviderResponse=null;
		LocalPaymentProviderModel paymentProviderPayloads=new LocalPaymentProviderModel();
		paymentProviderPayloads.id="lpxxx";
		paymentProviderPayloads.sessionToken=session_tok;
		
		paymentProviderResponse=ckoClient.PaymentProviderService.getLocaPaymentProvider(paymentProviderPayloads);
		
		assertEquals(true, paymentProviderResponse.getHasError());
		assertEquals(true, paymentProviderResponse.error.message.contains("Validation error"));		
		
	}

	
	@Test
	public void testGetCardProviderRequest_ValidationError() throws JsonSyntaxException, IOException  {
		
		Response<CardProvider> paymentProviderResponse=null;
		CardProviderModels paymentProviderPayloads=new CardProviderModels();
		
		paymentProviderPayloads.id="cp";
		
		paymentProviderResponse=ckoClient.PaymentProviderService.getCardProvider(paymentProviderPayloads);
		
		assertEquals(true, paymentProviderResponse.getHasError());
		assertEquals(true, paymentProviderResponse.error.message.contains("Validation error"));
		
	}
	
	@Test
	public void testCreateCardTokenRequest_InvalidCardNumber() throws JsonSyntaxException, IOException {
		Response<CardToken> tokenResponse = null;
		CardTokenCreate tokenPayload = new CardTokenCreate();		

		Card cardContent = new Card();
		tokenPayload.card = cardContent;
		cardContent.name="testamde2";
		cardContent.number="3232555555554444";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2018";
		cardContent.cvv="1000";
		
		tokenResponse = ckoClient.TokenService.createCardToken(tokenPayload);

		assertEquals(true, tokenResponse.getHasError());
		assertEquals(true,tokenResponse.error.message.contains("Invalid Card Number"));
		
	}
	

	@Test
	public void testCreateSessionTokenRequest_InvalidCurrency() throws JsonSyntaxException, IOException  {
		Response<PaymentToken> tokenResponse= null;
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		
		tokenPayload.value=6;
		tokenPayload.currency="kde";
		
		tokenResponse = ckoClient.TokenService.createPaymentToken(tokenPayload);
		assertEquals(true, tokenResponse.getHasError());
		assertEquals(true,tokenResponse.error.message.contains("Invalid currency"));
	}

}
