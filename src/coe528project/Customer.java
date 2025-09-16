//customer class 
package coe528project;
import java.io.Serializable; 

import java.util.ArrayList;

public class Customer implements Serializable {
    // Instance variables
    private int points; 
    private ArrayList<Book> cart; 
    private String username; 
    private String password; 
    private String status; 
    private int totalPrice; 

    // Constructor
    public Customer(String username, String password) {
        this.username = username; 
        this.password = password; 
        this.points = 0; 
        this.status = "Silver"; 
        this.cart = new ArrayList<>(); 
    }

    // Update status
    public void updateStatus() {
        if (points < 1000) {
            status = "Silver"; 
        } else {
            status = "Gold"; 
        }
    }

    // Add to cart
    public void addToCart(Book b) {
        cart.add(b);
    }

    // Buy everything in cart
   public void buy() {
       int purchaseTotal = 0; 
       ArrayList<Book> booksToRemove = (cart);
       
        for (Book b : booksToRemove) {
            Owner.books.remove(b);  // Correct way to reference the book list
            purchaseTotal += b.getPrice();
            System.out.println("item price: " +b.getPrice() );
            
        }
        System.out.println("Total price: " + purchaseTotal );
        totalPrice += purchaseTotal; 
        points += purchaseTotal * 10;  // Given point-to-CAD rate --> 10:1
        updateStatus();
        cart.clear();
        
        
    }

    //reddeem points and buy --> this does not add to points
    public void redeemAndBuy() {
        int totalCartPrice = 0; 
        
        ArrayList<Book> booksToRemove = (cart); 
        
        //calculate total cart price
        for(Book b : booksToRemove){
            totalCartPrice += b.getPrice(); 
        }
        
        //calculate discount --> 100 points = 1 CAD
        int discount = points / 100; 
        
        //make sure it doesn't go negative 
        int finalPrice = Math.max(0, totalCartPrice - discount); 
        
        //deduct the amount of points used 
        int pointsUsed = (totalCartPrice - finalPrice) * 100; 
        points -= pointsUsed; 
        
        //update total spent and customer status
        totalPrice += finalPrice; 
        
        //remove books from stock after calculating total 
        for(Book b : booksToRemove){
            Owner.books.remove(b); 
        }
        updateStatus(); 
        
        //clear the cart
        cart.clear();
    }
    
    
    //reset the total to 0
    public void resetTotal(){
        totalPrice = 0; 
    }

    // Getter methods
    public int getTotalPrice() {
        return totalPrice; 
    }
    
    public int getPoints(){
        return points; 
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status; 
    }
    
    //setter methods 
    public void setPoints(int points){
        this.points = points; 
    }
}
