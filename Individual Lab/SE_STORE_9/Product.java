package SE_STORE_9;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String categoryId;

    public Product(){
        this.id = "";
        this.name = "";
        this.price = 0;
        this.quantity = 0;
        this.categoryId = "";
    }

    public Product(String id, String name, double price, int quantity, String categoryId){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
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

    public String getCategoryId(){
        return this.categoryId;
    }

    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }
    
}
