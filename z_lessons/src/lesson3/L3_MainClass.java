package lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class L3_MainClass {
    public static void main(String[] args){
        taskOne();

        //Задача 2
        Phonebook pbook = new Phonebook();
        pbook.get("Петров");
        pbook.add("Иванов", "+79171332255");
        pbook.add("Петров", "+79171333377");
        pbook.add("Сидоров", "+79171334499");
        pbook.add("Иванов", "+79171335511");
        pbook.add("Иванов", "+79171332259");

        pbook.get("Иванов");

    }

    // Задача 1
    static void taskOne(){
        String[] str = new String[25];
        str = arrCreate(str);
        uniqueWord(str);

    }

    static String[] arrCreate(String[] str){
        String sourceStr = "качестве завхозов механиков доездке лагерю техкому этот раз ничего интересного было механиков пожалуй того что нами качестве завхозов механиков поехали два Бориных приятеля был Карелии";
        str = sourceStr.split(" ",str.length);
        return str;
    }

    static void uniqueWord(String[] str) {
        HashMap<String, Integer> hashStr = new HashMap<>();
        for(int i=0; i<str.length; i++){
           hashStr.put(str[i], (hashStr.get(str[i]) == null) ? 1 : hashStr.get(str[i])+1);
        }

        for (HashMap.Entry<String, Integer> pair : hashStr.entrySet())
        {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + " : " + value);
        }
    }

    //Конец задачи 1
}
