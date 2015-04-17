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
import test.ChargeHelper;
import apiServices.charges.request.ChargeRefund;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;

/**
 * Servlet implementation class RefundCardCharge
 */
@WebServlet("/RefundCardCharge")
public class RefundCardCharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefundCardCharge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
			Response<Charge> chargesResponse=ChargeHelper.chargeWithFullCardRequest();
			
			String charge_id=chargesResponse.getType().id;
			
			ChargeRefund Payload=new ChargeRefund();
			Payload.chargeId=charge_id;
			Payload.value=100;
			
			Response<Charge> Response=ckoClient.ChargeService.refundCardChargeRequest(Payload);
			
			String s="";
			Gson gson=new Gson();
			if(Response.getHttpStatus()==200){
				 s=gson.toJson(Response.getType());
			}else{				
				s=gson.toJson(Response);
			}
			
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<p>RefundCardCharge:</p>");
			out.println("<p>" + s+ "</p>");
			out.println("</body>");
			out.println("</html>");
		
	}

}
