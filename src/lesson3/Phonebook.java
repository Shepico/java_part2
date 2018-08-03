package lesson3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Phonebook {
    private HashMap<String,LinkedHashSet<String>> surname;

    Phonebook() {
        this.surname = new HashMap<>();
    }

    void add(String surname, String number) {

        LinkedHashSet<String> phone = findNumber(surname);
        if (phone == null) {
            phone = new LinkedHashSet<>();
            phone.add(number);
        }else {
            phone.add(number);
        }
        this.surname.put(surname,phone);
    }

    void get(String surname) {
        LinkedHashSet<String> phone = findNumber(surname);
        if (phone == null) {
            System.out.println("Номера по фамилии " + surname + " не найдены");
        }else {
            System.out.println("По фамилии " + surname + " найдены следующие номера:");

            Iterator<String> iterator = phone.iterator();
            while(iterator.hasNext()) {
                System.out.printf("%-15s%n",iterator.next());
            }
        }
    }

    private boolean findSurname(String surname){
        if (this.surname != null) {
            return this.surname.containsKey(surname);
        }
        return false;
    }

    private LinkedHashSet findNumber(String surname) {
        if (findSurname(surname)) {
            return this.surname.get(surname);
        }
        return null;
    }

}
