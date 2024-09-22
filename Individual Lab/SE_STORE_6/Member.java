package SE_STORE_6;

public class Member {
    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String codePassword;
    private String tel;
    private Double point;
    private String role;
    private double discount;
    

    public Member(){
        this.memberId = "0000";
        this.firstName = "";
        this.lastName = "";
        this.email = "***@***";
        this.codePassword = "*******";
        this.tel = "**********";
        this.point = 0.0;
        this.discount = 0.0;
    }

    public Member(String id, String fname, String lname, String email, String codePW, String tel, double point){
        this.memberId = id;
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.codePassword = codePW;
        this.tel = tel;
        this.point = point;
    
    }

    public String getMemberId(){
        return memberId;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getCodePassword(){
        return codePassword;
    }
    public String getTel(){
        return tel;
    }
    public Double getPoint(){
        return point;
    }
    public String getRole(){
        return role;
    }
    public Double getDiscount(){
        return discount;
    }

    public void setID(String id){
        this.memberId = id;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCodepassword(String code){
        this.codePassword = code;
    }
    public void tel(String tel){
        this.tel = tel;
    }
    public void role (String role){
        this.role = role;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
}
