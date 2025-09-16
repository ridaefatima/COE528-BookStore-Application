//the purpose of this file is to set the Book class along with all its properties and functions

//the package
package coe528project;

//imports
import java.io.Serializable;


public class Book implements Serializable {
    //instance variables 
    private static final long serialVersionUID = 1L;  // Add a serialVersionUID
    private String name; 
    private int price; 

    //intiating the instance variables through the contructor 
    public Book(String name, int price){
        this.name = name; 
        this.price = price; 
    }

    //getter and setter methods
    public String getName(){
        return name; 
    }

    public int getPrice(){
        return price; 
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price; 
    }
}
