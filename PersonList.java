import java.util.ArrayList;
import java.util.Iterator;

public class PersonList<T extends Person> implements ShowInterface, SortInterface {
    public PersonList(ArrayList<T> listPerson) {
        this.listPerson = listPerson;
    }

    public PersonList() {
        this.listPerson = new ArrayList<T>();
    }

    public ArrayList<T> listPerson;

    public void appendPersonToList(T Person) {
        this.listPerson.add(Person);

    }

    public void deletePerson(T Person) {
        Iterator<T> tmpiter = this.listPerson.iterator();
        while (tmpiter.hasNext()) {
            Person tmpItem = tmpiter.next();
            if (tmpItem.getID_person() == Person.getID_person()) {
                tmpiter.remove();
            }
        }
    }

    public int size() {
        return this.listPerson.size();
    }

    public boolean contains(String person) {
        boolean answer = false;
        Iterator<T> tmpiter = this.listPerson.iterator();
        while (tmpiter.hasNext()) {
            Person tmpItem = tmpiter.next();
            if (tmpItem.getName().equals(person)) {
                answer = true;
            } else {
                answer = false;
            }
        }
        return answer;
    }

    public ArrayList<T> getListPerson() {
        return listPerson;
    }

    public void setListPerson(ArrayList<T> listPerson) {
        this.listPerson = listPerson;
    }

    @Override
    public void ShowItem() {
        Iterator<T> tmpiter = this.listPerson.iterator();
        while (tmpiter.hasNext()) {
            Person tmpItem = tmpiter.next();
            tmpItem.ShowItem();
        }
    }

    @Override
    public void Sorted() {
        this.listPerson.sort(Person.NameComparator);
    }
}