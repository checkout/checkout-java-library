package charges;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.CheckoutClient;
import test.CustomerHelper;
import apiServices.cards.response.Card;
import apiServices.charges.request.CardCharge;
import apiServices.charges.response.Charge;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;

/**
 * Servlet implementation class ChargeWithFullCard
 */
@WebServlet("/ChargeWithFullCard")
public class ChargeWithFullCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargeWithFullCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
			Response<Customer> customerResponse = CustomerHelper.createCustomerRequestTest();
			
			String customer_id=customerResponse.getType().id;
			
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
			Gson gson=new Gson();
			String s=gson.toJson(chargesResponse.getType());
			
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>ChargeWithFullCard:</p>");
			out.println("<p>" + s+ "</p>");
			out.println("</body>");
			out.println("</html>");
			
	}

}
