import java.util.Iterator;
import java.util.Scanner;

public class Menu implements BuildAndPrintInterface {
    public Menu(String name, Scanner in) {
        Name = name;
        this.nowPersonList = new PersonList<>();
        this.nowBook = new PhoneBook<>("");
        this.in = in;

    }

    public String Name;
    private PersonList<Person> nowPersonList;
    private PhoneBook<PhoneBookItem> nowBook;
    public Scanner in;

    public boolean run() {
        boolean exit = false;
        int answer = 5;
        System.out.println(
                " Введите команду: 1- Создать новую книгу,2- добавть в текущую книгу, 3- импортировать книгу, 4- сохранить книгу,5-показать всех людей в этой (не импортированой) книге в алфавитном порядке, 6- выход ");

        String answerTmp = this.in.nextLine();
        try {
            answer = Integer.parseInt(answerTmp);
        } catch (Exception e) {
            answer = Integer.parseInt(this.in.nextLine());
            System.out.println("непредвиденный вылет");
        }
        switch (answer) {
            case 1:
                Build_newBook();
                break;
            case 2:
                AppendBookNow(this.nowPersonList, this.nowBook);
                break;
            case 3:
                this.nowBook.ImportFullInfomation("FullFormatWthitouthSplit.csv");
                this.nowBook.ShowItem();
                break;
            case 4:
                boolean FullValue = false;
                boolean delimeter = false;
                System.out.println("Введите вариант сохранения: 1- Все данные,2- короткие записи");
                String TmpAnswer = in.nextLine();
                int Answer = Integer.parseInt(TmpAnswer);
                if (Answer == 1) {
                    FullValue = true;
                }
                System.out.println("Введите вариант сохранения: 1- сразделителем,2- без разделителя");
                TmpAnswer = in.nextLine();
                Answer = Integer.parseInt(TmpAnswer);
                if (Answer == 1) {
                    delimeter = true;
                }
                this.nowBook.SaveCSV(delimeter, FullValue);
                break;
            case 5:
                this.nowPersonList.Sorted();
                this.nowPersonList.ShowItem();
                break;
            case 6:
                exit = true;

        }
        return exit;
    }

    @Override
    public void Build_newBook() {
        System.out.println(" Введите имя нового телефоннного справочника ");
        String tmpNameBook = in.nextLine().trim();
        this.nowBook.setName(tmpNameBook);
        System.out.println(" Введите имя человека ");
        String tmpNamePersonNew = in.nextLine();
        Person nowNewPerson = new Person(tmpNamePersonNew);
        this.nowPersonList.appendPersonToList(nowNewPerson);
        BuildRowsFromMenu(this.nowBook, nowNewPerson);

    }

    @Override
    public void AppendBookNow(PersonList<Person> PersonList, PhoneBook<PhoneBookItem> PhoneBook) {

        System.out.println(" Введите имя человека ");
        String tmpNamePerson = in.nextLine();
        Person nowPerson = new Person(tmpNamePerson);
        if (PersonList.size() > 0) {
            if (PersonList.contains(tmpNamePerson)) {
                Iterator<Person> tmpiter = PersonList.listPerson.iterator();
                while (tmpiter.hasNext()) {
                    Person tmpItem = tmpiter.next();
                    if (tmpItem.getName().equals(tmpNamePerson)) {
                        nowPerson = tmpItem;
                        System.out.println("найден в базе");
                        break;
                    }
                }
            } else {
                nowPerson.setName(tmpNamePerson);
                PersonList.appendPersonToList(nowPerson);
                System.out.println("новый персоонаж");
            }
        }
        BuildRowsFromMenu(PhoneBook, nowPerson);

    }

    public void BuildRowsFromMenu(PhoneBook<PhoneBookItem> nowBook, Person nowNewPerson) {

        System.out.println(
                " Введите номер телефона: международный код (+7 для мобилных и 8 для стационарных)");

        String tmpInternCode = this.in.nextLine();
        System.out.println(" Введите номер телефона: код города ");
        int tmpMediumCode = Integer.parseInt(this.in.nextLine());
        System.out.println(" Введите номер телефона: основной номер ");
        int tmpPhoneBody = Integer.parseInt(this.in.nextLine());
        if (tmpInternCode.equals("+7")) {
            MobilePhone nowMobilePhone = new MobilePhone(tmpMediumCode, tmpPhoneBody);
            PhoneBookItem nowRow = new PhoneBookItem(nowMobilePhone, nowNewPerson);
            nowBook.appendRow(nowRow);
        } else {
            StationaryPhone nowStationaryPhone = new StationaryPhone(tmpMediumCode, tmpPhoneBody);
            PhoneBookItem nowRow = new PhoneBookItem(nowStationaryPhone, nowNewPerson);
            nowBook.appendRow(nowRow);
        }

        nowBook.ShowItem();

    }

}
