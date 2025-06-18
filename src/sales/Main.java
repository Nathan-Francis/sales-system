package sales;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Initialise scanner
        Scanner scanner = new Scanner(System.in);

        // Customer list to store customer objects
        List<Customer> customers = new ArrayList<>();

        // Program running flag
        boolean programRunning = true;

        // Main program loop
        // The program will continue to run until the user chooses to exit
        while (programRunning)
        {
            // Display the main menu and get user input
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            // User input handler
            switch (choice)
            {
                case 1:
                    // Add a new customer
                    addCustomer(scanner, customers);
                    break;

                case 2:
                    // Record a purchase
                    recordPurchase(scanner, customers);
                    break;

                case 3:
                    // Record a refund
                    recordRefund(scanner, customers);
                    break;

                case 4:
                    // View customer transactions
                    viewCustomerTransactions(scanner, customers);
                    break;

                case 5:
                    // Exit the program
                    programRunning = false;
                    System.out.println("\nGoodbye!");
                    break;

                default:
                    // Invalid choice handler
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
    
    // Main Menu displaying options to the user
    private static void displayMainMenu()
    {
        System.out.println("\nMain Menu");
        System.out.println("1. Add Customer");
        System.out.println("2. Record Purchase");
        System.out.println("3. Record Refund");
        System.out.println("4. View Customer Transactions");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /*
    * Add new customer to the list
    * Customer created with a unique ID and added to customers list
    * User prompted to enter the customer name
    * Customer ID automatically generated and displayed to the user
    */
    private static void addCustomer(Scanner scanner, List<Customer> customers)
    {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        Customer customer = new Customer(name);
        customers.add(customer); // Adds to list of customers
        System.out.println("\nCustomer added successfully with ID: " + customer.getCustomerId());
    }

    /* 
    * Record a purchase for a specific customer
    * User prompted to enter customer ID, product/service details, and quantity
    * The purchase is created and added to customers transactions
    * Total cost calculated based on the product or service price and quantity
    */
    private static void recordPurchase(Scanner scanner, List<Customer> customers)
    {
    	// Error handling
        if (customers.isEmpty())
        {
            System.out.println("\nNo customers available. Select option 1 to add a Customer.");
            return;
        }

        // User prompted to enter customer ID
        Customer customer = findCustomerById(scanner, customers);
        
        // Error handling
        if (customer == null)
        {
            System.out.println("\nInvalid customer ID.");
            return;
        }

        System.out.println("\nIs this a product or a service?");
        System.out.println("1. Product");
        System.out.println("2. Service");

        int choice = scanner.nextInt();

        // Decision handler for Product or Service
        switch (choice)
        {
            case 1:
                handleProductPurchase(scanner, customer);
                break;

            case 2:
                handleService(scanner, customer);
                break;
                
            default:
                System.out.println("\nInvalid choice.");
        }
    }

    // User prompted to enter product price, weight, and quantity
    private static void handleProductPurchase(Scanner scanner, Customer customer)
    {
        System.out.print("Enter product price: ");
        int price = scanner.nextInt();
    
        System.out.print("Enter product weight: ");
        int weight = scanner.nextInt();
    
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
    
        Product product = new Product(price, weight); // Creates purchase object
        Purchase purchase = new Purchase(product, quantity); // Calculates cost of product + delivery
        customer.transact(purchase); // Adds to list of customer transactions
        System.out.println("\nProduct purchase recorded successfully.");
    }
    

    // User prompted to enter service price (no weight or quantity)
    private static void handleService(Scanner scanner, Customer customer)
    {
        System.out.print("Enter service price: ");
        int price = scanner.nextInt();
    
        Service service = new Service(price);
        Purchase purchase = new Purchase(service, 1); // Quantity is always 1 for services
        customer.transact(purchase); // Adds to list of customer transactions
        System.out.println("\nService purchase recorded successfully.");
    }
    
    /*
    * Record refund for specific customer, determined by customer ID
    * User prompted to enter customer ID, refund value, and reason
    * Refund created and added to customers transactions
    */
    private static void recordRefund(Scanner scanner, List<Customer> customers)
    {
    	// Error handling
        if (customers.isEmpty())
        {
            System.out.println("\nNo customers available. Select option 1 to add a Customer.");
            return;
        }

        // User prompted to enter customer ID
        Customer customer = findCustomerById(scanner, customers);
        if (customer == null) return;

        // Error handling
        if (customer.getTransactions().isEmpty())
        {
            System.out.println("\nThis customer has no recorded transactions. Refund not permitted.");
            return;
        }

        System.out.print("Enter refund value: ");
        int refundValue = scanner.nextInt();
        scanner.nextLine();

        // Prevents giving customer more than they paid
        if (refundValue > customer.getTotal())
        {
            System.out.println("\nRefund value exceeds the customer account balance. Refund not permitted.");
            return;
        }

        System.out.print("Enter refund reason: ");
        String reason = scanner.nextLine();
        Refund refund = new Refund(refundValue, reason);
        customer.transact(refund); // Adds to list of customer transactions
        System.out.println("\nRefund successful.");
    }

    // View all transactions for a customer, including purchases and refunds
    private static void viewCustomerTransactions(Scanner scanner, List<Customer> customers)
    {
    	// Error handling
        if (customers.isEmpty())
        {
            System.out.println("\nNo customers available. Select option 1 to add a Customer.");
            return;
        }

        // Locate the customer based on their unique ID
        Customer customer = findCustomerById(scanner, customers);
        if (customer == null) return;

        System.out.println("\nCustomer Transactions:");
        
        // Iterate over customer transactions list
        for (Transaction transaction : customer.getTransactions())
        {
        	// Verify if transaction is instance of the Purchase or Refund class
            if (transaction instanceof Purchase)
            {
            	// Type cast transaction to the Purchase Class to enable Purchase Class methods
                Purchase p = (Purchase) transaction;
                System.out.println("Purchase - Item Price: " + p.getItem().getPrice() + ", Quantity: " + p.getQuantity()
                        + ", Total: " + p.getValue());
            } 
            else if (transaction instanceof Refund)
            {
            	// Type cast transaction to the Refund Class to enable Refund Class methods
                Refund r = (Refund) transaction;
                System.out.println("Refund - Value: " + r.getValue() + ", Reason: " + r.getReason());
            }
        }
        
        System.out.println("Total Balance: " + customer.getTotal());
    }
    
    // Returns the customer object if found, otherwise returns null
    private static Customer findCustomerById(Scanner scanner, List<Customer> customers)
    {
    	// User prompt for Customer ID
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        // Iterates over customers list and returns if customer ID found
        for (Customer customer : customers)
        {
            if (customer.getCustomerId() == customerId)
            {
                return customer;
            }
        }

        System.out.println("\nInvalid customer ID.");
        return null;
    }
}
