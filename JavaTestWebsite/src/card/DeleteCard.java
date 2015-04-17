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
import apiServices.cards.request.CardDelete;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;

/**
 * Servlet implementation class DeleteCard
 */
@WebServlet("/DeleteCard")
public class DeleteCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCard() {
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
		String card_id=customerResponse.getType().cards.data.get(0).id;
		
		CardDelete cardsPayload=new CardDelete();
		cardsPayload.customerId=customer_id;
		cardsPayload.cardId=card_id;
		
		Response<DeleteResponse> cardResponse=ckoClient.CardService.deleteCard(cardsPayload);
		Gson gson=new Gson();
		String s=gson.toJson(cardResponse.getType());
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>DeleteCard:</p>");
		out.println("<p>" + s+ "</p>");
		out.println("</body>");
		out.println("</html>");
	
	}

}
