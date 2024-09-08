package SE_STORE_1;
/************************************************************************************/ 
/* Program Assignment: SE STORE #1 (VIP)
/* Student ID: 66160237
/* Student Name: Wanasart Nianthasat
/* Date: 14/08/2024
/* Description: โปรแกรมแสดงข้อมูลสิ้นค้าที่มีในอยู่ระบบ
/*************************************************************************************/

import java.util.Scanner;

public class SEStore2 {
    private int choose;
    public void showOptions() {
        Scanner input = new Scanner(System.in);
        System.out.printf("===== SE STORE =====\n1. Show Product\n2. Exit\n====================\nSelect (1-2) : "); 
        this.choose = input.nextInt();
    }
    public static void main(String[] args) {
        SEStore2 seStore = new SEStore2();        
        ProductManager store = new ProductManager();

        store.loadProductFromFile();

        // ลูปรับค่าที่ input เข้ามา
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