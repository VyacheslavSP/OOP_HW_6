public class MobilePhone extends NumberPhone { 
    public MobilePhone() {
        this.internationalСode = "+7"; 
    }

    public MobilePhone(Integer MediumCode, Integer PhoneBody) {
        this.internationalСode = "+7";
        this.MediumCode = MediumCode;
        this.PhoneBody = PhoneBody;
    }

    @Override
    public void ShowItem() {
        System.out.println("Этот телефон мобильный");
        System.out.println("+7" + Integer.toString(MediumCode) + Integer.toString(PhoneBody));
    }

}
