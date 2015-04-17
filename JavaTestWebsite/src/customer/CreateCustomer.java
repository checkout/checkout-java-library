package customer;

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
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;

/**
 * Servlet implementation class CreateCustomer
 */
@WebServlet("/CreateCustomer")
public class CreateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");

		Response<Customer> customerResponse = null;
		CustomerCreate customerPayload = new CustomerCreate();
		
		customerPayload.email=CustomerHelper.createRandomEmail();
		customerPayload.name=CustomerHelper.generateName();
		customerPayload.phoneNumber="42213121226";		
				
		
			customerResponse = ckoClient.CustomerService.createCustomer(customerPayload);
			Gson gson=new Gson();
			String s=gson.toJson(customerResponse.getType());
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>CreateCustomer:</p>");
			out.println("<p>" + s+ "</p>");
			out.println("</body>");
			out.println("</html>");
		
	}

}
