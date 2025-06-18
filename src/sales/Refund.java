package sales;

public class Refund extends Transaction
{
	private String reason;

	public Refund(int value, String reason)
	{
		this.value = value > 0 ? -value : value; // Converts refunds into negative values
		this.reason = reason; // Reason for the refund
	}

	// Returns the reason for the refund
	public String getReason()
	{
		return reason;
	}
}
