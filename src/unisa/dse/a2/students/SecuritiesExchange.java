package unisa.dse.a2.students;

import java.util.HashMap;


import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author Aoto Iwao
 * @studentID: 110355679
 * @email: iwaay002@mymail.unisa.edu.au>
 */

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	
	//Initialises the exchange ready to handle brokers, 
	//announcements, and companies
	public SecuritiesExchange(String name)
	{
		this.name = name;
		this.brokers = new DSEListGeneric<StockBroker>();
		this.announcements = new DSEListGeneric<String>();
		this.companies = new HashMap<String, ListedCompany>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company)
	{
		//if company is null, return null.
		if (company == null) {
			return false;
		}
		
		//false if it was not
		//if the code is already present in the companies map.
		if (companies.containsKey(company.getCode())) {
			return false;
		}
		
		//add companycode and its company.
		companies.put(company.getCode(), company);
		return true;
	}
	
	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker)
	{
		//Adds the given broke to the list of brokers on the exchange by add method.
		if (broker == null) {
			return false;
			
		}
		
		// for each loop is to check broker is already exist or not.
		for(int i = 0; i < brokers.size(); i++){
			
			StockBroker existingBroker = brokers.get(i);
			if(existingBroker.getName().equals(broker.getName())){
				// If broker already exists, return false.
				return false;
				
			}
		}
		return this.brokers.add(broker);
	
	}
	
	public int processTradeRound() throws UntradedCompanyException
	{
		int successfulTradeNumber = 0;
		for (int i = 0; i < brokers.size(); i++ ) {
			//get broker.
			//get() takes an index.
			StockBroker broker = brokers.get(i);
			//Process the next trade provided by each broker.
			Trade trade = broker.getNextTrade();
			
			// null check.
			//If a broker has no pending trades, that broker is skipped. 
			//so if trade (broker.getNextTrade()) is null, the code inside of if statement will not work.
			if (trade != null) {
				//If the exchange has three brokers, each with trades in their queue, 
				//then three trades will processed, one from each broker.
				String companyCode = trade.getCompanyCode();
				
				//UntradedCompanyException when traded company is not listed on this exchange
				if (companyCode== null || !companies.containsKey(companyCode)) {
	                throw new UntradedCompanyException(companyCode);
	            }
				ListedCompany company = companies.get(companyCode);
				int priceBeforeTrade = company.getCurrentPrice();
				company.processTrade(trade.getShareQuantity());
				// if trade is successful, increasing successfulTradeNumber.
			
				successfulTradeNumber++;
			
				
				//"Trade: 100 DALL @ 99 via Honest Harry Broking"
				//"Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
				String announcementString = "Trade: " + trade.getShareQuantity() + " " + trade.getCompanyCode() + " @ " + priceBeforeTrade + " via " + broker.getName();

				this.announcements.add(announcementString);
			}
		
			
			
		}
		return successfulTradeNumber;
	}
	

	//public int runCommandLineExchange(Scanner sc)


}
