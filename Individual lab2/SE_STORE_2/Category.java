package SE_STORE_2;

public class Category {
    private String categoryId;
    private String categoryName;

    public Category(){
        this.categoryId = "";
        this.categoryName = "";
    }

    public Category(String categoryId, String categoryName){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryId(){
        return categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }
}
