
import java.util.Comparator;
import java.util.UUID;

public class Person implements ShowInterface {
    public String name;
    private String ID_person;

    public Person(String name) {
        this.name = name;
        this.ID_person = UUID.randomUUID().toString();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID_person() {
        return ID_person;
    }

    public void setID_person(String iD_person) {
        ID_person = iD_person;
    }

    @Override
    public void ShowItem() {
        System.out.println("ID person=" + getID_person());
        System.out.println("Имя =" + getName());
    }
    public static Comparator<Person> NameComparator = new Comparator<>() {
        public int compare(Person o1, Person o2) {
            return o2.getName().length() - o1.getName().length();
        }
    };
}
