package ru.antonm.beelineservice;

import org.json.JSONException;

public class SomeClass {
	public static void main(String[] args) throws JSONException{
		BeelineConnector connector = new BeelineConnector("8970199170384027759");
		connector.getLastIMEI();
		}
}