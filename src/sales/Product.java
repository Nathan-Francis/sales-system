package sales;

public class Product implements Saleable
{
	private int price; // Stores the price of the product
	private int weight; // Stores the weight of the product
	
	private static final int DELIVERY_FREE = 100; // Weight limit for free delivery
	private static final int DELIVERY_PAID = 1000; // Weight limit for paid delivery
	private static final double RATE_LOWER = 0.1; // Multiplication factor used for cost
	private static final double RATE_UPPER = 0.2; // Multiplication factor used for cost
	
	// Constructor initialising the price and weight of the product
	public Product(int price, int weight)
	{
		this.price = price;
		this.weight = weight;
	}
	
	// Return the price of the product, from the Saleable interface getPrice() method
	@Override
	public int getPrice()
	{
		return price;
	}
	
	// Return the weight of the product
	public int getWeight()
	{
		return weight;
	}
	
	// Return the total cost of delivery
	public int calculateDelivery()
	{
		if (weight < DELIVERY_FREE) return 0;
		else if (weight < DELIVERY_PAID) return (int) (weight * RATE_UPPER);
		else
		{
			// Charge the first 'X' number of grams (DELIVERY_PAID) at the upper rate
			int firstThousand = (int) (DELIVERY_PAID * RATE_UPPER);
			// Charge the the remaining weight at the lower rate
			int remainingWeight = (int) ((weight - DELIVERY_PAID) * RATE_LOWER);
			return firstThousand + remainingWeight;
		}
	}
}
