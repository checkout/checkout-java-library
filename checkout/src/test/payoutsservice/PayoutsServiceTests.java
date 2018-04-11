package test.payoutsservice;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import test.TestHelper;

import com.checkout.APIClient;
import com.checkout.api.services.customer.response.Customer;
import com.checkout.api.services.payouts.request.BasePayout;
import com.checkout.api.services.payouts.response.Payout;
import com.checkout.api.services.shared.Response;
import com.google.gson.JsonSyntaxException;

public class PayoutsServiceTests {
	
	APIClient ckoClient;
	

	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient(TestHelper.secretKey,true);
	}


    @Test
    public void createPayout() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {

        Response<Customer> customerResponse =  ckoClient.customerService.createCustomer(TestHelper.getCustomerCreateModel());
        String cardId = customerResponse.model.cards.data.get(0).id;

        BasePayout payload = TestHelper.getPayoutModel(cardId);

        Response<Payout> payoutResponse= ckoClient.payoutsService.createPayout(payload);
        Payout payout = payoutResponse.model;

        assertEquals(200, payoutResponse.httpStatus);
        assertEquals("Authorised", payout.status);
        assertEquals("10000", payout.responseCode);
        assertEquals("Approved", payout.responseSummary);
        assertEquals("Approved", payout.responseDetails);

    }
	
}
