package ru.antonm.beelineservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

public class SimCard {
	private String iccid;
	private String currentIMEI;
	private String bindedIMEI;
	private String status;

	public void setCurrentIMEI() {
		String requestURL = BeelineCredentials.weburl + this.iccid + "/ctdUsages";
		try {
			String authString = BeelineCredentials.login + ":" + BeelineCredentials.password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);

			URL url = new URL(requestURL);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			
			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
//				System.out.println(i++);
			}
			String result = sb.toString();

			JSONObject jsonObj = new JSONObject(result);
			System.out.println("IMEI is: " + jsonObj.get("imei"));
			
			
			
			System.out.println("*** BEGIN ***");
			//System.out.println(result);
			System.out.println("*** END ***");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (JSONException e){
			e.printStackTrace();
		}

	};

	public void updateStatus() {
	};

	public SimCard(String iccid) {
		this.iccid = iccid;
	}
}
