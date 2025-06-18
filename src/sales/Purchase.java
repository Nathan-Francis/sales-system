package sales;

public class Purchase extends Transaction
{
	private Saleable item;
	private int quantity;
	
	// Constructor for Purchase object with item and quantity parameters
	public Purchase(Saleable item, int quantity)
	{
		this.item = item; // The item being purchased
		this.quantity = quantity; // Quantity of item being purchased
		
		// Calculate total cost of purchase
        if (item instanceof Product)
        {
            Product product = (Product) item;
            value = item.getPrice() * quantity + product.calculateDelivery();
        }
        else
        {
            value = item.getPrice() * quantity; // No delivery cost for services
        }
	}
	
	// Returns the product or service being purchased
	public Saleable getItem()
	{
		return item;
	}
	
	// Returns the quantity of the product being purchased
	public int getQuantity()
	{
		return quantity;
	}
}
