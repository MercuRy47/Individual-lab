/************************************************************************************/ 
/* Program Assignment: SE STORE #9 V2
/*************************************************************************************/
package SE_STORE_9_V2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SEStore {
    private int choose, num, chooseSort;
    private String index, email, tel, firstName, lastName, emailLogin, passwordLogin, role, fullName, nameProduct, quantity;
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
                if (userManager.CheckEmail(this.emailLogin) && userManager.CheckPassword(this.passwordLogin, this.emailLogin)) {
                    if(userManager.CheckStatus(this.emailLogin)) {
                        System.out.println("Error! - Your Account are Expired!");
                        loginDisplay();
                    }else {
                        return;
                    }
                }else {
                    this.num++;
                    System.out.println("====================");
                    System.out.printf("Error! - Email or Password is Incorrect (%d)\n", this.num);
                }
            }
        }
        System.out.println("Sorry, Please try again later :(");
    }

    // แสดง Display เลือก Category | logout
    public void showOptions_dis1() { 
        this.input = new Scanner(System.in);
        while (true) {
            System.out.printf("1. Show Category\n2. Order Product\n3. Logout\n====================\nSelect (1-3) : ");
            try {
                this.choose = input.nextInt();
                if (this.choose == 1 || this.choose == 2 || this.choose == 3)  {
                    break;  // ออกจากลูปเมื่อกรอกข้อมูลถูกต้อง
                } else {
                    System.out.println("Error: Please enter 1 or 3.");
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
            System.out.printf("1. Show Category\n2. Add Member\n" + //
                                "3. Edit Member\n4. Edit Product\n5. Logout\n====================\nSelect (1-5) : ");
            try {
                this.choose = input.nextInt();
                if (this.choose == 1 || this.choose == 2 || this.choose == 3 || this.choose == 4 || this.choose == 5) {
                    break;  // ออกจากลูปเมื่อกรอกข้อมูลถูกต้อง
                } else {
                    System.out.println("Error: Please enter 1 - 5.");
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
        store.loadProductFromFile("Individual Lab/SE_STORE_9/CATEGORY.txt");
        System.out.printf("Select Category to Show Product (1-%d) or Q for exit" + "\nSelect : ", store.dataCategory.size());
        this.index = input.next();
    }

    // แสดง Display สำหรับออก
    public void showOptions_dis3(){
        this.input = new Scanner(System.in);
        System.out.print("1. Show Name By DESC\n2. Show Quantity By ASC\n");
        System.out.printf("or Press Q to Exit : ");
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

    public void chooseMember_dis(){
        UserManager userManager = new UserManager();
        userManager.loadUserFromFile();
        System.out.println("Type Member Number, You want to edit or Press Q to Exit");
        System.out.printf("Select (1-%d) : ", userManager.users.length);
        this.index = input.next();
    }

    public void chooseProduct_dis(){
        StoreManager storeManager = new StoreManager();
        storeManager.loadProductFromFile("Individual Lab/SE_STORE_9/PRODUCT.txt");
        System.out.println("Type Product Number, You want to edit or Press Q to Exit");
        System.out.printf("Select (1-%d) : ", storeManager.products.length);
        this.index = input.next();
    }

    public void editProduct_dis(String NameProduct){
        System.out.printf("==== Edit info of %s ====\nType new info or Hyphen (-) for none edit.\n", NameProduct);
        System.out.print("Name : ");
        this.nameProduct = input.next();
        System.out.print("Quantity (+ or -) : ");
        this.quantity = input.next();
    }

    public void editMember_dis(String fullName){
        System.out.printf("==== Edit info of %s ====\nType new info or Hyphen (-) for none edit.\n", fullName);
        System.out.print("Firstname : ");
        this.firstName = input.next();
        System.out.print("Lastname : ");
        this.lastName = input.next();
        System.out.print("Email : ");
        this.email = input.next();
        System.out.print("Phone : ");
        this.tel = input.next();
    }

    public void order_options(){
        System.out.println("Enter the product number followed by the quantity.");
        System.out.println("1. How to Order");
        System.out.println("2. List Products");
        System.out.println("Q. Exit");
    }

    public void input_orderOptions(){
        System.out.print("Enter : ");
        this.index = input.next();
    }

    public void showExample(){
        System.out.println("How to Order:");
        System.out.println("To Add Product:");
        System.out.println("\tEnter the product number followed by the quantity.");
        System.out.println("\tExample: 1 50 (Adds 50 chips)");
        System.out.println("To Adjust Quantity:");
        System.out.println("\t+ to add more items: 1 +50 (Adds 50 more chips)");
        System.out.println("\t- to reduce items: 1 -50 (Removes 50 chips)");
    }

    
    /////<----- Main Head ----->/////
    public static void main(String[] args) {
        SEStore seStore = new SEStore();        
        StoreManager store = new StoreManager();
        UserManager userManager = new UserManager();

        // Path
        String pathProduct = "Individual Lab/SE_STORE_9/PRODUCT.txt";
        String pathCategory = "Individual Lab/SE_STORE_9/CATEGORY.txt";
        String pathCart = "Individual Lab/SE_STORE_9/CART.txt";

        // Load File
        store.loadProductFromFile(pathProduct);
        store.loadProductFromFile(pathCategory);

        userManager.loadUserFromFile();
        userManager.checkRole();

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
                            seStore.role = userManager.users[i].getRole();
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
                                        store.loadProductFromFile(pathCart);
                                        if(seStore.index.equalsIgnoreCase("Q")){
                                            break;
                                        }else {  
                                            // Search Category by ID
                                            int categoryChoose = (Integer.parseInt(seStore.index) - 1);
                                            store.searchCategoryId(store.categories[(Integer.parseInt(seStore.index) - 1)].getCategoryId(), (Integer.parseInt(seStore.index) - 1));
                                            seStore.chooseSort = (Integer.parseInt(seStore.index) - 1);
                                            while(true) {
                                                seStore.showOptions_dis3();
                                                if(seStore.index.equalsIgnoreCase("Q")){
                                                    break;
                                                }else if(seStore.index.equalsIgnoreCase("1")){
                                                    store.searchCategoryId_role_DESC(store.categories[seStore.chooseSort].getCategoryId(), seStore.role, categoryChoose);
                                                }else if(seStore.index.equalsIgnoreCase("2")){
                                                    store.searchCategoryId_role_ASC(store.categories[seStore.chooseSort].getCategoryId(), seStore.role, categoryChoose);
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
                                else if (seStore.choose == 3){
                                    userManager.printAllMember();
                                    seStore.chooseMember_dis();

                                    if(seStore.index.equalsIgnoreCase("Q")){
                                        continue;
                                    }
                                    else {
                                        seStore.choose = Integer.parseInt(seStore.index) - 1;
                                        seStore.fullName = userManager.users[seStore.choose].getFirstName() + " " + userManager.users[seStore.choose].getLastName();
                                        seStore.editMember_dis(seStore.fullName);

                                        boolean hasError = false;
                                        String saveFname = userManager.users[seStore.choose].getFirstName();
                                        String saveLname = userManager.users[seStore.choose].getLastName();
                                        String saveEmail = userManager.users[seStore.choose].getEmail();
                                        String saveTel = userManager.users[seStore.choose].getTel();

                                        // Check and update first name
                                        if (!seStore.firstName.equals("-")) {
                                            hasError = seStore.firstName.length() <= 2;
                                            if (!hasError) userManager.users[seStore.choose].setFirstName(seStore.firstName);
                                        }
                                        // Check and update last name
                                        if (!seStore.lastName.equals("-")) {
                                            hasError = seStore.lastName.length() <= 2;
                                            if (!hasError) userManager.users[seStore.choose].setLastName(seStore.lastName);
                                        }
                                        // Check and update email
                                        if (!seStore.email.equals("-")) {
                                            hasError = seStore.email.length() <= 2 || !seStore.email.contains("@");
                                            if (!hasError) userManager.users[seStore.choose].setEmail(seStore.email);
                                        }
                                        // Check and update telephone number
                                        if (!seStore.tel.equals("-")) {
                                            hasError = seStore.tel.length() != 10;
                                            if (!hasError) userManager.users[seStore.choose].setTel(seStore.tel);
                                        }
                                        // Handle error or save data
                                        if (hasError) {
                                            System.out.println("Error! - Your Information are Incorrect!");
                                            userManager.users[seStore.choose].setFirstName(saveFname);
                                            userManager.users[seStore.choose].setLastName(saveLname);
                                            userManager.users[seStore.choose].setEmail(saveEmail);
                                            userManager.users[seStore.choose].setTel(saveTel);
                                        } else {
                                            userManager.updateFile_edit();
                                            userManager.loadUserFromFile();
                                            System.out.println("Success - Member has been updated!");
                                        }
                                    }
                                }
                                else if(seStore.choose == 4){
                                    store.printAllProducts();
                                    seStore.chooseProduct_dis();

                                    if(seStore.index.equalsIgnoreCase("Q")){
                                        continue;
                                    }
                                    else {
                                        seStore.choose = Integer.parseInt(seStore.index) - 1;
                                        seStore.nameProduct = store.products[seStore.choose].getName();
                                        seStore.editProduct_dis(seStore.nameProduct);

                                        boolean hasError = false;
                                        String saveNproduct = store.products[seStore.choose].getName();
                                        int saveQproduct = store.products[seStore.choose].getQuantity();

                                        if (!seStore.nameProduct.equals("-")) {
                                            store.products[seStore.choose].setName(seStore.nameProduct);
                                        }
                                        if (seStore.quantity.charAt(0) == '-') {
                                            if (seStore.quantity.length() == 1) {
                                                // ไม่ต้องทำอะไรถ้ามีแค่เครื่องหมายลบตัวเดียว
                                            } else if (seStore.quantity.length() > 1) {
                                                hasError = seStore.quantity.contains(".");
                                                if (!hasError) {
                                                    // ถ้าไม่มีจุด ('.') ใน email ให้ปรับค่า quantity
                                                    int newValue = store.products[seStore.choose].getQuantity() - Integer.parseInt(seStore.quantity.substring(1));
                                                    store.products[seStore.choose].setQuantity(newValue);
                                                }
                                            }
                                        }
                                        if (seStore.quantity.charAt(0) == '+') {
                                            hasError = seStore.quantity.contains(".");
                                            if(seStore.quantity.length() > 1){
                                                if (!hasError) {
                                                    int newValue = store.products[seStore.choose].getQuantity() + Integer.parseInt(seStore.quantity.substring(1));
                                                    store.products[seStore.choose].setQuantity(newValue);
                                                }
                                            }else {
                                                hasError = true;
                                            }
                                        }

                                        if (hasError) {
                                            System.out.println("Error! - Your Information are Incorrect!");
                                            store.products[seStore.choose].setName(saveNproduct);
                                            store.products[seStore.choose].setQuantity(saveQproduct);
                                        } else {
                                            store.updateFile_edit();
                                            store.loadProductFromFile(pathProduct);
                                            System.out.printf("Success - %s has been updated!\n", seStore.nameProduct);
                                        }
                                    }

                                }
                                else if(seStore.choose == 5){
                                    break;
                                }
                            }

                        }
                        /////< STAFF (End) >/////
                        /////< OTHER ROLE (Head) >/////
                        else {
                            seStore.role = userManager.users[i].getRole();
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
                                        store.loadProductFromFile(pathCart);
                                        if(seStore.index.equalsIgnoreCase("Q")){
                                            break;
                                        }else {  
                                            // Search Category by ID
                                            int categoryChoose = (Integer.parseInt(seStore.index) - 1);
                                            if(seStore.role.equalsIgnoreCase("Regular")){
                                                store.searchCategoryId(store.categories[(Integer.parseInt(seStore.index) - 1)].getCategoryId(), (Integer.parseInt(seStore.index) - 1));
                                            }else {
                                                store.searchCategoryId_role(store.categories[(Integer.parseInt(seStore.index) - 1)].getCategoryId(), seStore.role, (Integer.parseInt(seStore.index) - 1));
                                            }
                                            seStore.chooseSort = (Integer.parseInt(seStore.index) - 1);
                                            while(true) {
                                                seStore.showOptions_dis3();
                                                if(seStore.index.equalsIgnoreCase("Q")){
                                                    break;
                                                }else if(seStore.index.equalsIgnoreCase("1")){
                                                    store.searchCategoryId_role_DESC(store.categories[seStore.chooseSort].getCategoryId(), seStore.role, categoryChoose);
                                                }else if(seStore.index.equalsIgnoreCase("2")){
                                                    store.searchCategoryId_role_ASC(store.categories[seStore.chooseSort].getCategoryId(), seStore.role, categoryChoose);
                                                }
                                            }
                                        }
                                    }
                                }else if(seStore.choose == 2){
                                    // Load File
                                    store.loadProductFromFile(pathProduct);
                                    store.loadProductFromFile(pathCategory);
                                    store.loadProductFromFile(pathCart);

                                    store.printAllProducts();
                                    seStore.order_options();

                                    String userID_current = userManager.users[i].getMemberId();

                                    while (true) {
                                        seStore.input_orderOptions();

                                        if (seStore.input.hasNextLine()) {
                                            seStore.index += " " + seStore.input.nextLine();
                                        }

                                        //System.out.println(seStore.index);
                                        store.loadProductFromFile(pathCart);
                                        boolean invalid = false;
                                        String[] parts = seStore.index.split("\\s+");

                                        if(parts.length == 1){
                                            if(parts[0].equalsIgnoreCase("Q")){
                                                System.out.println("Your cart has been saved!");
                                                break;
                                            }else if(parts[0].equalsIgnoreCase("1")){
                                                seStore.showExample();
                                            }else if(parts[0].equalsIgnoreCase("2")){
                                                store.printAllProducts();
                                            }
                                        }else if(parts.length == 2){
                                            String productID_choose = store.products[Integer.parseInt(parts[0])-1].getId();
                                            boolean newOrder = true;
                                            int index_Order = -1;

                                            int index = 0;
                                            for (Cart item : store.carts) {
                                                if(item.getuserID().equalsIgnoreCase(userID_current) && item.getIdProduct().equalsIgnoreCase(productID_choose)){
                                                    newOrder = false;
                                                    index_Order = index;
                                                }
                                                index++;
                                            }

                                            if(parts[1].charAt(0) == '+'){
                                                int quantity_input = Integer.parseInt(parts[1].substring(1));
                                                if(newOrder){
                                                    if(store.products[Integer.parseInt(parts[0])-1].getQuantity() - quantity_input < 0){
                                                        invalid = true;
                                                    }else {
                                                        store.creatNewOrder(userID_current, productID_choose, quantity_input);

                                                        store.products[Integer.parseInt(parts[0])-1].setQuantity(store.products[Integer.parseInt(parts[0])-1].getQuantity() - quantity_input);
                                                        store.updateFile_edit();
                                                    }
                                                }else {
                                                    if(store.products[Integer.parseInt(parts[0])-1].getQuantity() - quantity_input < 0){
                                                        invalid = true;
                                                    }else {
                                                        store.carts[index_Order].setQuantity(store.carts[index_Order].getQuantity() + quantity_input);
                                                        store.updateFile_Order();

                                                        store.products[Integer.parseInt(parts[0])-1].setQuantity(store.products[Integer.parseInt(parts[0])-1].getQuantity() - quantity_input);
                                                        store.updateFile_edit();
                                                    }
                                                }
                                            }else if(parts[1].charAt(0) == '-'){
                                                int quantity_input = Integer.parseInt(parts[1].substring(1));
                                                if(!newOrder){
                                                    if(store.carts[index_Order].getQuantity() - quantity_input <= 0){
                                                        int quantity_current = store.carts[index_Order].getQuantity();
                                                        
                                                        store.carts[index_Order].setQuantity(store.carts[index_Order].getQuantity() - quantity_input);
                                                        store.updateFile_Order();

                                                        store.products[Integer.parseInt(parts[0])-1].setQuantity(store.products[Integer.parseInt(parts[0])-1].getQuantity() + quantity_current);
                                                        store.updateFile_edit();
                                                    }else {
                                                        store.carts[index_Order].setQuantity(store.carts[index_Order].getQuantity() - quantity_input);
                                                        store.updateFile_Order();
    
                                                        store.products[Integer.parseInt(parts[0])-1].setQuantity(store.products[Integer.parseInt(parts[0])-1].getQuantity() + quantity_input);
                                                        store.updateFile_edit();
                                                    }
                                                }else {
                                                    invalid = true;
                                                }
                                            }else if(Integer.parseInt(parts[1]) >= 0){
                                                int quantity_input = Integer.parseInt(parts[1]);

                                                if(newOrder){
                                                    if(store.products[Integer.parseInt(parts[0])-1].getQuantity() - quantity_input < 0){
                                                        invalid = true;
                                                    }else {
                                                        store.creatNewOrder(userID_current, productID_choose, quantity_input);
    
                                                        store.products[Integer.parseInt(parts[0])-1].setQuantity(store.products[Integer.parseInt(parts[0])-1].getQuantity() - quantity_input);
                                                        store.updateFile_edit();
                                                    }
                                                }else {
                                                    int quantity_current = store.carts[index_Order].getQuantity() + store.products[Integer.parseInt(parts[0])-1].getQuantity();

                                                    if(quantity_current - quantity_input < 0){
                                                        invalid = true;
                                                    }else {
                                                        store.carts[index_Order].setQuantity(quantity_input);
                                                        store.updateFile_Order();

                                                        store.products[Integer.parseInt(parts[0])-1].setQuantity(quantity_current - quantity_input);
                                                        store.updateFile_edit();
                                                    }
                                                }
                                            }else {
                                                invalid = true;
                                            }
                                        }else {
                                            invalid = true;
                                        }
                                        if(invalid){
                                            System.out.println("Your input is invalid!");
                                        }
                                    }
                                }
                                else if(seStore.choose == 3){
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