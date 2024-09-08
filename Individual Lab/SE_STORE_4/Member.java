package SE_STORE_4;

public class Member {
    private String menberId;
    private String firstName;
    private String lastName;
    private String email;
    private String codePassword;
    private String tel;
    private Double point;

    public Member(){
        this.menberId = "0000";
        this.firstName = "";
        this.lastName = "";
        this.email = "***@***";
        this.codePassword = "*******";
        this.tel = "**********";
        this.point = 0.0;
    }

    public Member(String id, String fname, String lname, String email, String codePW, String tel, double point){
        this.menberId = id;
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.codePassword = codePW;
        this.tel = tel;
        this.point = point;
    }

    public String getMenberId(){
        return menberId;
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
}
