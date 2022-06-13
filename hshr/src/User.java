public class User{
    public int orderNumber;
    public String phoneNumber;
    public String orderAddress;

    public User(){}

    public String showUser(){
        return "주문번호 : "+orderNumber+" 폰번호 : "+phoneNumber+" 주문주소 : "+orderAddress;
    }
}