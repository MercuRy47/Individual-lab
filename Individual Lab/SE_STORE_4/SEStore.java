/************************************************************************************/ 
/* Program Assignment: SE STORE #4 
/* Student ID: 66160237
/* Student Name: Wanasart Nianthasat
/* Date: 07/09/2024
/* Description: เพิ่มระบบ Login
/*************************************************************************************/
package SE_STORE_4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SEStore {
    private int choose, num;
    private String index, email, password;
    private Scanner input;

    // แสดง Display ตัวเลือกหน้า login
    public void loginDisplay(){
        this.input = new Scanner(System.in);
        while (true) {
            System.out.printf("===== SE STORE =====\n1. Login\n2. Exit\n====================\nSelect (1-2) : ");
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

    // แสดง Display หน้า login form
    public void loginForm(){
        UserManager userManager = new UserManager();
        userManager.loadUserFromFile();
        this.input = new Scanner(System.in);
        this.num = 0;
        if (this.num != 3) {
            System.out.println("===== LOGIN =====");
            while (this.num != 3) {
                System.out.print("Email : ");
                this.email = this.input.next();
                System.out.print("Password : ");
                this.password = this.input.next();
                if (userManager.CheckEmail(this.email) && userManager.CheckPassword(this.password)) {
                    if(userManager.CheckStatus(this.email)) return;
                    userManager.printInfo(this.email, this.password);
                    return;
                }
                this.num++;
                System.out.println("====================");
                System.out.printf("Error! - Email or Password is Incorrect (%d)\n", this.num);
            }
        }
        System.out.println("Sorry, Please try again later :(");
    }

    // แสดง Display เลือก Category | logout
    public void showOptions_dis1() { 
        this.input = new Scanner(System.in);
        while (true) {
            System.out.printf("1. Show Category\n2. Logout\n====================\nSelect (1-2) : ");
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

    // แสดง Display สำหรับใส่ตัวเลือกใน Category
    public void showOptions_dis2(){
        this.input = new Scanner(System.in);
        StoreManager store = new StoreManager();
        store.loadProductFromFile("Individual Lab/SE_STORE_4/CATEGORY.txt");
        System.out.printf("Select Category to Show Product (1-%d) or Q for exit" + "\nSelect : ", store.dataCategory.size());
        this.index = input.next();
    }

    // แสดง Display สำหรับออก
    public void showOptions_dis3(){
        this.input = new Scanner(System.in);
        System.out.printf("Press Q to Exit\n");
        this.index = input.next();
    }
    
    /////<----- Main Head ----->/////
    public static void main(String[] args) {
        SEStore seStore = new SEStore();        
        StoreManager store = new StoreManager();
        UserManager userManager = new UserManager();

        // Load File
        store.loadProductFromFile("Individual Lab/SE_STORE_4/PRODUCT.txt");
        store.loadProductFromFile("Individual Lab/SE_STORE_4/CATEGORY.txt");
        userManager.loadUserFromFile();

        while(true) {
            // Login Show
            seStore.loginDisplay();

            if(seStore.choose == 1){
                // Login Form
                seStore.loginForm();

                while(true) {
                    if(seStore.num == 3) break;
                    if(userManager.CheckStatus(seStore.email)){
                        System.out.println("Error! - Your Account are Expired!");
                        break;
                    }
                    seStore.showOptions_dis1();

                    if(seStore.choose == 1){
                        while(true) {
                            store.printAllCategories();
                            seStore.showOptions_dis2();
        
                            // Load File
                            store.loadProductFromFile("Individual Lab/SE_STORE_4/PRODUCT.txt");
                            store.loadProductFromFile("Individual Lab/SE_STORE_4/CATEGORY.txt");
                            if(seStore.index.equalsIgnoreCase("Q")){
                                break;
                            }else {  
                                // Search Category by ID
                                store.searchCategoryId(store.categories[(Integer.parseInt(seStore.index) - 1)].getCategoryId());
                                while(true) {
                                    seStore.showOptions_dis3();
                                    if(seStore.index.equalsIgnoreCase("Q")){
                                        break;
                                    }
                                }
                            }
                        }
                    }else if(seStore.choose == 2){
                        break;
                    }
                }
            }else if(seStore.choose == 2){
                System.out.printf("===== SE STORE =====\nThank you for using our service :3");
                System.exit(0);
            }
        }
        /////<----- Main End ----->/////
    }
}