package com.checkout.api.services.charge.response;

import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.charge.request.BaseCharge;

public class Charge extends BaseCharge {
	public String id;
	public String liveMode;
	public String created;
	public String transactionIndicator;
	public Card card;
	public String responseMessage;
	public String responseAdvancedInfo;
	public String responseCode;
	public String status;
	public boolean isCascaded;
	public String authCode;

	/*
	 * 3D Secure charge verification specific fields
	*/
	public String enrolled;
	public String signatureValid;
	public String authenticationResponse;
	public String eci;
	public String cavv;
	public String xid;
}
