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
import apiServices.sharedModels.Response;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.PaymentToken;

/**
 * Servlet implementation class PaymentTokenTest
 */
@WebServlet("/PaymentTokenTest")
public class CreatePaymentToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePaymentToken() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response<PaymentToken> tokenResponse= null;
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();		
		tokenPayload.value=100;
		tokenPayload.currency="GBP";
			
        CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
		tokenResponse = ckoClient.TokenService.createPaymentToken(tokenPayload);
		Gson gson=new Gson();
		String s=gson.toJson(tokenResponse.getType());
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>PaymentToken:</p>");
		out.println("<p>" + s+ "</p>");
		out.println("</body>");
		out.println("</html>");		
	}

}
