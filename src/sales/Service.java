package sales;

public class Service implements Saleable
{
	private int price;
	
	// Constructor initialising the price of a service
	public Service(int price)
	{
		this.price = price;
	}
	
	// Returns the price of the service
	@Override
	public int getPrice()
	{
		return price;
	}
}
