public class Book {
    int id;
    String name; 
    String author;
    String category;
    String url;
    int price;
    int amount;    

    public Book(){}

    public Book(int id, String name, String author, String category, String url, int price, int amount ){
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.url = url;
        this.price = price;
        this.amount = amount;
    }

    public int get_id(){
        return this.id;
    }

    public void set_name(String name){
        this.name = name;
    }

    public String get_name(){
        return this.name;
    }
    
    public void set_author(String author){
        this.author = author;
    }

    public String get_author(){
        return this.author;
    }

    public void set_category(String category){
        this.category = category;
    }

    public String get_category(){
        return this.category;
    }

    public void set_url(String url){
        this.url = url;
    }

    public String get_url(){
        return this.url;
    }

    public void set_price(int price){
        this.price = price;
    }

    public int get_price(){
        return this.price;
    }

    public void set_amount(int amount){
        this.amount = amount;
    }

    public int get_amount(){
        return this.amount;
    }
}
