package controllers;

public class ApiUrls  {

	public static String CustomersApiUri = String.format(AppSettings.baseApiUrl, "customers");
	public static String CustomerApiUri = String.format(AppSettings.baseApiUrl, "customers/%s");
	
	public static String CardsApiUri=String.format(AppSettings.baseApiUrl,"customers/%s/cards");
	public static String CardApiUri=String.format(AppSettings.baseApiUrl,"customers/%s/cards/%s");
	public static String CardChargesApiUri = String.format(AppSettings.baseApiUrl, "charges/card");
	
	public static String DefaultCardChargesApiUri = String.format(AppSettings.baseApiUrl, "charges/customer");
	public static String CardTokenChargesApiUri = String.format(AppSettings.baseApiUrl, "charges/token");
	public static String LocalPaymentChargesApiUri = String.format(AppSettings.baseApiUrl,"charges/localpayment");
	public static String UpdateChargesApiUri=String.format(AppSettings.baseApiUrl,"charges/%s");
	public static String RefundChargeApiUri=String.format(AppSettings.baseApiUrl,"charges/%s/refund");
	public static String CaptureChargesApiUri=String.format(AppSettings.baseApiUrl,"charges/%s/capture");

	public static String PaymentTokensApiUri = String.format(AppSettings.baseApiUrl, "tokens/payment");
	public static String CardTokensApiUri = String.format(AppSettings.baseApiUrl, "tokens/card");
		
	public static String CardProvidersUri=String.format(AppSettings.baseApiUrl,"providers/cards/%s");
	public static String CardProvidersListUri = String.format(AppSettings.baseApiUrl, "providers/cards");
	public static String LocalPaymentProvidersUri=String.format(AppSettings.baseApiUrl,"providers/localpayments/%s?PaymentToken=%s");
	public static String LocalPaymentProvidersListUri=String.format(AppSettings.baseApiUrl,"providers/localpayments?PaymentToken=%s");

}
