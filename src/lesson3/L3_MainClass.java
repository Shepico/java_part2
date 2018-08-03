package lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class L3_MainClass {
    public static void main(String[] args){
        taskOne();

    }

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
        for(int i=0;i<str.length; i++){
            hashStr.put(str[i], (hashStr.get(str[i]) == null) ? 1 : hashStr.get(str[i])+1);
        }

        for (HashMap.Entry<String, Integer> pair : hashStr.entrySet())
        {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + " : " + value);
        }
    }
}
