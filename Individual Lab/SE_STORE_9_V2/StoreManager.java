package SE_STORE_9_V2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Comparator;

public class StoreManager {
    public Product[] products;
    public Category[] categories;
    public Cart[] carts;
    private String path;
    private String choose;

    public List<String> dataProduct = new ArrayList<>();
    public List<String> dataCategory = new ArrayList<>();
    public List<String> dataCart = new ArrayList<>();

    // Path
    String pathProduct = "Individual Lab/SE_STORE_9/PRODUCT.txt";
    String pathCategory = "Individual Lab/SE_STORE_9/CATEGORY.txt";
    String pathCart = "Individual Lab/SE_STORE_9/CART.txt";

    // โหลดข้อมูล Product ทั้งหมดจากไฟล์
    public void loadProductFromFile(String path){
        this.path = path;
        this.dataProduct = new ArrayList<>();
        this.dataCategory = new ArrayList<>();
        this.dataCart = new ArrayList<>();
        try(BufferedReader readFile = new BufferedReader(new FileReader(path))){
            String line;
            while((line = readFile.readLine()) != null) {
                if(path.equalsIgnoreCase(pathProduct)){
                    dataProduct.add(line);
                }else if(path.equalsIgnoreCase(pathCategory)){
                    dataCategory.add(line);
                }else if(path.equalsIgnoreCase(pathCart)){
                    dataCart.add(line);
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
        }else if(path.equalsIgnoreCase(pathCart)){
            carts = new Cart[dataCart.size()];
            for(int i = 0; i < dataCart.size(); i++) {
                String[] parts = dataCart.get(i).split("\\s+");
                carts[i] = new Cart(parts[0], parts[1], Integer.parseInt(parts[2]));
            }
        }
    }

    // แสดงข้อมูลทั้งหมดของสินค้า
    public void printAllProducts() {
        String format = "%-5s %-12s %-10s %-10s%n";
        System.out.println("=========== SE STORE's Products ===========");
        System.out.printf(format, "#", "Name", "Price (฿)", "Quantity");
        int index = 1;
        for(Product product: products){
            System.out.printf("%-5d %-12s %-10.2f %-10d%n", index, product.getName(), + product.getPrice()*34, product.getQuantity());
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

    // Linear Search
    // แสดงข้อมูลทั้งหมดตาม CategoryID นั้นๆ
    public void searchCategoryId(String choose, int num) {
        this.choose = choose;
        String format = "%-5s %-12s %-10s %-10s%n";
        System.out.printf("============ %s ============\n", categories[num].getCategoryName());
        System.out.printf(format, "#", "Name", "Price (฿)", "Quantity");
        int index = 1;
        // Linear Search
        for(Product product: products){
            if(product.getCategoryId().equalsIgnoreCase(choose)){
                System.out.printf("%-5d %-12s %-10.2f %-10d%n", index, product.getName(), product.getPrice()*34, product.getQuantity());
            index++;
            }
        }
        System.out.println("===========================================");
    }

    public void searchCategoryId_role(String choose, String role, int num) {
        this.choose = choose;
    
        // ปรับฟอร์แมตสำหรับหัวตารางและข้อมูลแถว
        String formatHeader = "%-5s %-12s %-18s %-15s%n"; // ฟอร์แมตหัวตาราง
        String formatRow = "%-5d %-12s %-7.2f (%7.2f) %5d%n"; // ฟอร์แมตแถวข้อมูล
        System.out.printf("======== %s ========\n", categories[num].getCategoryName());
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

    // public void searchCategoryId_role_DESC(String choose, String role, int num) {
    //     this.choose = choose;
    
    //     String formatHeader = role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold") ?
    //             "%-5s %-12s %-18s %-15s%n" : "%-5s %-12s %-10s %-10s%n";
    //     String formatRow = role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular") ?
    //             "%-5d %-12s %-10.2f %-10d%n" : "%-5d %-12s %-7.2f (%7.2f) %5d%n";
    
    //     // สร้างอาร์เรย์ใหม่เพื่อเรียงข้อมูล
    //     Product[] filteredProducts = Arrays.stream(products)
    //             .sorted(Comparator.comparing(Product::getName, Comparator.reverseOrder())
    //             .thenComparing(Product::getQuantity, Comparator.reverseOrder()))
    //             .toArray(Product[]::new);
    
    //     System.out.printf("======== %s ========\n", categories[num].getCategoryName());
    //     System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");
    
    //     int index = 1;
    //     for (Product product : filteredProducts) {
    //         double priceInBaht = product.getPrice() * 34;
    //         double discountedPrice = role.equalsIgnoreCase("Silver") ? priceInBaht * 0.95 :
    //                 role.equalsIgnoreCase("Gold") ? priceInBaht * 0.9 : priceInBaht;
    
    //         if (product.getCategoryId().equalsIgnoreCase(choose)) {
    //             if (role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold")) {
    //                 System.out.printf(formatRow, index++, product.getName(), discountedPrice, priceInBaht, product.getQuantity());
    //             } else {
    //                 System.out.printf(formatRow, index++, product.getName(), priceInBaht, product.getQuantity());
    //             }
    //         }
    //     }
    //     System.out.println("================================");
    // }

    public void searchCategoryId_role_DESC(String choose, String role, int num) {
        this.choose = choose;
    
        String formatHeader = role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold") ?
                "%-5s %-12s %-18s %-15s%n" : "%-5s %-12s %-10s %-10s%n";
        String formatRow = role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular") ?
                "%-5d %-12s %-10.2f %-10d%n" : "%-5d %-12s %-7.2f (%7.2f) %5d%n";
    
        // สร้างอาร์เรย์ใหม่เพื่อคัดกรองผลิตภัณฑ์
        Product[] filteredProducts = new Product[this.products.length];
        int count = 0;
    
        // คัดกรองผลิตภัณฑ์ตาม categoryId
        for (Product product : this.products) {
            if (product.getCategoryId().equalsIgnoreCase(choose)) {
                filteredProducts[count++] = product;
            }
        }
    
        // ลดขนาดอาร์เรย์ filteredProducts ให้เหมาะสม
        filteredProducts = Arrays.copyOf(filteredProducts, count);
    
        // เรียงตามชื่อในลำดับย้อนกลับ (มากไปน้อย)
        sortByNameDescending(filteredProducts);
    
        // เรียงกลุ่มตามจำนวนที่ชื่อมีอักษรตัวแรกเหมือนกัน
        groupByFirstCharAndSortQuantity(filteredProducts);
    
        // แสดงผลข้อมูล
        System.out.printf("======== %s ========\n", categories[num].getCategoryName());
        System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");
    
        int index = 1;
        for (Product product : filteredProducts) {
            double priceInBaht = product.getPrice() * 34;
            double discountedPrice = role.equalsIgnoreCase("Silver") ? priceInBaht * 0.95 :
                    role.equalsIgnoreCase("Gold") ? priceInBaht * 0.9 : priceInBaht;
    
            if (role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold")) {
                System.out.printf(formatRow, index++, product.getName(), discountedPrice, priceInBaht, product.getQuantity());
            } else {
                System.out.printf(formatRow, index++, product.getName(), priceInBaht, product.getQuantity());
            }
        }
        System.out.println("================================");
    }
    // ฟังก์ชันสำหรับจัดกลุ่มโดยอักษรตัวแรกที่เหมือนกัน แล้วเรียงตามจำนวน
    private void groupByFirstCharAndSortQuantity(Product[] products) {
        int i = 0;
        while (i < products.length) {
            int j = i + 1;
    
            // หากตัวอักษรตัวแรกเหมือนกัน ให้จัดกลุ่ม
            while (j < products.length && products[j].getName().charAt(0) == products[i].getName().charAt(0)) {
                j++;
            }
    
            // เรียงตามจำนวนในกลุ่มที่มีอักษรตัวแรกเหมือนกัน
            if (j - i > 1) {
                Product[] subArray = Arrays.copyOfRange(products, i, j);
                sortByQuantityDescending(subArray);
    
                // แทนที่กลุ่มที่จัดเรียงแล้วกลับไปใน products
                System.arraycopy(subArray, 0, products, i, subArray.length);
            }
    
            // ไปยังกลุ่มถัดไป
            i = j;
        }
    }
    // Insertion Sort
    public void sortByNameDescending(Product[] products) {
        for (int i = 1; i < products.length; i++) {
            Product key = products[i];
            int j = i - 1;
    
            while (j >= 0 && products[j].getName().compareToIgnoreCase(key.getName()) < 0) {
                products[j + 1] = products[j];
                j = j - 1;
            }
            products[j + 1] = key;
        }
  }
    // Shell Sort
    public void sortByQuantityDescending(Product[] products) {
        int n = products.length;
    
        // เริ่มต้นด้วย gap เป็นครึ่งหนึ่งของความยาว
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Product temp = products[i];
                int j;
    
                // เปรียบเทียบและสลับตำแหน่งโดยดูจาก quantity
                for (j = i; j >= gap && products[j - gap].getQuantity() < temp.getQuantity(); j -= gap) {
                    products[j] = products[j - gap];
                }
                products[j] = temp;
            }
        }
    }        

    public void searchCategoryId_role_ASC(String choose, String role, int num) {
        this.choose = choose;
    
        String formatHeader = role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold") ?
                "%-5s %-12s %-18s %-15s%n" : "%-5s %-12s %-10s %-10s%n";
        String formatRow = role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Regular") ?
                "%-5d %-12s %-10.2f %-10d%n" : "%-5d %-12s %-7.2f (%7.2f) %5d%n";
    
        // สร้างอาร์เรย์ใหม่เพื่อคัดกรองผลิตภัณฑ์
        Product[] filteredProducts = new Product[this.products.length];
        int count = 0;
    
        // คัดกรองผลิตภัณฑ์ตาม categoryId
        for (Product product : this.products) {
            if (product.getCategoryId().equalsIgnoreCase(choose)) {
                filteredProducts[count++] = product;
            }
        }
    
        // ลดขนาดอาร์เรย์ filteredProducts ให้เหมาะสม
        filteredProducts = Arrays.copyOf(filteredProducts, count);
    
        // เรียงตามจำนวนด้วย Selection Sort
        sortByQuantityAscending(filteredProducts);
    
        // ตรวจสอบสินค้าที่มีจำนวนเท่ากัน และแยกออกมาเรียงตามชื่อด้วย Bubble Sort
        int start = 0;
        while (start < filteredProducts.length) {
            int end = start + 1;
            while (end < filteredProducts.length && filteredProducts[start].getQuantity() == filteredProducts[end].getQuantity()) {
                end++;
            }
            // เรียงสินค้าที่มีจำนวนเท่ากันตามชื่อ
            if (end - start > 1) {
                sortByNameAscending(filteredProducts, start, end);
            }
            start = end;
        }
    
        // แสดงผลข้อมูล
        System.out.printf("======== %s ========\n", categories[num].getCategoryName());
        System.out.printf(formatHeader, "#", "Name", "Price (฿)", "Quantity");
    
        int index = 1;
        for (Product product : filteredProducts) {
            double priceInBaht = product.getPrice() * 34;
            double discountedPrice = role.equalsIgnoreCase("Silver") ? priceInBaht * 0.95 :
                    role.equalsIgnoreCase("Gold") ? priceInBaht * 0.9 : priceInBaht;
    
            if (role.equalsIgnoreCase("Silver") || role.equalsIgnoreCase("Gold")) {
                System.out.printf(formatRow, index++, product.getName(), discountedPrice, priceInBaht, product.getQuantity());
            } else {
                System.out.printf(formatRow, index++, product.getName(), priceInBaht, product.getQuantity());
            }
        }
        System.out.println("================================");
    }
    // Selection Sort
    public void sortByQuantityAscending(Product[] products) {
        int n = products.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (products[j].getQuantity() < products[minIndex].getQuantity()) {
                    minIndex = j;
                }
            }
            // สลับตำแหน่ง
            Product temp = products[minIndex];
            products[minIndex] = products[i];
            products[i] = temp;
        }
    }
    // Bubble Sort
    public void sortByNameAscending(Product[] products, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            for (int j = start; j < end - 1 - (i - start); j++) {
                if (products[j].getName().compareToIgnoreCase(products[j + 1].getName()) > 0) {
                    // สลับตำแหน่ง
                    Product temp = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = temp;
                }
            }
        }
    }    

    public void updateFile_edit(){
        Product[] backUpProducts = products;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathProduct, false))) {
            for (int i = 0; i < backUpProducts.length; i++) {
                writer.write(backUpProducts[i].getId() + "\t" + backUpProducts[i].getName() + "\t" + "$" + backUpProducts[i].getPrice() + "\t" + backUpProducts[i].getQuantity() + "\t" + backUpProducts[i].getCategoryId() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Write File
    public void AddCartToFile(String newLine){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathCart, true))) {
            writer.write(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void creatNewOrder(String userID, String productID, int quantity){
        String newUser = userID + "\t" + productID + "\t" + quantity + "\n";

        AddCartToFile(newUser);
        loadProductFromFile(pathCart);
        updateFile_Order();
    }

    public void updateFile_Order(){
        Cart[] backUpOrder = carts;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathCart, false))) {
            for (int i = 0; i < backUpOrder.length; i++) {
                if(backUpOrder[i].getQuantity() > 0){
                    writer.write(backUpOrder[i].getuserID() + "\t" + backUpOrder[i].getIdProduct() + "\t" + backUpOrder[i].getQuantity() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
