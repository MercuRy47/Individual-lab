/************************************************************************************/ 
/* Program Assignment: SE STORE #2 (H)
/* Student ID: 66160237
/* Student Name: Wanasart Nianthasat
/* Date: 21/08/2024
/* Description: โปรแกรมแสดงข้อมูลสิ้นค้าที่มีในอยู่ระบบ แบบเลือกหมวดได้
/*************************************************************************************/
package SE_STORE_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SEStore {
    private int choose;
    private String index;
    private Scanner input;

    // แสดง Display#1
    public void showOptions_dis1() {
        this.input = new Scanner(System.in);
        while (true) {
            System.out.printf("===== SE STORE =====\n1. Show Category\n2. Exit\n====================\nSelect (1-2) : ");
            try {
                this.choose = input.nextInt();
                if (this.choose == 1 || this.choose == 2) {
                    break;  // ออกจากลูปเมื่อกรอกข้อมูลถูกต้อง
                } else {
                    System.out.println("Error: Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: You must enter a valid number.");
                input.next(); // เคลียร์ค่า input ที่ไม่ถูกต้อง
            }
        }
    }

    // แสดง Display#2
    public void showOptions_dis2(){
        this.input = new Scanner(System.in);
        StoreManager store = new StoreManager();
        store.loadProductFromFile("SE_STORE_2/CATEGORY.txt");
        System.out.printf("Select Category to Show Product (1-%d) or Q for exit" + "\nSelect : ", store.dataCategory.size());
        this.index = input.next();
    }

    // แสดง Display#3
    public void showOptions_dis3(){
        this.input = new Scanner(System.in);
        System.out.printf("Press Q to Exit\n");
        this.index = input.next();
    }
    public static void main(String[] args) {
        SEStore seStore = new SEStore();        
        StoreManager store = new StoreManager();

        // Load data
        store.loadProductFromFile("SE_STORE_2/PRODUCT.txt");
        store.loadProductFromFile("SE_STORE_2/CATEGORY.txt");
        while(true) {
            // Display#1
            seStore.showOptions_dis1();

            if(seStore.choose == 1){
                while(true) {
                    store.printAllCategories();
                    // Display#2
                    seStore.showOptions_dis2();

                    // Load data
                    store.loadProductFromFile("SE_STORE_2/PRODUCT.txt");
                    store.loadProductFromFile("SE_STORE_2/CATEGORY.txt");
                    if(seStore.index.equalsIgnoreCase("Q")){
                        break;
                    }else {  
                        store.searchCategoryId(store.categories[(Integer.parseInt(seStore.index) - 1)].getCategoryId());
                        while(true) {
                            // Display#3
                            seStore.showOptions_dis3();
                            if(seStore.index.equalsIgnoreCase("Q")){
                                break;
                            }
                        }
                    }
                }
            }else if(seStore.choose == 2){
                System.out.printf("===== SE STORE =====\nThank you for using our service :3");
                System.exit(0);
            }else {
                System.out.println();
            }
        }
    }
}