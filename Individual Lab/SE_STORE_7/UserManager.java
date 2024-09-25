package SE_STORE_7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserManager {
    public Member[] users;
    public List<String> dataMember = new ArrayList<>();

    // Path
    private String pathMember = "Individual Lab/SE_STORE_7/MEMBER.txt";
    
    // โหลดข้อมูล Member ทั้งหมดจากไฟล์
    public void loadUserFromFile(){
        this.dataMember = new ArrayList<>();
        try(BufferedReader readFile = new BufferedReader(new FileReader(pathMember))){
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
        if(CheckEmail(email) && CheckPassword(password, email)){
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
        checkRole();
        for(Member user : users){
            if(email.equals(user.getEmail()) && password.equals(Decode(user.getCodePassword()))) {
                System.out.println("===== SE STORE =====");
                System.out.println("Hello, " + NewName(user.getFirstName(), user.getLastName()) + " (" + user.getRole().toUpperCase() + ")");
                System.out.println("Email: " + SensorEmail(user.getEmail()));
                System.out.println("Phone: " + NewTel(user.getTel()));
                //System.out.println("Password: " + Decode(user.getCodePassword()));
                System.out.printf("You have %d Point\n", user.getPoint().intValue());
                System.out.println("====================");
            }
        }
    }

    // Check role
    public void checkRole(){
        for(Member user : users){
                switch (user.getCodePassword().charAt(6)) {
                    case '0':
                        user.setRole("Staff");
                        break;
                    case '1':
                        user.setRole("Regular");
                        break;
                    case '2':
                        user.setRole("Silver");
                        user.setDiscount(0.05);
                        break;
                    case '3':
                        user.setRole("Gold");
                        user.setDiscount(0.1);
                        break;
                    default:
                        break;
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
    public boolean CheckPassword(String password, String email){
        for (Member user : users) {
            if (user.getEmail().equals(email)) {
                if (Decode(user.getCodePassword()).equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    
    // Write File
    public void AddNewUserToFile(String newLine){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathMember, true))) {
            writer.write(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatNewUser(String fname, String lname, String email, String tel){
        String newUser = String.valueOf(9000 + dataMember.size() + 1) + "\t" + fname + "\t" + lname + "\t" + email + "\t" + generatePassWord() + "\t" + tel + "\t" + (0.00) + "\n";

        AddNewUserToFile(newUser);
        updateFile();
    }

    public void updateFile(){
        loadUserFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathMember))) {
            for (int i = 0; i < dataMember.size(); i++) {
                String newUserID = String.valueOf(9001 + i);
                users[i].setID(newUserID);
                writer.write(newUserID + "\t" + users[i].getFirstName() + "\t" + users[i].getLastName() + "\t" + users[i].getEmail() + "\t" + users[i].getCodePassword() + "\t" + users[i].getTel() + "\t" + users[i].getPoint() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generatePassWord(){
        Random random = new Random();
        String newPassword = "";
        
        String letterPW = "";
        for(int i = 0; i < 19; i++){
            letterPW += (char) (random.nextInt(26) + 'A');
        }
        String numPW = "";
        for(int i = 0; i < 6; i++){
            numPW += random.nextInt(10);
        }
        
        StringBuilder sb = new StringBuilder(letterPW);
        sb.setCharAt(2, '1');
        sb.setCharAt(6, '1');
        int[] positions = {9, 10, 13, 14, 15, 16};
        for (int i = 0; i < positions.length; i++) {
            sb.setCharAt(positions[i], numPW.charAt(i));
            }
        newPassword = sb.toString();
        return newPassword;
    }

    public void printAllMember(){
        String format = "%-5s %-30s %-10s%n";
        System.out.println("===== SE STORE's Member =====");
        System.out.printf(format, "#", "Name", "Email");
        int index = 1;
        for(Member user: users){
            System.out.printf(format, index, user.getFirstName() + " " + user.getLastName(), user.getEmail());
            index++;
        }
        System.out.println("===========================================");
    }

    public void updateFile_edit(){
        Member[] backUpUsers = users;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathMember, false))) {
            for (int i = 0; i < dataMember.size(); i++) {
                String newUserID = String.valueOf(9001 + i);
                backUpUsers[i].setID(newUserID);
                writer.write(newUserID + "\t" + backUpUsers[i].getFirstName() + "\t" + backUpUsers[i].getLastName() + "\t" + backUpUsers[i].getEmail() + "\t" + backUpUsers[i].getCodePassword() + "\t" + backUpUsers[i].getTel() + "\t" + backUpUsers[i].getPoint() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
