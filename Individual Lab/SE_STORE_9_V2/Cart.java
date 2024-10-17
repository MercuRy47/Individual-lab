package SE_STORE_9_V2;

public class Cart {
    private String userID;
    private String productID;
    private int quantity;

    public Cart(){
        userID = null;
        productID = null;
        quantity = 0;
    }

    public Cart(String userID, String productID, int quantity){
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getuserID() {
        return userID;
    }
    public String getIdProduct() {
        return productID;
    }
    public int getQuantity() {
        return quantity;
    }
    
    public void setuserID(String userID) {
        this.userID = userID;
    }
    public void setIdProduct(String productID) {
        this.productID = productID;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}