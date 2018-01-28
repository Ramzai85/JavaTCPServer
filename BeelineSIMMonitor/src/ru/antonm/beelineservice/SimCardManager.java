package ru.antonm.beelineservice;

import org.json.JSONException;

public class SimCardManager {
	public static void main(String[] args) throws JSONException{
		SimCard simCard = new SimCard("8970199170384027759");
		simCard.setCurrentIMEI();
		simCard.setStatus();
		
		String currentIMEI = simCard.getCurrentIMEI();
		String status = simCard.getStatus();
		System.out.println(currentIMEI);
		System.out.println(status);		
		}
}