package sales;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest 
{
    private Customer customer;

    @BeforeEach
    public void setUp()
    {
        customer = new Customer("Customer");
    }

    @Test
    public void totalNoTransactions()
    {
        assertEquals(0, customer.getTotal()); // Total: 0
    }

    @Test
    public void totalPurchaseOnly()
    {
        Product product1 = new Product(50, 50); // Price: 50, Weight: 50, Delivery: Free
        Product product2 = new Product(100, 200); // Price: 100, Weight: 200, Delivery: 40
        Product product3 = new Product(200, 2000); // Price: 200, Weight: 2000, Delivery: 300
        customer.transact(new Purchase(product1, 2)); // 50 * 2 = 100
        customer.transact(new Purchase(product2, 3)); // (100 * 3) + 40 = 340
        customer.transact(new Purchase(product3, 1)); // (200 * 1) + 300 = 500
        assertEquals(940, customer.getTotal()); // Total: 100 + 340 + 500 = 940
    }

    @Test
    public void totalServiceOnly()
    {
        Service service = new Service(200); // Price: 200
        customer.transact(new Purchase(service, 1)); // 200 * 1 = 200
        assertEquals(200, customer.getTotal()); // Total: 200
    }

    @Test
    public void totalRefundOnly()
    {
        customer.transact(new Refund(100, "Reason")); // -100
        customer.transact(new Refund(200, "Reason")); // -200
        assertEquals(-300, customer.getTotal()); // Total: -100 - 200 = -300
    }

    @Test
    public void totalPurchaseRefund()
    {
        Product product = new Product(100, 200); // Price: 100, Weight: 200, Delivery: 40
        customer.transact(new Purchase(product, 2)); // (100 * 2) + 40 = 240
        customer.transact(new Refund(50, "Reason")); // -50
        assertEquals(190, customer.getTotal()); // Total: 240 - 50 = 190
    }

    @Test
    public void totalServiceRefund()
    {
        Service service = new Service(300); // Price: 300
        customer.transact(new Purchase(service, 1)); // 300 * 1 = 300
        customer.transact(new Refund(50, "Reason")); // -50
        assertEquals(250, customer.getTotal()); // Total: 300 - 50 = 250
    }

    @Test
    public void totalPurchaseService()
    {
        Product product = new Product(100, 200); // Price: 100, Weight: 200, Delivery: 40
        Service service = new Service(300); // Price: 300
        customer.transact(new Purchase(product, 2)); // (100 * 2) + 40 = 240
        customer.transact(new Purchase(service, 1)); // 300 * 1 = 300
        assertEquals(540, customer.getTotal()); // Total: 240 + 300 = 540
    }

    @Test
    public void totalPurchaseServiceRefund()
    {
        Product product = new Product(100, 200); // Price: 100, Weight: 200, Delivery: 40
        Service service = new Service(300); // Price: 300
        customer.transact(new Purchase(product, 2)); // (100 * 2) + 40 = 240
        customer.transact(new Purchase(service, 1)); // 300 * 1 = 300
        customer.transact(new Refund(50, "Reason")); // -50
        assertEquals(490, customer.getTotal()); // Total: 540 - 50 = 490
    }
}
