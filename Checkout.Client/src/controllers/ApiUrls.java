package controllers;

public class ApiUrls {

	public static String CustomersApiUri = AppSettings.baseApiUrl + "/customers";
	public static String ChargesApiUri = AppSettings.baseApiUrl  + "/charges";
	public static String CardChargesApiUri = AppSettings.baseApiUrl  + "/charges/card";
	public static String DefaultCardChargesApiUri = AppSettings.baseApiUrl  + "/charges/customer";
	public static String CardTokenChargesApiUri = AppSettings.baseApiUrl  + "/charges/token";
	public static String LocalPaymentChargesApiUri = AppSettings.baseApiUrl +"/charges/localpayment";
	public static String PaymentTokensApiUri = AppSettings.baseApiUrl  + "/tokens/payment";
	public static String CardTokensApiUri = AppSettings.baseApiUrl  + "/tokens/card";
	public static String CardProvidersListUri = AppSettings.baseApiUrl  + "/providers/cards";

	public static String CustomerApiUri(String para) {
		return AppSettings.baseApiUrl + "/customers/" + para;
	}

	public String CardsApiUri(String para) {
		return AppSettings.baseApiUrl + "/customers/" + para + "/cards";
	}

	public String CardApiUri(String para1, String para2) {
		return AppSettings.baseApiUrl + "/customers/" + para1 + "/cards/" + para2;
	}

	public String UpdateChargesApiUri(String para) {
		return AppSettings.baseApiUrl + "/charges/" + para;
	}

	public String RefundChargeApiUri(String para) {
		return AppSettings.baseApiUrl + "/charges/" + para + "/refund";
	}

	public String CaptureChargesApiUri(String para) {
		return AppSettings.baseApiUrl + "/charges/" + para + "/capture";
	}

	public String CardProvidersUri(String para) {
		return AppSettings.baseApiUrl + "/providers/cards/" + para;
	}

	public String LocalPaymentProvidersUri(String para1, String para2) {
		return AppSettings.baseApiUrl + "/providers/localpayments/" + para1+ "?PaymentToken=" + para2;
	}

	public String LocalPaymentProvidersListUri(String para) {
		return AppSettings.baseApiUrl + "/providers/localpayments?PaymentToken=" + para;
	}

}
