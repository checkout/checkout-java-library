package token;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import controllers.CheckoutClient;
import apiServices.cards.response.Card;
import apiServices.sharedModels.Response;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.response.CardToken;

/**
 * Servlet implementation class CreateCardToken
 */
@WebServlet("/CreateCardToken")
public class CreateCardToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCardToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
		Response<CardToken> tokenResponse = null;
		CardTokenCreate tokenPayload = new CardTokenCreate();

		Card cardContent = new Card();
		tokenPayload.card = cardContent;
		cardContent.name = "zwp";
		cardContent.number = "4543474002249996";
		cardContent.expiryMonth = "06";
		cardContent.expiryYear = "2017";
		cardContent.cvv = "956";
		
			tokenResponse = ckoClient.TokenService.createCardToken(tokenPayload);
			Gson gson=new Gson();
			String s=gson.toJson(tokenResponse.getType());
					System.out.println(s);	
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>CreateCardToken:</p>");
			out.println("<p>" + s+ "</p>");
			out.println("</body>");
			out.println("</html>");

		

	}

}
