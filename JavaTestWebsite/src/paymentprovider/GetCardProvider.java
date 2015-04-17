package paymentprovider;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.CheckoutClient;
import apiServices.payment_provider.PaymentProviderService;
import apiServices.payment_provider.request.CardProviderModels;
import apiServices.payment_provider.response.CardProvider;
import apiServices.payment_provider.response.CardProviderList;
import apiServices.sharedModels.Response;

/**
 * Servlet implementation class GetCardProvider
 */
@WebServlet("/GetCardProvider")
public class GetCardProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCardProvider() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
			
			Response<CardProviderList> paymentProviderResponse=ckoClient.PaymentProviderService.getCardProviderList();
			String cp=paymentProviderResponse.getType().data.get(0).id;
			
			CardProviderModels paymentProviderPayloads=new CardProviderModels();
			
			paymentProviderPayloads.id=cp;
			PaymentProviderService api2=new PaymentProviderService();
			Response<CardProvider> Response=api2.getCardProvider(paymentProviderPayloads);
			
			Gson gson=new Gson();
			String s=gson.toJson(Response.getType());
			
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>GetCardProvider:</p>");
			out.println("<p>" + s+ "</p>");
			out.println("</body>");
			out.println("</html>");
		
	}

}
