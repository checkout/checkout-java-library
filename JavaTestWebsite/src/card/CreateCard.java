package card;

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
import apiServices.cards.request.CardCreate;
import apiServices.cards.response.Card;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.Response;

/**
 * Servlet implementation class CreateCard
 */
@WebServlet("/CreateCard")
public class CreateCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");	
		Response<Customer> customerResponse = CustomerHelper.createCustomerRequestTest();
		String customer_id = customerResponse.getType().id;
		Response<CardResponseModel> cardResponse = null;
		CardCreate cardsPayload=new CardCreate();
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";
		cardsPayload.customerId=customer_id;
		
		
		cardResponse=ckoClient.CardService.createCard(cardsPayload);
		Gson gson=new Gson();
		String s=gson.toJson(cardResponse.getType());
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>CreateCard:</p>");
		out.println("<p>" + s+ "</p>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
