import java.io.PrintWriter;
import java.util.UUID;

public class PhoneBookItem { // индивудальная компонента для хранения "единицы" телефонной книги
    public NumberPhone numberPhone;
    public Person person;
    private UUID ID_item;

    public PhoneBookItem(NumberPhone numberPhone, Person person) {
        this.numberPhone = numberPhone;
        this.person = person;
        ID_item = UUID.randomUUID();
        ;
    }

    public UUID getID_item() {
        return ID_item;
    }

    public NumberPhone getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(NumberPhone numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void SaveXLS(PrintWriter writer, String Path,boolean full) {
        StringBuilder sb = new StringBuilder();
        if(full){
        sb.append(this.ID_item);
        sb.append(',');
        }
        sb.append(this.person.getName());
        sb.append(',');
        if(full){
        sb.append(this.numberPhone.getInternationalСode());
        sb.append(',');
        sb.append(this.numberPhone.getMediumCode());
        sb.append(',');
        sb.append(this.numberPhone.getPhoneBody());
        sb.append(',');
        sb.append(this.person.getID_person());
        }else{
            sb.append(this.numberPhone.getInternationalСode());
            sb.append(this.numberPhone.getMediumCode());
            sb.append(this.numberPhone.getPhoneBody()); 
        }
        sb.append('\n');
        writer.write(sb.toString());
    }
}
