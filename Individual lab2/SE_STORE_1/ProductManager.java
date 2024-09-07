package SE_STORE_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    public Product[] products;

    // โหลดข้อมูล Product ทั้งหมดจากไฟล์
    public void loadProductFromFile(){
        List<String> dataProduct = new ArrayList<>();
        try(BufferedReader readFile = new BufferedReader(new FileReader("SE_STORE_1/PRODUCT.txt"))){
            String line;
            while((line = readFile.readLine()) != null) {
                dataProduct.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        products = new Product[dataProduct.size()];
        for(int i = 0; i < dataProduct.size(); i++) {
            String[] parts = dataProduct.get(i).split("\\s+");
            products[i] = new Product(parts[0], parts[1], Double.parseDouble(parts[2].replace("$", "")), Integer.parseInt(parts[3]));
        }
    }

    // แสดงข้อมูลทั้งหมดของสินค้า
    public void printAllProducts() {
        String format = "%-5s %-15s %-10s %-10s%n";
        System.out.println("=========== SE STORE's Products ===========");
        System.out.printf(format, "#", "Name", "Price", "Quantity");
        int index = 1;
        for(Product product: products){
            System.out.printf(format, index, product.getName(), "$" + product.getPrice(), product.getQuantity());
            index++;
        }
        System.out.println("===========================================");
    }
}
