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
import test.TokenHelper;
import apiServices.charges.request.CardTokenCharge;
import apiServices.charges.response.Charge;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;
import apiServices.tokens.response.CardToken;

/**
 * Servlet implementation class ChargeWithCardToken
 */
@WebServlet("/ChargeWithCardToken")
public class ChargeWithCardToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargeWithCardToken() {
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
			
			CardTokenCharge chargesPayload=new CardTokenCharge();
			Response<CardToken> cardToken=TokenHelper.createCardToken();
			
			chargesPayload.customerId=customer_id;
			chargesPayload.currency="usd";
			chargesPayload.value=100;
			chargesPayload.cardToken=cardToken.getType().id;
			
			Response<Charge> chargesResponse=ckoClient.ChargeService.chargeWithCardToken(chargesPayload);
			
			Gson gson=new Gson();
			String s=gson.toJson(chargesResponse.getType());
			
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>ChargeWithCardToken:</p>");
			out.println("<p>" + s+ "</p>");
			out.println("</body>");
			out.println("</html>");
		
	}

}
