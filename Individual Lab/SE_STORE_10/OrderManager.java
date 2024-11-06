package SE_STORE_10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OrderManager {
    public Order[] orders;
    private double totalProduct;
    private double total;

    public List<String> dataOrder = new ArrayList<>();

    // Path
    String pathOrder = "Individual Lab/SE_STORE_10/ORDER.txt";

    // โหลดข้อมูล Order ทั้งหมดจากไฟล์
    public void loadOrderFromFile(){
        this.dataOrder = new ArrayList<>();
        try(BufferedReader readFile = new BufferedReader(new FileReader(pathOrder))){
            String line;
            while((line = readFile.readLine()) != null) {
                dataOrder.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        orders = new Order[dataOrder.size()];
        for(int i = 0; i < dataOrder.size(); i++) {
            String[] parts = dataOrder.get(i).split("\\s+");
            orders[i] = new Order(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4].replace("$", "")));
        }
    }

    public void printAllOrder(String userIDCurrent){
        StoreManager storeManager = new StoreManager();
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/PRODUCT.txt");
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/CART.txt");
        this.total = 0;

        String format = "%-5s %-12s %-10s %-10s%n";
        System.out.printf(format, "#", "Name", "Quantity", "Totals (฿)");
        int index = 1;
        for(Cart cart: storeManager.carts){
            if(cart.getuserID().equalsIgnoreCase(userIDCurrent)){
                int index_productCurrent = 0;
                for(Product product : storeManager.products) {
                    if(product.getId().equalsIgnoreCase(cart.getIdProduct())){
                        this.totalProduct = cart.getQuantity() * product.getPrice();
                        System.out.printf("%-5d %-12s %-10d %-10.2f%n", index, storeManager.products[index_productCurrent].getName(), cart.getQuantity(), this.totalProduct);
                        index++;
                        this.total += this.totalProduct;
                    }
                    index_productCurrent++;
                }
            }
        }
        System.out.println("===========================================");
        System.out.printf("Price: %.2f Baht\n", this.total);
        System.out.println("===========================================");
    }

    public void printOrderCheckout(String userIDCurrent){
        StoreManager storeManager = new StoreManager();
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/PRODUCT.txt");
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/CART.txt");
        this.total = 0;

        String format = "%-5s %-12s %-10s %-10s%n";
        System.out.println("====================\nCheckout\n====================");
        System.out.printf(format, "#", "Name", "Quantity", "Totals (฿)");
        int index = 1;
        for(Cart cart: storeManager.carts){
            if(cart.getuserID().equalsIgnoreCase(userIDCurrent)){
                int index_productCurrent = 0;
                for(Product product : storeManager.products) {
                    if(product.getId().equalsIgnoreCase(cart.getIdProduct())){
                        this.totalProduct = cart.getQuantity() * product.getPrice();
                        System.out.printf("%-5d %-12s %-10d %-10.2f%n", index, storeManager.products[index_productCurrent].getName(), cart.getQuantity(), this.totalProduct);
                        index++;
                        this.total += this.totalProduct;
                    }
                    index_productCurrent++;
                }
            }
        }
        double shipping = 50.0;
        System.out.println("===========================================");
        System.out.printf("Price: %.2f Baht\n", this.total);
        System.out.printf("Shipping Fee: %.2f Baht\n", shipping);
        System.out.printf("Total: %.2f Baht\n", this.total + shipping);
        System.out.println("===========================================");
    }

    // Write File
    public void AddOrderToFile(ArrayList<String> newLine){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathOrder, true))) {
            for (String string : newLine) {
                writer.write(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void creatNewOrder(String userID_current){
        StoreManager storeManager = new StoreManager();
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/PRODUCT.txt");
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/CART.txt");
        ArrayList<String> listOrder = new ArrayList<>();
        int index_Order = orders.length != 0 ? Integer.parseInt(orders[orders.length - 1].getOrderID()) + 1 : 8001;

        for (Cart order : storeManager.carts) {
            if (order.getuserID().equalsIgnoreCase(userID_current)) {
                OptionalInt targetIndex = IntStream.range(0, storeManager.products.length)
                    .filter(i -> storeManager.products[i].getId().equalsIgnoreCase(order.getIdProduct()))
                    .findFirst();
                
                if (targetIndex.isPresent()) {
                    listOrder.add(index_Order + "\t" + order.getuserID() + "\t" + order.getIdProduct() + "\t" + order.getQuantity() + '\t' + storeManager.products[targetIndex.getAsInt()].getPrice() +"\n");
                }
            }
        }        

        AddOrderToFile(listOrder);
        loadOrderFromFile();
        updateFile_Order();
        clearCart(userID_current);
    }

    public void updateFile_Order(){
        Order[] backUpOrder = orders;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathOrder, false))) {
            for (int i = 0; i < backUpOrder.length; i++) {
                writer.write(backUpOrder[i].getOrderID() + "\t" + backUpOrder[i].getuserID() + "\t" + backUpOrder[i].getproductID() + "\t" + backUpOrder[i].getquantity() + "\t" + "$" + backUpOrder[i].getpriceOnOrder() + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(String userID_current){
        StoreManager storeManager = new StoreManager();
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/PRODUCT.txt");
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_10/CART.txt");

        for (Cart order : storeManager.carts) {
            if (order.getuserID().equalsIgnoreCase(userID_current)) {
                order.setQuantity(0);
            }
        }
        storeManager.updateFile_Order();
    }
}
