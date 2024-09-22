package SE_STORE_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
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
        String format = "%-5s %-15s %-10s %-10s%n";
        System.out.println("============ Snacks ============");
        System.out.printf(format, "#", "Name", "Price (฿)", "Quantity");
        int index = 1;
        for(Product product: products){
            if(product.getCategoryId().equalsIgnoreCase(choose)){
                System.out.printf("%-5d %-15s %-9.2f %-10d%n", index, product.getName(), product.getPrice()*34, product.getQuantity());
            index++;
            }
        }
        System.out.println("===========================================");
    }

    // แสดงข้อมูลทั้งหมดตาม CategoryID นั้นๆ
    public void searchCategoryId_role(String choose, String role) {
        this.choose = choose;
    
        // ปรับฟอร์แมตสำหรับหัวตารางและข้อมูลแถว
        String formatHeader = "%-5s %-15s %-20s %-10s%n"; // ฟอร์แมตหัวตาราง
        String formatRow = "%-5d %-15s %-10.2f (%6.2f) %-10d%n"; // ฟอร์แมตแถวข้อมูล
        
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
    
        // ปรับฟอร์แมตสำหรับหัวตารางและข้อมูลแถว
        String formatHeader = "%-5s %-15s %-20s %-10s%n"; // ฟอร์แมตหัวตาราง
        String formatRow = "%-5d %-15s %-10.2f (%6.2f) %-10d%n"; // ฟอร์แมตแถวข้อมูล
    
        // สร้าง list ใหม่ที่มีเฉพาะ products ตาม CategoryId ที่เลือก
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            filteredProducts.add(product);
        }
    
        // เรียงลำดับตามชื่อ (Name) จาก Z ถึง A และถ้าชื่อเท่ากันให้เรียงตามจำนวน (Quantity) จากมากไปน้อย
        Collections.sort(filteredProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                int nameComparison = p2.getName().compareToIgnoreCase(p1.getName()); // เรียงตามชื่อ (Z-A)
                if (nameComparison != 0) {
                    return nameComparison; // ถ้าชื่อไม่เท่ากันให้เรียงตามชื่อ
                }
                return Integer.compare(p2.getQuantity(), p1.getQuantity()); // ถ้าชื่อเท่ากันให้เรียงตามจำนวน (มากไปน้อย)
            }
        });
    
        int index = 1;
        
        System.out.println("======== Meat & Seafood ========");
        if(!role.equalsIgnoreCase("Silver") || !role.equalsIgnoreCase("Gold")){
            formatHeader = "%-5s %-15s %-10s %-10s%n";
        }
        System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");
        for (Product product : filteredProducts) {
            double priceInBaht = product.getPrice() * 34;
            double discountedPrice = priceInBaht;
    
            if (role.equalsIgnoreCase("Silver")) {
                discountedPrice -= priceInBaht * 0.05; // ส่วนลดสำหรับ Silver
            } else if (role.equalsIgnoreCase("Gold")) {
                discountedPrice -= priceInBaht * 0.1; // ส่วนลดสำหรับ Gold
            } else if(role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular")){
                formatRow = "%-5d %-15s %-9.2f %-10d%n";
            }
    
            if(role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular")){
                if (product.getCategoryId().equalsIgnoreCase(choose)) {
                    System.out.printf(formatRow, 
                        index, 
                        product.getName(), 
                        priceInBaht,  // ราคาหลังส่วนลด
                        product.getQuantity() // จำนวน
                    );
                    index++;
                }
            }else if(role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold")){
                if (product.getCategoryId().equalsIgnoreCase(choose)) {
                    System.out.printf(formatRow, 
                        index, 
                        product.getName(), 
                        discountedPrice,  // ราคาหลังส่วนลด
                        priceInBaht,      // ราคาปกติในวงเล็บ
                        product.getQuantity() // จำนวน
                    );
                    index++;
                }
            }
        }
    
        System.out.println("================================");
    }

    public void searchCategoryId_role_ASC(String choose, String role) {
        this.choose = choose;
    
        // ปรับฟอร์แมตสำหรับหัวตารางและข้อมูลแถว
        String formatHeader = "%-5s %-15s %-20s %-10s%n"; // ฟอร์แมตหัวตาราง
        String formatRow = "%-5d %-15s %-10.2f (%6.2f) %-10d%n"; // ฟอร์แมตแถวข้อมูล
    
        // สร้าง list ใหม่ที่มีเฉพาะ products ตาม CategoryId ที่เลือก
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            filteredProducts.add(product);
        }
    
        // เรียงลำดับจากจำนวน (Quantity) น้อยไปมาก และถ้าจำนวนเท่ากันให้เรียงตามชื่อ (Name)
        Collections.sort(filteredProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                int quantityComparison = Integer.compare(p1.getQuantity(), p2.getQuantity());
                if (quantityComparison != 0) {
                    return quantityComparison; // ถ้าจำนวนไม่เท่ากันให้เรียงตามจำนวน
                }
                return p1.getName().compareToIgnoreCase(p2.getName()); // ถ้าจำนวนเท่ากันให้เรียงตามชื่อ
            }
        });
    
        int index = 1;
        
        System.out.println("======== Meat & Seafood ========");
        if(!role.equalsIgnoreCase("Silver") || !role.equalsIgnoreCase("Gold")){
            formatHeader = "%-5s %-15s %-10s %-10s%n";
        }
        System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");
        for (Product product : filteredProducts) {
            double priceInBaht = product.getPrice() * 34;
            double discountedPrice = priceInBaht;
    
            if (role.equalsIgnoreCase("Silver")) {
                discountedPrice -= priceInBaht * 0.05; // ส่วนลดสำหรับ Silver
            } else if (role.equalsIgnoreCase("Gold")) {
                discountedPrice -= priceInBaht * 0.1; // ส่วนลดสำหรับ Gold
            } else if(role.equalsIgnoreCase("Staff") || !role.equalsIgnoreCase("Regular")){
                formatRow = "%-5d %-15s %-9.2f %-10d%n";
            }
            
            if(role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular")){
                if (product.getCategoryId().equalsIgnoreCase(choose)) {
                    System.out.printf(formatRow, 
                        index, 
                        product.getName(), 
                        priceInBaht,  // ราคาหลังส่วนลด
                        product.getQuantity() // จำนวน
                    );
                    index++;
                }
            }else if(role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold") ){
                if (product.getCategoryId().equalsIgnoreCase(choose)) {
                    System.out.printf(formatRow, 
                        index, 
                        product.getName(), 
                        discountedPrice,  // ราคาหลังส่วนลด
                        priceInBaht,      // ราคาปกติในวงเล็บ
                        product.getQuantity() // จำนวน
                    );
                    index++;
                }
            }
        }
    
        System.out.println("================================");
    }


}
