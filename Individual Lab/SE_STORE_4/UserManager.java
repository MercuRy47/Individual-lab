package SE_STORE_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    public Member[] users;
    public List<String> dataMember = new ArrayList<>();

    // โหลดข้อมูล Member ทั้งหมดจากไฟล์
    public void loadUserFromFile(){
        this.dataMember = new ArrayList<>();
        try(BufferedReader readFile = new BufferedReader(new FileReader("Individual Lab/SE_STORE_4/MEMBER.txt"))){
            String line;
            while((line = readFile.readLine()) != null) {
                dataMember.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        users = new Member[dataMember.size()];
        for(int i = 0; i < dataMember.size(); i++) {
            String[] parts = dataMember.get(i).split("\\s+");
            users[i] = new Member(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Double.parseDouble(parts[6]));
        }
    }

    // Check ว่าบัญชีผู้ใช้นั้น เข้าใช้ได้หรือไม่
    public boolean CheckStatus(String email){
        for(Member user: users){
            if(email.equals(user.getEmail())){
                if(user.getCodePassword().charAt(2) == '0'){
                    return true;
                }
            }
        }
        return false;
    }

    // Check ว่า Email กับ Password ตรงกับข้อมูลใน Data base หรือไม่
    public boolean CheckLogin(String email, String password){
        if(CheckEmail(email) && CheckPassword(password)){
            return true;
        }
        return false;
    }

    // ถอดรหัสจากใน Data base เป็นรหัสใช้ Login
    public String Decode(String password){
        String decode = "";
        for(int i = 0; i < password.length(); i++){
            if(i == 9 || i == 10 || i == 13 || i == 14 || i == 15 || i == 16){
                decode += password.charAt(i);
            }
        }
        return decode;
    }

    // เปลี่ยน format ขอชื่อ
    public String NewName(String fname, String lname){
        String newName = lname.charAt(0) + ". " + fname;
        return newName;
    }

    // Sensor Email
    public String SensorEmail(String email){
        String sensorEmail = "";
        String[] parts = email.split("@");
        for(int i = 0; i < parts[0].length(); i++){
            if(i < 2){
                sensorEmail += parts[0].charAt(i);
            }else {
                if(i == 5) break;
                sensorEmail += "*";
            }
        }
        sensorEmail += "@";
        for(int i = 0; i < parts[1].length(); i++){
            if(i < 2){
                sensorEmail += parts[1].charAt(i);
            }else {
                if(i == 5) break;
                sensorEmail += "*";
            }
        }
        return sensorEmail;
    }

    // เปลี่ยน format ของเบอร์โทร
    public String NewTel(String tel){
        String newTel = "";
        for(int i = 0; i < tel.length(); i++){
            newTel += tel.charAt(i);
            if(i == 2 || i == 5 ) {
                newTel += "-";
            }
        }
        return newTel;
    }

    // แสดงข้อมูลบัญชีผู้ใช้ที่กรอกเข้ามา
    public void printInfo(String email, String password){
        for(Member user : users){
            if(email.equals(user.getEmail()) && password.equals(Decode(user.getCodePassword()))) {
                System.out.println("===== SE STORE =====");
                System.out.println("Hello, " + NewName(user.getFirstName(), user.getLastName()));
                System.out.println("Email: " + SensorEmail(user.getEmail()));
                System.out.println("Phone: " + NewTel(user.getTel()));
                //System.out.println("Password: " + Decode(user.getCodePassword()));
                System.out.printf("You have %d Point\n", user.getPoint().intValue());
                System.out.println("====================");
            }
        }
    }

    // แสดงข้อมูลบัญชีผู้ใช้ทั้งหมด ที่มีใน Data base
    public void printAllInfo(){
        for(Member user : users){
            System.out.println("===== SE STORE =====");
            System.out.println("Hello, " + NewName(user.getFirstName(), user.getLastName()));
            System.out.println("Email: " + SensorEmail(user.getEmail()));
            System.out.println("Phone: " + NewTel(user.getTel()));
            //System.out.println("Password: " + Decode(user.getCodePassword()));
            System.out.printf("You have %d Point\n", user.getPoint().intValue());
            System.out.println("====================");
        }
    }

    // Check ว่า Email ตรงกับที่มีอยู่ใน Data base หรือไม่
    public boolean CheckEmail(String email){
        for (Member user : users) {
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    // Check ว่า Password ถูกต้องหรือไม่
    public boolean CheckPassword(String password){
        for (Member user : users) {
            if(Decode(user.getCodePassword()).equals(password)){
                return true;
            }
        }
        return false;
    }
}
