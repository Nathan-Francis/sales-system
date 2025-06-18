# Sales System

A simple Java console application for managing customers, purchases, and refunds. The system allows you to add customers, record purchases of products or services, process refunds, and view customer transaction histories.

## Features

- Add customers with unique IDs
- Record purchases of products (with delivery calculation) or services
- Process refunds with reasons
- View all transactions and total balance for each customer

## Project Structure

```
src/
  sales/
    Customer.java
    CustomerTest.java
    Main.java
    Product.java
    Purchase.java
    Refund.java
    Saleable.java
    Service.java
    Transaction.java
```

## How to Run

From the project root, run:
```sh
java -cp bin sales.Main
```

## Running Tests

This project uses JUnit 5 for unit testing. To run the tests, ensure JUnit 5 is on your classpath and run:

```sh
javac -cp "path/to/junit-platform-console-standalone.jar;bin" src/sales/CustomerTest.java
java -jar path/to/junit-platform-console-standalone.jar --class-path bin --scan-class-path
```

## Class Overview

- [`sales.Main`](src/sales/Main.java): Entry point and user interface.
- [`sales.Customer`](src/sales/Customer.java): Represents a customer and their transactions.
- [`sales.Product`](src/sales/Product.java): Represents a product with price and weight.
- [`sales.Service`](src/sales/Service.java): Represents a service with a price.
- [`sales.Purchase`](src/sales/Purchase.java): Represents a purchase transaction.
- [`sales.Refund`](src/sales/Refund.java): Represents a refund transaction.
- [`sales.Transaction`](src/sales/Transaction.java): Abstract base class for transactions.
- [`sales.Saleable`](src/sales/Saleable.java): Interface for saleable items (products/services).
- [`sales.CustomerTest`](src/sales/CustomerTest.java): Unit tests for customer transactions.

## License

This project is for educational purposes only.  
You may not use, copy, modify, or distribute this code for commercial purposes or personal gain without explicit permission from the author.
