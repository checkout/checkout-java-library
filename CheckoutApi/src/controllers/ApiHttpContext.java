package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import apiServices.sharedModels.Response;
import apiServices.sharedModels.ResponseError;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class ApiHttpContext {
	HttpURLConnection connection = null;
	int httpStatus = 0;
	static Logger logger=Logger.getLogger("MyLog"); 
	static FileHandler fh;
	
	public static FileHandler logFile() throws IOException{		  
		SimpleFormatter formatter = new SimpleFormatter();
		fh = new FileHandler("ResponseLog.log");
		fh.setFormatter(formatter);  
		logger.addHandler(fh);	
		return  fh;		
	}
	
	/* when the request content is needed for POST PUT method */
	public void createPostConnection(String uri, String apiKey, String method,
			String query) throws IOException {
		
		URL url = new URL(uri);
		
		
		connection = (HttpURLConnection) url.openConnection();

		connection.setConnectTimeout(AppSettings.connectTimeout * 1000);
		connection.setReadTimeout(AppSettings.readTimeout * 1000);

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod(method); // POST¡¢PUT
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);

		// set http header
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", apiKey);		
		connection.connect();
	
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream());
		out.write(query);
		
		if(AppSettings.debugMode){	
				      
		    logger.info("**Request**  "+method+":"+uri);
		    logger.info("**Payload**:"+query);
		    		    
		}
		
		out.flush();
		out.close();

	}

	/* when the request content is needed for Get Delete method */
	public void createGetConnection(String uri,
			String apiKey, String method) throws IOException {		
		
		URL url = new URL(uri);
		if(AppSettings.debugMode){	
		    logger.info("**Request**  "+method+":"+uri);	
		   
		}
		
		connection = (HttpURLConnection) url.openConnection();

		connection.setConnectTimeout(30 * 1000);
		connection.setReadTimeout(80 * 1000);

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod(method); // GET¡¢DELETE
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);

		// set http header
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", apiKey);
		connection.connect();		

	}

	
	public <T> Response<T> getResponse(Class<T> type) throws SecurityException, IOException  {

		Response<T> jsonResponse = null;
		JSONObject json = null;
		String lines = null;
		T jsonObject = null;
		Gson gson = new Gson();
		BufferedReader reader = null; 
		try {
			httpStatus = connection.getResponseCode();
			
			if (this.httpStatus == 200) {
				reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				while ((lines = reader.readLine()) != null) {
					json = JSONObject.fromObject(lines);
				}				
				if(AppSettings.debugMode){	
				    logger.info("** HttpResponse - Status 200 OK**:"+json+"\n");					
				}
				
				jsonObject = gson.fromJson(json.toString(),type);
				
				jsonResponse = new Response<T>(jsonObject);
				jsonResponse.setHttpStatus(this.httpStatus);				
			} else{
								
				reader = new BufferedReader(new InputStreamReader(
						connection.getErrorStream()));
				while ((lines = reader.readLine()) != null) {
					json = JSONObject.fromObject(lines);
				}
							
				ResponseError error = gson.fromJson(json.toString(),
						ResponseError.class);

				jsonResponse = new Response<T>(jsonObject);

				jsonResponse.setError(error);
				jsonResponse.setHasError(true);
				jsonResponse.setHttpStatus(this.httpStatus);
				if(AppSettings.debugMode){	
				    logger.info("** HttpResponse --StatusError:"+jsonResponse.getHttpStatus()+json);			
				}				
				reader.close();
			}
		} catch (IOException e) {
			}
		 finally {
			reader=null;
			connection.disconnect();
		}		

		return jsonResponse;
	}

}