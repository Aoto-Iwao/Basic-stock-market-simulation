package unisa.dse.a2.students;

import java.util.PriorityQueue;
import java.util.concurrent.CountDownLatch;

public class StockBroker {

	//List of pending transactions to be completed. Stores generic types.
	private PriorityQueue<Trade> pendingTrades = new PriorityQueue<Trade>();
	
	//create watchList.
	private DSEListGeneric<String> watchList = new DSEListGeneric<String>();

	//Returns a DEEP copy of the watch list. Modify the returned list from here.
	public DSEListGeneric<String> getWatchlist() {
		return new DSEListGeneric<String>(watchList);
	}
	
	//Add company code to watchlist. Returns true if added.
	public boolean addWatchlist(String companyCode)
	
	{
		if (companyCode!= null && !watchList.contains(companyCode)) {
			return watchList.add(companyCode);
		}
		return false;
	}

	private String name;

	//get name.
	public String getName() {
		return this.name;
	}
	
	//store the broker's name 
	public StockBroker(String name)
	{
		this.name = name;
	}
	
	//Adds the Trade to the pendingTrades list if it's not null and not already in there
	public boolean placeOrder(Trade order)
	{
		if (order!= null && !pendingTrades.contains(order)) {
			return pendingTrades.add(order);
		}
		return false;
	}
	
	//Gets, removes, and returns the next trade to process
	public Trade getNextTrade()
	{
		return pendingTrades.poll();
	}
	
	//get a number of pending trades.
	public int getPendingTradeCount()
	{
		return pendingTrades.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockBroker other = (StockBroker) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
