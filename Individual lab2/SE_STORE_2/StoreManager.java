package SE_STORE_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    public Product[] products;
    public Category[] categories;
    private String path;
    private String choose;

    public List<String> dataProduct = new ArrayList<>();
    public List<String> dataCategory = new ArrayList<>();

    // โหลดข้อมูล Product ทั้งหมดจากไฟล์
    public void loadProductFromFile(String path){
        this.path = path;
        this.dataProduct = new ArrayList<>();
        this.dataCategory = new ArrayList<>();
        try(BufferedReader readFile = new BufferedReader(new FileReader(path))){
            String line;
            while((line = readFile.readLine()) != null) {
                if(path.equalsIgnoreCase("SE_STORE_2/PRODUCT.txt")){
                    dataProduct.add(line);
                }else if(path.equalsIgnoreCase("SE_STORE_2/CATEGORY.txt")){
                    dataCategory.add(line);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(path.equalsIgnoreCase("SE_STORE_2/PRODUCT.txt")){
            products = new Product[dataProduct.size()];
            for(int i = 0; i < dataProduct.size(); i++) {
                String[] parts = dataProduct.get(i).split("\\s+");
                products[i] = new Product(parts[0], parts[1], Double.parseDouble(parts[2].replace("$", "")), Integer.parseInt(parts[3]), parts[4]);
            }
        }else if(path.equalsIgnoreCase("SE_STORE_2/CATEGORY.txt")){
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
        System.out.printf(format, "#", "Name", "Price", "Quantity");
        int index = 1;
        for(Product product: products){
            if(product.getCategoryId().equalsIgnoreCase(choose)){
                System.out.printf(format, index, product.getName(), "$" + product.getPrice(), product.getQuantity());
            }
            index++;
        }
        System.out.println("===========================================");
    }
}
