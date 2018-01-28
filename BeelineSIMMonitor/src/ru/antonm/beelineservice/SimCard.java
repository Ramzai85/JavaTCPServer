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

	public String getCurrentIMEI() {
		return currentIMEI;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus() {
		String requestURL = BeelineCredentials.weburl + this.iccid + "/ctdUsages";
		try {
			String authString = BeelineCredentials.login + ":" + BeelineCredentials.password;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);

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

			}
			String result = sb.toString();
			//System.out.println(result);
			JSONObject jsonObj = new JSONObject(result);
			this.status = (String) jsonObj.get("status");

			// System.out.println("IMEI is: " + jsonObj.get("imei"));

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} 

	};

	public void setCurrentIMEI() {
		String requestURL = BeelineCredentials.weburl + this.iccid + "/ctdUsages";
		try {
			String authString = BeelineCredentials.login + ":" + BeelineCredentials.password;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);

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

			}
			String result = sb.toString();
			//System.out.println(result);
			JSONObject jsonObj = new JSONObject(result);
			this.currentIMEI = (String) jsonObj.get("imei");
			// System.out.println("IMEI is: " + jsonObj.get("imei"));

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	};

	public void updateStatus() {
	};

	public SimCard(String iccid) {
		this.iccid = iccid;
	}
}
