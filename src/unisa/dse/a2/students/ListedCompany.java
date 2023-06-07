package unisa.dse.a2.students;

import java.security.SecureRandom;

/**
 * @author Aoto Iwao
 * @studentID: 110355679
 * @email: iwaay002@mymail.unisa.edu.au>
 */

public class ListedCompany {


	private String name;
	
	public String getName() {
		return this.name;
	}


	private String code;
	
	public String getCode() {
		return this.code;
	}


	private int currentPrice;
	
	public int getCurrentPrice() {
		return this.currentPrice;
	}
	
	//Create a new ListedCompany object.
	public ListedCompany(String code, String name, int currentPrice)
	{
		this.code = code;
		this.name = name;
		this.currentPrice = currentPrice;
	}
	
	//It takes the volume of transactions (QUANTITY) as an argument and updates the current price of the company's shares 
	//based on that volume. Specifically, the quantity of transactions divided by 100 is added to the current price. 
	//However, if this result is less than 1, the price is set to 1. The updated price is then returned.
	public int processTrade(int quantity)
	{
		int updateCurrentPrice = this.currentPrice + (quantity/100);
		this.currentPrice = updateCurrentPrice;
		if (updateCurrentPrice< 1) {
			this.currentPrice = 1;
		}
		return currentPrice;
		

	}
}
