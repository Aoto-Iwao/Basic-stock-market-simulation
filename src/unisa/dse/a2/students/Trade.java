package unisa.dse.a2.students;

public class Trade implements Comparable<Trade> {


	private long tradeId = -1;
	private long created;

	
	public long getCreated()
	{
		return this.created;
	}
	
	public String listedCompanyCode;

	public String getCompanyCode() {
		return this.listedCompanyCode;
	}
	
	private int shareQuantity;

	//The quantity of shares to trade
	public int getShareQuantity() {
		return this.shareQuantity;
	}

	private StockBroker broker;

	public StockBroker getStockBroker() {
		return this.broker;
	}
	
	public Trade(StockBroker broker, int id)
	{
		created = System.nanoTime(); //do not change this
		tradeId = id; //do not change this
		try { Thread.sleep(100); } catch (Exception x) {}
	}
	
	//Create a new trade with the associated broker, company, and share quantity.
	public Trade(StockBroker broker, String listedCompanyCode, int shareQuantity)
	{
		this.broker = broker; // Add this line
	    this.listedCompanyCode = listedCompanyCode; // Add this line
	    this.shareQuantity = shareQuantity; // Add this line
		created = System.nanoTime(); //do not change this
		tradeId = System.nanoTime(); //do not change this
		try { Thread.sleep(100); } catch (Exception x) {}
	}
	
	public int compareTo(Trade t) {
		//create a new list which call from StockBroker class.
		boolean watchList = this.getStockBroker().getWatchlist().contains(listedCompanyCode);
		boolean tWatchlist = t.getStockBroker().getWatchlist().contains(t.listedCompanyCode);
		
		if (watchList && tWatchlist) {
			return 0;
		}
		if (watchList && !tWatchlist) {
			return 1;
		}
		if (!watchList && tWatchlist) {
			return -1;
		}
		//compare this and t (Long) with Long.compare()
		//if neither trade is on their broker's list, then compare the "created" field, 
		//returning -1 if "this" is smaller, 0 if equal, or 1 if greater
		return Long.compare(this.created, t.created);	
	}
	
	@Override
	public String toString() {
		return ""+tradeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeId != other.tradeId)
			return false;
		return true;
	}
	
}
