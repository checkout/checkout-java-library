package apiServices.cards.request;

import java.util.ArrayList;

import apiServices.cards.response.Card;

public class BaseCards {
	public Card card;
	public String customerId;
	public String cardId;
	
	public ArrayList<String> errorList=new ArrayList<String>();
	
}
