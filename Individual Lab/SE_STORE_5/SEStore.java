/************************************************************************************/ 
/* Program Assignment: SE STORE #5
/* Student ID: 66160237
/* Student Name: Wanasart Nianthasat
/* Date: 11/09/2024
/* Description: เพิ่มระบบ Role, Add Member
/*************************************************************************************/
package SE_STORE_5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SEStore {
    private int choose, num;
    private String index, email, tel, firstName, lastName, emailLogin, passwordLogin;
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
                this.emailLogin = this.input.next();
                System.out.print("Password : ");
                this.passwordLogin = this.input.next();
                if (userManager.CheckEmail(this.emailLogin) && userManager.CheckPassword(this.passwordLogin)) {
                    if(userManager.CheckStatus(this.emailLogin)) return;
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

    public void showOptions_staff() { 
        this.input = new Scanner(System.in);
        while (true) {
            System.out.printf("1. Show Category\n2. Add Member\n3. Logout\n====================\nSelect (1-3) : ");
            try {
                this.choose = input.nextInt();
                if (this.choose == 1 || this.choose == 2 || this.choose == 3) {
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
        store.loadProductFromFile("Individual Lab/SE_STORE_5/CATEGORY.txt");
        System.out.printf("Select Category to Show Product (1-%d) or Q for exit" + "\nSelect : ", store.dataCategory.size());
        this.index = input.next();
    }

    // แสดง Display สำหรับออก
    public void showOptions_dis3(){
        this.input = new Scanner(System.in);
        System.out.printf("Press Q to Exit\n");
        this.index = input.next();
    }

    // Add Member
    public void addMember_dis(){
        System.out.println("===== Add Member =====");
        System.out.print("Firstname : ");
        this.firstName = input.next();
        System.out.print("Lastname : ");
        this.lastName = input.next();
        System.out.print("Email : ");
        this.email = input.next();
        System.out.print("Phone : ");
        this.tel = input.next();
    }
    
    /////<----- Main Head ----->/////
    public static void main(String[] args) {
        SEStore seStore = new SEStore();        
        StoreManager store = new StoreManager();
        UserManager userManager = new UserManager();

        // Path
        String pathProduct = "Individual Lab/SE_STORE_5/PRODUCT.txt";
        String pathCategory = "Individual Lab/SE_STORE_5/CATEGORY.txt";

        // Load File
        store.loadProductFromFile(pathProduct);
        store.loadProductFromFile(pathCategory);
        userManager.loadUserFromFile();
        userManager.checkRole();

        // Test
        //userManager.creatNewUser("wanasart", "nianthasat", "wanasart2@gmail.com", "0661124703");

        while(true) {
            // Login Show
            seStore.loginDisplay();

            if(seStore.choose == 1){
                // Login Form
                seStore.loginForm();

                for(int i = 0; i < userManager.users.length; i++){
                    if(userManager.users[i].getEmail().equalsIgnoreCase(seStore.emailLogin)){
                        /////< STAFF (Head) >/////
                        if(userManager.users[i].getRole().equalsIgnoreCase("Staff")){
                            while(true) {
                                userManager.printInfo(seStore.emailLogin, seStore.passwordLogin);
                                seStore.showOptions_staff();
                                
                                if(seStore.choose == 1){
                                    while(true) {
                                        store.printAllCategories();
                                        seStore.showOptions_dis2();
                    
                                        // Load File
                                        store.loadProductFromFile(pathProduct);
                                        store.loadProductFromFile(pathCategory);
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
                                }
                                else if(seStore.choose == 2){
                                    // Add Member
                                    seStore.addMember_dis();
                                    if(seStore.firstName.length() <= 2 || seStore.lastName.length() <= 2 || (seStore.email.length() <= 2 || !seStore.email.contains("@")) || seStore.tel.length() != 10){
                                        System.out.println("Error! - Your Information are Incorrect!");
                                    }else {
                                        userManager.creatNewUser(seStore.firstName, seStore.lastName, seStore.email, seStore.tel);
                                        System.out.println("Success - New Member has been created!");
                                        System.out.println(seStore.firstName + "'s Password is " + userManager.Decode(userManager.users[userManager.users.length - 1].getCodePassword()));
                                    }
                                }
                                else if(seStore.choose == 3){
                                    break;
                                }
                            }

                        }
                        /////< STAFF (End) >/////
                        /////< OTHER ROLE (Head) >/////
                        else {
                            while(true) {
                                userManager.printInfo(seStore.emailLogin, seStore.passwordLogin);
                                seStore.showOptions_dis1();
                                
                                if(seStore.choose == 1){
                                    while(true) {
                                        store.printAllCategories();
                                        seStore.showOptions_dis2();
                    
                                        // Load File
                                        store.loadProductFromFile(pathProduct);
                                        store.loadProductFromFile(pathCategory);
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
                        }
                        /////< OTHER ROLE (End) >/////
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