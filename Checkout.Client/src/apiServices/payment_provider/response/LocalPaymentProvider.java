package apiServices.payment_provider.response;

import java.util.List;
import java.util.Map;

public class LocalPaymentProvider {
	 public String id;
	 public String type;
	 public String name;
	 public boolean iframe;
	 public List<Region> regions;
	 public String[] countrycodes;
	 public Map<String,String> dimensions;	
	 public List<CustomFields> customfields;
}
