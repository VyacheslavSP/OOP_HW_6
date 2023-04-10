import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class PhoneBook<T extends PhoneBookItem> implements ImportExport, ShowInterface {
    public PhoneBookItem bookRow;
    public String Name;
    public ArrayList<T> list;
    private static MobilePhone newMobilePhone;
    private static StationaryPhone newStationaryPhone;

    public PhoneBook(String name) { // новая книга-новый список
        Name = name;
        this.list = new ArrayList<T>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public void appendRow(T bookRow) {
        ArrayList<T> tmplist = this.list;
        tmplist.add(bookRow);
        setList(tmplist);
    }

    public void deleteRow(UUID ID_item) {
        ArrayList<T> tmplist = this.list;
        Iterator<T> iter = tmplist.iterator();
        while (iter.hasNext()) {
            T elem = iter.next();
            if (elem.getID_item() == ID_item) {
                iter.remove();
            }
        }
        this.list = tmplist;
    }

    @Override
    public void SaveCSV(boolean Split, boolean Full) {
        {
            PrintWriter writer;
            String filename = null;
            if (Full) {
                if (Split) {
                    filename = "FullFormatWthithSplit.csv";
                } else {
                    filename = "FullFormatWthitouthSplit.csv";
                }
            } else {
                filename = "ShortFormat.csv";
            }
            try {
                writer = new PrintWriter(filename);
                StringBuilder sb = new StringBuilder();
                if (Full) {
                    sb.append("ID item");
                    sb.append(',');
                }
                sb.append("Name");
                sb.append(',');
                if (Full) {
                    sb.append("InternationalСode");
                    sb.append(',');
                    sb.append("MediumCode");
                    sb.append(',');
                    sb.append("PhoneBody");
                    sb.append(',');
                    sb.append("ID_person(not for users)");
                } else {
                    sb.append("full phone number");
                }
                sb.append('\n');
                if (Split) {
                    sb.append('\n');
                }
                writer.write(sb.toString());

                for (T t : this.list) {
                    t.SaveXLS(writer, filename, Full);
                    if (Split) {
                        writer.write('\n'); // через пустую строчку
                    }
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<String> find_by_id_item(ArrayList<ArrayList<String>> records) {
        Set<String> objectsIds = new HashSet<>();

        for (ArrayList<String> object : records) {
            objectsIds.add(object.get(0)); // ID записи всегда 0
        }
        ArrayList<String> itemsID = new ArrayList<>();
        for (T row : this.list) {
            itemsID.add(row.getID_item().toString());
        }
        for (String object : itemsID) {
            if (objectsIds.contains(object)) {
                objectsIds.remove(object);
            } else {
                objectsIds.add(object);
            }
        }
        return objectsIds; /// массив строк ID_записей на добаление

    }

    @Override
    public void ImportFullInfomation(String filename) { // импорт как всегда перегруженный
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        records.remove(0); // удалили шапка
        Set<String> objectsIds = find_by_id_item(records);
        boolean mobile = true;
        if (objectsIds.size() != 0) { // если что то есть
            for (String string_ID : objectsIds) {
                for (ArrayList<String> rows : records) {
                    if (rows.get(0) == string_ID) {
                        if (rows.get(2).indexOf("+7") != -1) { // с текущими условиями проверка на стационарность через
                                                               // код
                            newMobilePhone = new MobilePhone(Integer.parseInt(rows.get(3)),
                                    Integer.parseInt(rows.get(4)));
                            mobile = true;
                        } else {
                            newStationaryPhone = new StationaryPhone(Integer.parseInt(rows.get(3)),
                                    Integer.parseInt(rows.get(4)));
                            mobile = !mobile;
                        }

                        Person NewPerson = new Person(null);
                        NewPerson.setName(rows.get(1));
                        NewPerson.setID_person((rows.get(3)));
                        if (mobile) {
                            PhoneBookItem bookRowNow = new PhoneBookItem(newMobilePhone, NewPerson);
                            this.appendRow((T) bookRowNow);
                        } else {
                            PhoneBookItem bookRowNow = new PhoneBookItem(newStationaryPhone, NewPerson);
                            this.appendRow((T) bookRowNow);
                        }
                    }
                }
            }

        }

    }

    private ArrayList<String> getRecordFromLine(String line) {
        ArrayList<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    @Override
    public void ShowItem() {
        Iterator<T> iter = this.list.iterator();
        while (iter.hasNext()) {
            T elem = iter.next();
            System.out.println("ID записи= " + elem.getID_item().toString());
            System.out.println("номер телефона= " + elem.numberPhone.getInternationalСode()
                    + Integer.toString(elem.numberPhone.getMediumCode())
                    + Integer.toString(elem.numberPhone.PhoneBody));
            System.out.println("Кому пренадлежит= " + elem.person.getName());
        }
    }
}
