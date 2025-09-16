//Owner class --> manages books and customers 
package coe528project;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Owner{
    private String password;
    private String username;
    
    // Making the list of books public and static 
    public static ArrayList<Book> books = new ArrayList<>();
    // making the list of customers public and static 
    public static ArrayList<Customer> customers = new ArrayList<>(); 

    // Constructor to set default credentials
    public Owner() {
        this.password = "admin";
        this.username = "admin";
    }

    // Delete a given customer 
    public void deleteCustomer(Customer c) {
        customers.remove(c);
    }

    public void addBook(Book book) {
        if (!Owner.books.contains(book)) {  //  Prevent duplicate books
            Owner.books.add(book);
            System.out.println("you have added a new book!");
        } else {
            System.out.println("You already have the book"); 
        }
    }


    // Delete a book from the list
    public void deleteBook(Book b) {
        books.remove(b);
    }

    // Add a customer to the list
    public void addCustomer(Customer c) {
        if(!Owner.customers.contains(c)){
            Owner.customers.add(c); 
        }
    }

    // Getter methods
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    //find a customer given their name
    public Customer findCustomer(String username){
        for(Customer c : Owner.customers){
            if(c.getUsername().equals(username)){
                return c; 
            }
        }
        System.out.println("Customer not found");
        return null; 
    }
    
    //saving and loading methods
    public void saveCustomersToFile() {
        try (FileWriter writer = new FileWriter("customers.txt")) {
            for (Customer c : Owner.customers) {
                writer.write(c.getUsername() + "," + c.getPassword() + "," + c.getPoints() + "\n");
            }
            System.out.println("Customers saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    

   public void loadCustomersFromFile() {
        File file = new File("customers.txt");

        if (!file.exists()) {
            System.out.println("No customers to load. The file doesn't exist.");
            Owner.customers = new ArrayList<>();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Owner.customers.clear(); // Clear the list before loading
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    int points = Integer.parseInt(parts[2]);

                    Customer c = new Customer(username, password);
                    c.setPoints(points);  
                    Owner.customers.add(c);
                }
            }
            System.out.println("Customers loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }

     
    public void saveBooksToFile() {
        try (FileWriter writer = new FileWriter("books.txt")) {
            for (Book b : Owner.books) {
                writer.write(b.getName() + "," + b.getPrice() + "\n");
            }
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    
    public void loadBooksFromFile() {
        File file = new File("books.txt");

        if (!file.exists()) {
            System.out.println("No books to load. The file doesn't exist.");
            Owner.books = new ArrayList<>();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Owner.books.clear(); // Clear previous data before loading

            while ((line = reader.readLine()) != null) {
                System.out.println("Reading line: " + line);
                String[] parts = line.split(",");

                if (parts.length != 2) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String name = parts[0];

                try {
                    int price = Integer.parseInt(parts[1]);  // Parse price as int
                    Book b = new Book(name, price);
                    Owner.books.add(b);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price in line: " + line);
                }
            }
            System.out.println("Books loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        // Debugging: Print the books loaded
        System.out.println("Books currently in Owner.books:");
        for (Book book : Owner.books) {
            System.out.println(book.getName() + " - $" + book.getPrice());
        }
   
    }
}


