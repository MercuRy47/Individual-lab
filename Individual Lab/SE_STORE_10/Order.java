package SE_STORE_10;

public class Order {
    private String orderID;
    private String userID;
    private String productID;
    private int quantity;
    private double priceOnOrder;

    public Order(){
        orderID = "8000";
        userID = "9000";
        productID = "1000";
        quantity = 0;
        priceOnOrder = 0.0;
    }

    public Order(String orderID, String userID, String productID, int quantity, double priceOnOrder){
        this.orderID = orderID;
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.priceOnOrder = priceOnOrder;
    }

    public String getOrderID(){
        return orderID;
    }
    public String getuserID(){
        return userID;
    }
    public String getproductID(){
        return productID;
    }
    public int getquantity(){
        return quantity;
    }
    public double getpriceOnOrder(){
        return priceOnOrder;
    }

    public void setOrderID(String orderID){
        this.orderID = orderID;
    }
    public void setuserID(String userID){
        this.userID = userID;
    }
    public void setproductID(String productID){
        this.productID = productID;
    }
    public void setquantity(int quantity){
        this.quantity = quantity;
    }
    public void setpriceOnOrder(double priceOnOrder){
        this.priceOnOrder = priceOnOrder;
    }
}
