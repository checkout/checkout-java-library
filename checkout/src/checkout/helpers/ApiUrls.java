package checkout.helpers;

public class ApiUrls {

	public static String ChargesApiUri = String.format(AppSettings.baseApiUrl, "charges");
	public static String PaymentTokensApiUri = String.format(AppSettings.baseApiUrl, "tokens/payment");
}
