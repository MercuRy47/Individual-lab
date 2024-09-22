package SE_STORE_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class StoreManager {
    public Product[] products;
    public Category[] categories;
    private String path;
    private String choose;

    public List<String> dataProduct = new ArrayList<>();
    public List<String> dataCategory = new ArrayList<>();

    // Path
    String pathProduct = "Individual Lab/SE_STORE_6/PRODUCT.txt";
    String pathCategory = "Individual Lab/SE_STORE_6/CATEGORY.txt";

    // โหลดข้อมูล Product ทั้งหมดจากไฟล์
    public void loadProductFromFile(String path){
        this.path = path;
        this.dataProduct = new ArrayList<>();
        this.dataCategory = new ArrayList<>();
        try(BufferedReader readFile = new BufferedReader(new FileReader(path))){
            String line;
            while((line = readFile.readLine()) != null) {
                if(path.equalsIgnoreCase(pathProduct)){
                    dataProduct.add(line);
                }else if(path.equalsIgnoreCase(pathCategory)){
                    dataCategory.add(line);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(path.equalsIgnoreCase(pathProduct)){
            products = new Product[dataProduct.size()];
            for(int i = 0; i < dataProduct.size(); i++) {
                String[] parts = dataProduct.get(i).split("\\s+");
                products[i] = new Product(parts[0], parts[1], Double.parseDouble(parts[2].replace("$", "")), Integer.parseInt(parts[3]), parts[4]);
            }
        }else if(path.equalsIgnoreCase(pathCategory)){
            categories = new Category[dataCategory.size()];
            for(int i = 0; i < dataCategory.size(); i++) {
                String[] parts = dataCategory.get(i).split("\\s+", 2);
                categories[i] = new Category(parts[0], parts[1]);
            }
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

    // แสดงข้อมูลทั้งหมดของ Category
    public void printAllCategories() {
        String format = "%-5s %-15s%n";
        System.out.println("===== SE STORE's Product Categories =====");
        System.out.printf(format, "#", "Category");
        int index = 1;
        for(Category category: categories){
            System.out.printf(format, index, category.getCategoryName());
            index++;
        }
        System.out.println("===========================================");
    }

    // แสดงข้อมูลทั้งหมดตาม CategoryID นั้นๆ
    public void searchCategoryId(String choose) {
        this.choose = choose;
        String format = "%-5s %-12s %-10s %-10s%n";
        System.out.println("============ Snacks ============");
        System.out.printf(format, "#", "Name", "Price (฿)", "Quantity");
        int index = 1;
        for(Product product: products){
            if(product.getCategoryId().equalsIgnoreCase(choose)){
                System.out.printf("%-5d %-12s %-10.2f %-10d%n", index, product.getName(), product.getPrice()*34, product.getQuantity());
            index++;
            }
        }
        System.out.println("===========================================");
    }

    public void searchCategoryId_role(String choose, String role) {
        this.choose = choose;
    
        // ปรับฟอร์แมตสำหรับหัวตารางและข้อมูลแถว
        String formatHeader = "%-5s %-12s %-18s %-15s%n"; // ฟอร์แมตหัวตาราง
        String formatRow = "%-5d %-12s %-6.2f (%6.2f) %5d%n"; // ฟอร์แมตแถวข้อมูล
        
        System.out.println("======== Meat & Seafood ========");
        System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");
    
        int index = 1;
    
        if (role.equalsIgnoreCase("Silver")) {
            for (Product product : products) {
                if (product.getCategoryId().equalsIgnoreCase(choose)) {
                    System.out.printf(formatRow, 
                        index, 
                        product.getName(), 
                        (product.getPrice() * 34) - ((product.getPrice() * 34) * 0.05), // ราคาหลังส่วนลด
                        product.getPrice() * 34, // ราคาปกติในวงเล็บ
                        product.getQuantity() // จำนวน
                    );
                    index++;
                }
            }
        } else if (role.equalsIgnoreCase("Gold")) {
            for (Product product : products) {
                if (product.getCategoryId().equalsIgnoreCase(choose)) {
                    System.out.printf(formatRow, 
                        index, 
                        product.getName(), 
                        (product.getPrice() * 34) - ((product.getPrice() * 34) * 0.1), // ราคาหลังส่วนลด
                        product.getPrice() * 34, // ราคาปกติในวงเล็บ
                        product.getQuantity() // จำนวน
                    );
                    index++;
                }
            }
        }
    
        System.out.println("================================");
    }    

    public void searchCategoryId_role_DESC(String choose, String role) {
        this.choose = choose;
    
        String formatHeader = role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold") ?
                "%-5s %-12s %-18s %-15s%n" : "%-5s %-12s %-10s %-10s%n";
        String formatRow = role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular") ?
                "%-5d %-12s %-10.2f %-10d%n" : "%-5d %-12s %-6.2f (%6.2f) %5d%n";
    
        // สร้างอาร์เรย์ใหม่เพื่อเรียงข้อมูล
        Product[] filteredProducts = Arrays.stream(products)
                .sorted(Comparator.comparing(Product::getName, Comparator.reverseOrder())
                .thenComparing(Product::getQuantity, Comparator.reverseOrder()))
                .toArray(Product[]::new);
    
        System.out.println("======== Meat & Seafood ========");
        System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");
    
        int index = 1;
        for (Product product : filteredProducts) {
            double priceInBaht = product.getPrice() * 34;
            double discountedPrice = role.equalsIgnoreCase("Silver") ? priceInBaht * 0.95 :
                    role.equalsIgnoreCase("Gold") ? priceInBaht * 0.9 : priceInBaht;
    
            if (product.getCategoryId().equalsIgnoreCase(choose)) {
                if (role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold")) {
                    System.out.printf(formatRow, index++, product.getName(), discountedPrice, priceInBaht, product.getQuantity());
                } else {
                    System.out.printf(formatRow, index++, product.getName(), priceInBaht, product.getQuantity());
                }
            }
        }
        System.out.println("================================");
    }
    

    public void searchCategoryId_role_ASC(String choose, String role) {
        this.choose = choose;

        String formatHeader = role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold") ?
                "%-5s %-12s %-18s %-15s%n" : "%-5s %-12s %-10s %-10s%n";
        String formatRow = role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular") ?
                "%-5d %-12s %-10.2f %-10d%n" : "%-5d %-12s %-6.2f (%6.2f) %5d%n";

        // สร้างอาร์เรย์ใหม่เพื่อคัดกรองและเรียงข้อมูล
        Product[] filteredProducts = Arrays.stream(this.products)
                .sorted(Comparator.comparingInt(Product::getQuantity)
                .thenComparing(p -> p.getName().toLowerCase()))
                .toArray(Product[]::new);

        System.out.println("======== Meat & Seafood ========");
        System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");

        int index = 1;
        for (Product product : filteredProducts) {
            double priceInBaht = product.getPrice() * 34;
            double discountedPrice = role.equalsIgnoreCase("Silver") ? priceInBaht * 0.95 :
                    role.equalsIgnoreCase("Gold") ? priceInBaht * 0.9 : priceInBaht;

            if (product.getCategoryId().equalsIgnoreCase(choose)) {
                if (role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold")) {
                    System.out.printf(formatRow, index++, product.getName(), discountedPrice, priceInBaht, product.getQuantity());
                } else {
                    System.out.printf(formatRow, index++, product.getName(), priceInBaht, product.getQuantity());
                }
            }
        }
        System.out.println("================================");
    }   

}
