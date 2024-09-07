package SE_STORE_1;
/************************************************************************************/ 
/* Program Assignment: SE STORE #1 (VIP)
/* Student ID: 66160237
/* Student Name: Wanasart Nianthasat
/* Date: 14/08/2024
/* Description: โปรแกรมแสดงข้อมูลสิ้นค้าที่มีในอยู่ระบบ V.2
/*************************************************************************************/

import java.util.InputMismatchException;
import java.util.Scanner;

public class SEStore {
    private int choose;
    public void showOptions() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.printf("===== SE STORE =====\n1. Show Product\n2. Exit\n====================\nSelect (1-2) : ");
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
    public static void main(String[] args) {
        SEStore seStore = new SEStore();        
        ProductManager store = new ProductManager();

        store.loadProductFromFile();
        while(true) {
            seStore.showOptions();

            if(seStore.choose == 1){
                store.printAllProducts();
            }else if(seStore.choose == 2){
                System.out.printf("===== SE STORE =====\nThank you for using our service :3");
                System.exit(0);
            }else {
                System.out.println();
            }
        }
    }
}