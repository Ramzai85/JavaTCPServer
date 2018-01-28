package ru.antonm.beelineservice;

import org.json.JSONException;

public class SimCardManager {
	public static void main(String[] args) throws JSONException{
		SimCard simCard = new SimCard("8970199170384027759");
		simCard.setCurrentIMEI();
		
		}
}