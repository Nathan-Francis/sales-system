package sales;

import java.util.List;
import java.util.ArrayList;

public class Customer
{
	private String name; // Name of the customer
	private List<Transaction> transactions = new ArrayList<>(); // List of transactions for a customer
	private static int customerIdCounter = 1; // Tracks the next unique customer ID
	private int customerId; // Unique customer ID
	
	// Constructor initialising a new customer with a unique ID
	public Customer(String name)
	{
		this.name = name;
		this.customerId = customerIdCounter++;
	}
	
	// Getter for the unique customer ID
	public int getCustomerId()
	{
		return customerId;
	}

	// Getter for the customers name
    public String getName()
    {
        return name;
    }
	
	// Add a transaction to the customers transaction array
	public void transact(Transaction transaction)
	{
		transactions.add(transaction);
	}
	
	// Returns the total value of all transactions for the customer
	public int getTotal()
	{
		int total = 0;
		
		// Iterate through transaction array and assign the sum of values to 'total'
		for (Transaction transaction : transactions)
		{
			total += transaction.getValue();
		}

		return total;
	}
	
	// Returns the list of transactions for the customer
	public List<Transaction> getTransactions()
	{
		return transactions;
	}
}
