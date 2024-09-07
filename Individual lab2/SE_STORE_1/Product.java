package SE_STORE_1;
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(){
        this.id = "";
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Product(String id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }
}
