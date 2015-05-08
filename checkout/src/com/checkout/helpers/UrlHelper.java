package com.checkout.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlHelper {

	 public static String addParameterToUrl(String url, String parameterName, String parameterValue)
	  {
	    int qpos = url.indexOf('?');
	    int hpos = url.indexOf('#');
	    char sep = qpos == -1 ? '?' : '&';
	    String segment = sep + encodeUrl(parameterName) + '=' + encodeUrl(parameterValue);
	    return hpos == -1 ? url + segment : url.substring(0, hpos) + segment + url.substring(hpos);
	  }

	  public static String encodeUrl(String url)
	  {
	    try
	    {
	      return URLEncoder.encode(url, "UTF-8");
	    }
	    catch (UnsupportedEncodingException uee)
	    {
	      throw new IllegalArgumentException(uee);
	    }
	  }

	
}